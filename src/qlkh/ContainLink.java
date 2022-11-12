package qlkh;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * DONE
 *
 * @author Lilly
 */
class ContainLink {

    private String positionID; // vị trí
    private String productID; // chứa cái gì
    private long amount; // số lượng bao nhiêu
    private String inputDate; // ngày bắt đầu chứa
    private long days; // số ngày tồn kho    
    private long actualAmount; // số lượng thực
    private long disparity; // chênh lệch
    private boolean status = true; //true còn chứa | false xuất rồi

// --Constructor----------------------------------------------------------------    
    public ContainLink() {
    }

    public ContainLink(String positionID, String productID, long amount, String inputDate) {
        this.positionID = positionID;
        this.productID = productID;
        this.amount = amount;
        this.inputDate = inputDate;
        setDays();
        actualAmount = amount;
        setDisparity();
    }

    public ContainLink(String positionID, String productID, long amount, String inputDate, long actualAmount, boolean status) {
        this.positionID = positionID;
        this.productID = productID;
        this.amount = amount;
        this.inputDate = inputDate;
        setDays();
        this.actualAmount = actualAmount;
        setDisparity();
        this.status = status;
    }

    public ContainLink(ContainLink other) {
        this.positionID = other.positionID;
        this.productID = other.productID;
        this.amount = other.amount;
        this.inputDate = other.inputDate;
        this.days = other.days;
        this.actualAmount = other.actualAmount;
        this.disparity = other.disparity;
        this.status = other.status;
    }

// -----------------------------------------------------------------------------
// --Update after inventory--
    public void update() {
        amount = actualAmount;
        disparity = 0;
    }

// --Console: Display table--
    public void display() { // "| Vi tri | ID san pham |  So luong  |So ngay ton kho|");
        System.out.printf("|  %-6s| %-12s|  %-10s|     %-10s|\n", positionID, productID, amount, days);
    }
    
// --Console: Enter inventory--
    public void enterInventory() { 
        System.out.println("Vi tri: " + positionID);
        System.out.println("San pham: " + productID);
        System.out.println("So luong: " + amount);
        System.out.print("Nhap so luong thuc te: ");
        String aStr = Tools.scan.nextLine();
        while (!Tools.isLong(aStr)) {
            System.out.print("Nhap so luong thuc te: ");
            aStr = Tools.scan.nextLine();
        }
        actualAmount = Long.parseLong(aStr);
        setDisparity();
    }

// --Console: Display inventory table--
    public void displayInventory() { // "| Vi tri | ID san pham |  So luong  |  Thuc te   | Chenh lech |");
        System.out.printf("|  %-6s| %-12s|  %-10s|  %-10s|    %-8s|\n", positionID, productID, amount, actualAmount, disparity);
    }

// --String to write to file--
    @Override
    public String toString() {
        return positionID + "," + productID + "," + amount + "," + inputDate + "," + status + "\n";
    }

// --Getter-Setter--------------------------------------------------------------
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

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
        setDisparity();
    }

    public String getInputDate() {
        return inputDate;
    }

    public void setInputDate(String inputDate) {
        this.inputDate = inputDate;
        setDays();
    }

    public long getDays() {
        return days;
    }

    private void setDays() {
        if (!status || !Tools.isDate(inputDate)) {
            return;
        }

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Calendar now = Calendar.getInstance();
            Calendar then = Calendar.getInstance();
            then.setTime(new SimpleDateFormat("dd/MM/yyyy").parse(inputDate));
            days = (now.getTime().getTime() - then.getTime().getTime()) / (24 * 3600 * 1000);
        } catch (ParseException ex) {
        }
    }

    public long getActualAmount() {
        return actualAmount;
    }

    public void setActualAmount(long actualAmount) {
        this.actualAmount = actualAmount;
        setDisparity();
    }

    public long getDisparity() {
        return disparity;
    }

    private void setDisparity() {
        disparity = actualAmount - amount;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
