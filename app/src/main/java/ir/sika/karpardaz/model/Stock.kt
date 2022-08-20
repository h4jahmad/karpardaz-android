package ir.sika.karpardaz.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Stock(
	@PrimaryKey override val id: String,
	val name: String,
	val currency: String,
	val createdAt: String,
	val userId: String
) : BaseModel(id)