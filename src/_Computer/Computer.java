package _Computer;

import java.io.Serializable;
import java.util.Date;

public class Computer implements Serializable {
    private int computerName;
    private boolean status = false;
    private int priceOfTime;
    private int priceOfService;
    private int usedTime;
    private Date startTime;
    private Date endTime;

    public Computer() {
    }

    public Computer(int computerName) {
        this.computerName = computerName;
    }

    public Computer(int computerName, boolean status) {
        this.computerName = computerName;
        this.status = status;
    }

    public Computer(int computerName, boolean status, int priceOfTime, int priceOfService, int usedTime, Date startTime, Date endTime) {
        this.computerName = computerName;
        this.status = status;
        this.priceOfTime = priceOfTime;
        this.priceOfService = priceOfService;
        this.usedTime = usedTime;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public int getComputerName() {
        return computerName;
    }

    public void setComputerName(int computerName) {
        this.computerName = computerName;
    }

    public boolean isStatus() {
        return status;
    }



    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getPriceOfTime() {
        return priceOfTime;
    }

    public void setPriceOfTime(int priceOfTime) {
        this.priceOfTime = priceOfTime;
    }

    public int getPriceOfService() {
        return priceOfService;
    }

    public void setPriceOfService(int priceOfService) {
        this.priceOfService = priceOfService;
    }

    public int getUsedTime() {
        return usedTime;
    }

    public void setUsedTime(int usedTime) {
        this.usedTime = usedTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public int payment() {
        return this.usedTime * this.priceOfTime + this.priceOfService;
    }

    public void checkStatus() {
        if (isStatus()) {
            System.out.println("Available");
        } else {
            System.out.println("Disable");
        }
    }

    public void displayBored() {
        System.out.printf("%-10S%-10S%-10S%-10S%-10S", "tên máy", "trạng thái", "thời gian sử dụng (phút)", "dịch vụ", "tổng tiền");
        System.out.println();
    }
    public void display() {
        System.out.printf("%-10S%-10S%-10S%-10S%-10S\n", getComputerName(), isStatus(), getUsedTime(), getPriceOfService(), payment());
    }


}
