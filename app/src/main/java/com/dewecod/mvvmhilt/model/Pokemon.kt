package com.dewecod.mvvmhilt.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pokemon")
data class Pokemon(
    @PrimaryKey(autoGenerate = true)
    private val id: Int,
    private val name: String,
    private val url: String
)