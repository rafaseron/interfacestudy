package br.com.rafaseron.interfacestudy.UI

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import br.com.rafaseron.interfacestudy.R

class CarFragmentFavorits: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.car_fragment_favorits, container, false)
        setupTextView()
        return view

    }
    fun setupTextView(){
        val txtWelcome: TextView = requireView().findViewById(R.id.textView)
        txtWelcome.text = "Ola mundo"

    }

}