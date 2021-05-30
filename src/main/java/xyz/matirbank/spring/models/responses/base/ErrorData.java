package xyz.matirbank.spring.models.responses.base;

public class ErrorData {
    
    int code;
    String name;
    String details;
    
    public ErrorData() {}

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
    
}
