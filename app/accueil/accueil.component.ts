import { Component, ElementRef, Input, OnInit, ViewChild } from '@angular/core';

@Component({
  selector: 'app-accueil',
  templateUrl: './accueil.component.html',
  styleUrls: ['./accueil.component.css']
})

export class AccueilComponent implements OnInit {
  @ViewChild('commentSection') commentSection!: ElementRef;
  constructor() { }
  @Input() scheduleType: string = '';
  @Input() iconClass: string = '';
  @Input() title: string = '';
  @Input() content: string = '';
  @Input() timeSlots: string[] = [];

  ngOnInit(): void {
  
  }

  
  toggleCommentSection() {
      if (this.commentSection.nativeElement.style.display === 'none') {
          this.commentSection.nativeElement.style.display = 'block';
      } else {
          this.commentSection.nativeElement.style.display = 'none';
      }
  }
}


