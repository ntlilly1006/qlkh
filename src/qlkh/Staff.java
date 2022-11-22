package qlkh;

public class Staff {

    private String staffID;
    private String staffName;
    private Address address;
    private String phoneNumber;
    private String position;

    public Staff() {
        staffID = null;
        staffName = null;
        address = new Address();
        phoneNumber = null;
        position = null;
    }

    public Staff(String staffID, String staffName, Address address, String phoneNumber, String position) {
        this.staffID = staffID;
        this.staffName = staffName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.position = position;

    }

    public String getStaffID() {
        return staffID;
    }

    public void setStaffID() {
        staffID = Tools.scan.nextLine();
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName() {
        staffName = Tools.scan.nextLine();
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress() {
        address.input();
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber() {
        phoneNumber = Tools.scan.nextLine();
    }

    public String getPosition() {
        return position;
    }

    public void setPosition() {
        position = Tools.scan.nextLine();
    }

    public void input() {
        System.out.print("Nhap ma nhan vien: ");
        setStaffID();
        System.out.print("Nhap ten nhan vien: ");
        setStaffName();
        setAddress();
        System.out.print("Nhap so dien thoai:");
        setPhoneNumber();
        System.out.print("Nhap chuc vu: ");
        setPosition();
    }

    public String toString() {
        return staffID + " " + staffName + " " + address + " " + phoneNumber + " " + position;
    }
}
