package top.ahcdc.periodical.common.lang;

import lombok.Getter;

@Getter
public enum ResponseCode {
    SUCCESS(200,"SUCCESS"),
    ERROR(400,"ERROR"),
    NEED_LOGIN(401,"NEED_LOGIN"),
    ILLEGAL_ARGUMENT(402,"ILLEGAL_ARGUMENT");

    private final int code;
    private final String description;

    ResponseCode(int code,String description){
        this.code=code;
        this.description = description;
    }

}
