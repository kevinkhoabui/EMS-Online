import { SalaryService } from "../services/salary.service";
import { Salary } from "../services/salary";
import { Component, OnInit } from "@angular/core";
import { Router, ActivatedRoute } from '@angular/router';
import { Observable } from "rxjs/internal/Observable";

@Component({
  selector: "app-salary-list",
  templateUrl: "./salary-list.component.html",
  styleUrls: ["./salary-list.component.css"]
})
export class SalaryListComponent implements OnInit {
  salaries: Observable<Salary[]>;
  user_level_id = window.sessionStorage.user_level_id;

  constructor(private salaryService: SalaryService,
    private route: ActivatedRoute,
    private router: Router) {}

  ngOnInit() {
    const id = this.route.snapshot.paramMap.get('id');
    if(id) {
      this.reloadData(id);
    } else {
      this.reloadData(0);
    }
  }

  reloadData(id) {
    this.salaries = this.salaryService.getSalariesList(id);
  }

  public openNewTab(location) {
    window.open(location, '_blank');
  }

  deleteSalary(id: number) {
    this.salaryService.deleteSalary(id)
      .subscribe(
        data => {
          console.log(data);
          this.reloadData(0);
        },
        error => console.log(error));
  }
}
