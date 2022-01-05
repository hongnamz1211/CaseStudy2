package _Computer;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

public class Computer implements Runnable, Serializable {
    private int computerName;
    private String status = "disable";
    private int priceOfTime = 18000;
    private int priceOfService;
    private String usedTime = "0";
    private LocalTime startTime;
    private LocalTime endTime;
    private LocalDate startUsed;
    private LocalDate endUsed;

    public Computer(int computerName, String status, String usedTime) {
        this.computerName = computerName;
        this.status = status;
        this.usedTime = usedTime;
    }

    public Computer() {
    }

    public Computer(int computerName) {
        this.computerName = computerName;
    }


    public Computer(int computerName, String status, int priceOfTime, int priceOfService, String usedTime, LocalTime startTime, LocalTime endTime) {
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

    public String getStatus() {
        return status;
    }


    public void setStatus(String status) {
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

    public String getUsedTime() {
        return usedTime;
    }

    public void setUsedTime(String usedTime) {
        this.usedTime = usedTime;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public int payment() {
        return getMinute() * (getPriceOfTime() / 60) + getPriceOfService();
    }

//    private final ComputerManager computerManager = new ComputerManager();

    public void displayBored() {
        System.out.printf("%-20S%-20S%-20S%-20S%-20S", "tên máy", "trạng thái", "thời gian sử dụng", "dịch vụ", "tổng tiền");
        System.out.println();
    }

    public void display() {
        System.out.printf("%-20S%-20S%-20S%-20S%-20S\n", getComputerName(), getStatus(), getUsedTime(), getPriceOfService(), payment());
    }

    public LocalDate getStartUsed() {
        return startUsed;
    }

    public void setStartUsed(LocalDate startUsed) {
        this.startUsed = startUsed;
    }

    public LocalDate getEndUsed() {
        return endUsed;
    }

    public void setEndUsed(LocalDate endUsed) {
        this.endUsed = endUsed;
    }

    private boolean done;
    private int minute;
//    private LocalTime timeNow;
//    private String usedTimeNow = "0";

//    public String getUsedTimeNow() {
//        return usedTimeNow;
//    }
//
//    public void setUsedTimeNow(String usedTimeNow) {
//        this.usedTimeNow = usedTimeNow;
//    }
//
//    public LocalTime getTimeNow() {
//        return timeNow;
//    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

//    public void setTimeNow(LocalTime timeNow) {
//        this.timeNow = timeNow;
//    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public void startTime() {
        Thread thread = new Thread(this);
        thread.start();
        done = false;
    }

    public void stopTime() {
        done = true;
    }

    @Override
    public void run() {
        startTime = LocalTime.now();
        while (!done) {
            endTime = LocalTime.now();
            long duration = Duration.between(startTime, endTime).getSeconds();
            minute = (int) ((duration % 3600) / 60);
            usedTime = String.format("%d:%02d:%02d", duration / 3600, minute, duration % 60);
        }
    }

    public String writerTrade() {
        return getComputerName() + ", " + getUsedTime() + ", " + payment() + ", " + getStartUsed() + ", " + getEndUsed();
    }


//    public String writeUser() {
//        return getComputerName() + ", " + getUsedTime() + ", " + payment() + ", " + getStartTime() + ", " + getEndTime();
//    }
//
//    private int hour = 0;
//    private int minute = 0;
//    private int second = 0;
//    private final String abc = hour + ":" + minute + ":" + second;
//
//    @Override
//    public void run() {
//        while (getStatus().equals("available")) {
//            try {
//                Thread.sleep(1000);
//                this.second++;
//                if (this.second == 60) {
//                    this.second = 0;
//                    this.minute++;
//                } else if (this.minute == 60) {
//                    this.minute = 0;
//                    this.hour++;
//                }
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//    }
}
