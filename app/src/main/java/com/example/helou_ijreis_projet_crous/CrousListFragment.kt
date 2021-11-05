package com.example.helou_ijreis_projet_crous

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

const val ARG_CROUS = "crous"

class CrousListFragment : Fragment(),CrousAdapter.OnItemClickListener {
    private lateinit var crousShelf: ArrayList<Crous>
    private lateinit var adapter: CrousAdapter
    private var favCrous = arrayListOf<String>()
    private var listener: CrousListListener? = null

    interface CrousListListener {
        fun favFromFragment(name: String)
        fun refreshPostDetail()
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val argCrous = requireArguments().getSerializable(ARG_CROUS) as ArrayList<Crous>?
        crousShelf = argCrous ?: ArrayList()
        favCrous = requireArguments().getSerializable("favs") as ArrayList<String>



    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_crous_list, container, false)
        val rcvCrous = view.findViewById<RecyclerView>(R.id.f_crous_list_rcv_crous)
        adapter = CrousAdapter(crousShelf, favCrous, this)
        rcvCrous.layoutManager = LinearLayoutManager(context)
        rcvCrous.adapter = adapter
        return view
    }

    companion object {

        @JvmStatic
        fun newInstance(crous: ArrayList<Crous>,favs: ArrayList<String>) =
            CrousListFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_CROUS, crous)
                    putSerializable("favs",favs)
                }
            }
    }

    override fun onItemClick(position: Int) {
        val crous: Crous = crousShelf[position]
        Toast.makeText(context, "Item ${crous.name} clicked", Toast.LENGTH_SHORT).show()

        val intent = Intent(context, DetailActivity::class.java)
        intent.putExtra("crous", crous)
        intent.putExtra("fav", favCrous.contains(crous.name))
        this.startActivityForResult(intent, 1)
    }

    override fun favFromAdapter(position: Int) {
        val crous: Crous = crousShelf[position]
        Toast.makeText(context, "Item ${crous.name} favorite", Toast.LENGTH_SHORT).show()
        if (favCrous.contains(crous.name)) {
            favCrous.remove(crous.name)
        } else {
            favCrous.add(crous.name)
        }
        listener?.favFromFragment(crous.name)
    }
}