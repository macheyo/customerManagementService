package zw.co.macheyo.customerManagementService.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.webjars.NotFoundException;
import zw.co.macheyo.customerManagementService.data.Customer;
import zw.co.macheyo.customerManagementService.data.FAQ;
import zw.co.macheyo.customerManagementService.modelAssembler.FAQModelAssembler;
import zw.co.macheyo.customerManagementService.service.FAQService;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/frequently-asked", produces = APPLICATION_JSON_VALUE)
public class FAQController {
    @Autowired
    FAQService faqService;

    @Autowired
    FAQModelAssembler assembler;

    @Operation(summary = "Create new question")
    @ApiResponse(responseCode = "201", description = "FAQ created", content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = FAQ.class))})
    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<EntityModel<FAQ>> newFAQ(@Valid @RequestBody FAQ faq){
        FAQ newFAQ = faqService.save(faq);
        return ResponseEntity
                .created(linkTo(methodOn(FAQController.class).FAQbyId(newFAQ.getId())).toUri())
                .body(assembler.toModel(faq));
    }

    @Operation(summary = "Get question by id")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Found the FAQ", content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = FAQ.class))}),
            @ApiResponse(responseCode = "404", description = "FAQ not found", content = @Content)})
    @GetMapping("/{id}")
    public EntityModel<FAQ> FAQbyId(@PathVariable String id) {
        FAQ faq = faqService.findById(id).orElseThrow(()->new NotFoundException("FAQ not found"));
        return assembler.toModel(faq);
    }

    @Operation(summary = "Get question by topic")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Found the FAQ", content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = FAQ.class))}),
            @ApiResponse(responseCode = "404", description = "FAQ not found", content = @Content)})
    @GetMapping("/topic/{topic}")
    public EntityModel<FAQ> FAQbyTopic(@PathVariable String topic) {
        FAQ faq = faqService.findByTopic(topic).orElseThrow(()->new NotFoundException("FAQ not found"));
        return assembler.toModel(faq);
    }

    @Operation(summary = "Returns a list of FAQs and sorted/filtered based on the query parameters")
    @ApiResponse(responseCode = "200", description = "FAQs listed", content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = FAQ.class))})
    @GetMapping()
    public CollectionModel<EntityModel<FAQ>> allFAQs() {

        List<EntityModel<FAQ>> faqList = faqService.findAll();
        return CollectionModel.of(faqList, linkTo(methodOn(FAQController.class).allFAQs()).withSelfRel());
    }
}
