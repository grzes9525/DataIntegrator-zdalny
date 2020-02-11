package mgr.pl.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GiosSensorData {

    private String id;
    private String stationName;
    private String gegrLat;
    private String gegrLon;
    private CityGios city;
    private String addressStreet;
}
