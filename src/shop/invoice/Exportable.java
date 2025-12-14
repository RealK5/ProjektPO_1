package shop.invoice;

public interface Exportable {
    String toCSV();
    String toJSON();
}
