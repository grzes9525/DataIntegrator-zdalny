package mgr.pl.api;

import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import java.util.Collections;

@Component
public class TtnApi {

    private static final String API_URL="https://20191213_2.data.thethingsnetwork.org/api/v2/query?last=300s";

//    public static  void  main(String[] args){
//        HttpHeaders headers = new HttpHeaders();
//        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
//        headers.add("Authorization", "key ttn-account-v2.VD4KXSf6QCKmJ1153NzY0cowLWO56ZPR63o84Q3ipuE");
//
//        HttpEntity<String> entity = new HttpEntity<>("body", headers);
//        HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
//            public boolean verify(String hostname, SSLSession session) {
//                return true;
//            }
//        });
//
//        RestTemplate rest = new RestTemplate();
//        ResponseEntity<String> repsonse = rest.exchange(API_URL, HttpMethod.GET,entity ,String.class);
//    }

    private String getSensorData() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.add("Authorization", "key ttn-account-v2.VD4KXSf6QCKmJ1153NzY0cowLWO56ZPR63o84Q3ipuE");

        HttpEntity<String> entity = new HttpEntity<>("body", headers);
        HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        });

        RestTemplate rest = new RestTemplate();
        ResponseEntity<String> repsonse = rest.exchange(API_URL, HttpMethod.GET,entity ,String.class);
        return repsonse.getBody();
    }

}
