package _ReadWriteFile;

import _Manager.ComputerManager;

import java.io.*;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IOFileCSV {
    private final String PATHNAME_OF_TURNOVER = "src/File/turnOver.csv";
    private final Scanner scanner = new Scanner(System.in);
    private final ComputerManager computerManager = new ComputerManager();

    public void turnOverAll() {
        Pattern pattern = Pattern.compile("^Ngày [0-9]+/[0-9]+:(.*?)VNĐ$");
        String line = "";
        Matcher matcher;
        int turnOver = 0;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("src/File/turnOver.csv"));
            while ((line = bufferedReader.readLine()) != null) {
                matcher = pattern.matcher(line);
                while (matcher.find()) {
                    turnOver += Integer.parseInt(matcher.group(1).trim());
                }
            }
            System.out.println("┎─────[CHỐT DOANH TỔNG]───────────────────────────┒");
            System.out.println("┠ ▹ Doanh thu: " + turnOver + "VNĐ");
            System.out.println("┖─────────────────────────────────────────────────┚");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void turnOverByDay() throws InputMismatchException, NumberFormatException {
        System.out.println("┎─────[DOANH THU THEO NGÀY]──────────┒");
        System.out.print("┠ ▹ Nhập ngày bắt đầu: ");
        int startDay = Integer.parseInt(scanner.nextLine());
        System.out.print("┠ ▹ Nhập tháng: ");
        int startmonth = Integer.parseInt(scanner.nextLine());
        System.out.print("┠ ▹ Nhập ngày kết thúc: ");
        int endDay = Integer.parseInt(scanner.nextLine());
        System.out.print("┠ ▹ Nhập tháng: ");
        int endMonth = Integer.parseInt(scanner.nextLine());
        System.out.println("┖─────────────────────────────────────┚");
        Pattern pattern = Pattern.compile("^Ngày [" + startDay + "-" + endDay + "]+/[" + startmonth + "-" + endMonth + "]+:(.*?)VNĐ$");
        String line = "";
        Matcher matcher;
        int turnOverByDay = 0;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("src/File/turnOver.csv"));
            while ((line = bufferedReader.readLine()) != null) {
                matcher = pattern.matcher(line);
                while (matcher.find()) {
                    turnOverByDay += Integer.parseInt(matcher.group(1).trim());
                }
            }
            System.out.println("┎─────[DOANH THU THEO NGÀY]───────────────────────┒");
            System.out.println("┠ ▹ Thời gian: " + startDay + "/" + startmonth + " - " + endDay + "/" + endMonth);
            System.out.println("┠ ▹ Doanh thu: " + turnOverByDay + "VNĐ");
            System.out.println("┖─────────────────────────────────────────────────┚");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeTurnOver() {
        System.out.println("┎─────[CHỐT DOANH THU CỦA PHIÊN]──────────────────┒");
        System.out.println("┠ ▹ Thời gian: " + new Date());
        System.out.println("┠ ▹ Doanh thu: " + computerManager.getTurnOver() + "VNĐ");
        System.out.println("┖─────────────────────────────────────────────────┚");
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(PATHNAME_OF_TURNOVER, true));
            bufferedWriter.write("Ngày " + new Date().getDate() + "/" + (new Date().getMonth() + 1) + ":" + computerManager.getTurnOver() + "VNĐ");
            bufferedWriter.newLine();
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
