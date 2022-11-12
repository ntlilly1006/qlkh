package qlkh;

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

        return true;
    }

    // --InvoiceID--X0000000--
    public static boolean isInvoiceID(String invoiceID) {
        if (invoiceID.length() != 8) {
            return false;
        }

        if (invoiceID.charAt(0) != 'I' && invoiceID.charAt(0) != 'E') {
            return false;
        }
        
        if (!isLong(invoiceID.substring(1)) && invoiceID.equalsIgnoreCase("I0000000") && invoiceID.equalsIgnoreCase("E0000000")) {
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
        System.out.println("\n\n\n\n\n");    
    }
}
