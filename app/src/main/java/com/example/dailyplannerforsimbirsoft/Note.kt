package com.example.dailyplannerforsimbirsoft

import java.io.Serializable
import java.sql.Timestamp

data class Note (
    val id : Int,
    val dateStart : Timestamp,
    val dateFinish : Timestamp,
    val name : String,
    val description: String
        ) : Serializable