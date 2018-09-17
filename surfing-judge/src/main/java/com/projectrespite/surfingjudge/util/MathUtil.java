package com.projectrespite.surfingjudge.util;

import lombok.var;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MathUtil {

    public static double average(List<Double> array) {

        double max = array.stream().max(Comparator.naturalOrder()).get();
        double min = array.stream().min(Comparator.naturalOrder()).get();
        double sum = array.stream().mapToDouble(d -> d).sum();
        return (sum - max - min) / (array.size() - 2);
    }

    public static double sumBestAndSecondBest(List<Double> list) {

        if (list.size() == 0)
            return 0.0;

        var temp = new ArrayList<Double>(list);
        temp.sort(Comparator.reverseOrder());

        if (temp.size() == 1)
            return temp.get(0);

        else
            return temp.get(0) + temp.get(1);
    }
}