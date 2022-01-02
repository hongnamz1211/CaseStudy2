package _Computer;

import _ReadWriteFile.IOFile;
import _Service.Service;
import _Service.ServiceManager;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class ComputerManager {
    private final Scanner scanner = new Scanner(System.in);
    private final ArrayList<Computer> computers = new ArrayList<>();
    private final Computer computer = new Computer();
    private final IOFile<Computer> ioFile = new IOFile<>();
    private final String PATHNAME_OF_COMPUTER = "src/File/computer";


    private final ServiceManager serviceManager = new ServiceManager();
//    private final ArrayList<Service> services = serviceManager.readFileData();

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
        System.out.println("Nhập số máy thêm");
        int computerName = scanner.nextInt();
        scanner.nextLine();
        for (Computer c :
                computers) {
            if (c.getComputerName() == computerName) {
                System.out.println("Đã có số máy này. Nhập lại");
                break;
            } else {
                computers.add(new Computer(computerName));
                writerFileData(computers);
                System.out.println("-----");
                System.out.println("Thêm máy số '" + computerName + "' thành công");
            }
        }
    }

    public void displayComputer() {
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
        for (Computer c: computers) {
            if (c.getComputerName() == computerName) {
                if (c.isStatus()) {
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
            switch (choice) {
                case 1:
                    addService(computers);
                    break;
                case 2:
                    payment(computers);
                    break;
            }
        } while (choice != 0);

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
                    c.setEndTime(new Date());
                    System.out.println("Số tiền phải thanh toán '" + c.payment() + "' VNĐ");
                    c.setStatus(false);
                    // thêm bill
                } else if (choice == 0) {
                    displayComputer();
                }
            }
        }
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
                ArrayList<Service> services = serviceManager.display();
                for (Service s:
                     services) {
                    if (s.getServiceName().equals(name)) {
                        priceOfService += c.getPriceOfService();
                        System.out.println("Thêm '" + s.getServiceName() + "' vào máy số '" + c.getComputerName() + "' thành công");
                    }
                }
            }
        }
        for (Computer c :
                computers) {
            if (c.getComputerName() == computerName) {
                c.setPriceOfService(priceOfService);
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
                        c.setStatus(true);
                        c.setStartTime(new Date());
                        writerFileData(computers);

//                            c.start();
                        System.out.println("Bật máy số '" + c.getComputerName() + "' thành công");
                        break;
                    }
                } while (choice != 0);
            }
        }
    }
}
