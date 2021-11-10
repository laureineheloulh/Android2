package com.example.helou_ijreis_projet_crous
import java.io.Serializable

data class Crous(
    val title: String,
    val type: String,
    val zone: String,
    val id:String,
    val short_desc: String,
    var favorite: Boolean,
    var photo: String? = null,
    val geolocalisation: List<Double>,
    //val latitude: Double,
    //val longitude: Double,
    val infos: String,
    val contact: String
) : Serializable