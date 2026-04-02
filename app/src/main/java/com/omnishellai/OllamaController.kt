package com.omnishellai

import com.chaquo.python.Python

class OllamaController {
    fun runModel(modelName: String, prompt: String): String {
        val py = Python.getInstance()
        val pyObj = py.getModule("OllamaModule")
        return pyObj.callAttr("run_model", modelName, prompt).toString()
    }
}
