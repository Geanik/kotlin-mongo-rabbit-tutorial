package io.geanik.kotlin_mongo_rabbit_tutorial.backend.data

import io.geanik.kotlin_mongo_rabbit_tutorial.backend.model.Customer
import org.springframework.data.mongodb.repository.MongoRepository

interface CustomerRepo : MongoRepository<Customer, Int>
