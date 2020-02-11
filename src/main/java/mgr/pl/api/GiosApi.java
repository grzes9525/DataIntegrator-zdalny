package mgr.pl.api;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import mgr.pl.model.GiosSensorData;
import mgr.pl.model.ListSensorGios;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import java.util.Collections;
import java.util.List;

@Component
public class GiosApi {

    private static final String FIND_ALL_SENSOR_URL="http://api.gios.gov.pl/pjp-api/rest/station/findAll";

    public List<GiosSensorData> getAllGiosSensor() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<String> entity = new HttpEntity<>("body", headers);
        HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        });

        RestTemplate rest = new RestTemplate();
        ResponseEntity<String> repsonse = rest.exchange(FIND_ALL_SENSOR_URL, HttpMethod.GET,entity ,String.class);
        String json = "";
        if(repsonse!=null){
            json = repsonse.getBody();
        }

        //.replaceAll("\\[","{").replaceAll("\\]","}");
        String finallJson = "{\n" +
                "  \"listSensor\": "+json+" }";
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        ListSensorGios sensorsGios=null;
        try {
            sensorsGios =  objectMapper.readValue(finallJson,ListSensorGios.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

//        for(GiosSensorData giosSensorData : sensorsGios.getListSensor()){
//            //https://airapi.airly.eu/v2/installations/nearest?lat=49.546539&lng=21.851006&maxDistanceKM=10&maxResults=6
//            //2053
//            //https://airapi.airly.eu/v2/measurements/installation?installationId=2053
//            //zapisz do pliku o nazwie id_data
//
//        }
        //System.out.println(finallJson);
        return sensorsGios.getListSensor();
    }

    public static void main(String[] args){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<String> entity = new HttpEntity<>("body", headers);
        HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        });

        RestTemplate rest = new RestTemplate();
        ResponseEntity<String> repsonse = rest.exchange(FIND_ALL_SENSOR_URL, HttpMethod.GET,entity ,String.class);
        String json = repsonse.getBody();
                //.replaceAll("\\[","{").replaceAll("\\]","}");
        String finallJson = "{\n" +
                "  \"listSensor\": "+json+" }";
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        ListSensorGios sensorsGios=null;
        try {
            sensorsGios =  objectMapper.readValue(finallJson,ListSensorGios.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        for(GiosSensorData giosSensorData : sensorsGios.getListSensor()){
            //https://airapi.airly.eu/v2/installations/nearest?lat=49.546539&lng=21.851006&maxDistanceKM=10&maxResults=6
            //2053
            //https://airapi.airly.eu/v2/measurements/installation?installationId=2053
            //zapisz do pliku o nazwie id_data

        }
        System.out.println(finallJson);
    }


}
