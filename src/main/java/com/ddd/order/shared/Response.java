package com.ddd.order.shared;

import com.ddd.order.infrastructure.log.RequestIdMdcFilter;
import lombok.Data;
import org.slf4j.MDC;

/**
 * @author Mr.Yangxiufeng
 * @date 2020-03-19 9:40
 */
@Data
public class Response extends DTO {

    private static final long serialVersionUID = 1L;

    public static final String DEFAULT_SUCCESS_CODE = "200";

    public static final String DEFAULT_SUCCESS_MESSAGE = "操作成功";

    private boolean isSuccess;

    private String code = DEFAULT_SUCCESS_CODE;

    private String message = DEFAULT_SUCCESS_MESSAGE;

    private String requestId;

    public Response() {
        this.requestId = MDC.get(RequestIdMdcFilter.REQUEST_ID);
    }

    public static Response buildFailure(String errCode, String errMessage) {
        Response response = new Response();
        response.setSuccess(false);
        response.setCode(errCode);
        response.setMessage(errMessage);
        return response;
    }

    public static Response buildSuccess(){
        Response response = new Response();
        response.setSuccess(true);
        return response;
    }

}
