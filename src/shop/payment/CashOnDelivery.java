package shop.payment;

public class CashOnDelivery implements Payable
{
    private String status = "NEW";
    private double authorizedAmount;

    @Override
    public boolean authorize(double amount)
    {
        System.out.println("Cash On Delivery: Deliver physical payment of " + amount);
        this.authorizedAmount = amount;
        return true;
    }

    @Override
    public boolean capture()
    {
        if ("NEW".equals(status))
        {
            System.out.println("Physical payment received upon delivery.");
            this.status = "CAPTURED";
            System.out.println("Successfully CAPTURED (by delivery staff).");
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
            System.out.println("Cash On Delivery: Initiating refund (transfer required).");
            this.status = "REFUNDED";
            System.out.println("Cash On Delivery: Successfully REFUNDED.");
            return true;
        }
        System.out.println("Cash On Delivery: Cannot REFUND. Current status: " + status);
        return false;
    }

    public String getStatus()
    {
        return status;
    }
}