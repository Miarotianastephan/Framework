package etu1846.framework.model;
import etu1846.framework.annotation.*;

@Dao(dao = "deptdao")
public class DeptDAO{
    int id;
    @field(val = "dname") String deptNameDAO;

    public DeptDAO(int id, String dnamedao){}
    public DeptDAO(){}
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getDeptNameDAO() {
        return deptNameDAO;
    }
    public void setDeptNameDAO(String deptNameDAO) {
        this.deptNameDAO = deptNameDAO;
    }
}
