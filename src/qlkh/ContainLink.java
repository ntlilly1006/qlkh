package qlkh;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * DONE
 * 
 * @author Lilly
 */
class ContainLink {
    private String positionID; // vị trí
    private String productID; // chứa cái gì
    private double amount; // số lượng bao nhiêu
    private String inputDate; // ngày bắt đầu chứa
    private long days; // số ngày tồn kho    
    private double actualAmount; // số lượng thực
    private double disparity; // chênh lệch
    private boolean status = true; //true còn chứa | false xuất rồi
    
    
    public ContainLink() {} 

    public ContainLink(String positionID, String productID, double amount, String inputDate) {
        this.positionID = positionID;
        this.productID = productID;
        this.amount = amount;
        this.inputDate = inputDate;
    }

    public ContainLink(ContainLink other) {
        this.positionID = other.positionID;
        this.productID = other.productID;
        this.amount = other.amount;
        this.inputDate = other.inputDate;
    }

// -----------------------------------------------------------------------------
    public void display() { // "| Vi tri | ID san pham |  So luong  | Ngay ton kho |");
        setDays();
        System.out.println("|   " + positionID + "   | " + productID + " |   " + amount + "   |    " + days + "   |");
    }
    
    public void update() {
        amount = actualAmount;
        disparity = 0;
    }
// --Private--------------------------------------------------------------------
    private void setDays() { 
        if (!status)
            return;
        SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
        Calendar now = Calendar.getInstance();
        Calendar then = Calendar.getInstance();
        try {  
                Date temp = new SimpleDateFormat("dd/MM/yyyy").parse(inputDate);  
                then.setTime(temp);
                days = (now.getTime().getTime() - then.getTime().getTime()) / (24 * 3600 * 1000);
            } catch (ParseException ex) {
                return;
            }
    }
// --Get-Set--------------------------------------------------------------------
    public String getPositionID() {
        return positionID;
    }

    public void setPositionID(String positionID) {
        this.positionID = positionID;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
        disparity = this.actualAmount - this.amount;
    }

    public String getInputDate() {
        return inputDate;
    }

    public void setInputDate(String inputDate) {
        this.inputDate = inputDate;
        setDays();
    }
    
    public long getDays() {
        setDays();
        return days;
    }
    
    public double getActualAmount() {
        return actualAmount;
    }

    public void setActualAmount(double actualAmount) {
        this.actualAmount = actualAmount;
        disparity = this.actualAmount - amount;
    }

    public double getDisparity() {
        return disparity;
    }
    
    public boolean getStatus() {
        return status;
    }
    
    public void setStatus(boolean status) {
        this.status = status;
    }
}