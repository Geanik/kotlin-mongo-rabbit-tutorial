package io.geanik.kotlin_mongo_rabbit_tutorial.backend

import io.geanik.kotlin_mongo_rabbit_tutorial.backend.data.BackendDataPackage
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories

@EnableMongoRepositories(basePackageClasses = [BackendDataPackage::class])
@SpringBootApplication
class BackendApplication

fun main(args: Array<String>) {
    runApplication<BackendApplication>(*args)
}