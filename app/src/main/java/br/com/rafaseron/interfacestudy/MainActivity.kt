package br.com.rafaseron.interfacestudy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    //inicializamos as variveis e typamos
    lateinit var btnCalculate: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fun setupViews(){
            btnCalculate = findViewById(R.id.btnCalculate)
        }
        setupViews()

        //adicionar listener para abrir a nova activity no botao
        fun setupListeners(){
            btnCalculate.setOnClickListener{
                startActivity(Intent(this,CalcularAutonomiaActivity::class.java))
        }

}
        setupListeners()
}
}