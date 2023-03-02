import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProductPromotionSpecialComponent } from './product-promotion-special.component';

describe('ProductPromotionSpecialComponent', () => {
  let component: ProductPromotionSpecialComponent;
  let fixture: ComponentFixture<ProductPromotionSpecialComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProductPromotionSpecialComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ProductPromotionSpecialComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
