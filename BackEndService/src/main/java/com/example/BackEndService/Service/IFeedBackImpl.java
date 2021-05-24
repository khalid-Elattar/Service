package com.example.BackEndService.Service;

import com.example.BackEndService.dao.FeedBackRepository;
import com.example.BackEndService.model.Admin;
import com.example.BackEndService.model.FeedBack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IFeedBackImpl implements IFeedBackService{
    @Autowired
    FeedBackRepository feedBackRepository;
    @Override
    public List<FeedBack> getAllFeedback() {
        List<FeedBack> feedBacks = feedBackRepository.findAll();
        return feedBacks;
    }

    @Override
    public void save(FeedBack feedBack) {

        feedBackRepository.save(feedBack);
    }

    @Override
    public void update(FeedBack feedBack) {
        FeedBack feedBack1 = feedBackRepository.findById(feedBack.getId()).orElse(null);
        if(feedBack1 !=null){
            feedBack1.setId(feedBack.getId());
            feedBack1.setComment(feedBack.getComment());
            feedBack1.setRating(feedBack.getRating());
            feedBack1.setAppointment(feedBack.getAppointment());

        }

    }

    @Override
    public void delete(Long id) {

        feedBackRepository.deleteById(id);
    }

    @Override
    public FeedBack getFeedbackById(Long id) {
        boolean trouve = feedBackRepository.existsById(id);
        if(!trouve)
            return null;
        return feedBackRepository.getOne(id);
    }
}
