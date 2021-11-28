package com.mfcc.hilt.persistence.movies

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.RoomWarnings

@SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
@Entity(tableName = "MOVIES")
class MovieEntity (

    @ColumnInfo(name = "id") @PrimaryKey
    var id: Int? = null,

    @ColumnInfo(name = "title")
    var title: String? = null,

    @ColumnInfo(name = "overview")
    var overview: String? = null,

    @ColumnInfo(name = "poster_path")
    var poster_path: String? = null,

    @ColumnInfo(name = "original_language")
    var original_language: String? = null,

    @ColumnInfo(name = "release_date")
    var release_date: String? = null
)