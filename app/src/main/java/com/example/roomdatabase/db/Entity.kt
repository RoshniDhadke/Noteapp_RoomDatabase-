package com.example.roomdatabase.db

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import androidx.room.Entity
import java.io.Serializable

@Entity(tableName = "Note_App")
data class Entity(


    @ColumnInfo(name="Title")
    var title:String,

    @ColumnInfo(name="Note")
    var note:String

): Serializable {
    @PrimaryKey(autoGenerate = true)
     var id:Int=0
}
