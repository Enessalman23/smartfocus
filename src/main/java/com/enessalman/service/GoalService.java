package com.enessalman.service;

import com.enessalman.dto.DtoGoal;
import com.enessalman.dto.DtoGoalRequest;
import com.enessalman.entities.Goal;
import com.enessalman.entities.Status;
import com.enessalman.entities.User;
import com.enessalman.mapper.IGoalMapper;
import com.enessalman.repository.GoalRepository;
import com.enessalman.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
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
    @Cacheable(value = "goals", key = "#id")
    public DtoGoal getGoalById(int id) {
        log.info("DB'den veri çekiliyor: {}", id);
        Goal goal = goalRepository.getReferenceById(id);
        return goalMapper.toDto(goal);
    }
    public DtoGoal addGoal(DtoGoalRequest request) {
        log.info("Hedef Ekleme isteği geldi. Hedef: {}", request.getGoal());
        try {
            User user = userRepository.getReferenceById(request.getUserId());
            Goal goal = goalMapper.toEntity(request);
            if (goal.getStatus() == null) {
                goal.setStatus(Status.PENDING);
            }
            goal.setUser(user);
            Goal dbGoal = goalRepository.save(goal);
            log.info("Hedef Eklendi. Hedef {}", dbGoal.getGoal());
            return goalMapper.toDto(dbGoal);
        } catch (Exception e) {
            log.error("Hedef Eklenemedi. Mesaj: {}", e.getMessage());
        }
        return null;
    }

    @CacheEvict(value = "goals",key = "#id") //sadece bu idyi temizler.
    public void deleteGoalById(int id) {
        log.info("Hedef Silme isteği geldi. ID: {}", id);
        try {
            goalRepository.deleteById(id);
            log.info("Hedef Başarıyla Silindi. ID: {}", id);
        } catch (Exception e) {
            log.error("Hedef Silinemedi. Mesaj: {}", e.getMessage());
            throw e;
        }
    }

    @CachePut(value = "goals",key = "#id") //cache i de günceller
    public DtoGoal updateGoal(int id, DtoGoalRequest request) {
        log.info("Hedef Güncelleme isteği geldi. ID: {}", request.getUserId());
        try {
            Optional<Goal> optional = goalRepository.findById(id);
            if (optional.isPresent()) {
                Goal dbGoal = optional.get();
                dbGoal.setGoal(request.getGoal());
                dbGoal.setEndAt(request.getEndAt());
                dbGoal.setPriority(request.getPriority());
                dbGoal.setStatus(request.getStatus());
                Goal updatedGoal = goalRepository.save(dbGoal);
                log.info("Hedef güncellendi. ID: {}", updatedGoal.getId());
                return goalMapper.toDto(updatedGoal);
            }
        } catch (Exception e) {
            log.error("Hedef güncellenemedi. Mesaj: {}", e.getMessage());
        }
        return null;
    }
}
