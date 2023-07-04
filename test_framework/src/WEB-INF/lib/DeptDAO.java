package etu1846.framework.model;
import etu1846.framework.annotation.*;


@Scopes(scope_val = "default")
@Dao(dao = "deptdao")
public class DeptDAO{
    
    int countAppel;
    int id;
    @field(val = "dname") String deptNameDAO;

    public DeptDAO(int id, String dnamedao){}
    public DeptDAO(){}

    
    public int getCountAppel() {
        return countAppel;
    }

    public void setCountAppel(int countAppel) {
        this.countAppel = countAppel;
    }
    
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
