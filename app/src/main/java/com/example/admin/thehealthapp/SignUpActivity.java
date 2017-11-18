package com.example.admin.thehealthapp;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class SignUpActivity extends AppCompatActivity {

    EditText username;
    EditText email;
Spinner dropdown;
    Spinner sex;
    private static final String TAG = SignUpActivity.class.getSimpleName();
    private static final int REQUEST_CODE_PICK_CONTACTS = 4;
    private Uri uriContact;
    public String contactID;     // contacts unique ID


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        username = (EditText) findViewById(R.id.username);
        email = (EditText) findViewById(R.id.email);

        //get the spinner from the xml.
        dropdown = (Spinner) findViewById(R.id.spinner1);
//create a list of items for the spinner.
        String[] items = new String[]{"A+", "A-", "B+", "B-", "O+", "O-", "AB+", "AB-"};
//create an adapter to describe how the items are displayed, adapters are used in several places in android.
//There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);
//set the spinners adapter to the previously created one.
        dropdown.setAdapter(adapter);

        //get the spinner from the xml.
        sex = (Spinner) findViewById(R.id.spinner2);
//create a list of items for the spinner.
        String[] genders = new String[]{"Male", "Female", "Others"};
//create an adapter to describe how the items are displayed, adapters are used in several places in android.
//There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, genders);
//set the spinners adapter to the previously created one.
        sex.setAdapter(adapter2);


    }


        public void onClickSelectContact(View btnSelectContact) {

            // using native contacts selection
            // Intent.ACTION_PICK = Pick an item from the data, returning what was selected.
            startActivityForResult(new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI), REQUEST_CODE_PICK_CONTACTS);
        }

        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);

            if (requestCode == REQUEST_CODE_PICK_CONTACTS && resultCode == RESULT_OK) {
                Log.d(TAG, "Response: " + data.toString());
                uriContact = data.getData();

                retrieveContactName();
                retrieveContactNumber();

            }
        }

        private void retrieveContactNumber() {

            String contactNumber = null;

            // getting contacts ID
            Cursor cursorID = getContentResolver().query(uriContact,
                    new String[]{ContactsContract.Contacts._ID},
                    null, null, null);

            if (cursorID.moveToFirst()) {

                contactID = cursorID.getString(cursorID.getColumnIndex(ContactsContract.Contacts._ID));
            }

            cursorID.close();

            Log.d(TAG, "Contact ID: " + contactID);

            // Using the contact ID now we will get contact phone number
            Cursor cursorPhone = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                    new String[]{ContactsContract.CommonDataKinds.Phone.NUMBER},

                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ? AND " +
                            ContactsContract.CommonDataKinds.Phone.TYPE + " = " +
                            ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE,

                    new String[]{contactID},
                    null);

            if (cursorPhone.moveToFirst()) {
                contactNumber = cursorPhone.getString(cursorPhone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            }

            cursorPhone.close();

            Log.d(TAG, "Contact Phone Number: " + contactNumber);
            Button button = (Button)findViewById(R.id.button);
            button.setText(contactNumber);

        }

        private void retrieveContactName() {

            String contactName = null;

            // querying contact data store
            Cursor cursor = getContentResolver().query(uriContact, null, null, null, null);

            if (cursor.moveToFirst()) {

                // DISPLAY_NAME = The display name for the contact.
                // HAS_PHONE_NUMBER =   An indicator of whether this contact has at least one phone number.

                contactName = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
            }

            cursor.close();

            Log.d(TAG, "Contact Name: " + contactName);


            Button next = (Button) findViewById(R.id.next);
            next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(SignUpActivity.this, Main2Activity.class);
                    intent.putExtra("name", username.getText().toString());
                    intent.putExtra("email", email.getText().toString());
                    intent.putExtra("blood", dropdown.getSelectedItem().toString());
                    intent.putExtra("sex", sex.getSelectedItem().toString());


                    startActivity(intent);
                }
            });

        }



}
