package qlkh;

/**
 * Managed by productID
 * @author Lilly
 */
public class Product {
    private String productID;       //mã sản phẩm
    private String unit;            //đơn vị tính
    private double amount;          //số lượng tổng
    private double price;           //giá thành
     
    
    public Product() {}
    public Product(String productID, String unit, double amount, double price) {
        
    }
    public Product(Product other) {
        
    }

//------------------------------------------------------------------------------    
    public int enter() {
    //input
        return 0;
    }
    
    public void display() {
    //output
    }
//--Get-Set---------------------------------------------------------------------
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
    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
}
