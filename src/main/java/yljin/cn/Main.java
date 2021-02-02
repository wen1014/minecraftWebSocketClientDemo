package yljin.cn;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        String demo = "{\"banjiahuafei\":[\"123\"]}";
        JSONObject jsonObject = JSONObject.parseObject(demo);
        String ccc = JSONArray.parseArray(jsonObject.getString("banjiahuafei")).getString(0);
        System.out.println(ccc);
    }

    public static <T> List<T> parseJsonArray(String jsonStr, Class<T> classOfT){
        if(StringUtils.isBlank(jsonStr)){
            return null;
        }
        return JSON.parseArray(jsonStr, classOfT);
    }
}
