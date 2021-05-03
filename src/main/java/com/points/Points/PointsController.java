package com.points.Points;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;

@RestController
public class PointsController {
    @Autowired
    private PointsService pointsService;

    @PostMapping("fetchRewards/addTransaction")
    public String addtransaction(@RequestBody Points points, HttpSession session){
        ArrayList< Points> newArray=new ArrayList<Points>();
        try{
            //setting object in database
            pointsService.setPoints(points);
        }
        catch (Exception e)
        {

            e.printStackTrace();
        }
        ArrayList<Points> a= (ArrayList<Points>) pointsService.getpointsSortedByTime();


        if(!(a.isEmpty()))
        {
            session.setAttribute("points",a);
            return "Transaction Added"+points.toString();
        }
        else
            return "Error: Transaction cannot be added"+points;
    }

    @GetMapping("fetchRewards/printAll")
    public ArrayList<Points> printAll(@SessionAttribute("points") ArrayList<Points>  hm)
    {

        ArrayList<Points> pointList=new ArrayList<Points>();
        try
        {
        Utility utility=new Utility();
        utility.printAllElements(hm);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return hm;
    }



    @PostMapping("fetchRewards/spendPoints")
    public ArrayList<Points> spend(@SessionAttribute("points") ArrayList<Points>  pointsList, @RequestBody Points useData, HttpSession session)
    {

        HashMap<String,Integer> hmap=new HashMap<>();
        int i=0;
        int subtraction=0;
        int pointsToSpend= useData.getPoints();
        while(pointsToSpend>0)
        {
            int id=pointsList.get(i).getId();
            int currentPoints = pointsList.get(i).getPoints();

            subtraction = pointsToSpend - currentPoints;

            if (subtraction > 0) {
                if (!(hmap.containsKey(pointsList.get(i).getPayer()))) {
                    hmap.put(pointsList.get(i).getPayer(),-currentPoints);
                }
                else {
                    int tempPoint = hmap.get(pointsList.get(i).getPayer());
                    hmap.put(pointsList.get(i).getPayer(), tempPoint - currentPoints);
                }
            } else {
                hmap.put(pointsList.get(i).getPayer(),-pointsToSpend);
            }

            //setting values in database
            Points u = pointsService.getPointById(id);
            if(subtraction>0)
            {
                //set point in database

                u.setPoints(0);
            }
            else {
                u.setPoints(Math.abs(subtraction));

            }
            pointsToSpend=subtraction;
            i++;
        }

        //setting last element point
            Points n= pointsService.getPointById(pointsList.get(i-1).getId());
            n.setPoints(Math.abs(pointsToSpend));
        //end


        //Creating new Points object to return as Json
        Utility useUtlity=new Utility();
        ArrayList<Points> list=useUtlity.toReturnPoints(hmap);



        return list;
    }

    @GetMapping("fetchRewards/getBalance")
    public HashMap<String, Integer> getBalance()
    {
        ArrayList<Points> pointsList= (ArrayList<Points>) pointsService.getAllPoints();
        Utility useUtility=new Utility();
        HashMap<String,Integer> hashMap= useUtility.getSumOfPoints(pointsList);
        return hashMap;

    }

}
