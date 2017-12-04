package day4

object PasswordValidator {

    fun numberOfValidPasswords(passwords: List<String>): Int {
        fun isValid(password: String): Boolean {
            tailrec fun isValid(phrase: String, phrases: List<String>): Boolean {
                if (phrases.isEmpty()) {
                    return true
                }
                val duplicatedPhrase = phrases.find { it == phrase }
                when (duplicatedPhrase) {
                    null -> return isValid(phrases[0], phrases.drop(1))
                    else -> return false
                }
            }

            val phrases = password.split(" ")
            return isValid(phrases[0], phrases.drop(1))
        }

        return passwords.filter { isValid(it) }.count()
    }

    fun numberOfValidPasswordsWithAnagrams(passwords: List<String>): Int {
        fun isValid(password: String): Boolean {
            fun isAnagram(phrase1: String, phrase2: String): Boolean {
                return phrase1.length == phrase2.length
                        && phrase1.toSortedSet() == phrase2.toSortedSet()
            }

            tailrec fun isValid(phrase: String, phrases: List<String>): Boolean {
                if (phrases.isEmpty()) {
                    return true
                }
                val duplicatedPhrase = phrases.find { it == phrase }
                val invalidPhrase = duplicatedPhrase ?: phrases.find { isAnagram(it, phrase) }
                when (invalidPhrase) {
                    null -> return isValid(phrases[0], phrases.drop(1))
                    else -> return false
                }
            }

            val phrases = password.split(" ")
            return isValid(phrases[0], phrases.drop(1))
        }

        return passwords.filter { isValid(it) }.count()
    }
}