package xyz.matirbank.spring.models.responses.base;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {

    int code;
    String summary;
    List<ErrorData> errors;

    public ErrorResponse() {
    }

    public ErrorResponse(int code, String summary) {
        this.code = code;
        this.summary = summary;
        this.errors = null;
    }

    public ErrorResponse(int code, String summary, List<ErrorData> errors) {
        this.code = code;
        this.summary = summary;
        this.errors = errors;
    }

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
