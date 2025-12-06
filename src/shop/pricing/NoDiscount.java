package shop.pricing;
import java.math.BigDecimal;

public class NoDiscount implements DiscountPolicy //Brak przeceny
{
    @Override
    public BigDecimal apply(BigDecimal subtotal)
    {
        return subtotal;//Brak przeceny - zwróć zwykłą cenę
    }
}
