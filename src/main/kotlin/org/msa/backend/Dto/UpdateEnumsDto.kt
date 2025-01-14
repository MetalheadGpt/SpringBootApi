package org.msa.backend.Dto

import org.msa.backend.Entities.CategoryEnum
import org.msa.backend.Entities.SubCategoryEnum
import org.msa.backend.Entities.BandsEnum

data class UpdateEnumsDTO(
    val category: CategoryEnum? = null,
    val subCategory: SubCategoryEnum? = null,
    val bands: BandsEnum? = null
)
