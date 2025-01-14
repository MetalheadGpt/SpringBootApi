package org.msa.backend.Dto

import org.msa.backend.Entities.Account
import org.msa.backend.Entities.CategoryEnum
import org.msa.backend.Entities.SubCategoryEnum
import org.msa.backend.Entities.BandsEnum
import org.msa.backend.Entities.Chat

data class ChatDTO(
    val id: Long = 0,
    val response: String = " ",
    val category: CategoryEnum? = null,
    val subCategory: SubCategoryEnum? = null,
    val bands: BandsEnum? = null,
    val accountId: Long? = null // Reference to Account
) {
    fun toEntity(account: Account?): Chat = Chat(
        id = this.id,
        response = this.response,
        category = this.category,
        subCategory = this.subCategory,
        bands = this.bands,
        account = account
    )
}
