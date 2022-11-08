package qlkh;

import java.util.Scanner;

public class SmartPhone extends Product {
	Details detail = new Details();

	public SmartPhone(Details detail) {
		this.detail = detail;
	}

	public SmartPhone() {
	}

	public int enter() {
		super.enter();
		detail.enter();
		return 0;
	}

	public void title() {
		super.title();
		detail.title();
	}

	public void display() {
		super.display();
		detail.display();
	}
}
