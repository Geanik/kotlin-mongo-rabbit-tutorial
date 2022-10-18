package io.geanik.kotlin_mongo_rabbit_tutorial.backend.model

import org.springframework.data.annotation.Id

data class Order(@Id val id: Int, val durationInSeconds: Int)
