package _Login;

import _Account.AccAdminManager;
import _Account.AccountAdmin;
import _ManagerAdmin.MenuManager;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Login {
    private final Scanner scanner = new Scanner(System.in);
    private final MenuManager menuManager = new MenuManager();
    private final AccAdminManager accAdminManager = new AccAdminManager();

    public Login() {
    }

    public void loginSystems() {
        try {
            addAccount();
            menuLogin();
        } catch (InputMismatchException | NullPointerException e) {
            System.out.println();
            System.out.println("[\uD83D\uDD14] Nhập sai dữ liệu! Vui lòng nhập lại");
            System.out.println();
            scanner.nextLine();
            loginSystems();
        }
    }

    public void menuLogin() {
        System.out.println();
        System.out.println("┎─────[HỆ THỐNG QUẢN LÝ QUÁN NET]─────┒");
        System.out.println("┠     1. Đăng nhập                    ┨");
        System.out.println("┠     2. Đăng ký                      ┨");
        System.out.println("┠     0. Thoát chương trình           ┨");
        System.out.println("┖─────────────────────────────────────┚");
        System.out.print("[\uD83D\uDEAC] Nhập lựa chọn: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        do {
            if (choice < 0 || choice > 2) {
                System.out.println("[\uD83D\uDD14] Lựa chọn không tồn tại! Vui lòng nhập lại");
                break;
            }
            switch (choice) {
                case 1:
                    signInManager();
                    break;
                case 2:
                    System.out.println("┎─────[XÁC NHẬN ĐIỆN THOẠI]───────────┒");
                    System.out.println("┠ ▹ Nhập số điện thoại: ");
                    String phoneNumber = scanner.nextLine();
                    System.out.println("┖─────────────────────────────────────┚");
                    signUpManager(phoneNumber);
                    break;
                case 0:
                    System.exit(0);
                    break;
            }
        }  while (true) ;
    }

    private void addAccount() {
        writeAccountAdmin("admin", "12345", "0868886855");
    }

    private void signInManager() {
        System.out.println("┎─────[ĐĂNG NHẬP]─────────────────────┒");
        System.out.print("┠ ▹ Nhập tài khoản: ");
        String adminAcc = scanner.nextLine();
        System.out.print("┠ ▹ Nhập mật khẩu: ");
        String adminPass = scanner.nextLine();
        System.out.println("┖─────────────────────────────────────┚");

        checkAcc(adminAcc, adminPass);
    }

    private void signUpManager(String phoneNumber) {
        System.out.println("┎─────[ĐĂNG KÝ]───────────────────────┒");
        System.out.print("┠ ▹ Nhập tài khoản mới: ");
        String adminAcc = scanner.nextLine();
        System.out.print("┠ ▹ Nhập mật khẩu mới: ");
        String adminPass = scanner.nextLine();
        System.out.println("┖─────────────────────────────────────┚");
        writeAccountAdmin(adminAcc, adminPass, phoneNumber);
        checkPhoneNumber(phoneNumber);
    }

    private void checkAcc(String adminAcc, String adminPass) {
        try {
            if (checkAccAdminInLogin(adminAcc, adminPass)) {
                System.out.println();
                System.out.println("Đăng nhập thành công");
                System.out.println();
                menuManager.menuManager();
            } else {
                System.out.println("[\uD83D\uDD14] Sai thông tin đăng nhập! Vui lòng nhập lại!");
                menuLogin();
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println();
            System.out.println("[\uD83D\uDD14] Nhập sai dữ liệu! Vui lòng nhập lại");
            System.out.println();
            menuLogin();
        }
    }

    private void checkPhoneNumber(String phoneNumber) {
        try {
            if (checkPhoneNumberAdmin(phoneNumber)) {
                System.out.println();
                System.out.println("[\uD83D\uDD14] Cập nhật tài khoản thành công! Vui lòng đăng nhập lại");
                System.out.println();
                menuLogin();
            } else {
                System.out.println("[\uD83D\uDD14] Sai thông tin số điện thoại! Vui lòng nhập lại!");
                menuLogin();
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println();
            System.out.println("[\uD83D\uDD14] Nhập sai dữ liệu! Vui lòng nhập lại");
            System.out.println();
            menuLogin();
        }
    }

    private boolean checkAccAdminInLogin(String adminAcc, String adminPass) {
        for (AccountAdmin a :
                accAdminManager.getAccountAdmins()) {
            boolean checkAccount = adminAcc.equals(a.getAdminAcc()) && adminPass.equals(a.getAdminPass());
            if (checkAccount) {
                return true;
            }
        }
        return false;
    }

    private boolean checkPhoneNumberAdmin(String phoneNumber) {
        for (AccountAdmin a :
                accAdminManager.getAccountAdmins()) {
            boolean checkPhoneNumber = phoneNumber.equals(a.getPhoneNumber());
            if (checkPhoneNumber) {
                return true;
            }
        }
        return false;
    }

    private void writeAccountAdmin(String adminAcc, String adminPass, String phoneNumber) {
        accAdminManager.setListAdmin(adminAcc, adminPass, "0868886855");
    }
}