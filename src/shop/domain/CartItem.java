package shop.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CartItem
{
    private final Product product;
    private int quantity;
    public CartItem(Product product, int quantity)
    {
        if (product == null)
        {
            throw new IllegalArgumentException("Product cannot be null."); //zabawna rzecz którą nauczyłem się z PP. Nie wiem czy wywalanie całego programu to dobry pomysł ale musi dać radę
        }
        if (quantity <= 0)
        {
            throw new IllegalArgumentException("Quantity must be more than zero.");
        }
        this.product = product;
        this.quantity = quantity;
    }
    //Konstruktor kopiujący, zadanie 9
    public CartItem(CartItem other)
    {
        this.product = other.product; //dosłownie jak na jednym z labów
        this.quantity = other.quantity;
    }

    public Product getProduct()
    {
        return product;
    }

    public int getQuantity() {

        return quantity;
    }
    //Obliczanie ceny dla pojedyńczego produktu, przyda się w koszyku(????)
    public BigDecimal calculateNetTotal()
    {
        // Skalowanie do 2 miejsc po przecinku dla ostatecznej sumy
        return product.netPrice().multiply(BigDecimal.valueOf(quantity)).setScale(2, RoundingMode.HALF_UP); //Te komendy będą naszą zgubą, ale tylko coś takiego wymyśliłem
    }

    public BigDecimal calculateTaxTotal() {
        // Używamy metody taxAmount() z Product i mnożymy przez ilość.
        return product.taxAmount().multiply(BigDecimal.valueOf(quantity)).setScale(2, RoundingMode.HALF_UP);
    }
    //Brutto
    public BigDecimal calculateGrossTotal() {
        return calculateNetTotal().add(calculateTaxTotal());
    }

    // Zmiana ilości, może się przydać
    public void setQuantity(int newQuantity) {
        if (newQuantity <= 0) {
            throw new IllegalArgumentException("Quantity must be more than zero.");
        }
        this.quantity = newQuantity;
    }

    @Override
    public String toString()
    {
        return product.getName() + " x " + quantity + " = " + calculateGrossTotal();
    }
}