package com.projectrespite.surfingjudge.util;

import lombok.var;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class AggregateUtil {

    public static double average(List<Double> array){

        double max = -1000;
        double min = 1000;
        double sum = 0;

        for (int i = 0; i < array.size(); i++){
            if(array.get(i) > max)
                max = array.get(i);
            if(array.get(i) < min)
                min = array.get(i);
            sum += array.get(i);
        }

        return (sum - max -min) / array.size();
    }

    public static double sumBestAndSecondBest(ArrayList<Double> list){

        if(list.size() == 0)
            return 0.0;

        var temp = (ArrayList<Double>) list.clone();
        temp.sort(Comparator.reverseOrder());

        if(temp.size() == 1)
            return temp.get(0);

        else
            return temp.get(0) + temp.get(1);
    }
}
