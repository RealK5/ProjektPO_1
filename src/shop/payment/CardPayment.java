package shop.payment;

public class CardPayment implements Payable {

    private String status = "NEW";

    public CardPayment() {} //
    // Obsługa zmiany stanu
    public String getStatus()
    { return status; }
    public void setStatus(String s)
    { status = s; }

    @Override// Obiekt płatności prosi PaymentService o wykonanie Autoryzacji na sobie
    public boolean authorize(double amount) {
        System.out.println("Card authorized: " + amount);
        status = "AUTHORIZED";
        return true;
    }
    // Obiekt płatności prosi PaymentService o wykonanie Obciążenia na sobie
    @Override
    public boolean capture() {
        System.out.println("Card captured");
        status = "CAPTURED";
        return true;
    }
    // Obiekt płatności prosi PaymentService o wykonanie Zwrotu na sobie
    @Override
    public boolean refund() {
        System.out.println("Card refunded");
        status = "REFUNDED";
        return true;
    }
}
