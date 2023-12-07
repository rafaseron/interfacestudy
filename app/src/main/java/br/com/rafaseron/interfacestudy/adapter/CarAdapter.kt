package br.com.rafaseron.interfacestudy.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.rafaseron.interfacestudy.R

class CarAdapter (private val carros : Array<String>) : RecyclerView.Adapter<CarAdapter.CarViewHolder>() {

    //criando um ViewHolder
    class CarViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val textView : TextView
        init {
            textView = itemView.findViewById(R.id.txtNumberPrice)
        }
    }

    //CONTROLE do ViewHolder

    //aqui é onde inflamos um layout
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        //aqui é criada uma variavel view para inflar o layout
        val view = LayoutInflater.from(parent.context).inflate(R.layout.carro_item, parent, false)

        //aqui há o retorno da variavel view com o layout já inflado. É passado diretamente ao nosso ViewHolder
        return CarViewHolder(view)
    }

    //é chamado quando um item precisa ser exibido na tela
    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        holder.textView.text = carros[position]
    }

    //numero total de itens do conjunto de dados
    override fun getItemCount(): Int = carros.size


}

