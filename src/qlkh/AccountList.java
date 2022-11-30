package qlkh;

import java.io.*;
import java.util.Arrays;

public class AccountList {
    private static Account[] list;

    public AccountList(){
        this.list=null;
    }
    public AccountList(Account[] list){
        this.list=list;
    }

    // create list and read data from file
    public void createList(File file){  
        list = new Account[0];
        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader(file));
            String line = br.readLine();

            while(line != null){
                String[] row = line.split(",");
                Account account = new Account(row[0], row[1], row[2]);
                account = giveRoleID(account, row[3]);
                list = addAccountFromFile(account, list);
                line = br.readLine();
            }
        } catch (IOException e) {
            System.out.println("Cannot read the file, using alternative solution instead.");

            Account account = new AdminAccount("admin", "123", "a001");
            account.addRoleID();
            list=addAccountFromFile(account, list);

            account = new StaffAccount("staff01", "123", "s001");
            account.addRoleID();
            list=addAccountFromFile(account,list);

            account = new StaffAccount("staff02", "123", "s002");
            account.addRoleID();
            list=addAccountFromFile(account,list);

            account = new StaffAccount("staff03", "123", "s003");
            account.addRoleID();
            list=addAccountFromFile(account,list);

            account = new StaffAccount("staff04", "123", "s004");
            account.addRoleID();
            list=addAccountFromFile(account,list);

            account = new StaffAccount("staff05", "123", "s005");
            account.addRoleID();
            list=addAccountFromFile(account,list);
            
            account = new StaffAccount("staff06", "123", "s006");
            account.addRoleID();
            list=addAccountFromFile(account,list);

            account = new StaffAccount("staff07", "123", "s007");
            account.addRoleID();
            list=addAccountFromFile(account,list);

            account = new StaffAccount("staff08", "123", "s008");
            account.addRoleID();
            list=addAccountFromFile(account,list);

            account = new StaffAccount("staff09", "123", "s009");
            account.addRoleID();
            list=addAccountFromFile(account,list);

            account = new StaffAccount("staff10", "123", "s0010");
            account.addRoleID();
            list=addAccountFromFile(account,list);

            updataFile(file);
        }
        finally{
            try {
                if (br!=null)
                    br.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // override new data to file
    public static void updataFile(File file){
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(file,false));
            for(Account account : list){
                bw.write(account.getAccountName() +",");
                bw.write(account.getPassword() +",");
                bw.write(account.getStaffID() +",");
                bw.write(account.getRoleID() +"\n");
            }
        } catch (IOException e) {
            System.out.println("Cannot update data!!");
        } finally{
            try {
                if (bw!=null)
                    bw.close();
            } catch (Exception e) {
                System.out.println("Cannot close the stream!!");
            }
        }
    }

    // this function is used for login class
    // it returns false when user give incorrect account name or password
    public boolean checkSignIn(Account account){
        for (Account a : list){
            if (account.getAccountName().equals(a.getAccountName())){
                if (account.getPassword().equals(a.getPassword())){
                    System.out.println("Danh nhap thanh cong!!");
                    return true;
                }
            }
        }
        return false;
    }

    public static Account[] addAccountFromFile(Account account, Account[] list){
        int n = list.length;
        list = Arrays.copyOf(list, n+1);
        list[n]=account;
        return list;
    }

    // add new account to list 
    public void addAccount(Account account){
        boolean check = checkAccount(account);
        if (check){
            list = addAccountFromFile(account, list);
            System.out.println("Tai khoan da duoc them thanh cong!!");
        }
        else
            System.out.println("Them tai khoan khong thanh cong, vui long kiem tra lai");
    }

    public boolean checkAccount(Account account){
        for (Account a: list){
            if (account.getAccountName().equals(a.getAccountName())){
                System.out.println("** Ten tai khoan da ton tai.");
                return false;
            }
            if (account.getStaffID().equals(a.getStaffID())){
                System.out.println("** Ma nhan vien da ton tai.");
                return false;
            }
        }
        return true;
    }

    // remove account from list
    //                               accountName: name of the account that need to be deleted
    public void removeAccount(String accountName){

        if (list==null)
            System.out.println("Khong ton tai danh sach");
        else{
            Account[] proxyList = new Account[list.length-1];
            for (int i = 0, k = 0; i < list.length; i++){
                if (list[i].getAccountName().equals(accountName))
                    // ignore the account that need to be deleted
                    continue;
                else
                    try {
                        proxyList[k++]=list[i];
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("Khong the xoa tai khoan");
                    }
            }
            list = proxyList;
        }
    }

    // change account's password
    public void changePassword(String accountName,String oldPassword, String newPassword){
        for (int i=0;i<list.length;i++){
            if (list[i].getAccountName().equals(accountName)){
                if (list[i].getPassword().equals(oldPassword)){
                    list[i].setPassword(newPassword);
                    System.out.println("Mat khau da duoc thay doi!!");
                    break;
                }
                else   
                    System.out.println("Sai mat khau");
            }
            else
                System.out.println("Sai tai khoan");
        }
    }

    // find account in list, ADMIN ACCESS ONLY
    public boolean findAccount(String accountName){
        for (Account account : list){
            if(account.getAccountName().equals(accountName)){
                System.out.println("Tai khoan tim thay!!");
                System.out.println(account);
                return true;
            }
            else{
                continue;
            }
        }
        return false;
    }

    // check the account type and recreate the account
    public Account giveRoleID(Account account, String roleID){
        if (roleID.equals("admin")){
            account = new AdminAccount(account.getAccountName(),account.getPassword(),account.getStaffID());
            account.addRoleID();
            return account;
        }
        else{
            account = new StaffAccount(account.getAccountName(),account.getPassword(),account.getStaffID());
            account.addRoleID();
            return account;
        }
    }

    // give the account in login class roleId to verify
    public Account giveLoginRoleID(Account account){
        for (Account a: list){
            if (account.getAccountName().equals(a.getAccountName())){
                account = new Account(a);
                account = giveRoleID(account, a.getRoleID());
                return account;
            }
        }
        return account;
    }



    // get the amount of account 
    public void getAmount(){
        System.out.println("Account amount: "+list.length);
    }

    public void displayList(){
        System.out.printf("%-15s%-15s%-15s%-20s", "Ten tai khoan", "Mat khau", "Ma nhan vien", "Quyen tai khoan");
        System.out.println();
        for(Account account : list){
            System.out.println(account);
        }
    }
}
