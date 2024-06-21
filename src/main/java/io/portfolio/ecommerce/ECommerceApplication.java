package io.portfolio.ecommerce;

import io.portfolio.ecommerce.model.Product;
import io.portfolio.ecommerce.service.ProductService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ECommerceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ECommerceApplication.class, args);
    }

    /**
     * This block of code defines a CommandLineRunner bean.
     *
     * CommandLineRunner is an interface provided by Spring Boot that allows the execution of code when the application starts.
     *
     * In this specific case, the CommandLineRunner bean is responsible for executing the provided lambda expression when the application starts.
     *
     * The lambda expression inside the CommandLineRunner bean performs three productService.save calls, each saving a new Product instance.
     *
     * The first call saves a Product with ID 1, name "iPhone", price 999.99, and picture URL "https://picsum.photos/200/300".
     *
     * The second call saves a Product with ID 2, name "MacBook", price 1299.99, and picture URL "https://picsum.photos/200/300".
     *
     * The third call saves a Product with ID 3, name "Apple Watch", price 299.99, and picture URL "https://picsum.photos/200/300".
     */
@Bean
    CommandLineRunner runner(ProductService productService){
        return args -> {
            productService.save(new Product(1L, "iPhone", 999.99, "https://picsum.photos/200/300"));
            productService.save(new Product(2L, "MacBook", 1299.99, "https://picsum.photos/200/300"));
            productService.save(new Product(3L, "Apple Watch", 299.99, "https://picsum.photos/200/300"));
        };
    }
}
