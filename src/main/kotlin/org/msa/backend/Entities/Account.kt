package org.msa.backend.Entities
import jakarta.persistence.*

@Entity
data class Account(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val username: String ="",
    val password: String =" ",
    @OneToMany(mappedBy = "account", cascade = [CascadeType.ALL])
    val chats: List<Chat> = listOf()
)
