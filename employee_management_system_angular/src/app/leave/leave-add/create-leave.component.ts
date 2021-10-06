import { LeaveService } from '../services/leave.service';
import { EmployeeService } from '../../employee/services/employee.service';
import { Router, ActivatedRoute } from '@angular/router';
import { Leave } from '../services/leave';
import { Employee } from '../../employee/services/employee';
import { TokenStorageService } from '../../services/token-storage.service';
import { Observable } from "rxjs/internal/Observable";

import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-create-leave',
  templateUrl: './create-leave.component.html',
  styleUrls: ['./create-leave.component.css']
})
export class CreateLeaveComponent implements OnInit {

  leave: Leave = new Leave();
  employee: Employee = new Employee();
  submitted = false;
  isUpdate = false;
  user_level_id = window.sessionStorage.user_level_id;
  user_id = window.sessionStorage.user_id;
  user = this.tokenStorageService.getUser();
  total_leaves = 0;

  constructor(
    private employeeService: EmployeeService,
    private tokenStorageService: TokenStorageService,
    private leaveService: LeaveService,
    private route: ActivatedRoute,
    private router: Router,
  ) { }

  ngOnInit() {
    const id = this.route.snapshot.paramMap.get('id');
    

    console.log(this.total_leaves)
    
    if (id) {
      this.isUpdate = true;
      this.getLeave(id);
    } else {
      // Show Leave Counts
      this.leaveService.getLeavesCount(this.user_id).subscribe(
        data => {
          console.log(data);
          this.total_leaves = data;
        },
        err => {
          console.log(err);
        }
      );
      this.leave.leave_status = "";
      this.leave.leave_employee_id = "";
    }

    
    this.getEmployeeOption();
  }

  getLeave(id): void {
    this.leaveService.getLeave(id).subscribe(
      data => {
        console.log(data);
        this.user_id = data.leave_employee_id;
        // Show Leave Counts
        this.leaveService.getLeavesCount(this.user_id).subscribe(
          data => {
            console.log(data);
            this.total_leaves = data;
          },
          err => {
            console.log(err);
          }
        );
        this.leave = data;
      },
      err => {
        console.log(err);
      }
    );

  }
  
  getEmployeeOption(): void {
    this.employeeService.getEmployeesList().subscribe(
      data => {
        console.log(data);
        this.employee = data;
      },
      err => {
        console.log(err);
      }
    );
  }

  newLeave(): void {
    this.submitted = false;
    this.leave = new Leave();
  }

  save() {
    console.log(this.leave);
    let from_date = new Date(this.leave.leave_from_date); 
    let to_date = new Date(this.leave.leave_to_date); 
    let differnce_time = to_date.getTime() - from_date.getTime(); 
    let total_days = (differnce_time / (1000 * 3600 * 24))+1; //Diference in Days
    this.leave.leave_total_days = total_days
    if(this.user_level_id == "2") {
      this.leave.leave_employee_id = this.user.login_employee_id;
      this.leave.leave_status = "Pending";
    }
    this.leaveService.createLeave(this.leave).subscribe(
      data => {
        console.log(data);
        // this.isSuccessful = true;
        // this.isSignUpFailed = false;
        if(this.user_level_id == "2") {
          this.router.navigate(['/leaves/'+this.user.login_employee_id]);
        } else {
          this.router.navigate(['/leaves']);
        }
      },
      err => {
        // this.errorMessage = err.error.message;
        // this.isSignUpFailed = true;
      }
    );
  }

  onSubmit() {
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.updateLeave();
    } else {
      this.submitted = true;
      this.save();
    }
    
  }

  updateLeave(): void {
    console.log(this.leave);
    let from_date = new Date(this.leave.leave_from_date); 
    let to_date = new Date(this.leave.leave_to_date); 
    let differnce_time = to_date.getTime() - from_date.getTime(); 
    let total_days = (differnce_time / (1000 * 3600 * 24))+1; //Diference in Days
    this.leave.leave_total_days = total_days
    this.leaveService.updateLeave(this.leave.leave_id, this.leave).subscribe(
      data => {
        console.log(data);
        // this.isSuccessful = true;
        // this.isSignUpFailed = false;
        this.router.navigate(['/leaves']);
      },
      err => {
        // this.errorMessage = err.error.message;
        // this.isSignUpFailed = true;
      }
    );
  }
}
