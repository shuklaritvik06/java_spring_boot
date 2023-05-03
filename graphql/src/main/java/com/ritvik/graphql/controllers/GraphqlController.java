package com.ritvik.graphql.controllers;

import com.ritvik.graphql.entities.CustomerEntity;
import com.ritvik.graphql.entities.GraphqlEntity;
import com.ritvik.graphql.graphql.CustomerInput;
import com.ritvik.graphql.graphql.OrderInput;
import com.ritvik.graphql.repositories.CustomerRepo;
import com.ritvik.graphql.repositories.OrderRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Controller
public class GraphqlController {
    @Autowired
    private CustomerRepo customerRepo;
    @Autowired
    private OrderRepo orderRepo;
    @QueryMapping("orders")
    public List<GraphqlEntity> getAllOrders(){
        return orderRepo.findAll();
    }
    @QueryMapping("customers")
    public List<CustomerEntity> getAllCustomers(){
        return customerRepo.findAll();
    }
    @QueryMapping("getOrder")
    public Optional<GraphqlEntity> getOrder(@Argument String orderId){
        return orderRepo.findById(new ObjectId(orderId));
    }
    @QueryMapping("getCustomer")
    public Optional<CustomerEntity> getCustomer(@Argument String customerId){
        return customerRepo.findById(new ObjectId(customerId));
    }
    @MutationMapping("add_order")
    public GraphqlEntity addOrder(@Argument OrderInput order){
        GraphqlEntity graphqlEntity = new GraphqlEntity();
        graphqlEntity.setAmount(order.getAmount());
        graphqlEntity.setDelivery_address(order.getDelivery_address());
        graphqlEntity.setDelivery_date(order.getDelivery_date());
        graphqlEntity.setCustomer_id(order.getCustomer_id());
        return orderRepo.save(graphqlEntity);
    }
    @MutationMapping("add_customer")
    public CustomerEntity addCustomer(@Argument CustomerInput customer){
        CustomerEntity customerInput1 = new CustomerEntity();
        customerInput1.setAddress(customer.getAddress());
        customerInput1.setName(customer.getName());
        customerInput1.setAge(customer.getAge());
        return customerRepo.save(customerInput1);
    }
    @MutationMapping("delete_order")
    public Optional<CustomerEntity> addCustomer(@Argument String id){
        Optional<CustomerEntity> customerEntity = customerRepo.findById(new ObjectId(id));
        customerRepo.deleteById(new ObjectId(id));
        return customerEntity;
    }
    @MutationMapping("update_customer")
    public Optional<CustomerEntity> updateCustomer(@Argument String id, @Argument CustomerInput customer){
        Optional<CustomerEntity> customerEntity = customerRepo.findById(new ObjectId(id));
        try{
            CustomerEntity customer2 = customerEntity.get();
            customer2.setName(customer.getName());
            customer2.setAge(customer.getAge());
            customer2.setAddress(customer.getAddress());
            customerRepo.save(customer2);
        }catch (NoSuchElementException err){
            System.out.println(err.getLocalizedMessage());
        }
      return customerRepo.findById(new ObjectId(id));
    }
    @MutationMapping("update_order")
    public Optional<GraphqlEntity> updateOrder(@Argument String id, @Argument OrderInput order){
        Optional<GraphqlEntity> graphqlEntity = orderRepo.findById(new ObjectId(id));
        try{
            GraphqlEntity graphql = graphqlEntity.get();
            graphql.setDelivery_date(order.getDelivery_date());
            graphql.setAmount(order.getAmount());
            graphql.setDelivery_address(order.getDelivery_address());
            graphql.setCustomer_id(order.getCustomer_id());
            orderRepo.save(graphql);
        }catch (NoSuchElementException err){
            System.out.println(err.getLocalizedMessage());
        }
        return orderRepo.findById(new ObjectId(id));
    }
}
