import { Actor } from "./Actor";
import { Rating } from "./Rating";

export class Movie {
  id: number | undefined;
  title: string | undefined;
  actors: Actor[] | undefined;
  genres: string[] | undefined;
  year: number | undefined;
  ratings: Rating[] | undefined;
  score: number | undefined;
  popularity: number | undefined;
}
