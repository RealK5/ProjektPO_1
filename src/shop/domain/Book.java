package shop.domain;

import java.math.BigDecimal;

public class Book extends Product
{
    private static final BigDecimal VAT = new BigDecimal(0.05);
    public Book(String sku, String name, BigDecimal netPrice) {
        super(sku, name, netPrice, VAT);
    }
}
