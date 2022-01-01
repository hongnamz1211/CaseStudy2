package service;

public class Service {
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

    @Override
    public String toString() {
        return "Service{" +
                "serviceName='" + serviceName + '\'' +
                ", priceOfService=" + priceOfService +
                '}';
    }


    public void display() {
        System.out.printf("%-10S%-10S", "tên dịch vụ:", getServiceName());
        System.out.printf("%-10S%-10S", "giá tiền", getPriceOfService());
        System.out.println();
    }
}
