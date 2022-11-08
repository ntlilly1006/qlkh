package qlkh;

import java.util.Scanner;

public class SmartWatch extends Product {
	Details detail = new Details();

	public SmartWatch(Details detail) {
		this.detail = detail;
	}

	public SmartWatch() {
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
