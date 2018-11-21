package usfq.proyecto.huasipichai.ui

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.google.firebase.auth.FirebaseAuth

import usfq.proyecto.huasipichai.R

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [registration.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [registration.newInstance] factory method to
 * create an instance of this fragment.
 */
class registration : Fragment(), View.OnClickListener{


    var fbAuth = FirebaseAuth.getInstance()
    private var email: EditText? = null
    private var pass: EditText? = null
    private var passC: EditText? = null

    private var mListener: OnFragmentInteractionListener? = null
    private lateinit var listener: OnRegister
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view: View = inflater!!.inflate(R.layout.fragment_registration, container, false);
        val button: Button = view.findViewById(R.id.registration_b) as Button
        button.setOnClickListener(this)

        email = (view.findViewById<View>(R.id.email_r) as EditText)
        pass = (view.findViewById<View>(R.id.pass) as EditText)
        passC = (view.findViewById<View>(R.id.conf) as EditText)
        return view
    }
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.registration_b-> {
                print("login")

                if(pass?.text.toString()!= passC?.text.toString() && !TextUtils.isEmpty(pass?.text.toString())){
                    fbAuth!!
                            .createUserWithEmailAndPassword(email?.text.toString()!!, pass?.text.toString()!!)

                    this.listener.onRegister()
                }


            }
            else -> {
            }
        }
    }


    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnRegister) {
            this.listener = context
        } else {
            throw ClassCastException(context.toString() + " must implement OnRageComicSelected.")
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments](http://developer.android.com/training/basics/fragments/communicating.html) for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {

        fun newInstance(): registration {
            return registration()
        }
    }

    interface OnRegister{
        fun onRegister()
    }
}// Required empty public constructor
