package com.points.Points;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Utility {
    private ArrayList<Points> hm=new ArrayList<Points>();


    public ArrayList<Points> addTransaction(Points thePoints){
        try {


            hm.add(thePoints);


        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return hm;
    }

    public void printAllElements(ArrayList<Points> hmap)
    {
        System.out.println("Inside print all");
        System.out.println("size: "+hmap.size());

        for(int i=0;i<hmap.size();i++) {
            System.out.println("********************************");
            // Points thePo= entry.getValue();
            System.out.println(hmap.get(i).getPoints()+" "+hmap.get(i).getPayer()+" "+hmap.get(i).getTimestamp());
            System.out.println("********************************");
        }
    }
    public ArrayList<Points> mergeSameCompany(ArrayList<Points> previousList)
    {
        //creating hash map to remove same elements
        HashMap<String,Points> hmap=new HashMap<>();
        for(Points p:previousList)
        {
            if(hmap.containsKey(p.getPayer()))
            {
                Points fromMap=hmap.get(p.getPayer());

                fromMap.setPoints(fromMap.getPoints()+p.getPoints());
                if(fromMap.getTimestamp().compareTo(p.getTimestamp())>0)
                {
                    fromMap.setTimestamp(p.getTimestamp());
                }
            }
            else
            {
                hmap.put(p.getPayer(),p);
            }
        }
        //convert map to list
        ArrayList<Points> valueList = new ArrayList<Points>(hmap.values());
        return valueList;
    }

    public void sortByDate( ArrayList<Points> previousArray)
    {
        ArrayList<Points> newArray=new ArrayList<Points>();
        for(Points p:previousArray)
        {

        }

    }

    public ArrayList<Points> toReturnPoints(HashMap<String, Integer> hmap) {

        ArrayList<Points> list=new ArrayList<Points>();
        for (Map.Entry<String, Integer> entry : hmap.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            Points newPoint=new Points(key,value.intValue(),null);
            list.add(newPoint);
        }
        return list;
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
