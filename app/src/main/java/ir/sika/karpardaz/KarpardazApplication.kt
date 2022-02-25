package ir.sika.karpardaz

import android.app.Application
import ir.sika.karpardaz.datasource.AppDatabase

class KarpardazApplication : Application() {

	val database: AppDatabase by lazy { AppDatabase.getDatabase(this) }
}