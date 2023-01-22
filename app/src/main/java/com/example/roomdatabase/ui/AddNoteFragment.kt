package com.example.roomdatabase.ui

import android.app.AlertDialog
import android.os.AsyncTask
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.loader.content.AsyncTaskLoader
import androidx.navigation.Navigation
import com.example.roomdatabase.R
import com.example.roomdatabase.db.Entity
import com.example.roomdatabase.db.Room_Database
import kotlinx.android.synthetic.main.fragment_add_note.*
import kotlinx.coroutines.launch


class AddNoteFragment : BaseFragment() {

   private var en:Entity?=null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_add_note, container, false)
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        arguments?.let {
           en=AddNoteFragmentArgs.fromBundle(it).note
            et_title.setText(en?.title)
            et_note.setText(en?.note)
        }
       // Room_Database(requireActivity()).dao().
        btn_done.setOnClickListener { view ->
            val ed_title=et_title.text.toString().trim()
            val ed_note=et_note.text.toString().trim()
            if(ed_title.isEmpty()){
                et_title.error="title required"
                et_title.requestFocus()
                return@setOnClickListener
            }
            if(ed_note.isEmpty()){
                et_note.error="title required"
                et_note.requestFocus()
                return@setOnClickListener
            }
            //Insert
            launch {

                context?.let {
                    val note = Entity(ed_title,ed_note)
                    if(en==null){
                        Room_Database(it).dao().insert(note)
                        it.toast("Note Saved")
                    }else{
                        note.id=en!!.id
                        Room_Database(it).dao().update(note)
                        it.toast("Note Updated")
                    }


                    val action=AddNoteFragmentDirections.actionSaveNote()
                    Navigation.findNavController(view).navigate(action)
                }
            }

      //  saveNote(note)
        }
    }

    private fun deleteNote() {
        AlertDialog.Builder(context).apply {
            setTitle("Are you Sure")
            setMessage("You cannot undo this operation")
            setPositiveButton("Yes"){ _, _ ->
                launch {
                    Room_Database(context).dao().delete(en!!)
                    val action=AddNoteFragmentDirections.actionSaveNote()
                    Navigation.findNavController(requireView()).navigate(action)
                    Toast.makeText(context,"Note deleted",Toast.LENGTH_SHORT).show()
                }
            }
            setNegativeButton("No"){_, _ ->

            }
        }.create().show()
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.delete -> if (en!=null) {
               deleteNote()
            }else{
                context?.toast("cannot delete")
            }
        }
        return super.onOptionsItemSelected(item)

    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu,menu)
    }
  /*  private fun saveNote(note:Entity){
        class SaveNote: AsyncTask<Void, Void, Void>(){
            override fun doInBackground(vararg p0: Void?): Void? {
               Room_Database(requireActivity()).dao().insert(note)
                return null
            }

            override fun onPostExecute(result: Void?) {
                super.onPostExecute(result)
                Toast.makeText(activity,"Note Saved",Toast.LENGTH_SHORT).show()
            }
        }

        SaveNote().execute()
    } */

}


