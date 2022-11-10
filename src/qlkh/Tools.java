package qlkh;

import java.util.Scanner;

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
        if (positionID.length() != 3) 
            return false;
        
        char area = positionID.charAt(0);
        char shelf1 = positionID.charAt(1);
        char shelf2 = positionID.charAt(2);
        if (!( ('A' <= area && area <= 'Z') 
            && ('0' <= shelf1 && shelf1 <= '9') 
            && ('0' <= shelf2 && shelf2 <= '9') )
            || (shelf1 == 0 && shelf2 == 0))
            return false;
        
        return true;
    }
      
    // --InvoiceID check--X0000000--
    public static boolean isInvoiceID(String invoiceID) {
//        if (invoiceID.length() != 3) 
//            return false;
//        
//        char area = positionID.charAt(0);
//        char shelf1 = positionID.charAt(1);
//        char shelf2 = positionID.charAt(2);
//        if (!( ('A' <= area && area <= 'Z') 
//            && ('0' <= shelf1 && shelf1 <= '9') 
//            && ('0' <= shelf2 && shelf2 <= '9') ))
//            return false;
//        
        return true;
    }
    
    // --Date format check--X0000000--
    public static boolean isDate(String date) {
//        if (invoiceID.length() != 3) 
//            return false;
//        
//        char area = positionID.charAt(0);
//        char shelf1 = positionID.charAt(1);
//        char shelf2 = positionID.charAt(2);
//        if (!( ('A' <= area && area <= 'Z') 
//            && ('0' <= shelf1 && shelf1 <= '9') 
//            && ('0' <= shelf2 && shelf2 <= '9') ))
//            return false;
//        
        return true;
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
        System.out.println("\n---Nhan phim bat ky de tiep tuc---");
        Scanner scan = new Scanner(System.in);
        String continute = scan.next();
    }
    
    // --Clear sceen console--
    public static void cls() {
        
    }
    

}
