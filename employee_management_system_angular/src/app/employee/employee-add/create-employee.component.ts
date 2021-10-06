import { EmployeeService } from '../services/employee.service';
import { StateService } from '../../state/services/state.service';
import { DepartmentService } from '../../department/services/department.service';
import { CountryService } from '../../country/services/country.service';
import { SaluationService } from '../../saluation/services/saluation.service';
import { Router, ActivatedRoute } from '@angular/router';
import { TokenStorageService } from '../../services/token-storage.service';


import { Employee } from '../services/employee';
import { State } from '../../state/services/state';
import { Country } from '../../country/services/country';
import { Department } from '../../department/services/department';
import { Saluation } from '../../saluation/services/saluation';
import { Roles } from '../../roles/services/roles';


import { Component, OnInit } from '@angular/core';
import { Login } from '../services/login';
import { LoginService } from '../services/login.service';
import { RolesService } from 'src/app/roles/services/roles.service';

@Component({
  selector: 'app-create-employee',
  templateUrl: './create-employee.component.html',
  styleUrls: ['./create-employee.component.css']
})
export class CreateEmployeeComponent implements OnInit {

  employee: Employee = new Employee();
  loginfrm: Login = new Login();
  state: State = new State();
  country: Country = new Country();
  roles: Roles = new Roles();
  department: Department = new Department();
  saluation: Saluation = new Saluation();
  user_level_id = window.sessionStorage.user_level_id;
  submitted = false;
  isUpdate = false;
  msg = "";
  type= "danger";
  employee_id = "save"
  readonly = false;
  user = this.tokenStorageService.getUser();

  constructor(
    private stateService: StateService,
    private rolesService: RolesService,
    private tokenStorageService: TokenStorageService,
    private countryService: CountryService,
    private departmentService: DepartmentService,
    private saluationService: SaluationService,
    private employeeService: EmployeeService,
    private loginService: LoginService,
    private route: ActivatedRoute,
    private router: Router,
  ) { }

  ngOnInit() {
    const id = this.route.snapshot.paramMap.get('id');
    if(this.user_level_id == 2) {
      this.readonly = true;
    }
    
    if (id) {
      this.employee_id="";
      this.isUpdate = true;
      this.getEmployee(id);
    } else {
      this.loginfrm.login_level_id = "";
      this.employee.employee_department = "";
      this.employee.employee_sal = "";
      this.employee.employee_gender = "";
      this.employee.employee_state = "";
      this.employee.employee_country = "";
    }
    this.getStateOption();
    this.getRolesOption();
    this.getCountryOption();
    this.getSaluationOption();
    this.getDepartmentOption();
  }

  getEmployee(id): void {
    this.employeeService.getEmployee(id).subscribe(
      data => {
        console.log(data);
        this.employee = data;
      },
      err => {
        console.log(err);
      }
    );

  }
  
  getStateOption(): void {
    this.stateService.getStatesList().subscribe(
      data => {
        console.log(data);
        this.state = data;
      },
      err => {
        console.log(err);
      }
    );
  }
  getCountryOption(): void {
    this.countryService.getCountriesList().subscribe(
      data => {
        console.log(data);
        this.country = data;
      },
      err => {
        console.log(err);
      }
    );
  }
  getDepartmentOption(): void {
    this.departmentService.getDepartmentsList().subscribe(
      data => {
        console.log(data);
        this.department = data;
      },
      err => {
        console.log(err);
      }
    );
  }
  getSaluationOption(): void {
    this.saluationService.getSaluationsList().subscribe(
      data => {
        console.log(data);
        this.saluation = data;
      },
      err => {
        console.log(err);
      }
    );
  }

  getRolesOption(): void {
    this.rolesService.getRolesList().subscribe(
      data => {
        console.log(data);
        this.roles = data;
      },
      err => {
        console.log(err);
      }
    );
  }

  newEmployee(): void {
    this.submitted = false;
    this.employee = new Employee();
  }

  save() {
    // Check Login ID Exits
    this.loginService.checkUserNameExits(this.loginfrm.login_email).subscribe(
      (logindata:Login) => {
        if(typeof logindata[0] === 'object' && logindata[0].hasOwnProperty('login_email')) {
          console.log("Username Exits ")
          console.log(logindata);
          this.msg = "Error : Username already exits. Kindly choose another !!!"
        } else {
          // Saving Employee and Login Details
          this.employeeService.createEmployee(this.employee).subscribe(
            data => {
              this.loginfrm.login_employee_id = data['employee_id'];
              console.log("Login Form Data")
              console.log(this.loginfrm)
              console.log("Employee Saved Data")
              console.log(data);
              // Saving Login Information 
              this.loginService.createLogin(this.loginfrm).subscribe(
                logindata => {
                  console.log("Loging Saved Data")
                  console.log(logindata);
              });
              // this.isSuccessful = true;
              // this.isSignUpFailed = false;
              this.router.navigate(['/employees']);
            },
            err => {
              // this.errorMessage = err.error.message;
              // this.isSignUpFailed = true;
            }
          );
        }
    });
  }

  onSubmit() {
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.updateEmployee();
    } else {
      this.submitted = true;
      this.save();
    }
    
  }

  updateEmployee(): void {
    console.log(this.employee);
    this.employeeService.updateEmployee(this.employee.employee_id, this.employee).subscribe(
      data => {
        console.log(data);
        // this.isSuccessful = true;
        // this.isSignUpFailed = false;
        if(this.user_level_id == "2") {
          this.msg = "Success : Your Account Updated Successfully !!!";
          this.type = "success";
        } else {
          this.router.navigate(['/employees']);
        }
      },
      err => {
        // this.errorMessage = err.error.message;
        // this.isSignUpFailed = true;
      }
    );
  }
}
