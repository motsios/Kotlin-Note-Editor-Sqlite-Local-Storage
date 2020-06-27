package com.example.recyclerviewer
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var btnStore: Button? = null
    private var btnget: Button? = null
    private var etname: EditText? = null
    private var ethobby: EditText? = null
    private var databaseHelper: DatabaseHelper? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        databaseHelper = DatabaseHelper(this)

        btnStore = findViewById(R.id.btnstore) as Button
        btnget = findViewById(R.id.btnget) as Button
        etname = findViewById(R.id.etname) as EditText
        ethobby = findViewById(R.id.ethobby) as EditText

        btnStore!!.setOnClickListener {
         var ok= databaseHelper!!.addUserDetail(etname!!.text.toString(), ethobby!!.text.toString())

            //ta arxikopoiei sto xml kena ksnaa
            etname!!.setText("")
            ethobby!!.setText("")
            if(ok.toString()!="-1")
            Toast.makeText(this@MainActivity, "Stored Successfully!", Toast.LENGTH_SHORT).show()
            else
            Toast.makeText(this@MainActivity, "Title is Empty!", Toast.LENGTH_SHORT).show()
        }
        btnget!!.setOnClickListener {
            val intent = Intent(this@MainActivity, GetAllUsersActivity::class.java)
            startActivity(intent)
        }
    }
}