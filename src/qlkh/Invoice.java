package qlkh;

/**
 * DONE
 *
 * @author Lilly
 */
import java.util.ArrayList;
import java.util.Scanner;

public class Invoice {

    private char iE;     //I: Nhap | E: Xuat
    private String invoiceID;
    private String date;
    private String prepareBy;
    private String receivedBy;
    private ArrayList<Product> products;
    private double totalPrice;

    public Invoice() {
        ArrayList<Product> products = new ArrayList<Product>();
    }

    public Invoice(char iE, String invoiceID, String date, String prepareBy, String receivedBy) {
        this.iE = iE;
        this.invoiceID = invoiceID;
        this.date = date;
        this.prepareBy = prepareBy;
        this.receivedBy = receivedBy;
        this.products = new ArrayList<Product>();
        this.totalPrice = total();
    }
    
    public Invoice(char iE, String invoiceID, String date, ArrayList<Product> products,
            double totalPrice, String prepareBy, String receivedBy) {
        this.iE = iE;
        this.invoiceID = invoiceID;
        this.date = date;
        this.products = new ArrayList<Product>(products);
        this.prepareBy = prepareBy;
        this.receivedBy = receivedBy;
        this.totalPrice = total();
    }

    public Invoice(Invoice other) {
        this.iE = other.iE;
        this.invoiceID = other.invoiceID;
        this.date = other.date;
        this.products = new ArrayList<Product>(other.products);
        this.prepareBy = other.prepareBy;
        this.receivedBy = other.receivedBy;
        this.totalPrice = other.total();
    }
//------------------------------------------------------------------------------    
//--Hoa don nhap hang tra ve 1--Xuat hang tra ve 0--That bai tra ve -1--    

    public int enter() {
        //        cls;
        Scanner scan = new Scanner(System.in);
        System.out.println("---XUAT HOA DON---");
        System.out.print("Nhap ID hoa don: ");
        invoiceID = scan.nextLine();
        System.out.print("Nhap ngay thang nam (dd/mm/yyyy): ");
        date = scan.nextLine();
        System.out.print("Nguoi lap hoa don: ");
        prepareBy = scan.nextLine();
        System.out.print("Nguoi nhan hoa don: ");
        receivedBy = scan.nextLine();
        System.out.println("Nhap thong tin san pham:");
        Product e = new Product();
        while (0 == e.enter()) {
            products.add(e);
        }
        total();
        System.out.println("Tong gia tri hoa don: " + totalPrice);
        System.out.print("Nhap vao kho (I) hay xuat khoi kho (E): ");
        iE = scan.next().charAt(0);
        return (iE == 'I') ? 1 : 0;
    }

//--Hien thi hoa don--   
    private void displayImportInvoice() {
        //        cls;
        System.out.println("---HOA DON NHAP HANG---");
        System.out.println("ID hoa don: " + invoiceID);
        System.out.println("Thoi gian: " + date);
        System.out.println("Nhap tu: " + receivedBy);
        System.out.println("| ID san pham | Don vi tinh |  So luong  |  Don gia  |");
        for (Product e : products) {
            e.display();
        }
        System.out.println("Tong gia tri hoa don: " + totalPrice);
        System.out.println("\n--Hoa don duoc lap boi " + prepareBy + "--");
    }

    private void displayExportInvoice() {
        //        cls;
        System.out.println("---HOA DON XUAT HANG---");
        System.out.println("ID hoa don: " + invoiceID);
        System.out.println("Thoi gian: " + date);
        System.out.println("Xuat cho: " + receivedBy);
        System.out.println("| ID san pham | Don vi tinh |  So luong  |  Don gia  |");
        for (Product e : products) {
            e.display();
        }
        System.out.println("Tong gia tri hoa don: " + totalPrice);
        System.out.println("\n--Hoa don duoc lap boi " + prepareBy + "--");
    }

    public void display() {
        if (iE == 'I') {
            displayImportInvoice();
        } else if (iE == 'E') {
            displayExportInvoice();
        }
    }

    public void displayTable() {
        System.out.println("|   " + invoiceID + "\t| " + date + "\t| " + ((iE == 'I') ? "Nhap" : "Xuat") + "\t|  " + prepareBy + "\t|  " + receivedBy + "\t|");
    }
    
    public int addMoreProduct(String productID, String unit, double amount, double price) {
        Product e = new Product(productID, unit, amount, price);
        products.add(e);
        return 0;
    }
//--Tools-----------------------------------------------------------------------    

    private double total() {
        double t = 0;
        for (Product e : products) {
            t += e.getAmount() * e.getPrice();
        }
        return t;
    }
//--Get-Set---------------------------------------------------------------------

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

    public ArrayList<Product> getProductsID() {
        return products;
    }

    public void setProductsID(ArrayList<Product> productsID) {
        this.products = productsID;
    }

    public Product getProduct(int index) {
        return products.get(index);
    }
    
    public void setProduct(int index, Product product) {
        this.products.set(index, product);
    }
    
    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
