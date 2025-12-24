package com.enessalman.service;

import com.enessalman.dto.DtoGoal;
import com.enessalman.dto.DtoGoalRequest;
import com.enessalman.entities.Goal;
import com.enessalman.entities.Status;
import com.enessalman.entities.User;
import com.enessalman.repository.GoalRepository;
import com.enessalman.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GoalService {
    @Autowired
    GoalRepository goalRepository;
    @Autowired
    UserRepository userRepository;


    public DtoGoal addGoal(DtoGoalRequest request){
        User user = userRepository.getReferenceById(request.getUserId());
        DtoGoal dtoGoal = new DtoGoal();
        Goal goal = new Goal();
        BeanUtils.copyProperties(request,goal);
        goal.setUser(user);
        Goal dbGoal = goalRepository.save(goal);
        BeanUtils.copyProperties(dbGoal,dtoGoal);
        return dtoGoal;
    }

    public DtoGoal deleteGoalById(int id){
        Optional<Goal> optional = goalRepository.findById(id);
       DtoGoal dtoGoal = new DtoGoal();
        if (optional.isPresent()){
           Goal dbGoal = optional.get();
            goalRepository.delete(dbGoal);
            BeanUtils.copyProperties(dbGoal,dtoGoal);
        }
        return dtoGoal;
    }

    public DtoGoal updateGoal(int id,DtoGoalRequest request){
        Optional<Goal> optional = goalRepository.findById(id);
        DtoGoal dtoGoal = new DtoGoal();
        if (optional.isPresent()){
            Goal dbGoal = optional.get();
            dbGoal.setGoal(request.getGoal());
            dbGoal.setEndAt(request.getEndAt());
            dbGoal.setPriority(request.getPriority());
            dbGoal.setStatus(request.getStatus());
            goalRepository.save(dbGoal);
            BeanUtils.copyProperties(dbGoal,dtoGoal);
        }
        return dtoGoal;
    }
}
