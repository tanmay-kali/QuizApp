package edu.tcu.tanmaykejriwal.quiz

data class Question(
    val question: String,
    val image: Int,
    val options: List<String>,
    val correctAnswer: String
)