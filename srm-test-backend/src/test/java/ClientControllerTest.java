import com.srm.test.entity.Client;
import com.srm.test.entity.RiskEnum;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class ClientControllerTest extends BaseTest {

    private Client client;

    @Test
    public void testGetAllClient(){
        testAddClient();
        HttpEntity<Object> requestEntity = new HttpEntity<>(getXSRF());

        ResponseEntity<List<Client>> responseEntity = restTemplate
                .exchange(
                        createURLWithPort("/api/client/all"),
                        HttpMethod.GET,
                        requestEntity,
                        new ParameterizedTypeReference<List<Client>>(){});

        Assert.assertNotNull(responseEntity);
        Assert.assertNotNull(responseEntity.getStatusCode());
        Assert.assertNotNull(responseEntity.getBody());

        List<Client> clients = responseEntity.getBody();
        Assert.assertNotNull(clients);
        Assert.assertTrue(clients.size() > 0);

        Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void testAddClient(){

        client = new Client("Test", 10000F, RiskEnum.A);
        HttpEntity<Client> requestEntity = new HttpEntity<>(client, getXSRF());

        ResponseEntity<Client> responseEntity = restTemplate
                .exchange(
                        createURLWithPort("/api/client/add"),
                        HttpMethod.POST,
                        requestEntity,
                        Client.class);

        Assert.assertNotNull(responseEntity);
        Assert.assertNotNull(responseEntity.getStatusCode());
        Assert.assertNotNull(responseEntity.getBody());

        client = responseEntity.getBody();

        Assert.assertNotNull(client);
        Assert.assertNotNull(client.getId());
        Assert.assertTrue(client.getId() > 0);
        Assert.assertEquals("Test", client.getName());
        Assert.assertTrue(10000F == client.getCreditLimit());
        Assert.assertEquals(RiskEnum.A, client.getRisk());
        Assert.assertNotNull(client.getTax());

        Assert.assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }

    @Test
    public void testEditClient(){
        testAddClient();
        client.setRisk(RiskEnum.B);

        HttpEntity<Client> requestEntity = new HttpEntity<>(client, getXSRF());

        ResponseEntity<Client> responseEntity = restTemplate
                .exchange(
                        createURLWithPort("/api/client/update"),
                        HttpMethod.PUT,
                        requestEntity,
                        Client.class);

        Assert.assertNotNull(responseEntity);
        Assert.assertNotNull(responseEntity.getStatusCode());
        Assert.assertNotNull(responseEntity.getBody());

        client = responseEntity.getBody();

        Assert.assertNotNull(client);
        Assert.assertNotNull(client.getId());
        Assert.assertTrue(client.getId() > 0);
        Assert.assertEquals("Test", client.getName());
        Assert.assertTrue(10000F == client.getCreditLimit());
        Assert.assertEquals(RiskEnum.B, client.getRisk());
        Assert.assertNotNull(client.getTax());

        Assert.assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }

    @Test
    public void testGetClient(){
        testAddClient();
        HttpEntity<Object> requestEntity = new HttpEntity<>(getXSRF());

        ResponseEntity<Client> responseEntity = restTemplate
                .exchange(
                        createURLWithPort(String.format("/api/client/%s", client.getId())),
                        HttpMethod.GET,
                        requestEntity,
                        Client.class);

        Assert.assertNotNull(responseEntity);
        Assert.assertNotNull(responseEntity.getStatusCode());
        Assert.assertNotNull(responseEntity.getBody());

        client = responseEntity.getBody();
        Assert.assertNotNull(client);

        Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void testDeleteClient(){
        testAddClient();
        HttpEntity<Object> requestEntity = new HttpEntity<>(getXSRF());

        ResponseEntity<Object> responseEntity = restTemplate
                .exchange(
                        createURLWithPort(String.format("/api/client/%s", client.getId())),
                        HttpMethod.DELETE,
                        requestEntity,
                        Object.class);

        Assert.assertNotNull(responseEntity);
        Assert.assertNotNull(responseEntity.getStatusCode());
        Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        ResponseEntity<Client> secondResponseEntity = restTemplate
                .exchange(
                        createURLWithPort(String.format("/api/client/%s", client.getId())),
                        HttpMethod.GET,
                        requestEntity,
                        Client.class);

        Assert.assertNotNull(secondResponseEntity);
        Assert.assertNotNull(secondResponseEntity.getStatusCode());
        Assert.assertNull(responseEntity.getBody());

        Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void testDeleteAllClient(){
        testAddClient();
        HttpEntity<Object> requestEntity = new HttpEntity<>(getXSRF());

        ResponseEntity<Object> responseEntity = restTemplate
                .exchange(
                        createURLWithPort("/api/client/all"),
                        HttpMethod.DELETE,
                        requestEntity,
                        Object.class);

        Assert.assertNotNull(responseEntity);
        Assert.assertNotNull(responseEntity.getStatusCode());
        Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        ResponseEntity<List<Client>> secondResponseEntity = restTemplate
                .exchange(
                        createURLWithPort("/api/client/all"),
                        HttpMethod.GET,
                        requestEntity,
                        new ParameterizedTypeReference<List<Client>>(){});

        Assert.assertNotNull(secondResponseEntity);
        Assert.assertNotNull(secondResponseEntity.getStatusCode());
        Assert.assertNull(responseEntity.getBody());

        Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void testTaxClient(){

        client = new Client("Test", 10000F, RiskEnum.A);
        HttpEntity<Client> requestEntity = new HttpEntity<>(client, getXSRF());

        ResponseEntity<Client> responseEntity = restTemplate
                .exchange(
                        createURLWithPort("/api/client/add"),
                        HttpMethod.POST,
                        requestEntity,
                        Client.class);

        Assert.assertNotNull(responseEntity);
        Assert.assertNotNull(responseEntity.getStatusCode());
        Assert.assertNotNull(responseEntity.getBody());

        client = responseEntity.getBody();

        Assert.assertNotNull(client);
        Assert.assertNotNull(client.getId());
        Assert.assertTrue(client.getId() > 0);
        Assert.assertEquals("Test", client.getName());
        Assert.assertTrue(10000F == client.getCreditLimit());
        Assert.assertEquals(RiskEnum.A, client.getRisk());
        Assert.assertNotNull(client.getTax());
        Assert.assertTrue(client.getTax() == 0F);

        Assert.assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());

        client.setRisk(RiskEnum.B);
        requestEntity = new HttpEntity<>(client, getXSRF());
        responseEntity = restTemplate
                        .exchange(
                                createURLWithPort("/api/client/update"),
                                HttpMethod.PUT,
                                requestEntity,
                                Client.class);

        Assert.assertNotNull(responseEntity);
        Assert.assertNotNull(responseEntity.getStatusCode());
        Assert.assertNotNull(responseEntity.getBody());

        client = responseEntity.getBody();

        Assert.assertNotNull(client);
        Assert.assertNotNull(client.getId());
        Assert.assertTrue(client.getId() > 0);
        Assert.assertEquals("Test", client.getName());
        Assert.assertTrue(10000F == client.getCreditLimit());
        Assert.assertEquals(RiskEnum.B, client.getRisk());
        Assert.assertNotNull(client.getTax());
        Assert.assertTrue(client.getTax() == 10F);

        Assert.assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());

        client.setRisk(RiskEnum.C);
        requestEntity = new HttpEntity<>(client, getXSRF());
        responseEntity = restTemplate
                .exchange(
                        createURLWithPort("/api/client/update"),
                        HttpMethod.PUT,
                        requestEntity,
                        Client.class);

        Assert.assertNotNull(responseEntity);
        Assert.assertNotNull(responseEntity.getStatusCode());
        Assert.assertNotNull(responseEntity.getBody());

        client = responseEntity.getBody();

        Assert.assertNotNull(client);
        Assert.assertNotNull(client.getId());
        Assert.assertTrue(client.getId() > 0);
        Assert.assertEquals("Test", client.getName());
        Assert.assertTrue(10000F == client.getCreditLimit());
        Assert.assertEquals(RiskEnum.C, client.getRisk());
        Assert.assertNotNull(client.getTax());
        Assert.assertTrue(client.getTax() == 20F);

        Assert.assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }
}
