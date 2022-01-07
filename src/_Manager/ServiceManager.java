package _Manager;

import _Computer.Service;
import _ReadWriteFile.IOFile;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class ServiceManager implements ModelManager {
    private final Scanner scanner = new Scanner(System.in);
    private final ArrayList<Service> services;
    private final Service service = new Service();

    private final IOFile<Service> ioFile = new IOFile<>();
    private final File PATHNAME_OF_SERVICE = new File("src/File/service");

    public ServiceManager() {
        if (PATHNAME_OF_SERVICE.length() == 0) {
            this.services = new ArrayList<>();
        } else {
            this.services = ioFile.readFileData(PATHNAME_OF_SERVICE);
        }
    }

    @Override
    public void create() {
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
            System.out.print("[\uD83D\uDEAC] Nhập giá dịch vụ: ");
            priceOfService = Integer.parseInt(scanner.nextLine());
            services.add(new Service(serviceName, priceOfService));
            ioFile.writerFileData(services, PATHNAME_OF_SERVICE);
            System.out.println("[\uD83D\uDD14] Thêm '" + serviceName + "' thành công");
            display();
        }
    }

    @Override
    public void display() {
        if (PATHNAME_OF_SERVICE.length() == 0) {
            System.out.println("[\uD83D\uDD14] Chưa có dịch vụ nào! Vui lòng thêm dịch vụ!");
        } else {
            System.out.println("-----");
            System.out.println("BẢNG DỊCH VỤ");
            ArrayList<Service> services = ioFile.readFileData(PATHNAME_OF_SERVICE);
            service.displayBored();
            for (Service s :
                    services) {
                s.display();
            }
            service.displayBoredBot();
            System.out.println("-----");
            ioFile.writerFileData(services, PATHNAME_OF_SERVICE);
        }
    }

    @Override
    public void delete(String deleteName) {
        boolean checkServiceName = false;
        Service service = null;
        for (Service s :
                services) {
            if (s.getServiceName().equals(deleteName)) {
                checkServiceName = true;
                service = s;
            }
        }
        if (service != null) {
            services.remove(service);
            ioFile.writerFileData(services, PATHNAME_OF_SERVICE);
            System.out.println("[\uD83D\uDD14] Xóa '" + service.getServiceName() + "' thành công");
            display();
        }
        if (!checkServiceName) {
            System.out.println("[\uD83D\uDD14] Không tìm thấy dịch vụ! Vui lòng nhập lại!");
        }
    }

    @Override
    public void edit(String editName) {
        boolean checkService = false;
        Service service = null;
        for (Service s :
                services) {
            if (s.getServiceName().equals(editName)) {
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
                ioFile.writerFileData(services, PATHNAME_OF_SERVICE);
                System.out.println("[\uD83D\uDD14] Cập nhật '" + serviceName + "' thành công");
                display();
            }
        }
    }
}
