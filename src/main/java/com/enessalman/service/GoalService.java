package com.enessalman.service;

import com.enessalman.dto.DtoGoal;
import com.enessalman.dto.DtoGoalRequest;
import com.enessalman.entities.Goal;
import com.enessalman.entities.Status;
import com.enessalman.entities.User;
import com.enessalman.mapper.IGoalMapper;
import com.enessalman.repository.GoalRepository;
import com.enessalman.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GoalService {
    @Autowired
    public GoalService(GoalRepository goalRepository, UserRepository userRepository, IGoalMapper goalMapper) {
        this.goalRepository = goalRepository;
        this.userRepository = userRepository;
        this.goalMapper = goalMapper;
    }

    private final IGoalMapper goalMapper;
    private final GoalRepository goalRepository;
    private final UserRepository userRepository;

    public Page<Goal> pagination(int offset, int pagesize) {
        Pageable pageable = PageRequest.of(offset, pagesize);
        return goalRepository.findAll(pageable);
    }

    public DtoGoal addGoal(DtoGoalRequest request) {
        User user = userRepository.getReferenceById(request.getUserId());
        Goal goal = goalMapper.toEntity(request);
        if (goal.getStatus() == null) {
            goal.setStatus(Status.PENDING);
        }
        goal.setUser(user);
        Goal dbGoal = goalRepository.save(goal);
        return goalMapper.toDto(dbGoal);
    }

    public void deleteGoalById(int id) {
        userRepository.deleteById(id);
    }

    public DtoGoal updateGoal(int id, DtoGoalRequest request) {
        Optional<Goal> optional = goalRepository.findById(id);

        if (optional.isPresent()) {
            Goal dbGoal = optional.get();
            dbGoal.setGoal(request.getGoal());
            dbGoal.setEndAt(request.getEndAt());
            dbGoal.setPriority(request.getPriority());
            dbGoal.setStatus(request.getStatus());
            Goal updatedGoal = goalRepository.save(dbGoal);
            return goalMapper.toDto(updatedGoal);
        }
        return null;
    }
}
