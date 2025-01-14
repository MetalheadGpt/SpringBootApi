package org.msa.backend.Entities
import jakarta.persistence.*
import org.msa.backend.Dto.ChatDTO

@Entity
data class Chat(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    val textBoxInputs: String = " ",
    @Column(length = 10000000)
    val response: String = " ",

    @Enumerated(EnumType.STRING)
    val category: CategoryEnum? = null,

    @Enumerated(EnumType.STRING)
    val subCategory: SubCategoryEnum? = null,

    @Enumerated(EnumType.STRING)
    val bands: BandsEnum? = null,

    @ManyToOne
    val account: Account? = null,
) {
    fun toDTO(): ChatDTO = ChatDTO(
        id = this.id,
        response = this.response,
        category = this.category,
        subCategory = this.subCategory,
        bands = this.bands,
        accountId = this.account?.id
    )
}

