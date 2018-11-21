package usfq.proyecto.huasipichai

import java.io.Serializable

data class Event(val nombre: String, val fecha: String, val desc: String, val lugar: String, val user: User, val price: String, val cat: String) : Serializable
