import { Component, OnInit, ViewChild } from '@angular/core';
import { MovieService } from 'src/app/service/Movie.service';
import { UserService } from 'src/app/service/User.service';
import { Chart } from 'chart.js/auto';
import { ActorService } from 'src/app/service/Actor.service';

declare global {
  interface Window {
    bootstrap: any;
  }
}

@Component({
  selector: 'app-admin-page',
  templateUrl: './admin-page.component.html',
  styleUrls: ['./admin-page.component.css']
})
export class AdminPageComponent implements OnInit{

  //chart
  names: any;
  ratingsCount: any;

  importMovies: any;
  selectedUser: any;
  users: any;

  @ViewChild('movieTextArea') movieTextArea: any;
  searchText: string = "";
  movies: any;
  selectedMovie: any; // = {id: 0, title: "", year: 0, actors: []};

  viewMovieModal:any;
  editMovieModal:any;
  deleteMovieModal:any;
  addMovieModal:any;
  generateXMLModal:any;
  deleteUserModal:any;

  constructor(private movieService: MovieService,
              private userService: UserService,
              private actorService: ActorService) { }

  ngOnInit() {
    this.getMovies();
    this.getUsers();

    this.viewMovieModal = new window.bootstrap.Modal(
      document.getElementById("viewMovieModal")
    );

    this.editMovieModal = new window.bootstrap.Modal(
      document.getElementById("editMovieModal")
    );

    this.deleteMovieModal = new window.bootstrap.Modal(
      document.getElementById("deleteMovieModal")
    );

    this.addMovieModal = new window.bootstrap.Modal(
      document.getElementById("addMovieModal")
    );

    this.generateXMLModal = new window.bootstrap.Modal(
      document.getElementById("generateXMLModal")
    );

    this.deleteUserModal = new window.bootstrap.Modal(
      document.getElementById("deleteUserModal")
    );
  }

  openViewMovieModal(id: number){
    this.viewMovieModal?.show();
    this.selectedMovie = this.getMovieById(id);
  }

  closeViewMovieModal(){
    this.viewMovieModal?.hide();
  }

  getTitle(): string {
    console.log(this.selectedMovie.title);
    return this.selectedMovie.title;
  }

  getActorsAsJson(): string {
    //console.log(JSON.stringify(this.selectedMovie.actors));
    return JSON.stringify(this.selectedMovie.actors);
  }

  getRatingAsJson(): string {
    //console.log(JSON.stringify(this.selectedMovie.ratings));
    return JSON.stringify(this.selectedMovie.ratings);
  }

  openEditMovieModal(id: number){
    this.editMovieModal?.show();
    this.selectedMovie = this.getMovieById(id);
  }

  closeEditMovieModal(){
    this.editMovieModal?.hide();
  }

  saveEditMovieModal(){
    this.editMovieModal?.hide();
    this.updateMovie(this.selectedMovie);
  }

  openDeleteMovieModal(id: number){
    this.deleteMovieModal?.show();
    this.selectedMovie = this.getMovieById(id);
  }

  closeDeleteMovieModal(){
    this.deleteMovieModal?.hide();
  }

  confirmDeleteMovie(){
    this.deleteMovie(this.selectedMovie.id);
    this.closeDeleteMovieModal();
  }

  openAddMovieModal(){
    this.addMovieModal?.show();
  }

  closeAddMovieModal(){
    this.addMovieModal?.hide();
  }

  confirmAddMovieModal(){
    this.addMovieModal?.hide();
    const movieData = this.movieTextArea.nativeElement.value;
    console.log(movieData);
    this.addMovie(JSON.parse(movieData));
  }

  openGenerateXMLModal(){
    this.generateXMLModal?.show();
  }

  closeGenerateXMLModal(){
    this.generateXMLModal?.hide();
  }

  confirmGenerateXMLModal(){
    this.generateXMLModal?.hide();
    this.actorService.exportActorsXML().subscribe(
      (data) => {
        console.log(data);
      }
    )
  }

  openDeleteUserModal(id: number){
    this.deleteUserModal?.show();
    this.selectedUser = this.getUserById(id);
  }

  closeDeleteUserModal(){
    this.deleteUserModal?.hide();
  }

  confirmDeleteUserModal(){
    this.deleteUser(this.selectedUser.id);
    this.closeDeleteUserModal();
  }

  addMovie(movie: any) {
    this.movieService.addMovie(movie).subscribe(
      (data) => {
        console.log(data);
        this.getMovies();
      },
      (error) => {
        console.log(error);
      }
    );
  }

  getMovies() {
    this.movieService.getAllMovies().subscribe(
      (data) => {
        this.movies = data;
        console.log(data);
      },
      (error) => {
        console.log(error);
      }
    );
  }

  getMovieById(id: number) {
    this.movieService.getMovieById(id).subscribe(
      (data) => {
        this.selectedMovie = data;
        console.log(data);
      },
      (error) => {
        console.log(error);
      }
    );
  }

  updateMovie(movie: any) {
    this.movieService.updateMovie(movie).subscribe(
      (data) => {
        console.log(data);
        this.getMovies();
      },
      (error) => {
        console.log(error);
      }
    );
  }

  deleteMovie(id: number) {
    this.movieService.deleteMovie(id).subscribe(
      (data) => {
        console.log(data);
        this.getMovies();
      },
      (error) => {
        console.log(error);
      }
    );
  }

  getUsers() {
    this.userService.getAllUsers().subscribe(
      (data) => {
        this.users = data;
        console.log(data);
        this.names = [];
        this.ratingsCount = [];

        for (let user of this.users) {
          if(!user.admin) {
            this.names.push(user.name);
            this.ratingsCount.push(user.ratings.length);
          }
        }

        this.createChart();
      },
      (error) => {
        console.log(error);
      }
    );
  }

  getUserById(id: number) {
    this.userService.getUserById(id).subscribe(
      (data) => {
        this.selectedUser = data;
        console.log(data);
      },
      (error) => {
        console.log(error);
      }
    );
  }

  deleteUser(id: number) {
    this.userService.deleteUser(id).subscribe(
      (data) => {
        console.log(data);
        this.getUsers();
      },
      (error) => {
        console.log(error);
      }
    );
  }

  createChart() {
    var barColors = ["teal","indigo","maroon","olive","aqua","silver","navy"];

    new Chart("myChart", {
      type: "bar",
      data: {
        labels: this.names,
        datasets: [{
          backgroundColor: barColors,
          data: this.ratingsCount
        }]
      }
    });
  }

}
