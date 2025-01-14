package org.msa.backend.Repositories
import org.msa.backend.Entities.Chat
import org.springframework.data.jpa.repository.JpaRepository

interface ChatRepository : JpaRepository<Chat, Long>