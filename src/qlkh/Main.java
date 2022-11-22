package qlkh;

/**
 * PositionID: A01 - Z99
 * ProductID: P01 - P99
 * InvoiceID: I01 - I99 / E01 - E99
 * StaffID: NV01 - NV99
 * SupplierID: S01 - S99
 * DistributorID: D01 - D99
 *
 * @author Lilly
 */
public class Main {

    public static void main(String[] args) {
        Manager manager = new Manager();
        staffMenu(manager);
    }

//    public static void adminMenu(Manager manager) {
//        Tools.cls();
//        System.out.println("---QUAN LY KHO HANG THIET BI DIEN TU---");
//        System.out.println("1. Nhap hang");
//        System.out.println("2. Xuat hang");
//        System.out.println("3. Kiem ke hang");
//        System.out.println("4. Tra cuu hang hoa");
//        System.out.println("5. Truy xuat hoa don");
//        System.out.println("6. Thiet lap kho");
//        System.out.println("7. Thoat chuong trinh");
//        System.out.print("Vui long nhap 1 so (1->7): ");
//        String option = Tools.scan.nextLine();
//        while (!Tools.isInteger(option) || Integer.parseInt(option) < 1 || Integer.parseInt(option) > 7) {
//            System.out.print("Vui long nhap 1 so (1->7): ");
//            option = Tools.scan.nextLine();
//        }
//        switch (Integer.parseInt(option)) {
//            case 1: {
//                manager.importPD();
//                Tools.continute();
//                break;
//            }
//            case 2: {
//                manager.exportPD();
//                Tools.continute();
//                break;
//            }
//            case 3: {
//                manager.inventory();
//                break;
//            }
//            case 4: {
//
//                break;
//            }
//            case 5: {
//                manager.invoiceList.menu();
//                break;
//            }
//            case 6: {
//                manager.positionList.menu();
//                break;
//            }
//            case 7: {
//                System.exit(1);
//            }
//        }
//        adminMenu(manager);
//    }
    public static void staffMenu(Manager manager) {
        Tools.cls();
        System.out.println("---QUAN LY KHO HANG THIET BI DIEN TU---");
        System.out.println("1. Nhap hang");
        System.out.println("2. Xuat hang");
        System.out.println("3. Kiem ke hang");
        System.out.println("4. Tra cuu hang hoa");
        System.out.println("5. Truy xuat hoa don");
        System.out.println("6. Thiet lap kho");
        System.out.println("7. Thoat chuong trinh");
        System.out.print("Vui long nhap 1 so (1->7): ");
        String option = Tools.scan.nextLine();
        while (!Tools.isInteger(option) || Integer.parseInt(option) < 1 || Integer.parseInt(option) > 7) {
            System.out.print("Vui long nhap 1 so (1->7): ");
            option = Tools.scan.nextLine();
        }
        switch (Integer.parseInt(option)) {
            case 1: {
                manager.importPD();
                Tools.continute();
                break;
            }
            case 2: {
                manager.exportPD();
                Tools.continute();
                break;
            }
            case 3: {
                manager.inventory();
                break;
            }
            case 4: {
                manager.productList.menu();
                break;
            }
            case 5: {
                manager.invoiceList.menu();
                break;
            }
            case 6: {
                manager.positionList.menu();
                break;
            }
            case 7: {
                System.exit(1);
            }
        }
        staffMenu(manager);
    }
}
