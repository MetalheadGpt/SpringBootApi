package org.msa.backend.Services

import jakarta.persistence.EntityNotFoundException
import org.msa.backend.Dto.ChatDTO
import org.msa.backend.Dto.UpdateEnumsDTO
import org.msa.backend.Entities.Chat
import org.msa.backend.Repositories.AccountRepository
import org.msa.backend.Repositories.ChatRepository
import org.springframework.stereotype.Service

@Service
class ChatService(
    private val chatRepository: ChatRepository,
    private val accountRepository: AccountRepository
) {

    fun getAllChats(): List<Chat> = chatRepository.findAll()

    fun getChatById(id: Long): Chat =
        chatRepository.findById(id).orElseThrow { EntityNotFoundException("Chat with ID $id not found") }

    fun createChat(chatDTO: ChatDTO): Chat {
        val account = chatDTO.accountId?.let { accountRepository.findById(it).orElse(null) }
        val chat = chatDTO.toEntity(account)
        return chatRepository.save(chat)
    }

    fun updateChat(id: Long, chatDTO: ChatDTO): Chat {
        val existingChat = getChatById(id)
        val account = chatDTO.accountId?.let { accountRepository.findById(it).orElse(null) }
        val updatedChat = existingChat.copy(
            response = chatDTO.response,
            category = chatDTO.category,
            subCategory = chatDTO.subCategory,
            bands = chatDTO.bands,
            account = account
        )
        return chatRepository.save(updatedChat)
    }

    fun deleteChat(id: Long) {
        if (!chatRepository.existsById(id)) {
            throw EntityNotFoundException("Chat with ID $id does not exist")
        }
        chatRepository.deleteById(id)
    }

    fun updateResponse(id: Long, newResponse: String): Chat {
        val chat = getChatById(id)
        val updatedChat = chat.copy(response = newResponse)
        return chatRepository.save(updatedChat)
    }

    fun updateEnums(id: Long, updateEnumsDTO: UpdateEnumsDTO): Chat {
        val chat = getChatById(id)
        val updatedChat = chat.copy(
            category = updateEnumsDTO.category ?: chat.category,
            subCategory = updateEnumsDTO.subCategory ?: chat.subCategory,
            bands = updateEnumsDTO.bands ?: chat.bands
        )
        return chatRepository.save(updatedChat)
    }
}
