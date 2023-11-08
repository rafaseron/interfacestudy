package br.com.rafaseron.interfacestudy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Switch
import android.widget.TextView
import kotlin.math.log

class MainActivity : AppCompatActivity() {
    //inicializamos as variveis e typamos
    lateinit var edtKHWPrice: EditText
    lateinit var edtKMPercorrido: EditText
    lateinit var btnCalculate: Button
    lateinit var txtResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //fizemos as variaveis (já typadas) receberem valores (no caso, Views)
        //dessa maneira ligamos a View com o codigo em Kotlin (com as variaveis)
        fun setupViews (){
            edtKHWPrice = findViewById(R.id.edtKHWPrice)
            btnCalculate = findViewById(R.id.btnCalculate)
            edtKMPercorrido = findViewById(R.id.edtKMPercorrido)
            txtResult = findViewById(R.id.txtResult)

        }
        setupViews()

        //agora vamos começar a usar Listeners para monitorar eventos de clicks
        fun setupListeners(){
            btnCalculate.setOnClickListener{
                startActivity(Intent(this,CalcularAutonomiaActivity::class.java))
                //toda logica de calculo esta ai em baixo
                /*
                val price = edtKHWPrice.text.toString().toFloat()
                val km = edtKMPercorrido.text.toString().toFloat()
                val custoPorKM = price/km

                Log.d("BOTAO CALCULATE","texto capturado (Preço) -> ${edtKHWPrice.text.toString()}")
                Log.d("BOTAO CALCULATE","texto capturado (KM Percorrido)-> ${edtKMPercorrido.text.toString()}")
                Log.d("BOTAO CALCULATE", "Custo por KM Rodado -> ${custoPorKM.toString()}")
                txtResult.text = custoPorKM.toString()

                 */
        }

}
        setupListeners()
}
}