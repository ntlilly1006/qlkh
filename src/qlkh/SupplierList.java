package qlkh;

import java.io.*;
import java.util.Arrays;

public class SupplierList {
    private Suppliers[] list;

    public SupplierList(){
        list= null;
    }
    public SupplierList(Suppliers[] list){
        this.list= list;
    }

    // create list and read data from file
    public void createList(File suppliersFile,File suppliersAddressFile){  
        list = new Suppliers[0];
        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader(suppliersAddressFile));
            String line = br.readLine();
            Address[] suppliersAddressArray= new Address[0];
            while(line != null){
                String[] row = line.split(",");
                Address address = new Address(Integer.parseInt(row[0]), row[1], row[2], row[3], row[4]);
                suppliersAddressArray=addAddressFromFile(address, suppliersAddressArray);
                line = br.readLine();
            }
            br.close();

            br = new BufferedReader(new FileReader(suppliersFile));
            line = br.readLine();
            int index=0;
            while(line != null){
                String[] row = line.split(",");
                Suppliers staff = new Suppliers(row[0], row[1], suppliersAddressArray[index], row[2]);
                list = addSupplierFromFile(staff, list);
                index++;
                line = br.readLine();
            }
        } catch (IOException e) {
            System.out.println("Cannot read the file, using alternative solution instead.");

            Address address = new Address(1,"Duong 1", "P1", "Q1", "TP Mo Mong");
            Suppliers supplier = new Suppliers("s001", "ncc1", address, "01123");
            list=addSupplierFromFile(supplier, list);

            address = new Address(2,"Duong 2", "P2", "Q2", "TP Mo Mong");
            supplier = new Suppliers("s002", "ncc2", address, "01234");
            list=addSupplierFromFile(supplier, list);

            address = new Address(3,"Duong 3", "P3", "Q3", "TP Mo Mong");
            supplier = new Suppliers("s003", "ncc3", address, "01345");
            list=addSupplierFromFile(supplier, list);

            address = new Address(4,"Duong 4", "P4", "Q4", "TP Mo Mong");
            supplier = new Suppliers("s004", "ncc4", address, "01456");
            list=addSupplierFromFile(supplier, list);

            address = new Address(5,"Duong 5", "P5", "Q5", "TP Mo Mong");
            supplier = new Suppliers("s005", "ncc5", address, "01567");
            list=addSupplierFromFile(supplier, list);

            address = new Address(6,"Duong 6", "P6", "Q6", "TP Mo Mong");
            supplier = new Suppliers("s006", "ncc6", address, "01678");
            list=addSupplierFromFile(supplier, list);

            address = new Address(7,"Duong 7", "P7", "Q7", "TP Mo Mong");
            supplier = new Suppliers("s007", "ncc7", address, "01789");
            list=addSupplierFromFile(supplier, list);;

            address = new Address(8,"Duong 8", "P8", "Q8", "TP Mo Mong");
            supplier = new Suppliers("s008", "ncc8", address, "01890");
            list=addSupplierFromFile(supplier, list);

            address = new Address(9,"Duong 9", "P9", "Q9", "TP Mo Mong");
            supplier = new Suppliers("s009", "ncc9", address, "01901");
            list=addSupplierFromFile(supplier, list);

            address = new Address(10,"Duong 10", "P10", "Q10", "TP Mo Mong");
            supplier = new Suppliers("s0010", "ncc10", address, "01902");
            list=addSupplierFromFile(supplier, list);
        }
        finally{
            try {
                br.close();
            } catch (Exception e) {
                System.out.println("Cannot close the stream");
            }
        }
    }

    // read address date from file
    public static Address[] addAddressFromFile(Address address,Address[] arr){
        int n = arr.length;
        arr = Arrays.copyOf(arr, n+1);
        arr[n] = address;
        return arr;
    }

    //read supplier data from file
    public static Suppliers[] addSupplierFromFile(Suppliers supplier,Suppliers[] arr){
        int n = arr.length;
        arr = Arrays.copyOf(arr, n+1);
        arr[n] = supplier;
        return arr;
    }

    // add supplier to list
    public void addSupplier(Suppliers supplier){
        list = addSupplierFromFile(supplier, list);
    }

    // remove supplier from list
    //                               supplierName: name of the supplier that need to be deleted
    public void removeStaff(String supplierName){

        if (list==null)
            System.out.println("No removal operation can be performed!!");
        else{
            Suppliers[] proxyList = new Suppliers[list.length-1];
            for (int i = 0, k = 0; i < list.length; i++){
                if (list[i].getSupplierName().equals(supplierName))
                    // ignore the supplier that need to be deleted
                    continue;
                else
                    try {
                        proxyList[k++]=list[i];
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("Cannot remove the account!!");
                    }
            }
            System.out.println("Account removal has been performed!!");
            list = proxyList;
        }
    }

    // change supplier information
    public void adjustSupplier(String supplierID){
        for (Suppliers supplier : list){
            if(supplier.getSupplierID().equals(supplierID)){
                supplier.input();
            }
            else
                System.out.println("Couldn't find suppliers.");
        }
    }

    // find supplier
    public void findSupplier(String supplierID){
        for (Suppliers supplier : list){
            if(supplier.getSupplierID().equals(supplierID)){
                System.out.println(supplier);
            }
            else
                System.out.println("Couldn't find supplier.");
        }
    }

    // get the amount of supplier
    public void getAmount(){
        System.out.println("Supplier number: "+list.length);
    }

    public void displayList(){
        for(Suppliers supplier : list)
            System.out.println(supplier);
    }

    // override new data to file
    // MUST give this warning: "You MUST choose "Submit change & exit"(press _) to save all your changes"
    public void updataFile(File suppliersFile,File suppliersAddressFile){
        BufferedWriter bw1 = null;
        BufferedWriter bw2 = null;
        try {
            bw1 = new BufferedWriter(new FileWriter(suppliersFile,false));
            bw2 = new BufferedWriter(new FileWriter(suppliersAddressFile,false));
            for(Suppliers supplier : list){
                bw1.write(supplier.getSupplierID() +",");
                bw1.write(supplier.getSupplierName() +",");
                bw1.write(supplier.getSupplierPhoneNumber() +"\n");
                bw2.write(supplier.getSupplierAddress() +"\n");
            }
        } catch (IOException e) {
            System.out.println("Cannot update data!!");
        } finally{
            try {
                bw1.close();
                bw2.close();
            } catch (Exception e) {
                System.out.println("Cannot close the stream!!");
            }
        }
    }
}
