package org.msa.backend.Services

import jakarta.persistence.EntityNotFoundException
import org.msa.backend.Entities.Account
import org.msa.backend.Repositories.AccountRepository
import org.springframework.stereotype.Service
import org.msa.backend.Dto.AccountDTO

@Service
class AccountService(
    private val accountRepository: AccountRepository
) {

    fun getAllAccounts(): List<AccountDTO> = accountRepository.findAll().map { it.toDTO() }

    fun getAccountById(id: Long): AccountDTO = accountRepository.findById(id)
        .orElseThrow { EntityNotFoundException("Account with ID $id not found") }
        .toDTO()

    fun createAccount(accountDTO: AccountDTO): Account {
        // Removed profilePicture logic
        val account = Account(
            username = accountDTO.username,
            password = "" // Password should be handled separately
        )
        return accountRepository.save(account)
    }

    fun updateAccount(id: Long, accountDTO: AccountDTO): AccountDTO {
        val existingAccount = accountRepository.findById(id)
            .orElseThrow { EntityNotFoundException("Account with ID $id not found") }

        // Removed profilePicture logic
        val updatedAccount = existingAccount.copy(
            username = accountDTO.username
        )
        return accountRepository.save(updatedAccount).toDTO()
    }

    fun deleteAccount(id: Long) {
        if (!accountRepository.existsById(id)) {
            throw EntityNotFoundException("Account with ID $id does not exist")
        }
        accountRepository.deleteById(id)
    }

    private fun Account.toDTO(): AccountDTO = AccountDTO(
        id = this.id,
        username = this.username
        // profilePicture is removed from the DTO
    )
}
