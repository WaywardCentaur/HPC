package usfq.proyecto.huasipichai.ui

import android.content.Context
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.fragment_event_detail.view.*

import usfq.proyecto.huasipichai.R
import usfq.proyecto.huasipichai.Singleton

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [eventDetail.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [eventDetail.newInstance] factory method to
 * create an instance of this fragment.
 */
class eventDetail : Fragment() {

    // TODO: Rename and change types of parameters

    val storage = FirebaseStorage.getInstance()
    val storageRef = storage.reference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view: View = inflater!!.inflate(R.layout.fragment_event_detail, container, false);
        val ONE_MEGABYTE: Long = 1024 * 1024
        val pathReference = storageRef.child(Singleton.get("event")!!.nombre+Singleton.get("event")!!.fecha+".jpg")
        pathReference.getBytes(ONE_MEGABYTE).addOnSuccessListener {
            val bmp = BitmapFactory.decodeByteArray(it, 0, it.size)
            view.imageView.setImageBitmap(bmp)
        }.addOnFailureListener {
            // Handle any errors
        }
        view.textView1.text = Singleton.get("event")!!.nombre
        view.textView2.text = Singleton.get("event")!!.price
        view.textView3.text = Singleton.get("Event")!!.cat

        return view
    }


    companion object {



        fun newInstance(): eventDetail {
            val fragment = eventDetail()
            return fragment
        }
    }
}// Required empty public constructor
