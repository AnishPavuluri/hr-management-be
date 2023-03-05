package com.hr.dto;

/**
 * Created by Anish on 02-03-2023
 *
 * @author Anish
 * @version 1.0
 * @since 02-03-2023
 */
public enum HRAppRestCode {
    SUCCESS(200),
    FAILED(500);

    int code;

    HRAppRestCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
