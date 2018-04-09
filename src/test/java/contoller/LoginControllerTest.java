/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contoller;

import com.xnet.wms.dto.UserDTO;
import com.xnet.wms.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/*
 * @author ramy
 */
public class LoginControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;


    @Test
    public void loginTest(){

//        MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
//        map.add("username","admin");
//        map.add("password","admin");
//
//        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map,null);
//
//        ResponseEntity<Map<String, Object>> response = this.restTemplate.postForObject("/authenticate",request, ResponseEntity.class);
//    String username= ((UserDTO)(( Map<String, Object>)response).get("user")).getUsername();
//        assertThat(response).isEqualTo("Admin");

    }
}
