package xyz.matirbank.spring.models.responses.base;

import java.util.List;

public class ErrorResponse {
    
    int code;
    String summary;
    List<ErrorData> errors;
    
    public ErrorResponse() {}

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public List<ErrorData> getErrors() {
        return errors;
    }

    public void setErrors(List<ErrorData> errors) {
        this.errors = errors;
    }
    
    
}
