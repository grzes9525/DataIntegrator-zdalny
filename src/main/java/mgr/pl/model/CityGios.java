package mgr.pl.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CityGios {

    private String id;
    private String name;
    private ComuneGios commune;
}
