package br.com.rafaseron.interfacestudy.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.rafaseron.interfacestudy.R
import br.com.rafaseron.interfacestudy.domain.Carro

class CarAdapter (private val ListaCarros : List<Carro>) : RecyclerView.Adapter<CarAdapter.CarViewHolder>() {

    //criando um ViewHolder
    class CarViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val textoPrice : TextView
        val textoBateria : TextView
        val textoPotencia : TextView
        val textoRecarga : TextView
        init {
            textoPrice = itemView.findViewById(R.id.txtNumberPrice)
            textoBateria = itemView.findViewById(R.id.txtNumberBattery)
            textoPotencia = itemView.findViewById(R.id.txtNumberPower)
            textoRecarga = itemView.findViewById(R.id.txtNumberCharge)
        }
    }

    //CONTROLE do ViewHolder

    //aqui é onde inflamos um layout
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        //aqui é criada um objeto "view" com o layout específico inflado
        val view = LayoutInflater.from(parent.context).inflate(R.layout.carro_item, parent, false)

        //aqui precisamos retornar o objeto com o layout inflado. É passado diretamente ao nosso ViewHolder
        return CarViewHolder(view)
    }

    //é chamado quando um item precisa ser exibido na tela
    /* ele é o método que faz o serviço braçal de trocar o conteúdo original da View que selecionamos em cima
    pelo novo conteúdo que será exibido no lugar */

    /* aqui criaremos um objeto do ViewHolder para então acessar diretamente a variável de view de dentro da classe para então fazer o serviço braçal.
    * Nisso, passamos então o conjunto de dados a qual ela será responsável por mostrar */
    override fun onBindViewHolder(objetoHolder: CarViewHolder, position: Int) {
        objetoHolder.textoPrice.text = ListaCarros[position].preco
        objetoHolder.textoBateria.text = ListaCarros[position].bateria
        objetoHolder.textoPotencia.text = ListaCarros[position].potencia
        objetoHolder.textoRecarga.text = ListaCarros[position].recarga
    }

    //numero total de itens do conjunto de dados
    override fun getItemCount(): Int = ListaCarros.size


}

