package br.com.rafaseron.interfacestudy.UI

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.os.AsyncTask
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import br.com.rafaseron.interfacestudy.R
import br.com.rafaseron.interfacestudy.adapter.CarAdapter
import br.com.rafaseron.interfacestudy.data.CarFactory
import br.com.rafaseron.interfacestudy.data.CarsAPI
import br.com.rafaseron.interfacestudy.domain.Carro
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.json.JSONArray
import org.json.JSONObject
import org.json.JSONTokener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL

class CarFragmentModels : Fragment() {

    lateinit var listaCarros: RecyclerView
    lateinit var fabCalcular: FloatingActionButton
    lateinit var pbLoading: ProgressBar
    lateinit var imgNoConnection: ImageView
    lateinit var txtNoConnection: TextView
    lateinit var carsAPI: CarsAPI
    //var carrosArray: ArrayList<Carro> = ArrayList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //return super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.car_fragment_models, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        setupListeners()
        setupRetrofit()
        Log.d("INTERNET", checkForInternet(context).toString())
    }

    override fun onResume() {
        super.onResume()
        checkForInternet(context)
        connectionReturn()
        //setupRetrofit() -> foi atribuido no onViewCreated -> nao precisa no onResume pois ele faz apenas as atribuicoes, nao as chamadas
        //getAllCars() -> sendo rodado no connectionReturn() -> faz chamada sempre que troca de Fragment A -> B ou B -> A
    }

    fun setupRetrofit(){
        val retrofit = Retrofit.Builder()
            .baseUrl("https://rafaseron.github.io/cars-api/")
            .addConverterFactory(GsonConverterFactory.create() )
            .build()

        carsAPI = retrofit.create(CarsAPI::class.java)
    }

    fun getAllCars(){
        carsAPI.getAllCars().enqueue(object: Callback<List<Carro>>{
            override fun onResponse(call: Call<List<Carro>>, response: Response<List<Carro>>) {
                if (response.isSuccessful){
                    imgNoConnection.visibility = View.GONE
                    txtNoConnection.visibility = View.GONE
                    listaCarros.visibility = View.VISIBLE
                    pbLoading.visibility = View.INVISIBLE
                    response.body()?.let {
                        setupAdapter(it)
                    }
                }else{
                    Toast.makeText(context, R.string.responseError, Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<List<Carro>>, t: Throwable) {
                Toast.makeText(context, R.string.responseError, Toast.LENGTH_LONG).show()
            }

        })
    }

    fun setupView(){
        pbLoading = requireView().findViewById<ProgressBar>(R.id.pbLoading)
        imgNoConnection = requireView().findViewById<ImageView>(R.id.imgNoConnection)
        txtNoConnection = requireView().findViewById<TextView>(R.id.txtNoConnection)
        listaCarros = requireView().findViewById<RecyclerView>(R.id.rvInformacoesFragment)
        //val listaCarros: View? = activity?.findViewById(R.id.rvInformacoesFragment)
        fabCalcular = requireView().findViewById<FloatingActionButton>(R.id.fabCalcular)
    }


    fun setupListeners(){
        fabCalcular.setOnClickListener(){
            startActivity(Intent(requireContext(), CalcularAutonomiaActivity::class.java))
        }
    }


    fun setupAdapter(lista: List<Carro>){
        /* CarAdapter */
        //val dados = CarFactory().list

        val adaptador = CarAdapter(lista)

        listaCarros.adapter = adaptador
        listaCarros.layoutManager = LinearLayoutManager(requireContext()) //tem como definir o layoutmanager aqui e no .xml também (app:layoutManager="androidx.recyclerview.widget.LinearLayoutManager")

    }

    fun checkForInternet(contexto: Context?): Boolean{

        val connectionManager = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){

            val network = connectionManager.activeNetwork ?: return false
            val activeNetwork = connectionManager.getNetworkCapabilities(network) ?: return false

            return when {
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                else -> false
            }

        }else{
            @Suppress("DEPRECATED")
            val networkInfo = connectionManager.activeNetworkInfo ?: return false
            @Suppress("DEPRECATED")
            return networkInfo.isConnected
        }

    }

    fun connectionReturn (){
        if (checkForInternet(context) == true){
            //runTask()
            getAllCars()
            imgNoConnection.visibility = View.GONE
            txtNoConnection.visibility = View.GONE
            listaCarros.visibility = View.VISIBLE
            pbLoading.visibility = View.INVISIBLE
        }else{
            imgNoConnection.visibility = View.VISIBLE
            txtNoConnection.visibility = View.VISIBLE
            listaCarros.visibility = View.GONE
            pbLoading.visibility = View.VISIBLE
        }
    }

    /*fun runTask(){
        //pbLoading.visibility = View.VISIBLE
        carrosArray.clear()
        MyTask().execute("https://rafaseron.github.io/cars-api/car.json")
    }*/

    /*inner class MyTask: AsyncTask<String, String, String>() {
        override fun doInBackground(vararg url: String?): String {
            var urlConnection: HttpURLConnection? = null
            try {
                val urlBase = URL(url[0])
                val urlConnection = urlBase.openConnection() as HttpURLConnection
                urlConnection.connectTimeout = 60000
                urlConnection.readTimeout = 60000
                urlConnection.setRequestProperty("Content-Type", "application/json")

                val responseCode = urlConnection.responseCode
                if(responseCode == HttpURLConnection.HTTP_OK){
                    var response = urlConnection.inputStream.bufferedReader().use { it.readText() }
                    publishProgress(response)
                }else{
                    listaCarros.visibility = View.GONE
                    pbLoading.visibility = View.VISIBLE
                    imgNoConnection.visibility = View.VISIBLE
                    txtNoConnection.visibility = View.VISIBLE
                    Log.e("Erro", "Servico retornou codigo $responseCode")
                }

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

            }catch (ex: Exception){
                Log.e("erro", ex.message.toString())

            }

        }


    }*/

}