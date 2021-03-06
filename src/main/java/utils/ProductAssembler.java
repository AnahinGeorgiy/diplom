package utils;

import com.maqfromspace.appsmartrestservice.controllers.CustomersController;
import com.maqfromspace.appsmartrestservice.controllers.ProductController;
import com.maqfromspace.appsmartrestservice.entities.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

//Assembler for creating EntityModel from Product entity
@Component
public class ProductAssembler implements RepresentationModelAssembler<Product, EntityModel<Product>> {
    public EntityModel<Product> toModel(Product product) {

        UUID customerId = product.getCustomer().getId();
        UUID productId = product.getId();
        return EntityModel.of(product,
                linkTo(methodOn(ProductController.class).getProduct(productId)).withSelfRel(),
                linkTo(methodOn(CustomersController.class).getCustomer(customerId)).withRel("customer"),
                linkTo(methodOn(ProductController.class).getProducts(customerId, Pageable.unpaged())).withRel("allCustomerProducts"),
                linkTo(methodOn(CustomersController.class).getCustomers(Pageable.unpaged())).withRel("allCustomers"));
    }
}
