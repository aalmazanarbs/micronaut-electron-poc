import { Component, OnInit } from '@angular/core';
// import { dialog } from 'electron'; // use dialog.showErrorBox is broken, fix me
import { Pizza } from './pizza';
import { PizzaService } from './pizza.service';

@Component({
    selector: 'app-root',
    templateUrl: './app.component.html',
    styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {

    public displayedColumns: string[] = ['id', 'name'];
    public pizzas: Pizza[];

    constructor(private readonly pizzaService: PizzaService) { }

    public ngOnInit(): void {
        this.pizzaService.get().subscribe((pizzas: Pizza[]) => this.pizzas = pizzas);
    }

    public addPizza(): void {
        this.pizzaService.send({ name: 'Buuu'}).subscribe( pizza => console.log(pizza), _ => console.error('Oops! We have failed'));
    }
}
