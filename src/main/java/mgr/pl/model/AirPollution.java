package mgr.pl.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Zanieczyszczenie_powietrza")
public class AirPollution {

    @Id
    @Column(name="ID_pomiaru",nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column(name="ID_TTN",nullable = false)
    String ttnID;

    @Column(name="Data_pomiaru",nullable = false)
    Date measureDate;

    @Column(name="PM10_wartość")
    Integer PM10_value;

    @Column(name="PM2_5_wartość")
    Integer PM25_value;

    @Column(name="Temperatura",nullable = false)
    Integer temperature;

    @Column(name="Wilgotność")
    Integer humadity;

    @Column(name="Siła_wiatru")
    Integer wind;

    @Column(name="Nazwa_czujnika")
    String sensorName;

    @Column(name="Szerokość_geogr_zzujnika")
    String latSensor;

    @Column(name="Długość_geogr_czujnika")
    String lonSensor;

}
