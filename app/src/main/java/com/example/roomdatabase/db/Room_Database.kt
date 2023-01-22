package com.example.roomdatabase.db

import android.content.Context
import android.os.Build.VERSION
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database( entities = [Entity::class],version=1)
 abstract class Room_Database :RoomDatabase(){
     abstract fun dao():DAO
//everything within companion object will be visible to other classes
     companion object{
         @Volatile  //volatile variable means it will made immediate visible  to all thread and volatile instance will be null
         private var instance:Room_Database?=null//creating room_database as singleton class  bcoz this will create only one instance of its class
         private val LOCK=Any()

         operator fun invoke(context: Context)= instance?:synchronized(LOCK){
             instance?: builderDatabase(context).also {
                 instance=it
             }
         }
         private fun builderDatabase(context: Context)= Room.databaseBuilder(context.applicationContext,
         Room_Database::class.java,"roomDatabase").build()
     }
}