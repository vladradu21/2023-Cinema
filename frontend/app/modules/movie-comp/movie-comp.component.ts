import { Component, OnInit } from '@angular/core';
import { MovieService } from 'src/app/service/Movie.service';
import { ActivatedRoute } from '@angular/router';
import { UserService } from 'src/app/service/User.service';
import { RatingService } from 'src/app/service/Rating.service';

@Component({
  selector: 'app-movie-comp',
  templateUrl: './movie-comp.component.html',
  styleUrls: ['./movie-comp.component.css']
})

export class MovieCompComponent implements OnInit {

  movie: any;
  user: any;
  newRating: any = {};

  addRatingModal: any;
  deleteRatingModal: any;
  deleteRatingId: any;

  constructor(private movieService: MovieService,
              private route: ActivatedRoute,
              private userService: UserService,
              private ratingService: RatingService ) { }

  ngOnInit() {
    this.getMovieById(this.route.snapshot.params['movieId']);
    this.getUserById(this.route.snapshot.params['userId']);

    this.addRatingModal = new window.bootstrap.Modal(
      document.getElementById("addRatingModal")
    );

    this.deleteRatingModal = new window.bootstrap.Modal(
      document.getElementById("deleteRatingModal")
    );
  }

  openAddRatingModal(){
    this.addRatingModal?.show();
  }

  closeAddRatingModal(){
    this.addRatingModal?.hide();
  }

  confirmAddRatingModal(){

    var userId = this.route.snapshot.params['userId'];
    var movieId = this.route.snapshot.params['movieId'];
    this.ratingService.addRatingUserIdMovieId(this.newRating, userId, movieId).subscribe(
      (data) => {
        console.log(data);
      },
      (error) => {
        console.log(error);
      }
    );
    this.addRatingModal?.hide();
  }

  getMovieById(id: number) {
    this.movieService.getMovieById(id).subscribe(
      (data) => {
        this.movie = data;
        console.log(data);
      },
      (error) => {
        console.log(error);
      }
    );
  }

  getUserById(id: number) {
    this.userService.getUserById(id).subscribe(
      (data) => {
        this.user = data;
        console.log(data);
      },
      (error) => {
        console.log(error);
      }
    );
  }

  openDeleteRatingModal(id: number){
    this.deleteRatingId = id;
    this.deleteRatingModal?.show();
  }

  closeDeleteRatingModal(){
    this.deleteRatingModal?.hide();
  }

  confirmDeleteRatingModal(){
    this.ratingService.deleteRating(this.deleteRatingId).subscribe(
      (data) => {
        console.log(data);
      }
    )
    this.deleteRatingModal?.hide();
  }
}
