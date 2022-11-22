package qlkh;

import java.io.*;
import java.util.Arrays;

public class DistributorList {
    private Distributors[] list;

    public DistributorList(){
        list= null;
    }
    public DistributorList(Distributors[] list){
        this.list= list;
    }

    // create list and read data from file
    public void createList(File distributorsFile,File distributorsAddressFile){  
        list = new Distributors[0];
        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader(distributorsAddressFile));
            String line = br.readLine();
            Address[] distributorsAddressArray= new Address[0];
            while(line != null){
                String[] row = line.split(",");
                Address address = new Address(Integer.parseInt(row[0]), row[1], row[2], row[3], row[4]);
                distributorsAddressArray=addAddressFromFile(address, distributorsAddressArray);
                line = br.readLine();
            }
            br.close();

            br = new BufferedReader(new FileReader(distributorsFile));
            line = br.readLine();
            int index=0;
            while(line != null){
                String[] row = line.split(",");
                Distributors distributor = new Distributors(row[0], row[1], distributorsAddressArray[index], row[2]);
                list = addDistributorFromFile(distributor, list);
                index++;
                line = br.readLine();
            }
        } catch (IOException e) {
            System.out.println("Cannot read the file, using alternative solution instead.");

            Address address = new Address(1,"Duong 1", "P1", "Q1", "TP Mo Mo");
            Distributors distributor = new Distributors("d001", "npp1", address, "02123");
            list=addDistributorFromFile(distributor, list);

            address = new Address(2,"Duong 2", "P2", "Q2", "TP Mo Mo");
            distributor = new Distributors("d002", "npp2", address, "02234");
            list=addDistributorFromFile(distributor, list);

            address = new Address(3,"Duong 3", "P3", "Q3", "TP Mo Mo");
            distributor = new Distributors("d003", "npp3", address, "02345");
            list=addDistributorFromFile(distributor, list);
            
            address = new Address(4,"Duong 4", "P4", "Q4", "TP Mo Mo");
            distributor = new Distributors("d004", "npp4", address, "02456");
            list=addDistributorFromFile(distributor, list);

            address = new Address(5,"Duong 5", "P5", "Q5", "TP Mo Mo");
            distributor = new Distributors("d005", "npp5", address, "02567");
            list=addDistributorFromFile(distributor, list);

            address = new Address(6,"Duong 6", "P6", "Q6", "TP Mo Mo");
            distributor = new Distributors("d006", "npp6", address, "02678");
            list=addDistributorFromFile(distributor, list);

            address = new Address(7,"Duong 7", "P7", "Q7", "TP Mo Mo");
            distributor = new Distributors("d007", "npp7", address, "02789");
            list=addDistributorFromFile(distributor, list);

            address = new Address(8,"Duong 8", "P8", "Q8", "TP Mo Mo");
            distributor = new Distributors("d008", "npp8", address, "02890");
            list=addDistributorFromFile(distributor, list);

            address = new Address(9,"Duong 9", "P9", "Q9", "TP Mo Mo");
            distributor = new Distributors("d009", "npp9", address, "02901");
            list=addDistributorFromFile(distributor, list);

            address = new Address(10,"Duong 10", "P10", "Q10", "TP Mo Mo");
            distributor = new Distributors("d0010", "npp10", address, "02902");
            list=addDistributorFromFile(distributor, list);
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

    //read distributor data from file
    public static Distributors[] addDistributorFromFile(Distributors distributor,Distributors[] arr){
        int n = arr.length;
        arr = Arrays.copyOf(arr, n+1);
        arr[n]= distributor;
        return arr;
    }

    // add distributor to list
    public void addDistributor(Distributors distributor){
        list = addDistributorFromFile(distributor, list);
    }

    // remove distributor from list
    //                               distributorName: name of the distributor that need to be deleted
    public void removeDistributor(String distributorName){

        if (list==null)
            System.out.println("No removal operation can be performed!!");
        else{
            Distributors[] proxyList = new Distributors[list.length-1];
            for (int i = 0, k = 0; i < list.length; i++){
                if (list[i].getDistributorName().equals(distributorName))
                    // ignore the distributor that need to be deleted
                    continue;
                else
                    try {
                        proxyList[k++]=list[i];
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("Cannot remove the distributor!!");
                    }
            }
            System.out.println("Distributor removal has been performed!!");
            list = proxyList;
        }
    }

    // change distributor information
    public void adjustDistributor(String distributorID){
        for (Distributors distributor : list){
            if(distributor.getDistributorAddress().equals(distributorID)){
                distributor.input();
            }
            else
                System.out.println("Couldn't find distributor.");
        }
    }

    // find distributor
    public void findDistributor(String distributorID){
        for (Distributors distributor : list){
            if(distributor.getDistributorAddress().equals(distributorID)){
                System.out.println(distributor);
            }
            else
                System.out.println("Couldn't find distributor.");
        }
    }

    // get the amount of distributor
    public void getAmount(){
        System.out.println("Distributor number: "+list.length);
    }

    public void displayList(){
        for(Distributors distributor : list)
            System.out.println(distributor);
    }

    // override new data to file
    // MUST give this warning: "You MUST choose "Submit change & exit"(press _) to save all your changes"
    public void updataFile(File distributorsFile,File distributorsAddressFile){
        BufferedWriter bw1 = null;
        BufferedWriter bw2 = null;
        try {
            bw1 = new BufferedWriter(new FileWriter(distributorsFile,false));
            bw2 = new BufferedWriter(new FileWriter(distributorsAddressFile,false));
            for(Distributors distributor : list){
                bw1.write(distributor.getDistributorName() +",");
                bw1.write(distributor.getDistributorName() +",");
                bw1.write(distributor.getDistributorPhoneNumber() +"\n");
                bw2.write(distributor.getDistributorAddress() +"\n");
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
