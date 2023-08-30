import { Rating } from './Rating';
import { Movie } from './Movie';

export class User {
  id: number | undefined;
  admin: boolean | undefined;
  name: string | undefined;
  email: string | undefined;
  username: string | undefined;
  password: string | undefined;
  ratings: Rating[] | undefined;
  favoriteMovies: Movie[] | undefined;
  watchList: Movie[] | undefined;
  lastLogin: Date | undefined;
}
