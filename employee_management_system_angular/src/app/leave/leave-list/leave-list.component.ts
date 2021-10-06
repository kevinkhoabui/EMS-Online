import { LeaveService } from "../services/leave.service";
import { Leave } from "../services/leave";
import { Component, OnInit } from "@angular/core";
import { Router, ActivatedRoute } from '@angular/router';
import { Observable } from "rxjs/internal/Observable";
import { TokenStorageService } from '../../services/token-storage.service';

@Component({
  selector: "app-leave-list",
  templateUrl: "./leave-list.component.html",
  styleUrls: ["./leave-list.component.css"]
})
export class LeaveListComponent implements OnInit {
  leaves: Observable<Leave[]>;
  user_level_id = window.sessionStorage.user_level_id;
  user_id = window.sessionStorage.user_id;
  total_leaves = 0;

  constructor(
    private leaveService: LeaveService,
    private tokenStorageService: TokenStorageService,
    private route: ActivatedRoute,
    private router: Router) {}

  ngOnInit() {
    const id = this.route.snapshot.paramMap.get('id');
    this.leaveService.getLeavesCount(this.user_id).subscribe(
      data => {
        console.log(data);
        this.total_leaves = data;
      },
      err => {
        console.log(err);
      }
    );
    if(id) {
      this.reloadData(id);
    } else {
      this.reloadData(0);
    }
    
  }

  reloadData(id) {
    this.leaves = this.leaveService.getLeavesList(id);
  }

  deleteLeave(id: number) {
    this.leaveService.deleteLeave(id)
      .subscribe(
        data => {
          console.log(data);
          this.reloadData(0);
        },
        error => console.log(error));
  }
}
