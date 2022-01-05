package _Account;

import _ReadWriteFile.IOFile;

import java.io.File;
import java.util.ArrayList;

public class AccAdminManager {
    private final ArrayList<AccountAdmin> accountAdminList = new ArrayList<>();
    private final IOFile<AccountAdmin> ioFile = new IOFile<>();
    private final File PATHNAME_OF_ACC_ADMIN = new File("src/File/accAdmin");

    public AccAdminManager() {
    }

    public ArrayList<AccountAdmin> getAccountAdmins() {
        return ioFile.readFileData(PATHNAME_OF_ACC_ADMIN);
    }

    public void setListAdmin(String adminAcc, String adminPass, String phoneNumber) {
        ArrayList<AccountAdmin> accountAdmins;
        if (checkFile()) {
            accountAdmins = accountAdminList;
        } else {
            accountAdmins = getAccountAdmins();
        }
        accountAdmins.add(new AccountAdmin(adminAcc, adminPass, phoneNumber));
        ioFile.writerFileData(accountAdmins, PATHNAME_OF_ACC_ADMIN);
    }

    public boolean checkFile() {
        ArrayList<AccountAdmin> accountAdminList = getAccountAdmins();
        return accountAdminList == null;
    }
}