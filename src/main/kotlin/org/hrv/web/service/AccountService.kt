package org.hrv.web.service

import org.hrv.web.domain.ReadAccount
import org.hrv.web.domain.WriteAccount
import org.hrv.web.service.database.DataBaseService

class AccountService {

    suspend fun getAllAccounts(): List<ReadAccount> = DataBaseService().getAllAccounts()

    suspend fun getAccountById(id: Int): ReadAccount? = DataBaseService().getAccountById(id)

    suspend fun createAccount(account: WriteAccount): ReadAccount = DataBaseService().createAccount(account)

    suspend fun updateAccount(account: WriteAccount, id: Int): ReadAccount? = DataBaseService().updateAccount(account, id)

    suspend fun deleteAccount(id: Int): Boolean = DataBaseService().deleteAccount(id)
}