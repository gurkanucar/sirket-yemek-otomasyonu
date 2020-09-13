package com.gucarsoft.bulutmdyemek.model;


import lombok.Data;

@Data
public class ResponsePojo {
    private int responseCode;
    private String responseMessage;
    private Object responseBody;

    public ResponsePojo(Object responseBody) {
        this.responseCode = 200;
        this.responseMessage = "İşlem Başarılı";
        this.responseBody = responseBody;
    }

    public ResponsePojo(int responseCode, String responseMessage) {
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
    }
}
