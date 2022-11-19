package convertisseur.service.data;


import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import convertisseur.service.data.HttpUtils;

import java.util.HashMap;

import java.util.Map;


public class Transform {

	public static float Transform(String fromCode, String toCode, int number) {
		String host = "https://ali-waihui.showapi.com";
	    String path = "/waihui-transform";
	    String method = "GET";
	    String appcode = "c5e22c4398a84d73a49c33bb99d31d71";
	    Map<String, String> headers = new HashMap<String, String>();
	    //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
	    headers.put("Authorization", "APPCODE " + appcode);
	    Map<String, String> querys = new HashMap<String, String>();
	    querys.put("fromCode", fromCode );
	    querys.put("money", Integer.toString(number));
	    querys.put("toCode", toCode);
	    try {
	    	/**
	    	* 重要提示如下:
	    	* HttpUtils请从
	    	* https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
	    	* 下载
	    	*
	    	* 相应的依赖请参照
	    	* https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
	    	*/
	    	HttpResponse response = HttpUtils.doGet(host, path, method, headers, querys);
//	    	System.out.println(response.toString());
	    	//获取response的body
	//    	System.out.println(EntityUtils.toString(response.getEntity()));
	        JSONObject jsonObj = JSON.parseObject(EntityUtils.toString(response.getEntity()));
	        //通过JSONObject中的getString("key")方法，得到对应的值  {"code":"0","error":null,"msg":"success"}这种类型
//	        System.out.println("showapi_res_body："+jsonObj.getString("showapi_res_body"));
	        JSONObject jsonObj2 = JSON.parseObject(jsonObj.getString("showapi_res_body"));
//	        System.out.println("money："+jsonObj2.getString("money"));
	
	        return Float.parseFloat(jsonObj2.getString("money"));
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
	    return 0;
	}
}