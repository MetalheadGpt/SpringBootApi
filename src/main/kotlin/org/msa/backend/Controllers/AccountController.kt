package org.msa.backend.Controllers

import org.msa.backend.Dto.AccountDTO
import org.msa.backend.Services.AccountService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/accounts")
@CrossOrigin(origins = ["*"])
class AccountController(private val accountService: AccountService) {

    @GetMapping
    fun getAllAccounts(): ResponseEntity<List<AccountDTO>> =
        ResponseEntity.ok(accountService.getAllAccounts())

    @GetMapping("/{id}")
    fun getAccountById(@PathVariable id: Long): ResponseEntity<AccountDTO> {
        val account = accountService.getAccountById(id)
        return ResponseEntity.ok(account)
    }

    @PostMapping
    fun createAccount(@RequestBody accountDTO: AccountDTO): ResponseEntity<AccountDTO> {
        val createdAccount = accountService.createAccount(accountDTO)
        return ResponseEntity.status(HttpStatus.CREATED).body(
            AccountDTO(createdAccount.id, createdAccount.username)
        )
    }

    @PutMapping("/{id}")
    fun updateAccount(@PathVariable id: Long, @RequestBody accountDTO: AccountDTO): ResponseEntity<AccountDTO> {
        val updatedAccount = accountService.updateAccount(id, accountDTO)
        return ResponseEntity.ok(updatedAccount)
    }

    @DeleteMapping("/{id}")
    fun deleteAccount(@PathVariable id: Long): ResponseEntity<Void> {
        accountService.deleteAccount(id)
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build()
    }
}
