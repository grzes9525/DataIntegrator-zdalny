package mgr.pl.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AirluPollutionData {

    private List<HistoryMeasurement> historyMeasurementList;
}
