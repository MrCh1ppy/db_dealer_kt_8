package com.example.db_dealer_kt_8.task


import com.example.db_dealer_kt_8.service.TaskService
import com.example.db_dealer_kt_8.utils.const_value.Const
import com.example.db_dealer_kt_8.utils.log.Slf4j
import com.example.db_dealer_kt_8.utils.log.Slf4j.Companion.log
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.runBlocking
import org.springframework.beans.factory.InitializingBean
import org.springframework.stereotype.Component
import java.io.IOException
import java.nio.file.Files
import java.util.concurrent.TimeUnit

@Component
@Slf4j
class Task constructor(
    val taskService: TaskService
):InitializingBean{

    override fun afterPropertiesSet(){
        log.info("start")
        try {
            if (!Const.prefix.toFile().exists()) {
                Files.createDirectory(Const.prefix)
            }
        } catch (e: IOException) {
            log.info("创建prefix失败")
        }
        runBlocking {
            doTask()
        }
    }

    suspend fun doTask()= coroutineScope {
        val charSet = Const.CHAR_SET.value
        val doneSet = Const.DONE_SET
        val futureList = mutableListOf<Deferred<String>>()
        for (first in charSet.iterator()) {
            if(doneSet.contains(first)){
                continue
            }
            for ((j, second) in charSet.slice(0 until charSet.size - 1).iterator().withIndex()) {
                val temp = charSet[j + 1]
                if('0'==temp||'A'==temp||'a'==temp){
                    continue
                }
                val left = first.toString() + second.toString()
                val right = first.toString() + (second.code+1).toChar().toString()
                val textFuture = async { doService(left, right) }
                futureList.add(textFuture)
            }
        }
        futureList.map { log.info(it.await()) }
    }

    suspend fun doService(left:String, right:String):String{
        val start = System.currentTimeMillis()
        val (file, num) = taskService.doService(left, right)
        if (num != 0 && file != null) {
            val end = System.currentTimeMillis() - start
            val seconds = TimeUnit.MILLISECONDS.toSeconds(end)
            return "from $left to $right is done,has write $num records and it takes $seconds second,file path located in  ${file.absoluteFile}"
        }
        return ""
    }


}