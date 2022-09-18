import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Home } from 'src/app/models/home';
import { HomeService } from 'src/app/service/home.service';

@Component({
  selector: 'app-admin-homes-table',
  templateUrl: './admin-homes-table.component.html',
  styleUrls: ['./admin-homes-table.component.css']
})
export class AdminHomesTableComponent implements OnInit {

  homes: Home[];
  displayedColumns: string[] = ["id", "name", "configure"];

  constructor(
    private router: Router,
    private homeService: HomeService
  ) { }

  ngOnInit() {
    this.getAll();
  }

  getAll() {
    this.homeService.getAll().subscribe(
      res => {
        this.homes = res;
      },
      () => {
        console.log("No homes to show.")
      }
    );
  }

  configure(element){
    this.homeService.setSelectedHome(element);
    this.router.navigate(['configure-home-tables']);
  }

}
