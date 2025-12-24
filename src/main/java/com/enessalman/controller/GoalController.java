package com.enessalman.controller;


import com.enessalman.dto.DtoGoal;
import com.enessalman.dto.DtoGoalRequest;
import com.enessalman.service.GoalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/rest/api/goal")
public class GoalController {

    @Autowired
    GoalService goalService;

    @PostMapping(path = "/add")
    public DtoGoal addGoal(DtoGoalRequest request){
        return goalService.addGoal(request);
    }

    @DeleteMapping(path = "/delete/{id}")
    public DtoGoal deleteGoalById(@PathVariable int id){
        return goalService.deleteGoalById(id);
    }

    @PutMapping(path = "/update/{id}")
    public DtoGoal updateGoal(@PathVariable int id,DtoGoalRequest request){
        return goalService.updateGoal(id,request);
    }
}
