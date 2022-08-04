package com.example.best7producttask


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_create_card.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class CreateCard : AppCompatActivity() {
    private lateinit var database: myDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_card)
        database = Room.databaseBuilder(
            applicationContext, myDatabase::class.java, "To_Do"
        ).build()
        save_button.setOnClickListener {
            if (create_title.text.toString().trim { it <= ' ' }.isNotEmpty()
                && intial_value.text.toString().trim { it <= ' ' }.isNotEmpty()
                && final_value.text.toString().trim { it <= ' ' }.isNotEmpty()
            ) {
                var title = create_title.getText().toString()
                var intialvalue = intial_value.getText().toString()
                var finalvalue = final_value.getText().toString()
                DataObject.setData(title, intialvalue , finalvalue )
                GlobalScope.launch {
                    database.dao().insertTask(Entity(0, title, intialvalue,finalvalue))

                }

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)

            }
        }
    }
}

