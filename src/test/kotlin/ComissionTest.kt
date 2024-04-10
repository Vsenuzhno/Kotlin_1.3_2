import org.junit.Test
import kotlin.test.assertEquals

class CommissionTest {

    @Test
    fun testVisaCommissionCalculation() {
        val commission = calculateCommission("Visa", 200000.0, 50000.0)
        assertEquals(372.0, commission)
    }

    @Test
    fun testMirCommissionCalculation() {
        val commission = calculateCommission("Мир", 300000.0, 70000.0)
        assertEquals(0.0, commission)
    }

    @Test
    fun testMastercardCommissionMonthlyLimitNotExceeded() {
        val commission = calculateCommission("Mastercard", 0.0, 10000.0)
        assertEquals(0.0, commission)
    }

    @Test
    fun testMastercardCommissionMonthlyLimitExceededButRemainingAmountLessThanLimit() {
        val commission = calculateCommission("Mastercard", 70000.0, 5000.0)
        assertEquals(0.0, commission)
    }


    @Test
    fun testInvalidCardType() {
        val commission = calculateCommission("AmericanExpress", 50000.0, 100000.0)
        assertEquals(0.0, commission)
    }

    @Test
    fun testExceedsDailyLimit() {
        val commission = calculateCommission("Mastercard", 50000.0, 200000.0)
        assertEquals(0.0, commission)
    }


    @Test
    fun testExceedsMonthlyLimit() {
        val commission = calculateCommission("Mastercard", 550000.0, 100000.0)
        assertEquals(0.0, commission)
    }

    @Test
    fun testVisaCommissionBelowMinimum() {
        val commission = calculateCommission("Visa", 0.0, 10.0)
        assertEquals(35.0, commission)
    }

    @Test
    fun testVisaMinimumCommission() {
        val commission = calculateCommission("Visa", 0.0, 35.0)
        assertEquals(35.0, commission)
    }

    @Test
    fun testMastercardCommissionWithZeroRemainingLimit() {
        val commission = calculateCommission("Mastercard", 75000.0, 100000.0)
        assertEquals(620.0, commission)
    }

    @Test
    fun `test exceeding daily limit`() {
        val commission = calculateCommission("Мир", 0.0, 200000.0)
        assertEquals(0.0, commission)
    }

    @Test
    fun `test exceeding monthly limit`() {
        val commission = calculateCommission("Mastercard", 500000.0, 200000.0)
        assertEquals(0.0, commission)
    }

    @Test
    fun `test Visa commission`() {
        val commission = calculateCommission("Visa", 200000.0, 50000.0)
        assertEquals(375.0, commission)
    }

    @Test
    fun `test Мир card commission`() {
        val commission = calculateCommission("Мир", 300000.0, 70000.0)
        assertEquals(0.0, commission)
    }

    @Test
    fun `test incorrect card type`() {
        val commission = calculateCommission("Unknown", 0.0, 100000.0)
        assertEquals(0.0, commission)
    }

    @Test
    fun testMastercardCommissionWithNegativeRemainingMonthlyLimit() {
        val commission = calculateCommission("Mastercard", 150000.0, 200000.0)
        assertEquals(0.0, commission, 0.0)
    }


    @Test
    fun `test Mastercard commission with no remaining monthly limit`() {
        val commission = calculateCommission("Mastercard", 75000.0, 100000.0)
        assertEquals(620.0, commission)
    }

    @Test
    fun `test Visa commission below minimum`() {
        val commission = calculateCommission("Visa", 0.0, 10.0)
        assertEquals(35.0, commission)
    }


    @Test
    fun `test Мир commission`() {
        val commission = calculateCommission("Мир", 300000.0, 70000.0)
        assertEquals(0.0, commission)
    }

    @Test
    fun `test invalid card type`() {
        val commission = calculateCommission("Unknown", 0.0, 100000.0)
        assertEquals(0.0, commission)
    }


    @Test
    fun `test transfer amount above daily limit`() {
        val commission = calculateCommission("Mastercard", 10000.0, 150100.0)
        assertEquals(0.0, commission)
    }


    @Test
    fun `test transfer amount above monthly limit`() {
        val commission = calculateCommission("Mastercard", 500000.0, 150000.0)
        assertEquals(0.0, commission)
    }

    @Test
    fun testDailyLimitExceeded() {
        val commission = calculateCommission("Mastercard", 0.0, 150001.0)
        assertEquals(0.0, commission)
    }

}
