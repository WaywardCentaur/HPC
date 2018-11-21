package usfq.proyecto.huasipichai

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.google.android.gms.auth.api.signin.internal.Storage
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.event_cell.view.*
import kotlinx.android.synthetic.main.fragment_event_detail.view.*
import android.opengl.ETC1.getHeight
import android.opengl.ETC1.getWidth
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.widget.ImageView
import usfq.proyecto.huasipichai.ui.eventShow


class eventAdapter// 1
(private val mContext: Context, private val events: ArrayList<Event>) : BaseAdapter() {

    val storage = FirebaseStorage.getInstance()
    val storageRef = storage.reference
    private lateinit var listener: eventShow.OnEventSelected
    override fun getCount(): Int {
        return events.size
    }

    // 3
    override fun getItemId(position: Int): Long {
        return 0
    }

    // 4
    override fun getItem(position: Int): Any? {
        return events[position]
    }

    // 5
    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View, parent: ViewGroup): View {
        var inflator = mContext!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        var eventView = inflator.inflate(R.layout.event_cell, null)
        val ONE_MEGABYTE: Long = 1024 * 1024

        eventView.event_name.text = this.events[position].nombre

        val pathReference = storageRef.child(this.events[position].nombre+this.events[position].fecha+".jpg")
        pathReference.getBytes(ONE_MEGABYTE).addOnSuccessListener {
            val bmp = BitmapFactory.decodeByteArray(it, 0, it.size)
            eventView.event_image.setImageBitmap(bmp)
        }.addOnFailureListener {
            // Handle any errors
        }

        eventView.setOnClickListener {
            Singleton.put("event", this.events[position])
             listener.onEventSelected()}


        return eventView
    }

}
