import { PizzaRequest } from './pizza-request';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Pizza } from './pizza';

@Injectable({
    providedIn: 'root'
})
export class PizzaService {

    private readonly url = 'http://localhost:8080/pizza';

    constructor(private readonly http: HttpClient) { }

    public get(): Observable<Pizza[]> {
        return this.http.get<Pizza[]>(this.url);
    }

    public send(pizzaRequest: PizzaRequest): Observable<Pizza> {
        return this.http.post<Pizza>(this.url, pizzaRequest);
    }
}
