# kotlin-mongo-rabbit-tutorial
Simple Spring project for customer order processing. Try out MongoDB and RabbitMQ with Kotlin.

### Backend service
- offers a REST Api for creating customers and orders
- data is stored in MongoDB
- if new order received then push to RabbitMQ queue

### Processor service
- simple worker that processes the customers orders
- takes orders out of a RabbitMQ queue
- if processing finished then push confirmation msg to RabbitMQ queue
