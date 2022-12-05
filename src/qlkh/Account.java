package qlkh;

import java.util.Scanner;

public class Account {

    Scanner sc = new Scanner(System.in);
    private String accountName;
    private String password;
    private String staffID;
    private String roleID;

    public Account() {
        accountName = null;
        password = null;
        staffID = null;
        roleID = null;
    }

    public Account(String accountName, String password, String staffID) {
        this.accountName = accountName;
        this.password = password;
        this.staffID = staffID;
    }

    public Account(Account account) {
        this.accountName = account.accountName;
        this.password = account.password;
        this.staffID = account.staffID;
    }

    public Account(String accountName, String password) {
        this.accountName = accountName;
        this.password = password;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName() {
        accountName = sc.nextLine();
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword() {
        password = sc.nextLine();
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStaffID() {
        return staffID;
    }

    public void setStaffID() {
        staffID = sc.nextLine();
    }

    public void setStaffID(String staffID) {
        this.staffID = staffID;
    }

    public String getRoleID() {
        return roleID;
    }

    public void setRoleID(String roleID) {
        this.roleID = roleID;
    }

    public void input() {
        System.out.print("Nhap tai khoan:");
        setAccountName();
        System.out.print("Nhap mat khau:");
        setPassword();
        System.out.print("Nhap ma nhan vien cua tai khoan:");
        setStaffID();
        while (!Tools.isStaffID(staffID)) {
            System.out.print("Nhap ma nhan vien theo dung dinh dang \"staff\" + 01 -> 99: ");
            setStaffID();
        }
    }

    public String toString() {
        return accountName + " " + password + " " + staffID + " " + roleID;
    }

    public void addRoleID() {
        System.out.println("Give role to account");
    }
}
