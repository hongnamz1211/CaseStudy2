package _Login;

import _Account.AccAdminManager;
import _Account.AccountAdmin;
import _ManagerAdmin.MenuManager;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Login {
    private final Scanner scanner = new Scanner(System.in);
    private final ArrayList<AccountAdmin> accountAdmins = new ArrayList<>();
    private final MenuManager menuManager = new MenuManager();
    private final AccAdminManager accAdminManager = new AccAdminManager();
    private final AccountAdmin accountAdmin = new AccountAdmin();


    public Login() {
    }


    public void loginSystems() {
        try {
            addAccount();
            menuLogin();
        } catch (InputMismatchException e) {
            System.out.println();
            System.out.println("Nhập sai dữ liệu! Vui lòng nhập lại");
            System.out.println();
            scanner.nextLine();
            loginSystems();
        }
    }

    public void menuLogin() {
//        addAccount();
        System.out.println("1. Đăng nhập");
        System.out.println("2. Quên mật khẩu");
        System.out.println("Nhập lựa chọn");
        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice) {
            case 1:
                loginManager();
                break;
            case 2:
                System.out.println("Nhập số điện thoại");
                String phoneNumber = scanner.nextLine();
                forgotPassword(phoneNumber);
                break;
        }
    }

    private void addAccount() {
        writeAccountAdmin("admin", "12345", "0868886855");
    }


    private void loginManager() {
        System.out.println("ĐĂNG NHẬP HỆ THỐNG QUẢN LÝ");
        System.out.println("Nhập tài khoản");
        String adminAcc = scanner.nextLine();
        System.out.println("Nhập mật khẩu");
        String adminPass = scanner.nextLine();
        checkAcc(adminAcc, adminPass);
    }

    private void forgotPassword(String phoneNumber) {
        System.out.println("Nhập tài khoản mới");
        String adminAcc = scanner.nextLine();
        System.out.println("Nhập mật khẩu mới");
        String adminPass = scanner.nextLine();
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
                System.out.println("Sai thông tin đăng nhập! Vui lòng nhập lại!");
                menuLogin();
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println();
            System.out.println("Nhập sai dữ liệu! Vui lòng nhập lại");
            System.out.println();
            menuLogin();
        }
    }

    private void checkPhoneNumber(String phoneNumber) {
        try {
            if (checkPhoneNumberAdmin(phoneNumber)) {
                System.out.println();
                System.out.println("Cập nhật tài khoản thành công! Vui lòng đăng nhập lại");
                System.out.println();
                loginManager();
            } else {
                System.out.println("Sai thông tin số điện thoại! Vui lòng nhập lại!");
                menuLogin();
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println();
            System.out.println("Nhập sai dữ liệu! Vui lòng nhập lại");
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