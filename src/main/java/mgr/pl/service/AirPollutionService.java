package mgr.pl.service;

import mgr.pl.api.AirlyApi;
import mgr.pl.api.TtnApi;
import mgr.pl.model.AirPollution;
import mgr.pl.repo.AirPollutionRepository;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class AirPollutionService {

    @Autowired
    private TtnApi ttnApi;

    @Autowired
    private AirlyApi airlyApi;

    @Autowired
    private EmailSender emailSender;

    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private AirPollutionRepository airPollutionRepository;

    //co 2h
    public void sendEmail(){
        //spr isTtnWorking
        if(!isTtnWorking()){
            Context context = new Context();
            context.setVariable("header", "TTN nie ma danych");
            context.setVariable("title", "TTN nie ma danych");
            context.setVariable("description", "Od 2 godzin nie ma nowych danych w TTN");
            String body = templateEngine.process("template", context);
            emailSender.sendEmail("jakubiak.grzegorz95@gmail.com", "TTN nie ma danych", body);
        }
        //Dodaj crona co 2h
    }

    private boolean isTtnWorking() {
        //sprawdz sysdate
//        Date date = DateUtils.addHours(new Date(),-2);
//        //pobierz dane z bazy z ostatnich 2h
//        if(airPollutionRepository.findAllByMeasureDateGreaterThanOrderByMeasureDateDesc(date).isPresent()){
//            return true;
//        }else {
//            //jeśli są to zwróć true
//            return false;
//        }
        return true;
    }

    //co 300s
    private void createAirPollutionData(){
        //Pobierz dane z TTN z 300s
        //wywal skrajne przypadki większe  2 razy niż w okolicy
        //Wylicz średnią z 300s
        //nada ttnID wartość z TTNu
        //Poberz pogodę z oklicy czujnika co 300s
        //połacz dane z TTN i pogodę (stwórz obiekt)

    }

    private boolean isNewDataUnique(AirPollution airPollution){
        //pobierz z bazy ostatnich 10 min
        Date date = new Date(new Date().getTime()-10*1000);
        List<AirPollution> airPollutions = airPollutionRepository.findAllByMeasureDateGreaterThanOrderByMeasureDateDesc(date);
        airPollutions = airPollutions == null ? Collections.emptyList() : airPollutions;
        //spr czy nowy obiekt już jest w bazie, po ttnID
        for(AirPollution pollution : airPollutions){

        }
        //zwróc true lub false
        return true;
    }

    @Transactional
    void saveAirPollution(){
        //spr metodą isNewDataUnique
        //jeśli nie ma to zapisz
    }


}
