package _Manager;

import _Computer.Computer;
import _ReadWriteFile.IOFile;
import _Computer.Service;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ComputerManager {
    private static int turnOver = 0;
    private final Scanner scanner = new Scanner(System.in);
    private final ArrayList<Computer> computers = new ArrayList<>();

    private final Computer computer = new Computer();
    private final ServiceManager serviceManager = new ServiceManager();

    private final IOFile<Computer> ioFileComputer = new IOFile<>();
    private final IOFile<Service> ioFileService = new IOFile<>();
    private final File PATHNAME_OF_COMPUTER = new File("src/File/computer");
    private final File PATHNAME_OF_SERVICE = new File("src/File/service");

    private static Pattern patternNumber;
    private static final String REGEX_NUMBER = "^(\\d+)$";

    public ComputerManager() {
        patternNumber = Pattern.compile(REGEX_NUMBER);
    }

    private boolean validateNumber(String regex) {
        Matcher matcher = patternNumber.matcher(regex);
        return matcher.matches();
    }

    public void createComputer() throws NumberFormatException {
        int computerName;
        boolean checkComputer = false;
        do {
            System.out.print("[\uD83D\uDEAC] Nhập số máy thêm: ");
            computerName = Integer.parseInt(scanner.nextLine());
        } while (!validateNumber(String.valueOf(computerName)));
        for (Computer c :
                computers) {
            if (c.getComputerName() == computerName) {
                System.out.println("[\uD83D\uDD14] Đã có số máy này! Vui lòng nhập lại");
                checkComputer = true;
                break;
            }
        }
        if (!checkComputer) {
            computers.add(new Computer(computerName));
            ioFileComputer.writerFileData(computers, PATHNAME_OF_COMPUTER);
            System.out.println("-----");
            System.out.println("[\uD83D\uDD14] Thêm máy số '" + computerName + "' thành công");
            displayComputer();
        }
    }

    public void displayComputer() {
        if (PATHNAME_OF_COMPUTER.length() == 0) {
            System.out.println("[\uD83D\uDD14] Chưa có máy nào! Vui lòng thêm máy!");
        } else {
            ioFileComputer.writerFileData(computers, PATHNAME_OF_COMPUTER);
            System.out.println("-----");
            System.out.println("DANH SÁCH MÁY");
            System.out.println("┎───[TÊN MÁY]────────┬───[TRẠNG THÁI]────────┬───[THỜI GIAN]────────┬───[GIÁ / GIỜ]────────┬───[DỊCH VỤ]────────┬───[TỔNG TIỀN]────────┒");
            for (Computer c :
                    computers) {
                System.out.printf("%1S%30S%22S%22S%22S%22s\n", "┠    " + c.getComputerName(), c.getStatus(), c.getUsedTime(), c.getPriceOfTime(), c.getPriceOfService(), payment(c.getComputerName()));
            }
            System.out.println("┖────────────────────┴───────────────────────┴──────────────────────┴──────────────────────┴────────────────────┴──────────────────────┚");
            System.out.println("-----");
        }
    }

    public void deleteComputer(int computerName) {
        Computer computer = null;
        for (Computer c :
                computers) {
            if (c.getComputerName() == computerName) {
                computer = c;
            }
        }
        if (computer != null) {
            computers.remove(computer);
            ioFileComputer.writerFileData(computers, PATHNAME_OF_COMPUTER);
            System.out.println("[\uD83D\uDD14] Xóa máy số '" + computer.getComputerName() + "' thành công");
            displayComputer();
        }
    }

    public void editComputer(int editComputer) throws NumberFormatException {
        Computer computer = null;
        for (Computer c :
                computers) {
            if (c.getComputerName() == editComputer) {
                computer = c;
            }
        }
        if (computer != null) {
            int index = computers.indexOf(computer);
            System.out.print("[\uD83D\uDEAC] Nhập số máy mới: ");
            int computerName = scanner.nextInt();
            for (Computer c :
                    computers) {
                if (c.getComputerName() == computerName) {
                    System.out.println("[\uD83D\uDD14] Đã có số máy này! Vui lòng nhập lại!");
                    break;
                }
            }
            computer.setComputerName(computerName);
            scanner.nextLine();
            computers.set(index, computer);
            ioFileComputer.writerFileData(computers, PATHNAME_OF_COMPUTER);
            System.out.println("[\uD83D\uDD14] Cập nhật máy số '" + computer.getComputerName() + "' thành công");
            displayComputer();
        }
    }

    public void statusComputer() throws NumberFormatException {
        System.out.print("[\uD83D\uDEAC] Nhập tên máy muốn thao tác: ");
        int computerName = scanner.nextInt();
        for (Computer c : computers) {
            if (c.getComputerName() == computerName) {
                if (c.getStatus().equals("available")) {
                    computerAvailable(c.getComputerName());
                } else {
                    computerDisable(c.getComputerName());
                }
            }
        }
    }

    public void computerDisable(int computerName) throws InputMismatchException, NumberFormatException {
        for (Computer c :
                computers) {
            if (c.getComputerName() == computerName) {
                int choice;
                do {
                    System.out.println("┎─────[BẬT MÁY SỐ " + c.getComputerName() + "]──────────────────┒");
                    System.out.println("┠     1. Xác nhận                     ┨");
                    System.out.println("┠     0. Quay lại                     ┨");
                    System.out.println("┖─────────────────────────────────────┚");
                    System.out.print("[\uD83D\uDEAC] Nhập lựa chọn: ");
                    choice = scanner.nextInt();
                    scanner.nextLine();
                    switch (choice) {
                        case 1:
                            c.setStatus("available");
                            c.setUsedTime("0:00:01");
                            c.setStartUsed(new Date());
                            c.startTime();
                            ioFileComputer.writerFileData(computers, PATHNAME_OF_COMPUTER);
                            System.out.println("[\uD83D\uDD14] Bật máy số '" + c.getComputerName() + "' thành công");
                            choice = 0;
                            displayComputer();
                            break;
                        case 0:
                            choice = 0;
                            break;
                    }
                } while (choice != 0);
            }
        }
    }

    public void computerAvailable(int computers) throws InputMismatchException, NumberFormatException {
        int choice;
        do {
            System.out.println("┎─────[QUẢN LÝ MÁY SỐ " + computers + "]──────────────┒");
            System.out.println("┠     1. Thêm dịch vụ                 ┨");
            System.out.println("┠     2. Thanh toán                   ┨");
            System.out.println("┠     0. Quay lại                     ┨");
            System.out.println("┖─────────────────────────────────────┚");
            System.out.print("[\uD83D\uDEAC] Nhập lựa chọn: ");
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    addService(computers);
                    choice = 0;
                    break;
                case 2:
                    paymentComputer(computers);
                    choice = 0;
                    break;
                case 0:
                    choice = 0;
                    break;
                default:
                    System.out.println("[\uD83D\uDD14] Nhập sai dữ liệu! Vui lòng nhập lại!");
            }
        } while (choice != 0);
    }

    public void addService(int computerName) {
        boolean checkServiceName = false;
        int priceOfService = 0;
        for (Computer c :
                computers) {
            if (c.getComputerName() == computerName) {
                System.out.println("-----");
                serviceManager.display();
                System.out.print("[\uD83D\uDEAC] Nhập tên dịch vụ muốn thêm: ");
                String name = scanner.nextLine();
                ArrayList<Service> services = ioFileService.readFileData(PATHNAME_OF_SERVICE);
                for (Service s :
                        services) {
                    if (s.getServiceName().equals(name)) {
                        priceOfService += s.getPriceOfService();
                        checkServiceName = true;
                        System.out.println("Thêm '" + s.getServiceName() + "' vào máy số '" + c.getComputerName() + "' thành công");
                    }
                }
                if (!checkServiceName) {
                    System.out.println("[\uD83D\uDD14] Không tìm thấy dịch vụ! Vui lòng nhập lại!");
                }
            }
        }
        for (Computer c :
                computers) {
            if (c.getComputerName() == computerName) {
                c.setPriceOfService(c.getPriceOfService() + priceOfService);
            }
        }
        ioFileComputer.writerFileData(computers, PATHNAME_OF_COMPUTER);
        displayComputer();
    }

    public void paymentComputer(int computerName) throws InputMismatchException, NumberFormatException {
        for (Computer c :
                computers) {
            if (c.getComputerName() == computerName) {
                int choice;
                do {
                    System.out.println("┎─────[THANH TOÁN MÁY SỐ " + c.getComputerName() + "]───────────┒");
                    System.out.println("┠     1. Xác nhận                     ┨");
                    System.out.println("┠     0. Quay lại                     ┨");
                    System.out.println("┖─────────────────────────────────────┚");
                    System.out.print("[\uD83D\uDEAC] Nhập lựa chọn: ");
                    choice = scanner.nextInt();
                    scanner.nextLine();
                    switch (choice) {
                        case 1:
                            System.out.println("[\uD83D\uDD14] Thanh toán thành công '" + payment(c.getComputerName()) + "' VNĐ");
                            turnOver += payment(c.getComputerName());
                            c.stopTime();
                            c.setMinute(0);
                            c.setStatus("disable");
                            c.setPriceOfService(0);
                            c.setEndUsed(new Date());
                            ioFileComputer.writerFileData(computers, PATHNAME_OF_COMPUTER);
                            displayComputer();
                            choice = 0;
                            break;
                    }
                } while (choice != 0);
            }
        }
    }

    public int getTurnOver() {
        return turnOver;
    }

    public int payment(int computerName) {
        for (Computer c :
                computers) {
            if (c.getComputerName() == computerName) {
                return (c.getMinute() * (c.getPriceOfTime() / 60) + c.getPriceOfService());
            }
        }
        return 0;
    }

    public void setupPriceOfTime() {
        if (checkComputerDisable()) {
            int choice;
            do {
                System.out.println("┎─────[THAY ĐỔI GIÁ TIỀN]─────────────┒");
                System.out.println("┠     1. Thay đổi một máy             ┨");
                System.out.println("┠     2. Thay đổi tất cả máy          ┨");
                System.out.println("┠     0. Quay lại                     ┨");
                System.out.println("┖─────────────────────────────────────┚");
                System.out.print("[\uD83D\uDEAC] Nhập lựa chọn: ");
                choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice) {
                    case 1:
                        setPriceOfTimeOneComputer();
                        choice = 0;
                        break;
                    case 2:
                        setupPriceOfTimeAllComputer();
                        choice = 0;
                        break;
                }
            } while (choice != 0);
        } else {
            System.out.println("[\uD83D\uDD14] Vui lòng tắt hết máy trước khi thay đổi");
        }
    }

    public void setPriceOfTimeOneComputer() {
        int choice;
        boolean checkComputerName = false;
        do {
            System.out.println("-----");
            System.out.println("THAY ĐỔI GIÁ 1 MÁY");
            System.out.print("[\uD83D\uDEAC] Nhập số máy: ");
            int computerNumber = scanner.nextInt();
            System.out.print("[\uD83D\uDEAC] Nhập giá mới: ");
            int priceOfTime = scanner.nextInt();
            System.out.println("┎─────[XÁC NHẬN THAY ĐỔI GIÁ]─────────┒");
            System.out.println("┠     1. Xác nhận                     ┨");
            System.out.println("┠     0. Quay lại                     ┨");
            System.out.println("┖─────────────────────────────────────┚");
            System.out.print("[\uD83D\uDEAC] Nhập lựa chọn: ");
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    for (Computer c :
                            computers) {
                        if (c.getComputerName() == computerNumber) {
                            c.setPriceOfTime(priceOfTime);
                            System.out.println("[\uD83D\uDD14] Thay đổi giá máy số '" + c.getComputerName() + "' thành '" + c.getPriceOfTime() + "'/1h thành công");
                            ioFileComputer.writerFileData(computers, PATHNAME_OF_COMPUTER);
                            checkComputerName = true;
                            choice = 0;
                            break;
                        }
                    }
                    if (!checkComputerName) {
                        System.out.println("[\uD83D\uDD14] Không tìm thấy số máy! Vui lòng nhập lại");
                    }
            }
        } while (choice != 0);
    }

    public void setupPriceOfTimeAllComputer() throws InputMismatchException, NumberFormatException {
        int choice;
        do {
            System.out.println("-----");
            System.out.println("THAY ĐỔI GIÁ TẤT CẢ MÁY");
            System.out.print("[\uD83D\uDEAC] Nhập giá mới: ");
            int priceOfTime = scanner.nextInt();
            System.out.println("┎─────[XÁC NHẬN THAY ĐỔI GIÁ]─────────┒");
            System.out.println("┠     1. Xác nhận                     ┨");
            System.out.println("┠     0. Quay lại                     ┨");
            System.out.println("┖─────────────────────────────────────┚");
            System.out.print("[\uD83D\uDEAC] Nhập lựa chọn: ");
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    for (Computer c :
                            computers) {
                        c.setPriceOfTime(priceOfTime);
                    }
                    computer.setPriceOfTime(priceOfTime);
                    System.out.println("[\uD83D\uDD14] Thay đổi giá tất cả máy thành '" + computer.getPriceOfTime() + "'/1h thành công");
                    ioFileComputer.writerFileData(computers, PATHNAME_OF_COMPUTER);
                    choice = 0;
                    break;
            }
        } while (choice != 0);
    }

    public boolean checkComputerDisable() {
        ArrayList<Computer> computers = ioFileComputer.readFileData(PATHNAME_OF_COMPUTER);
        for (Computer c :
                computers) {
            if (c.getStatus().equals("available")) {
                return false;
            }
        }
        return true;
    }

    public void sortManager() {
        int choice;
        do {
            System.out.println("┎─────[XÁC NHẬN THAY ĐỔI GIÁ]─────────┒");
            System.out.println("┠     1. Theo tên máy tăng dần        ┨");
            System.out.println("┠     2. Theo tên máy giảm dần        ┨");
            System.out.println("┠     3. Theo tổng tiền tăng dần      ┨");
            System.out.println("┠     4. Theo tổng tiền giảm dần      ┨");
            System.out.println("┖─────────────────────────────────────┚");
            System.out.print("[\uD83D\uDEAC] Nhập lựa chọn: ");
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    sortComputerNameUp();
                    choice = 0;
                    break;
                case 2:
                    sortComputerNameDown();
                    choice = 0;
                    break;
                case 3:
                    sortPaymentUp();
                    choice = 0;
                    break;
                case 4:
                    sortPaymentDown();
                    choice = 0;
                    break;
                case 0:
                    choice = 0;
                    break;
            }
        } while (choice != 0);
    }

    public void sortComputerNameUp() {
        computers.sort(new Comparator<Computer>() {
            @Override
            public int compare(Computer o1, Computer o2) {
                if (o1.getComputerName() > o2.getComputerName()) return 1;
                else if (o1.getComputerName() < o2.getComputerName()) return -1;
                else return 0;
            }
        });
        displayComputer();
    }

    public void sortComputerNameDown() {
        computers.sort((o1, o2) -> {
            if (o1.getComputerName() > o2.getComputerName()) return -1;
            else if (o1.getComputerName() < o2.getComputerName()) return 1;
            else return 0;
        });
        displayComputer();
    }

    public void sortPaymentUp() {
        computers.sort(new Comparator<Computer>() {
            @Override
            public int compare(Computer o1, Computer o2) {
                if (payment(o1.getComputerName()) > payment(o2.getComputerName())) return 1;
                else if (payment(o1.getComputerName()) < payment(o2.getComputerName())) return -1;
                else return 0;
            }
        });
        displayComputer();
    }

    public void sortPaymentDown() {
        computers.sort((o1, o2) -> {
            if (payment(o1.getComputerName()) > payment(o2.getComputerName())) return -1;
            else if (payment(o1.getComputerName()) < payment(o2.getComputerName())) return 1;
            else return 0;
        });
        displayComputer();
    }
}