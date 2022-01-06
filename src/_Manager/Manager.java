package _Manager;

import _Computer.Computer;
import _ReadWriteFile.IOFile;
import _Computer.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Manager {
    private int turnOver = 0;
    private final Scanner scanner = new Scanner(System.in);
    private final ArrayList<Computer> computers = new ArrayList<>();

    private final Computer computer = new Computer();
    private final Service service = new Service();

    private final IOFile<Computer> ioFileComputer = new IOFile<>();
    private final IOFile<Service> ioFileService = new IOFile<>();
    private final File PATHNAME_OF_COMPUTER = new File("src/File/computer");
    private final File PATHNAME_OF_SERVICE = new File("src/File/service");
    private final File PATHNAME_OF_TURNOVER = new File("src/File/turnOver.csv");

    private ArrayList<Service> services;
    private static Pattern patternNumber;
    private static final String REGEX_NUMBER = "^(\\d+)$";

    public Manager() {
        patternNumber = Pattern.compile(REGEX_NUMBER);
        if (PATHNAME_OF_SERVICE.length() == 0) {
            this.services = new ArrayList<>();
        } else {
            this.services = ioFileService.readFileData(PATHNAME_OF_SERVICE);
        }
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
            computer.displayBored();
            for (Computer c :
                    computers) {
                c.display();
            }
            computer.displayBoredBot();
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
                    payment(computers);
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
                displayService();
                System.out.print("[\uD83D\uDEAC] Nhập tên dịch vụ muốn thêm: ");
                String name = scanner.nextLine();
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

    public void payment(int computerName) throws InputMismatchException, NumberFormatException {
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
                            System.out.println("[\uD83D\uDD14] Thanh toán thành công '" + c.payment() + "' VNĐ");
                            turnOver += c.payment();
                            c.stopTime();
                            c.setMinute(0);
                            c.setStatus("disable");
                            c.setPriceOfService(0);
                            c.setPriceOfTime(0);
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
                    }
                } while (choice != 0);
            }
        }
    }

    public void setupPriceOfTime() throws InputMismatchException, NumberFormatException {
        if (checkComputerDisable()) {
            int choice;
            do {
                System.out.print("Giá tiền 1h hiện tại: ");
                System.out.println(computer.getPriceOfTime());
                System.out.print("[\uD83D\uDEAC] Nhập giá muốn thay đổi: ");
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
                        computer.setPriceOfTime(priceOfTime);
                        System.out.println("[\uD83D\uDD14] Thay đổi giá thành '" + priceOfTime + "'/1h thành công");
                        choice = 0;
                        break;
                }
            } while (choice != 0);
        } else {
            System.out.println("[\uD83D\uDD14] Vui lòng tắt hết máy trước khi thay đổi");
        }
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

    public int getTurnOver() {
        return turnOver;
    }


    public void createService() throws NumberFormatException {
        try {
            boolean checkService = false;
            System.out.print("[\uD83D\uDEAC] Nhập tên dịch vụ: ");
            String serviceName = scanner.nextLine();
            for (Service s :
                    services) {
                if (s.getServiceName().equals(serviceName)) {
                    System.out.println("[\uD83D\uDD14] Đã có dịch vụ này! Vui lòng nhập lại");
                    checkService = true;
                    break;
                }
            }
            int priceOfService;
            if (!checkService) {
                do {
                    System.out.print("[\uD83D\uDEAC] Nhập giá dịch vụ: ");
                    priceOfService = Integer.parseInt(scanner.nextLine());
                } while (!validateNumber(String.valueOf(priceOfService)));
                services.add(new Service(serviceName, priceOfService));
                ioFileService.writerFileData(services, PATHNAME_OF_SERVICE);
                System.out.println("[\uD83D\uDD14] Thêm '" + serviceName + "' thành công");
                displayService();
            }
        } catch (Exception e) {
            System.out.println("[\uD83D\uDD14] Nhập sai dữ liệu! Vui lòng nhập lại");
        }
    }


    public void displayService() {
        if (PATHNAME_OF_SERVICE.length() == 0) {
            System.out.println("[\uD83D\uDD14] Chưa có dịch vụ nào! Vui lòng thêm dịch vụ!");
        } else {
            System.out.println("-----");
            System.out.println("BẢNG DỊCH VỤ");
            ioFileService.writerFileData(services, PATHNAME_OF_SERVICE);
            ArrayList<Service> services = ioFileService.readFileData(PATHNAME_OF_SERVICE);
            service.displayBored();
            for (Service s :
                    services) {
                s.display();
            }
            service.displayBoredBot();
            System.out.println("-----");
            ioFileService.writerFileData(services, PATHNAME_OF_SERVICE);
        }
    }

    public void deleteService(String name) {
        boolean checkServiceName = false;
        Service service = null;
        for (Service s :
                services) {
            if (s.getServiceName().equals(name)) {
                checkServiceName = true;
                service = s;
            }
        }
        if (service != null) {
            services.remove(service);
            ioFileService.writerFileData(services, PATHNAME_OF_SERVICE);
            System.out.println("[\uD83D\uDD14] Xóa '" + service.getServiceName() + "' thành công");
            displayService();
        }
        if (!checkServiceName) {
            System.out.println("[\uD83D\uDD14] Không tìm thấy dịch vụ! Vui lòng nhập lại!");
        }
    }

    public void editService(String name) {
        boolean checkService = false;
        Service service = null;
        for (Service s :
                services) {
            if (s.getServiceName().equals(name)) {
                service = s;
            }
        }
        if (service != null) {
            int index = services.indexOf(service);
            System.out.print("[\uD83D\uDEAC] Nhập tên dịch vụ mới: ");
            String serviceName = scanner.nextLine();
            for (Service s :
                    services) {
                if (s.getServiceName().equals(serviceName)) {
                    System.out.println("[\uD83D\uDD14] Đã có dịch vụ này! Vui lòng nhập lại!");
                    checkService = true;
                    break;
                }
            }
            if (!checkService) {
                service.setServiceName(serviceName);
                System.out.print("[\uD83D\uDEAC] Nhập giá dịch vụ mới: ");
                int priceOfService = scanner.nextInt();
                service.setPriceOfService(priceOfService);
                scanner.nextLine();
                services.set(index, service);
                ioFileService.writerFileData(services, PATHNAME_OF_SERVICE);
                System.out.println("[\uD83D\uDD14] Cập nhật '" + serviceName + "' thành công");
                displayService();
            }
        }
    }

    public void turnOverAll() {
        Pattern pattern = Pattern.compile("^Ngày [0-9]+/[0-9]+:(.*?)VNĐ$");
        String line = "";
        Matcher matcher;
        int turnOver = 0;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("src/File/turnOver.csv"));
            while ((line = bufferedReader.readLine()) != null) {
                matcher = pattern.matcher(line);
                while (matcher.find()) {
                    turnOver += Integer.parseInt(matcher.group(1).trim());
                }
            }
            System.out.println("┎─────[CHỐT DOANH TỔNG]───────────────────────────┒");
            System.out.println("┠ ▹ Doanh thu: " + turnOver + "VNĐ");
            System.out.println("┖─────────────────────────────────────────────────┚");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void turnOverByDay() throws InputMismatchException, NumberFormatException {
        System.out.println("┎─────[DOANH THU THEO NGÀY]──────────┒");
        System.out.print("┠ ▹ Nhập ngày bắt đầu: ");
        int startDay = Integer.parseInt(scanner.nextLine());
        System.out.print("┠ ▹ Nhập tháng: ");
        int startmonth = Integer.parseInt(scanner.nextLine());
        System.out.print("┠ ▹ Nhập ngày kết thúc: ");
        int endDay = Integer.parseInt(scanner.nextLine());
        System.out.print("┠ ▹ Nhập tháng: ");
        int endMonth = Integer.parseInt(scanner.nextLine());
        System.out.println("┖─────────────────────────────────────┚");
        Pattern pattern = Pattern.compile("^Ngày [" + startDay + "-" + endDay + "]+/[" + startmonth + "-" + endMonth + "]+:(.*?)VNĐ$");
        String line = "";
        Matcher matcher;
        int turnOverByDay = 0;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("src/File/turnOver.csv"));
            while ((line = bufferedReader.readLine()) != null) {
                matcher = pattern.matcher(line);
                while (matcher.find()) {
                    turnOverByDay += Integer.parseInt(matcher.group(1).trim());
                }
            }
            System.out.println("┎─────[DOANH THU THEO NGÀY]───────────────────────┒");
            System.out.println("┠ ▹ Thời gian: " + startDay + "/" + startmonth + " - " + endDay + "/" + endMonth);
            System.out.println("┠ ▹ Doanh thu: " + turnOverByDay + "VNĐ");
            System.out.println("┖─────────────────────────────────────────────────┚");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeTurnOver() {
        System.out.println("┎─────[CHỐT DOANH THU CỦA PHIÊN]──────────────────┒");
        System.out.println("┠ ▹ Thời gian: " + new Date());
        System.out.println("┠ ▹ Doanh thu: " + getTurnOver() + "VNĐ");
        System.out.println("┖─────────────────────────────────────────────────┚");
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(PATHNAME_OF_TURNOVER, true));
            bufferedWriter.write("Ngày " + new Date().getDate() + "/" + (new Date().getMonth() + 1) + ":" + getTurnOver() + "VNĐ");
            bufferedWriter.newLine();
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
