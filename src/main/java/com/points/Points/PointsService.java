package com.points.Points;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@Service
@Transactional
public class PointsService {

    @Autowired
    private ProductRepository repo;

    public void setPoints(Points thePoint)
    {
          repo.save(thePoint);
    }
    public ArrayList<Points> getpointsSortedByTime()
    {
        return repo.findAllByOrderByTimestamp();
    }

    public List<Points> getAllPoints()
    {

        return repo.findAll();
    }

    public Points getPointById(int id)
    {
        Points p=new Points();
        p=repo.findById(id).orElse(null);;
        return p;

    }
    public HashMap<String, Integer> getSumOfPoints(ArrayList<Points> pointsList)
    {
        HashMap<String,Integer> hmap=new HashMap<>();
        for (Points thePoints:pointsList)
        {
            if(hmap.containsKey(thePoints.getPayer()))
            {
                int tempPoints=thePoints.getPoints()+hmap.get(thePoints.getPayer());
                hmap.put(thePoints.getPayer(),tempPoints);
            }
            else
            {
                hmap.put(thePoints.getPayer(),thePoints.getPoints());
            }

        }

        return hmap;
    }
}
