import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { ReactiveFormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './modules/home/home.component';
import { LoginComponent } from './modules/login/login.component';
import { RegisterComponent } from './modules/register/register.component';
import { MovieCompComponent } from './modules/movie-comp/movie-comp.component';
import { MovieService } from './service/Movie.service';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { NavbarComponent } from './modules/navbar/navbar.component';
import { UserPageComponent } from './modules/user-page/user-page.component';
import { AdminPageComponent } from './modules/admin-page/admin-page.component';
import { FooterComponent } from './modules/footer/footer.component';
import { Ng2SearchPipeModule } from 'ng2-search-filter';
import { NotificationComponent } from './modules/notification/notification.component';
import { JsonPipe } from '@angular/common';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    LoginComponent,
    RegisterComponent,
    MovieCompComponent,
    NavbarComponent,
    UserPageComponent,
    AdminPageComponent,
    FooterComponent,
    NotificationComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    Ng2SearchPipeModule,
    JsonPipe,
    ReactiveFormsModule

  ],
  providers: [MovieService],
  bootstrap: [AppComponent]
})
export class AppModule { }
