package qlkh;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Lilly
 */
public class Manager {

    private Warehouse positionList;
    private ProductList productList;
    private InvoiceList invoiceList;
    private ArrayList<ContainLink> linkList;

    public Manager() {
        positionList = new Warehouse();
        productList = new ProductList();
        invoiceList = new InvoiceList();
        linkList = new ArrayList<ContainLink>();
    }
//------------------------------------------------------------------------------    

    public int importPD() {
//        cls;
        Scanner scan = new Scanner(System.in);
        System.out.println("---NHAP HANG---");
        System.out.print("Nhap ngay thang nam (dd/mm/yyyy): ");
        String date = scan.nextLine();
        System.out.print("Nhap tu: ");
        String from = scan.nextLine();
        System.out.print("Nguoi chiu trach nhiem: ");
        String respond = scan.nextLine();
        System.out.print("Co xuat hoa don khong (Y/N) ? ");
        char option = scan.next().charAt(0);
        if (option == 'N') {
            System.out.println("Nhap thong tin san pham (Nhap STT = 0 de ket thuc): ");
            System.out.print("STT: ");
            int seri = scan.nextInt();
            do {
                if (positionList.ablePositionCheck() < 1) {
                    System.out.println("---KHONG DU NOI CHUA---");
                    break;
                }
                System.out.print("Nhap ID san pham: ");
                String proID = scan.nextLine();
//            int index = productList.find(proID);
                int index = 0;
                if (-1 == index) {
//                tạo sp mới trong productList
                }
                System.out.print("Nhap so luong san pham: ");
                double amount = scan.nextDouble();
                System.out.print("Nhap ID ke chua: ");
                String posID = scan.nextLine();
                while (!positionList.ablePositionCheck(posID, true)) {
                    System.out.println("---Ke da chua hang---");
                    System.out.print("Nhap ID ke khac: ");
                    posID = scan.nextLine();
                }
                if (-1 != addLink(posID, proID, amount, date)) {
                    System.out.println("---Nhap thanh cong---");
//                    Sửa số lượng sp trong productList;
                } else {
                    System.out.println("---Nhap khong thanh cong---");
                }
            } while (0 != seri);
            if (seri == 0) {
                System.out.println("---HOAN TAT NHAP HANG---");
                return 0;
            }
            return -1;
        }
//Nhap hang co xuat hoa don        
        System.out.print("Nhap ma hoa don: ");
        String invoiceID = scan.nextLine();
        Invoice invoice = new Invoice('I', invoiceID, date, respond, from);
        System.out.println("Nhap thong tin san pham (Nhap STT = 0 de ket thuc): ");
        System.out.print("STT: ");
        int seri = scan.nextInt();
        do {
            if (positionList.ablePositionCheck() < 1) {
                System.out.println("---KHONG DU NOI CHUA---");
                break;
            }
            System.out.print("Nhap ID san pham: ");
            String proID = scan.nextLine();
//            int index = productList.find(proID);
            int index = 0;
            if (-1 == index) {
//                tạo sp mới trong productList
            }
            System.out.print("Nhap so luong san pham: ");
            double amount = scan.nextDouble();
            System.out.print("Nhap ID ke chua: ");
            String posID = scan.nextLine();
            while (!positionList.ablePositionCheck(posID, true)) {
                System.out.println("---Ke da chua hang---");
                System.out.print("Nhap ID ke khac: ");
                posID = scan.nextLine();
            }
            if (-1 != addLink(posID, proID, amount, date)) {
                System.out.println("---Nhap thanh cong---");
//                Sửa số lượng sp trong productList;                
//                invoice.addMoreProduct(proID, lay unit trong productlist , amount, lay don gia)
            } else {
                System.out.println("---Nhap khong thanh cong---");
            }
        } while (0 != seri);
        if (seri != 1) {
            System.out.println("---HOAN TAT NHAP HANG---");
            return 0;
        } 
        return -1;
    }

    public int exportPD() {
//        cls;
        System.out.println("---NHAP HANG---");

        return 0;
    }

    public void inventory() {
//        cls;
        System.out.println("---KIEM KE---");
        System.out.println("0. Quay lai");
        System.out.println("1. Xem danh sach hang ton kho");
        System.out.println("2. Tra cuu hang hoa");
        System.out.println("3. Tra cuu vi tri");
        System.out.println("4. Thay doi vi tri hang hoa");
        System.out.println("5. Kiem kho");
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
                showInventoryList();
                continute();
                break;
            }
            case 2: {
                System.out.print("Nhap ID san pham: ");
                String productID = scan.next();
                showByProduct(productID);
                continute();
                break;
            }
            case 3: {
                System.out.print("Nhap ID vi tri: ");
                String posID = scan.next();
                if (-1 != positionList.find(posID)) {
                    int index = findByPosID(posID, true);
                    if (-1 != index) {
                        System.out.println("San pham: " + linkList.get(index).getProductID());
                        System.out.println("So luong: " + linkList.get(index).getAmount());
                        System.out.println("So ngay ton kho: " + linkList.get(index).getDays());
                    } else {
                        System.out.println("---Vi tri khong chua hang---");
                    }
                } else {
                    System.out.println("---Vi tri khong ton tai---");
                }
                continute();
                break;
            }
            case 4: {
                System.out.print("Nhap ID vi tri: ");
                String posID = scan.next();
                int posIndex = positionList.find(posID);
                if (-1 != posIndex) {
                    int linkIndex = findByPosID(posID, true);
                    if (-1 != linkIndex) {
                        System.out.println("Vi tri: " + posID);
                        System.out.println("San pham: " + linkList.get(linkIndex).getProductID());
                        System.out.println("So luong: " + linkList.get(linkIndex).getAmount());
                        System.out.println("So ngay ton kho: " + linkList.get(linkIndex).getDays());

                        System.out.print("Nhap ID vi tri chuyen den: ");
                        String modifyPosID = scan.next();
                        if (positionList.ablePositionCheck(modifyPosID, true)) {
                            if (-1 != addLink(modifyPosID, linkList.get(linkIndex).getProductID(), linkList.get(linkIndex).getAmount(), linkList.get(linkIndex).getInputDate())) {
                                linkList.get(linkIndex).setStatus(false);
                                positionList.get(posIndex).setStatus(true);
                                System.out.println("---Chuyen thanh cong---");
                            } else {
                                System.out.println("---Chuyen khong thanh cong---");
                            }
                        } else {
                            System.out.println("---Vi tri moi da chua hang---");
                        }

                    } else {
                        System.out.println("---Vi tri dang trong---");
                    }
                } else {
                    System.out.println("---Vi tri khong ton tai---");
                }
                continute();
                break;
            }
            case 5: {
//                boolean check = false;
//                for (ContainLink i : linkList) {
//                    if (i.getStatus()) {
//                        check = true;
//                        break;
//                    }
//                }
//                if (!check) {
//                    System.out.println("---KHONG CO HANG TON KHO---");
//                    return;
//                }
//                System.out.println("---DANH SACH HANG TON KHO---");
//                System.out.println("| Vi tri | ID san pham |  So luong  | So ngay ton kho |");
//                for (ContainLink i : linkList) {
//                    if (i.getStatus()) {
//                        i.display();
//                    }
//                }
                continute();
                break;
            }
            case 6: {
                System.exit(1);
            }
        }
        inventory();
    }

    private void showInventoryList() {
        boolean check = false;
        for (ContainLink i : linkList) {
            if (i.getStatus()) {
                check = true;
                break;
            }
        }
        if (!check) {
            System.out.println("---KHONG CO HANG TON KHO---");
            return;
        }
        System.out.println("---DANH SACH HANG TON KHO---");
        System.out.println("| Vi tri | ID san pham |  So luong  | So ngay ton kho |");
        for (ContainLink i : linkList) {
            if (i.getStatus()) {
                i.display();
            }
        }
    }

    private void showByProduct(String productID) {
        System.out.println("---TIM KIEM: " + productID + "---");
        System.out.println("---KET QUA---");
//        int index = productList.find(productID);
//        if (-1 == index || -1 == findByProductID(productID, true)) {
        if (-1 == findByProductID(productID, true)) {
            System.out.println("---KHONG CO HANG NAY TRONG KHO---");
            return;
        }
//        String name = productList.get(index).getName();
        System.out.println("| Vi tri | ID san pham |  So luong  | So ngay ton kho |");
        for (ContainLink i : linkList) {
            if (productID == i.getProductID() && i.getStatus()) {
                i.display();
            }
        }
    }

//--Private---------------------------------------------------------------------
    private boolean isInteger(String number) {
        try {
            Integer.parseInt(number);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private void continute() {
        System.out.println("\n---Nhan phim bat ky de tiep tuc---");
        Scanner scan = new Scanner(System.in);
        String continute = scan.next();
    }

    private int addLink(String posID, String productID, double amount, String inputDate) {
        //Neu pos enable => huy bo
        //pos able => create new containLink => add => modify posStatus
        if (positionList.ablePositionCheck(posID, true)) {
            ContainLink link = new ContainLink(posID, productID, amount, inputDate);
            if (linkList.add(link)) {
                positionList.modify(posID, false);
                return 0;
            }
        }
        return -1;
    }

    private int findByPosID(String posID, boolean status) {
        if (!linkList.isEmpty()) {
            for (int index = 0; index < linkList.size(); index++) {
                String temp = linkList.get(index).getPositionID();
                if (posID.equalsIgnoreCase(temp) && status == linkList.get(index).getStatus()) {
                    return index;
                }
            }
        }
        return -1;
    }

    private int findByProductID(String productID, boolean status) {
        if (!linkList.isEmpty()) {
            for (int index = 0; index < linkList.size(); index++) {
                String temp = linkList.get(index).getProductID();
                if (productID.equalsIgnoreCase(temp) && status == linkList.get(index).getStatus()) {
                    return index;
                }
            }
        }
        return -1;
    }

//--Get-Set---------------------------------------------------------------------
    public Warehouse getPositionList() {
        return positionList;
    }

    public void setPositionList(Warehouse positionList) {
        this.positionList = positionList;
    }

    public ProductList getProductList() {
        return productList;
    }

    public void setProductList(ProductList productList) {
        this.productList = productList;
    }

    public InvoiceList getInvoiceList() {
        return invoiceList;
    }

    public void setInvoiceList(InvoiceList invoiceList) {
        this.invoiceList = invoiceList;
    }
}
