package qlkh;

/**
 * Create Product
 * @author Lilly
 */

public class ProductList extends ListAbs {  
    public Product[] productList;
    
    public ProductList() {}
    public ProductList(Product[] other) {
        this.productList = other;
    }
    
//-----Chu y (list null)?-------------------------------------------------------    
    public void clear() {
    }
    public int add(Product element) {
        
        return 0;
    }
    
    public int add() {
        //nhap tu ban phim them
        return 0;
    }
    
    public int delete(Product element) {
        
        return 0;
    }
    
    public int delete() {
        //nhap tu ban phim doi tuong xoa
        return 0;
    }
    
    public int modify(Product element) {
        
        return 0;
    }
    
    public int modify() {
        //nhap tu ban phim doi tuong chinh sua
        return 0;
    }
    
    public Product find() {
        //them tuy chon. Tim 1 doi tuong (sp cu the)/nhom doi tuong (cac sp cung brand/type ...)
        //ket hop showList xuat kq
        return null;
    }
    
    public void sort() {
        
    }
    
    public void showList() {
        
    }
//--Private-methor-function-----------------------------------------------------
    private static boolean isInteger(String str) { 
        try {  
            Integer.parseInt(str);
            return true;
        } catch(NumberFormatException e){  
            return false;  
        }
    }
    
    
    
//--Get-Set---------------------------------------------------------------------

    @Override
    public int addNew() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
