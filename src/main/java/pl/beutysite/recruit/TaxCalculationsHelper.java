package pl.beutysite.recruit;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class TaxCalculationsHelper {

    public static BigDecimal addPercentage(BigDecimal base, BigDecimal percentage) {//можно удалить
        return base.add(getPercentagePart(base, percentage)).setScale(2, BigDecimal.ROUND_HALF_EVEN);
    }

    public static BigDecimal getPercentagePart(BigDecimal base, BigDecimal percentage) { //фикс на точность вычислений
        return percentage.divide(new BigDecimal(100)).multiply(base).setScale(2, BigDecimal.ROUND_HALF_EVEN);
    }

    public static BigDecimal subtractPercentage(BigDecimal base, BigDecimal percentage) {//можно удалить
        return base.subtract(getPercentagePart(base, percentage)).setScale(2, BigDecimal.ROUND_HALF_EVEN);
    }


}
