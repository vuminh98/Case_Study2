package Account;

import CourseSystem.CourseManager;
import CourseSystem.CourseSystem;
import IOTextFile.IOTextFile;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UserLoginManager implements Serializable {

    IOTextFile<User> ioTextFile = new IOTextFile();

    public ArrayList<User> users;


//    Scanner scanner = new Scanner(System.in);

    CourseSystem courseSystem = new CourseSystem();


    public UserLoginManager() {
        users = new ArrayList<>();
//        User admin = new User("minh", "123");
//        admin.setRole1(new Role("ADMIN"));
//        users.add(admin);
//        ioTextFile.writeFile(users, "CaseStudy_Module2/src/File/UserAccount.txt");
    }

    public void loginAccount(Scanner scanner) {
        users = ioTextFile.readFile("CaseStudy_Module2/src/File/UserAccount.txt");
        try {
            System.out.println("Enter account: ");
            String account = scanner.nextLine();
            System.out.println("Enter password: ");
            String password = scanner.nextLine();
            for (User user : users) {
                if (user.getAccount().equals(account) && user.getPassword().equals(password)) {
                    if (user.getRole1().getName_role().equals("ADMIN")) {
                        courseSystem.menuAdmin(scanner, new CourseManager());
                    } else {
                        courseSystem.menuUser(scanner, new CourseManager());
                    }
                    break;
                }
            }
        } catch (InputMismatchException e) {
            System.err.println(e.getMessage());
        }
    }


    public void registerAccount(Scanner scanner) {
        try {
            System.out.println("Enter name account: ");
            String account = scanner.nextLine();
            System.out.println("Enter password account: ");
            String password = scanner.nextLine();
            User user = new User(account, password);
            user.setRole1(new Role("USER"));
            users.add(user);
            System.out.println("Register successful!");
            ioTextFile.writeFile(users, "CaseStudy_Module2/src/File/UserAccount.txt");
        } catch (NumberFormatException | InputMismatchException e) {
            System.err.println(e.getMessage());
        }
    }

    public void registerAccountGuest(Scanner scanner) {
        try {
            System.out.println("Enter name account: ");
            String account = scanner.nextLine();
            System.out.println("Enter password account: ");
            String password = scanner.nextLine();
            User guest = new User(account, password);
            guest.setRole1(new Role("GUEST"));
            users.add(guest);
            System.out.println("Register successful!");
            ioTextFile.writeFile(users, "CaseStudy_Module2/src/File/UserAccount.txt");
        } catch (NumberFormatException | InputMismatchException e) {
            System.err.println(e.getMessage());
        }
    }
}

