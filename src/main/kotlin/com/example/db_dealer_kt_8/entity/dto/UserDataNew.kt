package com.example.db_dealer_kt_8.entity.dto

import com.example.db_dealer_kt_8.entity.po.UserData
import com.example.db_dealer_kt_8.sz.CodingUtils

data class UserDataNew constructor(
    val vaccinationDate: String? = null,
    val vaccinationId: String? = null,
    val vaccineCode: String? = null,
    val idCardNo: String? = null,
    val name: String? = null,
    val idCardType: String? = null,
    val vaccineSeq: Int? = null,
    val sfjzwc: String? = null,
    val pushTime: String? = null,
    val etlTime: String? = null,
    val option1: String? = null,
    val recordStatus: String? = null,
    val lastUpdateTime: String? = null,
    val vaccinationOrgCode:String?=null,
    val parsedName:String?=null,
    val parsedId:String?=null
){
    companion object Builder{
        fun of(userData: UserData): UserDataNew {
            return UserDataNew(
                vaccinationDate = userData.vaccinationDate,
                vaccinationId = userData.vaccinationId,
                vaccineCode = userData.vaccineCode,
                idCardNo = userData.idCardNo,
                name = userData.name,
                idCardType = userData.idCardType,
                vaccineSeq = userData.vaccineSeq,
                sfjzwc = userData.sfjzwc,
                pushTime = userData.pushTime,
                etlTime = userData.etlTime,
                option1 = userData.option1,
                recordStatus = userData.recordStatus,
                lastUpdateTime = userData.lastUpdateTime,
                parsedName = CodingUtils.codeDeal(userData.name),
                parsedId = CodingUtils.codeDeal(userData.idCardNo)
            )
        }
    }

    override fun toString(): String {
        return "$vaccinationDate,$vaccinationId,$vaccineCode,$idCardNo,$name,$idCardType,$vaccineSeq,$sfjzwc,$pushTime,$etlTime,$option1,$recordStatus,$lastUpdateTime,$parsedName,$parsedId"
    }
}





