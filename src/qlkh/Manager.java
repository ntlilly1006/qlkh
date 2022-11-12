package qlkh;

import java.io.*;
import java.util.*;

/**
 * Default init
 *
 * @author Lilly
 */
public class Manager {

    public PositionList positionList;
    public ProductList productList;
    public InvoiceList invoiceList;
    private ArrayList<ContainLink> linkList;
    private String filePath = "ContainLinkList.txt";

// --Constructor----------------------------------------------------------------    
    public Manager() {
        positionList = new PositionList();
        productList = new ProductList();
        invoiceList = new InvoiceList();
        linkList = new ArrayList<>();

        FileInputStream fileInputStream = null;
        Scanner scanFile = null;

        try {
            fileInputStream = new FileInputStream(filePath);
            scanFile = new Scanner(fileInputStream);

            // --Open and read from file--
            while (scanFile.hasNext()) {
                String line = scanFile.nextLine();
                String[] E = null;
                if (line != null) {
                    E = line.split(",", 2);
                }
                if (E.length >= 5) {
                    if (Tools.isPositionID(E[0]) && Tools.isProductID(E[1]) && Tools.isLong(E[2]) && Tools.isDate(E[3])) {
                        ContainLink element = new ContainLink(E[0], E[1], Long.parseLong(E[2]), E[3]);
                        if (Tools.isBoolean(E[4])) {
                            element.setStatus(Boolean.parseBoolean(E[4]));
                        }
                        linkList.add(element);
                    }
                }
            }
        } catch (FileNotFoundException ex) {
            // --Default init--
//            
//            
//            
//            
//            
//            
//            
//            
//            
//            
//            
            writeToFile();
        } finally {
            try {
                if (scanFile != null) {
                    scanFile.close();
                }
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
            } catch (IOException ex) {
                System.out.println("---Da xay ra loi---");
            }
        }
    }

    public Manager(PositionList positionList, ProductList productList, InvoiceList invoiceList, ArrayList<ContainLink> linkList) {
        this.positionList = positionList;
        this.productList = productList;
        this.invoiceList = invoiceList;
        this.linkList = new ArrayList<>(linkList);
    }

    public Manager(Manager other) {
        this.positionList = other.positionList;
        this.productList = other.productList;
        this.invoiceList = other.invoiceList;
        this.linkList = new ArrayList<>(other.linkList);
    }

// --Private--------------------------------------------------------------------
// --Add new link and write to file--    
    private boolean addLink(String posID, String productID, Long amount, String inputDate) {
        if (positionList.isEmptyPosition(posID)) {
            ContainLink link = new ContainLink(posID, productID, amount, inputDate);
            if (linkList.add(link)) {
                positionList.modify(posID, false);
                writeToFile();
                return true;
            }
        }
        return false;
    }

// --Find link by position ID--    
    private int findLinkByPosition(String positionID, boolean status) {
        if (!linkList.isEmpty()) {
            for (int index = 0; index < linkList.size(); index++) {
                String temp = linkList.get(index).getPositionID();
                if (positionID.equalsIgnoreCase(temp) && status == linkList.get(index).getStatus()) {
                    return index;
                }
            }
        }
        return -1;
    }

// --Find link by product ID--
    private int findLinkByProduct(String productID, boolean status) {
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

// --Disable contain links and write to file--
    private boolean disableLink(String productID, long amount) {

        writeToFile();
        return true;
    }

// --Disable contain links and write to file--
    private boolean disableLink(String positionID) {
        int index = findLinkByPosition(positionID, true);
        if (index != -1) {
            linkList.get(index).setStatus(false);
            positionList.modify(positionID, true);
            writeToFile();
            return true;
        }
        return false;
    }

// --Console: Display inventory list--
    private void showInventoryList() {
        boolean check = false;
        for (Position i : positionList.getPositionList()) {
            if (!i.getStatus()) {
                check = true;
                break;
            }
        }

        if (!check) {
            System.out.println("---KHONG CO HANG TON KHO---");
            return;
        }
        System.out.println("---DANH SACH HANG TON KHO---");
        System.out.println(" ________ _____________ ____________ _______________ ");
        System.out.println("| Vi tri | ID san pham |  So luong  |So ngay ton kho|");
        for (ContainLink i : linkList) {
            if (i.getStatus()) {
                i.display();
            }
        }
        System.out.println(" ________ _____________ ____________ _______________ ");
    }

// --Console: --    
    private void showByProduct(String productID) {
        System.out.println("---TIM KIEM: " + productID + "---");
        System.out.println("---KET QUA---");
        if (findLinkByProduct(productID, true) == -1) {
            System.out.println("---KHONG CO HANG NAY TRONG KHO---");
            return;
        }

        System.out.println(" ________ _____________ ____________ _______________ ");
        System.out.println("| Vi tri | ID san pham |  So luong  |So ngay ton kho|");
        for (ContainLink i : linkList) {
            if (i.getStatus() && productID.equalsIgnoreCase(i.getProductID())) {
                i.display();
            }
        }
        System.out.println(" ________ _____________ ____________ _______________ ");
    }

// -----------------------------------------------------------------------------    
// --Console: Import--
    public boolean importPD() {
        Tools.cls();
        System.out.println("---NHAP HANG---");
        System.out.print("Nhap ngay thang nam (dd/mm/yyyy): ");
        String date = Tools.scan.nextLine();
        while (!Tools.isDate(date)) {
            System.out.print("Nhap ngay thang nam (dd/mm/yyyy): ");
            date = Tools.scan.nextLine();
        }

        System.out.print("Nhap tu: ");
        String from = Tools.scan.nextLine();
        System.out.print("Nguoi chiu trach nhiem: ");
        String respond = Tools.scan.nextLine();

        System.out.print("Co xuat hoa don khong (Y/N) ? ");
        char option = Tools.scan.next().charAt(0);
        if (option != 'N' && option != 'Y') {
            System.out.print("Co xuat hoa don khong (Y/N) ? ");
            option = Tools.scan.next().charAt(0);
        }
        Invoice invoice = null;
        if (option == 'Y') {
            System.out.print("Nhap ID hoa don: ");
            String invoiceID = Tools.scan.nextLine();
            while (!Tools.isInvoiceID(invoiceID) || invoiceList.isExist(invoiceID)) {
                if (invoiceList.isExist(invoiceID)) {
                    System.out.println("---ID da ton tai---");
                }
                System.out.print("Nhap ID hoa don: ");
                invoiceID = Tools.scan.nextLine();
            }
            invoice = new Invoice('I', invoiceID, date, respond, from);
        }

        System.out.println("Nhap tong so san pham trong danh sach:");
        String nStr = Tools.scan.nextLine();
        while (!Tools.isInteger(nStr)) {
            System.out.println("Nhap tong so san pham trong danh sach:");
            nStr = Tools.scan.nextLine();
        }
        int n = Integer.parseInt(nStr);

        // --Check position list--
        if (positionList.countEmptyPosition() < n) {
            System.out.println("---KHONG DU NOI CHUA---");
            return false;
        }

        System.out.println("Nhap thong tin san pham:");
        for (int i = 0; i < n;) {
            System.out.println(i + ")");

            System.out.print("Nhap ID san pham: ");
            String productID = Tools.scan.nextLine();
            while (!Tools.isProductID(productID)) {
                System.out.print("Nhap ID san pham: ");
                productID = Tools.scan.nextLine();
            }

            // --Get product index--
            int proIndex = productList.find(productID);
            if (-1 == proIndex) {
//                tạo sp mới trong productList. luu lai index
            }

            System.out.print("Nhap so luong nhap vao: ");
            String aStr = Tools.scan.nextLine();
            while (!Tools.isLong(aStr)) {
                System.out.print("Nhap so luong nhap vao: ");
                aStr = Tools.scan.nextLine();
            }
            Long amount = Long.parseLong(aStr);

            System.out.print("Nhap ID ke chua: ");
            String positionID = Tools.scan.nextLine();
            while (!Tools.isPositionID(positionID) || !positionList.isEmptyPosition(positionID)) {
                if (!positionList.isEmptyPosition(positionID)) {
                    System.out.println("---Ke da chua hang---");
                }
                System.out.print("Nhap ID ke chua: ");
                positionID = Tools.scan.nextLine();
            }

            // --Create a contain link--Update product list--Create an invoice (If)--
            if (addLink(positionID, productID, amount, date)) {
                System.out.println("---Nhap thanh cong---");
                long newAmount = productList.get(proIndex).getAmount() + amount;
                productList.modify(productID, newAmount);
                if (invoice != null) {
                    invoice.addMoreProduct(productID, productList.get(proIndex).getUnit(), amount, productList.get(proIndex).getPrice());
                }
            } else {
                System.out.println("---Nhap khong thanh cong---");
            }
        }
        System.out.println("---HOAN TAT NHAP HANG---");
        return true;
    }

// --Console: Export--    
    public boolean exportPD() {
        Tools.cls();
        System.out.println("---XUAT HANG---");
        System.out.print("Nhap ngay thang nam (dd/mm/yyyy): ");
        String date = Tools.scan.nextLine();
        while (!Tools.isDate(date)) {
            System.out.print("Nhap ngay thang nam (dd/mm/yyyy): ");
            date = Tools.scan.nextLine();
        }

        System.out.print("Xuat den: ");
        String to = Tools.scan.nextLine();
        System.out.print("Nguoi chiu trach nhiem: ");
        String respond = Tools.scan.nextLine();

        System.out.print("Co xuat hoa don khong (Y/N) ? ");
        char option = Tools.scan.next().charAt(0);
        if (option != 'N' && option != 'Y') {
            System.out.print("Co xuat hoa don khong (Y/N) ? ");
            option = Tools.scan.next().charAt(0);
        }
        Invoice invoice = null;
        if (option == 'Y') {
            System.out.print("Nhap ID hoa don: ");
            String invoiceID = Tools.scan.nextLine();
            while (!Tools.isInvoiceID(invoiceID) || invoiceList.isExist(invoiceID)) {
                if (invoiceList.isExist(invoiceID)) {
                    System.out.println("---ID da ton tai---");
                }
                System.out.print("Nhap ID hoa don: ");
                invoiceID = Tools.scan.nextLine();
            }
            invoice = new Invoice('E', invoiceID, date, respond, to);
        }

        System.out.println("Nhap tong so san pham xuat di:");
        String nStr = Tools.scan.nextLine();
        while (!Tools.isInteger(nStr)) {
            System.out.println("Nhap tong so san pham xuat di:");
            nStr = Tools.scan.nextLine();
        }
        int n = Integer.parseInt(nStr);

        // --Check--
        if (positionList.countEmptyPosition() < n) {
            System.out.println("---KHONG DU SAN PHAM---");
            return false;
        }

        System.out.println("Nhap thong tin san pham:");
        for (int i = 0; i < n; i++) {
            System.out.println(i + ")");

            System.out.print("Nhap ID san pham: ");
            String productID = Tools.scan.nextLine();
            while (!Tools.isProductID(productID)) {
                System.out.print("Nhap ID san pham: ");
                productID = Tools.scan.nextLine();
            }

            // --Check remain--
            int proIndex = productList.find(productID);
            if (-1 == proIndex || productList.get(proIndex).getAmount() == 0) {
                System.out.println("---San pham khong co trong kho---");
            } else {
                Long amount = productList.get(proIndex).getAmount();
                System.out.printf("Con %l san pham trong kho.\n", amount);
                System.out.print("Nhap so luong xuat di: ");
                String aStr = Tools.scan.nextLine();
                while (!Tools.isLong(aStr)) {
                    System.out.print("Nhap so luong nhap vao: ");
                    aStr = Tools.scan.nextLine();
                }
                if (Long.parseLong(aStr) > amount) {
                    System.out.println("So luong da nhap lon hon so luong trong kho. Tu dong xuat toi da.");
                } else {
                    amount = Long.parseLong(aStr);
                }

                // --Disable a contain link--Update product list--Create an invoice (If)--
                long newAmount = productList.get(proIndex).getAmount() - amount;
                productList.modify(productID, newAmount);
                if (invoice != null) {
                    invoice.addMoreProduct(productID, productList.get(proIndex).getUnit(), amount, productList.get(proIndex).getPrice());
                }
                disableLink(productID, amount);

                System.out.println("---Xuat thanh cong---");
            }
        }
        System.out.println("---HOAN TAT XUAT HANG---");
        return true;
    }

// --Console: Inventory--
    public void inventory() {
        Tools.cls();
        System.out.println("---KIEM KE---");
        System.out.println("0. Quay lai");
        System.out.println("1. Xem danh sach hang ton kho");
        System.out.println("2. Tra cuu hang hoa");
        System.out.println("3. Tra cuu vi tri");
        System.out.println("4. Thay doi vi tri hang hoa");
        System.out.println("5. Kiem kho");
        System.out.println("6. Thoat");
        System.out.print("Vui long nhap 1 so (0->6): ");
        String option = Tools.scan.nextLine();
        while (!Tools.isInteger(option) || Integer.parseInt(option) < 0 || Integer.parseInt(option) > 6) {
            System.out.print("Vui long nhap 1 so (0->6): ");
            option = Tools.scan.nextLine();
        }
        switch (Integer.parseInt(option)) {
            case 0: {
                return;
            }
            case 1: {
                showInventoryList();
                Tools.continute();
                break;
            }
            case 2: {
                System.out.print("Nhap ID san pham: ");
                String productID = Tools.scan.nextLine();
                while (!Tools.isProductID(productID)) {
                    System.out.print("Nhap ID san pham: ");
                    productID = Tools.scan.nextLine();
                }
                showByProduct(productID);
                Tools.continute();
                break;
            }
            case 3: {
                System.out.print("Nhap ID vi tri: ");
                String posID = Tools.scan.nextLine();
                while (!Tools.isPositionID(posID)) {
                    System.out.print("Nhap ID vi tri: ");
                    posID = Tools.scan.nextLine();
                }

                if (positionList.isExist(posID)) {
                    int index = findLinkByPosition(posID, true);
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
                Tools.continute();
                break;
            }
            case 4: {
                System.out.print("Nhap ID vi tri: ");
                String posID = Tools.scan.next();
                while (!Tools.isPositionID(posID)) {
                    System.out.print("Nhap ID vi tri: ");
                    posID = Tools.scan.next();
                }

                if (positionList.isExist(posID)) {
                    if (!positionList.isEmptyPosition(posID)) {
                        int linkIndex = findLinkByPosition(posID, true);
                        System.out.println("San pham: " + linkList.get(linkIndex).getProductID());
                        System.out.println("So luong: " + linkList.get(linkIndex).getAmount());
                        System.out.println("So ngay ton kho: " + linkList.get(linkIndex).getDays());

                        System.out.print("Nhap ID vi tri chuyen den: ");
                        String modifyPosID = Tools.scan.next();
                        while (!Tools.isPositionID(posID)) {
                            System.out.print("Nhap ID vi tri chuyen den: ");
                            modifyPosID = Tools.scan.next();
                        }

                        if (positionList.isEmptyPosition(modifyPosID)) {
                            ContainLink e = linkList.get(linkIndex);
                            e.setPositionID(modifyPosID);
                            linkList.set(linkIndex, e);
                            writeToFile();
                            positionList.modify(posID, true);
                            positionList.modify(modifyPosID, false);
                            System.out.println("---Chuyen thanh cong---");
                        } else {
                            System.out.println("---Vi tri moi da chua hang---");
                        }

                    } else {
                        System.out.println("---Vi tri dang trong---");
                    }
                } else {
                    System.out.println("---Vi tri khong ton tai---");
                }
                Tools.continute();
                break;
            }
            case 5: {
                Tools.cls();
                System.out.println("---KIEM KHO---");
                boolean check = false;
                for (Position i : positionList.getPositionList()) {
                    if (!i.getStatus()) {
                        check = true;
                        int index = findLinkByPosition(i.getID(), true);
                        ContainLink e = linkList.get(index);
                        e.enterInventory();
                        linkList.set(index, e);
                    }
                }

                if (check) {
                    System.out.println(" ________ _____________ ____________ ____________ ____________ ");
                    System.out.println("| Vi tri | ID san pham |  So luong  |  Thuc te   | Chenh lech |");
                    for (Position i : positionList.getPositionList()) {
                        if (!i.getStatus()) {
                            int index = findLinkByPosition(i.getID(), true);
                            linkList.get(index).displayInventory();
                        }
                    }
                    System.out.println(" ________ _____________ ____________ ____________ ____________ ");
                } else {
                    System.out.println("---Kho hang trong---");
                }
                Tools.continute();
                break;
            }

            case 6: {
                System.exit(1);
            }
        }
    }

// --Write list contain link to file--    
    public boolean writeToFile() {
        File file = null;
        FileOutputStream fout = null;
        try {
            file = new File(filePath);
            fout = new FileOutputStream(file);

            // --Create file if not exists--
            if (!file.exists()) {
                file.createNewFile();
            }

            for (ContainLink e : linkList) {
                byte[] bs = e.toString().getBytes();
                fout.write(bs);
                fout.flush();
            }
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            try {
                if (fout != null) {
                    fout.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

//--Getter-Setter---------------------------------------------------------------
    public PositionList getPositionList() {
        return positionList;
    }

    public void setPositionList(PositionList positionList) {
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
