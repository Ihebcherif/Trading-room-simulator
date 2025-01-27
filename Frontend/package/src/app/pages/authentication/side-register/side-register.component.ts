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
import { CommonModule } from '@angular/common';
import { AuthenticationResponse } from 'src/app/modules/authentication-response';
import { RegisterRequest } from 'src/app/modules/register-request';
import { VerificationRequest } from 'src/app/modules/verification-request';
import { AuthenticationService } from 'src/app/services/authentication.service';

@Component({
  selector: 'app-side-register',
  standalone: true,
  imports: [RouterModule, MaterialModule, FormsModule, ReactiveFormsModule,CommonModule],
  templateUrl: './side-register.component.html',
})
export class AppSideRegisterComponent {
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
    console.log(this.registerRequest); 
    
    this.authService.register(this.registerRequest).subscribe({
      next: (response) => {
        if (response) {
          this.authResponse = response;
        } else {
          this.message = 'Account created successfully. Redirecting...';
          setTimeout(() => this.router.navigate(['/authentication/login']), 3000);
        }
      },
      error: (error) => {
        console.error('Registration error:', error);
        this.message = 'Registration failed. Please check your inputs or try again later.';
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
  submit() {
    // console.log(this.form.value);
    this.router.navigate(['/']);
  }
}
