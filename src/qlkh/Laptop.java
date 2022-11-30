package qlkh;

public class Laptop extends Product {
	private Details detail = null;

	public Laptop(String id, String name, String brand, String manufacturingDate, String unit, long amount,
			double price, String graphics, String cpu, String sizeMemory, String color, double sizeScreen,
			double weight) {
		super(id, name, "LapTop", brand, manufacturingDate, unit, amount, price);
		this.detail = new Details(graphics, cpu, sizeMemory, color, sizeScreen, weight);
	}

	public Laptop() {
		this.detail = new Details();
		this.type = "LapTop";
	}

	public int enter() {
		super.enter();
		detail.enter();
		setDetail(detail);
		return 0;
	}

	public static void title() {
		Product.title();
		Details.title();
	}

	public void display() {
		super.display();
		detail.display();
	}

	public Details getDetail() {
		return detail;
	}

	public void setDetail(Details detail) {
		this.detail = detail;
	}

	@Override
	public String toString() {
		return super.toString() + detail.toString();
	}
}
