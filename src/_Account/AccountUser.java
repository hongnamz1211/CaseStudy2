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
        System.out.println("┎───[TÀI KHOẢN]─────────┬───[MẬT KHẨU]─────────┒");
    }

    public void  display() {
        System.out.printf("%-30s%-32s\n", "┠    " + getUserAcc(), getUserPass());
    }

    public void displayBoredBot() {
        System.out.println("┖───────────────────────┴──────────────────────┚");
    }
}