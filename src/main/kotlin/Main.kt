fun calculateCommission(
    cardType: String = "Мир",
    totalPreviousTransfers: Double = 0.0,
    transferAmount: Double
): Double {
    val dailyLimit = 150000.0
    val monthlyLimit = 600000.0
    val mastercardMonthlyLimit = 75000.0
    val mastercardCommissionLimit = 0.006
    val mastercardCommissionFixed = 20.0
    val visaCommissionRate = 0.0075
    val visaCommissionMin = 35.0

    val remainingMonthlyLimit = when (cardType) {
        "Mastercard" -> mastercardMonthlyLimit - totalPreviousTransfers
        else -> Double.POSITIVE_INFINITY
    }

    if (transferAmount > dailyLimit) {
        println("Превышен дневной лимит на переводы")
        return 0.0
    }

    if (totalPreviousTransfers + transferAmount > monthlyLimit) {
        println("Превышен месячный лимит на переводы")
        return 0.0
    }

    return when (cardType) {
        "Mastercard" -> {
            if (remainingMonthlyLimit <= 0) {
                transferAmount * mastercardCommissionLimit + mastercardCommissionFixed
            } else {
                val remainingAmount = transferAmount - remainingMonthlyLimit
                if (remainingAmount > 0) {
                    remainingAmount * mastercardCommissionLimit + mastercardCommissionFixed
                } else {
                    0.0
                }
            }
        }

        "Visa" -> {
            val visaCommissionAmount = transferAmount * visaCommissionRate
            visaCommissionAmount.coerceAtLeast(visaCommissionMin)
        }

        "Мир" -> 0.0
        else -> {
            println("Неверный тип карты")
            0.0
        }
    }
}

fun main() {
    val commission1 = calculateCommission("Mastercard", 50000.0, 100000.0)
    println("Комиссия для Mastercard: $commission1 рублей")

    val commission2 = calculateCommission("Visa", 200000.0, 50000.0)
    println("Комиссия для Visa: $commission2 рублей")

    val commission3 = calculateCommission("Мир", 300000.0, 70000.0)
    println("Комиссия для Мир: $commission3 рублей")
}