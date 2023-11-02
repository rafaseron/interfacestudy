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
                val price = edtKHWPrice.text.toString().toFloat()
                val km = edtKMPercorrido.text.toString().toFloat()
                val custoPorKM = price/km
                //desenvolver ideia abaixo quando for aplicar as correcoes no app
                //val custoRecarga = custoPorKM*km
                Log.d("BOTAO CALCULATE","texto capturado (Preço) -> ${edtKHWPrice.text.toString()}")
                Log.d("BOTAO CALCULATE","texto capturado (KM Percorrido)-> ${edtKMPercorrido.text.toString()}")
                Log.d("BOTAO CALCULATE", "Custo por KM Rodado -> ${custoPorKM.toString()}")
                //desenvolver ideia abaixo quando for aplicar as correcoes no app
                //Log.d("BOTAO CALCULATE", "Custo total dessa Recarga -> ${custoRecarga.toString()} R$")
        }
            //Treino prático das outras Views
            //configurando setOnCheckedChangeListener no RadioGroup
            rgFood.setOnCheckedChangeListener(){
                    group, checkedId ->
                when (checkedId){
                    R.id.rbPizza -> {Log.d("RADIOGROUP FOOD", "Pizza")}
                    R.id.rbBurger -> {Log.d("RADIOGROUP FOOD", "Burger")}
                }
            }

            //cofigurando setOnCheckedChangeListener no checkbox
            cb_Compromisso1.setOnCheckedChangeListener(){
                buttonView, isChecked ->
                if (isChecked){
                    Log.d("CHECKBOX1","Compromisso 1")
                }else{
                    Log.d("CHECKBOX1", "Compromisso 1 desmarcado")
                }
            }
            cb_Compromisso2.setOnCheckedChangeListener(){
                    buttonView, isChecked ->
                if (isChecked){
                    Log.d("CHECKBOX2","Compromisso 2")
                }else{
                    Log.d("CHECKBOX2", "Compromisso 2 desmarcado")
                }
            }
            //configurando setOnCheckedChangeListener no switch
            swLampada.setOnCheckedChangeListener(){
                buttonView, isChecked ->
            if (swLampada.isChecked){
                Log.d("SWITCH","Lampada ligada")
            }else{
                Log.d("SWITCH", "Lampada desligada")
            }
    }
}
        setupListeners()
}
}