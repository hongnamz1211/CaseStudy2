package _ManagerAdmin;

import _Account.AccUserManager;
import _Manager.Manager;

import java.util.InputMismatchException;
import java.util.Scanner;


public class MenuManager {
    private final Scanner scanner = new Scanner(System.in);
    private final AccUserManager accUserManager = new AccUserManager();
    private final Manager manager = new Manager();

    public MenuManager() {
    }

    public int choiceOfAdmin() {

        System.out.println("\n                                  ꧁꧂HỆ THỐNG QUẢN LÝ QUÁN NET꧁꧂");
        System.out.println("                                        ───────────────────────        ");
        System.out.println("┎───[QUẢN LÝ]─────────────────┬───[MÁY TÍNH]─────────────┬───[DỊCH VỤ]──────────────┬───[DOANH THU]────────────────┒");
        System.out.println("┠ 1. Hiển thị máy               5. Thay đổi giá            9. Hiển thị dịch vụ        13. Chốt doanh thu phiên     ┨");
        System.out.println("┠ 2. Chọn máy                   6. Thêm máy                10. Thêm dịch vụ           14. Doanh thu theo ngày      ┨");
        System.out.println("┠ 3. Tài khoản người dùng       7. Sửa thông tin máy       11. Sửa dịch vụ            15. Doanh thu tổng           ┨");
        System.out.println("┠ 4. Tài khoản quản lý          8. Xóa máy                 12. Xóa dịch vụ            0. Đăng xuất                 ┨");
        System.out.println("┖─────────────────────────────┴──────────────────────────┴──────────────────────────┴──────────────────────────────┚");
        System.out.print("[\uD83D\uDEAC] Nhập lựa chọn: ");
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
                        manager.displayComputer();
                        break;
                    case 2:
                        manager.statusComputer();
                        break;
                    case 3:
                        accUserManager.accUserManager();
                        break;
                    case 4:
//                        accountAdmin.display();
                        break;
                    case 5:
                        manager.setupPriceOfTime();
                        break;
                    case 6:
                        manager.createComputer();
                        break;
                    case 7:
                        System.out.println("Nhập tên máy mốn sửa");
                        int editName = scanner.nextInt();
                        manager.editComputer(editName);
                        break;
                    case 8:
                        System.out.println("Nhập tên máy muốn xóa");
                        int deleteName = scanner.nextInt();
                        manager.deleteComputer(deleteName);
                        break;
                    case 9:
                        manager.displayService();
                        break;
                    case 10:
                        manager.createService();
                        break;
                    case 11:
                        System.out.println("Nhập tên dịch vụ muốn sửa");
                        String serEdit = scanner.nextLine();
                        manager.editService(serEdit);
                        break;
                    case 12:
                        System.out.println("Nhập tên dịch vụ muốn xóa");
                        String serDel = scanner.nextLine();
                        manager.deleteService(serDel);
                        break;
                    case 13:
                        manager.writeTurnOver();
                        break;
                    case 14:
                        manager.turnOverByDay();
                        break;
                    case 15:
                        manager.turnOverAll();
                        break;
//                    case 0:
//                        login.menuLogin();
//                        break;
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
            menuManager();
        }
    }
}
