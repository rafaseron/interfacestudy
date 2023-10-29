package br.com.rafaseron.interfacestudy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Switch
import kotlin.math.log

class MainActivity : AppCompatActivity() {
    //inicializamos as variveis e typamos
    lateinit var edtKHWPrice: EditText
    lateinit var edtKMPercorrido: EditText
    lateinit var btnCalculate: Button

    //Treino prático de outras Views
    lateinit var rgFood: RadioGroup
    lateinit var cb_Compromisso1: CheckBox
    lateinit var cb_Compromisso2: CheckBox
    lateinit var swLampada: Switch
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //fizemos as variaveis (já typadas) receberem valores (no caso, Views)
        //dessa maneira ligamos a View com o codigo em Kotlin (com as variaveis)
        fun setupViews (){
            edtKHWPrice = findViewById(R.id.edtKHWPrice)
            btnCalculate = findViewById(R.id.btnCalculate)
            edtKMPercorrido = findViewById(R.id.edtKMPercorrido)

            //Treino prático das outras Views
            rgFood = findViewById(R.id.rgFood)
            cb_Compromisso1 = findViewById(R.id.cb_Compromisso1)
            cb_Compromisso2 = findViewById(R.id.cb_Compromisso2)
            swLampada = findViewById(R.id.swLampada)
        }
        setupViews()

        //agora vamos começar a usar Listeners para monitorar eventos de clicks
        fun setupListeners(){
            btnCalculate.setOnClickListener{
                Log.d("BOTAO CALCULATE","texto capturado -> ${edtKHWPrice.text.toString()}")
        }
            //Treino prático das outras Views
            when (rgFood.checkedRadioButtonId){
                R.id.rbPizza -> Log.d("RADIOGROUP","Pizza")
                R.id.rbBurger -> Log.d("RADIOGROUP","Hamburguer")
            }
            if (cb_Compromisso1.isChecked){
                Log.d("CHECKBOX1","Compromisso 1")
            }
            if (cb_Compromisso2.isChecked){
                Log.d("CHECKBOX2","Compromisso 2")
            }
            if (swLampada.isChecked){
                Log.d("SWITCH","Lampada ligada")
            }
    }
        setupListeners()
}
}