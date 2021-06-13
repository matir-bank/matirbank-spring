package xyz.matirbank.spring.models;

import xyz.matirbank.spring.models.responses.base.ErrorResponse;

/**
 * This is a method return container which helps return Data or ErrorResponse in
 * case of an error
 *
 * @author Arman Hossain
 * @param <T>
 */
public class ReturnContainer<T> {

    Boolean status;
    T data;
    ErrorResponse error;

    public ReturnContainer() {
    }

    public ReturnContainer(Boolean status, T data, ErrorResponse error) {
        this.status = status;
        this.data = data;
        this.error = error;
    }

    public ReturnContainer<T> returnData(T data) {
        this.status = true;
        this.data = data;
        this.error = null;

        return this;
    }

    public ReturnContainer<T> returnErrorSummary(ErrorResponse errorResponse) {
        return returnErrorSummary(errorResponse.getCode(), errorResponse.getSummary());
    }

    public ReturnContainer<T> returnErrorSummary(int errorCode, String errorSummary) {
        // Create Error Response
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setCode(errorCode);
        errorResponse.setSummary(errorSummary);

        // Pack
        this.status = false;
        this.data = null;
        this.error = errorResponse;

        return this;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
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
