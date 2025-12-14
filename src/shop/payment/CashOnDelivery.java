package shop.payment;

public class CashOnDelivery implements Payable {

    private String status = "NEW";

    public CashOnDelivery() {}

    public String getStatus() { return status; }
    public void setStatus(String s) { status = s; }

    @Override
    public boolean authorize(double amount) {
        System.out.println("Cash on delivery authorized: " + amount);
        status = "AUTHORIZED";
        return true;
    }

    @Override
    public boolean capture() {
        System.out.println("Cash on delivery captured");
        status = "CAPTURED";
        return true;
    }

    @Override
    public boolean refund() {
        System.out.println("Cash on delivery refunded");
        status = "REFUNDED";
        return true;
    }
}
