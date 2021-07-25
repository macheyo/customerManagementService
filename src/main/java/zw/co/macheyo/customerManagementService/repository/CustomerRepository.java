package zw.co.macheyo.customerManagementService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import zw.co.macheyo.customerManagementService.data.Customer;

public interface CustomerRepository extends JpaRepository<Customer, String> {
}
