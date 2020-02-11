package mgr.pl.api;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import mgr.pl.model.AirluPollutionData;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class AirlyApi {

    private static final String API_URL="https://airapi.airly.eu/v2/meta/indexes";

    public String getResposneHisotrySensorDataById(String id){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.add("apikey", "HL8C303EqjOo5Hh7Mmd7m8DrXFTsDQBa");

        HttpEntity<String> entity = new HttpEntity<>("body", headers);
        HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        });

        RestTemplate rest = new RestTemplate();
        String url = "https://airapi.airly.eu/v2/measurements/installation?installationId="+id;

        ResponseEntity<String> repsonse = rest.exchange(url, HttpMethod.GET,entity ,String.class);
        AirluPollutionData airluPollutionData = new AirluPollutionData();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        if(repsonse!=null){
            try {
                airluPollutionData = objectMapper.readValue(repsonse.getBody(),AirluPollutionData.class);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }

        return repsonse.getBody();
    }

   public AirluPollutionData getHisotrySensorDataById(String id){
       HttpHeaders headers = new HttpHeaders();
       headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
       headers.add("apikey", "HL8C303EqjOo5Hh7Mmd7m8DrXFTsDQBa");

       HttpEntity<String> entity = new HttpEntity<>("body", headers);
       HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
           public boolean verify(String hostname, SSLSession session) {
               return true;
           }
       });

       RestTemplate rest = new RestTemplate();
       String url = "https://airapi.airly.eu/v2/measurements/installation?installationId="+id;

       ResponseEntity<String> repsonse = rest.exchange(url, HttpMethod.GET,entity ,String.class);
       AirluPollutionData airluPollutionData = new AirluPollutionData();
       ObjectMapper objectMapper = new ObjectMapper();
       objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
       if(repsonse!=null){
           try {
               airluPollutionData = objectMapper.readValue(repsonse.getBody(),AirluPollutionData.class);
           } catch (JsonProcessingException e) {
               e.printStackTrace();
           }
       }

       return airluPollutionData;
   }

    public List<String> getNearestSensorByLatLon(String lat, String lon) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.add("apikey", "HL8C303EqjOo5Hh7Mmd7m8DrXFTsDQBa");

        HttpEntity<String> entity = new HttpEntity<>("body", headers);
        HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        });

        RestTemplate rest = new RestTemplate();
        String url = "https://airapi.airly.eu/v2/installations/nearest?lat="+lat+"&lng="+lon+"&maxDistanceKM=10&maxResults=6";

        ResponseEntity<String> repsonse = rest.exchange(url, HttpMethod.GET,entity ,String.class);
        List<String> ids = new ArrayList<>();
        if(repsonse!=null){
            ids = getValuesForGivenKey(repsonse.getBody(),"id");
        }

        return ids;
    }

    private List<String> getValuesForGivenKey(String jsonArrayStr, String key) {
        JSONArray jsonArray = new JSONArray(jsonArrayStr);
        return IntStream.range(0, jsonArray.length())
                .mapToObj(index -> ((JSONObject)jsonArray.get(index)).optString(key))
                .collect(Collectors.toList());
    }


}
