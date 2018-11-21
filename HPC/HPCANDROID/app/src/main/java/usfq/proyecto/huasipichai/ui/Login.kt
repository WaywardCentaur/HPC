package usfq.proyecto.huasipichai.ui

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import usfq.proyecto.huasipichai.MainActivity

import usfq.proyecto.huasipichai.R
import usfq.proyecto.huasipichai.Singleton
import usfq.proyecto.huasipichai.User


class Login : Fragment(), View.OnClickListener {


    private lateinit var listener: OnLogin
    var fbAuth = FirebaseAuth.getInstance()
    var email: EditText? = null
    var pass: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view: View = inflater!!.inflate(R.layout.fragment_login, container, false);
        val button: Button = view.findViewById(R.id.email_sign_in_button) as Button
        button.setOnClickListener(this)
        val but: Button = view.findViewById(R.id.register_button) as Button
        but.setOnClickListener(this)
        email = (view.findViewById<View>(R.id.email) as EditText)
        pass = (view.findViewById<View>(R.id.password) as EditText)
        return view
    }

    override fun onClick(v: View?) {

        when (v?.id) {
            R.id.email_sign_in_button -> {
                if(email?.text.toString() == "enter"){
                    Log.d("TAG", "logged")
                    this.listener.onLogin()
                }
               if( !pass?.text.toString().isEmpty() &&fbAuth.signInWithEmailAndPassword(email?.text.toString(), pass?.text.toString()).isSuccessful){

                   Singleton.putU("user", User("email?.text.toString()","","","","",""))

                   this.listener.onLogin()
               }

            }
            R.id.register_button ->  {
                print("login")
                this.listener.onReg()
            }else ->{

        }
        }


    }




    override fun onAttach(context: Context?) {
        super.onAttach(context)

        if (context is OnLogin) {
            this.listener = context
        } else {
            throw ClassCastException(context.toString() + " must implement OnRageComicSelected.")
        }
    }


    interface OnLogin{
        fun onLogin()
        fun onReg()
    }


    companion object {
        fun newInstance(): Login {
            return Login()
        }
    }


}
