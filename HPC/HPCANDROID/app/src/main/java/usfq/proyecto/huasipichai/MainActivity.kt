package usfq.proyecto.huasipichai

import android.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import usfq.proyecto.huasipichai.ui.Login
import usfq.proyecto.huasipichai.ui.eventShow
import usfq.proyecto.huasipichai.ui.registration

class MainActivity : AppCompatActivity(), Login.OnLogin, registration.OnRegister, eventShow.OnEventSelected {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .add(R.id.root_layout, Login.newInstance(), "login")
                    .commit()
        }
    }



    override fun onLogin(){
        val log =
                eventShow.newInstance()
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.root_layout, log, "eventShow")
                .addToBackStack(null)
                .commit()
    }

    override fun onReg(){
        val reg =
                registration.newInstance()
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.root_layout, reg, "reg")
                .addToBackStack(null)
                .commit()
    }


    override fun onRegister(){
        val reg =
                Login.newInstance()
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.root_layout, reg, "eventShow")
                .addToBackStack(null)
                .commit()
    }

    override fun onEventSelected() {

    }
}



