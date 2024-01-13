package br.com.rafaseron.interfacestudy.UI

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import br.com.rafaseron.interfacestudy.R

class CalcularAutonomiaActivity : AppCompatActivity() {
    //inicializacao tardia das variaveis
    lateinit var edtKHWPrice: EditText
    lateinit var edtKMPercorrido: EditText
    lateinit var btnCalculate: Button
    lateinit var txtResult: TextView
    lateinit var imgClose : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calcular_autonomia)

        //ligacao entre view e variaveis
        fun setupViews (){
            edtKHWPrice = findViewById(R.id.edtKHWPrice)
            btnCalculate = findViewById(R.id.btnCalculate)
            edtKMPercorrido = findViewById(R.id.edtKMPercorrido)
            txtResult = findViewById(R.id.txtResult)
            imgClose = findViewById(R.id.imgClose)

        }
        setupViews()

        fun saveSharedPreferences(resultado: Float){
            val sharedPref = getPreferences(Context.MODE_PRIVATE) ?: return
            with(sharedPref.edit()){
                putFloat(getString(R.string.saved_calc), resultado)
                apply()
            }
        }

        //colocar um listener no botao pra fazer o calculo
        fun setupListeners(){
            btnCalculate.setOnClickListener{

                val price = edtKHWPrice.text.toString().toFloat()
                val km = edtKMPercorrido.text.toString().toFloat()
                val custoPorKM = price/km
                saveSharedPreferences(custoPorKM)


                Log.d("BOTAO CALCULATE","texto capturado (Preço) -> ${edtKHWPrice.text.toString()}")
                Log.d("BOTAO CALCULATE","texto capturado (KM Percorrido)-> ${edtKMPercorrido.text.toString()}")
                Log.d("BOTAO CALCULATE", "Custo por KM Rodado -> ${custoPorKM.toString()}")
                txtResult.text = custoPorKM.toString()

            }
            imgClose.setOnClickListener{
                finish()
            }

        }
        setupListeners()
    }

}