package com.example.helou_ijreis_projet_crous

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.ImageLoader
import com.android.volley.toolbox.ImageRequest
import com.android.volley.toolbox.NetworkImageView
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide

class DetailActivity : AppCompatActivity() {
    private var fav : Boolean = false
    private lateinit var crous : Crous
    private lateinit var imageView:ImageView
    private lateinit var imgBttn: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        crous = intent.getSerializableExtra(ARG_CROUS ) as Crous

        fav= intent.getBooleanExtra("fav",false)

        imageView=findViewById<ImageView>(R.id.imageView)

        // il y a un problème d'affichage quand le lien de la photo commence par http donc on la remplace par https
        if(crous.photo!!.startsWith("http:") == true){
            crous.photo = crous.photo!!.drop(5)
            crous.photo = "https:" + crous.photo
        }
        Glide.with(this).load(crous.photo).into(imageView)
        //requestImage()
        imgBttn= findViewById<ImageButton>(R.id.imageButton)
        imgBttn.visibility = View.VISIBLE
       // Picasso.get().load(crous.photo).into(imageView)

        var nameTextView =findViewById<TextView>(R.id.name)
        nameTextView.text = "Name: ${crous.title.toString()}"


        findViewById<TextView>(R.id.type).text = "Type: ${crous.type.toString()}"
        findViewById<TextView>(R.id.zone).text = "Zone: ${crous.zone.toString()}"
        findViewById<TextView>(R.id.description).text = "Description: ${crous.short_desc.toString()}"
        findViewById<TextView>(R.id.info).text = "Info: ${crous.infos}"

        if(fav){
            findViewById<ImageButton>(R.id.imageButton).setImageResource(R.drawable.ic_baseline_star_24)
        }

        var btnBack = findViewById<Button>(R.id.back_button)
        btnBack.setOnClickListener(){
            onBackPressed()
        }

    }
/*
    private fun requestImage() {

        val requestQueue: RequestQueue = Volley.newRequestQueue(this)

        val imageRequest: ImageRequest = ImageRequest(crous.photo, {
            @Override
            fun onResponse(response: Bitmap){
                imageView.setImageBitmap(response)
            }
        },0,0,ImageView.ScaleType.FIT_CENTER,null, {
            @Override
            fun onErrorResponse(error: VolleyError){

            }
        });
        requestQueue.add(imageRequest)
    }
*/
    fun favButton(view: View) {
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
        returnIntent.putExtra("crousname",crous.title )
        setResult(Activity.RESULT_OK, returnIntent)
        finish()
        super.onBackPressed()
    }
}