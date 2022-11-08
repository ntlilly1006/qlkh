package qlkh;

import java.util.Scanner;

/**
 *
 * @author Lilly
 */
public class Display {
//--Tools-----------------------------------------------------------------------

    public static boolean isInteger(String number) {
        try {
            Integer.parseInt(number);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static void continute() {
        System.out.println("\n---Nhan phim bat ky de tiep tuc---");
        Scanner scan = new Scanner(System.in);
        String continute = scan.next();
    }

//------------------------------------------------------------------------------    
//--Menu chinh--
    public static void mainMenu(Manager manager) {
//        cls;
        Scanner scan = new Scanner(System.in);
        System.out.println("---QUAN LY KHO HANG THIET BI DIEN TU---");
        System.out.println("1. Nhap hang");
        System.out.println("2. Xuat hang");
        System.out.println("3. Kiem ke hang");
        System.out.println("4. Tra cuu hang hoa");
        System.out.println("5. Truy xuat hoa don");
        System.out.println("6. Thiet lap kho");
        System.out.println("7. Nap xuat file");
        System.out.println("8. Thoat chuong trinh");
        System.out.print("Vui long nhap 1 so (1->8): ");
        String option = scan.nextLine();
        while (!isInteger(option) || Integer.parseInt(option) < 1 || Integer.parseInt(option) > 8) {
            System.out.print("Vui long nhap 1 so (1->8): ");
            option = scan.nextLine();
        }
        switch (Integer.parseInt(option)) {
            case 1: {
                if (0 != manager.importPD()) {
                    System.out.println("---Nhap hang khong thanh cong---");
                } else {
                    System.out.println("---Nhap hang thanh cong---");
                }
                continute();
                break;
            }
            case 2: {
                if (0 != manager.exportPD()) {
                    System.out.println("---Xuat hang khong thanh cong---");
                } else {
                    System.out.println("---Xuat hang thanh cong---");
                }
                continute();
                break;
            }
            case 3: {
                manager.inventory();
                break;
            }
            case 4: {
                productMenu(manager);
                break;
            }
            case 5: {
                invoiceMenu(manager);
                break;
            }
            case 6: {
                warehouseMenu(manager);
                break;
            }
            case 7: {
//                LoadFile.class;
                break;
            }
            case 8: {
                System.exit(1);
            }
        }
        Display.mainMenu(manager);
    }
//--TRUY XUAT HOA DON--

    public static void invoiceMenu(Manager manager) { //hoa don khong duoc sua
        ListAbs invoiceList = manager.getInvoiceList();
//        cls;
        System.out.println("---TRUY XUAT HOA DON---");
        System.out.println("0. Quay lai");
        System.out.println("1. Xem danh sach hoa don");
        System.out.println("2. Them hoa don moi");
        System.out.println("3. Xoa hoa don");
        System.out.println("4. Thoat");
        System.out.print("Vui long nhap 1 so (0->4): ");
        Scanner scan = new Scanner(System.in);
        String option = scan.nextLine();
        while (!isInteger(option) || Integer.parseInt(option) < 0 || Integer.parseInt(option) > 4) {
            System.out.print("Vui long nhap 1 so (0->4): ");
            option = scan.nextLine();
        }
        switch (Integer.parseInt(option)) {
            case 0: {
                return;
            }
            case 1: {
                invoiceList.showList();
                break;
            }
            case 2: {
                if (0 == invoiceList.addNew()) {
                    manager.setInvoiceList((InvoiceList) invoiceList);
                }
                continute();
                break;
            }
            case 3: {
                if (0 == invoiceList.delete()) {
                    manager.setInvoiceList((InvoiceList) invoiceList);
                }
                continute();
                break;
            }
            case 4: {
                System.exit(1);
            }
        }
        Display.invoiceMenu(manager);
    }
//--THIET LAP KHO--

    public static void warehouseMenu(Manager manager) {
        ListAbs positionList = manager.getPositionList();
//        cls();
        System.out.println("---THIET LAP KHO---");
        System.out.println("0. Quay lai");
        System.out.println("1. Khoi tao co so vat chat");
        System.out.println("2. Thay doi trang thai ke hang");
        System.out.println("3. Them ke hang");
        System.out.println("4. Xoa ke hang");
        System.out.println("5. Xem danh sach ke hang");
        System.out.println("6. Thoat");
        System.out.print("Vui long nhap 1 so (0->6): ");
        Scanner scan = new Scanner(System.in);
        String option = scan.nextLine();
        while (!isInteger(option) || Integer.parseInt(option) < 0 || Integer.parseInt(option) > 6) {
            System.out.print("Vui long nhap 1 so (0->6): ");
            option = scan.nextLine();
        }
        switch (Integer.parseInt(option)) {
            case 0: {
                return;
            }
            case 1: {
                positionList.init();
                manager.setPositionList((Warehouse) positionList);
                continute();
                break;
            }
            case 2: {
                if (0 == positionList.modify()) {
                    manager.setPositionList((Warehouse) positionList);
                }
                continute();
                break;
            }
            case 3: {
                if (0 == positionList.addNew()) {
                    manager.setPositionList((Warehouse) positionList);
                }
                continute();
                break;
            }
            case 4: {
                if (0 == positionList.delete()) {
                    manager.setPositionList((Warehouse) positionList);
                }
                continute();
                break;
            }
            case 5: {
                positionList.showList();
                manager.setPositionList((Warehouse) positionList);
                break;
            }
            case 6: {
                System.exit(1);
            }
        }
        Display.warehouseMenu(manager);
    }

    public static void productMenu(Manager manager) {
        ProductList productList = manager.getProductList();
        Scanner sc = new Scanner(System.in);
        String choice;
        do {
            System.out.println("----------MENU----------");
            System.out.println("(1) Them");
            System.out.println("(2) Sua");
            System.out.println("(3) Xoa");
            System.out.println("(4) Sap xep");
            System.out.println("(5) Tim");
            System.out.println("(6) Xuat san pham");
            System.out.println("(7) Thoat");
            System.out.println("Vui long nhap 1 so (1->7)");
            choice = sc.nextLine();
            while (!isInteger(choice) || Integer.parseInt(choice) < 1 || Integer.parseInt(choice) > 7) {
                System.err.println("Nhap sai! Vui long nhap lai 1 so (1->7): ");
                choice = sc.nextLine();
            }
            switch (Integer.parseInt(choice)) {
                case 1: {
                    if (0 == productList.addNew())
                        manager.setProductList(productList);
                    break;
                }
                case 2: {
                    productList.modify();
                    manager.setProductList(productList);
                    break;
                }
                case 3: {
                    if (0 == productList.delete())
                        manager.setProductList(productList);
                    break;
                }
                case 4: {
                    productList.sort();
                    manager.setProductList(productList);
                    break;
                }
                case 5: {
                    productList.find();
                    break;
                }
                case 6: {
                    productList.showList();
                    break;
                }
                default:
                    System.out.println("Thoat!");
            }
        } while (Integer.parseInt(choice) != 7);
    }
}
