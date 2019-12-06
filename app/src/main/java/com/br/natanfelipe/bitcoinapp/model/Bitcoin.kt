package com.br.natanfelipe.bitcoinapp.model

import androidx.room.*

data class Bitcoin(
    val name: String,
    val unit: String,
    val period: String,
    val description: String,
    val values: List<Values>
)

@Entity
data class Values(
    val x: Long,
    val y: Double
) {
    @PrimaryKey(autoGenerate = true)
    var valueId: Int = 0
}