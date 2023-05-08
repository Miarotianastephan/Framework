package etu1846.framework.model;
import java.util.HashMap;
import etu1846.framework.*;

public class ModelView{
    String url;
    HashMap<String,Object> data;

    public HashMap<String, Object> getData() {
        return data;
    }

    public void setData(HashMap<String, Object> data) {
        this.data = data;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public ModelView(String route){
        url = route;
        data = new HashMap<>();
    }

    // function additem
    public void addItem(String key, Object element){
        data.put(key, element);
    }
}