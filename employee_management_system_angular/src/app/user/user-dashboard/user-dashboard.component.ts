import { UserService } from "../services/user.service";
import { User } from "../services/user";
import { Component, OnInit } from "@angular/core";
import { Router } from '@angular/router';
import { Observable } from "rxjs/internal/Observable";
import { TokenStorageService } from '../../services/token-storage.service';


@Component({
  selector: "app-user-dashboard",
  templateUrl: "./user-dashboard.component.html",
  styleUrls: ["./user-dashboard.component.css"]
})
export class UserDashboardComponent implements OnInit {
  users: Observable<User[]>;
  user_level_id = window.sessionStorage.user_level_id;
  user_id = window.sessionStorage.user_id;

  constructor(
    private userService: UserService,
    private tokenStorageService: TokenStorageService,
    private router: Router) {}

  ngOnInit() {
   
  }

  logout(): void {
    this.tokenStorageService.signOut();
    this.router.navigate(['/']);
  }
}
