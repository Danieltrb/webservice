package convertisseur.service.data;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class JsonStr {
    public static void main(String[] args) {
        //1.比如从前端接收的是这个样子的json字符串,但是我们是不能直接获取到经度 纬度所对应的值的，所以必须要对这个字符串进行解析，
        String str = "{\"code\":\"0\"," +
                            "\"error\":null," +
                            "\"msg\":\"success\"," +
                            "\"detail\":[\n" +
                             "    {\"phoneNum\":\"013012401091\",\n" +
                             "    \"lat\":34.431864,\n" +
                             "    \"lon\":109.25992,\n" +
                             "    \"height\":360,\n" +
                             "    \"speed\":33,\n" +
                             "    \"direction\":10,\n" +
                             "    \"gpsTime\":1615420800000,\n" +
                             "    \"gpsDateTime\":1615420800000,\n" +
                             "    \"mileage\":253830800,\n" +
                             "    \"alarms\":[24],\n" +
                             "    \"status\":[1,2,21],\n" +
                             "    \"property\":{}\n" +
                             "    }" +
                            " ]" +
                         " }";
        //先转换成JSONObject类型
        JSONObject jsonObj = JSON.parseObject(str);
        //通过JSONObject中的getString("key")方法，得到对应的值  {"code":"0","error":null,"msg":"success"}这种类型
        System.out.println("code："+jsonObj.getString("code"));
        
        //2.字符串中含有数组的，比如像detail中的数据
        JSONArray jsonInfo = JSONObject.parseArray(jsonObj.getString("detail"));//将jsonObj解析成json数组
        for (int i = 0; i < jsonInfo.size(); i++) {//遍历detail信息
            JSONObject jsonDetailInfo = jsonInfo.getJSONObject(i);//根据下标以此拿数据，每一个数据又是一个JSONObject对象，所以用JSONObject接收
            String lat = jsonDetailInfo.getString("lat");
            String lon = jsonDetailInfo.getString("lon");
            String gpsDateTime = jsonDetailInfo.getString("gpsDateTime");
            //然后进行其他处理
            System.out.println("lat："+lat+";lon:"+lon+";gpsDateTime："+gpsDateTime);
        }
    }
}

