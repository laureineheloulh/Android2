package com.example.helou_ijreis_projet_crous

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView

class DetailActivity : AppCompatActivity() {
    private var fav : Boolean = false
    private lateinit var crous : Crous

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        crous = intent.getSerializableExtra(ARG_CROUS ) as Crous

        fav= intent.getBooleanExtra("fav",false)

        var imageView=findViewById<ImageView>(R.id.imageView)
        //Picasso.get().load(crous.linkPhoto).into(imageView)

        var nameTextView =findViewById<TextView>(R.id.name)
        nameTextView.text = "Name: ${crous.name.toString()}"


        findViewById<TextView>(R.id.type).text = "Type: ${crous.type.toString()}"
        findViewById<TextView>(R.id.zone).text = "Zone: ${crous.zone.toString()}"
        findViewById<TextView>(R.id.description).text = "Description: ${crous.description.toString()}"
        findViewById<TextView>(R.id.info).text = "Info: ${crous.info.toString()}"

        if(fav){
            findViewById<ImageButton>(R.id.imageButton).setImageResource(R.drawable.ic_baseline_star_24)
        }

    }

    fun favButton(view: View) {
        val imgBttn= findViewById<ImageButton>(R.id.imageButton)
        if(fav){
            imgBttn.setImageResource(R.drawable.ic_baseline_star_border_24)
            fav = !fav
        }else{
            imgBttn.setImageResource(R.drawable.ic_baseline_star_24)
            fav = !fav
        }
    }

    override fun onBackPressed() {
        val returnIntent = Intent()
        returnIntent.putExtra("fav", fav)
        returnIntent.putExtra("crousname",crous.name )
        setResult(Activity.RESULT_OK, returnIntent)
        finish()
        super.onBackPressed()
    }
}