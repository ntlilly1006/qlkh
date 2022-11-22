package qlkh;

import java.io.IOException;
import java.util.Scanner;

public class Menu {
	public Menu() throws IOException {
		ProductList obj = new ProductList();
		Scanner sc = new Scanner(System.in);
		if (!obj.readFromFile())
			obj.defaultProducts();
		String choice;
		do {
			System.out.println("----------MENU----------");
			System.out.println("(1) Sua.");
			System.out.println("(2) Xoa.");
			System.out.println("(3) Tim kiem.");
			System.out.println("(4) Sap xep.");
			System.out.println("(5) Hien thi danh sach san pham.");
			System.out.println("(0) Thoat.");
			System.out.println("Chon 1 so (0->5)");
			choice = sc.nextLine();
			while (!Tools.isInteger(choice) || Integer.parseInt(choice) < 0 || Integer.parseInt(choice) > 5) {
				System.out.println("Khong hop le! Nhap lai: ");
				choice = sc.nextLine();
			}
			switch (Integer.parseInt(choice)) {
			case 1: {
				obj.modify();
				break;
			}
			case 2: {
				obj.delete();
				break;
			}
			case 3: {
				obj.findProduct1();
				break;
			}
			case 4: {
				obj.sort();
				break;
			}
			case 5: {
				obj.showList();
				break;
			}
			default:
				System.out.println("Thoat!");
			}
		} while (Integer.parseInt(choice) != 0);
	}

	private static boolean isInteger(String str) {
		try {
			Integer.parseInt(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
}
