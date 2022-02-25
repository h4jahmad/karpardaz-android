package ir.sika.karpardaz.model

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Income(
	@PrimaryKey override val id: String,
	@NonNull val purchasedAt: String, @NonNull val subject: String,
	@NonNull val amount: String,
	@NonNull val createdAt: String,
	@NonNull val stockId: String
) : BaseModel(id)