package qlkh;

/**
 * DONE
 * @author Lilly
 */
import java.util.ArrayList;
import java.util.Scanner;

public class Warehouse extends ListAbs{
    private ArrayList<Position> positionList;
    
    public Warehouse() {
        positionList = new ArrayList<Position>();
    }
    public Warehouse(ArrayList<Position> positionList) {
        this.positionList = new ArrayList<Position>(positionList);
    }
//--Khoi tao kho, tra ve -1 0--
    public int init() {
        Scanner scan = new Scanner(System.in);
        positionList.clear();
        System.out.println("---KHOI TAO CO SO VAT CHAT---");
        System.out.printf("Nhap so khu vuc trong kho: ");
        int i = 0;
        short area = scan.nextShort();
        for (short a = 0; a < area; a++) {
            char ar = (char)(65+a);
            System.out.printf("Nhap so ke hang co o khu vuc %c: ", ar);
            short shelf = scan.nextShort();
            for (short s = 0; s < shelf;) {
                addNew(ar, ++s);
                System.out.print(positionList.get(i++).getID() + " ");
            }
            System.out.println();
        }
        System.out.println("---Khoi tao thanh cong---");
        return 0;
    }
    
    public void clear() {
        positionList.clear();
    }

//--Them ke hang moi, tra ve -1 0--
    public int addNew(char area, short shelf) {
        //--Co ton tai hay chua--
        if (find(area, shelf) != -1) {
            return -1;
        }
        //--Tao moi neu null--
        Position e = new Position(area,shelf);
        if (!positionList.add(e)) 
            return -1;
        return 0;
    }
    
    @Override
    public int addNew() {
        Scanner scan = new Scanner(System.in);
        System.out.println("---THEM KE HANG MOI---");
        Position e = new Position();
        e.enter();
        
        if (0 != addNew(e.getArea(), e.getShelf())) {
            System.out.println("---Them khong thanh cong---");
            return -1;
        }
        System.out.println("---Them thanh cong---");
        return 0;
    }
    
//--Xoa ke hang, tra ve -1 0--
    public int delete(String positionID) {
        int index = find(positionID);
        if (index == -1) {
            return -1;
        }
        positionList.remove(positionList.get(index));
        return 0;
    }
    
    public int delete(char area, short shelf) {
        int index = find(area, shelf);
        if (index == -1) {
            return -1;
        }
        positionList.remove(positionList.get(index));
        return 0;
    }

    @Override
    public int delete() {
        Scanner scan = new Scanner(System.in);
        System.out.println("---XOA KE HANG---");
        System.out.printf("Nhap ID vi tri: ");
        String posID = scan.next();
        
        if (delete(posID) != 0) {
            System.out.println("---Xoa khong thanh cong---");
            return -1;
        }
        else
        System.out.println("---Xoa thanh cong---");
        return 0;
    }
    
//--Sua trang thai ke hang, tra ve -1 0--
    public int modify(String positionID, boolean status) {
        int index = find(positionID);
        if (index == -1) {
            return -1;
        }
        Position e = positionList.get(index);
        e.setStatus(status);
        positionList.set(index, e);
        return 0;
    }
    
    @Override
    public int modify() {
        Scanner scan = new Scanner(System.in);
        System.out.println("---THAY DOI TRANG THAI KE HANG---");
        System.out.printf("Nhap ID vi tri: ");
        String posID = scan.next();
        System.out.printf("Nhap trang thai (1.Trong/0.Day): ");
        String temp = scan.next();
        while(!isInteger(temp) || Integer.parseInt(temp) < 0 || Integer.parseInt(temp) > 1 ) {
            System.out.printf("Nhap trang thai (1.Trong/0.Day): ");
            temp = scan.nextLine();
        }
        boolean status = true;
        if (Integer.parseInt(temp) == 0)
            status = false;
        if (modify(posID, status) != 0) {
            System.out.println("---Khong thanh cong---");
            return -1;
        }
        else
        System.out.println("---Thanh cong---");
        return 0;
    }
    
//--Tim kiem ke hang, tra ve index--    
    public int find(String positionID) {
        if (!positionList.isEmpty()) {
            for (int index = 0; index < positionList.size(); index++) {
                String temp = positionList.get(index).getID();
                if (positionID.equalsIgnoreCase(temp))
                    return index;
            }
        }
        return -1;
    }
    
    public int find(char area, short shelf) {
        if (positionList != null) {
            for (int index = 0; index < positionList.size(); index++) {
                char ai = positionList.get(index).getArea();
                short si = positionList.get(index).getShelf();
                if (area == ai && shelf == si)
                    return index;
            }
        }
        return -1;
    }

    public boolean ablePositionCheck(String positionID, boolean status) {
        int index = find(positionID);
        if (index == -1) {
            return false;
        }
        if (status == positionList.get(index).getStatus())
            return true;
        return false;
    }
    
    public int ablePositionCheck() {
        int n = 0;
        for (Position i : positionList)
            if (i.getStatus()) 
                n++;
        return n;
    }
//--Xem danh sach ke hang--
    public void showAblePosition() {
        boolean check = false;
        for (Position i : positionList) {
            if (i.getStatus()) {
                check = true;
                break;
            }
        }
        if (!check) {
            System.out.println("---KHONG CO KE HANG TRONG---");
            return;
        }
        System.out.println("---DANH SACH KE HANG TRONG---");
        System.out.println("|   Ma vi tri  |   Khu vuc  |   Ke hang  |   Trang thai  |");
        for (Position i : positionList) {
            boolean status = i.getStatus();
            if (status)
                i.display();
        }
    }
    
    public void showAll() {
        System.out.println("---DANH SACH KE HANG---");
        System.out.println("|   Ma vi tri  |   Khu vuc  |   Ke hang  |   Trang thai  |");
        for (Position i : positionList) {
            i.display();
        }
    }
   
    @Override
    public void showList() {
//        cls;
        System.out.println("---XEM DANH SACH KE HANG---");
        System.out.println("0. Quay lai");
        System.out.println("1. Ke con trong");
        System.out.println("2. Tat ca ke");
        System.out.println("3. Thoat");
        System.out.print("Vui long nhap 1 so (0->3): ");
        Scanner scan = new Scanner(System.in);
        String option = scan.nextLine();
        while(!isInteger(option) || Integer.parseInt(option) < 0 || Integer.parseInt(option) > 3 ) {
            System.out.print("Vui long nhap 1 so (0->3): ");
            option = scan.nextLine();
        } 
        switch(Integer.parseInt(option)) {
            case 0: {
                return;
            }
            case 1: {
                showAblePosition();
                continute();
                break;
            }
            case 2: {
                showAll();
                continute();
                break;
            }
            case 3: {
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
    public ArrayList<Position> getPositionList() {
        return positionList;
    }
    
    public void setPositionList(ArrayList<Position> positionList) {
        this.positionList = positionList;
    }
    
    public Position get(int index) {
        return positionList.get(index);
    }
}