
package com.flipdeal;

import java.util.ArrayList;

class PromotionSetA extends ProductFactory {
    protected Discount calculateDiscount(ProductResponse prod) {
        ArrayList<String> discountCategories = new ArrayList<String>();
        discountCategories.add("electronics");
        discountCategories.add("furnishing");
        Discount disc = new Discount();
        try {
            if (prod.getOrigin().equals("Africa")) {
                disc.setAmount(7.0);
                disc.setAmountTag("get 7% off");
            }
            if (prod.getRating() == 2.0 && disc.getAmount() < 4.0) {
                disc.setAmount(4.0);
                disc.setAmountTag("get 4% off");
            }

            if (prod.getRating() < 2.0 && disc.getAmount() < 8.0) {
                disc.setAmount(8.0);
                disc.setAmountTag("get 8% off");
            }

            disc.setAmount((disc.getAmount() / 100.0) * prod.getPrice());

            if (discountCategories.contains(prod.getCategory()) && prod.getPrice() > 500.0
                    && disc.getAmount() < 100.0) {
                disc.setAmountTag("get Rs 100 off");
                disc.setAmount(100.0);
            }
            if (prod.getPrice() > 1000.0 && disc.getAmount() == 0.0) {
                disc.setAmountTag("get 2% off");
                disc.setAmount((2.0 / 100.0) * prod.getPrice());
            }
            if (disc.getAmountTag() == null || disc.getAmountTag().isEmpty()) {
                disc = null;
            }
        } catch (Exception e) {
            this.LOGGER.severe("PromotionSetA:calculatePromotu:: " + e.toString());
            disc = null;
        }
        return disc;

    }

    public void generateDiscountedProductJSON() {
        try {
            this.prePromotion();
            for (ProductResponse prod : this.products) {
                prod.setDiscount(this.calculateDiscount(prod));
            }
            this.saveDiscountedProductData();
            this.LOGGER.info(this.discountedProductsJSONPath + " file created for promotion set A");

        } catch (Exception e) {
            this.LOGGER.severe("PromotionSetA:generateDiscountedProductJSON:: " + e.toString());
        }
    }
}

class PromotionSetB extends ProductFactory {
    public Discount calculateDiscount(ProductResponse prod) {
        Discount disc = new Discount();
        try {
            if (prod.getInventory() > 20) {
                disc.setAmountTag("get 12% off");
                disc.setAmount(12.0);
            }
            if (prod.getArrival() != null && prod.getArrival().equals("NEW") && disc.getAmount() < 7.0) {
                disc.setAmountTag("get 7% off");
                disc.setAmount(7.0);
            }
            disc.setAmount((disc.getAmount() / 100.0) * prod.getPrice());
            if (prod.getPrice() > 1000.0 && disc.getAmount() == 0.0) {
                disc.setAmountTag("get 2% off");
                disc.setAmount((2.0 / 100.0) * prod.getPrice());
            }
            if (disc.getAmountTag() == null || disc.getAmountTag().isEmpty()) {
                disc = null;
            }

        } catch (Exception e) {
            this.LOGGER.severe("PromotionSetB:calculateDiscount:: " + e.toString());
            disc = null;
        }
        return disc;

    }

    public void generateDiscountedProductJSON() {
        try {
            this.prePromotion();
            for (ProductResponse prod : this.products) {
                prod.setDiscount(this.calculateDiscount(prod));
            }
            this.saveDiscountedProductData();
            this.LOGGER.info(this.discountedProductsJSONPath + " file created for promotion set B");

        } catch (Exception e) {
            this.LOGGER.severe("PromotionSetB:generateDiscountedProductJSON:: " + e.toString());
        }
    }
}

public class App {
    public static void main(String[] args) {
        try {
            if (args.length > 0){
                if (args[0].toLowerCase().equals("promotionseta")){
                PromotionSetA pseta = new PromotionSetA();
                pseta.generateDiscountedProductJSON();    
                }
                else if (args[0].toLowerCase().equals("promotionsetb")){
                    PromotionSetB psetb = new PromotionSetB();
                    psetb.generateDiscountedProductJSON();
                }
                else {
                    System.out.println("Invalid promotion set provided!");    
                }
            }
            else {
                System.out.println("No promotion set provided!");
            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
