package com.example.recyclerviewer

import android.content.Intent
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import java.util.ArrayList

class GetAllUsersActivity : AppCompatActivity() {

    private var listView: ListView? = null
    private var userModelArrayList: ArrayList<UserModel>? = null
    private var databaseHelper: DatabaseHelper? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_all_users)

        listView = findViewById(R.id.lv) as ListView


        databaseHelper = DatabaseHelper(this)
        userModelArrayList = databaseHelper!!.getAllUsers
        listView!!.adapter = CustomAdapter(this,this!!.userModelArrayList!!)


        listView!!.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            val intent = Intent(this@GetAllUsersActivity, UpdateDeleteActivity::class.java)
            intent.putExtra("user", userModelArrayList!![position])
            startActivity(intent)
        }
    }
}