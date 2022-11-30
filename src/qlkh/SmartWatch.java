package qlkh;

public class SmartWatch extends Product {
	private Details detail = null;

	public SmartWatch(String id, String name, String brand, String manufacturingDate, String unit, long amount,
			double price, String graphics, String cpu, String sizeMemory, String color, double sizeScreen,
			double weight) {
		super(id, name, "SmartWatch", brand, manufacturingDate, unit, amount, price);
		this.detail = new Details(graphics, cpu, sizeMemory, color, sizeScreen, weight);
	}

	public SmartWatch() {
		this.detail = new Details();
		this.type = "SmartWatch";
	}

	public int enter() {
		super.enter();
		detail.enter();
		return 0;
	}

	public static void title() {
		Product.title();
		Details.title();
	}

	@Override
	public String toString() {
		return super.toString() + detail.toString();
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
}
