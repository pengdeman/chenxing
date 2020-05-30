package com.cx.chenxing.utils.zj;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;

public class JsonUtil {

    public static <T> T parseJson(String json, Class<T> clazz) {
        return JSON.parseObject(json, clazz);
    }

    /**
     * @param json
     * @param type 例 new TypeReference<Map<Integer, Map<BigDecimal,
     *             UmFullstep>>>() {}
     * @return
     */
    public static <T> T parseJson(String json, TypeReference<T> type) {
        return JSON.parseObject(json, type);
    }

    public static String toJson(Object object) {
        return JSON.toJSONString(object, SerializerFeature.WriteDateUseDateFormat);
    }
    public static String toJson(Object object,SimplePropertyPreFilter filter) {
        return JSON.toJSONString(object,filter);
    }

}
