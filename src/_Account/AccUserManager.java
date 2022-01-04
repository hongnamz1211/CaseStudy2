//package _Account;
//
//import _ReadWriteFile.IOFile;
//
//import java.io.File;
//import java.util.ArrayList;
//import java.util.Scanner;
//
//public class AccUserManager {
//    private final Scanner scanner = new Scanner(System.in);
//    private final ArrayList<AccountUser> accountUsers;
//
//    private final IOFile<AccountUser> ioFile = new IOFile<>();
//    private final File PATHNAME_OF_ACC_USER = new File("src/File/accUser");
//
//    public AccUserManager() {
//        if (PATHNAME_OF_ACC_USER.length() == 0) {
//            this.accountUsers = new ArrayList<>();
//        } else {
//            this.accountUsers = readFileData();
//        }
//    }
//
//    public void writeFileData(ArrayList<AccountUser> accountUsers) {
//        ioFile.writerFileData(accountUsers, PATHNAME_OF_ACC_USER);
//    }
//
//    public ArrayList<AccountUser> readFileData() {
//        return ioFile.readFileData(PATHNAME_OF_ACC_USER);
//    }
//
//    public ArrayList<AccountUser> display() {
//        return readFileData();
//    }
//
//    public void addAccUser() {
//
//    }
//}
