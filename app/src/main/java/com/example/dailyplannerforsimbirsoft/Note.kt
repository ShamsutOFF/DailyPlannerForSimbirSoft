package com.example.dailyplannerforsimbirsoft

import java.io.Serializable

data class Note (
    val id : Int,
    val dateStart : Long,
    val dateFinish : Long,
    val name : String,
    val description: String
        ) : Serializable