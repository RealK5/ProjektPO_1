package shop.payment;

public class PaymentService
{

    private static final String NEW = "NEW";
    private static final String AUTHORIZED = "AUTHORIZED";
    private static final String CAPTURED = "CAPTURED";
    private static final String REFUNDED = "REFUNDED";

    // Zarządzanie Stanem, myślałem że zrobię dla każdego osobno ale może tak jest lepiej?
    private String getStatus(Payable p)
    {
        if (p instanceof CardPayment)
        {
            return ((CardPayment)p).getStatus();
        }
        if (p instanceof BlikPayment)
        {
            return ((BlikPayment)p).getStatus();
        }
        if (p instanceof CashOnDelivery)
        {
            return ((CashOnDelivery)p).getStatus();
        }
        return NEW; // Domyślny, jeśli typ nieznany chociaż to nie powinna się stać
    }
    //Ustawianie statusu
    private void setStatus(Payable p, String newStatus)
    {
        if (p instanceof CardPayment)
        {
            ((CardPayment)p).setStatus(newStatus);
        }
        if (p instanceof BlikPayment)
        {
            ((BlikPayment)p).setStatus(newStatus);
        }
        if (p instanceof CashOnDelivery)
        {
            ((CashOnDelivery)p).setStatus(newStatus);
        }
    }
    // Autoryzacja, sprawdzamy czy przebiega dobrze, przy okazji wywołuje ten crap więc jest cool
    public boolean processPayment(Payable payment, double amount)
    {
        if (authorize(payment, amount))
        {
            return capture(payment); //Gdy autorka przejdzie, zbieraj kasę
        }
        return false;
    }

    //Autorka
    public boolean authorize(Payable payment, double amount)
    {
        String currentStatus = getStatus(payment);
        System.out.println("Authorizing, current state: " + currentStatus);

        if (!NEW.equals(currentStatus))
        {
            System.err.println("Error, can't authorize non-new transactions!");
            return false;
        }
        if (payment.authorize(amount))
        {
            setStatus(payment, AUTHORIZED); // ZARZĄDZANIE STANEM
            System.out.println("Authorized!");
            return true;
        }
        else
        {
            System.err.println("Error, can't authorize");
            return false;
        }
    }
    //Pobieranie kasiory
    public boolean capture(Payable payment)
    {
        String currentStatus = getStatus(payment);
        System.out.println("Capturing, current state: " + currentStatus);

        if (!(AUTHORIZED.equals(currentStatus) || NEW.equals(currentStatus)))
        {
            System.err.println("Error, can only capture authorized or new transactions!");
            return false;
        }
        if (payment.capture())
        {
            setStatus(payment, CAPTURED);
            System.out.println("Captured!");
            return true;
        } else
        {
            System.err.println("Error, can't capture");
            return false;
        }
    }

    public boolean refund(Payable payment)
    {
        String currentStatus = getStatus(payment);
        System.out.println("Refunding, current state: " + currentStatus);

        if (!CAPTURED.equals(currentStatus))
        {
            System.err.println("Error, can't refund non-captured transactions!");
            return false;
        }
        if (payment.refund())
        {
            setStatus(payment, REFUNDED); // ZARZĄDZANIE STANEM
            System.out.println("Refunded! :(");
            return true;
        }
        else
        {
            System.err.println("Error, can't refund :)");
            return false;
        }
    }
}