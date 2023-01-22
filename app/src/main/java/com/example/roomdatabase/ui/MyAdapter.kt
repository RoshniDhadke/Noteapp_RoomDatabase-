package com.example.roomdatabase.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdatabase.R
import com.example.roomdatabase.db.Entity
import kotlinx.android.synthetic.main.note_list.view.*

class MyAdapter( val note:List<Entity>): RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    inner class MyViewHolder(val view: View) :RecyclerView.ViewHolder(view){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.note_list,parent,false)
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val n=note[position]
        holder.view.tv_title.text=n.title
        holder.view.tv_note.text=n.note
        holder.view.abc.setOnClickListener {
        val action=HomeFragmentDirections.actionAddNote()
            action.note=n
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return note.size
    }
}


