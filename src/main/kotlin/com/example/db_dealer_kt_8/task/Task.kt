package com.example.db_dealer_kt_8.task


import com.example.db_dealer_kt_8.service.TaskService
import com.example.db_dealer_kt_8.utils.const_value.Const
import com.example.db_dealer_kt_8.utils.log.Slf4j
import com.example.db_dealer_kt_8.utils.log.Slf4j.Companion.log
import org.springframework.beans.factory.InitializingBean
import org.springframework.stereotype.Component
import java.io.IOException
import java.nio.file.Files
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

@Component
@Slf4j
class Task constructor(
    val taskService: TaskService
):InitializingBean{

    override fun afterPropertiesSet() {
        log.info("start")
        val pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors())!!
        try {
            if (!Const.prefix.toFile().exists()) {
                Files.createDirectory(Const.prefix)
            }
        } catch (e: IOException) {
            log.info("创建prefix失败")
        }
        val charSet = Const.CHAR_SET.value
        val doneSet = Const.DONE_SET
        for (first in charSet.iterator()) {
            if(doneSet.contains(first)){
                continue
            }
            for ((j, second) in charSet
                .slice(0 until charSet.size - 1)
                .iterator()
                .withIndex()) {
                val temp = charSet[j + 1]
                if('0'==temp||'A'==temp||'a'==temp){
                    continue
                }
                val left = first.toString() + second.toString()
                val right = first.toString() + (second.code+1).toChar().toString()
                pool.execute {
                    val start = System.currentTimeMillis()
                    val (file,num) = taskService.doService(left, right)
                    if(num!=0&&file!=null){
                        val end = System.currentTimeMillis() - start
                        val seconds = TimeUnit.MILLISECONDS.toSeconds(end)
                        log.info("from $left to $right is done , has write $num records and it takes $seconds second,file path located in  ${file.absoluteFile}")
                    }
                }
            }
        }
    }


}