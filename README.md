## Pomysł 2: Mini Sklep Internetowy (koszyk + płatności + faktury)

Cel: zaprojektować miniaturowy sklep z koszykiem, produktami różnych typów, mechanizmem rabatów i wieloma formami płatności. Projekt akcentuje interfejsy, klasy abstrakcyjne i polimorfizm.

### Lista kontrolna
1. Inicjalizacja projektu i pakietów [02][04]
   - Utwórz projekt z pakietami: `shop.domain`, `shop.pricing`, `shop.payment`, `shop.invoice`, `shop.app`. [04]
   - Dodaj `shop.app.Main` z `public static void main`. [03]

2. Abstrakcja produktu [08][07][05b]
   - Klasa abstrakcyjna `domain.Product` z polami: `sku` (String), `name` (String), `netPrice` (BigDecimal), `taxRate` (BigDecimal). [08][05b]
   - Metody: `BigDecimal netPrice()`, `BigDecimal taxAmount()`, `BigDecimal grossPrice()` (częściowo/lub w pełni zaimplementowane). [08]
   - Klasy konkretne: `Book`, `Food`, `Electronic` (różne reguły podatkowe/rabaty). [07]

3. Interfejsy rozszerzające możliwości [08]
   - `domain.Taxable` (już implikowane w `Product`, ale wypchnij kontrakt do interfejsu dla spójności). [08]
   - `invoice.Printable` z metodą `print()` do drukowania faktury/rachunku. [08]
   - `payment.Payable` jako kontrakt metod `authorize()`, `capture()`, `refund()`; implementacje: `CardPayment`, `BlikPayment`, `CashOnDelivery`. [08]

4. Koszyk i pozycje [03][05b]
   - `domain.CartItem` (product + quantity) z walidacją w konstruktorze. [06][05b]
   - `domain.Cart` z kolekcją pozycji, metodami dodawania/usuwania, liczenia sum netto/podatku/brutto. [03]
   - Zadbaj o kapsułkowanie kolekcji i niezmienniki (ilości > 0). [05]

5. Rabaty jako strategia [08][07]
   - Interfejs `pricing.DiscountPolicy` z `BigDecimal apply(BigDecimal subtotal)`. [08]
   - Implementacje: `NoDiscount`, `PercentageDiscount`, `ThresholdFixedDiscount`. [08]
   - Użyj polimorfizmu do podmiany polityki rabatowej w `Cart`. [07]

6. Generowanie dokumentów sprzedaży [08]
   - Klasa `invoice.Invoice` implementująca `Printable`, potrafi tworzyć czytelną reprezentację tekstową dokumentu. [08]
   - Rozważ `Exportable` do generacji CSV/JSON z danymi zamówienia. [08]

7. Obsługa płatności [08]
   - Zaimplementuj `Payable` w klasach płatności, dodaj proste stany (AUTHORIZED/CAPTURED/REFUNDED). [08]
   - Zaprojektuj `payment.PaymentService` orkiestrujący proces: autoryzacja → obciążenie → w razie potrzeby zwrot. [03]

8. Modyfikatory dostępu i pola statyczne [05][05b]
   - Użyj `private` dla pól, publicznych getterów, metod pakietowych, jeśli potrzebne. [05]
   - Dodaj `public static final BigDecimal TAX_23 = ...` itp. w klasie narzędziowej `Tax`. [05b]

9. Konstruktory i walidacje [06]
   - Waliduj `sku`, `name`, ceny i stawki podatkowe w konstruktorach. [06]
   - Rozważ konstruktor kopiujący dla `CartItem`. [06]

10. Scenariusz demo w `Main` [03]
    - Zbuduj koszyk z produktami, zastosuj politykę rabatową, wygeneruj fakturę i „opłać” zamówienie różnymi metodami. [03][08]

11. Rozszerzenia (opcjonalnie) [07][08]
    - Łańcuch odpowiedzialności dla promocji (lista `DiscountPolicy` stosowana sekwencyjnie). [08]
    - Interfejs `Returnable` dla obsługi zwrotów produktów. [08]

Co ćwiczysz: hierarchie klas i kontrakty (07–08), konstrukcję i walidację obiektów (06), pola i stałe (05b), kontrolę dostępu i kapsułkowanie (05), pakiety i importy (04), klasę uruchomieniową (03), praktykę narzędzi (02), myślenie obiektowe (01).

---
