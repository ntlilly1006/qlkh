package qlkh;

import java.util.Scanner;

public class Suppliers {
    Scanner sc = new Scanner(System.in);
    private String supplierID;
    private String supplierName;
    private Address supplierAddress;
    private String supplierPhoneNumber;

    public Suppliers(){
        supplierID=null;
        supplierName=null;
        supplierAddress=null;
        supplierPhoneNumber=null;
    }
    public Suppliers(String supplierID, String supplierName, Address supplierAddress, String supplierPhoneNumber){
        this.supplierID=supplierID;
        this.supplierName=supplierName;
        this.supplierAddress=supplierAddress;
        this.supplierPhoneNumber=supplierPhoneNumber;
    }
    public String getSupplierID(){
        return supplierID;
    }
    public void setSupplierID(){
        supplierID=sc.nextLine();
    }
    public String getSupplierName(){
        return supplierName;
    }
    public void setSupplierName(){
        supplierName=sc.nextLine();
    }
    public Address getSupplierAddress(){
        return supplierAddress;
    }
    public void setSupplierAddress(){
        supplierAddress.input();
    }
    public String getSupplierPhoneNumber(){
        return supplierPhoneNumber;
    }
    public void setSupplierPhoneNumber(){
        supplierPhoneNumber=sc.nextLine();
    }
    public void input(){
        setSupplierID();
        setSupplierName();
        setSupplierAddress();
        setSupplierPhoneNumber();
    }
    public String toString(){
        return supplierID +" " +supplierName +" " +supplierAddress +" " +supplierPhoneNumber;
    }
}