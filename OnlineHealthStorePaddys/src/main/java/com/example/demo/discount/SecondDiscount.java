package com.example.demo.discount;

public class SecondDiscount extends DiscountDecorator{

	//Receive second discount if after the first 20% discount is applied and your price is still over 90er
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
