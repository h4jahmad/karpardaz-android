package ir.sika.karpardaz.model

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
	@PrimaryKey override val id: String,
	@NonNull var name: String,
	@NonNull var email: String,
	@NonNull var password: String,
	@NonNull var createdAt: String
) : BaseModel(id)