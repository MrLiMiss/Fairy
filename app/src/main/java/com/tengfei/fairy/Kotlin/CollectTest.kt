package com.tengfei.fairy.Kotlin

/**
 * @Description :集合相关测试
 * @Auther tengfei.li
 * @Since 2022/8/4 16:42
 * @Version 1.0
 */
class CollectTest {
}

fun List<String>.getShortWordsTo(shortWords: MutableList<String>, maxLength: Int) {
    this.filterTo(shortWords) { it.length <= maxLength }
    // throwing away the articles
    val articles = setOf("a", "A", "an", "An", "the", "The")
    shortWords -= articles
}

fun main() {
    val words = "A long time ago in a galaxy far far away".split(" ")
    val shortWords = mutableListOf<String>()
    words.getShortWordsTo(shortWords, 3)
    println(shortWords)
}