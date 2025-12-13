package shop.payment;

public class BlikPayment implements Payable {
    private String status = "NEW";
    private double authorizedAmount;

    @Override
    public boolean authorize(double amount)
    {
        System.out.println("Requesting authorization for " + amount);
        if ("NEW".equals(status))
        {
            this.authorizedAmount = amount;
            this.status = "AUTHORIZED";
            System.out.println("Successfully AUTHORIZED (via Blik code).");
            return true;
        }
        System.out.println("Cannot authorize. Current status: " + status);
        return false;
    }

    @Override
    public boolean capture()
    {
        if ("AUTHORIZED".equals(status) || "NEW".equals(status))
        {
            System.out.println("Blik Payment: Instant capture");
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
            System.out.println("Initiating refund");
            this.status = "REFUNDED";
            System.out.println("Successfully REFUNDED.");
            return true;
        }
        System.out.println("Cannot REFUND. Current status: " + status);
        return false;
    }

    public String getStatus() {
        return status;
    }
}