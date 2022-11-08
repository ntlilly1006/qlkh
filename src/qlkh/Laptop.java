package qlkh;

import java.util.Scanner;

import org.w3c.dom.html.HTMLTitleElement;

public class Laptop extends Product {
	Details detail = new Details();

	public Laptop(Details detail) {
		super();
		this.detail = detail;
	}

	public Laptop() {
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
