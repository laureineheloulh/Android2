package com.example.helou_ijreis_projet_crous

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CrousAdapter (private val crousList: ArrayList<Crous>,private val favCrous :ArrayList<String>,
                    private val listener: OnItemClickListener
) :
    RecyclerView.Adapter<CrousAdapter.CrousViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CrousViewHolder {
        val row = LayoutInflater.from(parent.context).inflate(R.layout.row_crous, parent, false)
        return CrousViewHolder(row)
    }
    override fun onBindViewHolder(holder: CrousViewHolder, position: Int) {
        val (name, type, zone) = crousList[position]
        if(favCrous.contains(name)){
            holder.buttonFav.setImageResource(R.drawable.ic_baseline_star_24)
        }
        else{
            holder.buttonFav.setImageResource(R.drawable.ic_baseline_star_border_24)
        }
        holder.txvName.text = name
        holder.txvType.text = type
        holder.txvZone.text = zone
    }

    override fun getItemCount(): Int {
        return crousList.size
    }
    inner class CrousViewHolder(rootView: View) : RecyclerView.ViewHolder(rootView),
        View.OnClickListener {

        var txvName = rootView.findViewById<TextView>(R.id.r_crous_txv_name)
        var txvType = rootView.findViewById<TextView>(R.id.r_crous_txv_type)
        var txvZone = rootView.findViewById<TextView>(R.id.r_crous_txv_zone)
        val buttonFav = itemView.findViewById<ImageButton>(R.id.imageButtonFav)
        init {
            itemView.setOnClickListener(this)
            buttonFav.setOnClickListener(this)
        }
        override fun onClick(v: View?) {
            when (v) {
                buttonFav -> {
                    if(favCrous.contains(txvName.text)){
                        buttonFav.setImageResource(R.drawable.ic_baseline_star_border_24)
                        favCrous.remove(txvName.text)
                        listener.favFromAdapter(adapterPosition)

                    }else{
                        buttonFav.setImageResource(R.drawable.ic_baseline_star_24)
                        favCrous.add(txvName.text as String)
                        listener.favFromAdapter(adapterPosition)
                    }
                }
                else -> {
                    val position: Int = adapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(position)
                    }
                }
            }
        }

    }

   /* override fun getItemId(position: Int): String {
        return crousList[position].id
    }*/

    fun refreshData(newCrous:ArrayList<Crous>){
        crousList.clear()
        crousList.addAll(newCrous)
    }
    interface OnItemClickListener {
        fun onItemClick(position: Int)
        fun favFromAdapter(position : Int)
    }


}