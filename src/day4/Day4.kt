package day4

fun main(args: Array<String>) {
    val input = Object::class.java.getResource("/day4/input.txt").readText()
    val passwords = input.split("\n")
    println("Part 1: ${PasswordValidator.numberOfValidPasswords(passwords)}")
    println("Part 2: ${PasswordValidator.numberOfValidPasswordsWithAnagrams(passwords)}")
}