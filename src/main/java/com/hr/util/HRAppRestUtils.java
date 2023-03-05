package com.hr.util;

import com.hr.dto.HRAppRestCode;
import com.hr.dto.HRAppRestResponse;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 * Created by Anish on 02-03-2023
 *
 * @author Anish
 * @version 1.0
 * @since 02-03-2023
 */
public class HRAppRestUtils {
    public static HRAppRestResponse createResponse(HRAppRestCode hrAppRestCode, Object data, String msg) {
        HRAppRestResponse hrAppRestResponse = new HRAppRestResponse();
        hrAppRestResponse.setStatusCode(hrAppRestCode.getCode());
        hrAppRestResponse.setStatus(hrAppRestCode.name());
        if (null != data) {
            hrAppRestResponse.setData(data);
        }
        if (StringUtils.isNotBlank(msg)) {
            hrAppRestResponse.setMessage(msg);
        } else {
            hrAppRestResponse.setMessage(hrAppRestCode.name());
        }
        return hrAppRestResponse;
    }

    public static LocalDate convertToLocalDate(Date dateToConvert) {
        return LocalDate.ofInstant(dateToConvert.toInstant(), ZoneId.systemDefault());
    }
}
