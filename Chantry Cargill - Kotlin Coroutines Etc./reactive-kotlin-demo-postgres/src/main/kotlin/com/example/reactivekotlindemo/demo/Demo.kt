package com.example.reactivekotlindemo.demo

sealed class DemoResponse

data class Demo(val id: Int?, val name: String) : DemoResponse()

class DemoError(val error: DemoErrorType, val message: String?) : DemoResponse()

enum class DemoErrorType {
    DEMO_DOES_NOT_EXIST
}