import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from 'src/app/service/User.service';
import { User } from 'src/app/model/User';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit{

  loginForm: FormGroup = new FormGroup({});

  username = '';
  password = '';
  users: User[] = [];

  searchedUser: any;
  searchEmail: string = "";
  sendEmailModal: any;

  constructor(private userService: UserService,
              private router: Router,
              private formBuilder: FormBuilder) { }

  ngOnInit() {
    this.getUsers();

    this.loginForm = this.formBuilder.group({
      username: ['', Validators.required],
      password: ['', Validators.required]
    });

    this.sendEmailModal = new window.bootstrap.Modal(
      document.getElementById("sendEmailModal")
    );
  }

  openSendEmailModal(){
    this.sendEmailModal?.show();
  }

  closeSendEmailModal(){
    this.sendEmailModal?.hide();
  }

  confirmSendEmailModal(){
    this.sendEmailModal?.hide();

    this.userService.getUserByEmail(this.searchEmail).subscribe(
      (data) => {
        this.searchedUser = data;
        console.log(this.searchedUser);

        this.sendEmail();
      }
    )

    this.userService.getUserByEmail(this.searchEmail).subscribe(
      (data) => {
        console.log(data);
      });
  }

  sendEmail() {
    if (!this.searchedUser) {
      console.log('No user found.');
      return;
    }

    const email = this.searchedUser.email;
    const subject = 'Username and Password';
    const body = `Here is your username and password:  ${this.searchedUser.username}  ${this.searchedUser.password}`;

    this.userService.sendEmail(email, subject, body).subscribe(
      (data) => {
        console.log(data);
      },
      (error) => {
        console.log(error);
      }
    );
  }

  getUsers() {
    this.userService.getAllUsers().subscribe(
      (data) => {
        this.users = data;
        console.log(data);
      },
      (error) => {
        console.log(error);
      }
    )
  }

  loginUser() {

    this.username = this.loginForm.value.username;
    this.password = this.loginForm.value.password;

    console.log("username: " + this.username);
    console.log("password: " + this.password);

    this.userService.loginUser(this.username, this.password).subscribe(
      (data) => {
        console.log(data);
        this.checkUser(data);
      },
      (error) => {
        console.log("User not found!");
      });
  }

  checkUser(user: User) {
    if(user.admin) {
      this.router.navigate(['admin']);
    } else {
      this.router.navigate(['user/' + user.id]);
    }
  }
}
