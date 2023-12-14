package br.com.rafaseron.interfacestudy.data

import br.com.rafaseron.interfacestudy.domain.Carros

class CarFactory {

    val list = listOf(

        Carros(
            id = 1,
            preco = "120.000 R$",
            bateria = "300 kWh",
            potencia = "300 cv",
            recarga = "12 mins",
            urlPhoto = "www.google.com.br")
    )

}