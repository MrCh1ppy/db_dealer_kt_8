package com.example.db_dealer_kt_8.utils.const_value

import java.nio.file.Path
import java.nio.file.Paths

object Const {
    const val NUM = 10000
    val prefix: Path = Paths.get("data")
    val CHAR_SET = lazy{
        val code0 = '0'.code
        val list = mutableListOf<Char>()
        for (i in 0 until  10){
            list.add((code0+i).toChar())
        }
        val codeA = 'A'.code
        for (i in 0 until 26){
            list.add((codeA+i).toChar())
        }
        /*val codea = 'a'.code
        for (i in 0 until 26){
            list.add((codea+i).toChar())
        }*/
        list.toCharArray()
    }
    val DONE_SET= setOf<Char>()
}