package qlkh;

import java.io.*;
import java.util.*;

/**
 * DONE
 *
 * @author Lilly
 */
public class PositionList {

    private ArrayList<Position> positionList;
    private String filePath = "PositionList.txt";

// --Constructor----------------------------------------------------------------    
    public PositionList() {
        positionList = new ArrayList<>();

        FileInputStream fileInputStream = null;
        Scanner scanFile = null;

        try {
            fileInputStream = new FileInputStream(filePath);
            scanFile = new Scanner(fileInputStream);

            // --Open and read from file--
            while (scanFile.hasNext()) {
                String line = scanFile.nextLine();
                String[] E = null;
                if (line != null) {
                    E = line.split(",");
                }
                if (E.length >= 2 && Tools.isPositionID(E[0])) {
                    Position element = new Position(E[0]);
                    if (Tools.isBoolean(E[1])) {
                        element.setStatus(Boolean.parseBoolean(E[1]));
                    }
                    add(element);
                }
            }
        } catch (FileNotFoundException ex) {
            // --Default init--
            for (short a = 0; a < 10; a++) {
                char area = (char) (65 + a);
                for (short s = 0; s < 20;) {
                    Position element = new Position(area, ++s);
                    positionList.add(element);
                }
            }
            writeToFile();
        } finally {
            try {
                if (scanFile != null) {
                    scanFile.close();
                }
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
            } catch (IOException ex) {
                System.out.println("---Da xay ra loi---");
            }
        }
    }

    public PositionList(ArrayList<Position> positionList) {
        this.positionList = new ArrayList<>(positionList);
        writeToFile();
    }

// -----------------------------------------------------------------------------
// --Find by ID, return index--    
    public int find(String positionID) {
        if (Tools.isPositionID(positionID) && !positionList.isEmpty()) {
            for (int index = 0; index < positionList.size(); index++) {
                String temp = positionList.get(index).getID();
                if (positionID.equalsIgnoreCase(temp)) {
                    return index;
                }
            }
        }
        return -1;
    }

// --Find by area and shelf seri, return index--    
    public int find(char area, short shelf) {
        if (positionList != null) {
            for (int index = 0; index < positionList.size(); index++) {
                char ai = positionList.get(index).getArea();
                short si = positionList.get(index).getShelf();
                if (area == ai && shelf == si) {
                    return index;
                }
            }
        }
        return -1;
    }

// --PositionID Exist In The List Check--
    public boolean isExist(String positionID) {
        if (find(positionID) != -1) {
            return true;
        }
        return false;
    }

// --PositionID Empty Check--
    public boolean isEmptyPosition(String positionID) {
        int index = find(positionID);
        if (index != -1) {
            return positionList.get(index).getStatus();
        }
        return false;
    }

// --Position Empty Count--    
    public int countEmptyPosition() {
        int n = 0;
        for (Position i : positionList) {
            if (i.getStatus()) {
                n++;
            }
        }
        return n;
    }

// --Add new position and write to file--
    public boolean add(Position e) {
        if (!positionList.add(e)) {
            return false;
        }
        writeToFile();
        return true;
    }

// --Add new position by area and shelf seri--
    public boolean add(char area, short shelf) {
        if (find(area, shelf) != -1) {
            return false;
        }
        Position e = new Position(area, shelf);
        return add(e);
    }

// --Add new position by ID--
    public boolean add(String positionID) {
        if (find(positionID) != -1) {
            return false;
        }
        Position e = new Position(positionID);
        return add(e);
    }

// --Console: Add new position--
    public boolean add() {
        System.out.println("---THEM KE HANG MOI---");
        Position e = new Position();
        e.enter();

        if (!add(e)) {
            System.out.println("---Them khong thanh cong---");
            return false;
        }
        System.out.println("---Them thanh cong---");
        return true;
    }

// --Remove position and update file--
    public boolean remove(Position element) {
        if (!positionList.remove(element)) {
            return false;
        }
        writeToFile();
        return true;
    }

// --Remove position by area and shelf seri--
    public boolean remove(char area, short shelf) {
        int index = find(area, shelf);
        if (index != -1) {
            return remove(positionList.get(index));
        }
        return false;
    }

// --Remove position by ID--
    public boolean remove(String positionID) {
        int index = find(positionID);
        if (index != -1) {
            return remove(positionList.get(index));
        }
        return false;
    }

// --Console: Remove position--
    public boolean remove() {
        System.out.println("---XOA KE HANG---");
        System.out.print("Nhap ID: ");
        String positionID = Tools.scan.nextLine();
//        while (!Tools.isPositionID(positionID)) {
//            System.out.print("Nhap ID: ");
//            positionID = Tools.scan.nextLine();
//        }

        if (!remove(positionID)) {
            System.out.println("---Xoa khong thanh cong---");
            return false;
        }
        System.out.println("---Xoa thanh cong---");
        return true;
    }

// --Modify position status by ID and update file--
    public boolean modify(String positionID, boolean status) {
        int index = find(positionID);
        if (index != -1) {
            Position e = positionList.get(index);
            e.setStatus(status);
            set(index, e);
            return true;
        }
        return false;
    }

// --Console: Modify position status--
    public boolean modify() {
        System.out.println("---THAY DOI TRANG THAI KE HANG---");
        System.out.printf("Nhap ID: ");
        String positionID = Tools.scan.nextLine();
        while (!Tools.isPositionID(positionID)) {
            System.out.print("Nhap ID: ");
            positionID = Tools.scan.nextLine();
        }
        System.out.print("Nhap trang thai (1.Trong/0.Day): ");
        String temp = Tools.scan.nextLine();
        while (!Tools.isInteger(temp) || Integer.parseInt(temp) < 0 || Integer.parseInt(temp) > 1) {
            System.out.printf("Nhap trang thai (1.Trong/0.Day): ");
            temp = Tools.scan.nextLine();
        }
        boolean status = true;
        if (Integer.parseInt(temp) != 1) {
            status = false;
        }
        if (!modify(positionID, status)) {
            System.out.println("---Khong thanh cong---");
            return false;
        }
        System.out.println("---Thanh cong---");
        return true;
    }

// --Console: Show empty position--
    private void showEmptyPosition() {
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
        int count = 0;
        for (Position i : positionList) {
            if (i.getStatus()) {
                count++;
                if (count % 10 != 0) {
                    System.out.printf("%-7s", i.getID());
                } else {
                    System.out.printf("%-7s\n", i.getID());
                }
            }
        }
    }

// --Console: Show all position--
    private void showAllPosition() {
        System.out.println("---DANH SACH KE HANG---");
        System.out.println(" ___________ ____________ ");
        System.out.println("| Ma vi tri | Trang thai |");
        for (Position e : positionList) {
            System.out.printf("|    %-7s|    %-8s|\n", e.getID(), e.getStatus() ? "Trong" : "Day");
        }
        System.out.println(" ___________ ____________ ");
    }

// --Console: Show menu--    
    public void show() {
        Tools.cls();
        System.out.println("---XEM DANH SACH KE HANG---");
        System.out.println("0. Quay lai");
        System.out.println("1. Ke con trong");
        System.out.println("2. Tat ca ke");
        System.out.println("3. Thoat");
        System.out.print("Vui long nhap 1 so (0->3): ");
        String option = Tools.scan.nextLine();
        while (!Tools.isInteger(option) || Integer.parseInt(option) < 0 || Integer.parseInt(option) > 3) {
            System.out.print("Vui long nhap 1 so (0->3): ");
            option = Tools.scan.nextLine();
        }
        switch (Integer.parseInt(option)) {
            case 0: {
                return;
            }
            case 1: {
                showEmptyPosition();
                Tools.continute();
                break;
            }
            case 2: {
                showAllPosition();
                Tools.continute();
                break;
            }
            case 3: {
                System.exit(1);
            }
        }
    }

//--Console: Display menu for position--
    public void menu() {
        Tools.cls();
        System.out.println("---THIET LAP KHO---");
        System.out.println("0. Quay lai");
        System.out.println("1. Thay doi trang thai ke hang");
        System.out.println("2. Them ke hang");
        System.out.println("3. Xoa ke hang");
        System.out.println("4. Xem danh sach ke hang");
        System.out.println("5. Thoat");
        System.out.print("Vui long nhap 1 so (0->5): ");
        String option = Tools.scan.nextLine();
        while (!Tools.isInteger(option) || Integer.parseInt(option) < 0 || Integer.parseInt(option) > 5) {
            System.out.print("Vui long nhap 1 so (0->5): ");
            option = Tools.scan.nextLine();
        }
        switch (Integer.parseInt(option)) {
            case 0: {
                return;
            }
            case 1: {
                modify();
                Tools.continute();
                break;
            }
            case 2: {
                add();
                Tools.continute();
                break;
            }
            case 3: {
                remove();
                Tools.continute();
                break;
            }
            case 4: {
                show();
                break;
            }
            case 5: {
                System.exit(1);
            }
        }
    }

// --Write to file--    
    public boolean writeToFile() {
        File file = null;
        FileOutputStream fout = null;
        try {
            file = new File(filePath);
            fout = new FileOutputStream(file);

            // --Create file if not exists--
            if (!file.exists()) {
                file.createNewFile();
            }

            for (Position e : positionList) {
                byte[] bs = e.toString().getBytes();
                fout.write(bs);
                fout.flush();
            }
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            try {
                if (fout != null) {
                    fout.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
// --Getter-Setter--------------------------------------------------------------

    public ArrayList<Position> getPositionList() {
        return positionList;
    }

    public void setPositionList(ArrayList<Position> positionList) {
        this.positionList = positionList;
        writeToFile();
    }

    public Position get(int index) {
        return positionList.get(index);
    }

    public void set(int index, Position element) {
        positionList.set(index, element);
        writeToFile();
    }
}
