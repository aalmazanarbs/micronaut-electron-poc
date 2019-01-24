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
        this.getPizzas();
    }

    public getPizzas(): void {
        this.pizzaService.get().subscribe((pizzas: Pizza[]) => this.pizzas = pizzas);
    }

    public addPizza(): void {
        this.pizzaService.send({ name: this.generateRandomPizzaName() })
                          .subscribe((pizza: Pizza) => {
                              this.pizzas.push(pizza);
                              this.pizzas = [...this.pizzas];
                          },
                          _ => console.error('Oops! We have failed'));
    }

    private generateRandomPizzaName(): string {
        const charset = 'abcdefghijklmnopqrstuvwxyz';
        const maxLength = Math.floor(Math.random() * 20) + 8;
        let pizza = '';

        for (let i = 0; i < maxLength; i++) {
            pizza += charset.charAt(Math.floor(Math.random() * charset.length));
        }

        return pizza;
    }
}
