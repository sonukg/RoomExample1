package com.sonukg.roomexample1.view

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.sonukg.roomexample1.R
import com.sonukg.roomexample1.adapter.TodoListAdapter
import com.sonukg.roomexample1.model.Todo
import com.sonukg.roomexample1.viewmodel.TodoViewModel
import java.util.*

class MainActivity : AppCompatActivity(),TodoListAdapter.OnDeleteClickListener {
    private lateinit var todoViewModel:TodoViewModel
    private var recycleView:RecyclerView?=null
    private var fab:FloatingActionButton?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recycleView=findViewById(R.id.recycleView)
        fab=findViewById(R.id.fab)

        fab!!.setOnClickListener { View ->
        val intent=Intent(this,NewTodoActivity::class.java)
            startActivityForResult(intent, NEW_NOTE_ACTIVITY_REQUEST_CODE)
        }

        val todoListAdapter=TodoListAdapter(this,this)
        recycleView!!.adapter=todoListAdapter
        recycleView!!.layoutManager=LinearLayoutManager(this)

        todoViewModel=ViewModelProviders.of(this).get(TodoViewModel::class.java)

        todoViewModel.allTodos.observe(this, Observer { todos ->
            todos?.let {
                todoListAdapter.setTodos(todos)
            }
        })


    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == NEW_NOTE_ACTIVITY_REQUEST_CODE && resultCode == Activity.RESULT_OK) {

            // Code to insert note
            val todoId = UUID.randomUUID().toString()
            val todo = data!!.getStringExtra(NewTodoActivity.TODO_ADDED)?.let { Todo(todoId, it) }
            todo?.let { todoViewModel.insertToDo(it) }

            Toast.makeText(
                applicationContext,
                R.string.saved,
                Toast.LENGTH_LONG
            ).show()
        } else if (requestCode == UPDATE_NOTE_ACTIVITY_REQUEST_CODE && resultCode == Activity.RESULT_OK) {

            // Code to update the note
            val todo = data!!.getStringExtra(EditTodoActivity.TODO_ID)?.let {
                data.getStringExtra(EditTodoActivity.UPDATED_TODO)?.let { it1 ->
                    Todo(
                            it,
                            it1
                    )
                }
            }
            todo?.let { todoViewModel.updateToDo(it) }

            Toast.makeText(
                applicationContext,
                R.string.updated,
                Toast.LENGTH_LONG
            ).show()

        } else {
            Toast.makeText(
                applicationContext,
                R.string.not_saved,
                Toast.LENGTH_LONG
            ).show()
        }
    }
    companion object {
        private val NEW_NOTE_ACTIVITY_REQUEST_CODE = 1
        val UPDATE_NOTE_ACTIVITY_REQUEST_CODE = 2
    }


    override fun onDeleteClickListener(todo: Todo) {
        todoViewModel.deleteToDo(todo)
    }
}