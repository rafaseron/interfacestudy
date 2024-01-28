package br.com.rafaseron.interfacestudy.UI

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.rafaseron.interfacestudy.R
import br.com.rafaseron.interfacestudy.adapter.CarAdapter
import br.com.rafaseron.interfacestudy.adapter.FavoritedCarAdapter
import br.com.rafaseron.interfacestudy.data.local.StoredCarsDbHelper

class CarFragmentFavorits: Fragment() {

    lateinit var rvFavoritedFragment : RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.car_fragment_favorits, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        setupAdater()
    }

    fun setupView(){
        rvFavoritedFragment = requireView().findViewById(R.id.rvFavoritedFragment)
    }

    fun setupAdater(){
        val dbHelper = StoredCarsDbHelper(requireContext())
        val favoritedCars = dbHelper.getAllFavoritedCars()
        val adaptador = FavoritedCarAdapter(favoritedCars, dbHelper)
        rvFavoritedFragment.adapter = adaptador
        rvFavoritedFragment.layoutManager = LinearLayoutManager(requireContext())

    }

}