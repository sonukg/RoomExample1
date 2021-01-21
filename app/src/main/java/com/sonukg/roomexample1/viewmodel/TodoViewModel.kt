package com.sonukg.roomexample1.viewmodel

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.sonukg.roomexample1.dao.TodoDao
import com.sonukg.roomexample1.db.TodoRoomDb
import com.sonukg.roomexample1.model.Todo

class TodoViewModel(application: Application):AndroidViewModel(application) {

    private val todoDao :TodoDao
    internal val allTodos : LiveData<List<Todo>>

    init  {
        val todoRoomDb =TodoRoomDb.getDatabase(application)
        todoDao = todoRoomDb!!.TodoDao()
        allTodos = todoDao.allTodos
    }

    fun insertToDo(todo: Todo){
        InsertAsyncTask(todoDao).execute(todo)
    }
    fun updateToDo(todo: Todo){
        UpdateAsyncTask(todoDao).execute(todo)
    }
    fun deleteToDo(todo: Todo){
        DeleteAsyncTask(todoDao).execute(todo)
    }

    companion object{
        private class InsertAsyncTask(private val mTodoDao: TodoDao):AsyncTask<Todo,Void,Void>(){
            override fun doInBackground(vararg todos: Todo): Void? {
                mTodoDao.insert(todos[0])
                return null
            }

        }

        private class UpdateAsyncTask(private val mTodoDao: TodoDao):AsyncTask<Todo,Void,Void>(){
            override fun doInBackground(vararg todos: Todo): Void? {
                mTodoDao.update(todos[0])
                return null
            }
        }

        private class DeleteAsyncTask(private val mTodoDao: TodoDao):AsyncTask<Todo,Void,Void>(){
            override fun doInBackground(vararg todos: Todo): Void? {
                mTodoDao.delete(todos[0])
                return null
            }
        }
    }
}