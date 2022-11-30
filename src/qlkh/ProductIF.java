package qlkh;

public interface ProductIF {
	public void add();
	public int modify();
	public boolean modify(String productID, long newAmount);
	public void delete();
	public void findProduct1();
	public Product findProduct2();
	public int findIndex(String id);
	public void sort();
	public void showList();
	public void showAll();
	public void showById();
	public void showByName();
	public void showByType();
	public void showByBrand();
	public void showByDate();
	public void showByUnit();
	public void showByAmount();
	public void showByPrice();
	public void menu();
	public Product get(int index);
}
