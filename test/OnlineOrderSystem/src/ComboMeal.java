import java.util.List;
import java.util.ArrayList;

public class ComboMeal extends MenuItem implements Customizable {
    private List<MenuItem> contents;

    public ComboMeal(String name, List<MenuItem> items) {
        super(name, 0);
        this.contents = items;
    }

    @Override
    public double calculatePrice() {
        // 加總內部所有單品價格
        return contents.stream().mapToDouble(MenuItem::calculatePrice).sum();
    }

    @Override
    public void applyCustomization(String option) {
        // 只將客製化需求設定給第一個品項 (主餐)，避免重複顯示
        if (!contents.isEmpty() && contents.get(0) instanceof Customizable) {
            ((Customizable) contents.get(0)).applyCustomization(option);
        }
    }

    @Override
    public ArrayList<String> getCustomizations() {
        ArrayList<String> allNotes = new ArrayList<>();
        for (MenuItem item : contents) {
            if (item instanceof Customizable) {
                allNotes.addAll(((Customizable) item).getCustomizations());
            }
        }
        return allNotes;
    }

    @Override
    public double getCustomizationFee() { return 0; }

    @Override
    public int getPreparationTime() {
        return contents.stream().mapToInt(MenuItem::getPreparationTime).max().orElse(0);
    }
}