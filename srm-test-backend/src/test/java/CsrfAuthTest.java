import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class CsrfAuthTest extends BaseTest {

    @Test
    public void testGetToken(){
        ResponseEntity<Object> responseEntity = restTemplate
                .withBasicAuth("user","pa$$word")
                .exchange(createURLWithPort("/user"), HttpMethod.GET, null, Object.class);

        Assert.assertNotNull(responseEntity.getHeaders());
        Assert.assertNotNull(responseEntity.getHeaders().get("Set-Cookie"));
        Assert.assertTrue(responseEntity.getHeaders().get("Set-Cookie").size() > 0);

        List<String> cookies = responseEntity.getHeaders().get("Set-Cookie");
        String token = cookies.stream().filter(s -> s.contains("XSRF-TOKEN")).findFirst().orElse(null);
        Assert.assertNotNull(token);
    }

    @Test
    public void tesCallWithBasicAuth(){
        ResponseEntity<Object> responseEntity = restTemplate
                .withBasicAuth("user","pa$$word")
                .exchange(createURLWithPort("/user"), HttpMethod.GET, null, Object.class);

        Assert.assertNotNull(responseEntity);
        Assert.assertNotNull(responseEntity.getStatusCode());
        Assert.assertNotNull(responseEntity.getBody());
        Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

}
