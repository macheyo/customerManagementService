package zw.co.macheyo.customerManagementService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import zw.co.macheyo.customerManagementService.data.Customer;
import zw.co.macheyo.customerManagementService.data.FAQ;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, String> {
    Optional<Customer> findByCustomerId(String id);
}
