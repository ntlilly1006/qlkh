package qlkh;

import java.util.Scanner;
public class Distributors {
    Scanner sc = new Scanner(System.in);
    private String distributorID;
    private String distributorName;
    private Address distributorAddress;
    private String distributorPhoneNumber;

    public Distributors(){
        distributorID=null;
        distributorName=null;
        distributorAddress=null;
        distributorPhoneNumber=null;
    }
    public Distributors(String distributorID, String distributorName, Address distributorAddress, String distributorPhoneNumber){
        this.distributorID=distributorID;
        this.distributorName=distributorName;
        this.distributorAddress=distributorAddress;
        this.distributorPhoneNumber=distributorPhoneNumber;
    }
    public String getDistributorID(){
        return distributorID;
    }
    public void setDistributorID(){
        distributorID=sc.nextLine();
    }
    public String getDistributorName(){
        return distributorName;
    }
    public void setDistributorName(){
        distributorName=sc.nextLine();
    }
    public Address getDistributorAddress(){
        return distributorAddress;
    }
    public void setDistributorAddress(){
        distributorAddress.input();
    }
    public String getDistributorPhoneNumber(){
        return distributorPhoneNumber;
    }
    public void setDistributorPhoneNumber(){
        distributorPhoneNumber=sc.nextLine();
    }
    public void input(){
        setDistributorID();
        setDistributorName();
        setDistributorAddress();
        setDistributorPhoneNumber();
    }
    public String toString(){
        return distributorID +" " +distributorName +" " +distributorAddress +" " +distributorPhoneNumber;
    }
}
