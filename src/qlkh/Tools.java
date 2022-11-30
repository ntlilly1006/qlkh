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
		if (!(('A' <= area && area <= 'Z') && ('0' <= shelf1 && shelf1 <= '9') && ('0' <= shelf2 && shelf2 <= '9'))
				|| (shelf1 == 0 && shelf2 == 0)) {
			return false;
		}

		return true;
	}

	// --ProductID--
	public static String isProductID() {
		String productId;
		System.out.println("Nhap id (VD: P01): ");
		do {
			productId = scan.nextLine();
			productId = productId.toUpperCase();
			productId = productId.trim();
			String numId = productId.substring(1);
			if (!productId.startsWith("P") || productId.endsWith("00") || !isInteger(numId) || productId.length() != 3
					|| productId == null) {
				System.out.println("Sai dinh dang! Nhap lai id (VD: P01): ");
			} else {
				return productId;
			}
		} while (true);
	}

	// --Long check (product)--
	public static long isAmount() {
		String l = null;
		System.out.println("Nhap so luong: ");
		do {

			l = scan.nextLine();
			if (!isLong(l) || l == null)
				System.out.println("Sai dinh dang! Nhap lai: ");
			else
				return Long.parseLong(l);
		} while (true);
	}

	// --Double check(product)--
	public static double isPrice() {
		String d = null;
		System.out.println("Nhap don gia: ");
		do {
			d = scan.nextLine();
			if (!isDouble(d) || d == null)
				System.out.println("Sai dinh dang! Nhap lai: ");
			else
				return Double.parseDouble(d);
		} while (true);
	}

	public static double isSize() {
		String s = null;
		System.out.println("Nhap kich thuoc man hinh: ");
		do {

			s = scan.nextLine();
			if (!isDouble(s) || s == null)
				System.out.println("Sai dinh dang! Nhap lai: ");
			else
				return Double.parseDouble(s);
		} while (true);
	}

	public static double isWeight() {
		String w = null;
		System.out.println("Nhap trong luong: ");
		do {
			w = scan.nextLine();
			if (!isDouble(w) || w == null)
				System.out.println("Sai dinh dang! Nhap lai: ");
			else
				return Double.parseDouble(w);
		} while (true);
	}

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

	// PhoneNumber
    public static boolean isPhoneNumber(String phoneNumber){
        if (phoneNumber.length() != 10) {
            return false;
        }

        if (!isInteger(phoneNumber)){
            return false;
        }

        if (phoneNumber.charAt(0) != '0') {
            return false;
        }

        return true;
    }
    
    // Position in Staff class
    public static boolean isPosition(String position){
        if (!position.equals("Quan ly") && !position.equals("Nhan vien"))
            return false;
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
