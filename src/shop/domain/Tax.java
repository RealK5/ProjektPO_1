package shop.domain;

import java.math.BigDecimal;

public final class Tax {

    private Tax() {} // brak możliwości tworzenia instancji

    public static final BigDecimal TAX_23 = new BigDecimal("0.23");
    public static final BigDecimal TAX_10 = new BigDecimal("0.10");
    public static final BigDecimal TAX_05 = new BigDecimal("0.05");
    public static final BigDecimal TAX_00 = BigDecimal.ZERO;
}
