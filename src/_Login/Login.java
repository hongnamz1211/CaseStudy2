package _Login;

import _Account.AccountAdmin;
import _ManagerAdmin.MenuManager;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Login {
    private final Scanner scanner = new Scanner(System.in);
    private final ArrayList<AccountAdmin> accountAdmins = new ArrayList<>();
    private final MenuManager menuManager = new MenuManager();


    public Login() {
    }

    public void loginSystems() {
        try {
            menuLogin();
        } catch (InputMismatchException e) {
            System.out.println();
            System.out.println("Nhập sai dữ liệu! Vui lòng nhập lại");
            System.out.println();
            scanner.nextLine();
            loginSystems();
        }
    }

    private void menuLogin() {
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


    private void loginManager() {
        System.out.println("ĐĂNG NHẬP HỆ THỐNG QUẢN LÝ");
        System.out.println("Nhập tài khoản");
        String adminAcc = scanner.nextLine();
        System.out.println("Nhập mật khẩu");
        String adminPass = scanner.nextLine();
        checkAcc(adminAcc, adminPass);
    }

    private void forgotPassword(String phoneNumber) {
        AccountAdmin accountAdmin = null;
        for (AccountAdmin a :
                accountAdmins) {
            if (a.getPhoneNumber().equals(phoneNumber)) {
                accountAdmin = a;
            }
        }
        if (accountAdmin != null) {
            int index = accountAdmins.indexOf(accountAdmin);
            System.out.println("Nhập tài khoản mới");
            String adminAcc = scanner.nextLine();
            accountAdmin.setAdminAcc(adminAcc);
            System.out.println("Nhập mật khẩu mới");
            String adminPass = scanner.nextLine();
            scanner.nextLine();
            accountAdmin.setAdminPass(adminPass);
            accountAdmins.set(index,accountAdmin);
            System.out.println("Cập nhật tài khoản thành công");
        } else {
            System.out.println("Sai số điện thoại! Vui lòng nhập lại");
            menuLogin();
        }

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
            System.out.println("Đăng nhập thất bại! Vui lòng kiểm tra lại");
            System.out.println();
            menuLogin();
        }
    }

    private boolean checkAccAdminInLogin(String adminAcc, String adminPass) {
        for (AccountAdmin a :
                accountAdmins) {
            boolean checkAccount = adminAcc.equals(a.getAdminAcc()) && adminPass.equals(a.getAdminPass());
            if (checkAccount) {
                return true;
            }
        }
        return false;
    }
}
