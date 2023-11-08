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
    lateinit var btnCalculate: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //adicionar listener para abrir a nova activity no botao
        fun setupListeners(){
            btnCalculate.setOnClickListener{
                startActivity(Intent(this,CalcularAutonomiaActivity::class.java))
        }

}
        setupListeners()
}
}