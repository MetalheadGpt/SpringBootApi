package org.msa.backend.Controllers

import org.msa.backend.Dto.ChatDTO
import org.msa.backend.Dto.ResponseDTO
import org.msa.backend.Dto.UpdateEnumsDTO
import org.msa.backend.Services.ChatService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/chats")
@CrossOrigin(origins = ["*"])
class ChatController(private val chatService: ChatService) {

    @GetMapping
    fun getAllChats(): ResponseEntity<List<ChatDTO>> =
        ResponseEntity.ok(chatService.getAllChats().map { it.toDTO() })

    @GetMapping("/{id}")
    fun getChatById(@PathVariable id: Long): ResponseEntity<ChatDTO> =
        ResponseEntity.ok(chatService.getChatById(id).toDTO())

    @PostMapping
    fun createChat(@RequestBody chatDTO: ChatDTO): ResponseEntity<ChatDTO> {
        val createdChat = chatService.createChat(chatDTO)
        return ResponseEntity.status(HttpStatus.CREATED).body(createdChat.toDTO())
    }

    @PutMapping("/{id}")
    fun updateChat(@PathVariable id: Long, @RequestBody updatedChatDTO: ChatDTO): ResponseEntity<ChatDTO> {
        val updatedChat = chatService.updateChat(id, updatedChatDTO)
        return ResponseEntity.ok(updatedChat.toDTO())
    }

    @DeleteMapping("/{id}")
    fun deleteChat(@PathVariable id: Long): ResponseEntity<Void> {
        chatService.deleteChat(id)
        return ResponseEntity.noContent().build()
    }

    @PatchMapping("/{id}/response")
    fun updateResponse(@PathVariable id: Long, @RequestBody responseDTO: ResponseDTO): ResponseEntity<ChatDTO> {
        val updatedChat = chatService.updateResponse(id, responseDTO.response)
        return ResponseEntity.ok(updatedChat.toDTO())
    }

    @PatchMapping("/{id}/enums")
    fun updateEnums(@PathVariable id: Long, @RequestBody updateEnumsDTO: UpdateEnumsDTO): ResponseEntity<ChatDTO> {
        val updatedChat = chatService.updateEnums(id, updateEnumsDTO)
        return ResponseEntity.ok(updatedChat.toDTO())
    }
}
