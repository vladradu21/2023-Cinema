import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { User } from 'src/app/model/User';
import { MovieService } from 'src/app/service/Movie.service';
import { UserService } from 'src/app/service/User.service';

@Component({
  selector: 'app-user-page',
  templateUrl: './user-page.component.html',
  styleUrls: ['./user-page.component.css']
})
export class UserPageComponent implements OnInit {

  selectedSortOption: string = "0";
  movies: any;
  user: any;

  constructor(private movieService: MovieService,
              private userService: UserService,
              private route: ActivatedRoute) { }

  ngOnInit() {
    this.getMovies();
    this.getUserById(this.route.snapshot.params['userId']);
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

  sortMovies() {
    switch (this.selectedSortOption) {
      case "1":
        console.log("Sort By Title: Asc");
        this.movieService.sortMoviesByTitle("asc").subscribe(
          (data) => {
            this.movies = data;
            console.log(data);
          }
        )
        break;
      case "2":
        console.log("Sort By Title: Desc");
        this.movieService.sortMoviesByTitle("desc").subscribe(
          (data) => {
            this.movies = data;
            console.log(data);
          }
        )
        break;
      case "3":
        console.log("Sort By Score: Asc");
        this.movieService.sortMoviesByScore("asc").subscribe(
          (data) => {
            this.movies = data;
            console.log(data);
          }
        )
        break;
      case "4":
        console.log("Sort By Score: Desc");
        this.movieService.sortMoviesByScore("desc").subscribe(
          (data) => {
            this.movies = data;
            console.log(data);
          }
        )
        break;
      default:
        this.getMovies();
    }
  }

  checkIfFavoriteMovie(id: number) {
    var isFavorite: boolean = false;
    if(this.user != null && this.user.favoriteMovies != null) {
      for(let movie of this.user.favoriteMovies) {
        if(movie.id == id) {
          this.removeFromFavorites(id);
          isFavorite = true;
        }
      }
      if(!isFavorite) {
        this.addToFavorites(id);
      }
    }
  }

  isFavorite(id: number) {
    if(this.user != null && this.user.favoriteMovies != null) {
      for(let movie of this.user.favoriteMovies) {
        if(movie.id == id) {
          return true;
        }
      }
    }

    return false;
  }

  addToFavorites(movieId: number) {
    console.log("Add to favorites " + movieId);

    if(this.user.id != null)
    this.userService.addMovieToFavoriteList(this.user.id, movieId).subscribe(
      (data) => {
        this.user = data;
      }
    )
  }

  removeFromFavorites(movieId: number) {
    console.log("Remove from favorites " + movieId);

    if(this.user.id != null)
    this.userService.removeMovieFromFavoriteList(this.user.id, movieId).subscribe(
      (data) => {
        this.user = data;
      }
    );
  }

  seeFavoriteMovies() {
    console.log("See favorite movies");
    this.movies = this.user.favoriteMovies;
  }

  checkToWatchdMovie(id: number) {
    var isWatched: boolean = false;
    if(this.user != null && this.user.watchList != null) {
      for(let movie of this.user.watchList) {
        if(movie.id == id) {
          this.removeFromToWatch(id);
          isWatched = true;
        }
      }
      if(!isWatched) {
        this.addToWatch(id);
      }
    }
  }

  isWatched(id: number) {
    if(this.user != null && this.user.watchList != null) {
      for(let movie of this?.user?.watchList) {
        if(movie.id == id) {
          return true;
        }
      }
    }

    return false;
  }

  addToWatch(movieId: number) {
    console.log("Add to watched " + movieId);

    if(this.user.id != null)
    this.userService.addMoiveToWatchList(this.user.id, movieId).subscribe(
      (data) => {
        this.user = data;
      }
    )
  }

  removeFromToWatch(movieId: number) {
    console.log("Remove from watched " + movieId);

    if(this.user.id != null)
    this.userService.removeMovieFromWatchList(this.user.id, movieId).subscribe(
      (data) => {
        this.user = data;
      }
    );
  }

  seeToWatchMovies() {
    console.log("To watch movies");
    this.movies = this.user.watchList;
  }

  seePopularMovies() {
    console.log("Popular movies");
    this.movieService.sortMoviesByPopularity().subscribe(
      (data) => {
        this.movies = data;
        console.log(data);
      }
    )
  }
}
