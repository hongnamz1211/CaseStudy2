package _Account;

import _ReadWriteFile.IOFile;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class AccAdminManager {
    private final Scanner scanner = new Scanner(System.in);
    private final ArrayList<AccountAdmin> accountAdmins;

    private final IOFile<AccountAdmin> ioFile = new IOFile<>();
    private final File PATHNAME_OF_ACC_ADMIN = new File("src/File/accAdmin");

    public AccAdminManager() {
        if (PATHNAME_OF_ACC_ADMIN.length() == 0) {
            this.accountAdmins = new ArrayList<>();
        } else {
            this.accountAdmins = readFileData();
        }
    }

    public void writerFileData(ArrayList<AccountAdmin> accountAdmins) {
        ioFile.writerFileData(accountAdmins, PATHNAME_OF_ACC_ADMIN);
    }

    public ArrayList<AccountAdmin> readFileData() {
        return ioFile.readFileData(PATHNAME_OF_ACC_ADMIN);
    }

    public ArrayList<AccountAdmin> display() {
        return readFileData();
    }

    public void addAccAdmin() {
        accountAdmins.add(new AccountAdmin("admin1", "123456"));
        writerFileData(accountAdmins);
    }

    public void editAccAdmin(String phoneNumber) {
        AccountAdmin accountAdmin = null;
        for (AccountAdmin a :
                accountAdmins) {
            if (a.getPhoneNumber().equals(phoneNumber)) {
                accountAdmin = a;
            }
        }
        if (accountAdmin != null) {
            int index = accountAdmins.indexOf(accountAdmin);
            System.out.println("Nhập tên tài khoản mới");
            String adminAcc = scanner.nextLine();
            accountAdmin.setAdminAcc(adminAcc);
            System.out.println("Nhập mật khẩu mới");
            String adminPass = scanner.nextLine();
            scanner.nextLine();
            accountAdmin.setAdminPass(adminPass);
            accountAdmins.set(index, accountAdmin);
            writerFileData(accountAdmins);
            System.out.println("Cập nhật tài khoản '" + accountAdmin + "' thành công");
        }
    }

    public void displayAccAdmin() {
        ArrayList<AccountAdmin> accountAdmins = display();
        for (AccountAdmin a:
             accountAdmins) {
            a.display();
        }
    }
}
