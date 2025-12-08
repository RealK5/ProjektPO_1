package shop.pricing;
import java.math.BigDecimal;

public class PercentageDiscount implements DiscountPolicy//Z przeceną procentową
{
    private final BigDecimal discount;//Format przeceny to 0.10 dla 10% , 0.20 dla 20% itd.

    public PercentageDiscount(BigDecimal discount)
    {
        this.discount=discount;
    }

    @Override
    public BigDecimal apply(BigDecimal subtotal)
    {
        return subtotal.subtract(subtotal.multiply(discount));//Przecena - zwraca cenę obniżoną o procent przeceny
    }
}
