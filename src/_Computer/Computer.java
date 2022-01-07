package _Computer;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Date;

public class Computer implements Runnable, Serializable {
    private int computerName;
    private String status = "disable";
    private int priceOfTime = 18000;
    private int priceOfService;
    private String usedTime = "0:00:00";
    private LocalTime startTime;
    private LocalTime endTime;
    private Date startUsed;
    private Date endUsed;

    public Computer() {
    }

    public Computer(int computerName) {
        this.computerName = computerName;
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

    public Date getStartUsed() {
        return startUsed;
    }

    public void setStartUsed(Date startUsed) {
        this.startUsed = startUsed;
    }

    public Date getEndUsed() {
        return endUsed;
    }

    public void setEndUsed(Date endUsed) {
        this.endUsed = endUsed;
    }

    private boolean done;
    private int minute;

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

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
        if (done) {
            usedTime = "0:00:00";
        }
    }
}
