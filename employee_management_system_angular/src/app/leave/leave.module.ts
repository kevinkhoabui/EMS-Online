import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CreateLeaveComponent } from './leave-add/create-leave.component';
import { LeaveListComponent } from './leave-list/leave-list.component';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule, ReactiveFormsModule } from '@angular/forms'; // <== add the imports!
import {RouterModule} from '@angular/router';


@NgModule({
  declarations: [
    CreateLeaveComponent,
    LeaveListComponent
  ],
  imports: [
    CommonModule,
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    RouterModule
  ],
  bootstrap: [
    LeaveListComponent
  ]
})
export class LeaveModule { }
