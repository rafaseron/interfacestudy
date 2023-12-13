package br.com.rafaseron.interfacestudy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.rafaseron.interfacestudy.adapter.CarAdapter

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
                startActivity(Intent(this,CalcularAutonomiaActivity::class.java))
        }

}
        setupListeners()

        fun setupListView(){
            val dados = arrayOf("Cupcake", "Marshmellow", "Lollipop", "Ice Cream Sandwich", "Jellybean", "teste", "teste", "teste", "teste")

            val adaptador = CarAdapter(dados)

            listaCarros.adapter = adaptador
            listaCarros.layoutManager = LinearLayoutManager(this) //tem como definir o layoutmanager aqui e no .xml também (app:layoutManager="androidx.recyclerview.widget.LinearLayoutManager")
        }
        setupListView()
}
}