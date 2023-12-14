package br.com.rafaseron.interfacestudy.data

import br.com.rafaseron.interfacestudy.domain.Carro

class CarFactory {

     val list = listOf(

        Carro(id = 1, preco = "120.000 R$", bateria = "300 kWh", potencia = "300 cv", recarga = "12 mins", urlPhoto = "www.google.com.br"),
         Carro(id = 2, preco = "300.000 R$", bateria = "500 kWh", potencia = "480 cv", recarga = "30 mins", urlPhoto = "www.google.com.br"),
         Carro(id = 3, preco = "500.000 R$", bateria = "900 kWh", potencia = "600 cv", recarga = "80 mins", urlPhoto = "www.google.com.br")


    )

}