package com.projectrespite.surfingjudge.util;

import lombok.var;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MathUtil {

    public static double average(List<Double> array) {

        return new BigDecimal(array.stream().mapToDouble(d -> d).sum() / array.size())
                .setScale(1, BigDecimal.ROUND_HALF_UP)
                .doubleValue();
    }

    public static double sumBestAndSecondBest(List<Double> list) {

        if (list.size() == 0)
            return 0.0;

        var temp = new ArrayList<Double>(list);
        temp.sort(Comparator.reverseOrder());

        if (temp.size() == 1)
            return new BigDecimal(temp.get(0))
                    .setScale(1, BigDecimal.ROUND_HALF_UP)
                    .doubleValue();

        else
            return new BigDecimal(temp.get(0) + temp.get(1))
                    .setScale(1, BigDecimal.ROUND_HALF_UP)
                    .doubleValue();
    }
}
