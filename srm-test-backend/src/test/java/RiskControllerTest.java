import com.srm.test.entity.Risk;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class RiskControllerTest extends BaseTest {

    @Test
    public void testGetAllClient(){
        HttpEntity<Object> requestEntity = new HttpEntity<>(getXSRF());

        ResponseEntity<List<Risk>> responseEntity = restTemplate
                .exchange(
                        createURLWithPort("/api/risk/all"),
                        HttpMethod.GET,
                        requestEntity,
                        new ParameterizedTypeReference<List<Risk>>(){});

        Assert.assertNotNull(responseEntity);
        Assert.assertNotNull(responseEntity.getStatusCode());
        Assert.assertNotNull(responseEntity.getBody());

        List<Risk> risks = responseEntity.getBody();
        Assert.assertNotNull(risks);
        Assert.assertTrue(risks.size() > 0);

        Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
}
