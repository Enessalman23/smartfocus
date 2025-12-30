package com.enessalman.controller;


import com.enessalman.dto.DtoGoal;
import com.enessalman.dto.DtoGoalRequest;
import com.enessalman.entities.Goal;
import com.enessalman.service.GoalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/rest/api/goal")
public class GoalController {

    private final GoalService goalService;

    @Autowired
    public GoalController(GoalService goalService) {
        this.goalService = goalService;
    }


    @PostMapping(path = "/add")
    public ResponseEntity<DtoGoal> addGoal(@RequestBody DtoGoalRequest request) {

        DtoGoal saveGoal = goalService.addGoal(request);
        return ResponseEntity.ok(saveGoal);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteGoalById(@PathVariable int id) {

        goalService.deleteGoalById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<DtoGoal> updateGoal(@PathVariable int id, @RequestBody DtoGoalRequest request) {

        DtoGoal updateGoal = goalService.updateGoal(id, request);
        return ResponseEntity.ok().body(updateGoal);
    }

    @GetMapping(path = "/pagination")
    public ResponseEntity<Page<Goal>> pagination(@RequestParam int offset, @RequestParam int pagesize) {
        return ResponseEntity.ok(goalService.pagination(offset, pagesize));
    }
}
