package fr.isen.cousseau.androiderestaurant.data.model

import java.util.Date

data class Price(var id: Int,
                 var id_pizza: Int,
                 var id_size: Int,
                 var price: String,
                 var create_date: String,
                 var update_date: String,
                 var size: String) : java.io.Serializable
