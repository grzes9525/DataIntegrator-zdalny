package mgr.pl.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComuneGios {

    private String communeName;
    private String districtName;
    private String provinceName;

}
