package qlkh;

import java.util.Scanner;

public class Product implements Comparable<Product> {
	protected String id; // mã sản phẩm
	protected String name; // tên sản phẩm
	protected String type; // phân loại
	protected String brand; // thương hiệu
	protected String manufacturingDate;// ngày sản xuất
	protected String unit; // đơn vị tính
	protected long amount; // số lượng tổng
	protected double price; // giá thành
	Scanner sc = new Scanner(System.in);

	public Product() {
	}

	public Product(String id, String name, String type, String brand, String manufacturingDate, String unit,
			long amount, double price) {
		this.id = id;
		this.name = name;
		this.type = type;
		this.brand = brand;
		this.manufacturingDate = manufacturingDate;
		this.unit = unit;
		this.amount = amount;
		this.price = price;
	}

	public Product(Product other) {

	}

//------------------------------------------------------------------------------    
	public int enter() {
		System.out.println("Nhap id: ");
		this.id = sc.nextLine();
		System.out.println("Nhap ten san pham: ");
		this.name = sc.nextLine();
//		System.out.println("Nhap loai san pham: ");
//		this.type = sc.nextLine();
		System.out.println("Nhap thuong hieu: ");
		this.brand = sc.nextLine();
		System.out.println("Nhap ngay san xuat (dd/mm/yyyy): ");
		this.manufacturingDate = sc.nextLine();
		System.out.println("Nhap don vi tinh: ");
		this.unit = sc.nextLine();
		System.out.println("Nhap so luong: ");
		this.amount = sc.nextLong();
		System.out.println("Nhap gia: ");
		this.price = sc.nextDouble();
		sc.nextLine();
		return 0;
	}

	public void title() {
		System.out.printf("%-11s%-12s%-18s%-20s%-22s%-20s%-17s%-20s", "Id", "Ten", "Phan loai", "Thuong hieu",
				"Ngay san xuat", "Don vi tinh", "So luong", "Gia");
	}

	public void display() {
		System.out.printf("%-11s%-12s%-18s%-20s%-22s%-20s%-17s%-20s", this.id, this.name, this.type, this.brand,
				this.manufacturingDate, this.unit, this.amount, this.price);
	}

//--Get-Set---------------------------------------------------------------------
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getManufacturingDate() {
		return manufacturingDate;
	}

	public void setManufacturingDate(String manufacturingDate) {
		this.manufacturingDate = manufacturingDate;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

// -----Equals------------------------------------
	public boolean equals(Product other) {
		if (this == other)
			return true;
		if (!(other instanceof Product))
			return false;

		Product o = (Product) other;

		return Double.compare(this.price, o.price) == 0;
	}

// -----Compare------------------------------------
	public int compareTo(Product other) {
		Product o = (Product) other;
		return this.getId().compareTo(o.getId());
	}
}
