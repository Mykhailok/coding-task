package pl.beutysite.recruit;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class TaxCalculationsHelperTest {

    @Test
    public void testCalculatePercentagePart() throws Exception {
        BigDecimal result = TaxCalculationsHelper.getPercentagePart(new BigDecimal(100), new BigDecimal(50));
        assertEquals(50, result.doubleValue(), 1e-3);

        result = TaxCalculationsHelper.getPercentagePart(new BigDecimal(100), new BigDecimal(25));
        assertEquals(25.0, result.doubleValue(), 1e-2);

        result = TaxCalculationsHelper.getPercentagePart(new BigDecimal(100.23), new BigDecimal(25));
        assertEquals(25.06, result.doubleValue(), 1e-2);

        result = TaxCalculationsHelper.getPercentagePart(new BigDecimal(100), new BigDecimal(1.5));
        assertEquals(1.5, result.doubleValue(), 1e-2);
    }

    @Test
    public void testCalculateAddPercentage() throws Exception {
        BigDecimal result = TaxCalculationsHelper.addPercentage(new BigDecimal(100), new BigDecimal("1.5"));
        assertEquals(101.5, result.doubleValue(), 1e-2);

        result = TaxCalculationsHelper.addPercentage(new BigDecimal(100), new BigDecimal("50"));
        assertEquals(150, result.doubleValue(), 1e-2);
    }

    @Test
    public void testCalculateSubtractPercentage() throws Exception {
        BigDecimal result = TaxCalculationsHelper.subtractPercentage(new BigDecimal(100), new BigDecimal("1.5"));
        assertEquals(98.5, result.doubleValue(), 1e-2);

        result = TaxCalculationsHelper.subtractPercentage(new BigDecimal(100), new BigDecimal("50"));
        assertEquals(50, result.doubleValue(), 1e-2);

        result = TaxCalculationsHelper.subtractPercentage(new BigDecimal(100.23), new BigDecimal(25));
        assertEquals(75.17, result.doubleValue(), 1e-2);


        result = TaxCalculationsHelper.subtractPercentage(new BigDecimal(100), new BigDecimal("55.5"));
        assertEquals(44.5, result.doubleValue(), 1e-2);
    }
}