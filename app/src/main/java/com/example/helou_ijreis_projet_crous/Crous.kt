package com.example.helou_ijreis_projet_crous
import java.io.Serializable

data class Crous(
    val name: String,
    val type: String,
    val zone: String,
    val description: String,
    var favorite: Boolean,
    val linkPhoto: String? = null,
    val latitude: Double,
    val longitude: Double,
    val info: String,
    val adress: String
) : Serializable