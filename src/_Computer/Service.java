package _Computer;

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
        System.out.println("┎───[TÊN DỊCH VỤ]────────┬───[GIÁ TIỀN]────────┒");
    }

    public void display() {
        System.out.printf("%4s%26s\n", "┠    " + getServiceName(), getPriceOfService());
    }

    public void displayBoredBot() {
        System.out.println("┖────────────────────────┴─────────────────────┚");
    }
}
