package br.com.rafaseron.interfacestudy.UI

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.rafaseron.interfacestudy.R
import br.com.rafaseron.interfacestudy.adapter.CarAdapter
import br.com.rafaseron.interfacestudy.data.local.StoredCarDatabaseManager

class CarFragmentFavorits: Fragment() {
    lateinit var rvFavoritedFragment : RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.car_fragment_favorits, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
    }

    override fun onResume() {
        super.onResume()
        setupAdapter()
    }

    fun setupView(){
        rvFavoritedFragment = requireView().findViewById(R.id.rvFavoritedFragment)
    }

    fun setupAdapter(){
        val listaCarros = StoredCarDatabaseManager().getAllSavedData(requireContext())
        rvFavoritedFragment.adapter = CarAdapter(listaCarros, requireContext())
        rvFavoritedFragment.layoutManager = LinearLayoutManager(requireContext())
    }


}