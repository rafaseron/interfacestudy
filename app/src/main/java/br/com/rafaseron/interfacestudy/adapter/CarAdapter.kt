package br.com.rafaseron.interfacestudy.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.rafaseron.interfacestudy.R
import br.com.rafaseron.interfacestudy.data.local.StoredCarDatabaseManager
import br.com.rafaseron.interfacestudy.domain.Carro

class CarAdapter (private val listaCarros : List<Carro>, private val contexto: Context) : RecyclerView.Adapter<CarAdapter.CarViewHolder>() {

    var carItemListener: (Carro) -> Unit = {}

    //criando um ViewHolder
    class CarViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val textoPrice : TextView
        val textoBateria : TextView
        val textoPotencia : TextView
        val textoRecarga : TextView
        val imgFavorite : ImageView
        init {
            textoPrice = itemView.findViewById(R.id.txtNumberPrice)
            textoBateria = itemView.findViewById(R.id.txtNumberBattery)
            textoPotencia = itemView.findViewById(R.id.txtNumberPower)
            textoRecarga = itemView.findViewById(R.id.txtNumberCharge)
            imgFavorite = itemView.findViewById(R.id.imgFavorite)
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
        objetoHolder.textoPrice.text = listaCarros[position].preco
        objetoHolder.textoBateria.text = listaCarros[position].bateria
        objetoHolder.textoPotencia.text = listaCarros[position].potencia
        objetoHolder.textoRecarga.text = listaCarros[position].recarga

        objetoHolder.imgFavorite.tag = (R.drawable.baseline_favorite_border_48)
        objetoHolder.imgFavorite.setOnClickListener(){

            carItemListener(listaCarros[position])
            mudarImagem(objetoHolder, position)

        }
    }

    private fun mudarImagem(objetoHolder: CarViewHolder, position: Int) {

        if (objetoHolder.imgFavorite.tag == R.drawable.baseline_favorite_border_48) {
            objetoHolder.imgFavorite.setImageResource(R.drawable.baseline_favorite_48)
            objetoHolder.imgFavorite.tag = R.drawable.baseline_favorite_48
        } else {
            objetoHolder.imgFavorite.tag = R.drawable.baseline_favorite_border_48
            objetoHolder.imgFavorite.setImageResource(R.drawable.baseline_favorite_border_48)
            StoredCarDatabaseManager().deleteCar(contexto, listaCarros[position].id)
        }
    }

    //numero total de itens do conjunto de dados
    override fun getItemCount(): Int = listaCarros.size


}

