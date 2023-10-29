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
    lateinit var btnCalculate: Button
    lateinit var radioGroup: RadioGroup
    lateinit var checkBox1: CheckBox
    lateinit var checkBox2: CheckBox
    lateinit var switch: Switch
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //fizemos as variaveis (já typadas) receberem valores (no caso, Views)
        //dessa maneira ligamos a View com o codigo em Kotlin (com as variaveis)
        fun setupViews (){
        edtKHWPrice = findViewById(R.id.edtKHWPrice)
        btnCalculate = findViewById(R.id.btnCalculate)
        }
        setupViews()

        //agora vamos começar a usar Listeners para monitorar eventos de clicks
        fun setupListeners(){
        btnCalculate.setOnClickListener{
            Log.d("BOTAO CALCULATE","texto capturado -> ${edtKHWPrice.text.toString()}")
        }
        }
        setupListeners()
    }
}