package org.qaway.base;

import java.util.ArrayList;
import java.util.List;

public class Utility {

    public static boolean isSorted(double[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] > array[i + 1])
                return false;
        }
        return true;
    }

    public static double[] listToArrayOfDoubles(List<String> list){
        double[] doubleList = new double[list.size()];
        double sum = 0;
        for (int i = 0; i < list.size(); ++i) {
            doubleList[i] = Double.parseDouble(list.get(i));
        }
        return doubleList;
    }


}
