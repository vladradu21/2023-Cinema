import { Injectable } from "@angular/core";
import {HttpClient} from '@angular/common/http';
import { Movie } from "../model/Movie";


@Injectable({
  providedIn: "root"
})
export class MovieService {

  baseUrl: string = "http://localhost:8081/movie";

  constructor(private httpClient:HttpClient) {
  }

  addMovie(movie: Movie) {
    return this.httpClient.post(this.baseUrl + "/addMovie", movie);
  }

  addMovies(movies: Movie[]) {
    return this.httpClient.post(this.baseUrl + "/addMovies", movies);
  }

  getAllMovies() {
    return this.httpClient.get<Movie[]>(this.baseUrl + "/allMovies");
  }

  getMovieById(id: number) {
    return this.httpClient.get<Movie>(this.baseUrl + "/byId/" + id);
  }

  getMoviesByGenre(genre: string) {
    return this.httpClient.get<Movie[]>(this.baseUrl + "/byGenre/" + genre);
  }

  getMoviesByScore(score: number) {
    return this.httpClient.get<Movie[]>(this.baseUrl + "/byScore/" + score);
  }

  getMoviesByYear(year: number) {
    return this.httpClient.get<Movie[]>(this.baseUrl + "/byYear/" + year);
  }

  updateMovie(movie: Movie) {
    return this.httpClient.put(this.baseUrl + "/updateMovie", movie);
  }

  deleteMovie(id: number) {
    return this.httpClient.delete(this.baseUrl + "/deleteMovie/" + id);
  }

  sortMoviesByTitle(order: string) {
    return this.httpClient.get<Movie[]>(this.baseUrl + "/sortMoviesByTitle/" + order);
  }

  sortMoviesByScore(order: string) {
    return this.httpClient.get<Movie[]>(this.baseUrl + "/sortMoviesByScore/" + order);
  }

  sortMoviesByPopularity() {
    return this.httpClient.get<Movie[]>(this.baseUrl + "/sortMoviesByPopularity");
  }

}
