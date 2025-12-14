package shop.payment;
//KOMENTARZE ODNOŚNIE DZIAŁANIA W CARDPAYMNENT!!!!!1!!!!!!!!!111!!!11!
public class BlikPayment implements Payable
{
    private final PaymentService paymentService;
    private String status = "NEW";

    public BlikPayment(PaymentService paymentService)
    {
        this.paymentService = paymentService;
    }

    public String getStatus()
    {
        return status;
    }
    public void setStatus(String newStatus)
    {
        this.status = newStatus;
    }

    @Override
    public boolean authorize(double amount)
    {
        return paymentService.authorize(this, amount);
    }

    @Override
    public boolean capture()
    {
        return paymentService.capture(this);
    }

    @Override
    public boolean refund()
    {
        return paymentService.refund(this);
    }
}