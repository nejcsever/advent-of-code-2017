package day1

object InverseCaptcha {
    fun solve(captchaInput: String, digitSpacing: Int): Int {
        return captchaInput.filterIndexed { i, value -> value == captchaInput[(i + digitSpacing) % captchaInput.length] }
                .sumBy { value -> Character.getNumericValue(value) }
    }
}