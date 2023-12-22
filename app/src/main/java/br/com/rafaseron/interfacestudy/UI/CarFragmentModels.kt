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

class CarFragmentModels : Fragment() {

    lateinit var btnCalculate: Button // <- PASSAR ESSA LOGICA
    lateinit var listaCarros: RecyclerView // <- PASSAR ESSA LOGICA

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //return super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.car_fragment_models,container,false)


        btnCalculate = requireView().findViewById(R.id.btnCalculate) // <- AQUI
        listaCarros = requireView().findViewById(R.id.rvInformacoesFragment) // <- AQUI

        return view

        fun setupListeners(){   // <- AQUI
            btnCalculate.setOnClickListener{
                startActivity(Intent(this, CalcularAutonomiaActivity::class.java))
            }
        }
        setupListeners()

        fun setupAdapter(){
            /* CarAdapter */
            //val dados = CarFactory().list

            val adaptador = CarAdapter(CarFactory().list)

            listaCarros.adapter = adaptador // <- AQUI
            listaCarros.layoutManager = LinearLayoutManager(this) //tem como definir o layoutmanager aqui e no .xml também (app:layoutManager="androidx.recyclerview.widget.LinearLayoutManager")

        }
        setupAdapter()

    }

}