package qlkh;

/**
 * DONE
 *
 * @author Lilly
 */
import java.util.*;

public class Invoice {

    private char iE; //I: Nhap | E: Xuat
    private String invoiceID; //X00
    private String date;
    private String prepareBy;
    private String receivedBy;
    private ArrayList<InvoiceProduct> productListInvoice;
    private double totalPrice;

// --Constructor----------------------------------------------------------------    
    public Invoice() {
        productListInvoice = new ArrayList<>();
    }

    public Invoice(char iE, String invoiceID, String date, String prepareBy, String receivedBy) {
        this.iE = iE;
        this.invoiceID = invoiceID;
        this.date = date;
        this.prepareBy = prepareBy;
        this.receivedBy = receivedBy;
        this.productListInvoice = new ArrayList<>();
    }

    public Invoice(char iE, String invoiceID, String date, ArrayList<InvoiceProduct> productListInvoice,
            double totalPrice, String prepareBy, String receivedBy) {
        this.iE = iE;
        this.invoiceID = invoiceID;
        this.date = date;
        this.prepareBy = prepareBy;
        this.receivedBy = receivedBy;
        this.productListInvoice = new ArrayList<>(productListInvoice);
        this.totalPrice = totalPrice;
    }

    public Invoice(Invoice other) {
        this.iE = other.iE;
        this.invoiceID = other.invoiceID;
        this.date = other.date;
        this.productListInvoice = new ArrayList<>(other.productListInvoice);
        this.prepareBy = other.prepareBy;
        this.receivedBy = other.receivedBy;
        this.totalPrice = other.totalPrice;
    }

// -----------------------------------------------------------------------------    
// --Add more product to invoice--   
    public boolean addMoreProduct(String productID, String unit, long amount, double price) {
        if (!Tools.isProductID(productID) || !Tools.isUnit(unit)) {
            return false;
        }

        InvoiceProduct e = new InvoiceProduct(productID, unit, amount, price);
        if (!productListInvoice.add(e)) {
            return false;
        }
        getTotalPrice();
        return true;
    }

// --Console: Add more product to invoice--   
    public boolean addMoreProduct() {
        InvoiceProduct e = new InvoiceProduct();
        e.enter();
        if (!productListInvoice.add(e)) {
            return false;
        }
        getTotalPrice();
        return true;
    }

// --Console: Enter a invoice--    
    public void enter() {
        Tools.cls();
        System.out.println("---XUAT HOA DON---");

        do {
            System.out.print("Nhap vao kho (I) hay xuat khoi kho (E): ");
            iE = Tools.scan.nextLine().charAt(0);
        } while (iE != 'I' && iE != 'E');

        do {
            System.out.print("Nhap ID hoa don (Vd I01, E01, ...): ");
            invoiceID = Tools.scan.nextLine();
        } while (!Tools.isInvoiceID(invoiceID));

        do {
            System.out.print("Nhap ngay thang nam (dd/mm/yyyy): ");
            date = Tools.scan.nextLine();
        } while (!Tools.isDate(date));

        do {
            System.out.print("Nguoi lap hoa don (ID nhan vien vd staff01): ");
            prepareBy = Tools.scan.nextLine();
        } while (!Tools.isStaffID(prepareBy));

        if (iE == 'I') {
            do {
                System.out.print("Nguoi nhan hoa don (ID nha cung cap vd S01): ");
                receivedBy = Tools.scan.nextLine();
            } while (!Tools.isSupplierID(receivedBy));
        }
        if (iE == 'E') {
            do {
                System.out.print("Nguoi nhan hoa don (ID nha phan phoi vd D01): ");
                receivedBy = Tools.scan.nextLine();
            } while (!Tools.isDistributorID(receivedBy));
        }

        System.out.println("Nhap thong tin san pham:");
        String hasNext = "Y";
        while (!hasNext.startsWith("N")) {
            if (addMoreProduct()) {
                System.out.print("Them san pham (Y/N)? ");
                hasNext = Tools.scan.nextLine();
                while (!(hasNext.startsWith("N") && hasNext.startsWith("Y"))) {
                    System.out.print("Them san pham (Y/N)? ");
                    hasNext = Tools.scan.nextLine();
                }
            }
        }
        System.out.println("Tong gia tri hoa don: " + totalPrice);
    }

// --Console: Display details--   
    private void displayImportInvoice() {
        Tools.cls();
        System.out.println("---HOA DON NHAP HANG---");
        System.out.println("ID hoa don: " + invoiceID);
        System.out.println("Thoi gian: " + date);
        System.out.println("Nhap tu: " + receivedBy);
        System.out.println(" _____________ _____________ ____________ ___________ ");
        System.out.println("| ID san pham | Don vi tinh |  So luong  |  Don gia  |");
        for (InvoiceProduct e : productListInvoice) {
            e.display();
        }
        System.out.println(" _____________ _____________ ____________ ___________ ");
        System.out.println("Tong gia tri hoa don: " + totalPrice);
        System.out.println("\n--Hoa don duoc lap boi " + prepareBy + "--");
    }

    private void displayExportInvoice() {
        Tools.cls();
        System.out.println("---HOA DON XUAT HANG---");
        System.out.println("ID hoa don: " + invoiceID);
        System.out.println("Thoi gian: " + date);
        System.out.println("Xuat cho: " + receivedBy);
        System.out.println(" _____________ _____________ ____________ ___________ ");
        System.out.println("| ID san pham | Don vi tinh |  So luong  |  Don gia  |");
        for (InvoiceProduct e : productListInvoice) {
            e.display();
        }
        System.out.println(" _____________ _____________ ____________ ___________ ");
        System.out.println("Tong gia tri hoa don: " + totalPrice);
        System.out.println("\n--Hoa don duoc lap boi " + prepareBy + "--");
    }

    public void displayDetails() {
        if (iE == 'I') {
            displayImportInvoice();
        } else if (iE == 'E') {
            displayExportInvoice();
        }
    }

// --Console: Display table--   
    public void display() {
        getTotalPrice();
        System.out.printf("|   %-12s| %-12s|   %-7s| %-13s| %-14s| %-13s|\n", invoiceID, date, (iE == 'I') ? "Nhap" : "Xuat", prepareBy, receivedBy, totalPrice);
    }

// --String to write to file--
    @Override
    public String toString() {
        String temp = iE + "," + invoiceID + "," + date + "," + prepareBy + "," + receivedBy + ",";
        for (InvoiceProduct e : productListInvoice) {
            temp = temp + e.toString() + ",";
        }
        temp += totalPrice + "\n";
        return temp;
    }

// --Getter-Setter--------------------------------------------------------------
    public char getIE() {
        return iE;
    }

    public void setIE(char iE) {
        this.iE = iE;
    }

    public String getInvoiceID() {
        return invoiceID;
    }

    public void setInvoiceID(String invoiceID) {
        this.invoiceID = invoiceID;
    }

    public String getPrepareBy() {
        return prepareBy;
    }

    public void setPrepareBy(String prepareBy) {
        this.prepareBy = prepareBy;
    }

    public String getReceivedBy() {
        return receivedBy;
    }

    public void setReceivedBy(String receivedBy) {
        this.receivedBy = receivedBy;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ArrayList<InvoiceProduct> getProductList() {
        return productListInvoice;
    }

    public void setProductList(ArrayList<InvoiceProduct> productListInvoice) {
        this.productListInvoice = productListInvoice;
        getTotalPrice();
    }

    public InvoiceProduct getProduct(int index) {
        return productListInvoice.get(index);
    }

    public void setProduct(int index, InvoiceProduct element) {
        this.productListInvoice.set(index, element);
    }

    public double getTotalPrice() {
        double t = 0;
        for (InvoiceProduct e : productListInvoice) {
            t += e.getAmount() * e.getPrice();
        }
        totalPrice = t;
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
