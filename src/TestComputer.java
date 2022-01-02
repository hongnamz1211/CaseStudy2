import _Computer.ComputerManager;

import java.util.Scanner;

public class TestComputer {
    private static final ComputerManager computerManager = new ComputerManager();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("Quản lý máy tính");
            System.out.println("1. Thêm máy");
            System.out.println("2. Hiển thị tất cả máy");
            System.out.println("3. xóa");
            System.out.println("4. sửa");
            System.out.println("5. thiết lập");
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    computerManager.createComputer();
                    break;
                case 2:
                    computerManager.displayComputer();
                    break;
                case 3:
                    System.out.println("Nhập tên máy muốn xóa");
                    int deleteName = scanner.nextInt();
                    computerManager.deleteComputer(deleteName);
                    break;
                case 4:
                    System.out.println("Nhập tên máy mốn sửa");
                    int editName = scanner.nextInt();
                    computerManager.editComputer(editName);
                    break;
                case 5:
                    System.out.println("Thiết lập máy");
                    computerManager.statusComputer();
                    break;
            }
        } while (choice != 0);
    }
}
