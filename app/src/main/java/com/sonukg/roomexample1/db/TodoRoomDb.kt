package com.sonukg.roomexample1.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.sonukg.roomexample1.dao.TodoDao
import com.sonukg.roomexample1.model.Todo

@Database(entities = [Todo::class],version = 1)
abstract class TodoRoomDb : RoomDatabase() {

    abstract fun TodoDao() : TodoDao

    companion object{

        @Volatile
        private var todoRoomInstance:TodoRoomDb?=null

        internal fun getDatabase(context:Context) : TodoRoomDb?{
            if (todoRoomInstance==null){
                synchronized(TodoRoomDb::class.java){
                    todoRoomInstance=Room.databaseBuilder(
                        context.applicationContext,
                        TodoRoomDb::class.java,"todo_db"
                    ).build()
                }
            }
            return todoRoomInstance
        }
    }
}