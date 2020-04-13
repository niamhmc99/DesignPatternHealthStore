package com.example.demo.decorator;

public class ShoppingCartPriceBasedDiscountCampaignDecorator extends ShoppingCartCampaignDecorator{

	 private double priceLimit;
	    private double discount;
	    /**
	     * If the amount is bigger than totalCost of this shoppping basket than discount will be applied
	     * @param delegate
	     * @param priceLimit
	     * @param discount
	     */
	public ShoppingCartPriceBasedDiscountCampaignDecorator(ShoppingCartInterface delegate, double priceLimit, double discount) {
		super(delegate);
		this.discount = discount;
        this.priceLimit = priceLimit;
    }
     
    public Double getTotalCost() {
        double actualTotalCost = super.getTotalCost();
        if(actualTotalCost > this.priceLimit){
            return actualTotalCost - actualTotalCost*discount;
        }else{
            return actualTotalCost;
        }
    }


}
