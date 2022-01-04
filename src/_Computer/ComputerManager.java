package _Computer;

import _ReadWriteFile.IOFile;
import _Service.Service;
import _Service.ServiceManager;

import java.io.File;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

public class ComputerManager {
    private int turnOver = 0;
    private final Scanner scanner = new Scanner(System.in);
    private final ArrayList<Computer> computers;
    private final Computer computer = new Computer();
    private final IOFile<Computer> ioFile = new IOFile<>();
    private final File PATHNAME_OF_COMPUTER = new File("src/File/computer");


    public ComputerManager() {
        if (PATHNAME_OF_COMPUTER.length() == 0) {
            this.computers = new ArrayList<>();
        } else {
            this.computers = readFileData();
        }
    }

    private final ServiceManager serviceManager = new ServiceManager();
    private final ArrayList<Service> services = serviceManager.display();

    public void writerFileData(ArrayList<Computer> serviceData) {
        ioFile.writerFileData(serviceData, PATHNAME_OF_COMPUTER);
    }

    public ArrayList<Computer> readFileData() {
        return ioFile.readFileData(PATHNAME_OF_COMPUTER);
    }

    public ArrayList<Computer> display() {
        return readFileData();
    }

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
            writerFileData(computers);
            System.out.println("-----");
            System.out.println("Thêm máy số '" + computerName + "' thành công");
        }
    }

    public void displayComputer() {
        writerFileData(computers);
        ArrayList<Computer> computers = display();
        computer.displayBored();
        for (Computer c :
                computers) {
            c.display();
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
            writerFileData(computers);
            System.out.println("Xóa máy số '" + computer.getComputerName() + "' thành công");
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
            writerFileData(computers);
            System.out.println("Cập nhật máy số '" + computer.getComputerName() + "' thành công");
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
                serviceManager.displayService();
                System.out.println("Nhập tên dịch vụ muốn thêm");
                String name = scanner.nextLine();
//                ArrayList<Service> services = serviceManager.display();
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
        writerFileData(computers);
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
                if (choice == 1) {
                    System.out.println("Số tiền phải thanh toán '" + c.payment() + "' VNĐ");
                    turnOver += c.payment();
                    c.setStatus("disable");
                    c.stopTime();
                    c.setMinute(0);
                    c.setUsedTime("0");
                    c.setPriceOfService(0);
                    c.setPriceOfTime(0);
                    writerFileData(computers);
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
                    if (choice == 1) {
                        c.setStatus("available");
                        c.setUsedTime("0:00:01");
                        c.startTime();
//                        saveStartTime = LocalTime.now();
                        writerFileData(computers);
                        System.out.println("Bật máy số '" + c.getComputerName() + "' thành công");
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
        writerFileData(computers);
    }

}
