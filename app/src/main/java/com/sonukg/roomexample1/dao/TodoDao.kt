package com.sonukg.roomexample1.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.sonukg.roomexample1.model.Todo

@Dao
interface TodoDao {
    @Insert
    fun insert(todo: Todo)

    @get:Query("SELECT * from todos")
    val allTodos : LiveData<List<Todo>>

    @Update
    fun update(todo: Todo)

    @Delete
    fun delete(todo: Todo)
}