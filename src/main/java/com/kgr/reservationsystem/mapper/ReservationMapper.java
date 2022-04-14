package com.kgr.reservationsystem.mapper;

import com.kgr.reservationsystem.entity.ReservationEntity;
import com.kgr.reservationsystem.model.Reservation;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ReservationMapper {

    @Mappings({
            @Mapping(source = "version", target = "version", defaultValue = "1")
    })
    ReservationEntity domainToEntity(Reservation source);

    Reservation entityToDomain(ReservationEntity source);

    default List<Reservation> entitiesToDomains(List<ReservationEntity> entities) {
        return entities.stream().map(this::entityToDomain).collect(Collectors.toList());
    }
}
