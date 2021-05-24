package com.example.BackEndService.Service;

import com.example.BackEndService.model.Client;
import com.example.BackEndService.model.Planning;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

public interface IPlanningService {
    List<Planning> getAllPlanning();
    Planning save(Planning planning);
    void update(Planning planning) throws ParseException;
    void delete(Long id);
    Planning getPlanningById(Long id);
    void ProviderSavePlanning(Planning planning);
    List<Planning> getPlanningsByAvailability(Date date);
    List<Planning> getPlanningsByIdServiceProvider(Long id);
}
