package com.example.recyclerviewer

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class UpdateDeleteActivity : AppCompatActivity() {

    private var userModel: UserModel? = null
    private var etname: EditText? = null
    private var ethobby: EditText? = null
    private var btnupdate: Button? = null
    private var btndelete: Button? = null
    private var databaseHelper: DatabaseHelper? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_delete)

        etname = findViewById(R.id.etname) as EditText
        ethobby = findViewById(R.id.ethobby) as EditText
        btndelete = findViewById(R.id.btndelete) as Button
        btnupdate = findViewById(R.id.btnupdate) as Button


        val intent = intent
        userModel = intent.getSerializableExtra("user") as UserModel

        etname!!.setText(userModel!!.getNames())
        ethobby!!.setText(userModel!!.getHobbys())

        databaseHelper = DatabaseHelper(this)

        btnupdate!!.setOnClickListener {
            databaseHelper!!.updateUser(userModel!!.getIds(), etname!!.text.toString(), ethobby!!.text.toString())
            Toast.makeText(this@UpdateDeleteActivity, "Updated Successfully!", Toast.LENGTH_SHORT).show()
            val intent = Intent(this@UpdateDeleteActivity, GetAllUsersActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }

        btndelete!!.setOnClickListener {
            databaseHelper!!.deleteUSer(userModel!!.getIds())
            Toast.makeText(this@UpdateDeleteActivity, "Deleted Successfully!", Toast.LENGTH_SHORT).show()
            val intent = Intent(this@UpdateDeleteActivity, GetAllUsersActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }

    }
}