package usfq.proyecto.huasipichai.ui

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.GridView
import com.google.firebase.database.*


import usfq.proyecto.huasipichai.R
import usfq.proyecto.huasipichai.eventAdapter
import usfq.proyecto.huasipichai.Event
import com.google.firebase.database.DataSnapshot
import android.text.method.TextKeyListener.clear




class eventShow : Fragment() {

    var mDatabase: DatabaseReference? = null
    private lateinit var listener: OnEventSelected
    val events : ArrayList<Event> = arrayListOf()

    private var mListener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        val view: View = inflater!!.inflate(R.layout.fragment_event_show, container,
                false)
        val gridview: GridView = view.findViewById(R.id.gridview)
        gridview.adapter = eventAdapter(this.context!!, events)

        mDatabase = FirebaseDatabase.getInstance().getReference("event-list");

        return view

    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        dbListener()
        if (context is OnEventSelected) {
            listener = context


        } else {
            throw ClassCastException(context.toString() + " must implement .")
        }



    }

    fun dbListener(){
        val menuListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                events.clear()
                Log.d("TAG", "help")
                for (ds in dataSnapshot.children) {
                    val ev = ds.getValue<Event>(Event::class.java!!)
                    events.add(ev!!)
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                println("loadPost:onCancelled ${databaseError.toException()}")
            }
        }
        mDatabase?.addListenerForSingleValueEvent(menuListener)

    }


    override fun onDetach() {
        super.onDetach()
        mListener = null
    }


    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {

        fun newInstance(): eventShow {
            return eventShow()
        }
    }

    interface OnEventSelected{
        fun onEventSelected()
    }
}// Required empty public constructor
