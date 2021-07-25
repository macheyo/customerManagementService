package zw.co.macheyo.customerManagementService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zw.co.macheyo.customerManagementService.data.FAQ;

import java.util.Optional;

@Repository
public interface FAQRepository extends JpaRepository <FAQ, String> {
    Optional<FAQ> findByTopic(String topic);
}
