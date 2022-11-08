package qlkh;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import javax.swing.border.TitledBorder;

import java.util.*;

public class ProductList extends ListAbs {
	ArrayList<Product> productList = new ArrayList<>();
	Product element = null;
	Scanner sc = new Scanner(System.in);

	public ProductList() {
	}

	public ProductList(ArrayList<Product> productList) {
		this.productList = productList;
	}

// ---Them san pham--------------------------------------------
	public int add(Product element) {
		element.enter();
		this.productList.add(element);
		return 0;
	}

	public int addNew() {
//		Product element = null;
		int check = -1;
		System.out.println("-----THEM SAN PHAM-----");
		System.out.println("Chon loai san pham muon them: ");
		System.out.println("(1) Laptop");
		System.out.println("(2) Dien thoai");
		System.out.println("(3) Dong ho thong minh");
		System.out.println("Nhap 1 so (1->3): ");
		String choice = sc.nextLine();
		while (Integer.parseInt(choice) < 1 || Integer.parseInt(choice) > 3) {
			System.out.println("Nhap sai! Chon 1 so (1->3): ");
			choice = sc.nextLine();
		}
		switch (Integer.parseInt(choice)) {
		case 1: {
			element = new Laptop();
			element.setType("Laptop");
			element.enter();
			this.productList.add(element);
			check = 0;
			break;
		}
		case 2: {
			element = new SmartPhone();
			element.setType("Smartphone");
			element.enter();
			this.productList.add(element);
			check = 0;
			break;
		}
		case 3: {
			element = new SmartWatch();
			element.setType("Smartwatch");
			element.enter();
			this.productList.add(element);
			check = 0;
			break;
		}
		default:
			System.out.println("Khong co trong danh sach nhung san pham tren! Chon lai: ");
		}

		if (check == 0)
			System.out.println("Them san pham thanh cong.");
		else
			System.out.println("Them san pham khong thanh cong!");

		return check;
	}

// ---Xoa san pham--------------------------------------------
	public boolean delete(Product element) {
		boolean check = false;
		if (this.productList.contains(element))
			check = this.productList.remove(element);

		return check;
	}

	public int delete() {
		int check = -1;
		if (this.productList.isEmpty()) {
			System.out.println("Khong co san pham nao trong danh sach");
			return check;
		}

		System.out.println("-----XOA SAN PHAM-----");
		System.out.println("Nhap id san pham muon xoa: ");
		String id = sc.nextLine();

		for (Product x : this.productList)
			if (x.getId().equalsIgnoreCase(id)) {
				this.productList.remove(x);
				check = 0;
			}

		if (check == 0)
			System.out.println("Xoa san pham thanh cong.");
		else
			System.out.println("Xoa san pham khong thanh cong!");

		return check;
	}

// ---Sua san pham--------------------------------------------
	public int modify(Product element) {
		int check = -1;
		if (this.productList.isEmpty()) {
			System.out.println("Khong co san pham nao trong danh sach");
			return check;
		}
		System.out.println("Nhap id sp muon thay doi: ");
		String id = sc.nextLine();
		System.out.println("Nhap ten muon thay doi: ");
		String name = sc.nextLine();
		for (Product x : this.productList)
			if (x.getId().equalsIgnoreCase(id)) {
				x.setName(name);
				check = 0;
			}

		if (check == 0)
			System.out.println("Thay doi thanh cong.");
		else
			System.out.println("Thay doi khong thanh cong");

		return check;
	}

	public int modify() {
		int check = -1;
		if (this.productList.isEmpty()) {
			System.out.println("Khong co san pham nao trong danh sach");
			return check;
		}

		System.out.println("Nhap id sp muon thay doi: ");
		String id = sc.nextLine();
		System.out.println("Chon thong tin thay doi: ");
		System.out.println("(1) Ten : ");
		System.out.println("(2) Phan loai: ");
		System.out.println("(3) Thuong hieu: ");
		System.out.println("(4) Ngay san xuat: ");
		System.out.println("(5) Don vi tinh: ");
		System.out.println("(6) So luong tong: ");
		System.out.println("(7) Gia thanh: ");
		String choice = sc.nextLine();
		switch (Integer.parseInt(choice)) {
		case 1: {
			System.out.println("Nhap ten muon thay doi: ");
			String name = sc.nextLine();
			for (Product x : this.productList)
				if (x.getId().equalsIgnoreCase(id)) {
					x.setName(name);
					check = 0;
				}
			break;
		}
		case 2: {
			System.out.println("Nhap phan loai muon thay doi: ");
			String type = sc.nextLine();
			for (Product x : this.productList)
				if (x.getId().equalsIgnoreCase(id)) {
					x.setType(type);
					check = 0;
				}
			break;
		}
		case 3: {
			System.out.println("Nhap thuong hieu muon thay doi: ");
			String brand = sc.nextLine();
			for (Product x : this.productList)
				if (x.getId().equalsIgnoreCase(id)) {
					x.setBrand(brand);
					check = 0;
				}
			break;
		}
		case 4: {
			System.out.println("Nhap ngay(dd/mm/yyyy) san xuat muon thay doi: ");
			String date = sc.nextLine();
			for (Product x : this.productList)
				if (x.getId().equalsIgnoreCase(id)) {
					x.setManufacturingDate(date);
					check = 0;
				}
			break;
		}
		case 5: {
			System.out.println("Nhap don vi tinh muon thay doi: ");
			String unit = sc.nextLine();
			for (Product x : this.productList)
				if (x.getId().equalsIgnoreCase(id)) {
					x.setUnit(unit);
					check = 0;
				}
			break;
		}
		case 6: {
			System.out.println("Nhap so luong muon thay doi: ");
			int amount = sc.nextInt();
			for (Product x : this.productList)
				if (x.getId().equalsIgnoreCase(id)) {
					x.setAmount(amount);
					check = 0;
				}
			break;
		}
		case 7: {
			System.out.println("Nhap gia muon thay doi: ");
			double price = sc.nextDouble();
			for (Product x : this.productList)
				if (x.getId().equalsIgnoreCase(id)) {
					x.setPrice(price);
					check = 0;
				}
			break;
		}
		default:
			System.out.println("Khong hop le!");
		}

		if (check == 0)
			System.out.println("Thay doi thanh cong.");
		else
			System.out.println("Thay doi khong thanh cong");

		return check;
	}

// ---Tim san pham--------------------------------------------
	public void find() {
		if (this.productList.isEmpty()) {
			System.out.println("Khong co san pham nao trong danh sach");
			return;
		}
		System.out.println("Nhap id san pham can tim: ");
		String id = sc.nextLine();
		element.title();
		for (Product x : this.productList)
			if (x.getId().equalsIgnoreCase(id))
				x.display();
	}

//	public static void sort(ArrayList<Product> list) {
//		list.sort((o1, o2) -> o1.getId().compareTo(o2.getId()));
//	}

// ---Sap xep san pham theo id--------------------------------------------
	public void sort() {
		if (this.productList.isEmpty()) {
			System.out.println("Khong co san pham nao trong danh sach");
			return;
		}
		System.out.println("Danh sach san pham sau khi sap xep");
		element.title();
		for (Product x : this.productList)
			x.display();
		Collections.sort(this.productList);
	}

// ---Hien thi danh sach san pham--------------------------------------------
	public void showList() {
		System.out.println("---XEM SAN PHAM---");
		System.out.println("(1) Tat ca cac san pham");
		System.out.println("(2) Loc theo id");
		System.out.println("(3) Loc theo ten");
		System.out.println("(4) Loc theo phan loai");
		System.out.println("(5) Loc theo thuong hieu");
		System.out.println("(6) Loc theo ngay san xuat(dd/mm/yyyy)");
		System.out.println("(7) Loc theo don vi tinh");
		System.out.println("(8) Loc theo so luong");
		System.out.println("(9) Loc theo gia");
		System.out.println("(10) Thoat");
		System.out.print("Vui long nhap 1 so (0->9): ");
		String choice = sc.nextLine();
		while (!isInteger(choice) || Integer.parseInt(choice) < 1 || Integer.parseInt(choice) > 10) {
			System.out.println("Nhap sai! Vui long nhap lai 1 so (1->10)");
			choice = sc.nextLine();
		}
		switch (Integer.parseInt(choice)) {
		case 1: {
			showAll();
			break;
		}
		case 2: {
			showById();
			break;
		}
		case 3: {
			showByName();
			break;
		}
		case 4: {
			showByType();
			break;
		}
		case 5: {
			showByBrand();
			break;
		}
		case 6: {
			showByDate();
			break;
		}
		case 7: {
			showByUnit();
			break;
		}
		case 8: {
			showByAmount();
			break;
		}
		case 9: {
			showByPrice();
			break;
		}
		default:
			System.out.println("Thoat!");
			System.exit(0);
		}
	}

	public void showAll() {
		if (this.productList.isEmpty()) {
			System.out.println("Khong co san pham nao trong danh sach");
			return;
		}
		System.out.println("Danh sach tat ca cac san pham: ");
		Iterator<Product> iterator = this.productList.iterator();
		element.title();
		while (iterator.hasNext())
			iterator.next().display();
	}

	public void showById() {
		int check = -1;
		if (this.productList.isEmpty()) {
			System.out.println("Khong co san pham nao trong danh sach");
			return;
		}
		System.out.println("Nhap id san pham: ");
		String id = sc.nextLine();
		element.title();
		for (Product x : this.productList)
			if (x.getId().equalsIgnoreCase(id)) {
				x.display();
				check = 0;
			}
				
		if(check == -1)
			System.out.println("Khong co san pham trong danh sach");
	}

	public void showByName() {
		if (this.productList.isEmpty()) {
			System.out.println("Khong co san pham nao trong danh sach");
			return;
		}
		System.out.println("Nhap ten san pham: ");
		String name = sc.nextLine();
		element.title();
		for (Product x : this.productList)
			if (x.getName().equalsIgnoreCase(name))
				x.display();
	}

	public void showByType() {
		if (this.productList.isEmpty()) {
			System.out.println("Khong co san pham nao trong danh sach");
			return;
		}
		System.out.println("Nhap phan loai san san pham: ");
		String type = sc.nextLine();
		element.title();
		for (Product x : this.productList)
			if (x.getType().equalsIgnoreCase(type))
				x.display();
	}

	public void showByBrand() {
		if (this.productList.isEmpty()) {
			System.out.println("Khong co san pham nao trong danh sach");
			return;
		}
		System.out.println("Nhap thuong hieu san pham: ");
		String brand = sc.nextLine();
		element.title();
		for (Product x : this.productList)
			if (x.getBrand().equalsIgnoreCase(brand))
				x.display();
	}

	public void showByDate() {
		if (this.productList.isEmpty()) {
			System.out.println("Khong co san pham nao trong danh sach");
			return;
		}
		System.out.println("Nhap ngay(dd/mm/yyyy) san pham: ");
		String date = sc.nextLine();
		element.title();
		for (Product x : this.productList)
			if (x.getManufacturingDate().equalsIgnoreCase(date))
				x.display();
	}

	public void showByUnit() {
		if (this.productList.isEmpty()) {
			System.out.println("Khong co san pham nao trong danh sach");
			return;
		}
		System.out.println("Nhap don vi tinh san pham: ");
		String unit = sc.nextLine();
		element.title();
		for (Product x : this.productList)
			if (x.getUnit().equalsIgnoreCase(unit))
				x.display();
	}

	public void showByAmount() {
		if (this.productList.isEmpty()) {
			System.out.println("Khong co san pham nao trong danh sach");
			return;
		}
		System.out.println("Nhap so luong san pham: ");
		double amount = sc.nextDouble();
		element.title();
		for (Product x : this.productList)
			if (x.getAmount() == amount)
				x.display();
	}

	public void showByPrice() {
		if (this.productList.isEmpty()) {
			System.out.println("Khong co san pham nao trong danh sach");
			return;
		}
		System.out.println("Nhap gia san pham: ");
		double price = sc.nextDouble();
		element.title();
		for (Product x : this.productList)
			if (x.getPrice() == price)
				x.display();
	}

//--Private-method-function-----------------------------------------------------
	private static boolean isInteger(String str) {
		try {
			Integer.parseInt(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

//--Get-Set---------------------------------------------------------------------
	public ArrayList<Product> getProductList() {
		return productList;
	}

	public void setProductList(ArrayList<Product> productList) {
		this.productList = productList;
	}

    @Override
    public void clear() {
        productList.clear();
    }
}
