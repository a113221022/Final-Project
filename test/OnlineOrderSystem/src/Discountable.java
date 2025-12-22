public interface Discountable {
    double calculateDiscount(String type);
    boolean isDiscountApplicable(String type);
}