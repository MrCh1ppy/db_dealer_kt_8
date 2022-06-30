package com.example.db_dealer_kt_8.service

import com.example.db_dealer_kt_8.entity.dto.DealInfo

interface TaskService {
    fun doService(left: String, right: String): DealInfo
}