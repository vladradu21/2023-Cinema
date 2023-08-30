import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MovieCompComponent } from './movie-comp.component';

describe('MovieCompComponent', () => {
  let component: MovieCompComponent;
  let fixture: ComponentFixture<MovieCompComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MovieCompComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MovieCompComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
