package com.example.BackEndService.Service;

import com.example.BackEndService.model.Appointment;
import com.example.BackEndService.model.Client;

import java.util.List;

public interface IAppointmentService {
    List<Appointment> getAllAppointement();
    Appointment saveAppointement(Appointment appointment);
    Appointment updateAppointement(Appointment appointment);
    void deleteAppointement(Long id);
    List<Appointment> getAppointementByPlanning(Long id);
    Appointment getAppointementById(Long id);

}
