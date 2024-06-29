import { Component, OnInit } from '@angular/core';
import {ProductOrders} from "../model/product-orders.model";
import {Subscription} from "rxjs/internal/Subscription";
import {EcommerceService} from "../services/ecommerce-service.service";
import { CommonModule } from "@angular/common";

@Component({
  selector: 'app-orders',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './orders.component.html',
  styleUrl: './orders.component.css'
})
export class OrdersComponent implements OnInit {

  orders : ProductOrders;
  //@ts-ignore
  total : number;
  //@ts-ignore
  paid : boolean;
  //@ts-ignore
  sub : Subscription;

  constructor(private ecommerceService: EcommerceService) {
    this.orders = ecommerceService.ProductOrders;
  }

  ngOnInit() {
    this.paid = false;
    this.sub = this.ecommerceService.OrdersChanged.subscribe(
      () => {
        this.orders = this.ecommerceService.ProductOrders;
      });
    this.loadTotal();
  }

  pay() {
    this.paid = true;
    this.ecommerceService.saveOrder(this.orders).subscribe();
  }

  loadTotal() {
    this.sub = this.ecommerceService.TotalChanged.subscribe(() => {
      this.total = this.ecommerceService.Total;
    });
  }
}


