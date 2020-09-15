package br.com.hellen.core

import java.lang.Exception

open class Response(private val message:String = "")

data class ResponseSuccess(val data:Any, val message:String): Response(message)

data class ResponseFail(val data:Any? = null, val message:String, val exception: Exception? = null): Response(message)