import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './modules/home/home.component';
import { LoginComponent } from './modules/login/login.component';
import { RegisterComponent } from './modules/register/register.component';
import { UserPageComponent } from './modules/user-page/user-page.component';
import { AdminPageComponent } from './modules/admin-page/admin-page.component';
import { MovieCompComponent } from './modules/movie-comp/movie-comp.component';

const routes: Routes = [
  {path:"home", component:HomeComponent},
  {path:"", redirectTo:"home", pathMatch:"full"},
  {path:"login", component:LoginComponent},
  {path:"register", component:RegisterComponent},
  //{path:"user", component:UserPageComponent},
  {path:"user/:userId", component:UserPageComponent},
  {path:"admin", component:AdminPageComponent},
  {path:"movie/:userId/:movieId", component:MovieCompComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
