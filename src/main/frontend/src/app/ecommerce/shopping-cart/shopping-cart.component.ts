import {Component, OnInit, OnDestroy, EventEmitter, Output} from '@angular/core';
import { ProductOrder } from '../model/product-order.model';
import { EcommerceService } from '../services/ecommerce-service.service';
import { Subscription } from 'rxjs/internal/Subscription';
import { ProductOrders } from '../model/product-orders.model';
import { CommonModule } from "@angular/common";

@Component({
  selector: 'app-shopping-cart',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './shopping-cart.component.html',
  styleUrl: './shopping-cart.component.css'
})
export class ShoppingCartComponent implements OnInit, OnDestroy {

  orderFinished: boolean;
  //@ts-ignore
  orders: ProductOrders;
  total: number;
  //@ts-ignore
  sub: Subscription;

  @Output() onOrderFinished = new EventEmitter<boolean>();

  constructor(private ecommerceService: EcommerceService) {
    this.total = 0;
    this.orderFinished = false;
    this.onOrderFinished = new EventEmitter<boolean>();
  }

  ngOnInit() {
    this.orders = new ProductOrders();
    this.loadCart();
    this.loadTotal();
  }
ngOnDestroy() {
    this.sub.unsubscribe(); // Unsubscribe from the subscription (rxjs feature)
}

private calculateTotal(productOrders: ProductOrder[]): number {
  let sum = 0;
  productOrders.forEach(productOrder => {
    sum += productOrder.product.price * productOrder.quantity;
  });
  return sum;
}

  /**
   * Finishes the order and emits an event indicating that the order has been finished.
   *
   * @return {void} This function does not return a value.
   */
finishOrder() {
    this.orderFinished = true;
    this.ecommerceService.Total = this.total;
    this.onOrderFinished.emit(this.orderFinished);
  }

  /**
   * Loads the total by subscribing to the `OrdersChanged` event of the `ecommerceService`.
   * When the event is triggered, it calculates the total by calling the `calculateTotal` method
   * with the `productOrders` array from the `orders` object. The calculated total is then assigned
   * to the `total` property.
   *
   * @return {void} This function does not return a value.
   */
  loadTotal(){
  this.sub = this.ecommerceService.OrdersChanged.subscribe(
    () => {
      this.total = this.calculateTotal(this.orders.productOrders);
    }
  )
  }

  /**
   * Loads the cart by subscribing to the `ProductOrderChanged` event of the `ecommerceService`.
   * When the event is triggered, it retrieves the selected product order from the `ecommerceService`,
   * creates a new `ProductOrder` object with the product and quantity, and adds it to the `productOrders`
   * array of the `orders` object. It then updates the `ProductOrders` property of the `ecommerceService`
   * with the `orders` object, updates the `orders` object with the updated `ProductOrders` property,
   * and calculates the total by calling the `calculateTotal` method with the `productOrders` array.
   * The calculated total is then assigned to the `total` property.
   *
   * @return {void} This function does not return a value.
   */
  loadCart(){
    this.sub = this.ecommerceService.ProductOrderChanged.subscribe(() => {
      let productOrder = this.ecommerceService.SelectedProductOrder;
      if(productOrder) {
        this.orders.productOrders.push(
          new ProductOrder(productOrder.product, productOrder.quantity)
        );
      }
      this.ecommerceService.ProductOrders = this.orders;
      this.orders = this.ecommerceService.ProductOrders;
      this.total = this.calculateTotal(this.orders.productOrders);

    }

    )
  }
  reset() {
    this.orderFinished = false;
    this.orders = new ProductOrders();
    this.orders.productOrders = []
    this.loadTotal();
    this.total = 0;
  }
}
