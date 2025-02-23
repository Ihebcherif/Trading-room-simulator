import { Component } from '@angular/core';
import {RegisterRequest} from "../../modules/register-request";
import {AuthenticationResponse} from "../../modules/authentication-response";
import {AuthenticationService} from "../../services/authentication.service";
import {Router} from "@angular/router";
import {VerificationRequest} from "../../modules/verification-request";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent {

  registerRequest:RegisterRequest = {};
  authResponse: AuthenticationResponse = {};
  message = '';
  otpCode = '';
  roles: string[] = ['CLIENT', 'INVESTOR', 'TRAINER'];
  constructor(private authService:AuthenticationService,
  private router:Router) {
  }

  registerUser() {
    this.message='';
this.authService.register(this.registerRequest)
  .subscribe({
    next: (response) => {
      if (response) {
        this.authResponse = response;
      } else {
        // inform the user
        this.message = 'Account created successfully\nYou will be redirected to the Login page in 3 seconds';
        setTimeout(() => {
          this.router.navigate(['login']);
        }, 3000)
      }
    }
  });
  }


  verifyTfa() {
    this.message = '';
    const verifyRequest: VerificationRequest = {
      username: this.registerRequest.username,
      code: this.otpCode
    };
    this.authService.verifyCode(verifyRequest)
      .subscribe({
        next: (response) => {
          this.message = 'Account created successfully\nYou will be redirected to the Welcome page in 3 seconds';
          setTimeout(() => {
            localStorage.setItem('token', response.accessToken as string);
            this.router.navigate(['welcome']);
          }, 3000);
        }
      });
  }
}
