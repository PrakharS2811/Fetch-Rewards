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

            //get Previous list from session
          /*  ArrayList<Points> previousArray= (ArrayList<Points>) session.getAttribute("points");

            //sorting by date
            Points previousPoints=new Points();
            previousPoints=previousArray.get(previousArray.size()-1);
            Date previousDateTemp=previousPoints.getTimestamp();
            Date newDateTemp=points.getTimestamp();

            long previousDate=previousDateTemp.getTime() / 1000L;
            long newDate=newDateTemp.getTime()/1000L;

            if(previousDate>newDate)
            {
                previousArray.set(previousArray.size()-1,points);
                previousArray.add(previousPoints);
            }
            else
            {
                previousArray.add(points);
            }

            //merge to new list
            newArray.addAll(previousArray);*/
        }
        catch (Exception e)
        {
            //if no previous list then add points to new array
           // newArray.add(points);
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
       // pointList=pointsService.getpointsSortedByTime();
        Utility utility=new Utility();
        utility.printAllElements(hm);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return hm;
    }


    public String spendPoints(@SessionAttribute("points") ArrayList<Points>  hm,@RequestBody Points point,HttpSession session)
    {
        Utility utility=new Utility();

       // ArrayList<Points> hm=utility.mergeSameCompany(list);
        int pointToSpend =point.getPoints();

        for (int i=0;i<hm.size();i++)
        {
            int currentPoint=hm.get(i).getPoints();
            if(currentPoint<0)//skip if points are in negative
            {
                continue;
            }
            int subtraction=pointToSpend-currentPoint;
            pointToSpend=subtraction;



            if(pointToSpend<=0)
            {
                hm.get(i).setPoints(Math.abs(subtraction));
                break;
            }
            else{
                hm.get(i).setPoints(0);
                continue;
            }


        }
        session.setAttribute("points",hm);
        return " "+point.getPoints()+"***"+hm.get(0).getPoints();
    }

    @PostMapping("fetchRewards/spendPoints")
    public ArrayList<Points> spend(@SessionAttribute("points") ArrayList<Points>  pointsList, @RequestBody Points useData, HttpSession session)
    {
        //printing pointList
        System.out.println("printing pointList");
        for(Points thePoints:pointsList)
        {
            System.out.println(thePoints.getPayer()+"   "+thePoints.getPoints());
        }
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
            System.out.println("ABS: "+Math.abs(pointsToSpend));
        //end


        //Creating new Points object to return as Json
        Utility useUtlity=new Utility();
        ArrayList<Points> list=useUtlity.toReturnPoints(hmap);


        //printing new list
        System.out.println("printing new list");
        for(Points p:list)
        {
            System.out.println(p.getPayer()+" "+p.getPoints());
        }
        return list;
    }

    @GetMapping("fetchRewards/getBalance")
    public HashMap<String, Integer> getBalance()
    {
        ArrayList<Points> pointsList= (ArrayList<Points>) pointsService.getAllPoints();
        Utility useUtility=new Utility();
        HashMap<String,Integer> hashMap= useUtility.getSumOfPoints(pointsList);
        System.out.println("hasmao size: "+hashMap.size());
        return hashMap;

    }

}
