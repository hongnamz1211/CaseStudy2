import _Service.ServiceManager;

import java.util.Scanner;

public class TestService {
    private static final ServiceManager serviceManager = new ServiceManager();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("1. create");
            System.out.println("2. display");
            System.out.println("3. delete");
            System.out.println("4. edit");
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    serviceManager.createService();
                    System.out.println("-----");
                    System.out.println("Bảng dịch vụ");
                    serviceManager.displayService();
                    System.out.println("-----");
                    break;
                case 2:
                    serviceManager.displayService();
                    break;
                case 3:
                    System.out.println("-----");
                    System.out.println("Bảng dịch vụ");
                    serviceManager.displayService();
                    System.out.println("Nhập tên");
                    String name = scanner.nextLine();
                    serviceManager.deleteService(name);
                    break;
                case 4:
                    System.out.println("-----");
                    System.out.println("Bảng dịch vụ");
                    serviceManager.displayService();
                    System.out.println("Nhập tên");
                    String name4 = scanner.nextLine();
                    serviceManager.editService(name4);
                    break;
            }
        } while (choice != 0);
    }
}
