package org.hrv.web.service

import io.mockk.*
import kotlinx.coroutines.runBlocking
import org.hrv.web.domain.ReadAccount
import org.hrv.web.service.database.DataBaseService
import org.junit.Test

class AccountServiceTest {

    private fun dataSource(): List<ReadAccount> {
        val readAccountOne = ReadAccount(id = 1, name="AccountOne")
        val readAccountTwo = ReadAccount(id = 2, name="AccountTwo")
        return arrayListOf(readAccountOne, readAccountTwo)
    }

    @Test
    fun getAllAccountsReturnsDataSource(){
        val mockDataBaseService = mockk<DataBaseService>()
        coEvery { mockDataBaseService.getAllAccounts() } returns dataSource()

        runBlocking {
            val realList = AccountService(mockDataBaseService).getAllAccounts()

            assert(realList == dataSource())
        }
    }
}