
import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CourseManager {
    private final ArrayList<Course> courses;

    public CourseManager() {
        courses = new ArrayList<>();
    }

    public void add(ArrayList<Category> categories,ArrayList<Name> names, Scanner scanner) {
        try {
            Name name = getNameByIndex(names, scanner);
            System.out.println("Enter price: ");
            Double price = Double.parseDouble(scanner.nextLine());
            System.out.println("Enter quantity: ");
            Integer quantity = Integer.parseInt(scanner.nextLine());
            System.out.println("Enter category: ");
            Category category = getCategoryByIndex(categories, scanner);
            courses.add(new Course(name, price, quantity, category));
            writeFile();
        } catch (NumberFormatException | InputMismatchException e) {
            System.err.println(e.getMessage());
        }
    }

    public void update(ArrayList<Category> categories,ArrayList<Name> names, Scanner scanner) {
        try {
            System.out.println("Enter the courseID you want to update: ");
            Long id = Long.parseLong(scanner.nextLine());
            Course courseUpdate;
            if ((courseUpdate = checkExist(id)) != null) {
                System.out.println("Enter new name: ");
                Name name;
                if ((name = getNameByIndex(names, scanner)) != null) {
                    courseUpdate.setName(name);
                }
                System.out.println("Enter new price: ");
                String price = scanner.nextLine();
                if (!price.equals("")) {
                    courseUpdate.setPrice(Double.parseDouble(price));
                }
                System.out.println("Enter new quantity:");
                String quantity = scanner.nextLine();
                if (!quantity.equals("")) {
                    courseUpdate.setQuantity(Integer.parseInt(quantity));
                }
                System.out.println("Enter new category: ");
                Category category;
                if ((category = getCategoryByIndex(categories, scanner)) != null) {
                    courseUpdate.setCategory(category);
                }
                writeFile();
            } else {
                System.err.println("There are no course belong this ID");
            }
        } catch (NumberFormatException | InputMismatchException e) {
            System.err.println(e.getMessage());
        }
    }

    public void delete(Scanner scanner) {
        try {
            System.out.println("Enter the courseID you want to delete: ");
            Long id = Long.parseLong(scanner.nextLine());
            Course courseDelete;
            if ((courseDelete = checkExist(id)) != null) {
                courses.remove(courseDelete);
                writeFile();
            } else {
                System.err.println("There are no course belong this ID");
            }
        } catch (NumberFormatException | InputMismatchException e) {
            System.err.println(e.getMessage());
        }
    }

    public void display() {
        System.out.printf("%-10s%-35s%-15s%-20s%s", "ID", "Name", "Price", "Quantity", "Category\n");
        for (Course course : courses) {
            course.display();
        }
    }

    public void displayById(Scanner scanner) {
        try {
            System.out.println("Enter the courseID you want to display: ");
            Long id = Long.parseLong(scanner.nextLine());
            Course course;
            if ((course = checkExist(id)) != null) {
                System.out.println(course);
            } else {
                System.err.println("There are no course belong this ID");
            }
        } catch (NumberFormatException | InputMismatchException e) {
            System.err.println(e.getMessage());
        }
    }

    public void displayByCategory(ArrayList<Category> categories, Scanner scanner) {
        System.out.println("Enter the category you want to display: ");
        Category category = getCategoryByIndex(categories, scanner);
        if (category != null) {
            for (Course course : courses) {
                if (course.getCategory().equals(category)) {
                    System.out.println(course);
                }
            }
        } else {
            System.err.println("There are no course belong this ID");
        }
    }

    public void sumPriceBill() {
        Double sum = 0.0;
        for (Course course : courses) {
            sum += course.getPrice();
        }
        System.out.println("The total amount of the bill is :" + sum);
    }

//    public void displayCourseByNameContaining(Scanner scanner) {
//        System.out.println("Enter character you want search: ");
//        String search = scanner.nextLine();
//        System.out.println("List course have name contains " + search + ": ");
//        for (Course course : courses) {
//            if (course.getName().contains(search)) {
//                System.out.println(course);
//            }
//        }
//    }

    private Category getCategoryByIndex(ArrayList<Category> categories, Scanner scanner) {
        for (int i = 0; i < categories.size(); i++) {
            System.out.println((i + 1) + ". " + categories.get(i).getName());
        }
        System.out.println("O. No choice!");
        int choice;
        try {
            do {
                System.out.println("Enter your choice: ");
                choice = Integer.parseInt(scanner.nextLine());
                if (choice == 0) {
                    return null;
                }
                if (choice > 0 && choice <= categories.size()) {
                    return categories.get(choice - 1);
                }
                System.err.println("Please re-enter your selection!");
            } while (choice < 0 || choice > categories.size());
        } catch (InputMismatchException | NumberFormatException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    private Name getNameByIndex(ArrayList<Name> names, Scanner scanner) {
        for (int i = 0; i < names.size(); i++) {
            System.out.println((i + 1) + ". " + names.get(i).getName());
        }
        System.out.println("0. No choice!");
        int choice;
        try {
            do {
                System.out.println("Enter your choice: ");
                choice = Integer.parseInt(scanner.nextLine());
                if (choice == 0) {
                    return null;
                }
                if (choice > 0 && choice <= names.size()) {
                    return names.get(choice - 1);
                }
                System.err.println("Please re-enter your selection!");
            } while (choice < 0 && choice > names.size());
        } catch (InputMismatchException | NumberFormatException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    private Course checkExist(Long id) {
        for (Course course : courses) {
            if (course.getId().equals(id)) {
                return course;
            }
        }
        return null;
    }

    public void writeFile() {
        File file = new File("CaseStudy_Module2/src/course.txt");
        try (ObjectOutputStream objectOutputStream
                     = new ObjectOutputStream(new FileOutputStream(file))) {
            objectOutputStream.writeObject(courses);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public ArrayList<Course> readFile() {
        File file = new File("CaseStudy_Module2/src/course.txt");
        ArrayList<Course> courseArrayList = new ArrayList<>();
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            if (fileInputStream.available() > 0) {
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                courseArrayList = (ArrayList<Course>) objectInputStream.readObject();
            }
            return courseArrayList;
        } catch (IOException | ClassNotFoundException e) {
            System.err.println(e.getMessage());
        }
        return courseArrayList;
    }
}
