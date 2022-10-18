package io.geanik.kotlin_mongo_rabbit_tutorial.backend.api

import io.geanik.kotlin_mongo_rabbit_tutorial.backend.logic.OrderService
import io.geanik.kotlin_mongo_rabbit_tutorial.backend.model.Order
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class OrderResource(val service: OrderService) {

    @PostMapping("customers/{customerId}/orders")
    fun createNewOrder(@RequestBody order: Order, @PathVariable customerId: Int): Order {
        return service.insertOrder(order, customerId)
    }
}