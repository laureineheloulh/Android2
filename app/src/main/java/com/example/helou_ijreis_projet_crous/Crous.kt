package com.example.helou_ijreis_projet_crous
import java.io.Serializable

data class Crous(
    val id:String,
    val title: String,
    val type: String,
    val zone: String,
    val short_desc: String,
    var favorite: Boolean,
    val photo: String? = null,
    val latitude: Double,
    val longitude: Double,
    val infos: String,
    val contact: String
) : Serializable