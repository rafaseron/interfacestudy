package br.com.rafaseron.interfacestudy.data

import br.com.rafaseron.interfacestudy.domain.Carro

class CarFactory {

     val list = listOf(

        Carro(id = 1, preco = "120.000 R$", bateria = "300 kWh", potencia = "300 cv", recarga = "12 mins", urlPhoto = "www.google.com.br"),
         Carro(id = 2, preco = "300.000 R$", bateria = "500 kWh", potencia = "480 cv", recarga = "30 mins", urlPhoto = "www.google.com.br"),
         Carro(id = 3, preco = "500.000 R$", bateria = "900 kWh", potencia = "600 cv", recarga = "80 mins", urlPhoto = "www.google.com.br")


    )

    //VERBOS HTTP

    /*
        - GET (recuperar informacoes)
        - POST (enviar informacoes)
        - DELETE (deletar algum recurso)
        - PUT (alterar uma entidade como um to.do) -> exemplo, seria usar para alterar todos os atributos da classe Carro acima
        - PATCH (alterar um atributo da entidade) -> exemplo, alterar só o atributo da bateria

        tem como utilizar por exemplo PUT ou POST para fazer alguams alteracoes ai, mas nao é uma boa prática
     */



}