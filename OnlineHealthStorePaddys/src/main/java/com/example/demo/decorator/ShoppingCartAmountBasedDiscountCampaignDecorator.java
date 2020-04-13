package com.example.demo.decorator;

public class ShoppingCartAmountBasedDiscountCampaignDecorator extends ShoppingCartCampaignDecorator {

    private int amount;
    private double discount;
    /**
     * If amount is bigger than the given parameter than discount will be applied.
     * @param delegate
     * @param discount
     * @param amount
     */
    public ShoppingCartAmountBasedDiscountCampaignDecorator(ShoppingCartInterface delegate, double discount, int amount) {
        super(delegate);
        this.amount = amount;
        this.discount = discount;
    }   
    public Double getTotalCost() {
        double actualTotalCost = super.getTotalCost();
        if(this.getShoppingListItems().size() > this.amount){
            return actualTotalCost - actualTotalCost*discount;
        }else{
            return actualTotalCost;
        }
    }
	
}
