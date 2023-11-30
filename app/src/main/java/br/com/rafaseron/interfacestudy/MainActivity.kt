package br.com.rafaseron.interfacestudy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView

class MainActivity : AppCompatActivity() {
    //inicializamos as variveis e typamos
    lateinit var btnCalculate: Button
    lateinit var lvInformacoes: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fun setupViews(){
            btnCalculate = findViewById(R.id.btnCalculate)
            lvInformacoes = findViewById(R.id.lvInformacoes)
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

            // este é um adapter. ele é como se fosse uma pessoa que organiza os itens para que o ListView saiba como mostra-los

            //ArrayAdapter é uma classe pronta do Android. estamos criando um objeto e passando os parametros
            /* no caso passamos que iremos utilizar o modelo de layout de lista sendo "simple list 1" e que os dados
            os quais ele precisa organizar, para que sejam exibidos pela ListView, estão em "dados" */
            val adaptador = ArrayAdapter(this, android.R.layout.simple_list_item_1, dados)
            lvInformacoes.adapter = adaptador

        }
        setupListView()
}
}