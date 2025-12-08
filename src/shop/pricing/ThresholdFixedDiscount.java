package shop.pricing;
import java.math.BigDecimal;

public class ThresholdFixedDiscount implements DiscountPolicy //Z przeceną o daną wartość
{
    private final BigDecimal threshold; //Minimalna cena koszyka od której zostanie nałożona przecena
    private final BigDecimal discount;//Przecna

    public ThresholdFixedDiscount(double threshold, double discountAmount)//setter
    {
        this.threshold = BigDecimal.valueOf(threshold);
        this.discount = BigDecimal.valueOf(discountAmount);
    }

    @Override
    public BigDecimal apply(BigDecimal subtotal)
    {
        if (subtotal.compareTo(threshold) >= 0) {
            return subtotal.subtract(discount).max(BigDecimal.ZERO);//jeżeli cena wynosi więcej niż treshold do stosuje przecenę jeżeli nie to nie stosuje
        }
        return subtotal;
    }


}
