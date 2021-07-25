package zw.co.macheyo.customerManagementService.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;
import zw.co.macheyo.customerManagementService.data.FAQ;
import zw.co.macheyo.customerManagementService.modelAssembler.FAQModelAssembler;
import zw.co.macheyo.customerManagementService.repository.FAQRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FAQServiceImpl implements FAQService{
    @Autowired
    FAQRepository faqRepository;

    @Autowired
    FAQModelAssembler assembler;

    @Override
    public FAQ save(FAQ faq) {
        return faqRepository.save(faq);
    }

    @Override
    public Optional<FAQ> findById(String id) {
        return faqRepository.findById(id);
    }

    @Override
    public Optional<FAQ> findByTopic(String topic) {
        return faqRepository.findByTopic(topic);
    }

    @Override
    public List<EntityModel<FAQ>> findAll() {
        return faqRepository.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
    }
}
