package qlkh;

import java.io.*;
import java.util.*;

/**
 *
 * @author Lilly
 */
public class InvoiceList {

    private ArrayList<Invoice> invoiceList;
    private String filePath = "InvoiceList.txt";

// --Constructor----------------------------------------------------------------    
    public InvoiceList() {
        invoiceList = new ArrayList<Invoice>();

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
                if (E.length >= 6) {
                    char iE = E[0].charAt(0);
                    String invoiceID = E[1];
                    String date = E[2];
                    String prepareBy = E[3];
                    String receivedBy = E[4];

                    // --Valid invoice check-- Bo sung check nguoi ghi hoa don ng nhan hoa don 
                    Invoice element = null;
                    if ((iE == 'I' || iE == 'E')
                            && Tools.isInvoiceID(invoiceID)
                            && Tools.isDate(date)) {
                        element = new Invoice(iE, invoiceID, date, prepareBy, receivedBy);

                        // --Add product list--
                        int i = 5;
                        for (; i < (E.length - 1);) {
                            String productID = E[i++];
                            String unit = E[i++];
                            String aStr = E[i++];
                            String pStr = E[i++];

                            // --Valid product check--
                            if (Tools.isLong(aStr) && Tools.isDouble(pStr)) {
                                element.addMoreProduct(productID, unit, Long.parseLong(aStr), Double.parseDouble(pStr));
                            }
                        }
                        add(element);
                    }
                }
            }
        } catch (FileNotFoundException ex) {
            // --Default init--
            Invoice e1 = new Invoice('I', "I01", "11/11/2022", "staff01", "S01");
            e1.addMoreProduct("P01", "Cai", Math.round(Math.random() * 101) + 1, Math.round(Math.random() * 30000001) + 1);
            e1.addMoreProduct("P03", "Cai", Math.round(Math.random() * 101) + 1, Math.round(Math.random() * 30000001) + 1);
            e1.addMoreProduct("P05", "Cai", Math.round(Math.random() * 101) + 1, Math.round(Math.random() * 30000001) + 1);
            e1.addMoreProduct("P08", "Cai", Math.round(Math.random() * 101) + 1, Math.round(Math.random() * 30000001) + 1);
            invoiceList.add(e1);
            
            Invoice e2 = new Invoice('I', "I02", "12/11/2022", "staff02", "S03");
            e2.addMoreProduct("P01", "Cai", Math.round(Math.random() * 101) + 1, Math.round(Math.random() * 30000001) + 1);
            e2.addMoreProduct("P02", "Cai", Math.round(Math.random() * 101) + 1, Math.round(Math.random() * 30000001) + 1);
            e2.addMoreProduct("P04", "Cai", Math.round(Math.random() * 101) + 1, Math.round(Math.random() * 30000001) + 1);
            e2.addMoreProduct("P07", "Cai", Math.round(Math.random() * 101) + 1, Math.round(Math.random() * 30000001) + 1);
            invoiceList.add(e2);
            
            Invoice e3 = new Invoice('I', "I03", "14/11/2022", "staff01", "S05");
            e3.addMoreProduct("P02", "Cai", Math.round(Math.random() * 101) + 1, Math.round(Math.random() * 30000001) + 1);
            e3.addMoreProduct("P04", "Cai", Math.round(Math.random() * 101) + 1, Math.round(Math.random() * 30000001) + 1);
            e3.addMoreProduct("P06", "Cai", Math.round(Math.random() * 101) + 1, Math.round(Math.random() * 30000001) + 1);
            invoiceList.add(e3);
            
            Invoice e4 = new Invoice('I', "I04", "17/11/2022", "staff02", "S09");
            e4.addMoreProduct("P09", "Cai", Math.round(Math.random() * 101) + 1, Math.round(Math.random() * 30000001) + 1);
            e4.addMoreProduct("P10", "Cai", Math.round(Math.random() * 101) + 1, Math.round(Math.random() * 30000001) + 1);
            invoiceList.add(e4);
            
            Invoice e5 = new Invoice('I', "I05", "19/11/2022", "staff01", "S07");
            e5.addMoreProduct("P02", "Cai", Math.round(Math.random() * 101) + 1, Math.round(Math.random() * 30000001) + 1);
            e5.addMoreProduct("P05", "Cai", Math.round(Math.random() * 101) + 1, Math.round(Math.random() * 30000001) + 1);
            e5.addMoreProduct("P08", "Cai", Math.round(Math.random() * 101) + 1, Math.round(Math.random() * 30000001) + 1);
            invoiceList.add(e5);
            
            Invoice e6 = new Invoice('E', "E01", "13/11/2022", "staff02", "D02");
            e6.addMoreProduct("P01", "Cai", Math.round(Math.random() * 101) + 1, Math.round(Math.random() * 30000001) + 1);
            e6.addMoreProduct("P05", "Cai", Math.round(Math.random() * 101) + 1, Math.round(Math.random() * 30000001) + 1);
            e6.addMoreProduct("P08", "Cai", Math.round(Math.random() * 101) + 1, Math.round(Math.random() * 30000001) + 1);
            invoiceList.add(e6);
            
            Invoice e7 = new Invoice('E', "E02", "15/11/2022", "staff01", "D04");
            e7.addMoreProduct("P04", "Cai", Math.round(Math.random() * 101) + 1, Math.round(Math.random() * 30000001) + 1);
            e7.addMoreProduct("P07", "Cai", Math.round(Math.random() * 101) + 1, Math.round(Math.random() * 30000001) + 1);
            invoiceList.add(e7);
            
            Invoice e8 = new Invoice('E', "E03", "18/11/2022", "staff02", "D06");
            e8.addMoreProduct("P02", "Cai", Math.round(Math.random() * 101) + 1, Math.round(Math.random() * 30000001) + 1);
            e8.addMoreProduct("P03", "Cai", Math.round(Math.random() * 101) + 1, Math.round(Math.random() * 30000001) + 1);
            e8.addMoreProduct("P06", "Cai", Math.round(Math.random() * 101) + 1, Math.round(Math.random() * 30000001) + 1);
            e8.addMoreProduct("P09", "Cai", Math.round(Math.random() * 101) + 1, Math.round(Math.random() * 30000001) + 1);
            invoiceList.add(e7);
            
            Invoice e9 = new Invoice('E', "E04", "20/11/2022", "staff01", "D03");
            e9.addMoreProduct("P08", "Cai", Math.round(Math.random() * 101) + 1, Math.round(Math.random() * 30000001) + 1);
            e9.addMoreProduct("P10", "Cai", Math.round(Math.random() * 101) + 1, Math.round(Math.random() * 30000001) + 1);
            invoiceList.add(e9);
            
            Invoice e10 = new Invoice('E', "E10", "21/11/2022", "staff02", "D07");
            e10.addMoreProduct("P01", "Cai", Math.round(Math.random() * 101) + 1, Math.round(Math.random() * 30000001) + 1);
            e10.addMoreProduct("P06", "Cai", Math.round(Math.random() * 101) + 1, Math.round(Math.random() * 30000001) + 1);
            e10.addMoreProduct("P07", "Cai", Math.round(Math.random() * 101) + 1, Math.round(Math.random() * 30000001) + 1);
            invoiceList.add(e10);
            
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

    public InvoiceList(ArrayList<Invoice> invoiceList) {
        this.invoiceList = new ArrayList<Invoice>(invoiceList);
        writeToFile();
    }

// -----------------------------------------------------------------------------
// --Find by ID, return index--
    public int find(String invoiceID) {
        if (!invoiceList.isEmpty() && Tools.isInvoiceID(invoiceID)) {
            for (int index = 0; index < invoiceList.size(); index++) {
                String temp = invoiceList.get(index).getInvoiceID();
                if (invoiceID.equalsIgnoreCase(temp)) {
                    return index;
                }
            }
        }
        return -1;
    }

// --InvoiceID Exist In The List Check--
    public boolean isExist(String invoiceID) {
        if (find(invoiceID) != -1) {
            return true;
        }
        return false;
    }

// --Count import/export invoice--    
    public int countByIE(char iE) {
        int n = 0;
        for (Invoice i : invoiceList) {
            if (iE == i.getIE()) {
                n++;
            }
        }
        return n;
    }

// --Count by date--    
    public int countByDate(String date) {
        int n = 0;
        for (Invoice i : invoiceList) {
            if (date.equalsIgnoreCase(i.getDate())) {
                n++;
            }
        }
        return n;
    }

// --Count by preparer--    
    public int countByPreparer(String prepareBy) {
        int n = 0;
        for (Invoice i : invoiceList) {
            if (prepareBy.equalsIgnoreCase(i.getPrepareBy())) {
                n++;
            }
        }
        return n;
    }

// --Count by receiver--    
    public int countByReceiver(String receivedBy) {
        int n = 0;
        for (Invoice i : invoiceList) {
            if (receivedBy.equalsIgnoreCase(i.getReceivedBy())) {
                n++;
            }
        }
        return n;
    }

// --Add new invoice and write to file--    
    public boolean add(Invoice element) {
        if (!invoiceList.add(element)) {
            return false;
        }
        writeToFile();
        return false;
    }

// --Console: Add new invoice--
    public boolean add() {
        System.out.println("---THEM HOA DON MOI---");
        System.out.println("--CANH BAO--");
        System.out.println("Them hoa don moi khong thay doi danh sach san pham");
        System.out.println("Huy bo them ?");
        System.out.println("Dong y. (Y)");
        System.out.println("Khong dong y, tiep tuc them hoa don. (N)");
        char option = Tools.scan.next().charAt(0);
        if (option == 'Y') {
            return false;
        }

        Invoice element = new Invoice();
        element.enter();

        if (!add(element) || isExist(element.getInvoiceID())) {
            System.out.println("---Them khong thanh cong---");
            return false;
        }
        System.out.println("---Them thanh cong---");
        return true;
    }

// --Remove invoice and update file--
    public boolean remove(Invoice element) {
        if (!invoiceList.remove(element)) {
            return false;
        }
        writeToFile();
        return true;
    }

// --Remove invoice by ID--
    public boolean remove(String invoiceID) {
        int index = find(invoiceID);
        if (index != -1) {
            return remove(invoiceList.get(index));
        }
        return false;
    }

// --Console: Remove invoice--
    public boolean remove() {
        System.out.println("---XOA HOA DON---");
        System.out.println("--CANH BAO--");
        System.out.println("Xoa hoa don moi khong thay doi danh sach san pham");
        System.out.println("Huy bo xoa ?");
        System.out.println("Dong y. (Y)");
        System.out.println("Khong dong y, tiep tuc xoa hoa don. (N)");
        char option = Tools.scan.next().charAt(0);
        if (option == 'Y') {
            return false;
        }

        System.out.print("Nhap ID hoa don: ");
        String invoiceID = Tools.scan.nextLine();
//        while (!Tools.isInvoiceID(invoiceID)) {
//            System.out.print("Nhap ID hoa don: ");
//            invoiceID = Tools.scan.nextLine();
//        }

        if (!remove(invoiceID)) {
            System.out.println("---Xoa khong thanh cong---");
            return false;
        }
        System.out.println("---Xoa thanh cong---");
        return true;
    }

// --Console: Show all invoice--
    private void showAll() {
        if (invoiceList.isEmpty()) {
            System.out.println("---KHONG CO HOA DON NAO---");
            return;
        }
        System.out.println("---TAT CA HOA DON---");
        System.out.println(" _______________ _____________ __________ ______________ _______________ ");
        System.out.println("|   Ma hoa don  |  Thoi gian  |   Loai   |   Nguoi lap  |   Nguoi nhan  |");
        for (Invoice e : invoiceList) {
            e.display();
        }
        System.out.println(" _______________ _____________ __________ ______________ _______________ ");
    }

// --Console: Show all import/export invoice--
    private void showByIE(char iE) {
        boolean check = false;
        for (Invoice e : invoiceList) {
            if (iE == e.getIE()) {
                check = true;
                break;
            }
        }
        if (!check) {
            System.out.println("---KHONG CO HOA DON THOA YEU CAU---");
            return;
        }
        if (iE == 'I') {
            System.out.println("---TAT CA HOA DON NHAP HANG---");
        } else if (iE == 'E') {
            System.out.println("---TAT CA HOA DON XUAT HANG---");
        }
        System.out.println(" _______________ _____________ __________ ______________ _______________ ");
        System.out.println("|   Ma hoa don  |  Thoi gian  |   Loai   |   Nguoi lap  |   Nguoi nhan  |");
        for (Invoice e : invoiceList) {
            if (iE == e.getIE()) {
                e.display();
            }
        }
        System.out.println(" _______________ _____________ __________ ______________ _______________ ");
    }

// --Console: Show invoice by date--
    private void showByDate(String date) {
        boolean check = false;
        for (Invoice e : invoiceList) {
            if (date.equalsIgnoreCase(e.getDate())) {
                check = true;
                break;
            }
        }
        if (!check) {
            System.out.println("---KHONG CO HOA DON THOA YEU CAU---");
            return;
        }
        System.out.println("---HOA DON NGAY " + date + "---");
        System.out.println(" _______________ _____________ __________ ______________ _______________ ");
        System.out.println("|   Ma hoa don  |  Thoi gian  |   Loai   |   Nguoi lap  |   Nguoi nhan  |");
        for (Invoice e : invoiceList) {
            if (date.equalsIgnoreCase(e.getDate())) {
                e.display();
            }
        }
        System.out.println(" _______________ _____________ __________ ______________ _______________ ");
    }

// --Console: Show invoice by preparer--
    private void showByPreparer(String name) {
        boolean check = false;
        for (Invoice e : invoiceList) {
            if (name.equalsIgnoreCase(e.getPrepareBy())) {
                check = true;
                break;
            }
        }
        if (!check) {
            System.out.println("---KHONG CO HOA DON THOA YEU CAU---");
            return;
        }
        System.out.println("---HOA DON LAP BOI " + name + "---");
        System.out.println(" _______________ _____________ __________ ______________ _______________ ");
        System.out.println("|   Ma hoa don  |  Thoi gian  |   Loai   |   Nguoi lap  |   Nguoi nhan  |");
        for (Invoice e : invoiceList) {
            if (name.equalsIgnoreCase(e.getPrepareBy())) {
                e.display();
            }
        }
        System.out.println(" _______________ _____________ __________ ______________ _______________ ");
    }

// --Console: Show invoice by receiver--
    private void showByReceiver(String name) {
        boolean check = false;
        for (Invoice e : invoiceList) {
            if (name.equalsIgnoreCase(e.getReceivedBy())) {
                check = true;
                break;
            }
        }
        if (!check) {
            System.out.println("---KHONG CO HOA DON THOA YEU CAU---");
            return;
        }
        System.out.println("---HOA DON DO " + name + " NHAN---");
        System.out.println(" _______________ _____________ __________ ______________ _______________ ");
        System.out.println("|   Ma hoa don  |  Thoi gian  |   Loai   |   Nguoi lap  |   Nguoi nhan  |");
        for (Invoice e : invoiceList) {
            if (name.equalsIgnoreCase(e.getReceivedBy())) {
                e.display();
            }
        }
        System.out.println(" _______________ _____________ __________ ______________ _______________ ");
    }

// --Console: Show--
    public void show() {
        Tools.cls();
        System.out.println("---XEM HOA DON---");
        System.out.println("0. Quay lai");
        System.out.println("1. Tat ca hoa don");
        System.out.println("2. Hoa don nhap");
        System.out.println("3. Hoa don xuat");
        System.out.println("4. Loc theo ngay");
        System.out.println("5. Loc theo nguoi lap");
        System.out.println("6. Loc theo nguoi nhan");
        System.out.println("7. Chi tiet hoa don");
        System.out.println("8. Thoat");
        System.out.print("Vui long nhap 1 so (0->8): ");
        String option = Tools.scan.nextLine();
        while (!Tools.isInteger(option) || Integer.parseInt(option) < 0 || Integer.parseInt(option) > 8) {
            System.out.print("Vui long nhap 1 so (0->8): ");
            option = Tools.scan.nextLine();
        }
        switch (Integer.parseInt(option)) {
            case 0 -> {
                return;
            }
            case 1 -> {
                showAll();
                Tools.continute();
                break;
            }
            case 2 -> {
                showByIE('I');
                Tools.continute();
                break;
            }
            case 3 -> {
                showByIE('E');
                Tools.continute();
                break;
            }
            case 4 -> {
                System.out.print("Nhap ngay muon xem (dd/mm/yyyy): ");
                String date = Tools.scan.nextLine();
                while (!Tools.isDate(date)) {
                    System.out.print("Nhap ngay muon xem (dd/mm/yyyy): ");
                    date = Tools.scan.nextLine();
                }
                showByDate(date);
                Tools.continute();
                break;
            }
            case 5 -> {
                System.out.print("Nhap ten nguoi lap: ");
                String name = Tools.scan.next();
                showByPreparer(name);
                Tools.continute();
                break;
            }
            case 6 -> {
                System.out.print("Nhap ten nguoi nhan: ");
                String name = Tools.scan.next();
                showByReceiver(name);
                Tools.continute();
                break;
            }
            case 7 -> {
                System.out.print("Nhap ID hoa don: ");
                String invoiceID = Tools.scan.next();
                int index = find(invoiceID);
                if (-1 != index) {
                    invoiceList.get(index).displayDetails();
                } else {
                    System.out.println("---Khong tim thay hoa don---");
                }
                Tools.continute();
                break;
            }
            case 8 -> {
                System.exit(1);
            }
        }
    }

// --Console: Display menu for invoice--
    public void menu() {
        Tools.cls();
        System.out.println("---TRUY XUAT HOA DON---");
        System.out.println("0. Quay lai");
        System.out.println("1. Xem danh sach hoa don");
        System.out.println("2. Them hoa don moi");
        System.out.println("3. Xoa hoa don");
        System.out.println("4. Thoat");
        System.out.print("Vui long nhap 1 so (0->4): ");
        String option = Tools.scan.nextLine();
        while (!Tools.isInteger(option) || Integer.parseInt(option) < 0 || Integer.parseInt(option) > 4) {
            System.out.print("Vui long nhap 1 so (0->4): ");
            option = Tools.scan.nextLine();
        }
        switch (Integer.parseInt(option)) {
            case 0: {
                return;
            }
            case 1: {
                show();
                break;
            }
            case 2: {
                add();
                Tools.continute();
                break;
            }
            case 3: {
                remove();
                Tools.continute();
                break;
            }
            case 4: {
                System.exit(1);
            }
        }
    }

// --Write to file--    
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

            for (Invoice e : invoiceList) {
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

// --Getter-Setter--------------------------------------------------------------
    public ArrayList<Invoice> getInvoiceList() {
        return invoiceList;
    }

    public void setInvoiceList(ArrayList<Invoice> invoiceList) {
        this.invoiceList = invoiceList;
        writeToFile();
    }

    public Invoice get(int index) {
        return invoiceList.get(index);
    }

    public void set(int index, Invoice element) {
        invoiceList.set(index, element);
        writeToFile();
    }
}
