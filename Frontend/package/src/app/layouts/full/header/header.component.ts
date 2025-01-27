import {
  Component,
  Output,
  EventEmitter,
  Input,
  ViewEncapsulation,
} from '@angular/core';
import { TablerIconsModule } from 'angular-tabler-icons';
import { MaterialModule } from 'src/app/material.module';
import { Router, RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { NgScrollbarModule } from 'ngx-scrollbar';
import { AuthenticationService } from 'src/app/services/authentication.service';

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [RouterModule, CommonModule, NgScrollbarModule, TablerIconsModule, MaterialModule],
  templateUrl: './header.component.html',
  encapsulation: ViewEncapsulation.None,
})
export class HeaderComponent {
  @Input() showToggle = true;
  @Input() toggleChecked = false;
  @Output() toggleMobileNav = new EventEmitter<void>();
  @Output() toggleMobileFilterNav = new EventEmitter<void>();
  @Output() toggleCollapsed = new EventEmitter<void>();

  constructor(private authService: AuthenticationService, private router: Router){}


  logout() {
    this.authService.logout().subscribe(() => {
      // Handle successful logout:
      this.clearUserData();  // Call a function to clear user data
      this.router.navigate(['/authentication/login']);  // Redirect to login route (assuming 'router' is injected)
    }, (error) => {
      // Handle logout error
      console.error("Error logging out:", error);  // Example error handling
    });
  }

  private clearUserData() {

      // Remove user data from local storage, cookies, etc. (if applicable)
      localStorage.removeItem('currentUser');  // Example using localStorage
      // Add additional logic to remove data from other storage mechanisms (if used)

  }
}