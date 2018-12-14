package org.hrv.web.service

import org.hrv.web.domain.ReadAccount
import org.hrv.web.domain.WriteAccount
import org.hrv.web.service.database.DataBaseService

class AccountService(private val dataBaseService: DataBaseService) {

    suspend fun getAllAccounts(): List<ReadAccount> = dataBaseService.getAllAccounts()

    suspend fun getAccountById(id: Int): ReadAccount? = dataBaseService.getAccountById(id)

    suspend fun createAccount(account: WriteAccount): ReadAccount = dataBaseService.createAccount(account)

    suspend fun updateAccount(account: WriteAccount, id: Int): ReadAccount? = dataBaseService.updateAccount(account, id)

    suspend fun deleteAccount(id: Int): Boolean = dataBaseService.deleteAccount(id)
}