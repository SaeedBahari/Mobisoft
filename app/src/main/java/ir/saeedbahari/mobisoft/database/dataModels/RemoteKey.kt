package ir.saeedbahari.mobisoft.database.dataModels

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "remote_keys")
data class RemoteKey(
        val label: String,
        val nextKey: Int?
){
        @PrimaryKey(autoGenerate = true)
        var id: Int = 0
}