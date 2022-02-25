package ir.sika.karpardaz.model

import androidx.annotation.NonNull
import androidx.room.PrimaryKey

data class Stock(
	@PrimaryKey override val id: String,
	@NonNull val name: String,
	@NonNull val currency: String,
	@NonNull val createdAt: String,
	@NonNull val userId: String
) : BaseModel(id)