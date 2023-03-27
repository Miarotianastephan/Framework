package etu1846.framework.model;
import etu1846.framework.annotation.*;

@Dao(dao = "deptdao")
public class DeptDAO{
    int id;
    @field(val = "dname") String deptNameDAO;

    public DeptDAO(int id, String dnamedao){}
    public DeptDAO(){}

    @Url(url_name = "find_dept")
    public void get_allDept(){
        System.out.println("You get the second method");
    }
}
