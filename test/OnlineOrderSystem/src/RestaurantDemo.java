import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

public class RestaurantDemo {
    public static void main(String[] args) {
        // 強制 Scanner 使用 UTF-8 讀取，解決部分亂碼問題
        Scanner sc = new Scanner(System.in, "UTF-8");
        ArrayList<MenuItem> orderList = new ArrayList<>();

        System.out.print("是否有會員( y / n ): ");
        boolean isMember = sc.next().equalsIgnoreCase("y");

        boolean shopping = true;
        while (shopping) {
            System.out.println("\n1.大麥克----100元\n2.麥香雞----60元\n3.麥香魚----55元\n4.麥克雞塊--90元");
            System.out.print("選擇訂購項目: ");
            String name = sc.next();
            double price = (name.equals("大麥克")) ? 100 : (name.equals("麥香雞") ? 60 : (name.equals("麥香魚") ? 55 : 90));

            MainDish mainDish = new MainDish(name, price);
            MenuItem currentItem = mainDish;

            System.out.print("是否需要加100元加購套餐( y / n ): ");
            if (sc.next().equalsIgnoreCase("y")) {
                System.out.println("1.大薯\n2.雞塊(+10元)\n3.小薯(-30元)");
                System.out.print("請選擇配餐: ");
                String sideName = sc.next();
                double sidePrice = 100 + (sideName.equals("雞塊") ? 10 : (sideName.equals("小薯") ? -30 : 0));
                
                System.out.println("1.紅茶\n2.雪碧\n3.可樂");
                System.out.print("請選擇飲料: ");
                String bevName = sc.next();

                MainDish side = new MainDish(sideName, sidePrice);
                Beverage bev = new Beverage(bevName, 0);
                currentItem = new ComboMeal(name + "+套餐", Arrays.asList(mainDish, side, bev));
            }

            System.out.print("是否需要餐點客製化( y / n ): ");
            if (sc.next().equalsIgnoreCase("y")) {
                System.out.print("請輸入需求: ");
                sc.nextLine(); // 緩衝
                String note = sc.nextLine();
                if (currentItem instanceof Customizable) {
                    ((Customizable) currentItem).applyCustomization(note);
                }
            }

            orderList.add(currentItem);
            System.out.print("是否要繼續點餐( y / n ): ");
            shopping = sc.next().equalsIgnoreCase("y");
        }

        renderInvoice(orderList, isMember);
        sc.close();
    }

    private static void renderInvoice(ArrayList<MenuItem> list, boolean isMember) {
        System.out.println("\n==========================");
        System.out.println("訂單明細");
        double total = 0;
        for (MenuItem item : list) {
            double p = item.calculatePrice();
            System.out.printf("%-15s %10.0f\n", item.getName(), p);
            if (item instanceof Customizable) {
                for (String note : ((Customizable) item).getCustomizations()) {
                    System.out.println("        " + note);
                }
            }
            total += p;
        }
        System.out.println("==========================");
        System.out.println("總計: " + (int)total);

        double finalTotal = total;
        if (isMember) {
            finalTotal *= 0.95;
            System.out.println("會員折扣(95折)");
        }
        if (finalTotal > 250) {
            finalTotal -= 10;
            System.out.println("訂單超過250元折10元");
        }

        if (isMember || total > 250) {
            System.out.println("折扣後總計: " + (int)finalTotal);
        }
        System.out.println("===========================");
    }
}