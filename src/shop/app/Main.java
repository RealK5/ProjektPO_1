package shop.app;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import shop.domain.*;
import shop.invoice.Invoice;
import shop.payment.*;
import shop.pricing.*;

import java.math.BigDecimal;

public class Main {

    public static void main(String[] args) {

        System.out.println("=== START MINI SKLEPU ===\n");

        // ===== PRODUKTY =====
        Product book = new Book("B001", "Java Piekło", new BigDecimal("100.00"));
        Product phone = new Electronic("E001", "Samsung Galaxy A55", new BigDecimal("1000.00"));
        Product owoc = new Food("F001", "Jabłko", new BigDecimal("3.50")); // Food z VAT 0% np.

        // ===== KOSZYK =====
        Cart cart = new Cart();
        cart.addItem(book, 2);   // 2 książki
        cart.addItem(phone, 1);  // 1 telefon
        cart.addItem(owoc, 10); // 10 jabłek

        // ===== RABAT =====
        System.out.println("\n-- Rabat 10% --");
        cart.setDiscountPolicy(new PercentageDiscount(new BigDecimal("0.10")));

        // ===== PODSUMOWANIE KOSZYKA =====
        System.out.println(cart);

        // ===== FAKTURA =====
        System.out.println("\n-- GENEROWANIE FAKTURY --");
        Invoice invoice = new Invoice(cart);
        invoice.print();

        // ===== PŁATNOŚĆ =====
        PaymentService paymentService = new PaymentService();

        System.out.println("\n-- PŁATNOŚĆ KARTĄ --");
        Payable cardPayment = new CardPayment();
        paymentService.processPayment(cardPayment, cart.calculateFinalGrossTotal().doubleValue());

        System.out.println("\n-- PŁATNOŚĆ GOTÓWKĄ --");
        Payable cash = new CashOnDelivery();
        paymentService.processPayment(cash, cart.calculateFinalGrossTotal().doubleValue());

        System.out.println("\n=== KONIEC SKLEPU ===");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("faktura.txt"))) {
            writer.write(invoice.toString());
            System.out.println("Faktura została zapisana do faktura.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // zapis CSV
        try (java.io.FileWriter fw = new java.io.FileWriter("faktura.csv")) {
            fw.write(invoice.toCSV());
            System.out.println("Zapisano fakturę do faktura.csv");
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }

// zapis JSON
        try (java.io.FileWriter fw = new java.io.FileWriter("faktura.json")) {
            fw.write(invoice.toJSON());
            System.out.println("Zapisano fakturę do faktura.json");
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

}
