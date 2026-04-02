package com.omnishellai

import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.chaquo.python.Python
import com.chaquo.python.android.AndroidPlatform
import com.omnishellai.data.db.AppDatabase
import com.omnishellai.data.db.LogEntry
import com.omnishellai.data.repository.LoggingRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class DashboardActivity : AppCompatActivity() {

    private lateinit var logView: TextView
    private val termuxController = TermuxController()
    private val openClawController = OpenClawController()
    private val ollamaController = OllamaController()
    private lateinit var repository: LoggingRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        // Initialize Repository
        val database = AppDatabase.getDatabase(this)
        repository = LoggingRepository(database.logDao())

        // Initialize Python
        if (!Python.isStarted()) {
            Python.start(AndroidPlatform(this))
        }

        logView = findViewById(R.id.log_view)
        val btnTermux = findViewById<Button>(R.id.btn_termux)
        val btnOpenClaw = findViewById<Button>(R.id.btn_openclaw)
        val btnOllama = findViewById<Button>(R.id.btn_ollama)

        btnTermux.setOnClickListener {
            executeTask("TERMUX") { termuxController.runCommand("ls -la") }
        }

        btnOpenClaw.setOnClickListener {
            executeTask("OPENCLAW") { openClawController.executeTask("Example Automation Task") }
        }

        btnOllama.setOnClickListener {
            executeTask("OLLAMA") { ollamaController.runModel("llama3", "Tell me a joke about robots.") }
        }

        // Observe logs
        lifecycleScope.launch {
            repository.allLogs.collect { logs ->
                displayLogs(logs)
            }
        }
    }

    private fun executeTask(module: String, task: () -> String) {
        lifecycleScope.launch {
            val result = withContext(Dispatchers.IO) {
                try {
                    val output = task()
                    LogEntry(
                        timestamp = System.currentTimeMillis(),
                        level = "SUCCESS",
                        module = module,
                        message = output
                    )
                } catch (e: Exception) {
                    LogEntry(
                        timestamp = System.currentTimeMillis(),
                        level = "ERROR",
                        module = module,
                        message = e.message ?: "Unknown Error"
                    )
                }
            }
            repository.insert(result)
        }
    }

    private fun displayLogs(logs: List<LogEntry>) {
        val dateFormat = SimpleDateFormat("HH:mm:ss", Locale.getDefault())

        logView.text = ""

        for (log in logs) {
            val time = dateFormat.format(Date(log.timestamp))
            val statusEmoji = when (log.level) {
                "SUCCESS" -> "✅"
                "ERROR" -> "❌"
                "WARNING" -> "⚠️"
                else -> "ℹ️"
            }

            val header = "[$time] $statusEmoji ${log.module}: "
            val message = "${log.message}\n\n"

            val spannable = SpannableString(header + message)
            val color = when (log.level) {
                "SUCCESS" -> Color.GREEN
                "ERROR" -> Color.RED
                "WARNING" -> Color.YELLOW
                else -> Color.WHITE
            }

            val startIndex = header.indexOf(statusEmoji)
            if (startIndex != -1) {
                spannable.setSpan(
                    ForegroundColorSpan(color),
                    startIndex,
                    header.length,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
            }

            logView.append(spannable)
        }
    }
}
