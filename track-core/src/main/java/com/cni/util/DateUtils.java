package com.cni.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.text.SimpleDateFormat;

/**
 * Created by CNI on 2018/2/1.
 */
public class DateUtils {

    private static final Log LOG = LogFactory.getLog(DateUtils.class);

    private DateUtils() {
    }

    public static Long parse(SimpleDateFormat sdf, String strDate) {
        try {
            return sdf.parse(strDate).toInstant().toEpochMilli();
        } catch (Exception e) {
            LOG.debug("日期解析失败:" + sdf.toPattern() + "-->" + strDate);
            return null;
        }
    }
}
