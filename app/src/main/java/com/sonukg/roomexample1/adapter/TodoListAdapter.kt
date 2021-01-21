package com.sonukg.roomexample1.adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sonukg.roomexample1.R
import com.sonukg.roomexample1.model.Todo
import com.sonukg.roomexample1.view.EditTodoActivity
import com.sonukg.roomexample1.view.MainActivity


class TodoListAdapter(private val mContext: Context,
       private val onDeleteClickListener: OnDeleteClickListener?): RecyclerView.Adapter<TodoListAdapter.TodoViewHolder>(){

    private var todoList:List<Todo> = mutableListOf()

    interface OnDeleteClickListener{
        fun onDeleteClickListener(todo: Todo)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TodoListAdapter.TodoViewHolder {
        val itemView=LayoutInflater.from(mContext).inflate(R.layout.list_item_recycleview,parent,false)
        return TodoViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TodoListAdapter.TodoViewHolder, position: Int) {
        val todo=todoList[position]
        holder.setData(todo.name,position)
        holder.setListener()
    }


    fun setTodos(todos: List<Todo>) {
        todoList = todos
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int =todoList.size

    inner class TodoViewHolder(view: View) : RecyclerView.ViewHolder(view){
        var pos:Int=0
        private var note:TextView=itemView.findViewById(R.id.note)
        private var deleteIcon:ImageView=itemView.findViewById(R.id.ivDelete)
        fun setData(todo: String,position: Int){

            note.setText(todo)
            pos=position
        }

        fun setListener(){
            itemView.setOnClickListener {
                val intent=Intent(mContext,EditTodoActivity::class.java)
                intent.putExtra("todo_id",todoList[pos].id)
                intent.putExtra("todo_name" ,todoList[pos].name)
                (mContext as Activity).startActivityForResult(intent,MainActivity.UPDATE_NOTE_ACTIVITY_REQUEST_CODE)
            }

            deleteIcon.setOnClickListener {
                onDeleteClickListener?.onDeleteClickListener(todoList[pos])
            }
        }
    }
}