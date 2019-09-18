package com.afkl.travel.exercise.persistence;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LocationRepository extends CrudRepository<Location, Long> {

    List<Location> findAll();

    @Query("select l from Location l where l.parentId in(\n" +
            "select l1.id from Location l1 where l1.parentId in (select  l2.id from Location l2 where l2.code=:code) ) and l.type=:type_")
    List<Location> findAllLocationsByTypeAndCode(@Param("type_") LocationType type, @Param("code") String code);

}

