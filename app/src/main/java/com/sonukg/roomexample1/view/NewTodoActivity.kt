package com.sonukg.roomexample1.view

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import com.sonukg.roomexample1.R

class NewTodoActivity : AppCompatActivity() {
    var newTodo:EditText?=null
    var btnSave:Button?=null
    var btnCancel:Button?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_todo)
        btnSave=findViewById(R.id.bSave)
        btnCancel=findViewById(R.id.bCancel)
        newTodo=findViewById(R.id.newTodo)
        btnSave!!.setOnClickListener {

            val resultIntent = Intent()

            if (TextUtils.isEmpty(newTodo!!.text)) {
                setResult(Activity.RESULT_CANCELED, resultIntent)
            } else {
                val note = newTodo!!.text.toString()
                resultIntent.putExtra(TODO_ADDED, note)
                setResult(Activity.RESULT_OK, resultIntent)
            }

            finish()
        }

        btnCancel!!.setOnClickListener {
            finish()
        }

    }

    companion object{
        val TODO_ADDED = "new_todo"
    }
}