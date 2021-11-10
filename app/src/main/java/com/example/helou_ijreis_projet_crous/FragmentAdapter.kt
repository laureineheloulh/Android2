package com.example.helou_ijreis_projet_crous

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class FragmentAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle, val crousList: ArrayList<Crous>, val favCrous: ArrayList<String>): FragmentStateAdapter(fragmentManager,lifecycle) {

    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> CrousListFragment.newInstance(crousList, favCrous)
            1 -> CrousMapFragment.newInstance(crousList)
            2 -> CrousInfoFragment.newInstance()
            else -> CrousListFragment.newInstance(crousList, favCrous)
        }
    }
}