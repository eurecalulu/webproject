import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.junit.Test;
import vo.PolicyInterpretationVO;
import vo.UserLoginVO;


public class Test1 {
    RestTemplate restTemplate = new RestTemplate();
    String url = "http://localhost:8002/";

    @Test
    public void getTest(){
        System.out.println(restTemplate.getForEntity(url + "user/count",Integer.class).getBody());
    }

    @Test
    public void postTest(){
        UserLoginVO request = new UserLoginVO();
        request.setName("blackman");
        request.setPassword("212121");
        System.out.println(restTemplate.postForEntity(url + "user/login", request, UserLoginVO.class).getStatusCodeValue());
    }

    @Test
    public void getByIdTest(){
        System.out.println(restTemplate.getForEntity(url + "policyInterpretation/getById?id={id}", PolicyInterpretationVO.class, 1052090).getBody());
    }


}
