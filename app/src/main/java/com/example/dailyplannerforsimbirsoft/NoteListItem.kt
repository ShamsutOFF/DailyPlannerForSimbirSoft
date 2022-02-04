package com.example.dailyplannerforsimbirsoft

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import java.sql.Timestamp
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun NoteListItem(note: Note, selectedItem: (Note) -> Unit) {
    Card(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(),
        elevation = 10.dp,
        shape = RoundedCornerShape(corner = CornerSize(10.dp))
    ) {
        val myTimeFormat: DateFormat = SimpleDateFormat("HH:mm")
        Row(
            modifier = Modifier
                .padding(5.dp)
                .fillMaxSize()
                .clickable { selectedItem(note) }
        ) {
            Column(
                modifier = Modifier
                    .padding(4.dp)
                    .height(70.dp)
                    .weight(1f), verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = myTimeFormat.format(note.dateStart),
                    style = MaterialTheme.typography.h5
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = myTimeFormat.format(note.dateFinish),
                    style = MaterialTheme.typography.h5
                )

            }
            Column(
                modifier = Modifier
                    .padding(4.dp)
                    .height(70.dp)
                    .weight(3f), verticalArrangement = Arrangement.Center
            ) {
                Text(text = note.name, style = MaterialTheme.typography.h5, maxLines = 1)
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = note.description,
                    style = MaterialTheme.typography.h6,
                    maxLines = 1
                )
            }
        }
    }

}