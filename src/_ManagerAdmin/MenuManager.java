package _ManagerAdmin;

import _Account.AccUserManager;
import _Login.Login;
import _Manager.ComputerManager;
import _Manager.ServiceManager;
import _ReadWriteFile.IOFileCSV;

import java.util.InputMismatchException;
import java.util.Scanner;


public class MenuManager {
    private final Scanner scanner = new Scanner(System.in);
    private final AccUserManager accUserManager = new AccUserManager();
    private final ComputerManager computerManager = new ComputerManager();
    private final IOFileCSV ioFileCSV = new IOFileCSV();
    private final ServiceManager serviceManager = new ServiceManager();

    public MenuManager() {
    }

    public int choiceOfAdmin() {

        System.out.println("\n                                 ꧁꧂HỆ THỐNG QUẢN LÝ QUÁN NET ꧁꧂");
        System.out.println("                                        ───────────────────────        ");
        System.out.println("┎───[QUẢN LÝ]─────────────────┬───[MÁY TÍNH]─────────────┬───[DỊCH VỤ]──────────────┬───[DOANH THU]────────────────┒");
        System.out.println("┠ 1. Hiển thị máy               5. Thay đổi giá            9. Hiển thị dịch vụ        13. Chốt doanh thu phiên     ┨");
        System.out.println("┠ 2. Chọn máy                   6. Thêm máy                10. Thêm dịch vụ           14. Doanh thu theo ngày      ┨");
        System.out.println("┠ 3. Tài khoản người dùng       7. Sửa thông tin máy       11. Sửa dịch vụ            15. Doanh thu tổng           ┨");
        System.out.println("┠ 4. Sắp xếp máy                8. Xóa máy                 12. Xóa dịch vụ            0. Đăng xuất                 ┨");
        System.out.println("┖─────────────────────────────┴──────────────────────────┴──────────────────────────┴──────────────────────────────┚");
        System.out.print("[\uD83D\uDEAC] Nhập lựa chọn: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        return choice;
    }

    public void menuManager() {
        try {
            int choice;
            do {
                choice = choiceOfAdmin();
                switch (choice) {
                    case 1:
                        computerManager.displayComputer();
                        break;
                    case 2:
                        computerManager.statusComputer();
                        break;
                    case 3:
                        accUserManager.accUserManager();
                        break;
                    case 4:
                        computerManager.sortManager();
                        break;
                    case 5:
                        computerManager.setupPriceOfTime();
                        break;
                    case 6:
                        computerManager.createComputer();
                        break;
                    case 7:
                        System.out.print("[\uD83D\uDEAC] Nhập tên máy mốn sửa: ");
                        int editName = scanner.nextInt();
                        computerManager.editComputer(editName);
                        break;
                    case 8:
                        System.out.print("[\uD83D\uDEAC] Nhập tên máy muốn xóa: ");
                        int deleteName = scanner.nextInt();
                        computerManager.deleteComputer(deleteName);
                        break;
                    case 9:
                        serviceManager.display();
                        break;
                    case 10:
                        serviceManager.create();
                        break;
                    case 11:
                        System.out.print("[\uD83D\uDEAC] Nhập tên dịch vụ muốn sửa: ");
                        String serEdit = scanner.nextLine();
                        serviceManager.edit(serEdit);
                        break;
                    case 12:
                        System.out.print("[\uD83D\uDEAC] Nhập tên dịch vụ muốn xóa: ");
                        String serDel = scanner.nextLine();
                        serviceManager.delete(serDel);
                        break;
                    case 13:
                        ioFileCSV.writeTurnOver();
                        break;
                    case 14:
                        ioFileCSV.turnOverByDay();
                        break;
                    case 15:
                        ioFileCSV.turnOverAll();
                        break;
                    case 0:
                        System.out.println("[\uD83D\uDD14] Đăng xuất thành công");
                        (new Login()).loginSystems();
                        break;
                }
            } while (choice != 0);
        } catch (InputMismatchException | NumberFormatException e) {
            System.out.println();
            System.out.println("[\uD83D\uDD14] Nhập sai dữ liệu! Vui lòng nhập lại");
            System.out.println();
            menuManager();
        }
    }
}