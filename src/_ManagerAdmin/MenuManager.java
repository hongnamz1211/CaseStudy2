package _ManagerAdmin;

import _Account.AccUserManager;
import _Computer.Computer;
import _Computer.ComputerManager;
import _Login.Login;
import _Service.ServiceManager;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuManager {
    private final Scanner scanner = new Scanner(System.in);
    private final ComputerManager computerManager = new ComputerManager();
    private final Computer computer = new Computer();
    private final ServiceManager serviceManager = new ServiceManager();
    private final AccUserManager accUserManager = new AccUserManager();
//    private final Login login = new Login();

    public MenuManager() {
    }

    public int choiceOfAdmin() {
        System.out.println("HỆ THỐNG QUẢN LÝ QUÁN NET");
        System.out.printf("%-25S%-25S%-25S%-25S", "quản lý", "máy tính", "dịch vụ", "tài khoản người dùng");
        System.out.printf("\n%-25s%-25s%-25s%-25s", "1. Restart System", "5. Thay đổi giá", "9. Hiển thị dịch vụ", "13. Tài khoản người dùng");
        System.out.printf("\n%-25s%-25s%-25s%-25s", "2. Chọn máy", "6. Thêm máy", "10. Thêm dịch vụ", "14. Tài khoản quản lý");
        System.out.printf("\n%-25s%-25s%-25s%-25s", "3. Doanh thu theo ngày", "7. Sửa thông tin máy", "11. Sửa thông tin dịch vụ", "0. Đăng xuất");
        System.out.printf("\n%-25s%-25s%-25s", "4. Doanh thu tổng", "8. Xóa máy", "12. Xóa dịch vụ");
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
                    case 1:
                        computerManager.displayComputer();
                        break;
                    case 2:
                        computerManager.statusComputer();
                        break;
                    case 3:
                        break;
                    case 4:
                        System.out.println("Tổng doanh thu của phòng máy: " + computerManager.getTurnOver());
                        break;
                    case 5:
                        computerManager.setupPriceOfTime();
                        break;
                    case 6:
                        computerManager.createComputer();
                        break;
                    case 7:
                        System.out.println("Nhập tên máy mốn sửa");
                        int editName = scanner.nextInt();
                        computerManager.editComputer(editName);
                        break;
                    case 8:
                        System.out.println("Nhập tên máy muốn xóa");
                        int deleteName = scanner.nextInt();
                        computerManager.deleteComputer(deleteName);
                        break;
                    case 9:
                        serviceManager.displayService();
                        break;
                    case 10:
                        serviceManager.createService();
                        break;
                    case 11:
                        System.out.println("Nhập tên dịch vụ muốn sửa");
                        String serEdit = scanner.nextLine();
                        serviceManager.editService(serEdit);
                        break;
                    case 12:
                        System.out.println("Nhập tên dịch vụ muốn xóa");
                        String serDel = scanner.nextLine();
                        serviceManager.deleteService(serDel);
                        break;
                    case 13:
                        accUserManager.accUserManager();
                        break;
                    case 14:
//                        accUserManager.accAdminManager();
                        break;
                    case 0:
                        System.exit(0);
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
