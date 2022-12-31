import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ShowFoldersComponent } from './show-folders.component';

describe('ShowFoldersComponent', () => {
  let component: ShowFoldersComponent;
  let fixture: ComponentFixture<ShowFoldersComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ShowFoldersComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ShowFoldersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
