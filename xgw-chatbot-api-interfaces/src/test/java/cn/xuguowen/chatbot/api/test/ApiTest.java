package cn.xuguowen.chatbot.api.test;

import org.apache.http.Consts;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * ClassName: ApiTest
 * Package: cn.xuguowen.chatbot.api.test
 * Description: 单元测试
 *
 * @Author 徐国文
 * @Create 2024/4/18 12:22
 * @Version 1.0
 */
public class ApiTest {

    @Test
    public void query_unanswered_question() throws IOException {
        // 1.创建HttpClient对象
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        // 2.创建一个GET请求
        HttpGet get = new HttpGet("https://api.zsxq.com/v2/groups/28885518425541/topics?scope=all&count=20");

        // 3.准备请求头信息和请求参数信息
        get.addHeader("Cookie", "zsxq_access_token=D66904DE-EA80-F09A-1F1A-77030442C7D1_FFE2169EED2BE37A; abtest_env=product; zsxqsessionid=df06372401f63f903e6dd6860a146f6b; sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%2218d353cc9311c5-02ef12e489f020a-26001951-1327104-18d353cc932ccd%22%2C%22first_id%22%3A%22%22%2C%22props%22%3A%7B%22%24latest_traffic_source_type%22%3A%22%E5%BC%95%E8%8D%90%E6%B5%81%E9%87%8F%22%2C%22%24latest_search_keyword%22%3A%22%E6%9C%AA%E5%8F%96%E5%88%B0%E5%80%BC%22%2C%22%24latest_referrer%22%3A%22https%3A%2F%2Fbugstack.cn%2F%22%7D%2C%22identities%22%3A%22eyIkaWRlbnRpdHlfY29va2llX2lkIjoiMThkMzUzY2M5MzExYzUtMDJlZjEyZTQ4OWYwMjBhLTI2MDAxOTUxLTEzMjcxMDQtMThkMzUzY2M5MzJjY2QifQ%3D%3D%22%2C%22history_login_id%22%3A%7B%22name%22%3A%22%22%2C%22value%22%3A%22%22%7D%2C%22%24device_id%22%3A%2218d353cc9311c5-02ef12e489f020a-26001951-1327104-18d353cc932ccd%22%7D");
        get.addHeader("Content-Type", "application/json;charset=utf8");

        // 4.执行get请求
        CloseableHttpResponse response = httpClient.execute(get);

        // 5.获取响应结果
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String respStr = EntityUtils.toString(response.getEntity());
            System.out.println(respStr);
        } else {
            System.out.println(response.getStatusLine().getStatusCode());
        }
    }


    @Test
    public void answer() throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpPost post = new HttpPost("https://api.zsxq.com/v2/topics/2855288854185221/comments");
        post.addHeader("cookie", "zsxq_access_token=D66904DE-EA80-F09A-1F1A-77030442C7D1_FFE2169EED2BE37A; abtest_env=product; zsxqsessionid=df06372401f63f903e6dd6860a146f6b; sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%2218d353cc9311c5-02ef12e489f020a-26001951-1327104-18d353cc932ccd%22%2C%22first_id%22%3A%22%22%2C%22props%22%3A%7B%22%24latest_traffic_source_type%22%3A%22%E5%BC%95%E8%8D%90%E6%B5%81%E9%87%8F%22%2C%22%24latest_search_keyword%22%3A%22%E6%9C%AA%E5%8F%96%E5%88%B0%E5%80%BC%22%2C%22%24latest_referrer%22%3A%22https%3A%2F%2Fbugstack.cn%2F%22%7D%2C%22identities%22%3A%22eyIkaWRlbnRpdHlfY29va2llX2lkIjoiMThkMzUzY2M5MzExYzUtMDJlZjEyZTQ4OWYwMjBhLTI2MDAxOTUxLTEzMjcxMDQtMThkMzUzY2M5MzJjY2QifQ%3D%3D%22%2C%22history_login_id%22%3A%7B%22name%22%3A%22%22%2C%22value%22%3A%22%22%7D%2C%22%24device_id%22%3A%2218d353cc9311c5-02ef12e489f020a-26001951-1327104-18d353cc932ccd%22%7D");
        post.addHeader("Content-Type", "application/json;charset=utf8");

        String paramJson = "{\n" +
                "    \"req_data\": {\n" +
                "        \"text\": \"自己去百度吧\\n\",\n" +
                "        \"image_ids\": [],\n" +
                "        \"replied_comment_id\": 8855288484811122,\n" +
                "        \"mentioned_user_ids\": []\n" +
                "    }\n" +
                "}";

        StringEntity stringEntity = new StringEntity(paramJson, ContentType.create("text/json", "UTF-8"));
        post.setEntity(stringEntity);

        CloseableHttpResponse response = httpClient.execute(post);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String res = EntityUtils.toString(response.getEntity());
            System.out.println(res);
        } else {
            System.out.println(response.getStatusLine().getStatusCode());
        }
    }
}
