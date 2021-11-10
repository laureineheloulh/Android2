package com.example.helou_ijreis_projet_crous

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.viewpager2.widget.ViewPager2
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity(),CrousListFragment.CrousListListener {
    var tabTitle = arrayOf("List", "Map", "Info")
    private val listCrous = ListCrous()
    private var favCrous= arrayListOf<String>()
    private lateinit var pager: ViewPager2
    private val MINIR = Crous(
        //id = "r486",
        name = "MINI R",
        type = "Cafétéria",
        zone = "Orléans",
        description = "Situé dans le hall du bâtiment",
        favorite = false,
        linkPhoto = "http://www.stockcrous.fr/Photos%20stuctures/500x/Orleans/Campus/CAFETERIAS/MINI%20R/MINI%20R.jpg",
        latitude = 40.845765,
        longitude = 1.936779,
        info = "Localisation Situé dans le hall",
        adress = "MINI R Campus 45000 Orléans"
    )

    private val RULahitolle = Crous(
       // id = "r782",
        name = "RULahitolle",
        type = "Restaurant",
        zone = "Bourges",
        description = "Situé près de l'INSA et de l'antenne ",
        favorite = false,
        linkPhoto = "http://www.stockcrous.fr/Photos%20stuctures/500x/Bourges/RU%20LAHITOLLE/RU_lahitolle.jpeg",
        latitude = 47.0810645,
        longitude = 2.414766,
        info = "Localisation Situé près de l'INSA",
        adress = "RU Lahitolle 15 rue Maurice Roy 1800"
    )
    private val LAnatidé = Crous(
       // id = "r481",
        name = "L'Anatidé",
        type = "Restaurant",
        zone = "Orléans",
        description = "Situé près du collegium Sciences",
        favorite = false,
        linkPhoto = "http://www.stockcrous.fr/Photos%20stuctures/500x/Orleans/Campus/CAFETERIAS/MINI%20R/MINI%20R.jpg",
        latitude = 47.845765,
        longitude = 5.936779,
        info = "Localisation Situé dans le hall",
        adress = "L'Anatidé Campus 45000 Orléans"
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listCrous.addCrous(MINIR)
        listCrous.addCrous(RULahitolle)
        listCrous.addCrous(LAnatidé)


        pager = findViewById<ViewPager2>(R.id.view_pager2)
        var tl = findViewById<TabLayout>(R.id.tab_layout)
        pager.adapter=FragmentAdapter(supportFragmentManager,lifecycle, listCrous.getAllCrous(), favCrous)

        TabLayoutMediator(tl, pager) {
                tab, position ->
            tab.text = tabTitle[position]
        }.attach()

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
              pager.setCurrentItem(0)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun displayCrousList() {
        // btnCreateCrous.visibility = View.VISIBLE
       val fragmentTransaction = supportFragmentManager.beginTransaction()
       val fragment = CrousListFragment.newInstance(listCrous.getAllCrous(),favCrous)
       fragmentTransaction.replace(R.id.a_main_lyt_fragment_container, fragment)
       fragmentTransaction.commit()
    }

    private fun displayCrousMap(){
        val bundle = Bundle()
        bundle.putSerializable(MAP_CROUS, listCrous.getAllCrous())
        val fragment = CrousMapFragment()
        fragment.arguments = bundle
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.a_main_lyt_fragment_container, fragment)
        fragmentTransaction.commit()

    }
    override fun favFromFragment(name: String) {
        TODO("Not yet implemented")
    }

    override fun refreshPostDetail() {
        TODO("Not yet implemented")
    }
}