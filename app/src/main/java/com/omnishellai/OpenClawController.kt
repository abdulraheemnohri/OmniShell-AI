package com.omnishellai

import com.chaquo.python.Python

class OpenClawController {
    fun executeTask(taskName: String): String {
        val py = Python.getInstance()
        val pyObj = py.getModule("OpenClawModule")
        return pyObj.callAttr("execute_task", taskName).toString()
    }
}
