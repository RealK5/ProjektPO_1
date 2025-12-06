package shop.pricing;
import java.math.BigDecimal;

public interface DiscountPolicy //Interfejs zarządzający przecenami
{
    BigDecimal apply(BigDecimal subtotal);
}
