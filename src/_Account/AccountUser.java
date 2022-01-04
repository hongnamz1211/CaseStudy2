package _Account;

import java.io.Serializable;

public class AccountUser implements Serializable {
    private String userAcc;
    private String userPass;

    public AccountUser() {
    }

    public AccountUser(String userAcc, String userPass) {
        this.userAcc = userAcc;
        this.userPass = userPass;
    }

    public String getUserAcc() {
        return userAcc;
    }

    public void setUserAcc(String userAcc) {
        this.userAcc = userAcc;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    public void displayBored() {
        System.out.printf("%-20S%-20S", "tài khoản", "mật khẩu");
        System.out.println();
    }

    public void display() {
        System.out.printf("%-20s%-20s\n", getUserAcc(), getUserPass());
    }

}
