package shop.domain;
import java.math.BigDecimal;
import java.math.RoundingMode;

public abstract class Product implements Taxable{
    private final String sku;
    private final String name;
    private final BigDecimal netPrice;
    private final BigDecimal taxRate;

    public Product(String sku, String name, BigDecimal netPrice, BigDecimal taxRate) {//Konstruktor produktu z walidacją wartości

        if (sku == null || sku.isEmpty())
        {
            throw new IllegalArgumentException("SKU cannot be empty");//Walidacja SKU
        }
        else  this.sku = sku;
        if (name == null || name.isEmpty())
        {
            throw new IllegalArgumentException("Name cannot be empty");//Walidacja nazwy
        }
        else this.name = name;
        if (netPrice.compareTo(BigDecimal.ZERO) < 0)
        {
            throw new IllegalArgumentException("Price cannot be negative");//Walidacja ceny
        }
        else this.netPrice = netPrice;
        if (taxRate.compareTo(BigDecimal.ZERO) < 0)
        {
            throw new IllegalArgumentException("TaxRate cannot be negative");//Walidacja podatku
        }
        else this.taxRate = taxRate;

    }

    //Metoda zwracjaca cenę netto
    public BigDecimal netPrice()
    {
        return netPrice;
    }
    //Metoda zwracjaca podatek produktu, zaokrąglony do 2 miejsca po precinku do góry
    public BigDecimal taxAmount()
    {
        return netPrice.multiply(taxRate).setScale(2, RoundingMode.HALF_UP);
    }
    //Metoda zwracjaca cenę brutto
    public BigDecimal bruttoPrice()
    {
        return netPrice.add(taxAmount());
    }
    //Metoda zwracjaca procent opodatkowania
    @Override
    public BigDecimal getTaxRate()
    {
        return taxRate;
    }
    // Metoda zwracająca nazwę productu
    public String getName()
    {
        return name;
    }
    //Metoda zwracająca SKU productu
    public String getSku()
    {
        return sku;
    }
}
