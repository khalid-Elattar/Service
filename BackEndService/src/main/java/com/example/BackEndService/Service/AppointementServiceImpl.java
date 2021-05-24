package com.example.BackEndService.Service;

import com.example.BackEndService.dao.AppointmentRepository;
import com.example.BackEndService.model.Appointment;
import com.example.BackEndService.model.Planning;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class AppointementServiceImpl implements IAppointmentService{
    @Autowired
    AppointmentRepository appointmentRepository;
    @Autowired
    IPlanningService planningService;

    @Override
    public List<Appointment> getAllAppointement() {
        List<Appointment> appointments = appointmentRepository.findAll();
        if(appointments != null){
            return appointments;
        }
        else {
            return null;
        }

    }


    @Override
    public Appointment saveAppointement(Appointment appointment) {
        List<Planning> plannings= planningService.getPlanningsByAvailability(appointment.getAppointmentDate());
        Appointment appointment1 = appointment;
        appointment1.setPlanning(plannings.get(0));

        appointmentRepository.save(appointment1);


return appointment1;
    }
    public long daysBetween(Date one,Date two){
        long difference = (one.getTime()-two.getTime())/86400000;
        return Math.abs(difference);
    }

    @Override
    public Appointment updateAppointement(Appointment appointment) {
        Appointment appointment1 = appointmentRepository.findById(appointment.getId()).orElse(null);
        Date date  = new Date();
        long days = daysBetween(date,appointment1.getAppointmentDate());
        if(days>2){
            appointment1.setAdresse(appointment.getAdresse());
            appointment1.setAppointmentTime(appointment.getAppointmentTime());
            appointment1.setAppointmentDate(appointment.getAppointmentDate());
            appointment1.setCommande(appointment.getCommande());
            appointment1.setPlanning(appointment.getPlanning());
            appointment1.setStatus(appointment.getStatus());
            appointment1.setFeedBack(appointment.getFeedBack());
            appointmentRepository.save(appointment1);
            return appointment1;
        }
        else {
            System.out.println("you don't have the righ to change anything about appointment");
            return null;
        }


    }

    @Override
    public void deleteAppointement(Long id) {
        appointmentRepository.deleteById(id);

    }

    @Override
    public List<Appointment> getAppointementByPlanning(Long id) {
        boolean trouve = appointmentRepository.existsById(id);
        List<Appointment> appointmentList = new ArrayList<>();
        if(!trouve)
             return null ;
       List<Appointment> appointments = appointmentRepository.findAll();
       for(Appointment app : appointments){
           if(app.getPlanning().getId()==id){
               appointmentList.add(app);
           }
       }
       return appointmentList;
    }

    @Override
    public Appointment getAppointementById(Long id) {
       boolean trouve = appointmentRepository.existsById(id);
       Appointment appointment = new Appointment();
       if(trouve == true ){
           appointment = appointmentRepository.getOne(id);
           return appointment;
       }
       else {
           return null;
       }
    }


}
