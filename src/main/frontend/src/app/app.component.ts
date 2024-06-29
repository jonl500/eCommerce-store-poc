import { Component } from '@angular/core';
import { EcommerceService } from "./ecommerce/services/ecommerce-service.service";
import {CommonModule} from "@angular/common";
import {RouterOutlet} from "@angular/router";
import {EcommerceComponent} from "./ecommerce/ecommerce.component";
import { bootstrapApplication} from "@angular/platform-browser";
import {platform} from "node:os";
import {platformBrowserDynamic} from "@angular/platform-browser-dynamic";
import {AppModule} from "./app.module";

@Component({
  selector: 'app-root',
   standalone: true,
  imports: [CommonModule, RouterOutlet, EcommerceComponent,AppModule],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  providers: [EcommerceService]
})
export class AppComponent {
  title = 'frontend';
}

