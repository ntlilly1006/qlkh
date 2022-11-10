package qlkh;

/**
 * DONE
 *
 * @author Lilly
 */
public class Position {

    private String positionID;     //mã vi tri A01 B05 ...
    private char area;             //khu vực ABC...
    private short shelf;           //kệ 123...
    private boolean status;        //empty true & full false ?

// --Constructor----------------------------------------------------------------    
    public Position() {
    }

    public Position(String positionID) {
        this.positionID = positionID;
        this.area = positionID.charAt(0);
        String temp = positionID.substring(1);
        this.shelf = (short) Integer.parseInt(temp);
        this.status = true;
    }

    public Position(char area, short shelf) {
        this.area = area;
        this.shelf = shelf;
        this.status = true;
        String at = String.valueOf(area);
        String st = String.valueOf(shelf);
        if (shelf < 10) {
            st = "0" + st;
        }
        this.positionID = at + st;
    }

    public Position(Position other) {
        this.positionID = other.positionID;
        this.area = other.area;
        this.shelf = other.shelf;
        this.status = other.status;
    }

// -----------------------------------------------------------------------------    
// --Console: Enter a position by ID--
    public void enter() {
        System.out.println("ID vi tri ke hang co dang X00, voi X la ky tu in hoa trong bang chu cai tieng Anh, 00 la so thu tu cua ke hang");
        System.out.print("Nhap ID: ");
        positionID = Tools.scan.nextLine();
        while (!Tools.isPositionID(positionID)) {
            System.out.print("Nhap ID: ");
            positionID = Tools.scan.nextLine();
        }

        area = positionID.charAt(0);
        String temp = positionID.substring(1);
        shelf = (short) Integer.parseInt(temp);
        status = true;
    }

    @Override
    public String toString() {
        return positionID + "," + status + "\n";
    }

// --Getter-Setter--------------------------------------------------------------
    public String getID() {
        return positionID;
    }

    public void setID(String positionID) {
        this.positionID = positionID;
    }

    public char getArea() {
        return area;
    }

    public void setArea(char area) {
        this.area = area;
    }

    public short getShelf() {
        return shelf;
    }

    public void setShelf(short shelf) {
        this.shelf = shelf;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
