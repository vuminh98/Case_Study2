package CourseSystem;

import Account.User;
import IOTextFile.IOTextFile;

import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CourseManager implements Serializable {
    public ArrayList<Course> courses;

    public ArrayList<User> users;

    public ArrayList<CourseUser> courseUsers;

    public IOTextFile<Course> ioTextFile1 = new IOTextFile();
    IOTextFile<User> ioTextFile = new IOTextFile();

    IOTextFile<CourseUser> ioTextFile2 = new IOTextFile();

    public CourseManager() {
        courses = ioTextFile1.readFile("CaseStudy_Module2/src/File/Cart.txt");
        courseUsers = ioTextFile2.readFile("CaseStudy_Module2/src/File/CartUser.txt");
        users = ioTextFile.readFile("CaseStudy_Module2/src/File/UserAccount.txt");
    }

    public void add(ArrayList<Category> categories, Scanner scanner) {
        try {
            System.out.println("Enter name:");
            String name = scanner.nextLine();
            System.out.println("Enter price: ");
            Double price = Double.parseDouble(scanner.nextLine());
            System.out.println("Enter quantity: ");
            Integer quantity = Integer.parseInt(scanner.nextLine());
            System.out.println("Enter category: ");
            Category category = getCategoryByIndex(categories, scanner);
            for (User user : users) {
                if (user.getRole1().getName_role().equals("USER")) {
                    courses.add(new Course(name, price, quantity, category));
                    ioTextFile1.writeFile(courses, "CaseStudy_Module2/src/File/Cart.txt");
                } else {
                    courseUsers.add(new CourseUser(name, price, quantity, category));
                    ioTextFile2.writeFile(courseUsers, "CaseStudy_Module2/src/File/CartUser.txt");
                }
            }
        } catch (NumberFormatException | InputMismatchException e) {
            System.err.println(e.getMessage());
        }
    }

    public void update(ArrayList<Category> categories, Scanner scanner) {
        try {
            System.out.println("Enter the courseID you want to update: ");
            Long id = Long.parseLong(scanner.nextLine());
            Course courseUpdate;
            if ((courseUpdate = checkExist(id)) != null) {
                System.out.println("Enter new name: ");
                String name = scanner.nextLine();
                if (!name.equals("")) {
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
                ioTextFile1.writeFile(courses, "CaseStudy_Module2/src/File/Cart.txt");
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
                ;
            } else {
                System.err.println("There are no course belong this ID");
            }
        } catch (NumberFormatException | InputMismatchException e) {
            System.err.println(e.getMessage());
        }
    }

    public void display() {
        System.out.printf("%-10s%-20s%-15s%-20s%s", "ID", "Name", "Price", "Quantity", "Category\n");
        for (Course course : courses) {
            course.display();
        }
    }

    public void displayCartUser() {
//        ioTextFile1.readFile("CaseStudy_Module2/src/File/Cart.txt");
        System.out.printf("%-10s%-20s%-15s%-20s%s", "ID", "Name", "Price", "Quantity", "Category\n");
        for (Course course : courses) {
            course.display();
        }
    }

    public void displayCartGuest() {
//        ioTextFile1.readFile("CaseStudy_Module2/src/File/CartUser.txt");
        System.out.printf("%-10s%-20s%-15s%-20s%s", "ID", "Name", "Price", "Quantity", "Category\n");
        for (CourseUser c : courseUsers) {
            c.display();
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

    public void sumPriceBillGuest() {
        System.out.printf("%-10s%-20s%-15s%-20s%s", "ID", "Name", "Price", "Quantity", "Category\n");
        for (CourseUser c : courseUsers) {
            c.display();
        }
        Double sum = 0.0;
        for (CourseUser c : courseUsers) {
            sum += c.getPrice();
        }
        System.out.println("---------------------------------------------------------------------------------");
        System.out.printf("%-10s%-20s%-15s%-20s%s", "Total", "", sum, "", "");
        System.out.println();
    }

    public void sumPriceBillUser() {
        System.out.printf("%-10s%-20s%-15s%-20s%s", "ID", "Name", "Price", "Quantity", "Category\n");
        for (Course course : courses) {
            course.display();
        }
        Double sum = 0.0;
        for (Course course : courses) {
            sum += course.getPrice();
        }
        System.out.println("---------------------------------------------------------------------------------");
        System.out.printf("%-10s%-20s%-15s%-20s%s", "Total", "", sum, "", "");
        System.out.println();
    }

    public void displayCourseByNameContaining(Scanner scanner) {
        System.out.println("Enter character you want search: ");
        String search = scanner.nextLine();
        System.out.println("List course have name contains " + search + ": ");
        for (Course course : courses) {
            if (course.getName().contains(search)) {
                System.out.println(course);
            }
        }
    }

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


    private Course checkExist(Long id) {
        for (Course course : courses) {
            if (course.getId().equals(id)) {
                return course;
            }
        }
        return null;
    }

//    public void writeFile() {
//        File file = new File("CaseStudy_Module2/src/File/course.txt");
//        try {
//            if (!file.exists()) {
//                file.createNewFile();
//            }
//            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file));
//            objectOutputStream.writeObject(courses);
//            objectOutputStream.close();
//        } catch (IOException e) {
//            System.err.println(e.getMessage());
//        }
//    }
//
//    public void  readFile() {
//        File file = new File("CaseStudy_Module2/src/File/course.txt");
//        ArrayList<Course> courseArrayList = new ArrayList<>();
//        try {
//            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file));
//            Object object;
//            while ((object = objectInputStream.readObject()) != null) {
//                courseArrayList = (ArrayList<Course>) object;
//            }
//        } catch (IOException | ClassNotFoundException e) {
//            System.out.println(e.getMessage());
//        }
//        courses = courseArrayList;
//    }

    public void resetStaticIndex() {
        if (!courses.isEmpty()) {
            Course.INDEX = courses.get(courses.size() - 1).getId();
        }
    }


}



