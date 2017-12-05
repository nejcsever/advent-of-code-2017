#!/bin/bash

SCRIPT_DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"

# Insert day as argument
DAY_NO=$1
if [ -z ${DAY_NO} ]; then
    echo "Please insert a day number"
    exit 1
fi

# Source
src_dir="${SCRIPT_DIR}/src/day${DAY_NO}"
mkdir -p "${src_dir}"
cat <<EOF > "${src_dir}/Day${DAY_NO}.kt"
package day${DAY_NO}

fun main(args: Array<String>) {
    val input = Object::class.java.getResource("/day${DAY_NO}/input.txt").readText()
    println("Part 1: \${part1(input)}")
    println("Part 2: \${part2(input)}")
}

fun part1(input: String): String {
    return input
}

fun part2(input: String): String {
    return input
}

EOF

# Input
resources_dir="${SCRIPT_DIR}/resources/day${DAY_NO}"
mkdir -p "${resources_dir}"
cat <<EOF > "${resources_dir}/input.txt"
input day ${DAY_NO}
EOF