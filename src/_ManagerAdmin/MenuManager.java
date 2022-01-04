package _ManagerAdmin;

import _Computer.ComputerManager;
import _Service.ServiceManager;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuManager {
    private final Scanner scanner = new Scanner(System.in);
    private final ComputerManager computerManager = new ComputerManager();
    private final ServiceManager serviceManager = new ServiceManager();

    public MenuManager() {
    }

    public int choiceOfAdmin() {
        System.out.println("HỆ THỐNG QUẢN LÝ QUÁN NET");
        System.out.printf("%-25S%-25S%-25S%-25S", "quản lý", "máy tính", "dịch vụ", "tài khoản người dùng");
        System.out.printf("\n%-25s%-25s%-25s%-25s", "1. Restart System", "5. Hiển thị máy", "9. Hiển thị dịch vụ", "13. Hiển thị tài khoản");
        System.out.printf("\n%-25s%-25s%-25s%-25s", "2. Chọn máy", "6. Thêm máy", "10. Thêm dịch vụ", "14. Tạo tài khoản");
        System.out.printf("\n%-25s%-25s%-25s%-25s", "3. Doanh thu theo ngày", "7. Sửa thông tin máy", "11. Sửa thông tin dịch vụ", "15. Sửa thông tin tài khoản");
        System.out.printf("\n%-25s%-25s%-25s%-25s", "4. Doanh thu tổng", "8. Xóa máy", "12. Xóa dịch vụ", "16. Xóa tài khoản");
        System.out.print("\nNhập lựa chọn: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        return choice;
    }

    public void menuManager() {
        try {
            do {
                int choice = choiceOfAdmin();
                switch (choice) {
                    case 9:
                        serviceManager.displayService();
                        break;
                    case 10:
                        serviceManager.createService();
                        break;
                    case 11:
                        System.out.println("Nhập tên dịch vụ muốn sửa");
                        String nameEdit = scanner.nextLine();
                        serviceManager.editService(nameEdit);
                        break;
                    case 12:
                        System.out.println("Nhập tên dịch vụ muốn xóa");
                        String nameDel = scanner.nextLine();
                        serviceManager.deleteService(nameDel);
                        break;
                    default:
                        System.out.println("Lựa chọn không đúng! Vui lòng nhập lại!");
                        break;
                }
            } while (true);
        } catch (InputMismatchException e) {
            System.out.println();
            System.out.println("Nhập sai dữ liệu! Vui lòng nhập lại");
            System.out.println();
            scanner.nextLine();
        }
    }
}
