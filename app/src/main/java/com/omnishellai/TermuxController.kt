package com.omnishellai

import com.chaquo.python.Python

class TermuxController {
    fun runCommand(command: String): String {
        val py = Python.getInstance()
        val pyObj = py.getModule("TermuxModule")
        return pyObj.callAttr("run_command", command).toString()
    }
}
