package qlkh;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * DONE
 * @author Lilly
 */
public class InvoiceList extends ListAbs {
    private ArrayList<Invoice> invoiceList;
    
    public InvoiceList() {
        this.invoiceList = new ArrayList<Invoice>();
    }
    public InvoiceList(ArrayList<Invoice> invoiceList) {
        this.invoiceList = new ArrayList<Invoice>(invoiceList);
    }
//------------------------------------------------------------------------------
    @Override
    public void clear() {
        invoiceList.clear();
    }
//--Them hoa don moi--    
    public int addNew(Invoice element) {
        //--Co trung ma hoa don hay khong--
        if (find(element.getInvoiceID()) != -1) {
            return -1;
        }
        //--Tao moi neu khong--
        if (!invoiceList.add(element)) 
            return -1;
        return 0;
    }
    
    @Override
    public int addNew() {
        Scanner scan = new Scanner(System.in);
        System.out.println("---THEM HOA DON MOI---");
        System.out.println("--CANH BAO--");
        System.out.println("Them hoa don moi khong thay doi danh sach san pham");
        System.out.println("Huy bo them ?");
        System.out.println("Dong y. (Y)");
        System.out.println("Khong dong y, tiep tuc them hoa don. (N)");
        char option = scan.next().charAt(0);
        if (option == 'Y')
            return -1;
        Invoice element = new Invoice();
        element.enter();
        if (0 != addNew(element)) {
            System.out.println("---Them khong thanh cong---");
            return -1;
        }
        System.out.println("---Them thanh cong---");
        return 0;
    }

//--Xoa hoa don--
    public int delete(String invoiceID) {
        int index = find(invoiceID);
        if (index == -1) {
            return -1;
        }
        invoiceList.remove(invoiceList.get(index));
        return 0;
    }
    
    @Override
    public int delete() {
        Scanner scan = new Scanner(System.in);
        System.out.println("---XOA HOA DON---");
        System.out.println("--CANH BAO--");
        System.out.println("Xoa hoa don moi khong thay doi danh sach san pham");
        System.out.println("Huy bo xoa ?");
        System.out.println("Dong y. (Y)");
        System.out.println("Khong dong y, tiep tuc xoa hoa don. (N)");
        char option = scan.next().charAt(0);
        if (option == 'Y')
            return -1;
        String invID = scan.next();
        
        if (delete(invID) != 0) {
            System.out.println("---Xoa khong thanh cong---");
            return -1;
        }
        else
        System.out.println("---Xoa thanh cong---");
        return 0;
    }

//--Chinh sua thong tin hoa don--
    @Override
    public int modify() { //Hoa don khong duoc sua
        return -1; 
    }
    
//--Tim kiem hoa don--
    public int find(String invoiceID) {
        if (!invoiceList.isEmpty()) {
            for (int index = 0; index < invoiceList.size(); index++) {
                String temp = invoiceList.get(index).getInvoiceID();
                if (invoiceID.equalsIgnoreCase(temp))
                    return index;
            }
        }
        return -1;
    }
    
//--Xem hoa don--   
    private void showAll() {
        if (invoiceList.isEmpty()) {
            System.out.println("---KHONG CO HOA DON NAO---");
            return;
        }
        System.out.println("---TAT CA HOA DON---");
        System.out.println("|   Ma hoa don  |  Thoi gian  |   Loai   |   Nguoi lap  |   Nguoi nhan  |");
        for (Invoice e : invoiceList) {
            e.displayTable();
        }
    }
    
    private void showByIE(char iE) {
        boolean check = false;
        for (Invoice e : invoiceList) {
            if(iE == e.getIE()) {
                check = true;
                break;
            }
        }
        if (!check) {
            System.out.println("---KHONG CO HOA DON THOA YEU CAU---");
            return;
        }
        if (iE == 'I')
            System.out.println("---TAT CA HOA DON NHAP HANG---");
        else if (iE == 'E')
            System.out.println("---TAT CA HOA DON XUAT HANG---");
        System.out.println("|   Ma hoa don  |  Thoi gian  |   Loai   |   Nguoi lap  |   Nguoi nhan  |");
        for (Invoice e : invoiceList) {
            if(iE == e.getIE())
                e.displayTable();
        }
    }

    private void showByDate(String date) {
        boolean check = false;
        for (Invoice e : invoiceList) {
            if(date == e.getDate()) {
                check = true;
                break;
            }
        }
        if (!check) {
            System.out.println("---KHONG CO HOA DON THOA YEU CAU---");
            return;
        }
        System.out.println("---HOA DON NGAY " + date + "---");
        System.out.println("|   Ma hoa don  |  Thoi gian  |   Loai   |   Nguoi lap  |   Nguoi nhan  |");
        for (Invoice e : invoiceList) {
            if(date == e.getDate())
                e.displayTable();
        }
    }
    
    private void showByPreparer(String name) {
        boolean check = false;
        for (Invoice e : invoiceList) {
            if(name == e.getPrepareBy()) {
                check = true;
                break;
            }
        }
        if (!check) {
            System.out.println("---KHONG CO HOA DON THOA YEU CAU---");
            return;
        }
        System.out.println("---HOA DON LAP BOI " + name + "---");
        System.out.println("|   Ma hoa don  |  Thoi gian  |   Loai   |   Nguoi lap  |   Nguoi nhan  |");
        for (Invoice e : invoiceList) {
            if(name == e.getPrepareBy())
                e.displayTable();
        }
    }

    private void showByReceiver(String name) {
        boolean check = false;
        for (Invoice e : invoiceList) {
            if(name == e.getReceivedBy()) {
                check = true;
                break;
            }
        }
        if (!check) {
            System.out.println("---KHONG CO HOA DON THOA YEU CAU---");
            return;
        }
        System.out.println("---HOA DON DO " + name + " NHAN---");
        System.out.println("|   Ma hoa don  |  Thoi gian  |   Loai   |   Nguoi lap  |   Nguoi nhan  |");
        for (Invoice e : invoiceList) {
            if(name == e.getReceivedBy())
                e.displayTable();
        }
    }
    
   
    @Override
    public void showList() {
//        cls;
        System.out.println("---XEM HOA DON---");
        System.out.println("0. Quay lai");
        System.out.println("1. Tat ca hoa don");
        System.out.println("2. Hoa don nhap");
        System.out.println("3. Hoa don xuat");
        System.out.println("4. Loc theo ngay");
        System.out.println("5. Loc theo nguoi lap");
        System.out.println("6. Loc theo nguoi nhan");
        System.out.println("7. Chi tiet hoa don");
        System.out.println("8. Thoat");
        System.out.print("Vui long nhap 1 so (0->8): ");
        Scanner scan = new Scanner(System.in);
        String option = scan.nextLine();
        while(!isInteger(option) || Integer.parseInt(option) < 0 || Integer.parseInt(option) > 8 ) {
            System.out.print("Vui long nhap 1 so (0->8): ");
            option = scan.nextLine();
        } 
        switch(Integer.parseInt(option)) {
            case 0: {
                return;
            }
            case 1: {
                showAll();
                continute();
                break;
            }
            case 2: {
                showByIE('I');
                continute();
                break;
            }
            case 3: {
                showByIE('E');
                continute();
                break;
            }
            case 4: {
                System.out.print("Nhap ngay muon xem (dd/mm/yyyy): ");
                String date = scan.next();
                showByDate(date);
                continute();
                break;
            }
            case 5: {
                System.out.print("Nhap ten nguoi lap: ");
                String name = scan.next();
                showByPreparer(name);
                continute();
                break;
            }
            case 6: {
                System.out.print("Nhap ten nguoi nhan: ");
                String name = scan.next();
                showByReceiver(name);
                continute();
                break;
            }
            case 7: {
                System.out.print("Nhap ID hoa don: ");
                String invoiceID = scan.next();
                int index = find(invoiceID);
                if (-1 != index)
                    invoiceList.get(index).display();
                else
                    System.out.println("---Khong tim thay hoa don---");
                continute();
                break;
            }
            case 8: {
                System.exit(1);
            }
        }
        showList();
    }
//------------------------------------------------------------------------------
    private boolean isInteger(String number) {
        try {
            Integer.parseInt(number);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    private void continute() {
        System.out.println("\n---Nhan phim bat ky de tiep tuc---");
        Scanner scan = new Scanner(System.in);
        String continute = scan.next();
    }
//--Get-Set---------------------------------------------------------------------
    public ArrayList<Invoice> getInvoiceList() {
        return invoiceList;
    }
    public void setInvoiceList(ArrayList<Invoice> invoiceList) {
        this.invoiceList = invoiceList;
    }
}
