package org.hrv.web.service

import io.mockk.*
import kotlinx.coroutines.runBlocking
import org.hrv.web.domain.ReadAccount
import org.hrv.web.domain.WriteAccount
import org.hrv.web.service.database.DataBaseService
import org.junit.Test

class AccountServiceTest {

    private val mockDataBaseService = mockk<DataBaseService>()

    private fun dataSource(): List<ReadAccount> {
        val readAccountOne = ReadAccount(id = 1, name="AccountOne")
        val readAccountTwo = ReadAccount(id = 2, name="AccountTwo")
        return arrayListOf(readAccountOne, readAccountTwo)
    }

    @Test
    fun getAllAccountsReturnsDataSource(){
        coEvery { mockDataBaseService.getAllAccounts() } returns dataSource()

        runBlocking {
            val realList = AccountService(mockDataBaseService).getAllAccounts()

            assert(realList == dataSource())
        }
    }

    @Test
    fun getAccountByIdReturnsAccountOne(){
        val id = 1
        val fakeAccount = dataSource().find { readAccount -> readAccount.id == id }!!

        coEvery { mockDataBaseService.getAccountById(id) } returns fakeAccount

        runBlocking {
            val realAccount = AccountService(mockDataBaseService).getAccountById(id)!!

            assert(realAccount == fakeAccount)
        }
    }

    @Test
    fun createAccountReturnsAccount(){
        val writeAccount = WriteAccount(name = "TestAccount")
        val fakeAccount = ReadAccount(id = 3, name = "TestAccount")

        coEvery{ mockDataBaseService.createAccount(writeAccount) } returns fakeAccount

        runBlocking {
            val realAccount = AccountService(mockDataBaseService).createAccount(writeAccount)
            assert(realAccount == fakeAccount)
        }
    }

    @Test
    fun updateAccountReturnsAccount(){
        val id = 1
        val writeAccount = WriteAccount(name = "TestAccount")
        val fakeAccount = ReadAccount(id = id, name = "TestAccount")

        coEvery { mockDataBaseService.updateAccount(account = writeAccount, id = id) } returns fakeAccount

        runBlocking {
            val realAccount = AccountService(mockDataBaseService).updateAccount(account = writeAccount, id = id)
            assert(realAccount == fakeAccount)
        }
    }

    @Test
    fun deleteAccountReturnsTrue(){
        val id = 1

        coEvery{ mockDataBaseService.deleteAccount(id) } returns true

        runBlocking {
            val deleteResult = AccountService(mockDataBaseService).deleteAccount(id)
            assert(deleteResult)
        }
    }
}