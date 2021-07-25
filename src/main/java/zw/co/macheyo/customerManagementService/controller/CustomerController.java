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
import zw.co.macheyo.customerManagementService.modelAssembler.CustomerModelAssembler;
import zw.co.macheyo.customerManagementService.service.CustomerService;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 *
 * @author Kudzai Justice Macheyo
 */
@RestController
@RequestMapping(value = "/customers", produces = APPLICATION_JSON_VALUE)
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @Autowired
    CustomerModelAssembler assembler;

    @Operation(summary = "create new customer")
    @ApiResponse(responseCode = "201", description = "Customer created", content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = Customer.class))})
    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<EntityModel<Customer>> newCustomer(@Valid @RequestBody Customer customer){
        Customer newCustomer = customerService.save(customer);
        return ResponseEntity
                .created(linkTo(methodOn(CustomerController.class).oneCustomer(newCustomer.getId())).toUri())
                .body(assembler.toModel(customer));
    }

    @Operation(summary = "Get customer by their id")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Found the customer", content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = Customer.class))}),
            @ApiResponse(responseCode = "404", description = "Customer not found", content = @Content)})
    @GetMapping("/{id}")
    public EntityModel<Customer> oneCustomer(@PathVariable String id) {
        Customer customer = customerService.findById(id).orElseThrow(()->new NotFoundException("Customer not found"));
        return assembler.toModel(customer);
    }

    @Operation(summary = "Returns a list of customers and sorted/filtered based on the query parameters")
    @ApiResponse(responseCode = "200", description = "Customers listed", content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = Customer.class))})
    @GetMapping()
    public CollectionModel<EntityModel<Customer>> allCustomers() {

        List<EntityModel<Customer>> customers = customerService.findAll();
        return CollectionModel.of(customers, linkTo(methodOn(CustomerController.class).allCustomers()).withSelfRel());
    }
}
