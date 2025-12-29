package com.enessalman.mapper;

import com.enessalman.dto.DtoGoal;
import com.enessalman.dto.DtoGoalRequest;
import com.enessalman.entities.Goal;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")// Spring bean olarak yönetilmesi için

public interface IGoalMapper {

    // Goal Entity -> DtoGoal (Geri dönüş için)
    DtoGoal toDto(Goal goal);

    // DtoGoalRequest -> Goal Entity (Kaydetme için)
    @Mapping(target = "user", ignore = true)  // Request -> Entity (UserId alanı eşlenirken ignore edilebilir veya servis katmanında setlenebilir)
    Goal toEntity(DtoGoalRequest dtoGoalRequest);
}
