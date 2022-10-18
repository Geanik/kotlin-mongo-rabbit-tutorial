package io.geanik.kotlin_mongo_rabbit_tutorial.backend.api

import io.geanik.kotlin_mongo_rabbit_tutorial.backend.logic.CustomerService
import io.geanik.kotlin_mongo_rabbit_tutorial.backend.model.Customer
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RestController
class CustomerResource(val service: CustomerService) {

    @GetMapping("customers")
    @ResponseBody
    fun getAllCustomers(): List<Customer> {
        return service.findAllCustomers()
    }

    @GetMapping("customers/{id}")
    @ResponseBody
    fun getCustomerById(@PathVariable id: Int): Customer? {
        return service.findCustomerById(id)
    }

    @PostMapping("customers")
    @ResponseBody
    fun createCustomer(@RequestBody customer: Customer): Customer {
        return service.insertCustomer(customer)
    }

}