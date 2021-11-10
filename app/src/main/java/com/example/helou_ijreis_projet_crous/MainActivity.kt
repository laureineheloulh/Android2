package com.example.helou_ijreis_projet_crous

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import org.json.JSONObject
import org.w3c.dom.Text
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(),CrousListFragment.CrousListListener {
    var tabTitle = arrayOf("List", "Map", "Info")
    private val listCrous = ListCrous()
    private var favCrous= arrayListOf<String>()
    private lateinit var pager: ViewPager2
    private lateinit var crous: Crous
    //private val crousLocalDataset = JSONObject()
    private val MINIR = Crous(
        id = "ismin",
        title = "Les pauvres ISMINs",
        type = "Cafétéria",
        zone = "Gardanne",
        short_desc = "Ils n'ont même pas une cafétéria",
        favorite = false,
        photo = "https://www.ville-gardanne.fr/IMG/jpg/cmp-2-800.jpg",
        geolocalisation = listOf(43.445793, 5.479295),
        //latitude = 40.845765,
        //longitude = 1.936779,
        infos = "Ils n'ont même pas une cafétéria",
        contact = "MINI R Campus 45000 Orléans"
    )

    private val RULahitolle = Crous(
        id = "r782",
        title = "RULahitolle",
        type = "Restaurant",
        zone = "Bourges",
        short_desc = "Situé près de l'INSA et de l'antenne ",
        favorite = false,
        photo = "https://www.crous-amiens.fr/wp-content/uploads/sites/9/2020/05/wifi.gif",
        geolocalisation =listOf(47.0810645, 2.42),
        //latitude = 47.0810645,
        //longitude = 2.414766,
        infos = "Localisation Situé près de l'INSA",
        contact = "RU Lahitolle 15 rue Maurice Roy 1800"
    )
    private val LAnatidé = Crous(
        id = "r481",
        title = "L'Anatidé",
        type = "Restaurant",
        zone = "Orléans",
        short_desc = "Situé près du collegium Sciences",
        favorite = false,
        photo = "https://www.stockcrous.fr/Photos%20stuctures/500x/Orleans/Campus/CAFETERIAS/MINI%20R/MINI%20R.jpg",
        geolocalisation = listOf(47.845765, 5.94),
        //latitude = 47.845765,
        //longitude = 5.936779,
        infos = "Localisation Situé dans le hall",
        contact = "L'Anatidé Campus 45000 Orléans"
    )

    val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl("https://crous-renovations.cleverapps.io/")
        .build()
    val crousService = retrofit.create(CrousService::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        listCrous.addCrous(MINIR)
        listCrous.addCrous(RULahitolle)
        listCrous.addCrous(LAnatidé)
        loadAllCrous()

        //displayCrousList()
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_action_refresh -> {
                Toast.makeText(this, "That's refreshing", Toast.LENGTH_SHORT).show()
              loadAllCrous()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun loadAllCrous() {
        crousService.getAllCrous().enqueue(object : Callback<List<Crous>> {
            override fun onResponse(
                call: Call<List<Crous>>,
                response: Response<List<Crous>>
            ) {
                val allCrous: List<Crous>? = response.body()
                allCrous?.forEach {
                    listCrous.addCrous(it)
                }
                displayTabs();
            }

            override fun onFailure(call: Call<List<Crous>>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Error when trying to fetch Data" + t.localizedMessage, Toast.LENGTH_LONG).show()
            }
        }
        )
    }

    private fun displayTabs() {
        pager = findViewById(R.id.view_pager2)
        var tl = findViewById<TabLayout>(R.id.tab_layout)
        pager.adapter=FragmentAdapter(supportFragmentManager,lifecycle, listCrous.getAllCrous(), favCrous)
        pager.isUserInputEnabled = false
        TabLayoutMediator(tl, pager) {
                tab, position ->
            tab.text = tabTitle[position]
        }.attach()
    }

    private fun displayCrousList() {
        // btnCreateCrous.visibility = View.VISIBLE
       val fragmentTransaction = supportFragmentManager.beginTransaction()
       val fragment = CrousListFragment.newInstance(listCrous.getAllCrous(),favCrous)
       fragmentTransaction.replace(R.id.view_pager2, fragment)
       fragmentTransaction.commit()
    }

    private fun displayCrousMap(){
        val bundle = Bundle()
        bundle.putSerializable(MAP_CROUS, listCrous.getAllCrous())
        val fragment = CrousMapFragment()
        fragment.arguments = bundle
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.view_pager2, fragment)
        fragmentTransaction.commit()

    }
    override fun favFromFragment(name: String) {
        TODO("Not yet implemented")
    }

    override fun refreshPostDetail() {
        TODO("Not yet implemented")
    }
}