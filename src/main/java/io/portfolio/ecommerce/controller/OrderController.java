package io.portfolio.ecommerce.controller;


import io.portfolio.ecommerce.dto.OrderProductDto;
import io.portfolio.ecommerce.model.Order;
import io.portfolio.ecommerce.model.OrderProduct;
import io.portfolio.ecommerce.model.OrderStatus;
import io.portfolio.ecommerce.model.Product;
import io.portfolio.ecommerce.service.OrderProductService;
import io.portfolio.ecommerce.service.OrderService;
import io.portfolio.ecommerce.service.ProductService;
import org.springframework.web.bind.annotation.RestController;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.validation.constraints.NotNull;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    //service calls will be injected
    ProductService productService;
    OrderService orderService;
    OrderProductService orderProductService;

    public OrderController(ProductService productService, OrderService orderService, OrderProductService orderProductService){
        this.productService = productService;
        this.orderService = orderService;
        this.orderProductService = orderProductService;
    }

    /**
     * Retrieves all orders by invoking the getAllOrders method of the order service.
     * the @GetMapping annotation maps the HTTP GET requests to the getAllOrders method.
     * the @ResponseStatus annotation sets the HTTP status code of the response to 200 (OK).
     * @return An iterable collection of Order objects representing all orders.
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public @NotNull Iterable<Order> list() {
        return this.orderService.getAllOrders();
    }


    // Define a method named 'create' that handles a POST request
    // and expects an 'OrderForm' object in the request body
    @PostMapping
    public ResponseEntity<Order> create(@RequestBody OrderForm orderForm) {

      // Extract the list of 'OrderProductDto' objects from the 'OrderForm'
      List<OrderProductDto> formDtos = orderForm.getOrderProducts();
      // Call a method 'validateProductsExistence' to ensure the products in the order exist
      validateProductsExistence(formDtos);

      // Create a new 'Order' object
      Order order = new Order();
      // Set the status of the order to "PAID"
      order.setStatus(OrderStatus.PAID.name());
      // Call the 'createOrder' method from the 'orderService'
      // to persist the order in the database and receive the updated order
      order = this.orderService.createOrder(order);


      // Create an empty list to hold 'OrderProduct' objects
      List<OrderProduct> orderProducts = new ArrayList<>();

      // Loop through each 'OrderProductDto' in the 'formDtos' list
      for(OrderProductDto dto: formDtos){
          // Create a new 'OrderProduct' object using the 'orderProductService' and 'productService'
          orderProducts.add(orderProductService.create(new OrderProduct(order,
                  productService.getProduct(dto.getProduct()
                  .getId()),dto.getQuantity())));
      }

      //the setOrderProducts method is used to set the list of OrderProduct objects in the Order object
      order.setOrderProducts(orderProducts);
      // Call the 'updateOrder' method from the 'orderService'
      this.orderService.updateOrder(order);
      //This string is used to construct the URI of the created order
      // Construct a URI using the current servlet mapping
      String uri = ServletUriComponentsBuilder
              .fromCurrentServletMapping()
              // Append the path "/orders/{id}" to the URI
              .path("/orders/{id}")
              // Replace the "{id}" placeholder with the ID of the 'order' object
              .buildAndExpand(order.getId())
              // Convert the UriComponents instance to a string representation of the URI
              .toString();
      HttpHeaders headers = new HttpHeaders();
      headers.add("Location",uri);

      return new ResponseEntity<>(order, headers, HttpStatus.CREATED);
    }

    /**
     * Validates the existence of products in the order.
     *
     * @param  orderProducts   the list of order product DTOs to validate
     */
    private void validateProductsExistence(List<OrderProductDto> orderProducts) {
        List<OrderProductDto> list =  orderProducts.stream()
                .filter(op -> Objects.isNull(productService.getProduct(op.
                        getProduct().getId()))).collect(Collectors.toList());

    }

    public static class OrderForm {

        private List<OrderProductDto> orderProducts;

        public List<OrderProductDto> getOrderProducts() {
            return orderProducts;
        }

        public void setOrderProducts(List<OrderProductDto> orderProducts) {
            this.orderProducts = orderProducts;
        }
    }
}


