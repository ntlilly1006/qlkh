package qlkh;

/**
 * DONE
 *
 * @author Lilly
 */
public class ProductInvoice {

    private String productID;
    private String unit;
    private long amount;
    private double price;

// --Constructor----------------------------------------------------------------    
    public ProductInvoice() {
    }

    public ProductInvoice(String productID, String unit, long amount, double price) {
        this.productID = productID;
        this.unit = unit;
        this.amount = amount;
        this.price = price;
    }

    public ProductInvoice(ProductInvoice other) {
        this.productID = other.productID;
        this.unit = other.unit;
        this.amount = other.amount;
        this.price = other.price;
    }
// -----------------------------------------------------------------------------
// --Console: Enter a product--
    public void enter() {
        System.out.print("Nhap ID san pham: ");
        productID = Tools.scan.nextLine();
        while (!Tools.isProductID(productID)) {
            System.out.print("Nhap ID san pham: ");
            productID = Tools.scan.nextLine();
        }

        System.out.print("Nhap don vi tinh: ");
        unit = Tools.scan.nextLine();
        while (!Tools.isUnit(unit)) {
            System.out.print("Nhap don vi tinh: ");
            unit = Tools.scan.nextLine();
        }
        
        System.out.print("Nhap so luong: ");
        String aStr = Tools.scan.nextLine();
        while (!Tools.isLong(aStr)) {
            System.out.print("Nhap so luong: ");
            aStr = Tools.scan.nextLine();
        }
        amount = Long.parseLong(aStr);
        
        System.out.print("Nhap don gia: ");
        String pStr = Tools.scan.nextLine();
        while (!Tools.isDouble(pStr)) {
            System.out.print("Nhap so luong: ");
            pStr = Tools.scan.nextLine();
        }
        price = Double.parseDouble(pStr);
    }

// --Console: Display a line--
    public void display() {
        System.out.printf("|%-13s|%-13s|%-12s|%-11s|\n", productID, unit, amount, price);
    }

// --String to write to file--
    @Override
    public String toString() {
        return productID + "," + unit + "," + amount + "," + price;
    }

// --Getter-Setter--------------------------------------------------------------
    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
