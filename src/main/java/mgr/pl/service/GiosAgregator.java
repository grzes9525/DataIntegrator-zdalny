package mgr.pl.service;

import mgr.pl.api.AirlyApi;
import mgr.pl.api.GiosApi;
import mgr.pl.model.GiosSensorData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class GiosAgregator {

    @Autowired
    private GiosApi giosApi;

    @Autowired
    private AirlyApi airlyApi;

    public void createContentFile(){
        List<GiosSensorData> giosSensors = giosApi.getAllGiosSensor();
        for(GiosSensorData giosSensorData : giosSensors){
            List<String> ids = airlyApi.getNearestSensorByLatLon(giosSensorData.getGegrLat(),giosSensorData.getGegrLon());
            try {
                Thread.sleep(150 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //https://airapi.airly.eu/v2/installations/nearest?lat=49.546539&lng=21.851006&maxDistanceKM=10&maxResults=6
            //2053
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-ddHH-mm-ss-SSS");

            for(String id:ids){
                saveFile("/mgr/webapp/files/"+id+"_"+format.format( new Date()),airlyApi.getResposneHisotrySensorDataById(id));
                try {
                    Thread.sleep(150 * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //https://airapi.airly.eu/v2/measurements/installation?installationId=2053
            //zapisz do pliku o nazwie id_data

        }
    }

    private void saveFile(String path, String content){

        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            writer.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
