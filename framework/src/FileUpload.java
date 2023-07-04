package etu1846.framework;

public class FileUpload{
    String name;
    String path;
    byte[] f_bytes;
    
    public FileUpload(String name, String path, byte[] f_bytes) {
        this.name = name;
        this.path = path;
        this.f_bytes = f_bytes;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPath() {
        return path;
    }
    public void setPath(String path) {
        this.path = path;
    }
    public byte[] getF_bytes() {
        return f_bytes;
    }
    public void setF_bytes(byte[] f_bytes) {
        this.f_bytes = f_bytes;
    }

}