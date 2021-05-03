package com.points.Points;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.Optional;


public interface ProductRepository extends JpaRepository<Points, Integer> {
    public ArrayList<Points> findAllByOrderByTimestamp();

}
