import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MedecinsComponent } from './medecins.component';

describe('MedecinsComponent', () => {
  let component: MedecinsComponent;
  let fixture: ComponentFixture<MedecinsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [MedecinsComponent]
    });
    fixture = TestBed.createComponent(MedecinsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
