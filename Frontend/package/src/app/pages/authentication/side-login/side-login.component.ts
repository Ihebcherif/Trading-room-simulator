import { Component } from '@angular/core';
import {
  FormGroup,
  FormControl,
  Validators,
  FormsModule,
  ReactiveFormsModule,
} from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { MaterialModule } from '../../../material.module';
import { MatButtonModule } from '@angular/material/button';
import { AuthenticationRequest } from 'src/app/modules/authentication-request';
import { AuthenticationResponse } from 'src/app/modules/authentication-response';
import { VerificationRequest } from 'src/app/modules/verification-request';
import { AuthenticationService } from 'src/app/services/authentication.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-side-login',
  standalone: true,
  imports: [
    RouterModule,
    MaterialModule,
    FormsModule,
    ReactiveFormsModule,
    MatButtonModule,CommonModule
  ],
  templateUrl: './side-login.component.html',
})
export class AppSideLoginComponent {
    constructor(
      private authService: AuthenticationService,
      private router: Router
    ) {
    }
    form = new FormGroup({
      username: new FormControl('', [Validators.required]),
      password: new FormControl('', [Validators.required]),
    });
  
    authRequest: AuthenticationRequest = {
      username: '', // Ensure username is set here
      password: ''   // Ensure password is set here
    };
    authResponse: AuthenticationResponse = {};
    otpCode = '';
    authenticate() {
      console.log(this.authRequest); // Check if authRequest has correct values
      this.authService.login(this.authRequest)
        .subscribe({
          next: (response) => {
            this.authResponse = response;
            if (!this.authResponse.mfaEnabled) {
              localStorage.setItem('token', response.accessToken as string);
              this.router.navigate(['dashboard']);
            }
          },
          error: (err) => {
            console.error('Authentication failed', err);
            alert('Login failed. Please check your credentials or try again later.');
          }
        });
    }
  
    verifyCode() {
      const verifyRequest: VerificationRequest = {
        username: this.authRequest.username,
        code: this.otpCode
      };
      this.authService.verifyCode(verifyRequest)
        .subscribe({
          next: (response) => {
            localStorage.setItem('token', response.accessToken as string);
            this.router.navigate(['welcome']);
          }
        });
    }
  get f() {
    return this.form.controls;
  }

  submit() {
    // console.log(this.form.value);
    this.router.navigate(['/']);
  }
}
