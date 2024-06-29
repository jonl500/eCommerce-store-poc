// import { NgModule } from '@angular/core';
// import { CommonModule } from '@angular/common';
// import { BrowserModule } from '@angular/platform-browser';
// import { FormsModule, ReactiveFormsModule } from '@angular/forms';
// import { HttpClientModule } from '@angular/common/http';
//
// import { AppComponent } from './app.component';
// import { EcommerceComponent } from './ecommerce/ecommerce.component';
// import { ProductsComponent } from './ecommerce/products/products.component';
// import { ShoppingCartComponent } from './ecommerce/shopping-cart/shopping-cart.component';
// import { OrdersComponent } from './ecommerce/orders/orders.component';
// import { EcommerceService } from "./ecommerce/services/ecommerce-service.service";
//
// @NgModule({
//   declarations: [
//     AppComponent,
//     EcommerceComponent,
//     ProductsComponent,
//     ShoppingCartComponent,
//     OrdersComponent
//
//   ],
//   imports: [
//     CommonModule,
//     BrowserModule,
//     HttpClientModule,
//     FormsModule,
//     ReactiveFormsModule
//   ],
//   providers: [EcommerceService],
//   bootstrap: [AppComponent]
// })
// export class AppModule { }
// import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';

import {AppComponent} from './app.component';
import {EcommerceComponent} from './ecommerce/ecommerce.component';
import {ProductsComponent} from './ecommerce/products/products.component';
import {ShoppingCartComponent} from './ecommerce/shopping-cart/shopping-cart.component';
import {OrdersComponent} from './ecommerce/orders/orders.component';
import {EcommerceService} from "./ecommerce/services/ecommerce-service.service";
import {CommonModule} from "@angular/common";

@NgModule({
  declarations: [
    // AppComponent,
    // EcommerceComponent,
    // ProductsComponent,
    // ShoppingCartComponent,
    // OrdersComponent
  ],
  imports: [
    //BrowserModule,
    CommonModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [EcommerceService],
  // bootstrap: [AppComponent]
})
export class AppModule {
}
