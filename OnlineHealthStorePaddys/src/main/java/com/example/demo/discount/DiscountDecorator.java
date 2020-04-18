package com.example.demo.discount;

public class DiscountDecorator implements Discount{
	
    protected Discount discount;

    public DiscountDecorator(Discount discount) {
        this.discount = discount;
    }

    @Override
    public double apply(double originalPrice) {
        return discount.apply(originalPrice);
    }

}
