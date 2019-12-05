package com.br.natanfelipe.bitcoinapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "bitcoinInfo")
data class Bitcoin (
    @ColumnInfo
    val name: String,
    @ColumnInfo
    val unit: String,
    @ColumnInfo
    val period: String,
    @ColumnInfo
    val description: String,
    @ColumnInfo
    val values: List<Values>
)

data class Values (val x: Long, val y: Double)