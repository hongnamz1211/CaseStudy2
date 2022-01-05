//import _Computer.Computer;
//import _Computer.ComputerManager;
//
//import java.util.Scanner;
//
//public class TestComputer {
//    private static final ComputerManager computerManager = new ComputerManager();
//    private static final Scanner scanner = new Scanner(System.in);
//    private static final Computer computer = new Computer();
//
//    public static void main(String[] args) {
//        int choice;
//        do {
//            System.out.println("Quản lý máy tính");
//            System.out.println("1. Thêm máy");
//            System.out.println("2. Hiển thị tất cả máy");
//            System.out.println("3. xóa");
//            System.out.println("4. sửa");
//            System.out.println("5. thiết lập");
//            System.out.println("6. doanh thu tổng");
//            System.out.println("7. time");
//            System.out.println("8. thay đổi giá");
//            System.out.println("9.time now");
//            choice = scanner.nextInt();
//            scanner.nextLine();
//            switch (choice) {
//                case 1:
//                    computerManager.createComputer();
//                    System.out.println("-----");
//                    System.out.println("Danh sách máy");
//                    computerManager.displayComputer();
//                    System.out.println("-----");
//                    break;
//                case 2:
//                    computerManager.displayComputer();
//                    break;
//                case 3:
//                    System.out.println("Nhập tên máy muốn xóa");
//                    int deleteName = scanner.nextInt();
//                    computerManager.deleteComputer(deleteName);
//                    System.out.println("-----");
//                    System.out.println("Danh sách máy");
//                    computerManager.displayComputer();
//                    System.out.println("-----");
//                    break;
//                case 4:
//                    System.out.println("Nhập tên máy mốn sửa");
//                    int editName = scanner.nextInt();
//                    computerManager.editComputer(editName);
//                    System.out.println("-----");
//                    System.out.println("Danh sách máy");
//                    computerManager.displayComputer();
//                    System.out.println("-----");
//                    break;
//                case 5:
//                    System.out.println("Thiết lập máy");
//                    computerManager.statusComputer();
//                    System.out.println("-----");
//                    System.out.println("Danh sách máy");
//                    computerManager.displayComputer();
//                    System.out.println("-----");
//                    break;
//                case 6:
//                    System.out.println("Tổng doanh thu là");
//                    System.out.println(computerManager.getTurnOver());
//                    break;
//                case 7:
//                    computerManager.time();
//                    break;
//                case 8:
//                    System.out.println("Giá hiện tại");
//                    System.out.println(computer.getPriceOfTime());
//                    computerManager.setupPriceOfTime();
//                    break;
//
//            }
//        } while (choice != 0);
//    }
//}
