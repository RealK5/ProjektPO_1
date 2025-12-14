package shop.payment;

public class CardPayment implements Payable
{
    //inicjalizacja serwisu i ustawienie stanu
    private final PaymentService paymentService;
    private String status = "NEW";

    public CardPayment(PaymentService paymentService)
    {
        this.paymentService = paymentService; //konstrukto, po prostu upewnia się, że bierze odpowiedni serwis
    }

    // Obsługa zmiany stanu
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
        // Obiekt płatności prosi PaymentService o wykonanie Autoryzacji na sobie
        return paymentService.authorize(this, amount);
    }

    @Override
    public boolean capture()
    {
        // Obiekt płatności prosi PaymentService o wykonanie Obciążenia na sobie
        return paymentService.capture(this);
    }

    @Override
    public boolean refund()
    {
        // Obiekt płatności prosi PaymentService o wykonanie Zwrotu na sobie
        return paymentService.refund(this);
    }
}