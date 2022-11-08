package qlkh;

import java.util.Scanner;

public class ProductMenu {
//	ProductList productList;
//
//	public ProductMenu(ProductList productList) {
//		this.productList = productList;
//	}
//
//	public ProductMenu() {
//
//	}
//
//// --Getter-Setter-----------------------------------------------------
//	public ProductList getProductList() {
//		return productList;
//	}
//
//	public void setProductList(ProductList productList) {
//		this.productList = productList;
//	}

// --Private-method-function-----------------------------------------------------
	private static boolean isInteger(String str) {
		try {
			Integer.parseInt(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

//--Menu-----------------------------------------------------
	public static void productMenu(ProductList productList) {
		productList = new ProductList();
		Scanner sc = new Scanner(System.in);
		String choice;
		do {
			System.out.println("----------MENU----------");
			System.out.println("(1) Them");
			System.out.println("(2) Sua");
			System.out.println("(3) Xoa");
			System.out.println("(4) Sap xep");
			System.out.println("(5) Tim");
			System.out.println("(6) Xuat san pham");
			System.out.println("(7) Thoat");
			System.out.println("Vui long nhap 1 so (1->7)");
			choice = sc.nextLine();
			while (!isInteger(choice) || Integer.parseInt(choice) < 1 || Integer.parseInt(choice) > 7) {
				System.err.println("Nhap sai! Vui long nhap lai 1 so (1->7): ");
				choice = sc.nextLine();
			}
			switch (Integer.parseInt(choice)) {
			case 1: {
				productList.addNew();
				break;
			}
			case 2: {
				productList.modify();
				break;
			}
			case 3: {
				productList.delete();
				break;
			}
			case 4: {
				productList.sort();
				break;
			}
			case 5: {
				productList.find();
				break;
			}
			case 6: {
				productList.showList();
				break;
			}
			default:
				System.out.println("Thoat!");
			}
		} while (Integer.parseInt(choice) != 7);
	}
}
