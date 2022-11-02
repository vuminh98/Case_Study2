import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static ArrayList<Category> categories;
    private static ArrayList<Name> names;

    static {
        categories = new ArrayList<>();
        categories.add(new Category("Main Course"));
        categories.add(new Category("Starter"));
        categories.add(new Category("Dessert"));
        categories.add(new Category("Drink"));
    }

    static {
        names = new ArrayList<>();
        names.add(new Name("Shrimp soup with pericardia"));
        names.add(new Name("Seafood and vegetables on rice"));
        names.add(new Name("Fried rice"));
        names.add(new Name("Salad"));
        names.add(new Name("Soy cake"));
        names.add(new Name("Biscuit"));
        names.add(new Name("Ice cream"));
        names.add(new Name("Yoghurt"));
        names.add(new Name("Apple pie"));
        names.add(new Name("Wine"));
        names.add(new Name("Beer"));
        names.add(new Name("Juice"));
    }

    public static void main(String[] args) {
        CourseManager courseManager = new CourseManager();
        Scanner scanner = new Scanner(System.in);
        do {
            String format = "│ %-45s │\n";
            System.out.println("┌───────────────────────────────────────────────┐");
            System.out.println("│                     HOME PAGE                 │");
            System.out.println("├───────────────────────────────────────────────┤");
            System.out.printf(format, "▶[1]. Guest Menu.");
            System.out.printf(format, "▶[2]. Sign In.");
            System.out.printf(format, "▶[3]. Sign Up.");
            System.out.printf(format, "▶[0]. Exit.");
            System.out.println("└───────────────────────────────────────────────┘");
            System.out.println("▶ Enter your choice:");
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                if (choice < 0 && choice > 10) {
                    throw new RuntimeException();
                }
                switch (choice) {
                    case 1:
                        menuCourse(scanner, courseManager);
                        break;
                    case 2:
                        courseManager.update(categories, names, scanner);
                        break;
                    case 3:
                        courseManager.delete(scanner);
                        break;
                    case 4:
                        courseManager.display();
                        break;
                    case 5:
                        courseManager.displayById(scanner);
                        break;
                    case 6:
                        courseManager.displayByCategory(categories, scanner);
                        break;
                    case 7:
                        courseManager.sumPriceBill();
                        break;
//                    case 8:
//                        courseManager.displayCourseByNameContaining(scanner);
//                        break;
                    case 9:
                        courseManager.writeFile();
                        break;
                    case 10:
                        courseManager.readFile();
                        break;
                    case 0:
                        System.exit(0);
                }
            } catch (Exception e) {
                System.err.println("Please re-enter the selection!");
            }
        } while (true);
}

        private static void menuCourse (Scanner scanner, CourseManager courseManager ) {
            int choice2;
            do {
                String format = "│ %-45s │\n";
                System.out.println("┌-----------------------------------------------┐");
                System.out.println("│                   MENU MANAGER                │");
                System.out.println("├-----------------------------------------------┤");
                System.out.printf(format, "▶[1]. Add course .");
                System.out.printf(format, "▶[2]. Update course by id.");
                System.out.printf(format, "▶[3]. Delete course by id.");
                System.out.printf(format, "▶[4]. Display all course.");
                System.out.printf(format, "▶[5]. Display course by id.");
                System.out.printf(format, "▶[6]. Display course by category.");
                System.out.printf(format, "▶[7]. Sum price course.");
                System.out.printf(format, "▶[8]. Display all course by search name.");
                System.out.printf(format, "▶[9]. WriteFileCourse.");
                System.out.printf(format, "▶[10]. ReadFileCourse.");
                System.out.printf(format, "▶[0]. Exit.");
                System.out.println("└-----------------------------------------------┘");
                System.out.println("▶ Enter your choice:");
                choice2 = Integer.parseInt(scanner.nextLine());
                if (choice2 == 0) {
                    break;
                }

            } while (true);
        }
    }
}