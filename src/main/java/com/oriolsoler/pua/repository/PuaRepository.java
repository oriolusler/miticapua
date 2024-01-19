package com.oriolsoler.pua.repository;


import com.oriolsoler.pua.entities.Pua;

import java.util.List;

public interface PuaRepository extends CrudRepository<Pua, Integer> {
    List<Pua> findAll();
}
