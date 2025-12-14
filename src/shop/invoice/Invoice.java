package shop.invoice;

import shop.domain.CartItem;
import shop.domain.Cart;
import java.util.stream.Collectors;

public class Invoice implements Printable, Exportable {

    private final String invoiceNumber;
    private final Cart cart;

    public Invoice(Cart cart) {
        this.cart = cart;
        this.invoiceNumber = java.util.UUID.randomUUID().toString().substring(0,8).toUpperCase();
    }

    @Override
    public void print() {
        System.out.println(this.toString());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("============== FAKTURA VAT ==============\n");
        sb.append("Numer: ").append(invoiceNumber).append("\n");
        sb.append("-----------------------------------------\n");
        for (CartItem item : cart.getItems()) {
            sb.append(item.getProduct().getName())
                    .append(" x ")
                    .append(item.getQuantity())
                    .append(" | brutto: ")
                    .append(item.calculateGrossTotal())
                    .append(" zł\n");
        }
        sb.append("-----------------------------------------\n");
        sb.append("Suma brutto: ").append(cart.calculateFinalGrossTotal()).append(" zł\n");
        sb.append("=========================================\n");
        return sb.toString();
    }

    // ===== Exportable =====
    @Override
    public String toCSV() {
        String header = "Produkt,Ilość,Brutto\n";
        String rows = cart.getItems().stream()
                .map(item -> item.getProduct().getName() + "," + item.getQuantity() + "," + item.calculateGrossTotal())
                .collect(Collectors.joining("\n"));
        return header + rows;
    }

    @Override
    public String toJSON() {
        String itemsJson = cart.getItems().stream()
                .map(item -> String.format("{\"produkt\":\"%s\",\"ilosc\":%d,\"brutto\":%.2f}",
                        item.getProduct().getName(),
                        item.getQuantity(),
                        item.calculateGrossTotal()))
                .collect(Collectors.joining(","));
        return String.format("{\"numer\":\"%s\",\"sumaBrutto\":%.2f,\"pozycje\":[%s]}",
                invoiceNumber,
                cart.calculateFinalGrossTotal(),
                itemsJson);
    }
}
