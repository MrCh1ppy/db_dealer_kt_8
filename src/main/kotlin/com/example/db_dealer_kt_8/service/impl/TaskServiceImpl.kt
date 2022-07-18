package com.example.db_dealer_kt_8.service.impl

import com.example.db_dealer_kt_8.service.TaskService
import com.example.db_dealer_kt_8.dao.Dao
import com.example.db_dealer_kt_8.entity.dto.DealInfo
import com.example.db_dealer_kt_8.entity.dto.UserDataNew
import com.example.db_dealer_kt_8.utils.const_value.Const
import com.example.db_dealer_kt_8.utils.log.Slf4j
import org.springframework.stereotype.Service
import java.io.FileOutputStream
import java.nio.charset.StandardCharsets
import java.util.*

@Service
@Slf4j
class TaskServiceImpl constructor(val dao: Dao) : TaskService {


    override fun doService(left: String, right: String): DealInfo {
        val temp = left.substring(1)
        if("9"==temp||"Z"==temp||"z"==temp){
            return DealInfo(null,0)
        }
        val fileName = if (left.toCharArray()[0] in 'a'.. 'z'){ "_$left" }else{ left }
        val one = dao.selectRecordOne(left, right)
        if(!one.isPresent){
            return DealInfo(null,0)
        }
        val file = Const.prefix.resolve("$fileName.txt").toFile()
        FileOutputStream(file,true).use {
            var limit = 1
            while (true){
                val record = dao.selectRecord(left, right, (limit - 1) * Const.NUM)?: emptyList()
                val vos = record.filterNotNull()
                    .map { cur -> UserDataNew.of(cur) }
                    .map { cur->cur.toString() }
                    .fold(StringJoiner("\n")){acc, s -> acc.add(s) }
                val text=vos.toString()+"\n"
                it.write(text.toByteArray(StandardCharsets.UTF_8))
                if (record.size < Const.NUM) {
                    return DealInfo(file,(limit - 1) * Const.NUM + record.size )
                }
                limit+=1
            }
        }
    }

}