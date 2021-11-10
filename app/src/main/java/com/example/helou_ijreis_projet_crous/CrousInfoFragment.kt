package com.example.helou_ijreis_projet_crous

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.google.android.gms.oss.licenses.OssLicensesMenuActivity

class CrousInfoFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_crous_info, container, false)
        val btnLicense = rootView.findViewById<Button>(R.id.license_button).setOnClickListener{
            startActivity(Intent(context, OssLicensesMenuActivity::class.java))
        }
        return rootView
    }

    companion object {
        @JvmStatic
        fun newInstance(): CrousInfoFragment =
            CrousInfoFragment()

    }
}