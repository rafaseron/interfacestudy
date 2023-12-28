package br.com.rafaseron.interfacestudy.UI

import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
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
import br.com.rafaseron.interfacestudy.domain.Carro
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.json.JSONArray
import org.json.JSONObject
import org.json.JSONTokener
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL

class CarFragmentModels : Fragment() {

    lateinit var listaCarros: RecyclerView
    lateinit var fabCalcular: FloatingActionButton
    var carrosArray: ArrayList<Carro> = ArrayList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //return super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.car_fragment_models, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        setupListeners()
        runTask()
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

    fun runTask(){
        MyTask().execute("https://rafaseron.github.io/cars-api/car.json")
    }


    fun setupAdapter(){
        /* CarAdapter */
        //val dados = CarFactory().list

        val adaptador = CarAdapter(carrosArray)

        listaCarros.adapter = adaptador
        listaCarros.layoutManager = LinearLayoutManager(requireContext()) //tem como definir o layoutmanager aqui e no .xml também (app:layoutManager="androidx.recyclerview.widget.LinearLayoutManager")

    }

    inner class MyTask: AsyncTask<String, String, String>() {
        override fun doInBackground(vararg url: String?): String {
            var urlConnection: HttpURLConnection? = null
            try {
                val urlBase = URL(url[0])
                val urlConnection = urlBase.openConnection() as HttpURLConnection
                urlConnection.connectTimeout = 60000
                urlConnection.readTimeout = 60000

                var response = urlConnection.inputStream.bufferedReader().use { it.readText() }
                publishProgress(response)

            }catch (ex: Exception){
                Log.e("Erro", "Erro ao realizar processamento")
            }finally {
                urlConnection?.disconnect()
            }

            return " "
        }

        override fun onPreExecute(){
            super.onPreExecute()
            Log.d("my task", "em pre execucao")

        }

        override fun onProgressUpdate(vararg values: String?) {
            try {
                val jsonArray = JSONTokener(values[0]).nextValue() as JSONArray


                for (i in 0 until jsonArray.length()){
                    val id = jsonArray.getJSONObject(i).getString("id")
                    Log.d("JSON", "id")
                    val preco = jsonArray.getJSONObject(i).getString("preco")
                    Log.d("JSON", "preco")
                    val bateria = jsonArray.getJSONObject(i).getString("bateria")
                    Log.d("JSON", "bateria")
                    val potencia = jsonArray.getJSONObject(i).getString("potencia")
                    Log.d("JSON", "potencia")
                    val recarga = jsonArray.getJSONObject(i).getString("recarga")
                    Log.d("JSON", "recarga")
                    val urlPhoto = jsonArray.getJSONObject(i).getString("urlPhoto")
                    Log.d("JSON", "urlPhoto")


                    val moldeCarro = Carro(id = id.toInt(), preco = preco, bateria = bateria, potencia = potencia, recarga = recarga, urlPhoto = urlPhoto)

                    carrosArray.add(moldeCarro)
                    Log.d("model", moldeCarro.toString())

                }

                setupAdapter()

            }catch (ex: Exception){
                Log.e("erro", ex.message.toString())

            }

        }


    }

}