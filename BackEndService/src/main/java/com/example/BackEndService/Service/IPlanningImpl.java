package com.example.BackEndService.Service;

import com.example.BackEndService.dao.PlanningRepository;
import com.example.BackEndService.model.Appointment;
import com.example.BackEndService.model.Planning;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Service
public class IPlanningImpl implements IPlanningService{
    @Autowired
    PlanningRepository planningRepository;
    @Autowired
    IAppointmentService appointmentService;
    @Override
    public List<Planning> getAllPlanning() {
        List<Planning> plannings = planningRepository.findAll();
        if(plannings !=null){
            return  plannings;
        }
        return null;
    }
    @Override
    public List<Planning> getPlanningsByIdServiceProvider(Long id) {
        List<Planning> plannings = planningRepository.findAll();
        List<Planning> planningList = new ArrayList<>();
        for (Planning planning : plannings){
            if(planning.getServiceProvider().getUserId()==id){
                planningList.add(planning);
            }
        }
        return planningList;

    }
    @Override
    public Planning save(Planning planning) {
        List<Planning> plannings = getPlanningsByIdServiceProvider(planning.getServiceProvider().getUserId());
        for(Planning planning1 : plannings){


        if((planning.getDateDebut().after(planning1.getDateDebut())|| planning.getDateDebut().before(planning1.getDateFin())) ||(planning.getDateFin().after(planning1.getDateDebut())|| planning.getDateDebut().before(planning1.getDateFin())) ){

            System.out.println("you have already an appointment");
            return planning1;
        }
        }
          planningRepository.save(planning);
        return planning;
    }

    //calcul days between date
    public long daysBetween(Date one,Date two){
        long difference = (one.getTime()-two.getTime())/86400000;
        return Math.abs(difference);
    }
    //check days from a list of appointements
    public boolean CheckDaysForPlanning(List<Appointment> appointments,Date debut){
        for(Appointment app : appointments){
            if(daysBetween(app.getAppointmentDate(),debut)<=2){
                return false;
            }
        }
        return true;
    }
    //check planning if there is appointement between two dates
    public  List<Appointment> checkPlanning(long id,Date debut,Date fin){
        List<Appointment> appointments = appointmentService.getAppointementByPlanning(id);
        List<Appointment> appointmentList = new ArrayList<>();
        for(Appointment app : appointments){
           if((app.getAppointmentDate().after(debut)&&app.getAppointmentDate().before(fin)) == true){
               appointmentList.add(app);
           }


        }
        return appointmentList;

    }

    @Override
    public void update(Planning planning) throws ParseException {

        Planning planning1 = planningRepository.findById(planning.getId()).orElse(null);
        List<Appointment> appointments = checkPlanning(planning.getId(),planning.getDateDebut(),planning.getDateFin());

        if (planning1 != null) {
            Date currentDate = new Date(); // This object contains the current date value
            if (CheckDaysForPlanning(appointments, currentDate) == true) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");


                    if (planning.getCommentaire() != null) {
                        planning1.setCommentaire(planning.getCommentaire());
                    }

                    if (planning.getDateDebut() != null) {
                        planning1.setDateDebut(planning.getDateDebut());
                    }
                    if (planning.getDateFin() != null) {
                        planning1.setDateFin(planning.getDateFin());
                    }

                    planning1.setModificationDate(currentDate);

                    if (planning.is_Disponible() != planning1.is_Disponible()) {
                        planning1.set_Disponible(planning.is_Disponible());
                    }
                    if (planning.isHas_appointment() != planning1.isHas_appointment()) {
                        planning1.setHas_appointment(planning.isHas_appointment());
                    }


            }
            else{
                System.out.println("you have another appointement in this time please check your planning");
            }
        }
    }



    @Override
    public void delete(Long id) {
planningRepository.deleteById(id);

    }

    @Override
    public Planning getPlanningById(Long id) {
        boolean trouve = planningRepository.existsById(id);
        if(!trouve){
            return  null;
        }
        return planningRepository.getOne(id);
    }


    @Override
    public void ProviderSavePlanning(Planning planning) {

    }

    @Override
    public List<Planning> getPlanningsByAvailability(Date date) {
        List<Planning> plannings = planningRepository.findAll();
        List<Planning> planningList = new ArrayList<>();
        for(Planning planning:plannings){
            if(planning.is_Disponible()==true&& (date.after(planning.getDateDebut())&&date.before(planning.getDateFin()))){
                planningList.add(planning);
            }
        }
        return planningList;
    }




}
