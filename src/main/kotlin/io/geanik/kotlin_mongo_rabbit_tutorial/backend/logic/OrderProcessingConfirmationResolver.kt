package io.geanik.kotlin_mongo_rabbit_tutorial.backend.logic

import com.google.gson.Gson
import com.rabbitmq.client.CancelCallback
import com.rabbitmq.client.ConnectionFactory
import com.rabbitmq.client.DeliverCallback
import com.rabbitmq.client.Delivery
import io.geanik.kotlin_mongo_rabbit_tutorial.backend.model.Order
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.nio.charset.StandardCharsets
import javax.annotation.PostConstruct

interface OrderProcessingConfirmationResolver {

    fun resolveConfirmation(order: Order)

}

@Service
class OrderProcessingConfirmationResolverImpl : OrderProcessingConfirmationResolver {

    private val factory = ConnectionFactory()
    private val connection = factory.newConnection(AMQP_CONNECTION_STRING)
    private val channel = connection.createChannel()
    private val consumerTag = "BackendApplication"

    private val logger = LoggerFactory.getLogger(this::class.java)
    private val gson = Gson()

    @PostConstruct
    fun startListeningForOrders() {
        channel.queueDeclare(QUEUE_NAME, true, false, false, null)

        val deliverCallback = DeliverCallback { consumerTag: String?, delivery: Delivery ->
            val message = String(delivery.body, StandardCharsets.UTF_8)
            val order = gson.fromJson(message, Order::class.java)
            logger.info("[$consumerTag] Received confirmation: '$order'")
            resolveConfirmation(order)
        }
        val cancelCallback = CancelCallback { consumerTag: String? ->
            logger.info("[$consumerTag] was canceled")
        }

        channel.basicConsume(QUEUE_NAME, true, consumerTag, deliverCallback, cancelCallback)
    }

    override fun resolveConfirmation(order: Order) {
        // TODO: save to DB
    }

    companion object {
        private const val AMQP_CONNECTION_STRING = "amqp://guest:guest@localhost:5672/"
        private const val QUEUE_NAME = "processing-confirmations"
    }

}