package com.sonukg.roomexample1.view

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.sonukg.roomexample1.R

class EditTodoActivity : AppCompatActivity() {
    var id: String? = null
    var btnSave:Button?=null
    var btnCancel:Button?=null
    var editTodo:EditText?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_todo)
        btnSave=findViewById(R.id.editSave)
        btnCancel=findViewById(R.id.editCancel)
        editTodo=findViewById(R.id.editTodo)

        val bundle: Bundle? = intent.extras

        bundle?.let {
            id = bundle.getString("todo_id")
            val todo = bundle.getString("todo_name")
            editTodo!!.setText(todo)
        }

        btnSave!!.setOnClickListener {

            val updatedNote = editTodo!!.text.toString()
            val resultIntent = Intent()
            resultIntent.putExtra(TODO_ID, id)
            resultIntent.putExtra(UPDATED_TODO, updatedNote)
            setResult(Activity.RESULT_OK, resultIntent)

            finish()
        }

        btnCancel!!.setOnClickListener {
            finish()
        }
    }
    companion object {
        val TODO_ID = "todo_id"
        internal val UPDATED_TODO = "todo_name"
    }
}