package io.geanik.kotlin_mongo_rabbit_tutorial.backend.logic

import com.google.gson.Gson
import com.rabbitmq.client.ConnectionFactory
import io.geanik.kotlin_mongo_rabbit_tutorial.backend.model.Order
import org.springframework.stereotype.Service
import java.nio.charset.StandardCharsets

interface OrderProcessingDelegator {

    fun delegateProcessing(order: Order)

}

@Service
class OrderProcessingAmqpDelegator : OrderProcessingDelegator {

    private val factory = ConnectionFactory()
    private val gson = Gson()

    override fun delegateProcessing(order: Order) {
        factory.newConnection(AMQP_CONNECTION_STRING).use { connection ->
            connection.createChannel().use { channel ->
                channel.queueDeclare(QUEUE_NAME, true, false, false, null)

                channel.basicPublish(
                    "",
                    QUEUE_NAME,
                    null,
                    gson.toJson(order).toByteArray(StandardCharsets.UTF_8)
                )
            }
        }
    }

    companion object {
        private const val AMQP_CONNECTION_STRING = "amqp://guest:guest@localhost:5672/"
        private const val QUEUE_NAME = "orders"
    }

}