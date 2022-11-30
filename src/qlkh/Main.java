package qlkh;
import java.io.*;
/**
 * PositionID: A01 - Z99 ProductID: P01 - P99 InvoiceID: I01 - I99 / E01 - E99
 * StaffID: staff01 - staff99 SupplierID: S01 - S99 DistributorID: D01 - D99
 * 
 * @author Lilly
 */
public class Main {
    static File accountFile = new File("Account.txt");
    static File staffFile = new File("Staff.txt");
    static File staffAddressFile = new File("Staff-address.txt");
    
    public static void main(String[] args) {
        Tools.cls();
        Manager manager = new Manager();
        AccountList accountList = new AccountList();
        StaffList staffList = new StaffList();
        accountList.createList(accountFile);
        staffList.createList(staffFile, staffAddressFile);

        while(true){
            Tools.cls();
            System.out.println("---HE THONG QUAN LY KHO HANG THIET BI DIEN TU---");

            Boolean loginCheck = login(accountList);
            System.out.println(loginCheck);
            if (Boolean.TRUE.equals(loginCheck)){
                adminMenu(manager, accountList, staffList);
            }
            else{
                staffMenu(manager, accountList, staffList);
            }
        }
    }

    public static boolean login(AccountList accountList){

        System.out.println("--Dang nhap");       
        System.out.print("Tai khoan: ");
        String accountName = Tools.scan.nextLine();
        System.out.print("Mat khau: ");
        String password = Tools.scan.nextLine();

        Account loginAccount = new Account(accountName, password);
        boolean signInCheck=accountList.checkSignIn(loginAccount);

        while(!signInCheck){
                System.out.println("Vui long kiem tra lai tai khoan hoac mat khau.");
                System.out.print("Tai khoan: ");
                accountName = Tools.scan.nextLine();
                System.out.print("Mat khau: ");
                password = Tools.scan.nextLine();
                loginAccount = new Account(accountName, password);
                signInCheck=accountList.checkSignIn(loginAccount);
        }

        loginAccount=accountList.giveLoginRoleID(loginAccount);
        if (loginAccount instanceof AdminAccount){
            return true;
        }
        else{
            return false;
        }
    }

    public static void adminMenu(Manager manager, AccountList accountList, StaffList staffList) {
        Tools.cls();
        System.out.println("---QUAN TRI HE THONG KHO HANG---\n");
        System.out.println("1. Quan ly hang hoa");
        System.out.println("2. Quan ly nhan vien va tai khoan");
        System.out.println("3. Doi mat khau\n");
        System.out.println("4. Thoat va luu\n");
        System.out.println("** WARNINGS: Phai chon \"Thoat va luu\" de luu cac thay doi ve nhan vien va tai khoan");
        String option = Tools.scan.nextLine();
        while (!Tools.isInteger(option) || Integer.parseInt(option) < 1 || Integer.parseInt(option) > 4) {System.out.println("Vui long nhap lai: ");option = Tools.scan.nextLine();}
        switch (Integer.parseInt(option)) {
            case 1: {
                warehouseMenu(manager);
                Tools.continute();
                break;
            }
            case 2: {
                staffManagementMenu(accountList, staffList);
                Tools.continute();
                break;
            }
            case 3: {
                System.out.print("Nhap lai ten tai khoan: ");
                String accountName = Tools.scan.nextLine();
                System.out.print("Nhap mat khau cu: ");
                String oldPassword = Tools.scan.nextLine();
                System.out.print("Nhap mat khau moi: ");
                String newPassword = Tools.scan.nextLine();
                accountList.changePassword(accountName, oldPassword, newPassword);
                Tools.continute();
                break;
            }
            case 4: {
                accountList.updataFile(accountFile);
                staffList.updataFile(staffFile, staffAddressFile);
                return;
            }
        }
        adminMenu(manager, accountList, staffList);
    }

    public static void staffMenu(Manager manager, AccountList accountList, StaffList staffList) {
        Tools.cls();
        System.out.println("---QUAN TRI HE THONG KHO HANG DANH CHO NHAN VIEN---\n");
        System.out.println("1. Quan ly hang hoa");
        System.out.println("2. Doi mat khau\n");
        System.out.println("3. Thoat va luu\n");
        System.out.println("** WARNINGS: Phai chon \"Thoat va luu\" de luu cac thay doi ve nhan vien va tai khoan");
        String option = Tools.scan.nextLine();
        while (!Tools.isInteger(option) || Integer.parseInt(option) < 1 || Integer.parseInt(option) > 3) {System.out.println("Vui long nhap lai: ");option = Tools.scan.nextLine();}
        switch (Integer.parseInt(option)) {
            case 1: {
                warehouseMenu(manager);
                Tools.continute();
                break;
            }
        
            case 2: {
                System.out.print("Nhap lai ten tai khoan: ");
                String accountName = Tools.scan.nextLine();
                System.out.print("Nhap mat khau cu: ");
                String oldPassword = Tools.scan.nextLine();
                System.out.print("Nhap mat khau moi: ");
                String newPassword = Tools.scan.nextLine();
                accountList.changePassword(accountName, oldPassword, newPassword);
                Tools.continute();
                break;
            }
            case 3: {
                staffList.updataFile(staffFile, staffAddressFile);
                return;
            }
        }
        staffMenu(manager, accountList, staffList);
    }

    public static void staffManagementMenu(AccountList accountList, StaffList staffList){
        Tools.cls();
        System.out.println("---QUAN LY NHAN VIEN VA TAI KHOAN---");
        System.out.println("1. Them nhan vien");
        System.out.println("2. Xoa nhan vien");
        System.out.println("3. Chinh sua nhan vien");
        System.out.println("4. Tim nhan vien");
        System.out.println("5. Hien thi danh sach nhan vien");
        System.out.println("6. Tim tai khoan");
        System.out.println("7. Them tai khoan nhan vien");
        System.out.println("8. Xoa tai khoan");
        System.out.println("9. Hien thi danh sach tai khoan");
        System.out.println("10. Thoat");

        String option = Tools.scan.nextLine();
        while (!Tools.isInteger(option) || Integer.parseInt(option) < 1 || Integer.parseInt(option) > 10) {
            System.out.println("Vui long nhap lai: "); option = Tools.scan.nextLine();
        }
        switch (Integer.parseInt(option)) {
            case 1: {
                System.out.println("--Nhap nhan vien moi");
                Staff newStaff= new Staff();
                newStaff.input();
                if (!staffList.checkStaff(newStaff.getStaffID())){
                    System.out.println("Ma nhan vien bi trung, vui long tao lai nhan vien");
                }
                else{
                    staffList.addStaff(newStaff);
                }
                Tools.continute();
                break;
            }
            case 2: {
                System.out.print("Nhap ten nhan vien can xoa: ");
                String staffName = Tools.scan.nextLine();
                staffList.removeStaff(staffName);
                Tools.continute();
                break;
            }
            case 3: {
                System.out.print("Nhap ma nhan vien can sua thong tin: ");
                String staffID = Tools.scan.nextLine();
                System.out.print("--Chinh sua nhan vien\n");
                if(!staffList.adjustStaff(staffID)){
                    System.out.println("Khong tim thay nhan vien.");
                }
                Tools.continute();
                break;
            }
            case 4: {
                System.out.print("Nhap ma nhan vien can tim: ");
                String staffID = Tools.scan.nextLine();
                if(!staffList.findStaff(staffID)){
                    System.out.println("Khong tim thay nhan vien.");
                }
                Tools.continute();
                break;
            }
            case 5: {
                staffList.displayList();
                Tools.continute();
                break;
            }
            case 6: {
                System.out.print("Nhap ten tai khoan can tim: ");
                String accountID = Tools.scan.nextLine();
                if(!accountList.findAccount(accountID)){
                    System.out.println("Khong tim thay tai khoan.");
                }
                Tools.continute();
                break;
            }
            case 7: {
                System.out.println("--Them tai khoan nhan vien moi");
                StaffAccount newAccount = new StaffAccount();
                newAccount.input();
                newAccount.addRoleID();
                accountList.addAccount(newAccount);
                Tools.continute();
                break;
            }
            case 8: {
                System.out.print("Nhap ten tai khoan can xoa: ");
                String accountName = Tools.scan.nextLine();
                accountList.removeAccount(accountName);
                Tools.continute();
                break;
            }
            case 9: {
                accountList.displayList();
                Tools.continute();
                break;
            }
            case 10:{
                return;
            }
        }
        staffManagementMenu(accountList, staffList);
    }
    public static void warehouseMenu(Manager manager) {
        Tools.cls();
        System.out.println("---QUAN LY KHO HANG THIET BI DIEN TU---");
        System.out.println("1. Nhap hang");
        System.out.println("2. Xuat hang");
        System.out.println("3. Kiem ke hang");
        System.out.println("4. Tra cuu hang hoa");
        System.out.println("5. Truy xuat hoa don");
        System.out.println("6. Thiet lap kho");
        System.out.println("7. Thoat");
        System.out.print("Vui long nhap 1 so (1->7): ");
        String option = Tools.scan.nextLine();
        while (!Tools.isInteger(option) || Integer.parseInt(option) < 1 || Integer.parseInt(option) > 7) {
            System.out.print("Vui long nhap 1 so (1->7): ");
            option = Tools.scan.nextLine();
        }
        switch (Integer.parseInt(option)) {
            case 1: {
                manager.importPD();
                Tools.continute();
                break;
            }
            case 2: {
                manager.exportPD();
                Tools.continute();
                break;
            }
            case 3: {
                manager.inventory();
                break;
            }
            case 4: {
                manager.productList.menu();
                break;
            }
            case 5: {
                manager.invoiceList.menu();
                break;
            }
            case 6: {
                manager.positionList.menu();
                break;
            }
            case 7: {
                //System.exit(0);
                return;
            }
        }
        warehouseMenu(manager);
    }
}
