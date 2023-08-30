import { User } from "./User";
import { Movie } from "./Movie";

export class Rating {
  id : number | undefined;
  title : string | undefined;
  score : number | undefined;
  description : string | undefined;
  movie : Movie | undefined;
  user: User | undefined;
}
