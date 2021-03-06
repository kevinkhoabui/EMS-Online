import { CreateEmployeeComponent } from './employee/employee-add/create-employee.component';
import { CreateLeaveComponent } from './leave/leave-add/create-leave.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { EmployeeListComponent } from './employee/employee-list/employee-list.component';
import { LeaveListComponent } from './leave/leave-list/leave-list.component';
import { CreateSalaryComponent } from './salary/salary-add/create-salary.component';
import { SalaryListComponent } from './salary/salary-list/salary-list.component';
import { UserLogin } from './user/user-login/user-login.component';
import { LoginLayoutComponent } from './layouts/login-layout/login-layout.component';
import { PageLayoutComponent } from './layouts/page-layout/page-layout.component';
import { UserDashboardComponent } from './user/user-dashboard/user-dashboard.component';
import { UserPasswordComponent } from './user/user-password/user-password.component';



const routes: Routes = [
  // Site routes goes here 
  { 
    path: '', 
    component: LoginLayoutComponent ,
    children: [
      { path: '', component: UserLogin },
    ]
  },
  { 
    path: '', 
    component: PageLayoutComponent ,
    children: [
      { path: '', redirectTo: 'employees', pathMatch: 'full' },
      { path: 'employees', component: EmployeeListComponent },
      { path: 'employee/add', component: CreateEmployeeComponent },
      { path: 'employee/update-employee/:id', component: CreateEmployeeComponent },
    
      { path: 'leaves', component: LeaveListComponent },
      { path: 'leave/add', component: CreateLeaveComponent },
      { path: 'leaves/:id', component: LeaveListComponent },
      { path: 'leave/update-leave/:id', component: CreateLeaveComponent },

      { path: 'user/dashboard', component: UserDashboardComponent },
      { path: 'user/password', component: UserPasswordComponent },
    
      { path: 'salary', component: SalaryListComponent },
      { path: 'salary/add', component: CreateSalaryComponent },
      { path: 'salary/:id', component: SalaryListComponent },
      { path: 'salary/update-salary/:id', component: CreateSalaryComponent },
      // { path: '**', redirectTo: '/employees', pathMatch: 'full' },
    ]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
