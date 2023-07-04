package etu1846.framework.model;
import etu1846.framework.annotation.*;

@Scopes(scope_val = "singleton")
public class Dept{
    int countAppel;
    int number;
    String name;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Dept(){

    }

    public int getCountAppel() {
        return countAppel;
    }

    public void setCountAppel(int countAppel) {
        this.countAppel = countAppel;
    }
}
