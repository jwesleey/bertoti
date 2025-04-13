import { CommonModule } from '@angular/common';
import { Component, HostListener} from '@angular/core';
import { FormsModule } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { VotesService } from '../../services/votes.service';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [CommonModule, 
  FormsModule,
  ],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})

export class HomeComponent {
  movies = [
    {
      src: '/assets/images/im_still_here.jpg',
      alt: 'Poster do filme Ainda Estou Aqui',
      title: 'Ainda Estou Aqui',
      description: 'No início da década de 1970, o Brasil enfrenta o endurecimento da ditadura militar. No Rio de Janeiro, a família Paiva - Rubens, Eunice e seus cinco filhos - vive à beira da praia em uma casa de portas abertas para os amigos. Um dia, Rubens Paiva é levado por militares à paisana e desaparece.',
      year: '2024'
    },
    {
      src: '/assets/images/Anora.jpg',
      alt: 'Poster do filme Anora',
      title: 'Anora',
      description: 'Anora, uma jovem stripper do Brooklyn, conhece o filho de um oligarca russo na boate em que trabalha. Os dois engatam um improvável romance e Anora vive uma história de Cinderela contemporânea por alguns dias. Em Las Vegas, o casal resolve consumar o relacionamento intempestivo e se casa de forma impulsiva. Quando a notícia do casamento chega à Rússia, o conto de fadas é rapidamente ameaçado: os pais do jovem partem para Nova York com a irredutível intenção de anular o matrimônio.',
      year: '2024'
    },
    {
      src: '/assets/images/duna.jpg',
      alt: 'Poster do filme Duna',
      title: 'Dune: Part II',
      description: 'Paul Atreides se une a Chani e aos Fremen enquanto busca vingança contra os conspiradores que destruíram sua família. Enfrentando uma escolha entre o amor de sua vida e o destino do universo, ele deve evitar um futuro terrível que só ele pode prever.',
      year: '2024'
    },
    {
      src: '/assets/images/emilia.jpg',
      alt: 'Poster do filme Emilia',
      title: 'Emilia Pérez',
      description: 'Rita trabalha em um escritório de advocacia mais interessado em lavar dinheiro do que em servir a lei. Para sobreviver, ela ajuda um chefe do cartel a sair do negócio para que ela possa finalmente se tornar a mulher que sempre sonhou ser.',
      year: '2024'
    },
    {
      src: '/assets/images/substance.jpg',
      alt: 'Poster do filme The Substance',
      title: 'The Substance',
      description: 'Elisabeth Sparkle, renomada por um programa de aeróbica, enfrenta um golpe devastador quando seu chefe a demite. Em meio ao seu desespero, um laboratório lhe oferece uma substância que promete transformá-la em uma versão aprimorada.',
      year: '2024'
    },
    {
      src: '/assets/images/The_Brutalist.png',
      alt: 'Poster do filme The Brutalist',
      title: 'The Brutalist',
      description: 'Arquiteto visionário foge da Europa pós-Segunda Guerra e chega aos Estados Unidos para reconstruir sua vida, carreira e casamento. Sozinho em um novo país, ele se estabelece na Pensilvânia, onde um rico e proeminente industrial reconhece seu talento.',
      year: '2024'
    },
    {
      src: '/assets/images/wicked.jpeg',
      alt: 'Poster do filme Wicked',
      title: 'Wicked',
      description: 'Na Terra de Oz, uma jovem chamada Elphaba forma uma improvável amizade com uma estudante popular chamada Glinda. Após um encontro com o Mágico de Oz, o relacionamento delas logo chega a uma encruzilhada.',
      year: '2024'
    },
    {
      src: '/assets/images/conclave.jpg',
      alt: 'Poster do filme Conclave',
      title: 'Conclave',
      description: 'Com a morte do Papa, o cardeal Lawrence reúne um grupo de sacerdotes para eleger seu sucessor. Cercado por líderes do mundo todo nos corredores do Vaticano, ele descobre uma trilha de segredos profundos que podem abalar a própria fundação da Igreja.',
      year: '2024'
    },
    {
      src: '/assets/images/acompleteunknow.jpg',
      alt: 'Poster do filme A Complete Unknown',
      title: 'A Complete Unknown',
      description: 'O jovem Bob Dylan chega a Nova York com seu violão e talento revolucionário. Na cidade, os seus relacionamentos mais íntimos são formados durante sua ascensão à fama. Em sua jornada, ele fica inquieto com o movimento folk, fazendo uma escolha controversa que reverbera no mundo todo.',
      year: '2024'
    },
    {
      src: '/assets/images/Nickel_Boys.jpg',
      alt: 'Poster do filme Nickel Boys',
      title: 'Nickel Boys',
      description: 'Acompanha a amizade e a transformação de dois adolescentes negros em um reformatório juvenil.',
      year: '2024'
    }
  ];

  showPopup = false;
  showPopupVote = false;
  selectedMovie: any = null;
  selectedMovieV: any = null;
  email!: string;
  critica!: string;
  stickyHeader =false;

  @HostListener('window:scroll', [])
  onWindowScroll(){
    this.stickyHeader = window.scrollY > 1;
  }

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
  }

  votar() {
    this.selectedMovieV = this.selectedMovieV;
    this.showPopupVote = true;
  }

  openGlassSnackbar() {
    this.snackBar.open(' Notificação com Glassmorphism!', 'Fechar', {
      duration: 4000,
      horizontalPosition: 'right', // 'start' | 'center' | 'end' | 'left' | 'right'
      verticalPosition: 'bottom', // 'top' | 'bottom'
      panelClass: ['glass-snackbar'] // Aplica o estilo personalizado
    });
  }

  constructor(private snackBar: MatSnackBar, private votesService: VotesService) { }

  enviarVoto() {
    if (!this.email || !this.critica) {
      this.snackBar.open('Email e crítica são obrigatórios!', 'Fechar', {
        duration: 3000,
        horizontalPosition: 'right',
        verticalPosition: 'bottom',
        panelClass: ['glass-snackbar']
      });
      return;
    }

    const voto = {
      email_user: this.email,
      critic_voto: this.critica,
      name_mov: this.selectedMovieV.title
    };

    console.log('Dados do voto:', voto); 
  
    this.votesService.criarvoto(voto).subscribe({
      next: response => {
        this.snackBar.open('Voto enviado com sucesso', 'Fechar', {
          duration: 3000,
          horizontalPosition: 'right',
          verticalPosition: 'bottom',
          panelClass: ['glass-snackbar']
        });
        this.closePopup();
      },
      error: (error) => {
        this.snackBar.open('Erro ao enviar o voto. Tente novamente.', 'Fechar', {
          duration: 3000,
          horizontalPosition: 'right',
          verticalPosition: 'bottom',
          panelClass: ['glass-snackbar']
        });
      }
    });
  }
}
