import { Component } from '@angular/core';
import { Footer } from './components/layout/footer/footer';
import { Header } from "./components/layout/header/header";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [Footer, Header],
  templateUrl: './app.html',
  styleUrl: './app.scss'
})
export class App {
}
