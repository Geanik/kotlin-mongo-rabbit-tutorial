package io.geanik.kotlin_mongo_rabbit_tutorial.backend.logic

import io.geanik.kotlin_mongo_rabbit_tutorial.backend.model.Order
import org.springframework.stereotype.Service

interface OrderService {

    fun insertOrder(order: Order, customerId: Int): Order

}

@Service
class OrderServiceImpl(private val customerService: CustomerService) : OrderService {

    override fun insertOrder(order: Order, customerId: Int): Order {
        val customer = customerService.findCustomerById(customerId)

        customer?.run {
            customer.orders.add(order)
            customerService.updateCustomer(customer)
        } ?: throw IllegalArgumentException("Customer not found")

        return order
    }

}