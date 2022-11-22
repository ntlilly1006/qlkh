package qlkh;
import java.util.Scanner;

public class Address {
    Scanner sc = new Scanner(System.in);
    private int apartmentNumber;
    private String streetName;
    private String wardCommune;
    private String districtDist;
    private String provinceCity;

    public Address(){
        apartmentNumber=0;
        streetName=null;
        wardCommune=null;
        districtDist=null;
        provinceCity=null;
    }
    public Address(int apartmentNumber,String streetName,String wardCommune,String districtDist,String provinceCity){
        this.apartmentNumber=apartmentNumber;
        this.streetName=streetName;
        this.wardCommune=wardCommune;
        this.districtDist=districtDist;
        this.provinceCity=provinceCity;
    }
    public int getApartmentNumber(){
        return apartmentNumber;
    }
    public void setApartmentNumber(){
        apartmentNumber = sc.nextInt();
    }
    public String getStreetName(){
        return streetName;
    }
    public void setStreetName(){
        streetName = sc.nextLine();
    }
    public String getWardCommune(){
        return wardCommune;
    }
    public void setWardCommune(){
        wardCommune = sc.nextLine();
    }
    public String getDistrictDist(){
        return districtDist;
    }
    public void setDistrictDist(){
        districtDist = sc.nextLine();
    }
    public String getProvinceCity(){
        return provinceCity;
    }
    public void setProvinceCity(){
        provinceCity = sc.nextLine();
    }
    public void input(){
        boolean check=false;

//      Check Exception
        while(!check){
            try{
                System.out.print("Nhap so nha: ");
                apartmentNumber = Integer.parseInt(sc.nextLine());
                check = true;
            }
            catch(Exception e){
                System.out.println("So nha chi chua so, khong chua cac ky tu khac");
            }
        }
        System.out.print("Nhap ten duong: ");
        setStreetName();
        System.out.print("Nhap phuong/xa: ");
        setWardCommune();
        System.out.print("Nhap quan/huyen: ");
        setDistrictDist();
        System.out.print("Nhap tinh/thanh pho: ");
        setProvinceCity();
}
    public String toString(){
        return apartmentNumber +"," +streetName +"," +wardCommune +"," +districtDist +"," +provinceCity;
    }
    public static void main(String[] args){
        Address a = new Address();
        a.input();
        System.out.println(a);
    }
}
