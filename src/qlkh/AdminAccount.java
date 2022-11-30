package qlkh;
public class AdminAccount extends Account {

    public AdminAccount(){
        super();
    }
    public AdminAccount(String taiKhoan, String matKhau, String maNV){
        super(taiKhoan,matKhau,maNV);
    }
    public AdminAccount(AdminAccount account){
        super(account);
    }
    @Override
    public String toString(){
        return getAccountName() +" " +getPassword() +" " +getStaffID() +" " +getRoleID(); 
    }
    @Override
    public void addRoleID(){
        setRoleID("admin");
    }
}
