package shop.payment;

public class CardPayment implements Payable {
    private String status = "NEW";
    private double authorizedAmount;

    @Override
    public boolean authorize(double amount)
    {
        System.out.println("Attempting to authorize " + amount);
        if ("NEW".equals(status))
        {
            this.authorizedAmount = amount;
            this.status = "AUTHORIZED";
            System.out.println("Successfully AUTHORIZED.");
            return true;
        }
        System.out.println("Cannot authorize. Current status: " + status);
        return false;
    }

    @Override
    public boolean capture() {
        if ("AUTHORIZED".equals(status))
        {
            System.out.println("Capturing authorized amount...");
            this.status = "CAPTURED";
            System.out.println("Successfully CAPTURED.");
            return true;
        }
        System.out.println("Cannot CAPTURE. Current status: " + status);
        return false;
    }

    @Override
    public boolean refund()
    {
        if ("CAPTURED".equals(status))
        {
            System.out.println("Initiating refund...");
            this.status = "REFUNDED";
            System.out.println("Successfully REFUNDED.");
            return true;
        }
        System.out.println("Cannot REFUND. Current status: " + status);
        return false;
    }

    public String getStatus()
    {
        return status;
    }
}