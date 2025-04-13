import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterOutlet } from '@angular/router';
import { MatSnackBarModule } from '@angular/material/snack-bar';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, FormsModule, MatSnackBarModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})

export class AppComponent  {
  title = 'myprojectWeb';
  message: string = '';
   url =  'http://localhost:8080/';

  constructor(private http: HttpClient){}

  /*ngOnInit() {
    this.http.get<string>('http://localhost:8080/api/test', { responseType: 'text' as 'json' })
    .subscribe((response) => this.message = response)
  }*/
}
