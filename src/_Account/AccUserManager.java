package _Account;

import _Manager.ModelManager;
import _ReadWriteFile.IOFile;

import java.io.File;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AccUserManager implements ModelManager {
    private final Scanner scanner = new Scanner(System.in);
    private final ArrayList<AccountUser> accountUsers;
    private final AccountUser accountUser = new AccountUser();

    private final IOFile<AccountUser> ioFile = new IOFile<>();
    private final File PATHNAME_OF_ACC_USER = new File("src/File/accUser");

    private Pattern pattern;
    private static final String REGEX_ACCOUNT = "^(?=.*[a-z])(?=.*[0-9]).{6,12}$";
    private static final String REGEX_PASS = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9]).{8,16}$";


    public AccUserManager() {
        if (PATHNAME_OF_ACC_USER.length() == 0) {
            this.accountUsers = new ArrayList<>();
        } else {
            this.accountUsers = readFileData();
        }
    }

    public boolean validateAccount(String regex) {
        pattern = Pattern.compile(REGEX_ACCOUNT);
        Matcher matcher = pattern.matcher(regex);
        return matcher.matches();
    }

    public boolean validatePass(String regex) {
        pattern = Pattern.compile(REGEX_PASS);
        Matcher matcher = pattern.matcher(regex);
        return matcher.matches();
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

    @Override
    public void create() {
        boolean checkAccUser = false;
        String userAcc;
        String userPass;
        do {
            System.out.println("6-12 ký tự (gồm a-z & 0-9)");
            System.out.print("[\uD83D\uDEAC] Nhập tài khoản: ");
            userAcc = scanner.nextLine();
        } while (!validateAccount(userAcc));
        for (AccountUser a :
                accountUsers) {
            if (a.getUserAcc().equals(userAcc)) {
                System.out.println("[\uD83D\uDD14] Trùng tên tài khoản! Vui lòng nhập lại");
                checkAccUser = true;
                break;
            }
        }
        if (!checkAccUser) {
            do {
                System.out.println("6-12 ký tự (gồm A-Z, a-z & 0-9)");
                System.out.print("[\uD83D\uDEAC] Nhập mật khẩu: ");
                userPass = scanner.nextLine();
            } while (!validatePass(userPass));
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
        String userAcc;
        String userPass;
        AccountUser accountUser = null;
        for (AccountUser a :
                accountUsers) {
            if (a.getUserAcc().equals(editName)) {
                accountUser = a;
            }
        }
        if (accountUser != null) {
            int index = accountUsers.indexOf(accountUser);
            do {
                System.out.println("6-12 ký tự (gồm a-z & 0-9)");
                System.out.print("[\uD83D\uDEAC] Nhập tài khoản: ");
                userAcc = scanner.nextLine();
            } while (!validateAccount(userAcc));
            for (AccountUser a :
                    accountUsers) {
                if (a.getUserAcc().equals(userAcc)) {
                    System.out.println("[\uD83D\uDD14] Trùng tên tài khoản! Vui lòng nhập lại");
                    checkAccUser = true;
                    break;
                }
            }
            if (!checkAccUser) {
                do {
                    System.out.println("6-12 ký tự (gồm A-Z, a-z & 0-9)");
                    System.out.print("[\uD83D\uDEAC] Nhập mật khẩu: ");
                    userPass = scanner.nextLine();
                } while (!validatePass(userPass));
                userPass = scanner.nextLine();
                accountUser.setUserPass(userPass);
                accountUsers.set(index, accountUser);
                writeFileData(accountUsers);
                System.out.println("[\uD83D\uDD14] Thay đổi tài khoản '" + userAcc + "' thành công");
                display();
            }
        }
    }
}
