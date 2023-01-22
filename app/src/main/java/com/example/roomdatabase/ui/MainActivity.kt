package com.example.roomdatabase.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.room.RoomDatabase
import com.example.roomdatabase.R
import com.example.roomdatabase.db.Room_Database

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navController=Navigation.findNavController(this,R.id.fragment)
        NavigationUI.setupActionBarWithNavController(this,navController)

     //   val db=RoomDatabase.Builder(applicationContext,Room_Database::class.java,"roomDatabase")

    }

    override fun onSupportNavigateUp(): Boolean {
    // return Navigation.findNavController(Navigation.findNavController(this,R.id.fragment),null)
   return NavigationUI.navigateUp(Navigation.findNavController(this,R.id.fragment),null)
    }
}