package qlkh;

import java.io.Serializable;
import java.util.Scanner;

public class Details implements Serializable {

	private String graphics;
	private String cpu;
	private String sizeMemory;
	private String color;
	private double sizeScreen;
	private double weight;

	public Details(String graphics, String cpu, String sizeMemory, String color, double sizeScreen, double weight) {
		this.graphics = graphics;
		this.cpu = cpu;
		this.sizeMemory = sizeMemory;
		this.color = color;
		this.sizeScreen = sizeScreen;
		this.weight = weight;
	}

	public Details() {

	}

//-----Nhap thong tin san pham----------------------------------------------------------------
	public int enter() {
		System.out.println("Nhap graphics: ");
		this.graphics = Tools.scan.nextLine();
		System.out.println("Nhap cpu: ");
		this.cpu = Tools.scan.nextLine();
		System.out.println("Nhap kich thuoc bo nho: ");
		this.sizeMemory = Tools.scan.nextLine();
		System.out.println("Nhap mau: ");
		this.color = Tools.scan.nextLine();
		this.sizeScreen = Tools.isSize();
		this.weight = Tools.isWeight();

		return 0;
	}

//-----Xuat thong tin----------------------------------------------------------------
	public void title() {
		System.out.printf("%-18s%-20s%-22s%-15s%-20s%-20s\n", "Graphics", "Cpu", "Size of Memory", "Color",
				"Size of screen", "Weight");
	}

	public void display() {
		System.out.printf("%-18s%-20s%-22s%-15s%-20s%-20s\n", this.graphics, this.cpu, this.sizeMemory, this.color,
				this.sizeScreen, this.weight);
	}

//-----Getter-Setter----------------------------------------------------------------
	public String getGraphics() {
		return graphics;
	}

	public void setGraphics(String graphics) {
		this.graphics = graphics;
	}

	public String getCpu() {
		return cpu;
	}

	public void setCpu(String cpu) {
		this.cpu = cpu;
	}

	public String getSizeMemory() {
		return sizeMemory;
	}

	public void setSizeMemory(String sizeMemory) {
		this.sizeMemory = sizeMemory;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public double getSizeScreen() {
		return sizeScreen;
	}

	public void setSizeScreen(double sizeScreen) {
		this.sizeScreen = sizeScreen;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	@Override
	public String toString() {
		return "graphics: " + graphics + ", cpu: " + cpu + ", sizeMemory: " + sizeMemory + ", color: " + color
				+ ", sizeScreen: " + sizeScreen + ", weight: " + weight + ".";
	}

}
