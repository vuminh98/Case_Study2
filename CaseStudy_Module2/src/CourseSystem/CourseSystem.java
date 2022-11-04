package CourseSystem;

import Account.UserLoginManager;

import java.util.ArrayList;
import java.util.Scanner;

public class CourseSystem {

    private static ArrayList<Category> categories;

    static {
        categories = new ArrayList<>();
        categories.add(new Category("Main Course"));
        categories.add(new Category("Starter"));
        categories.add(new Category("Dessert"));
        categories.add(new Category("Drink"));
    }


    public static void main(String[] args) {
        CourseManager courseManager = new CourseManager();
        Scanner scanner = new Scanner(System.in);
        UserLoginManager userLoginManager = new UserLoginManager();
        do {
            String format = "│ %-45s │\n";
            System.out.println("┌───────────────────────────────────────────────┐");
            System.out.println("│                     HOME PAGE                 │");
            System.out.println("├───────────────────────────────────────────────┤");
            System.out.printf(format, "▶[1]. Guest Menu.");
            System.out.printf(format, "▶[2]. Login.");
            System.out.printf(format, "▶[3]. Register user.");
            System.out.printf(format, "▶[4]. Register guest.");
            System.out.printf(format, "▶[0]. Exit.");
            System.out.println("└───────────────────────────────────────────────┘");
            System.out.println("▶ Enter your choice:");
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                if (choice < 0 && choice > 5) {
                    throw new RuntimeException();
                }
                switch (choice) {
                    case 1:
                        menuGuest(scanner, courseManager);
                        break;
                    case 2:
                        userLoginManager.loginAccount(scanner);
                        break;
                    case 3:
                        userLoginManager.registerAccount(scanner);
                        break;
                    case 4:
                        userLoginManager.registerAccountGuest(scanner);
                        break;
                    case 0:
                        System.exit(0);
                }
            } catch (Exception e) {
                System.err.println("Please re-enter the selection!");
            }
        } while (true);
    }


    public void menuAdmin(Scanner scanner, CourseManager courseManager) {
        int choice3;
        do {
            String format = "│ %-45s │\n";
            System.out.println("┌-----------------------------------------------┐");
            System.out.println("│                   MENU ADMIN                  │");
            System.out.println("├-----------------------------------------------┤");
            System.out.printf(format, "▶[1]. Add course.");
            System.out.printf(format, "▶[2]. Update course by id.");
            System.out.printf(format, "▶[3]. Delete course by id.");
            System.out.printf(format, "▶[4]. Display all course.");
            System.out.printf(format, "▶[5]. Display course by id.");
            System.out.printf(format, "▶[6]. Display course by category.");
            System.out.printf(format, "▶[7]. Sum price course.");
            System.out.printf(format, "▶[8]. Display all course by search name.");
            System.out.printf(format, "▶[0]. Return.");
            System.out.println("└-----------------------------------------------┘");
            System.out.println("▶ Enter your choice:");
            choice3 = Integer.parseInt(scanner.nextLine());
            if (choice3 == 0) {
                break;
            }
            switch (choice3) {
                case 1:
                    courseManager.add(categories, scanner);
                    break;
                case 2:
                    courseManager.update(categories, scanner);
                    break;
                case 3:
                    courseManager.delete(scanner);
                    break;
//                case 4:
//                    courseManager.displayCartAdmin();
//                    break;
                case 5:
                    courseManager.displayById(scanner);
                    break;
                case 6:
                    courseManager.displayByCategory(categories, scanner);
                    break;
//                case 7:
//                    courseManager.sumPriceBill();
//                    break;
                case 8:
                    courseManager.displayCourseByNameContaining(scanner);
                    break;
            }
        } while (true);
    }

    public static void menuGuest(Scanner scanner, CourseManager courseManager) {
        int choice2;
        do {
            String format = "│ %-45s │\n";
            System.out.println("┌-----------------------------------------------┐");
            System.out.println("│                   MENU GUEST                  │");
            System.out.println("├-----------------------------------------------┤");
            System.out.printf(format, "▶[1]. Add course .");
            System.out.printf(format, "▶[2]. Delete course by id.");
            System.out.printf(format, "▶[3]. Display all course.");
            System.out.printf(format, "▶[0]. Return.");
            System.out.println("└-----------------------------------------------┘");
            System.out.println("▶ Enter your choice:");
            choice2 = Integer.parseInt(scanner.nextLine());
            if (choice2 == 0) {
                break;
            } else {
                switch (choice2) {
                    case 1:
                        courseManager.add(categories, scanner);
                        break;
                    case 2:
                        courseManager.delete(scanner);
                        break;
//                    case 3:
//                        courseManager.display();
//                        break;
                }
            }
        } while (true);
    }

    public void menuUser(Scanner scanner, CourseManager courseManager) {
        int choice1;
        do {
            String format = "│ %-45s │\n";
            System.out.println("┌-----------------------------------------------┐");
            System.out.println("│                   MENU USER                   │");
            System.out.println("├-----------------------------------------------┤");
            System.out.printf(format, "▶[1]. Payment for user.");
            System.out.printf(format, "▶[2]. Payment for guest.");
            System.out.printf(format, "▶[3]. Display bill user.");
            System.out.printf(format, "▶[4]. Display bill guest.");
            System.out.printf(format, "▶[5]. Add.");
            System.out.printf(format, "▶[0]. Back.");
            System.out.println("└-----------------------------------------------┘");
            System.out.println("▶ Enter your choice:");
            choice1 = Integer.parseInt(scanner.nextLine());
            if (choice1 == 0) {
                break;
            } else {
                switch (choice1) {
                    case 1:
                        courseManager.sumPriceBillUser();
                        break;
                    case 2:
                        courseManager.sumPriceBillGuest();
                        break;
                    case 3:
                        courseManager.displayCartUser();
                        break;
                    case 4:
                        courseManager.displayCartGuest();
                        break;
                    case 5:
                        courseManager.add(categories, scanner);
                        break;
                }
            }
        } while (true);
    }
}