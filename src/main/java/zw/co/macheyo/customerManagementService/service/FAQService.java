package zw.co.macheyo.customerManagementService.service;

import org.springframework.hateoas.EntityModel;
import zw.co.macheyo.customerManagementService.data.Customer;
import zw.co.macheyo.customerManagementService.data.FAQ;

import java.util.List;
import java.util.Optional;

public interface FAQService {
    FAQ save(FAQ faq);
    Optional<FAQ> findById(String id);
    Optional<FAQ> findByTopic(String topic);
    List<EntityModel<FAQ>> findAll();

}
