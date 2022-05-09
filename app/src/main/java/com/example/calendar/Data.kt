package com.example.calendar

data class Data(
    val name: String = "",
    val events: List<Events> = emptyList()
)

data class Events(
    val name: String = "",
    val link: String = ""
)