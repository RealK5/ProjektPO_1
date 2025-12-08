package shop.domain;

import java.math.BigDecimal;

public class Food extends Product
{
    private static final BigDecimal VAT = new BigDecimal(0.15);
    public Food(String sku, String name, BigDecimal netPrice) {
        super(sku, name, netPrice, VAT);
    }
}
