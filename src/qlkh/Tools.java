package qlkh;

import java.io.*;
import java.text.*;
import java.util.*;

/**
 *
 * @author Lilly, Ken
 */
public class Tools {
// --Properties-----------------------------------------------------------------
    // --Console scan--

    public static Scanner scan = new Scanner(System.in);

// --Functions check------------------------------------------------------------    
    // --StaffID--
    public static boolean isStaffID(String staffID) {
        if (staffID.length() != 7) {
            return false;
        }

        if (!staffID.startsWith("staff")) {
            return false;
        }

        String temp = staffID.substring(5);
        if (!isLong(temp) || temp.equalsIgnoreCase("00")) {
            return false;
        }

        return true;
    }

    // --SupplierID--
    public static boolean isSupplierID(String supplierID) {
        if (supplierID.length() != 3) {
            return false;
        }

        if (supplierID.charAt(0) != 'S') {
            return false;
        }

        String temp = supplierID.substring(1);
        if (!isLong(temp) || temp.equalsIgnoreCase("00")) {
            return false;
        }

        return true;
    }

    // --DistributorID--
    public static boolean isDistributorID(String distributorID) {
        if (distributorID.length() != 3) {
            return false;
        }

        if (distributorID.charAt(0) != 'D') {
            return false;
        }

        String temp = distributorID.substring(1);
        if (!isLong(temp) || temp.equalsIgnoreCase("00")) {
            return false;
        }

        return true;
    }

    // --PositionID--X00--
    public static boolean isPositionID(String positionID) {
        if (positionID.length() != 3) {
            return false;
        }

        char area = positionID.charAt(0);
        char shelf1 = positionID.charAt(1);
        char shelf2 = positionID.charAt(2);
        if (!(('A' <= area && area <= 'Z')
                && ('0' <= shelf1 && shelf1 <= '9')
                && ('0' <= shelf2 && shelf2 <= '9'))
                || (shelf1 == 0 && shelf2 == 0)) {
            return false;
        }

        return true;
    }

    // --ProductID--
    public static boolean isProductID(String productID) {
        if (productID.length() != 3) {
            return false;
        }

        if (productID.charAt(0) != 'P') {
            return false;
        }

        String temp = productID.substring(1);
        if (!isLong(temp) || temp.equalsIgnoreCase("00")) {
            return false;
        }

        return true;
    }

    // --InvoiceID--X00--
    public static boolean isInvoiceID(String invoiceID) {
        if (invoiceID.length() != 3) {
            return false;
        }

        if (invoiceID.charAt(0) != 'I' && invoiceID.charAt(0) != 'E') {
            return false;
        }

        String temp = invoiceID.substring(1);
        if (!isLong(temp) || temp.equalsIgnoreCase("00")) {
            return false;
        }

        return true;
    }

    // --Unit--Cai-Chiec--
    public static boolean isUnit(String unit) {

        return true;
    }

    // --Date format--dd/MM/yyyy--
    public static boolean isDate(String date) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Calendar element = Calendar.getInstance();
            element.setTime(new SimpleDateFormat("dd/MM/yyyy").parse(date));
            return true;
        } catch (ParseException ex) {
            return false;
        }
    }

    // --Interger check--
    public static boolean isInteger(String element) {
        try {
            Integer.parseInt(element);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // --Long check--
    public static boolean isLong(String element) {
        try {
            Long.parseLong(element);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // --Double check--
    public static boolean isDouble(String element) {
        try {
            Double.parseDouble(element);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // --Boolean check--
    public static boolean isBoolean(String element) {
        try {
            Boolean.parseBoolean(element);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

// --Methods--------------------------------------------------------------------
    // --Press any key to continue--
    public static void continute() {
        System.out.println("\n---Nhan Enter de tiep tuc---");
        Scanner scan = new Scanner(System.in);
        String continute = scan.nextLine();
    }

    // --Clear sceen console--
    public static void cls() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                Runtime.getRuntime().exec("clear");
            }
        } catch (IOException | InterruptedException ex) {
        }
    }
}
