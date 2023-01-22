package com.example.roomdatabase.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.roomdatabase.R
import com.example.roomdatabase.db.Room_Database
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.launch


class HomeFragment : BaseFragment() {

   // private lateinit var btn_add:Button
    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        rc.setHasFixedSize(true)
       // rc.layoutManager=StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL)
        rc.layoutManager=GridLayoutManager(context,2)

        launch {
            context?.let {
                val note= Room_Database(it).dao().getAll()
                rc.adapter=MyAdapter(note)
            }
        }



        btn_add.setOnClickListener {
            val action =HomeFragmentDirections.actionAddNote()
            Navigation.findNavController(it).navigate(action)
        }

    }



}