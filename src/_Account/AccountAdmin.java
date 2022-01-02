package _Account;

import java.io.Serializable;

public class AccountAdmin implements Serializable {
    private String adminAcc;
    private String adminPass;

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

    @Override
    public String toString() {
        return "AccountAdmin{" +
                "adminAcc='" + adminAcc + '\'' +
                ", adminPass='" + adminPass + '\'' +
                '}';
    }
}
