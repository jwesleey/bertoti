import { CommonModule} from '@angular/common';
import { Component, TemplateRef, ViewChild } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';



@Component({
  selector: 'app-home',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {
  movies = [
    { src: '/assets/images/im_still_here.jpg', alt: 'Poster do filme I\'m Still Here', title: 'I\'m Still Here', description: 'Um drama emocionante sobre resiliência.', year: '2025' },
    { src: '/assets/images/Anora.jpg', alt: 'Poster do filme Anora', title: 'Anora', description: 'Uma história de amor e ambição.', year: '2025' },
    { src: '/assets/images/duna.jpg', alt: 'Poster do filme Duna', title: 'Duna', description: 'Uma épica de ficção científica no deserto.', year: '2025' },
    { src: '/assets/images/emilia.jpg', alt: 'Poster do filme Emilia', title: 'Emilia Perez', description: 'Um conto histórico poderoso.', year: '2025' },
    { src: '/assets/images/substance.jpg', alt: 'Poster do filme The Substance', title: 'The Substance', description: 'Um thriller psicológico intenso.', year: '2025' },
    { src: '/assets/images/The_Brutalist.png', alt: 'Poster do filme The Brutalist', title: 'The Brutalist', description: 'Uma obra-prima arquitetônica.', year: '2025' },
    { src: '/assets/images/onesheet.jpg', alt: 'Poster do filme One Sheet', title: 'One Sheet', description: 'Uma comédia leve e divertida.', year: '2025' },
    { src: '/assets/images/conclave.jpg', alt: 'Poster do filme Conclave', title: 'Conclave', description: 'Intriga política no Vaticano.', year: '2025' },
    { src: '/assets/images/acompleteunknow.jpg', alt: 'Poster do filme A Complete Unknown', title: 'A Complete Unknown', description: 'A jornada de um ícone musical.', year: '2025' },
    { src: '/assets/images/Nickel_Boys.jpg', alt: 'Poster do filme Nickel Boys', title: 'Nickel Boys', description: 'Um drama sobre injustiça.', year: '2025' }
  ];


  showPopup = false;
  showPopupVote = false;
  selectedMovie: any = null;
  selectedMovieV: any = null;
  email!: string;
  critica!: string;




  openPopup(movie: any) {
    this.selectedMovie = movie;
    this.selectedMovieV = movie;
    this.showPopup = true;
  }

  closePopup() {
    this.showPopup = false;
    this.showPopupVote = false;
    this.selectedMovie = null;
    this.selectedMovieV = null;
  }

  selectedMovieVoted(id: any) {
    this.selectedMovieV = id;
  }

  voto() {
    this.showPopupVote = true;

    /*if (this.email.trim() === "" || this.critica.trim() === "") {
      this.mostrarSweetAlertErro();
    } else {
      this.mostrarSweetAlert();
    }*/
  }

  votar() {
    this.showPopupVote = true;
  }

  /*mostrarSweetAlert() {
    Swal.fire({
      title: 'Operação realizada!',
      text: 'Você completou a ação com sucesso.',
      icon: 'success',
      confirmButtonText: 'Ok',
    });
  }

  mostrarSweetAlertErro() {
    Swal.fire({
      title: 'Erro!',
      text: 'E-mail e Crítica são campos obrigatórios.',
      icon: 'error',
      confirmButtonText: 'Ok',
    });
  }*/

    constructor(private snackBar: MatSnackBar) {}

    openGlassSnackbar() {
      this.snackBar.open(' Notificação com Glassmorphism!', 'Fechar', {
        duration: 4000,
        horizontalPosition: 'right', // 'start' | 'center' | 'end' | 'left' | 'right'
        verticalPosition: 'bottom', // 'top' | 'bottom'
        panelClass: ['glass-snackbar'] // Aplica o estilo personalizado
      });
    }
}