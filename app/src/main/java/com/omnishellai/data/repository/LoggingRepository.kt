package com.omnishellai.data.repository

import com.omnishellai.data.db.LogDao
import com.omnishellai.data.db.LogEntry
import kotlinx.coroutines.flow.Flow

class LoggingRepository(private val logDao: LogDao) {
    val allLogs: Flow<List<LogEntry>> = logDao.getAllLogs()

    suspend fun insert(log: LogEntry) {
        logDao.insert(log)
    }

    suspend fun clearAll() {
        logDao.clearLogs()
    }
}
