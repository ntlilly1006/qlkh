package qlkh;
import java.util.Scanner;

public class Staff {
    Scanner sc = new Scanner(System.in);
    private String staffID;
    private String staffName;
    private Address address;
    private String phoneNumber;
    private String position;

    public Staff(){
        staffID=null;
        staffName=null;
        address = new Address();
        phoneNumber=null;
        position=null;
    }
    public Staff(String staffID, String staffName, Address address, String phoneNumber, String position){
        this.staffID = staffID;
        this.staffName = staffName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.position = position;

    }
    public String getStaffID(){
        return staffID;
    }
    public void setStaffID(){
        staffID = sc.nextLine();
    }
    public String getStaffName(){
        return staffName;
    }
    public void setStaffName(){
        staffName = sc.nextLine();
    }
    public Address getAddress(){
        return address;
    }
    public void setAddress(){
        address.input();
    }
    public String getPhoneNumber(){
        return phoneNumber;
    }
    public void setPhoneNumber(){
        phoneNumber = sc.nextLine();
    }
    public String getPosition(){
        return position;
    }
    public void setPosition(){
        position = sc.nextLine();
    }
    public void input(){
        System.out.print("Nhap ma nhan vien: ");
        setStaffID();
        while(!Tools.isStaffID(staffID)){
            System.out.print("Nhap ma nhan vien theo dung dinh dang \"staff\" + 01 -> 99: ");
            setStaffID();
        }
    

        System.out.print("Nhap ten nhan vien: ");
        setStaffName();
        setAddress();
        System.out.print("Nhap so dien thoai:");
        setPhoneNumber();
        while(!Tools.isPhoneNumber(phoneNumber)){
            System.out.print("So dien thoai phai co 10 chu so bat dau bang so 0:  ");
            setPhoneNumber();
        }
        System.out.print("Nhap chuc vu (Quan ly/ Nhan vien): ");
        setPosition();
        while(!Tools.isPosition(position)){
            System.out.print("Chuc vu phai la \"Quan ly\" hoac \"Nhan vien\":  ");
            setPosition();
        }
    }
    public String toString(){
        return staffID +" " +staffName +" " +address +" " +phoneNumber +" " +position;
    }
}
