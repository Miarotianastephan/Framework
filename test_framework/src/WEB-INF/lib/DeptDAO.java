package etu1846.framework.model;
import etu1846.framework.annotation.*;

@Dao(dao = "deptdao")
public class DeptDAO{
    int id;
    @field(val = "dname") String deptNameDAO;

    public DeptDAO(int id, String dnamedao){}
    public DeptDAO(){}
}
