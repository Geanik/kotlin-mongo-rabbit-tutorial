package io.geanik.kotlin_mongo_rabbit_tutorial.backend.logic

import io.geanik.kotlin_mongo_rabbit_tutorial.backend.data.CustomerRepo
import io.geanik.kotlin_mongo_rabbit_tutorial.backend.model.Customer
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

interface CustomerService {

    fun findCustomerById(id: Int): Customer?

    fun findAllCustomers(): List<Customer>

    fun insertCustomer(customer: Customer): Customer

    fun updateCustomer(customer: Customer): Customer
}

@Service
class CustomerServiceImpl(val customerRepo: CustomerRepo) : CustomerService {

    override fun findCustomerById(id: Int): Customer? {
        return customerRepo.findByIdOrNull(id)
    }

    override fun findAllCustomers(): List<Customer> {
        return customerRepo.findAll()
    }

    override fun insertCustomer(customer: Customer): Customer {
        return customerRepo.insert(customer)
    }

    override fun updateCustomer(customer: Customer): Customer {
        return customerRepo.save(customer)
    }


}