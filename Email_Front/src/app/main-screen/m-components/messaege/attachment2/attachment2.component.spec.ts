import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Attachment2Component } from './attachment2.component';

describe('Attachment2Component', () => {
  let component: Attachment2Component;
  let fixture: ComponentFixture<Attachment2Component>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ Attachment2Component ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(Attachment2Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
