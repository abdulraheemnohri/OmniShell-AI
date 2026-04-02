package com.omnishellai

import org.junit.Test
import org.junit.Assert.*

class ModuleLogicTest {

    @Test
    fun testControllersNotNull() {
        val termuxController = TermuxController()
        val openClawController = OpenClawController()
        val ollamaController = OllamaController()

        assertNotNull(termuxController)
        assertNotNull(openClawController)
        assertNotNull(ollamaController)
    }
}
