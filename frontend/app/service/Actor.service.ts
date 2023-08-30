import { Injectable } from "@angular/core";
import {HttpClient} from '@angular/common/http';
import { Actor } from "../model/Actor";

@Injectable({
  providedIn: "root"
})

export class ActorService {

  baseUrl: string = "http://localhost:8081/actor";

  constructor(private httpClient:HttpClient) {
  }

  exportActorsXML() {
    return this.httpClient.get(this.baseUrl + "/exportActorsXML");
  }
}
