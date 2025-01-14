package org.msa.backend.Repositories

import org.msa.backend.Entities.Account
import org.springframework.data.jpa.repository.JpaRepository

interface AccountRepository : JpaRepository<Account, Long>