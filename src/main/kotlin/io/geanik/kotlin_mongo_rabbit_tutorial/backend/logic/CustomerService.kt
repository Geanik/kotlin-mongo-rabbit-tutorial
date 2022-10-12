package io.geanik.kotlin_mongo_rabbit_tutorial.backend.logic

import io.geanik.kotlin_mongo_rabbit_tutorial.backend.data.CustomerRepo
import io.geanik.kotlin_mongo_rabbit_tutorial.backend.model.Customer
import org.springframework.stereotype.Service

interface CustomerService {

    fun findAllCustomers(): List<Customer>

    fun insertCustomer(customer: Customer): Customer

}

@Service
class CustomerServiceImpl(val customerRepo: CustomerRepo) : CustomerService {

    override fun findAllCustomers(): List<Customer> {
        return customerRepo.findAll()
    }

    override fun insertCustomer(customer: Customer): Customer {
        return customerRepo.insert(customer)
    }

}