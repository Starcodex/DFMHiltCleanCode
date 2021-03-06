package com.mfcc.hilt.persistence.movies

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "CATEGORY")
class CategoryEntity (
    @ColumnInfo(name = "name") @PrimaryKey var name: String = ""
)