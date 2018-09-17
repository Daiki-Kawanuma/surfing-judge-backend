package com.projectrespite.surfingjudge.util;

import lombok.var;

import java.util.ArrayList;
import java.util.Comparator;

public class AggregateUtil {

    public static double avarage(double[] array){

        double max = -1000;
        double min = 1000;
        double sum = 0;

        for (int i = 0; i < array.length; i++){
            if(array[i] > max)
                max = array[i];
            if(array[i] < min)
                min = array[i];
            sum += array[i];
        }

        return (sum - max -min) / array.length;
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
