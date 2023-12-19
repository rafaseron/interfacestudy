package br.com.rafaseron.interfacestudy.UI

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.rafaseron.interfacestudy.R
import br.com.rafaseron.interfacestudy.adapter.CarAdapter
import br.com.rafaseron.interfacestudy.data.CarFactory

class MainActivity : AppCompatActivity() {
    //inicializamos as variveis e typamos
    lateinit var btnCalculate: Button
    lateinit var listaCarros: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fun setupViews(){
            btnCalculate = findViewById(R.id.btnCalculate)
            listaCarros = findViewById(R.id.rvInformacoes)
        }
        setupViews()

        //adicionar listener para abrir a nova activity no botao
        fun setupListeners(){
            btnCalculate.setOnClickListener{
                startActivity(Intent(this, CalcularAutonomiaActivity::class.java))
        }

}
        setupListeners()

        fun setupListView(){
            //val dados = CarFactory().list

            val adaptador = CarAdapter(CarFactory().list)

            listaCarros.adapter = adaptador
            listaCarros.layoutManager = LinearLayoutManager(this) //tem como definir o layoutmanager aqui e no .xml também (app:layoutManager="androidx.recyclerview.widget.LinearLayoutManager")
        }
        setupListView()
}


    override fun onStart() {
        super.onStart()
        Log.d("Activity","onStart")
    }
    override fun onResume() {
        super.onResume()
        Log.d("Activity","onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("Activity","onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("Activity","onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("Activity","onDestroy")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("Activity","onRestart")
    }



}