package _Account;

import _ReadWriteFile.IOFile;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class AccUserManager {
    private final Scanner scanner = new Scanner(System.in);
    private final ArrayList<AccountUser> accountUsers;
    private final AccountUser accountUser = new AccountUser();

    private final IOFile<AccountUser> ioFile = new IOFile<>();
    private final File PATHNAME_OF_ACC_USER = new File("src/File/accUser");

    public AccUserManager() {
        if (PATHNAME_OF_ACC_USER.length() == 0) {
            this.accountUsers = new ArrayList<>();
        } else {
            this.accountUsers = readFileData();
        }
    }

    public void writeFileData(ArrayList<AccountUser> accountUsers) {
        ioFile.writerFileData(accountUsers, PATHNAME_OF_ACC_USER);
    }

    public ArrayList<AccountUser> readFileData() {
        return ioFile.readFileData(PATHNAME_OF_ACC_USER);
    }

    public ArrayList<AccountUser> display() {
        return readFileData();
    }

    public void accUserManager() {
        int choice;
        do {
            System.out.println("Quản lý tài khoản người dùng");
            System.out.println("1. Hiển thị danh sách tài khoản");
            System.out.println("2. Thêm tài khoản");
            System.out.println("3. Sửa tài khoản");
            System.out.println("4. Xóa tài khoản");
            System.out.println("0. Quay lại");
            System.out.println("-----");
            System.out.println("Nhập lựa chọn");
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    displayAccUser();
                    break;
                case 2:
                    createAccUser();
                    break;
                case 3:
                    System.out.println("Nhập tên tài khoản muốn sửa");
                    String accEdit = scanner.nextLine();
                    editAccUser(accEdit);
                    break;
                case 4:
                    System.out.println("Nhập tên tài khoản muốn xóa");
                    String accDel = scanner.nextLine();
                    deleteAccUser(accDel);
                    break;
            }
        } while (choice != 0);
    }

    public AccountUser createAccUser() {
        System.out.println("Nhập tài khoản");
        String userAcc = scanner.nextLine();
        for (AccountUser a :
                accountUsers) {
            if (a.getUserAcc().equals(userAcc)) {
                System.out.println("Trùng tên tài khoản! Vui lòng nhập lại");
                return null;
            }
        }
        System.out.println("Nhập mật khẩu");
        String userPass = scanner.nextLine();
        AccountUser accountUser = new AccountUser(userAcc, userPass);
        accountUsers.add(accountUser);
        writeFileData(accountUsers);
        System.out.println("-----");
        System.out.println("Thêm tài khoản '" + userAcc + "' thành công");
        displayAccUser();
        return accountUser;
    }

    public void displayAccUser() {
        writeFileData(accountUsers);
        System.out.println("-----");
        System.out.println("Danh sách tài khoản");
        ArrayList<AccountUser> accountUsers = display();
        accountUser.displayBored();
        for (AccountUser a :
                accountUsers) {
            a.display();
        }
        System.out.println("-----");
    }

    public void deleteAccUser(String userAcc) {
        AccountUser accountUser = null;
        for (AccountUser a :
                accountUsers) {
            if (a.getUserAcc().equals(userAcc)) {
                accountUser = a;
            }
        }
        if (accountUser != null) {
            accountUsers.remove(accountUser);
            writeFileData(accountUsers);
            System.out.println("Xóa tài khoản '" + userAcc + "' thành công");
            displayAccUser();
        }
    }

    public void editAccUser(String editUserAcc) {
        AccountUser accountUser = null;
        for (AccountUser a :
                accountUsers) {
            if (a.getUserAcc().equals(editUserAcc)) {
                accountUser = a;
            }
        }
        if (accountUser != null) {
            int index = accountUsers.indexOf(accountUser);
            System.out.println("Nhập tài khoản");
            String userAcc = scanner.nextLine();
            for (AccountUser a :
                    accountUsers) {
                if (a.getUserAcc().equals(userAcc)) {
                    System.out.println("Trùng tên tài khoản! Vui lòng nhập lại");
                    break;
                }
            }
            accountUser.setUserAcc(userAcc);
            System.out.println("Nhập mật khẩu");
            String userPass = scanner.nextLine();
            accountUser.setUserPass(userPass);
            accountUsers.set(index, accountUser);
            writeFileData(accountUsers);
            System.out.println("Thay đổi tài khoản '" + userAcc + "' thành công");
            displayAccUser();
        }
    }
}
