package com.example.best7producttask

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.room.Room
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_update_card.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class UpdateCard : AppCompatActivity() {
    private lateinit var database: myDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_card)
        database = Room.databaseBuilder(
            applicationContext, myDatabase::class.java, "To_Do"
        ).build()
        val pos = intent.getIntExtra("id", -1)
        if (pos != -1) {
            val title = DataObject.getData(pos).title
            var ival = DataObject.getData(pos).intialvalue
            val fval = DataObject.getData(pos).finalvalue
            incdec.text = ival+"/"+fval

            increement.setOnClickListener {
                var x = ival.toInt()

                x=x+1;
                if(x>fval.toInt())
                {
                    Snackbar.make(it,"Already Completed",Snackbar.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                ival = x.toString()
                incdec.text = ival+"/"+fval

            }
            decreement.setOnClickListener {
                var x = ival.toInt()

                x=x-1;
                if(x<0)
                {
                    Snackbar.make(it,"Reached Minimum",Snackbar.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                ival = x.toString()
                incdec.text = ival+"/"+fval

            }

            delete_button.setOnClickListener {
                DataObject.deleteData(pos)
                GlobalScope.launch {
                    database.dao().deleteTask(
                        Entity(
                            pos + 1,
                            title,
                            ival,
                            fval
                        )
                    )
                }
                myIntent()
            }

            update_button.setOnClickListener {
                DataObject.updateData(
                    pos,
                    ival,
                    fval
                )
                GlobalScope.launch {
                    database.dao().updateTask(
                        Entity(
                            pos + 1,
                            title,
                            ival,
                            fval
                        )
                    )
                }
                myIntent()
            }

        }
    }

    fun myIntent() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)

    }
}