package qlkh;

import java.util.Scanner;

/**
 * DONE
 *
 * @author Lilly
 */
public class Position {

    private String positionID;     //mã vi tri A01 B05 ...
    private char area;             //khu vực ABC...
    private short shelf;           //kệ 123...
    private boolean status;        //able true & enable false ?

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
    //------------------------------------------------------------------------------    

    public void enter() {
        Scanner scan = new Scanner(System.in);
        System.out.printf("Nhap ma khu vuc (ABC...): ");
        area = scan.next().charAt(0);
        System.out.printf("Nhap ma ke hang (123...): ");
        shelf = scan.nextShort();
        this.status = true;
        String at = String.valueOf(area);
        String st = String.valueOf(shelf);
        if (shelf < 10) {
            st = "0" + st;
        }
        this.positionID = at + st;
        System.out.println("ID ke hang: " + positionID);
    }

    public void display() {//   |     A00     |       A       |      0       |     Trong	|
        System.out.println("|     " + positionID + "     |      " + area + "      |     " + shelf + "      |     " + ((status) ? "Trong" : "Day") + "\t|");
    }
//--Get-Set---------------------------------------------------------------------

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
