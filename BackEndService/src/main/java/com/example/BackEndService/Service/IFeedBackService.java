package com.example.BackEndService.Service;

import com.example.BackEndService.model.Client;
import com.example.BackEndService.model.FeedBack;

import java.util.List;

public interface IFeedBackService {
    List<FeedBack> getAllFeedback();
    void save(FeedBack feedBack);
    void update(FeedBack feedBack);
    void delete(Long id);
    FeedBack getFeedbackById(Long id);
}
