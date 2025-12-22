public class DeliveryFeeCalculator {
    public static double calculate(double distance, int hour) {
        double fee = distance * 15;
        if ((hour >= 11 && hour <= 13) || (hour >= 17 && hour <= 19)) {
            fee *= 2.0;
        }
        return fee;
    }
}