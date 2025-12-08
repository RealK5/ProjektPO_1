package shop.domain;

import java.math.BigDecimal;

public class Electronic extends Product
{
    private static final BigDecimal VAT = new BigDecimal(0.1);
    public Electronic(String sku, String name, BigDecimal netPrice) {
        super(sku, name, netPrice, VAT);
    }

}
