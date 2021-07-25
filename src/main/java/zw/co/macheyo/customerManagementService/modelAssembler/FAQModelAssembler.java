package zw.co.macheyo.customerManagementService.modelAssembler;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import zw.co.macheyo.customerManagementService.controller.FAQController;
import zw.co.macheyo.customerManagementService.data.FAQ;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class FAQModelAssembler implements RepresentationModelAssembler<FAQ, EntityModel<FAQ>> {
    @Override
    public EntityModel<FAQ> toModel(FAQ entity) {
        return EntityModel.of(entity, linkTo(methodOn(FAQController.class).allFAQs()).withSelfRel());
    }

    @Override
    public CollectionModel<EntityModel<FAQ>> toCollectionModel(Iterable<? extends FAQ> entities) {
        return RepresentationModelAssembler.super.toCollectionModel(entities);
    }
}
