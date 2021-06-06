package xyz.matirbank.spring.models.responses.base;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseResponse<T> {
    
    int status;
    T data;
    ErrorResponse error;
    Date date_updated = new Date();
    
    public BaseResponse(){}

    public BaseResponse(int status, T data, ErrorResponse error) {
        this.status = status;
        this.data = data;
        this.error = error;
    }
    
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ErrorResponse getError() {
        return error;
    }

    public void setError(ErrorResponse error) {
        this.error = error;
    }
    
}
