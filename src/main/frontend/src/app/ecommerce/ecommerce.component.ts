import {Component, OnInit, ViewChild} from '@angular/core';
import {ProductsComponent} from "./products/products.component";
import {ShoppingCartComponent} from "./shopping-cart/shopping-cart.component";
import {OrdersComponent} from "./orders/orders.component";
import {NgClass} from "@angular/common";

@Component({
        selector: 'app-ecommerce',
        standalone: true,
  imports: [ProductsComponent, ShoppingCartComponent, OrdersComponent, NgClass],
        templateUrl: './ecommerce.component.html',
        styleUrls: ['./ecommerce.component.css']
})
export class EcommerceComponent implements OnInit { //OnInit is an interface that needs to be implemented
  // so that the component can be initialized, OnInit handles the initialization of the component when it is created and initialized in the browser meaning that the component is ready to be used

    protected collapsed = true;
        orderFinished = false;

        @ViewChild(ProductsComponent)
        // @ts-ignore
        productsC: ProductsComponent;

        @ViewChild(ShoppingCartComponent)
        // @ts-ignore
        shoppingCartC: ShoppingCartComponent;

        @ViewChild(OrdersComponent)
        //@ts-ignore
        ordersC: OrdersComponent;

        constructor() {
        }

        ngOnInit() {
        }


        toggleCollapsed(): void {
                this.collapsed = !this.collapsed;
        }

        finishOrder(orderFinished: boolean) {
                this.orderFinished = orderFinished;
        }

        reset() {
                this.orderFinished = false;
                this.productsC.reset();
                this.shoppingCartC.reset();
                this.ordersC.paid = false;
        }

}
