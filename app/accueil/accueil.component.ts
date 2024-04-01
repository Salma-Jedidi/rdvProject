import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-accueil',
  templateUrl: './accueil.component.html',
  styleUrls: ['./accueil.component.css']
})
export class AccueilComponent implements OnInit {

  constructor() { }
  @Input() scheduleType: string = '';
  @Input() iconClass: string = '';
  @Input() title: string = '';
  @Input() content: string = '';
  @Input() timeSlots: string[] = [];

  ngOnInit(): void {
  
  }
 
}
