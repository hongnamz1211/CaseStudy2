package _Computer;

import _ReadWriteFile.IOFile;
import _Service.Service;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Manager {
    private int turnOver = 0;
    private final Scanner scanner = new Scanner(System.in);
    private final ArrayList<Computer> computers = new ArrayList<>();
    private final ArrayList<Service> services = new ArrayList<>();
    private final Computer computer = new Computer();
    private final Service service = new Service();

    private final IOFile<Computer> ioFileComputer = new IOFile<>();
    private final IOFile<Service> ioFileService = new IOFile<>();
    private final File PATHNAME_OF_COMPUTER = new File("src/File/computer");
    private final File PATHNAME_OF_SERVICE = new File("src/File/service");
    private final File PATHNAME_OF_TURNOVER = new File("src/File/turnOver.csv");
    private final File PATHNAME_OF_TRADE = new File("src/File/trade.csv");

    public void createComputer() {
        boolean checkComputer = false;
        System.out.println("Nhập số máy thêm");
        int computerName = scanner.nextInt();
        scanner.nextLine();
        for (Computer c :
                computers) {
            if (c.getComputerName() == computerName) {
                System.out.println("Đã có số máy này. Nhập lại");
                checkComputer = true;
                break;
            }
        }
        if (!checkComputer) {
            computers.add(new Computer(computerName));
            ioFileComputer.writerFileData(computers, PATHNAME_OF_COMPUTER);
            System.out.println("-----");
            System.out.println("Thêm máy số '" + computerName + "' thành công");
            displayComputer();
        }
    }

    public void displayComputer() {
        if (PATHNAME_OF_COMPUTER.length() == 0) {
            System.out.println("Chưa có máy nào! Vui lòng thêm máy!");
        } else {
            ioFileComputer.writerFileData(computers, PATHNAME_OF_COMPUTER);
            System.out.println("-----");
            System.out.println("Danh sách máy");
//        ArrayList<Computer> computers = ioFileComputer.readFileData(computers);
            computer.displayBored();
            for (Computer c :
                    computers) {
                c.display();
            }
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
            System.out.println("Xóa máy số '" + computer.getComputerName() + "' thành công");
            displayComputer();
        }
    }

    public void editComputer(int editComputer) {
        Computer computer = null;
        for (Computer c :
                computers) {
            if (c.getComputerName() == editComputer) {
                computer = c;
            }
        }
        if (computer != null) {
            int index = computers.indexOf(computer);
            System.out.println("Nhập số máy mới");
            int computerName = scanner.nextInt();
            for (Computer c :
                    computers) {
                if (c.getComputerName() == computerName) {
                    System.out.println("Đã có số máy này. Nhập lại");
                    break;
                }
            }
            computer.setComputerName(computerName);
            scanner.nextLine();
            computers.set(index, computer);
            ioFileComputer.writerFileData(computers, PATHNAME_OF_COMPUTER);
            System.out.println("Cập nhật máy số '" + computer.getComputerName() + "' thành công");
            displayComputer();
        }
    }

    public void statusComputer() {
        System.out.println("Nhập tên máy muốn thao tác");
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

    public void computerAvailable(int computers) {
        int choice;
        do {
            System.out.println("-----");
            System.out.println("Thao tác");
            System.out.println("1. Thêm dịch vụ");
            System.out.println("2. Thanh toán");
            System.out.println("0. Quay lại");
            System.out.println("-----");
            System.out.println("Nhập lựa chọn");
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
            }
        } while (choice != 0);
    }

    public void addService(int computerName) {
        int priceOfService = 0;
        for (Computer c :
                computers) {
            if (c.getComputerName() == computerName) {
                System.out.println("-----");
                displayService();
                System.out.println("Nhập tên dịch vụ muốn thêm");
                String name = scanner.nextLine();
                for (Service s :
                        services) {
                    if (s.getServiceName().equals(name)) {
                        priceOfService += s.getPriceOfService();
                        System.out.println("Thêm '" + s.getServiceName() + "' vào máy số '" + c.getComputerName() + "' thành công");
                    }
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

    public void payment(int computerName) {
        for (Computer c :
                computers) {
            if (c.getComputerName() == computerName) {
                System.out.println("-----");
                System.out.println("Xác nhận thanh toán máy số '" + c.getComputerName() + "'");
                System.out.println("1. Thanh toán");
                System.out.println("0. Quay lại");
                System.out.println("-----");
                System.out.println("Nhập lựa chọn");
                int choice = scanner.nextInt();
                scanner.nextLine();
                if (choice == 1) {
                    System.out.println("Số tiền phải thanh toán '" + c.payment() + "' VNĐ");

                    turnOver += c.payment();
                    writeTrade(c);
                    c.setStatus("disable");
                    c.setEndUsed(LocalDate.now());
                    c.stopTime();
//                    c.setMinute(0);
                    c.setUsedTime("0");
                    c.setPriceOfService(0);
                    c.setPriceOfTime(0);
                    ioFileComputer.writerFileData(computers, PATHNAME_OF_COMPUTER);
                    displayComputer();
                    // thêm bill
                } else if (choice == 0) {
                    displayComputer();
                }
            }
        }
    }


    public void computerDisable(int computerName) {

        for (Computer c :
                computers) {
            if (c.getComputerName() == computerName) {
                int choice;
                do {
                    System.out.println("-----");
                    System.out.println("Máy đang offline. Bạn có xác nhận bật máy không?");
                    System.out.println("1. Bật máy");
                    System.out.println("0. Quay lại");
                    System.out.println("-----");
                    choice = scanner.nextInt();
                    scanner.nextLine();
                    if (choice == 1) {
                        c.setStatus("available");
                        c.setUsedTime("0:00:01");
                        c.setStartUsed(LocalDate.now());
                        c.startTime();
//                        saveStartTime = LocalTime.now();
                        ioFileComputer.writerFileData(computers, PATHNAME_OF_COMPUTER);
                        System.out.println("Bật máy số '" + c.getComputerName() + "' thành công");
                        displayComputer();
                        break;
                    }
                } while (choice != 0);
            }
        }
    }

    public void setupPriceOfTime() {
        int choice;
        System.out.println("Giá tiền 1h hiện tại");
        System.out.println(computer.getPriceOfTime());
        System.out.println("Nhập giá muốn thay đổi");
        int priceOfTime = scanner.nextInt();
        System.out.println("Xác nhận thay đổi");
        System.out.println("1. Xác nhận");
        System.out.println("0. Quay lại");
        System.out.println("Nhập lựa chọn");
        choice = scanner.nextInt();
        scanner.nextLine();
        if (choice == 1) {
            computer.setPriceOfTime(priceOfTime);
            System.out.println("Thay đổi giá thành '" + priceOfTime + "'/1h thành công");
        }
        // yêu cầu tắt hết máy khi thay đổi giá.
    }

    public int getTurnOver() {
        return turnOver;
    }

    public void time() {
        System.out.println("số máy");
        int computerName = scanner.nextInt();
        for (Computer c :
                computers) {
            if (c.getComputerName() == computerName) {
                System.out.println(c.getUsedTime());
                System.out.println(c.getMinute());
            }
        }
        ioFileComputer.writerFileData(computers, PATHNAME_OF_COMPUTER);
    }

    public void createService() {
//        boolean checkService = false;
//        System.out.println("Nhập tên dịch vụ");
//        String serviceName = scanner.nextLine();
//        for (Service s :
//                services) {
//            if (s.getServiceName().equals(serviceName)) {
//                System.out.println("Đã có dịch vụ này");
//                checkService = true;
//                break;
//            }
//        }
//        if (!checkService) {
//            System.out.println("Nhập giá dịch vụ");
//            int priceOfService = scanner.nextInt();
//            scanner.nextLine();
//            Service service = new Service(serviceName, priceOfService);
//            services.add(service);
//            System.out.println("-----");
//            System.out.println("Thêm '" + serviceName + "' thành công");
//        }
//        writerFileData(services);
//        displayService();
        boolean checkService = false;
        System.out.println("Nhập tên dịch vụ");
        String serviceName = scanner.nextLine();
        for (Service s :
                services) {
            if (s.getServiceName().equals(serviceName)) {
                System.out.println("Đã có dịch vụ này");
                checkService = true;
                break;
            }
        }
        if (!checkService) {
            System.out.println("Nhập giá dịch vụ");
            int priceOfService = scanner.nextInt();
            scanner.nextLine();
            services.add(new Service(serviceName, priceOfService));
            ioFileService.writerFileData(services, PATHNAME_OF_SERVICE);
            displayService();
            System.out.println("-----");
            System.out.println("Thêm '" + serviceName + "' thành công");
        }
    }


    public void displayService() {
        if (PATHNAME_OF_SERVICE.length() == 0) {
            System.out.println("Chưa có dịch vụ nào! Vui lòng thêm dịch vụ!");
        } else {
            System.out.println("-----");
            System.out.println("Bảng dịch vụ");
            ioFileService.writerFileData(services, PATHNAME_OF_SERVICE);
            ArrayList<Service> services = ioFileService.readFileData(PATHNAME_OF_SERVICE);
            service.displayBored();
            for (Service s :
                    services) {
                s.display();
            }
            System.out.println("-----");
            ioFileService.writerFileData(services, PATHNAME_OF_SERVICE);
        }
    }

    public void deleteService(String name) {
        Service service = null;
        for (Service s :
                services) {
            if (s.getServiceName().equals(name)) {
                service = s;
            }
        }
        if (service != null) {
            services.remove(service);
            ioFileService.writerFileData(services, PATHNAME_OF_SERVICE);
            System.out.println("Xóa '" + service.getServiceName() + "' thành công");
            displayService();
        }
    }

    public void editService(String name) {
        Service service = null;
        for (Service s :
                services) {
            if (s.getServiceName().equals(name)) {
                service = s;
            }
        }
        if (service != null) {
            int index = services.indexOf(service);
            System.out.println("Nhập tên dịch vụ mới");
            String serviceName = scanner.nextLine();
            for (Service s :
                    services) {
                if (s.getServiceName().equals(serviceName)) {
                    System.out.println("Đã có dịch vụ này");
                    break;
                }
            }
            service.setServiceName(serviceName);
            System.out.println("Nhập giá dịch vụ mới");
            int priceOfService = scanner.nextInt();
            service.setPriceOfService(priceOfService);
            scanner.nextLine();
            services.set(index, service);
            ioFileService.writerFileData(services, PATHNAME_OF_SERVICE);
            System.out.println("Cập nhật '" + serviceName + "' thành công");
            displayService();
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
            System.out.println("Tổng doanh thu là: " + turnOver + "VNĐ");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void turnOverByDay() {
        System.out.println("Doanh thu theo ngày");
        System.out.println("Nhập tháng");
        String month = scanner.nextLine();
        System.out.println("Nhập ngày bắt đầu");
        String startDay = scanner.nextLine();
        System.out.println("Nhập ngày kết thúc");
        String endDay = scanner.nextLine();
        System.out.println("-----");
        Pattern pattern = Pattern.compile("^Ngày [" + startDay + "-" + endDay + "]+/[" + month + "]+:(.*?)VNĐ$");
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
            System.out.println("Doanh thu từ ngày " + startDay + " đến ngày " + endDay + " là: " + turnOverByDay + "VNĐ");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeTrade(Computer computer) {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(PATHNAME_OF_TRADE, true));
            bufferedWriter.write(computer.writerTrade());
            bufferedWriter.newLine();
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeTurnOver() {
        System.out.println("Đã chốt doanh thu của phiên");
        System.out.println("Thời gian: " + new Date() + " = " + getTurnOver() + "VNĐ");
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
