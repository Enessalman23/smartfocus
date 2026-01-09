package com.enessalman.controller;


import com.enessalman.dto.DtoGoal;
import com.enessalman.dto.DtoGoalRequest;
import com.enessalman.entities.Goal;
import com.enessalman.service.GoalService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/rest/api/goal")
public class GoalController {

    private final GoalService goalService;

    public GoalController(GoalService goalService) {
        this.goalService = goalService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<DtoGoal> getGoalById(@PathVariable int id){
        goalService.getGoalById(id);
        return ResponseEntity.ok().build();
    }
    @PostMapping
    public ResponseEntity<DtoGoal> addGoal(@Valid @RequestBody DtoGoalRequest request) {

        DtoGoal saveGoal = goalService.addGoal(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(saveGoal); //TODO: 201 dönsün CREATED
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteGoalById(@PathVariable int id) {

        goalService.deleteGoalById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<DtoGoal> updateGoal(@PathVariable int id, @Valid @RequestBody DtoGoalRequest request) {

        DtoGoal updateGoal = goalService.updateGoal(id, request);
        return ResponseEntity.ok().body(updateGoal);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Page<Goal>> getById(@PathVariable int id) {
        //return ResponseEntity.ok(goalService.getById(id));
        //TODO: implement remain parts
        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "/filter")
    //TODO: bu şu an pagination api si olmuş gerçek bir filter apisine çevir
    public ResponseEntity<Page<Goal>> pagination(@RequestParam(defaultValue = "10") int pagesize, @RequestParam(defaultValue = "0") int offset) {
        return ResponseEntity.ok(goalService.pagination(offset, pagesize));
    }
}
