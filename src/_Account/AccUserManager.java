package _Account;

import _Manager.ModelManager;
import _ReadWriteFile.IOFile;

import java.io.File;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class AccUserManager implements ModelManager {
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

    public ArrayList<AccountUser> displayFile() {
        return readFileData();
    }

    public void accUserManager() {
        try {
            int choice;
            do {
                System.out.println("┎─────[Quản lý tài khoản người dùng]──────────┒");
                System.out.println("┠     1. Hiển thị danh sách tài khoản         ┨");
                System.out.println("┠     2. Thêm tài khoản                       ┨");
                System.out.println("┠     3. Sửa tài khoản                        ┨");
                System.out.println("┠     4. Xóa tài khoản                        ┨");
                System.out.println("┠     0. Quay lại                             ┨");
                System.out.println("┖─────────────────────────────────────────────┚");
                System.out.print("[\uD83D\uDEAC] Nhập lựa chọn: ");
                choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice) {
                    case 1:
                        display();
                        break;
                    case 2:
                        create();
                        break;
                    case 3:
                        System.out.print("[\uD83D\uDEAC] Nhập tên tài khoản muốn sửa: ");
                        String accEdit = scanner.nextLine();
                        edit(accEdit);
                        break;
                    case 4:
                        System.out.print("[\uD83D\uDEAC] Nhập tên tài khoản muốn xóa: ");
                        String accDel = scanner.nextLine();
                        delete(accDel);
                        break;
                    case 0:
                        choice = 0;
                        break;
                }
            } while (choice != 0);
        } catch (InputMismatchException e) {
            System.out.println();
            System.out.println("[\uD83D\uDD14] Nhập sai dữ liệu! Vui lòng nhập lại");
            System.out.println();
            accUserManager();
        }

    }

//    public void createAccUser() {
//        boolean checkAccUser = false;
//        System.out.print("[\uD83D\uDEAC] Nhập tài khoản: ");
//        String userAcc = scanner.nextLine();
//        for (AccountUser a :
//                accountUsers) {
//            if (a.getUserAcc().equals(userAcc)) {
//                System.out.println("[\uD83D\uDD14] Trùng tên tài khoản! Vui lòng nhập lại");
//                checkAccUser = true;
//                break;
//            }
//        }
//        if (!checkAccUser) {
//            System.out.print("[\uD83D\uDEAC] Nhập mật khẩu: ");
//            String userPass = scanner.nextLine();
//            AccountUser accountUser = new AccountUser(userAcc, userPass);
//            accountUsers.add(accountUser);
//            writeFileData(accountUsers);
//            System.out.println("-----");
//            System.out.println("[\uD83D\uDD14] Thêm tài khoản '" + userAcc + "' thành công");
//            displayAccUser();
//        }
//
//
//    }

//    public void displayAccUser() {
//        writeFileData(accountUsers);
//        System.out.println("-----");
//        System.out.println("DANH SÁCH TÀI KHOẢN");
//        ArrayList<AccountUser> accountUsers = displayFile();
//        accountUser.displayBored();
//        for (AccountUser a :
//                accountUsers) {
//            a.display();
//        }
//        accountUser.displayBoredBot();
//        System.out.println("-----");
//    }

//    public void deleteAccUser(String userAcc) {
//        AccountUser accountUser = null;
//        for (AccountUser a :
//                accountUsers) {
//            if (a.getUserAcc().equals(userAcc)) {
//                accountUser = a;
//            }
//        }
//        if (accountUser != null) {
//            accountUsers.remove(accountUser);
//            writeFileData(accountUsers);
//            System.out.println("[\uD83D\uDD14] Xóa tài khoản '" + userAcc + "' thành công");
//            displayAccUser();
//        }
//    }

//    public void editAccUser(String editUserAcc) {
//        boolean checkAccUser = false;
//        AccountUser accountUser = null;
//        for (AccountUser a :
//                accountUsers) {
//            if (a.getUserAcc().equals(editUserAcc)) {
//                accountUser = a;
//            }
//        }
//        if (accountUser != null) {
//            int index = accountUsers.indexOf(accountUser);
//            System.out.print("[\uD83D\uDEAC] Nhập tài khoản: ");
//            String userAcc = scanner.nextLine();
//            for (AccountUser a :
//                    accountUsers) {
//                if (a.getUserAcc().equals(userAcc)) {
//                    System.out.println("[\uD83D\uDD14] Trùng tên tài khoản! Vui lòng nhập lại");
//                    checkAccUser = true;
//                    break;
//                }
//            }
//            if (!checkAccUser) {
//                accountUser.setUserAcc(userAcc);
//                System.out.print("[\uD83D\uDEAC] Nhập mật khẩu: ");
//                String userPass = scanner.nextLine();
//                accountUser.setUserPass(userPass);
//                accountUsers.set(index, accountUser);
//                writeFileData(accountUsers);
//                System.out.println("[\uD83D\uDD14] Thay đổi tài khoản '" + userAcc + "' thành công");
//                displayAccUser();
//            }
//        }
//    }

    @Override
    public void create() {
        boolean checkAccUser = false;
        System.out.print("[\uD83D\uDEAC] Nhập tài khoản: ");
        String userAcc = scanner.nextLine();
        for (AccountUser a :
                accountUsers) {
            if (a.getUserAcc().equals(userAcc)) {
                System.out.println("[\uD83D\uDD14] Trùng tên tài khoản! Vui lòng nhập lại");
                checkAccUser = true;
                break;
            }
        }
        if (!checkAccUser) {
            System.out.print("[\uD83D\uDEAC] Nhập mật khẩu: ");
            String userPass = scanner.nextLine();
            AccountUser accountUser = new AccountUser(userAcc, userPass);
            accountUsers.add(accountUser);
            writeFileData(accountUsers);
            System.out.println("-----");
            System.out.println("[\uD83D\uDD14] Thêm tài khoản '" + userAcc + "' thành công");
            display();
        }
    }

    @Override
    public void display() {
        writeFileData(accountUsers);
        System.out.println("-----");
        System.out.println("DANH SÁCH TÀI KHOẢN");
        ArrayList<AccountUser> accountUsers = displayFile();
        accountUser.displayBored();
        for (AccountUser a :
                accountUsers) {
            a.display();
        }
        accountUser.displayBoredBot();
        System.out.println("-----");
    }

    @Override
    public void delete(String deleteName) {
        AccountUser accountUser = null;
        for (AccountUser a :
                accountUsers) {
            if (a.getUserAcc().equals(deleteName)) {
                accountUser = a;
            }
        }
        if (accountUser != null) {
            accountUsers.remove(accountUser);
            writeFileData(accountUsers);
            System.out.println("[\uD83D\uDD14] Xóa tài khoản '" + deleteName + "' thành công");
            display();
        }
    }

    @Override
    public void edit(String editName) {
        boolean checkAccUser = false;
        AccountUser accountUser = null;
        for (AccountUser a :
                accountUsers) {
            if (a.getUserAcc().equals(editName)) {
                accountUser = a;
            }
        }
        if (accountUser != null) {
            int index = accountUsers.indexOf(accountUser);
            System.out.print("[\uD83D\uDEAC] Nhập tài khoản: ");
            String userAcc = scanner.nextLine();
            for (AccountUser a :
                    accountUsers) {
                if (a.getUserAcc().equals(userAcc)) {
                    System.out.println("[\uD83D\uDD14] Trùng tên tài khoản! Vui lòng nhập lại");
                    checkAccUser = true;
                    break;
                }
            }
            if (!checkAccUser) {
                accountUser.setUserAcc(userAcc);
                System.out.print("[\uD83D\uDEAC] Nhập mật khẩu: ");
                String userPass = scanner.nextLine();
                accountUser.setUserPass(userPass);
                accountUsers.set(index, accountUser);
                writeFileData(accountUsers);
                System.out.println("[\uD83D\uDD14] Thay đổi tài khoản '" + userAcc + "' thành công");
                display();
            }
        }
    }
}
