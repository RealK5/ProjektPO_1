package shop.payment;

public interface Payable {
    boolean authorize(double amount);
    boolean capture();
    boolean refund();
}