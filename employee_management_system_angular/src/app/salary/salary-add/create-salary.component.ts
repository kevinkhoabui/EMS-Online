import { SalaryService } from '../services/salary.service';
import { MonthService } from '../../month/services/month.service';
import { DepartmentService } from '../../department/services/department.service';
import { CountryService } from '../../country/services/country.service';
import { EmployeeService } from '../../employee/services/employee.service';
import { Router, ActivatedRoute } from '@angular/router';


import { Salary } from '../services/salary';
import { Month } from '../../month/services/month';
import { Country } from '../../country/services/country';
import { Department } from '../../department/services/department';
import { Employee } from '../../employee/services/employee';


import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-create-salary',
  templateUrl: './create-salary.component.html',
  styleUrls: ['./create-salary.component.css']
})
export class CreateSalaryComponent implements OnInit {
  

  salary: Salary = new Salary();
  month: Month = new Month();
  country: Country = new Country();
  department: Department = new Department();
  employee: Employee = new Employee();
  submitted = false;
  isUpdate = false;
  formData = new FormData();
  file: File;
  

  constructor(
    private monthService: MonthService,
    private countryService: CountryService,
    private departmentService: DepartmentService,
    private employeeService: EmployeeService,
    private salaryService: SalaryService,
    private route: ActivatedRoute,
    private router: Router,
  ) { 
    console.log("I am here");

  }

  ngOnInit() {
    const id = this.route.snapshot.paramMap.get('id');
    console.log("I am here salary");
    if (id) {
      this.isUpdate = true;
      this.getSalary(id);
    } else {
      this.salary.salary_month = "0";
      this.salary.salary_employee_id = "0";
    }
    this.getMonthOption();
    this.getEmployeeOption();
  }

  getSalary(id): void {
    this.salaryService.getSalary(id).subscribe(
      data => {
        console.log(data);
        this.salary = data;
      },
      err => {
        console.log(err);
      }
    );

  }
  
  getMonthOption(): void {
    this.monthService.getMonthsList().subscribe(
      data => {
        console.log(data);
        this.month = data;
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

  newSalary(): void {
    this.submitted = false;
    this.salary = new Salary();
  }

  // File Upload Logic
  fileChange(event) {
    console.log("Inside file upload")
    let fileList: FileList = event.target.files;
    if(fileList.length > 0) {
        this.file = fileList[0];
        this.formData.append('salary_slip', this.file, this.file.name);
    }
}

  save() {    
    // Put salary form in FormData
    for (let key in this.salary) {
      console.log("Insie Iterator")
      this.formData.append(key, this.salary[key]);
    }
   
    this.salaryService.createSalary(this.formData).subscribe(
      data => {
        console.log(data);
        // this.isSuccessful = true;
        // this.isSignUpFailed = false;
        this.router.navigate(['/salary']);
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
      this.updateSalary();
    } else {
      this.submitted = true;
      this.save();
    }
    
  }

  updateSalary(): void {
    
    // Put salary form in FormData ///
    for (let key in this.salary) {
      console.log("Insie Iterator")
      this.formData.append(key, this.salary[key]);
    }
    if(this.file) {
      // End of Request ///
      this.salaryService.updateUploadSalary(this.salary.salary_id, this.formData).subscribe(
        data => {
          console.log(data);
          // this.isSuccessful = true;
          // this.isSignUpFailed = false;
          this.router.navigate(['/salary']);
        },
        err => {
          // this.errorMessage = err.error.message;
          // this.isSignUpFailed = true;
        }
      );
    } else {
      // End of Request ///
      this.salaryService.updateSalary(this.salary.salary_id, this.salary).subscribe(
        data => {
          console.log(data);
          // this.isSuccessful = true;
          // this.isSignUpFailed = false;
          this.router.navigate(['/salary']);
        },
        err => {
          // this.errorMessage = err.error.message;
          // this.isSignUpFailed = true;
        }
      );
    }
    
  }
}
