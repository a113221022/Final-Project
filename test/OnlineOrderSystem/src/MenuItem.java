import java.util.ArrayList;

public abstract class MenuItem implements Preparable {
    private String name;
    private double basePrice;
    public MenuItem(String name, double basePrice) { this.name = name; this.basePrice = basePrice; }
    public String getName() { return name; }
    public double getBasePrice() { return basePrice; }
    public abstract double calculatePrice();
    @Override public int getPriority() { return 1; }
}

class MainDish extends MenuItem implements Customizable {
    private String note = "";
    public MainDish(String name, double basePrice) { super(name, basePrice); }
    @Override public double calculatePrice() { return getBasePrice(); }
    @Override public void applyCustomization(String opt) { this.note = opt; }
    @Override public double getCustomizationFee() { return 0; }
    @Override public ArrayList<String> getCustomizations() {
        ArrayList<String> l = new ArrayList<>();
        if (!note.isEmpty()) l.add(note);
        return l;
    }
    @Override public int getPreparationTime() { return 15; }
}

class Beverage extends MenuItem implements Customizable {
    private String note = "";
    public Beverage(String name, double basePrice) { super(name, basePrice); }
    @Override public double calculatePrice() { return getBasePrice(); }
    @Override public void applyCustomization(String opt) { this.note = opt; }
    @Override public double getCustomizationFee() { return 0; }
    @Override public ArrayList<String> getCustomizations() {
        ArrayList<String> l = new ArrayList<>();
        if (!note.isEmpty()) l.add(note);
        return l;
    }
    @Override public int getPreparationTime() { return 3; }
}