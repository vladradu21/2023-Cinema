// register.component.ts
import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/service/User.service';
import { User } from 'src/app/model/User';
import { Router } from '@angular/router';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  registerForm: FormGroup = new FormGroup({});

  email = "";
  name = "";
  username = "";
  password = "";
  isAdmin: boolean = false;

  constructor(private userService : UserService,
              private router: Router,
              private formBuilder: FormBuilder) { }

  ngOnInit() {
    this.registerForm = this.formBuilder.group({
      email: ['', Validators.required],
      name: ['', Validators.required],
      username: ['', Validators.required],
      password: ['', Validators.required]
    });
  }

  registerNow() {

    this.email = this.registerForm.value.email;
    this.name = this.registerForm.value.name;
    this.username = this.registerForm.value.username;
    this.password = this.registerForm.value.password;

    var user: User = new User();
    user.email = this.email;
    user.name = this.name;
    user.username = this.username;
    user.password = this.password;
    user.admin = this.isAdmin;

    this.createAccount(user);
    this.router.navigate(['login']);
  }

  createAccount(user: User) {

    this.userService.addUser(user).subscribe(
      (data) => {
        console.log(data);
      }
    )
  }
}
