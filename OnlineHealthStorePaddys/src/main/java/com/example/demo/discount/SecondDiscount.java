package com.example.demo.discount;

public class SecondDiscount extends DiscountDecorator{

	public SecondDiscount(Discount discount) {
		super(discount);
	}
	
    public static final double FIVE = 5.00;

 
    @Override
    public double apply(double originalPrice) {

    	double discountedPrice = super.apply(originalPrice);
        if(discountedPrice >= 90) {
            return discountedPrice - FIVE;
        }
        return discountedPrice;
    }

}
