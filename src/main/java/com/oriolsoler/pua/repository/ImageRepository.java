package com.oriolsoler.pua.repository;


import com.oriolsoler.pua.entities.Image;
import com.oriolsoler.pua.entities.Pua;

import java.util.List;

public interface ImageRepository extends CrudRepository<Image, Integer> {
    List<Image> findImageByPua(Pua pua);
}
