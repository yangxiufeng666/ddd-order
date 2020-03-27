package com.ddd.order.shared;

/**
 * @author Mr.Yangxiufeng
 * @date 2020-03-19 9:41
 */
public class ResponseWithData<T> extends Response {

    private static final long serialVersionUID = 1L;
    private T data;

    public ResponseWithData() {
        super();
    }

    public static <T> ResponseWithData<T> of(T data) {
        ResponseWithData<T> singleResponse = new ResponseWithData<>();
        singleResponse.setSuccess(true);
        singleResponse.setData(data);
        return singleResponse;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static ResponseWithData buildFailure(String errCode, String errMessage) {
        ResponseWithData response = new ResponseWithData();
        response.setSuccess(false);
        response.setCode(errCode);
        response.setMessage(errMessage);
        return response;
    }

    public static ResponseWithData buildSuccess(){
        ResponseWithData response = new ResponseWithData();
        response.setSuccess(true);
        return response;
    }
}
