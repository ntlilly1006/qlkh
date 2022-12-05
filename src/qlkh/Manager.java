package qlkh;

import java.io.*;
import java.util.*;

/**
 *
 * @author Lilly
 */
public class Manager {

    public PositionList positionList;
    public ProductIF productList;
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
                    E = line.split(",");
                }
                if (E.length >= 5) {
                    if (Tools.isPositionID(E[0]) && Tools.isProductID(E[1]) && Tools.isLong(E[2]) && Tools.isDate(E[3])) {
                        addLink(E[0], E[1], Long.parseLong(E[2]), E[3]);
                        if (E[4].equalsIgnoreCase("false")) {
                            disableLink(linkList.get(linkList.size() - 1).getPositionID());
                        }
                    }
                }
            }
        } catch (Exception ex) {
            // --Default init--
            addLink("A01", "P01", 12, "06/10/2022");
            addLink("A02", "P02", 12, "06/10/2022");
            addLink("A03", "P03", 20, "06/10/2022");
            addLink("A04", "P04", 4, "06/10/2022");
            writeToFile();
        } finally {
            try {
                if (scanFile != null) {
                    scanFile.close();
                }
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
            } catch (Exception ex) {
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
    private boolean addLink(String posID, String productID, long amount, String inputDate) {
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
    private boolean disableLink(String positionID) {
        int index = findLinkByPosition(positionID, true);
        if (index != -1) {
            ContainLink e = linkList.get(index);
            e.setStatus(false);
            linkList.set(index, e);
            positionList.modify(positionID, true);
            writeToFile();
            return true;
        }
        return false;
    }

// --Disable contain links and write to file--
    private boolean disableLink(String productID, long amount) {
        int index = findLinkByProduct(productID, true);
        if (index == -1) {
            return false;
        }
        while (amount > 0 && index != -1) {
            if (amount >= linkList.get(index).getAmount()) {
                amount -= linkList.get(index).getAmount();
                disableLink(linkList.get(index).getPositionID());
                index = findLinkByProduct(productID, true);
            } else {
                amount = linkList.get(index).getAmount() - amount;
                linkList.get(index).setAmount(amount);
                amount = 0;
            }
        }
        writeToFile();
        return true;
    }
    
// --Active Link Count--
    public int countActiveLink() {
        int n = 0;
        for (ContainLink i : linkList) {
            if (i.getStatus()) {
                n++;
            }
        }
        return n;
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
    public boolean importPD(String respond) {
        Tools.cls();
        System.out.println("---NHAP HANG---");
        System.out.print("Nhap ngay thang nam (dd/mm/yyyy): ");
        String date = Tools.scan.nextLine();
        while (!Tools.isDate(date)) {
            System.out.print("Nhap ngay thang nam (dd/mm/yyyy): ");
            date = Tools.scan.nextLine();
        }

        System.out.print("ID nha cung cap: ");
        String from = Tools.scan.nextLine();
        while (!Tools.isSupplierID(from)) {
            System.out.print("ID nha cung cap (Vd S01):  ");
            from = Tools.scan.nextLine();
        }

        System.out.print("Co xuat hoa don khong (Y/N) ? ");
        char option = Tools.scan.nextLine().charAt(0);
        while (option != 'N' && option != 'Y') {
            System.out.print("Co xuat hoa don khong (Y/N) ? ");
            option = Tools.scan.nextLine().charAt(0);
        }
        Invoice invoice = null;
        if (option == 'Y') {
            invoice = new Invoice('I', invoiceList.getNextImportID(), date, respond, from);
        }

        // --Check position list--
        int i = 0;
        int n = positionList.countEmptyPosition();
        if (n <= 0) {
            System.out.println("---KHO HANG DA DAY---");
            return false;
        }

        System.out.println("Nhap thong tin san pham:");
        String hasNext = "Y";
        while (!hasNext.startsWith("N") && (i < n)) {
            System.out.print("Nhap ID san pham: ");
            String productID = Tools.scan.nextLine();
            while (!Tools.isProductID(productID)) {
                System.out.print("Nhap ID san pham (Vd P01): ");
                productID = Tools.scan.nextLine();
            }

            // --Get product index--
            int proIndex = productList.findIndex(productID);
            if (-1 == proIndex) {
                System.out.println("San pham nhap lan dau. Vui long nhap thong tin san pham.");
                productList.add();
                proIndex = productList.findIndex(productID);
            }

            System.out.print("Nhap so luong nhap vao: ");
            String aStr = Tools.scan.nextLine();
            while (!Tools.isLong(aStr)) {
                System.out.print("Nhap so luong nhap vao: ");
                aStr = Tools.scan.nextLine();
            }
            long amount = Long.parseLong(aStr);

            System.out.print("Nhap ID ke chua: ");
            String positionID = Tools.scan.nextLine();
            while (!Tools.isPositionID(positionID) || !positionList.isEmptyPosition(positionID)) {
                if (!Tools.isPositionID(positionID) || !positionList.isExist(positionID)) {
                    System.out.println("---Nhap sai---");
                } else if (!positionList.isEmptyPosition(positionID)) {
                    System.out.println("---Ke da chua hang---");
                }
                System.out.print("Nhap ID ke chua (Vd A01): ");
                positionID = Tools.scan.nextLine();
            }

            // --Create a contain link--Update product list--Create an invoice (If)--
            if (addLink(positionID, productID, amount, date)) {
                long newAmount = productList.get(proIndex).getAmount() + amount;
                productList.modify(productID, newAmount);
                if (invoice != null) {
                    invoice.addMoreProduct(productID, productList.get(proIndex).getUnit(), amount, productList.get(proIndex).getPrice());
                }
                i++;
                System.out.println("---Nhap thanh cong---");
            } else {
                System.out.println("---Nhap khong thanh cong---");
            }
            if (i < n) {
                System.out.print("Them san pham (Y/N)? ");
                hasNext = Tools.scan.nextLine();
                while (!hasNext.startsWith("N") && !hasNext.startsWith("Y")) {
                    System.out.print("Them san pham (Y/N)? ");
                    hasNext = Tools.scan.nextLine();
                }
            } else {
                System.out.println("---KHO HANG DA DAY---");
            }
        }
        if (invoice != null) {
            invoiceList.add(invoice);
        }
        System.out.println("---HOAN TAT NHAP HANG---");
        return true;
    }

// --Console: Export--    
    public boolean exportPD(String respond) {
        Tools.cls();
        System.out.println("---XUAT HANG---");
        System.out.print("Nhap ngay thang nam (dd/mm/yyyy): ");
        String date = Tools.scan.nextLine();
        while (!Tools.isDate(date)) {
            System.out.print("Nhap ngay thang nam (dd/mm/yyyy): ");
            date = Tools.scan.nextLine();
        }

        System.out.print("ID nha phan phoi: ");
        String to = Tools.scan.nextLine();
        while (!Tools.isDistributorID(to)) {
            System.out.print("ID nha phan phoi (Vd D01): ");
            to = Tools.scan.nextLine();
        }

        System.out.print("Co xuat hoa don khong (Y/N) ? ");
        char option = Tools.scan.nextLine().charAt(0);
        if (option != 'N' && option != 'Y') {
            System.out.print("Co xuat hoa don khong (Y/N) ? ");
            option = Tools.scan.nextLine().charAt(0);
        }
        Invoice invoice = null;
        if (option == 'Y') {
            invoice = new Invoice('E', invoiceList.getNextExportID(), date, respond, to);
        }
        if (countActiveLink() <= 0) {
            System.out.println("---KHO HANG DANG TRONG---");
            return false;
        }

        System.out.println("Nhap thong tin san pham xuat di:");
        String hasNext = "Y";
        while (!hasNext.startsWith("N") && countActiveLink() > 0) {
            System.out.print("Nhap ID san pham: ");
            String productID = Tools.scan.nextLine();
            while (!Tools.isProductID(productID)) {
                System.out.print("Nhap ID san pham (Vd P01): ");
                productID = Tools.scan.nextLine();
            }

            // --Check remain--
            int proIndex = productList.findIndex(productID);
            if (-1 == proIndex || productList.get(proIndex).getAmount() == 0) {
                System.out.println("---San pham khong co trong kho---");
            } else {
                long amount = productList.get(proIndex).getAmount();
                System.out.println("Con " + amount + " san pham trong kho.");
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
            if (countActiveLink() > 0) {
                System.out.print("Them san pham (Y/N)? ");
                hasNext = Tools.scan.nextLine();
                while (!(hasNext.startsWith("N") && hasNext.startsWith("Y"))) {
                    System.out.print("Them san pham (Y/N)? ");
                    hasNext = Tools.scan.nextLine();
                }
            } else {
                System.out.println("---KHO HANG DA TRONG---");
                break;
            }
        }
        if (invoice != null) {
            invoiceList.add(invoice);
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
                    System.out.print("Nhap ID vi tri (Vd A01): ");
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
                String posID = Tools.scan.nextLine();
                while (!Tools.isPositionID(posID)) {
                    System.out.print("Nhap ID vi tri (Vd A01): ");
                    posID = Tools.scan.nextLine();
                }

                if (positionList.isExist(posID)) {
                    if (!positionList.isEmptyPosition(posID)) {
                        int linkIndex = findLinkByPosition(posID, true);
                        System.out.println("San pham: " + linkList.get(linkIndex).getProductID());
                        System.out.println("So luong: " + linkList.get(linkIndex).getAmount());
                        System.out.println("So ngay ton kho: " + linkList.get(linkIndex).getDays());

                        System.out.print("Nhap ID vi tri chuyen den: ");
                        String modifyPosID = Tools.scan.nextLine();
                        while (!Tools.isPositionID(posID)) {
                            System.out.print("Nhap ID vi tri chuyen den (Vd A01): ");
                            modifyPosID = Tools.scan.nextLine();
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
                        ContainLink e = null;
                        if (index != -1) {
                            e = linkList.get(index);
                            e.enterInventory();
                            linkList.set(index, e);
                        }
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

    public ProductIF getProductList() {
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
