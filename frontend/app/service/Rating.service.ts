import { Injectable } from "@angular/core";
import {HttpClient} from '@angular/common/http';
import { Rating } from "../model/Rating";

@Injectable({
  providedIn: "root"
})

export class RatingService {

  baseUrl: string = "http://localhost:8081/rating";

  constructor(private httpClient:HttpClient) {
  }

  addRatingUserIdMovieId(rating: Rating, userId: number, movieId: number) {
    return this.httpClient.post(this.baseUrl + "/addRating/" + userId + "/" + movieId, rating);
  }

  getAllRatings() {
    return this.httpClient.get<Rating[]>(this.baseUrl + "/findAllRatings");
  }

  getRatingById(id: number) {
    return this.httpClient.get<Rating>(this.baseUrl + "/byId/" + id);
  }

  getRatingByScore(score: number) {
    return this.httpClient.get<Rating[]>(this.baseUrl + "/byScore/" + score);
  }

  updateRating(rating: Rating) {
    return this.httpClient.put(this.baseUrl + "/updateRating", rating);
  }

  deleteRating(id: number) {
    return this.httpClient.delete(this.baseUrl + "/deleteRating/" + id);
  }
}
