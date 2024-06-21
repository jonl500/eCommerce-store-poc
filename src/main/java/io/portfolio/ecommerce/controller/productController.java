package io.portfolio.ecommerce.controller;
import io.portfolio.ecommerce.service.ProductService;
import io.portfolio.ecommerce.model.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.constraints.NotNull;

/**
 * productController class
 * @RestController annotation marks the class as a RESTful web resource.
 * @RequestMapping annotation provides the mapping for the class.
 *
 * */
@RestController
@RequestMapping("/api/products")
public class productController {

    private ProductService productService;

    public productController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * This method is annotated with @GetMapping to handle HTTP GET requests with URLs "/" and "".
     * It retrieves all products by invoking the getAllProducts method of the productService instance.
     *
     * @return Iterable<Product> An iterable collection of Product objects representing all products.
     */
    @GetMapping(value = {"", "/"})
    public @NotNull Iterable<Product> getAllProducts() {
        return productService.getAllProducts();
    }


}
