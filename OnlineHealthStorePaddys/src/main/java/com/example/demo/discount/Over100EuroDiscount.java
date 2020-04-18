package com.example.demo.discount;


public class Over100EuroDiscount implements Discount{
	
	//if buy more than 10 products give 20% discount
	//here if orginal purchase costs more than £100 give 20% off
	
	   public static final double EIGHTY = 80;
	    public static final double ONE_HUNDRED = 100;

	    @Override
	    public double apply(double originalPrice) {
	    	if( originalPrice > 100.00) {
	    		 return originalPrice* EIGHTY/ ONE_HUNDRED;
	    	}
	       return originalPrice;
	    }
	    
	    
}
