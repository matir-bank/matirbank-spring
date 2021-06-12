package xyz.matirbank.spring.models.responses.base;

import java.util.List;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import xyz.matirbank.spring.models.ReturnContainer;

public class BaseResponseEntity<T> {

    BaseResponse<T> baseResponse = new BaseResponse<>();
    HttpHeaders httpHeaders = new HttpHeaders();
    HttpStatus httpStatus = HttpStatus.OK;

    public BaseResponseEntity() {}
    
    public ResponseEntity getEntity() {
        return new ResponseEntity<>(baseResponse, httpHeaders, httpStatus);
    }

    public BaseResponseEntity basicData(T data) {
        this.baseResponse = new BaseResponse<>(200, data, null);
        return this;
    }
    
    public BaseResponseEntity basicData(ReturnContainer<T> returnContainer) {
        if(returnContainer.getStatus()){
            this.baseResponse = new BaseResponse<>(200, returnContainer.getData(), null);
        }else{
            this.baseResponse = new BaseResponse<>(200, null, returnContainer.getError());
        }
        return this;
    }

    public BaseResponseEntity basicError(int code, String summary) {
        this.baseResponse = new BaseResponse<>().basicError(code, summary);
        return this;
    }
    
    public BaseResponseEntity basicError(ErrorResponse errorResponse) {
        this.baseResponse = new BaseResponse<>().basicError(errorResponse.getCode(), errorResponse.getSummary());
        return this;
    }

    public BaseResponseEntity listErrors(int code, String summary, List<ErrorData> errors) {
        this.baseResponse = new BaseResponse<>().listErrors(code, summary, errors);
        return this;
    }

}
