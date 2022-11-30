package qlkh;

import java.util.*;
import java.io.*;

public class ProductList {

	private int size = 0;
	private static Product[] product;
	private String filePath = "products.txt";

	// ------Constructor------
	public ProductList(int size, Product[] product) {
		this.size = size;
		product = product;
	}

	public ProductList() {
		product = new Product[this.size];
		try {
			readFromFile();
		} catch (Exception e) {
			defaultProducts();
			return;
		}
	}

	// -----Add products get from file-----
	public void add(Product element) {
		this.size = product.length;
		product = Arrays.copyOf(product, product.length + 1);
		product[this.size] = element;
		this.size++;
	}

	// -----Add products entered by users-----
	public void add() {
		String choice;
		Product obj = null;
		System.out.println("-----THEM SAN PHAM-----");
		System.out.println("(1) LapTop");
		System.out.println("(2) SmartPhone");
		System.out.println("(3) SmartWatch");
		System.out.println("(0) Thoat");
		System.out.println("Chon 1 so (0->3)");
		choice = Tools.scan.nextLine();
		while (!isInteger(choice) || Integer.parseInt(choice) < 0 || Integer.parseInt(choice) > 3) {
			System.out.println("Khong hop le! Chon lai 1 so (0->3): ");
			choice = Tools.scan.nextLine();
		}
		switch (Integer.parseInt(choice)) {
		case 1: {
			obj = new Laptop();
			obj.enter();
			for (int i = 0; i < product.length; i++) {
				if (product[i].getId().equalsIgnoreCase(obj.id)) {
					System.out.println("Id da ton tai!");
					return;
				}
			}
			add(obj);
			System.out.println("Them san pham thanh cong.");
			break;
		}
		case 2: {
			obj = new SmartWatch();
			obj.enter();
			for (int i = 0; i < product.length; i++) {
				if (product[i].getId().equalsIgnoreCase(obj.id)) {
					System.out.println("Id da ton tai!");
					return;
				}
			}
			add(obj);
			System.out.println("Them san pham thanh cong.");
			break;
		}
		case 3: {
			obj = new SmartWatch();
			obj.enter();
			for (int i = 0; i < product.length; i++) {
				if (product[i].getId().equalsIgnoreCase(obj.id)) {
					System.out.println("Id da ton tai!");
					return;
				}
			}
			add(obj);
			System.out.println("Them san pham thanh cong.");
			break;
		}
		default:
			System.out.println("Thoat!");
		}
		writeToFile();
	}

	// -----Modify product-----
	public int modify(Product element) {
		int check = -1;
		if (product.length == 0) {
			System.out.println("Khong co san pham nao trong danh sach");
			return check;
		}
		System.out.println("Nhap id sp muon thay doi: ");
		String id = Tools.scan.nextLine();
		System.out.println("Nhap ten muon thay doi: ");
		String name = Tools.scan.nextLine();
		for (Product x : product) {
			if (x.getId().equalsIgnoreCase(id)) {
				x.setName(name);
				check = 0;
			}
		}
		writeToFile();
		return check;
	}

	// -----Modify product-----
	public boolean modify(String productID, long newAmount) {
		int index = findIndex(productID);
		if (index != -1) {
			product[index].setAmount(newAmount);
			writeToFile();
			return true;
		}
		return false;
	}

	// -----Modify products-----
	public int modify() {
		int check = -1;
		int index = -1;
		if (product.length == 0) {
			System.out.println("Danh sach rong!");
			return check;
		}
		System.out.println("-----SUA SAN PHAM-----");
		String id = Tools.isProductID();
		boolean test = false;
		for (int i = 0; i < product.length; i++)
			if (product[i].id.equalsIgnoreCase(id))
				test = true;
		if (test == false) {
			System.out.println("Khong co san pham!");
			return -1;
		}
		System.out.println("Chon thong tin thay doi: ");
		System.out.println("(1) Ten : ");
		System.out.println("(2) Phan loai: ");
		System.out.println("(3) Thuong hieu: ");
		System.out.println("(4) Ngay san xuat: ");
		System.out.println("(5) Don vi tinh: ");
		System.out.println("(6) Gia thanh: ");
		System.out.println("Chon 1 so tu (1->6): ");
		String choice = Tools.scan.nextLine();
		while (!isInteger(choice) || Integer.parseInt(choice) < 1 || Integer.parseInt(choice) > 6) {
			System.out.println("Nhap sai! Chon 1 so tu (1->6): ");
			choice = Tools.scan.nextLine();
		}
		switch (Integer.parseInt(choice)) {
		case 1: {
			System.out.println("Nhap ten muon thay doi: ");
			String name = Tools.scan.nextLine();
			for (Product x : product) {
				if (x.getId().equalsIgnoreCase(id)) {
					x.setName(name);
					check = 0;
				}
			}
			break;
		}
		case 2: {
			System.out.println("Nhap phan loai muon thay doi: ");
			String type = Tools.scan.nextLine();
			for (Product x : product) {
				if (x.getId().equalsIgnoreCase(id)) {
					x.setType(type);
					check = 0;
				}
			}
			break;
		}
		case 3: {
			System.out.println("Nhap thuong hieu muon thay doi: ");
			String brand = Tools.scan.nextLine();
			for (Product x : product) {
				if (x.getId().equalsIgnoreCase(id)) {
					x.setBrand(brand);
					check = 0;
				}
			}
			break;
		}
		case 4: {
			System.out.println("Nhap ngay(dd/mm/yyyy) san xuat muon thay doi: ");
			String date = Tools.scan.nextLine();
			for (Product x : product) {
				if (x.getId().equalsIgnoreCase(id)) {
					x.setManufacturingDate(date);
					check = 0;
				}
			}
			break;
		}
		case 5: {
			System.out.println("Nhap don vi tinh muon thay doi: ");
			String unit = Tools.scan.nextLine();
			for (Product x : product) {
				if (x.getId().equalsIgnoreCase(id)) {
					x.setUnit(unit);
					check = 0;
				}
			}
			break;
		}
		case 6: {
			System.out.println("Nhap gia muon thay doi: ");
			double price = Tools.scan.nextDouble();
			for (Product x : product) {
				if (x.getId().equalsIgnoreCase(id)) {
					x.setPrice(price);
					check = 0;
				}
			}
			break;
		}
		default:
			System.out.println("Khong hop le!");
		}
		writeToFile();
		if (check == 0) {
			System.out.println("Thay doi thanh cong.");
		} else {
			System.out.println("Thay doi khong thanh cong");
		}
		return check;
	}

	// -----Delete product-----
	public int delete(int index) {
		int check = -1;
		for (int i = index; i < product.length - 1; i++) {
			product[i] = product[i + 1];
		}
		this.size = product.length;
		product = Arrays.copyOf(product, product.length - 1);
		check = 0;
		writeToFile();
		return check;
	}

	public void delete() {
		if (product.length == 0) {
			System.out.println("Danh sach rong!");
			return;
		}
		System.out.println("-----XOA SAN PHAM-----");
		String id = Tools.isProductID();
		int index = findIndex(id);
		boolean temp = false;
		for (int i = 0; i < product.length; i++)
			if (product[i].id.equalsIgnoreCase(id))
				temp = true;
		if (temp == false) {
			System.out.println("Khong co san pham!");
			return;
		}
		if (delete(index) == 0) {
			System.out.println("Xoa thanh cong.");
		} else {
			System.out.println("Xoa khong thanh cong!");
		}
		writeToFile();
	}

	// -----Find product: console-----
	public void findProduct1() {
		if (product.length == 0) {
			System.out.println("Khong co san pham trong danh sach");
			return;
		}
		boolean check = false;
		String id = Tools.isProductID();
		for (int i = 0; i < product.length; i++)
			if (product[i].id.equalsIgnoreCase(id))
				check = true;
		if (check == false) {
			System.out.println("Khong co san pham!");
			return;
		}
		for (int i = 0; i < product.length; i++)
			if (product[i].id.equalsIgnoreCase(id))
				product[i].display();

	}

	// -----Find product: console-----
	public Product findProduct2() {
		Product temp = null;
		if (product.length == 0) {
			System.out.println("Khong co san pham nao trong danh sach");
			return null;
		}
		System.out.println("Nhap id san pham can tim: ");
		String id = Tools.scan.nextLine();
		for (int i = 0; i < product.length; i++) {
			if (product[i].id.equalsIgnoreCase(id)) {
				temp = product[i];
			}
		}
		return temp;
	}

	// -----Find product: index-----
	public int findIndex(String id) {
		int index = -1;
		for (int i = 0; i < product.length; i++) {
			if (product[i] != null && product[i].id.equalsIgnoreCase(id)) {
				index = i;
			}
		}
		return index;
	}

	// -----Sort by ID-----
	public void sort() {
		if (product.length == 0) {
			System.out.println("Khong co san pham nao trong danh sach");
			return;
		}
		Arrays.sort(product);
		writeToFile();
	}

	// -----Show list of products-----
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
		String choice = Tools.scan.nextLine();
		while (!isInteger(choice) || Integer.parseInt(choice) < 1 || Integer.parseInt(choice) > 10) {
			System.out.println("Nhap sai! Vui long nhap lai 1 so (1->10)");
			choice = Tools.scan.nextLine();
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

	// -----Show product: console-----
	public void showAll() {
		if (product.length == 0) {
			System.out.println("Khong co san pham nao trong danh sach");
			return;
		}
		Product.title();
		for (int i = 0; i < product.length; i++) {
			product[i].display();
		}
	}

	public void showById() {
		if (product.length == 0) {
			System.out.println("Khong co san pham nao trong danh sach");
			return;
		}
		System.out.println("Nhap id san pham: ");
		String id = Tools.isProductID();
		for (int i = 0; i < product.length; i++) {
			if (!product[i].id.equalsIgnoreCase(id)) {
				System.out.println("Khong co san pham trong danh sach!");
				return;
			}
		}
		for (int i = 0; i < product.length; i++) {
			if (product[i].getId().equalsIgnoreCase(id)) {
				product[i].display();
			}
		}
	}

	public void showByName() {
		if (product.length == 0) {
			System.out.println("Khong co san pham nao trong danh sach");
			return;
		}
		System.out.println("Nhap ten san pham: ");
		String name = Tools.scan.nextLine();
		for (int i = 0; i < product.length; i++) {
			if (!product[i].name.equalsIgnoreCase(name)) {
				System.out.println("Khong co san pham trong danh sach!");
				return;
			}
		}
		for (int i = 0; i < product.length; i++) {
			if (product[i].getName().equalsIgnoreCase(name)) {
				product[i].display();
			}
		}
	}

	public void showByType() {
		if (product.length == 0) {
			System.out.println("Khong co san pham nao trong danh sach");
			return;
		}
		System.out.println("Nhap phan loai san san pham: ");
		String type = Tools.scan.nextLine();
		for (int i = 0; i < product.length; i++) {
			if (!product[i].type.equalsIgnoreCase(type)) {
				System.out.println("Khong co san pham trong danh sach!");
				return;
			}
		}
		for (int i = 0; i < product.length; i++) {
			if (product[i].getType().equalsIgnoreCase(type)) {
				product[i].display();
			}
		}
	}

	public void showByBrand() {
		if (product.length == 0) {
			System.out.println("Khong co san pham nao trong danh sach");
			return;
		}
		System.out.println("Nhap thuong hieu san pham: ");
		String brand = Tools.scan.nextLine();
		for (int i = 0; i < product.length; i++) {
			if (!product[i].brand.equalsIgnoreCase(brand)) {
				System.out.println("Khong co san pham trong danh sach!");
				return;
			}
		}
		for (int i = 0; i < product.length; i++) {
			if (product[i].getBrand().equalsIgnoreCase(brand)) {
				product[i].display();
			}
		}
	}

	public void showByDate() {
		if (product.length == 0) {
			System.out.println("Khong co san pham nao trong danh sach");
			return;
		}
		System.out.println("Nhap ngay(dd/mm/yyyy) san pham: ");
		String date = Tools.scan.nextLine();
		for (int i = 0; i < product.length; i++) {
			if (!product[i].manufacturingDate.equalsIgnoreCase(date)) {
				System.out.println("Khong co san pham trong danh sach!");
				return;
			}
		}
		for (int i = 0; i < product.length; i++) {
			if (product[i].getManufacturingDate().equalsIgnoreCase(date)) {
				product[i].display();
			}
		}
	}

	public void showByUnit() {
		if (product.length == 0) {
			System.out.println("Khong co san pham nao trong danh sach");
			return;
		}
		System.out.println("Nhap don vi tinh san pham: ");
		String unit = Tools.scan.nextLine();
		for (int i = 0; i < product.length; i++) {
			if (!product[i].unit.equalsIgnoreCase(unit)) {
				System.out.println("Khong co san pham trong danh sach!");
				return;
			}
		}
		for (int i = 0; i < product.length; i++) {
			if (product[i].getUnit().equalsIgnoreCase(unit)) {
				product[i].display();
			}
		}
	}

	public void showByAmount() {
		if (product.length == 0) {
			System.out.println("Khong co san pham nao trong danh sach");
			return;
		}
		System.out.println("Nhap so luong san pham: ");
		double amount = Tools.scan.nextDouble();
		for (int i = 0; i < product.length; i++) {
			if (product[i].amount != amount) {
				System.out.println("Khong co san pham trong danh sach!");
				return;
			}
		}
		for (int i = 0; i < product.length; i++) {
			if (product[i].getAmount() == amount) {
				product[i].display();
			}
		}
	}

	public void showByPrice() {
		if (product.length == 0) {
			System.out.println("Khong co san pham nao trong danh sach");
			return;
		}
		System.out.println("Nhap gia san pham: ");
		double price = Tools.scan.nextDouble();
		for (int i = 0; i < product.length; i++) {
			if (product[i].price != price) {
				System.out.println("Khong co san pham trong danh sach!");
				return;
			}
		}
		for (int i = 0; i < product.length; i++) {
			if (product[i].getPrice() == price) {
				product[i].display();
			}
		}
	}

	// -----Cut string-----
	public static String cutString(String str, String beginString, String endString) {
		int beginIndex = str.indexOf(beginString) + beginString.length();
		int endIndex = str.indexOf(endString, beginIndex);
		return str.substring(beginIndex, endIndex);
	}

	// -----Convert string to object-----
	public Product dataToObject(String data) {
		Product newProduct = null;
		switch (cutString(data, "type: ", ",")) {
		case "LapTop" -> {
			newProduct = new Laptop();
			newProduct.setId(cutString(data, "id: ", ","));
			newProduct.setName(cutString(data, "name: ", ","));
			newProduct.setType(cutString(data, "type: ", ","));
			newProduct.setBrand(cutString(data, "brand: ", ","));
			newProduct.setManufacturingDate(cutString(data, "manufacturingDate: ", ","));
			newProduct.setUnit(cutString(data, "unit: ", ","));
			newProduct.setAmount(Long.parseLong(cutString(data, "amount: ", ",")));
			newProduct.setPrice(Double.parseDouble(cutString(data, "price: ", ",")));
			Details newDetails = new Details();
			newDetails.setGraphics(cutString(data, "graphics: ", ","));
			newDetails.setCpu(cutString(data, "cpu: ", ","));
			newDetails.setSizeMemory(cutString(data, "sizeMemory: ", ","));
			newDetails.setColor(cutString(data, "color: ", ","));
			newDetails.setSizeScreen(Double.parseDouble(cutString(data, "sizeScreen: ", ",")));
			newDetails.setWeight(Double.parseDouble(cutString(data, "weight: ", ".")));
			((Laptop) newProduct).setDetail(newDetails);
		}
		case "SmartPhone" -> {
			newProduct = new SmartPhone();
			newProduct.setId(cutString(data, "id: ", ","));
			newProduct.setName(cutString(data, "name: ", ","));
			newProduct.setType(cutString(data, "type: ", ","));
			newProduct.setBrand(cutString(data, "brand: ", ","));
			newProduct.setManufacturingDate(cutString(data, "manufacturingDate: ", ","));
			newProduct.setUnit(cutString(data, "unit: ", ","));
			newProduct.setAmount(Long.parseLong(cutString(data, "amount: ", ",")));
			newProduct.setPrice(Double.parseDouble(cutString(data, "price: ", ",")));
			Details newDetails = new Details();
			newDetails.setGraphics(cutString(data, "graphics: ", ","));
			newDetails.setCpu(cutString(data, "cpu: ", ","));
			newDetails.setSizeMemory(cutString(data, "sizeMemory: ", ","));
			newDetails.setColor(cutString(data, "color: ", ","));
			newDetails.setSizeScreen(Double.parseDouble(cutString(data, "sizeScreen: ", ",")));
			newDetails.setWeight(Double.parseDouble(cutString(data, "weight: ", ".")));
			((SmartPhone) newProduct).setDetail(newDetails);
		}
		case "SmartWatch" -> {
			newProduct = new SmartWatch();
			newProduct.setId(cutString(data, "id: ", ","));
			newProduct.setName(cutString(data, "name: ", ","));
			newProduct.setType(cutString(data, "type: ", ","));
			newProduct.setBrand(cutString(data, "brand: ", ","));
			newProduct.setManufacturingDate(cutString(data, "manufacturingDate: ", ","));
			newProduct.setUnit(cutString(data, "unit: ", ","));
			newProduct.setAmount(Long.parseLong(cutString(data, "amount: ", ",")));
			newProduct.setPrice(Double.parseDouble(cutString(data, "price: ", ",")));
			Details newDetails = new Details();
			newDetails.setGraphics(cutString(data, "graphics: ", ","));
			newDetails.setCpu(cutString(data, "cpu: ", ","));
			newDetails.setSizeMemory(cutString(data, "sizeMemory: ", ","));
			newDetails.setColor(cutString(data, "color: ", ","));
			newDetails.setSizeScreen(Double.parseDouble(cutString(data, "sizeScreen: ", ",")));
			newDetails.setWeight(Double.parseDouble(cutString(data, "weight: ", ".")));
			((SmartWatch) newProduct).setDetail(newDetails);
		}
		}
		return newProduct;
	}

	public boolean readFromFile() {
		try {
			File myObj = new File(filePath);
			if (myObj.length() == 0) {
				System.out.println("File is empty!");
				return false;
			}
			Scanner myReader = new Scanner(myObj);
//			product = new Product[this.size];
			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();
				add(dataToObject(data));
			}
			myReader.close();
			System.out.println("Read from file Successfully.");
		} catch (FileNotFoundException e) {
			defaultProducts();
			return false;
		}
		return true;
	}

	// -----Write to file-----
	public boolean writeToFile() {
		try {
			File outPath = new File(filePath);
			FileWriter myWriter = new FileWriter(filePath);
			if (!outPath.exists()) {
				outPath.createNewFile();
			}
			for (int i = 0; i < product.length; i++)
				myWriter.write(product[i].toString() + "\n");
			myWriter.close();
		} catch (IOException e) {
			System.out.println("Error!");
			e.printStackTrace();
			return false;
		}
		return true;
	}

	// -----Default products-----
	public void defaultProducts() {
		product = new Product[4];
		product[0] = new Laptop("P02", "Dell Inspiron", "Dell", "12/10/2003", "Cai", 12, 12.000, "Nvidia", "Intel",
				"256gb", "Trang", 13.3, 2);
		product[1] = new SmartWatch("P01", "Macbook M1", "Apple", "12/12/2003", "Cai", 12, 12.000, "Nvidia", "Ryzon",
				"256gb", "Trang", 13.3, 2);
		product[2] = new SmartPhone("P04", "HP Pavilion", "Dell", "12/9/2000", "Cai", 04, 12.000, "Gtx", "Intel",
				"256gb", "Trang", 13.3, 2);
		product[3] = new Laptop("P03", "Dell Inspiron", "Dell", "12/10/2003", "Cai", 20, 12.000, "Nvidia", "Intel",
				"256gb", "Trang", 13.3, 2);
		writeToFile();
	}

	public void menu() {
		String choice;
		do {
			System.out.println("----------MENU----------");
			System.out.println("(1) Them.");
			System.out.println("(2) Sua.");
			System.out.println("(3) Xoa.");
			System.out.println("(4) Tim kiem.");
			System.out.println("(5) Sap xep.");
			System.out.println("(6) Hien thi danh sach san pham.");
			System.out.println("(0) Thoat.");
			System.out.println("Chon 1 so (0->6)");
			choice = Tools.scan.nextLine();
			while (!isInteger(choice) || Integer.parseInt(choice) < 0 || Integer.parseInt(choice) > 6) {
				System.out.println("Khong hop le! Nhap lai: ");
				choice = Tools.scan.nextLine();
			}
			switch (Integer.parseInt(choice)) {
			case 1: {
				add();
				break;
			}
			case 2: {
				modify();
				break;
			}
			case 3: {
				delete();
				break;
			}
			case 4: {
				findProduct1();
				break;
			}
			case 5: {
				sort();
				break;
			}
			case 6: {
				showList();
				break;
			}
			default:
				System.out.println("Thoat!");
			}
		} while (Integer.parseInt(choice) != 0);
	}

	// --Private-method-function-----------------------------------------------------
	private static boolean isInteger(String str) {
		try {
			Integer.parseInt(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	// --Get-Set---------------------------------------------------------------------
	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public Product[] getProduct() {
		return product;
	}

	public void setProduct(Product[] product) {
//		product = product;
	}

	public Product get(int index) {
		if (index != -1) {
			return product[index];
		} else {
			return null;
		}
	}
}
