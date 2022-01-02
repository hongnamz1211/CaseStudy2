package _Service;

import _ReadWriteFile.IOFile;

import java.util.ArrayList;
import java.util.Scanner;

public class ServiceManager {
    private final IOFile<Service> ioFile = new IOFile<>();
    private final String PATHNAME_OF_SERVICE = "src/File/service";
    private final ArrayList<Service> services = new ArrayList<>();
    private final Scanner scanner = new Scanner(System.in);


    public void writerFileData(ArrayList<Service> serviceData) {
        ioFile.writerFileData(serviceData, PATHNAME_OF_SERVICE);
    }

    public ArrayList<Service> readFileData() {
        return ioFile.readFileData(PATHNAME_OF_SERVICE);
    }

    public ArrayList<Service> display() {
        return readFileData();
    }


    public void createService() {
        System.out.println("Nhập tên dịch vụ");
        String serviceName = scanner.nextLine();
        for (Service s :
                services) {
            if (s.getServiceName().equals(serviceName)) {
                System.out.println("Đã có dịch vụ này");
                break;
            }
        }
        System.out.println("Nhập giá dịch vụ");
        int priceOfService = scanner.nextInt();
        scanner.nextLine();
        services.add(new Service(serviceName, priceOfService));
        writerFileData(services);
        System.out.println("-----");
        System.out.println("Thêm '" + serviceName + "' thành công");
    }


    public void displayService() {
        ArrayList<Service> services = display();
        for (Service s :
                services) {
            s.display();
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
            writerFileData(services);
            System.out.println("Xóa '" + service.getServiceName() + "' thành công");
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
            writerFileData(services);
            System.out.println("Cập nhật '" + serviceName + "' thành công");
        }
    }
}
