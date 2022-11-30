package qlkh;

public class StaffAccount extends Account {
    
    public StaffAccount(){
        super();
    }
    public StaffAccount(String taiKhoan, String matKhau, String maNV){
        super(taiKhoan,matKhau,maNV);
    }
    public StaffAccount(StaffAccount account){
        super(account);
    }
    @Override
    public void addRoleID(){
        setRoleID("staff");
    }
    @Override
    public String toString(){
        return getAccountName() +" " +getPassword() +" " +getStaffID() +" " +getRoleID(); 
    }
}
