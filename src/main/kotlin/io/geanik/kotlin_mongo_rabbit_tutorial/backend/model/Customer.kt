package io.geanik.kotlin_mongo_rabbit_tutorial.backend.model

import org.springframework.data.annotation.Id

data class Customer(@Id val id: Int, val name: String, val orders: MutableList<Order>)
