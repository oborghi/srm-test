import com.srm.test.Application;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class BaseTest {

    final protected TestRestTemplate restTemplate = new TestRestTemplate();

    @LocalServerPort
    private int port;

    protected String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }

    protected HttpHeaders getXSRF() {
        HttpHeaders httpHeaders = new HttpHeaders();
        try {
            HttpEntity<String> responseEntity = restTemplate
                    .withBasicAuth("user","pa$$word")
                    .exchange(createURLWithPort("/user"), HttpMethod.GET, null, String.class);
            List<String> cookieHeader = responseEntity.getHeaders().get("Set-Cookie");
            httpHeaders = new HttpHeaders();
            httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

            if (cookieHeader != null) {
                for (String cookie : cookieHeader) {
                    String[] tokens = cookie.split("=");
                    if (tokens[0].equals("XSRF-TOKEN")) {
                        String cookieToken = cookie.split(";")[0];
                        httpHeaders.add("Cookie", cookieToken);
                        String tokenValue = cookieToken.split("=")[1];
                        httpHeaders.add("X-XSRF-TOKEN", tokenValue);
                    }
                    if (tokens[0].equals("JSESSIONID")) {
                        httpHeaders.add("Cookie", cookie.split(";")[0]);
                    }
                }
            }
        } finally {
            return httpHeaders;
        }
    }
}
