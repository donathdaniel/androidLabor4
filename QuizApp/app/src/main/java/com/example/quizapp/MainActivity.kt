package com.example.quizapp

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

const val EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE"

class MainActivity : AppCompatActivity() {

    val REQUEST_SELECT_CONTACT = 1
    lateinit var contactUri: Uri


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.button)
        val editText = findViewById<EditText>(R.id.editTextTextPersonName)


        button.setOnClickListener {
            if(editText.text.isEmpty()){
                /*Toast.makeText(applicationContext, "You did not give a name!", Toast.LENGTH_SHORT).show();*/
                val intent = Intent(this, MainActivity2::class.java).apply {
                    putExtra(EXTRA_MESSAGE, "You did not give a name!")
                }
                startActivity(intent)
            }
            else{
                /*Toast.makeText(applicationContext, "Your name: ${editText.text}", Toast.LENGTH_SHORT).show();*/
                val intent = Intent(this, MainActivity2::class.java).apply {
                    putExtra(EXTRA_MESSAGE, "Your name: ${editText.text}")
                }
                startActivity(intent)
            }

        }

        val contactsButtonn = findViewById<Button>(R.id.contactsButton)

        contactsButtonn.setOnClickListener {

            val intent = Intent(Intent.ACTION_PICK)

            intent.type = ContactsContract.Contacts.CONTENT_TYPE

            startActivityForResult(intent, REQUEST_SELECT_CONTACT)
        }

    }

    @SuppressLint("MissingSuperCall")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode == REQUEST_SELECT_CONTACT && resultCode == Activity.RESULT_OK){
            contactUri = data!!.data!!
            getContactName()
        }
    }

    @SuppressLint("Range")
    private fun getContactName() {
        val cursor = contentResolver.query(contactUri,null,null,null,null)

        if(cursor!!.moveToFirst()){
            val contactName = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))

            val editTextTextPersonName = findViewById<EditText>(R.id.editTextTextPersonName)
            editTextTextPersonName.setText(contactName)

        }
    }
}