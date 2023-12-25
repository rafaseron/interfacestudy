package br.com.rafaseron.interfacestudy.UI

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import br.com.rafaseron.interfacestudy.R
import br.com.rafaseron.interfacestudy.adapter.CarAdapter
import br.com.rafaseron.interfacestudy.data.CarFactory
import com.google.android.material.floatingactionbutton.FloatingActionButton

class CarFragmentModels : Fragment() {

    lateinit var listaCarros: RecyclerView
    lateinit var fabCalcular: FloatingActionButton

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //return super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.car_fragment_models, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        setupListeners()
        setupAdapter()
    }

    fun setupView(){
        listaCarros = requireView().findViewById<RecyclerView>(R.id.rvInformacoesFragment)
        //val listaCarros: View? = activity?.findViewById(R.id.rvInformacoesFragment)
        fabCalcular = requireView().findViewById<FloatingActionButton>(R.id.fabCalcular)
    }

    fun setupListeners(){
        fabCalcular.setOnClickListener(){
            startActivity(Intent(requireContext(), CalcularAutonomiaActivity::class.java))
        }
    }


    fun setupAdapter(){
        /* CarAdapter */
        //val dados = CarFactory().list

        val adaptador = CarAdapter(CarFactory().list)

        listaCarros.adapter = adaptador
        listaCarros.layoutManager = LinearLayoutManager(requireContext()) //tem como definir o layoutmanager aqui e no .xml também (app:layoutManager="androidx.recyclerview.widget.LinearLayoutManager")

    }
}