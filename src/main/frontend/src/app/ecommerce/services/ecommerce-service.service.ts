import { Injectable } from '@angular/core';
import { Subject } from "rxjs/internal/Subject";
import { HttpClient } from '@angular/common/http';
import {ProductOrders} from "../model/product-orders.model";
import { ProductOrder } from '../model/product-order.model';

@Injectable({
        providedIn: 'root'
})
export class EcommerceService {

  private productsUrl = "/api/products";
  private ordersUrl = "/api/orders";
  //we are ignoring the issue with the type of productOrder here
  //@ts-ignore
  private productOrder: ProductOrder;
  private orders: ProductOrders = new ProductOrders();

  // Observable sources, a subject is a type of observable
  // the subject is a source of events that can be observed by other components
  // the subject is created using the new Subject() constructor in the subject.ts file
  private productOrderSubject= new Subject();
  private ordersSubject = new Subject();
  private totalSubject = new Subject();

  /**
   * Defines three observables that can be subscribed to for changes.
   * - `ProductOrderChanged`: Observable for changes in product orders.
   * - `OrdersChanged`: Observable for changes in orders.
   * - `TotalChanged`: Observable for changes in total.
   */
  ProductOrderChanged = this.productOrderSubject.asObservable();
  OrdersChanged = this.ordersSubject.asObservable();
  TotalChanged = this.totalSubject.asObservable();

  private total: number = 0;


  //the http client is injected into the constructor
  constructor(private http: HttpClient) {

  }

  /**
   * Retrieves the list of products from the server.
   *
   * @return {Observable<any>} An Observable of the HTTP GET request.
   */
  getAllProducts() {
    return this.http.get(this.productsUrl);
  }

  /**
   * A method to make a POST request to the orders URL with the provided ProductOrders object.
   *
   * @param {ProductOrders} order - The ProductOrders object to be sent in the POST request
   * @return {Observable<any>} An Observable of the HTTP POST request
   */
  saveOrder(order: ProductOrders) {
    return this.http.post(this.ordersUrl, order);
  }

  /**
   * Returns the currently selected product order.
   *
   * @return {ProductOrder} The currently selected product order.
   */
  get SelectedProductOrder() {
      return this.productOrder;
  }

  set SelectedProductOrder(value: ProductOrder) {
    this.productOrder = value;
    this.productOrderSubject.next(value); // Trigger an update in the subscribers
  }

  /**
   * Sets the value of ProductOrders and triggers an update in the subscribers.
   *
   * @param {ProductOrders} value - The ProductOrders object to set
   */
  set ProductOrders(value: ProductOrders) {
    this.orders = value;
    this.ordersSubject.next(value); // Trigger an update in the subscribers
  }

  get Total() {
    return this.total;
  }

  set Total(value: number) {
    this.total = value;
    this.totalSubject.next(value); // Trigger an update in the subscribers
  }
}
