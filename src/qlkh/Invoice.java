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
    private ArrayList<ProductInvoice> productListInvoice;
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

    public Invoice(char iE, String invoiceID, String date, ArrayList<ProductInvoice> productListInvoice,
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

        ProductInvoice e = new ProductInvoice(productID, unit, amount, price);
        if (!productListInvoice.add(e)) {
            return false;
        }
        getTotalPrice();
        return true;
    }

// --Console: Add more product to invoice--   
    public boolean addMoreProduct() {
        ProductInvoice e = new ProductInvoice();
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
            System.out.print("Nhap ID hoa don: ");
            invoiceID = Tools.scan.nextLine();
        } while (!Tools.isInvoiceID(invoiceID));

        do {
            System.out.print("Nhap ngay thang nam (dd/mm/yyyy): ");
            date = Tools.scan.nextLine();
        } while (!Tools.isDate(date));

        do {
            System.out.print("Nguoi lap hoa don (ID nhan vien): ");
            prepareBy = Tools.scan.nextLine();
        } while (!Tools.isStaffID(prepareBy));

        if (iE == 'I') {
            do {
                System.out.print("Nguoi nhan hoa don (ID nha cung cap): ");
                receivedBy = Tools.scan.nextLine();
            } while (!Tools.isSupplierID(receivedBy));
        }
        if (iE == 'E') {
            do {
                System.out.print("Nguoi nhan hoa don (ID nha phan phoi): ");
                receivedBy = Tools.scan.nextLine();
            } while (!Tools.isDistributorID(receivedBy));
        }

        System.out.println("Nhap tong so san pham trong danh sach:");
        String nStr = Tools.scan.nextLine();
        while (!Tools.isInteger(nStr)) {
            System.out.println("Nhap tong so san pham trong danh sach:");
            nStr = Tools.scan.nextLine();
        }
        int n = Integer.parseInt(nStr);
        System.out.println("Nhap thong tin san pham:");
        for (int i = 0; i < n;) {
            System.out.println(i + ")");
            if (addMoreProduct()) {
                i++;
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
        for (ProductInvoice e : productListInvoice) {
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
        for (ProductInvoice e : productListInvoice) {
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
        System.out.printf("|   %-12s| %-12s|   %-6s| %-13| %-14s|\n", invoiceID, date, (iE == 'I') ? "Nhap" : "Xuat", prepareBy, receivedBy);
    }

// --String to write to file--
    @Override
    public String toString() {
        String temp = iE + "," + invoiceID + "," + date + "," + prepareBy + "," + receivedBy + ",";
        for (ProductInvoice e : productListInvoice) {
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

    public ArrayList<ProductInvoice> getProductList() {
        return productListInvoice;
    }

    public void setProductList(ArrayList<ProductInvoice> productListInvoice) {
        this.productListInvoice = productListInvoice;
    }

    public ProductInvoice getProduct(int index) {
        return productListInvoice.get(index);
    }

    public void setProduct(int index, ProductInvoice element) {
        this.productListInvoice.set(index, element);
    }

    public double getTotalPrice() {
        double t = 0;
        for (ProductInvoice e : productListInvoice) {
            t += e.getAmount() * e.getPrice();
        }
        totalPrice = t;
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
