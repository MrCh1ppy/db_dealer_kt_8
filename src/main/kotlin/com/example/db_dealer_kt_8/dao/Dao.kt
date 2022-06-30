package com.example.db_dealer_kt_8.dao

import com.example.db_dealer_kt_8.entity.po.UserData
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param
import java.util.Optional

@Mapper
interface Dao {
    fun selectRecord(
        @Param("left") left: String,
        @Param("right") right: String,
        @Param("start") start: Int
    ): List<UserData?>?

    fun selectRecordOne(
        @Param("left") left: String,
        @Param("right") right: String
    ):Optional<UserData>
}