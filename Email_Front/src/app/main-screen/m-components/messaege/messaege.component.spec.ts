import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MessaegeComponent } from './messaege.component';

describe('MessaegeComponent', () => {
  let component: MessaegeComponent;
  let fixture: ComponentFixture<MessaegeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MessaegeComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MessaegeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
