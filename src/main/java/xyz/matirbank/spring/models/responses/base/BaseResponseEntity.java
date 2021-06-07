package xyz.matirbank.spring.models.responses.base;

import java.util.List;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

public class BaseResponseEntity<T> {

    BaseResponse<T> baseResponse = new BaseResponse<>();
    HttpHeaders httpHeaders = new HttpHeaders();
    HttpStatus httpStatus = HttpStatus.OK;

    public BaseResponseEntity() {}

    public BaseResponseEntity basicData(T data) {
        this.baseResponse = new BaseResponse<>(200, data, null);
        return this;
    }

    public BaseResponseEntity basicError(int code, String summary) {
        this.baseResponse = new BaseResponse<>().basicError(code, summary);
        return this;
    }

    public BaseResponseEntity listErrors(int code, String summary, List<ErrorData> errors) {
        this.baseResponse = new BaseResponse<>().listErrors(code, summary, errors);
        return this;
    }

}
