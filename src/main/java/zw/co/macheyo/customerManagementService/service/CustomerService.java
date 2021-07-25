package zw.co.macheyo.customerManagementService.service;

import org.springframework.hateoas.EntityModel;
import zw.co.macheyo.customerManagementService.data.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    Customer save(Customer customer);
    Optional<Customer> findById(String id);
    List<EntityModel<Customer>> findAll();
}
