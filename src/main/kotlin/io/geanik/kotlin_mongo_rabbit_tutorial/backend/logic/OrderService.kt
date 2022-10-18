package io.geanik.kotlin_mongo_rabbit_tutorial.backend.logic

import com.rabbitmq.client.ConnectionFactory
import io.geanik.kotlin_mongo_rabbit_tutorial.backend.model.Order
import org.springframework.stereotype.Service
import java.nio.charset.StandardCharsets

interface OrderService {

    fun insertOrder(order: Order, customerId: Int): Order

}

@Service
class OrderServiceImpl(
    private val customerService: CustomerService,
    private val orderProcessingDelegator: OrderProcessingDelegator
) : OrderService {

    private val factory = ConnectionFactory()

    override fun insertOrder(order: Order, customerId: Int): Order {
        val customer = customerService.findCustomerById(customerId)

        customer?.run {
            customer.orders.add(order)
            customerService.updateCustomer(customer)
        } ?: throw IllegalArgumentException("Customer not found")

        orderProcessingDelegator.delegateProcessing(order)
        return order
    }

}