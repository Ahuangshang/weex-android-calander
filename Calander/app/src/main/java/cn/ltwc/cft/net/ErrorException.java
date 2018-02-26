package cn.ltwc.cft.net;

/**
 * ErrorException
 * Created by LZL on 2017/1/23.
 */

public class ErrorException extends RuntimeException {
    private static final int CODE_400 = 400;
    private static final int CODE_401 = 401;
    private static final int CODE_402 = 402;
    private static final int CODE_403 = 403;
    private static final int CODE_404 = 404;
    private static final int CODE_407 = 407;
    private static final int CODE_415 = 415;
    private static final int CODE_500 = 500;
    private static final int CODE_501 = 501;
    private static final int CODE_502 = 502;
    private static final int CODE_503 = 503;


    public ErrorException(String e) {
        super(e);

    }

    public ErrorException(int code) {
        this(getAPIExceptionMessage(code));
    }

    /**
     * 由于服务器传递过来的错误信息直接给用户看的话，用户未必能够理解
     * 需要根据错误码对错误信息进行一个转换，在显示给用户
     *
     * @param code
     * @return
     */
    private static String getAPIExceptionMessage(int code) {
        switch (code) {
            case CODE_400:
                return "请求中有语法问题，或不能满足请求";
            case CODE_401:
                return "客户机访问数据未授权";
            case CODE_402:
                return "需要付款";
            case CODE_403:
                return "禁止访问";
            case CODE_404:
                return "服务器找不到给定的资源";
            case CODE_407:
                return "客户机首先必须使用代理认证自身";
            case CODE_415:
                return "服务器拒绝服务请求，因为不支持请求实体的格式";
            case CODE_500:
                return "服务器内部错误";
            case CODE_501:
                return "服务器不支持请求的工具";
            case CODE_502:
                return "错误网关";
            case CODE_503:
                return "由于临时过载或维护，服务器无法处理请求";
            default:
                return "请求异常，请稍后重试";
        }
    }
}
