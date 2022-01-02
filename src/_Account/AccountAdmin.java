package _Account;

import java.io.Serializable;
import java.util.ArrayList;

public class AccountAdmin implements Serializable {
    private String adminAcc;
    private String adminPass;
    private String phoneNumber = "0868886855";

    public AccountAdmin() {
    }

    public AccountAdmin(String adminAcc, String adminPass) {
        this.adminAcc = adminAcc;
        this.adminPass = adminPass;
    }

    public String getAdminAcc() {
        return adminAcc;
    }

    public void setAdminAcc(String adminAcc) {
        this.adminAcc = adminAcc;
    }

    public String getAdminPass() {
        return adminPass;
    }

    public void setAdminPass(String adminPass) {
        this.adminPass = adminPass;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }


    public void display() {
        System.out.println("TÀI KHOẢN ĐĂNG NHẬP ADMIN");
        System.out.printf("%-20S%-20S%-20S", "tài khoản", "mật khẩu", "số điện thoại");
        System.out.println();
        System.out.printf("%-20s%-20s%-20s", adminAcc, adminPass, getPhoneNumber());
    }

    @Override
    public String toString() {
        return "AccountAdmin{" +
                "adminAcc='" + adminAcc + '\'' +
                ", adminPass='" + adminPass + '\'' +
                '}';
    }
}
