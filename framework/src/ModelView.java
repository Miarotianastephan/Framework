package etu1846.framework.model;

public class ModelView{
    String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public ModelView(String route){
        url = route;
    }
}