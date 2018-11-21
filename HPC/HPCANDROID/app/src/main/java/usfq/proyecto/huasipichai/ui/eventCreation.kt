package usfq.proyecto.huasipichai.ui

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.google.firebase.database.DatabaseReference
import usfq.proyecto.huasipichai.Event

import usfq.proyecto.huasipichai.R
import usfq.proyecto.huasipichai.Singleton
import usfq.proyecto.huasipichai.User
import java.text.SimpleDateFormat
import java.util.*

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [eventCreation.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [eventCreation.newInstance] factory method to
 * create an instance of this fragment.
 */
class eventCreation : Fragment(), View.OnClickListener  {


    var nom: EditText? = null
    var pre: EditText? = null
    var org: EditText? = null
    var desc: EditText? = null
    private lateinit var listener: OnCreate


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var v:View = inflater.inflate(R.layout.fragment_event_creation, container, false)
        nom = (v.findViewById<View>(R.id.nombreEvento) as EditText)
        pre = (v.findViewById<View>(R.id.precioE) as EditText)
        org = (v.findViewById<View>(R.id.orgE) as EditText)
        desc = (v.findViewById<View>(R.id.descE) as EditText)
        val button: Button = v!!.findViewById(R.id.button2) as Button
        button.setOnClickListener(this)
        return v

    }

    override fun onClick(v: View?) {

        create()
        listener.onCreate()
    }

    fun create(){
        val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
        val currentDate = sdf.format(Date())
        var event :Event = Event(nom?.text.toString(), currentDate, desc?.text.toString(), "Quito", Singleton.getU("user")!!, pre?.text.toString(), "Todos")

        fun loadDatabase(firebaseData: DatabaseReference) {
            val key = firebaseData.child("event-list").push().key
            firebaseData.child("salads").child(key!!).setValue(event)

        }
    }


    override fun onAttach(context: Context?) {
        super.onAttach(context)

    }


    companion object {

        // TODO: Rename and change types and number of parameters
        fun newInstance(): eventCreation {
            val fragment = eventCreation()
            return fragment
        }
    }

    interface OnCreate{
        fun onCreate()
    }
}// Required empty public constructor
