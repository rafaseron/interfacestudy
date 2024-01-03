package br.com.rafaseron.interfacestudy.data
import br.com.rafaseron.interfacestudy.domain.Carro
import retrofit2.http.GET
import retrofit2.Call

interface CarsAPI {
    @GET("car.json")
    fun getAllCars(): Call<List<Carro>>
}