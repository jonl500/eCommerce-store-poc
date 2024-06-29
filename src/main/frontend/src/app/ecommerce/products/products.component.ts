import { Component, OnInit } from '@angular/core';
import {ProductOrder} from "../model/product-order.model";
import {EcommerceService} from "../services/ecommerce-service.service";
import {Subscription} from "rxjs/internal/Subscription";
import {ProductOrders} from "../model/product-orders.model";
import {Product} from "../model/product.model";
import { CommonModule} from "@angular/common";
import {FormsModule} from "@angular/forms";

@Component({
        selector: 'app-products',
        standalone: true,
  imports: [CommonModule, FormsModule],
        templateUrl: './products.component.html',
        styleUrl: './products.component.css'
})
export class ProductsComponent implements OnInit {
productOrders: ProductOrder[] = [];
products: Product[] = [];
// @ts-ignore
selectedProductOrder: ProductOrder ;
// @ts-ignore
private shoppingCartOrders: ProductOrders;
// @ts-ignore
sub : Subscription ;
productSelected: boolean = false;
  constructor(private ecommerceService: EcommerceService) {

  }

  ngOnInit() {
    this.productOrders = [];
    this.loadProducts();
    this.loadOrders();
  }

  /**
   * Retrieves all products from the ecommerce service and updates the component's state.
   * The `products` array is assigned to the `this.products` property of the component.
   * For each product in the `products` array, a new `ProductOrder` object is created with the `product`
   * and a default value of `0` for the order, and it is pushed into the `productOrders` array.
   * If there is an error while fetching the products, the error is logged to the console using `console.log()`.
   *
   * @return {void} This function does not return anything.
   */
  loadProducts(){
    this.ecommerceService.getAllProducts()
      .subscribe(
        //@ts-ignore
        (products : any[]) => {
          this.products = products;
          this.products.forEach(product => {
          this.productOrders.push(new ProductOrder(product, 0));
        });
      }, error => console.log(error)
    );
  }

  /**
   * Loads the orders by subscribing to the `OrdersChanged` event of the `ecommerceService`
   * and updating the `shoppingCartOrders` property accordingly.
   * This function creates a subscription to the `OrdersChanged` event of the `ecommerceService`.
   * Whenever the event is triggered, the provided callback function is executed.
   * This callback function updates the `shoppingCartOrders` property
   * with the value of `ecommerceService.ProductOrders`.
   *
   * @returns {void} This function does not return a value.
   */
  loadOrders(){
    this.sub = this.ecommerceService.OrdersChanged.subscribe(
      () => {
        this.shoppingCartOrders = this.ecommerceService.ProductOrders;
      }
    )
  }

  addToCart(order: ProductOrder) {
    this.ecommerceService.SelectedProductOrder = order;
    this.selectedProductOrder = this.ecommerceService.SelectedProductOrder;
    this.productSelected = true;
  }

  getProductIndex(product: Product): number {
    return this.ecommerceService.ProductOrders.productOrders.findIndex(
      value => value.product === product);
  }

  isProductSelected(product: Product): boolean {
    return this.getProductIndex(product) > -1;
  }

  removeFromCart(productOrder: ProductOrder) {
    let index = this.getProductIndex(productOrder.product);
    if (index > -1) {
      this.shoppingCartOrders.productOrders.splice(
        this.getProductIndex(productOrder.product), 1);
    }
    this.ecommerceService.ProductOrders = this.shoppingCartOrders;
    this.shoppingCartOrders = this.ecommerceService.ProductOrders;
    this.productSelected = false;
  }

  reset() {
    this.productOrders = [];
    this.loadProducts();
    this.ecommerceService.ProductOrders.productOrders = [];
    this.loadOrders();
    this.productSelected = false;
  }
}

