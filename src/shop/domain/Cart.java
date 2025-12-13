package shop.domain;

import shop.pricing.DiscountPolicy;
import shop.pricing.NoDiscount;

import java.math.*;
import java.util.*;

public class Cart
{
    private final List<CartItem> items;
    private DiscountPolicy discountPolicy;

    public Cart()
    {
        this.items = new ArrayList<>();
        // Domyślnie brak rabatu, to chyba liczy się w zadanie 5
        this.discountPolicy = new NoDiscount();
    }

    public boolean addItem(Product product, int quantity)  //Boolean może do komunikacji z zewnątrz, jak nie będzie miało użytku wyrąbać return i zmienić w void
    {
        if (quantity <= 0)
        {
            return false; //aby nie było więcej błędów. Przy zmianie w void po prostu zostawić "return;"
        }

        CartItem existingItem = null;
        for (CartItem item : items)
        {
            if (item.getProduct().getSku().equals(product.getSku()))
            {
                existingItem = item;
                break;
            }
        }

        if (existingItem != null)
        {
            existingItem.setQuantity(existingItem.getQuantity() + quantity); //dodaje więcej przedmiotu do koszyka
            return true;
        }
        else
        {
            items.add(new CartItem(product, quantity)); //Dodajemy nowy produkt
            return true;
        }
    }

    public boolean removeItem(String sku)
    {
        for (int i = 0; i < items.size(); i++)
        {
            CartItem item = items.get(i);
            if (item.getProduct().getSku().equals(sku))
            {
                items.remove(i);
                return true;
            }
        }
        return false; // Nie znaleziono
    }

    //Netto
    public BigDecimal calculateNetSubtotal()
    {
        BigDecimal netTotal = BigDecimal.ZERO;
        for (CartItem item : items)
        {
            netTotal = netTotal.add(item.calculateNetTotal());
        }
        // Skalowanie do 2 miejsc po przecinku
        return netTotal.setScale(2, RoundingMode.HALF_UP);
    }

    //Obliczamy Podatki
    public BigDecimal calculateTaxTotal() {
        BigDecimal taxTotal = BigDecimal.ZERO;
        for (CartItem item : items)
        {
            taxTotal = taxTotal.add(item.calculateTaxTotal());
        }
        return taxTotal.setScale(2, RoundingMode.HALF_UP);
    }

    //Brutto
    public BigDecimal calculateGrossSubtotal() {
        BigDecimal grossTotal = BigDecimal.ZERO;
        for (CartItem item : items)
        {
            grossTotal = grossTotal.add(item.calculateGrossTotal());
        }
        return grossTotal.setScale(2, RoundingMode.HALF_UP);
    }

    //Obliczanie brutto po rabacie zgodnie z zadaniem 5
    public BigDecimal calculateFinalGrossTotal()
    {
        BigDecimal grossSubtotal = calculateGrossSubtotal();
        // Używamy polimorfizmu do zastosowania wybranej strategii rabatowej
        return discountPolicy.apply(grossSubtotal).setScale(2, RoundingMode.HALF_UP);
    }

    //Doodawanie nowej zniżki zgodnie z zadaniem 5
    public void setDiscountPolicy(DiscountPolicy policy)
    {
        this.discountPolicy = (policy != null) ? policy : new NoDiscount();
    }


    public List<CartItem> getItems()
    {
        return Collections.unmodifiableList(items);
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("-------------------- KOSZYK --------------------\n");
        for (CartItem item : items) {
            sb.append(item.toString()).append("\n");
        }
        sb.append("------------------------------------------------\n");
        sb.append(String.format("Suma Brutto przed rabatem: %10.2f zł\n", calculateGrossSubtotal()));
        sb.append(String.format("Podatek VAT (Łącznie):    %10.2f zł\n", calculateTaxTotal()));

        BigDecimal grossSubtotal = calculateGrossSubtotal();
        BigDecimal finalTotal = calculateFinalGrossTotal();
        BigDecimal discount = grossSubtotal.subtract(finalTotal);

        sb.append(String.format("Rabat:                    %10.2f zł\n", discount));
        sb.append(String.format("SUMA DO ZAPŁAT (Brutto):  %10.2f zł\n", finalTotal));
        sb.append("------------------------------------------------");
        return sb.toString();
    }

}