package _Service;

import java.io.Serializable;

public class Service implements Serializable {
    private String serviceName;
    private int priceOfService;
    private int quantityOfService;

    public Service() {
    }

    public Service(String serviceName, int priceOfService) {
        this.serviceName = serviceName;
        this.priceOfService = priceOfService;
    }

    public Service(String serviceName, int priceOfService, int quantityOfService) {
        this.serviceName = serviceName;
        this.priceOfService = priceOfService;
        this.quantityOfService = quantityOfService;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public int getPriceOfService() {
        return priceOfService;
    }

    public void setPriceOfService(int priceOfService) {
        this.priceOfService = priceOfService;
    }

    public int getQuantityOfService() {
        return quantityOfService;
    }

    public void setQuantityOfService(int quantityOfService) {
        this.quantityOfService = quantityOfService;
    }

    public void displayBored() {
        System.out.printf("%-20S%-20S", "tên dịch vụ", "giá tiền");
        System.out.println();
    }

    public void display() {
        System.out.printf("%-20s%-20s\n", getServiceName(), getPriceOfService());
    }

}
