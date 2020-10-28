package ir.saeedbahari.mobisoft.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface MovieDao {
//    @Insert(entity = SIPARISItem::class, onConflict = OnConflictStrategy.IGNORE)
//    suspend fun insertSiparisItem(items: SIPARISItem?)
//
////    @Insert(
////        entity = com.utechia.depotChecklist.database.dbModels.ImageProductItem::class,
////        onConflict = OnConflictStrategy.IGNORE
////    )
////    suspend fun insertProductItem(items: com.utechia.depotChecklist.database.dbModels.ImageProductItem?)
//
//
//
//
//    @Insert(entity = SATIRItem::class,onConflict = OnConflictStrategy.IGNORE)
//    suspend fun insertSattirItem(items: List<SATIRItem>)
//
//    @Query("SELECT Count(*) FROM SIPARISItem ")
//    fun countTasks(): LiveData<Int>
//
//
//    @Transaction
//    @Query("SELECT * from SATIRItem where idSiparis = :idSiparis")
//    suspend fun getProductList(idSiparis: String): List<SATIRItem>
//
//
//
//    @Query("SELECT cariHesapOzelKodu from SIPARISItem where sIPARISNO = :barcodeSiparis")
//    fun getSiparisNameAsSiparisNo(barcodeSiparis: String): LiveData<String>
//
////    @Query("SELECT cariHesapOzelKodu from SIPARISItem where pAZARYERIKARGOKODU = :barcodeSiparis")
////    fun getSiparisNameAsKargoKodu(barcodeSiparis: String): LiveData<String>
//
////    @Transaction
//    @Query("SELECT * from SATIRItem where idSiparis = :idSiparis and bARCODE=:barcode")
//    fun getProductBarcode(idSiparis: String, barcode: String): SATIRItem
//
//    @Query("SELECT * from SATIRItem where idSiparis = :idSiparis and uRUNKODU=:urunKod")
//    fun getProductKod(idSiparis: String, urunKod: String): SATIRItem
////
////    @Transaction
////    @Query("SELECT * from SATIRItem where pAZARYERIKARGOKODU = :kargoKodu and bARCODE=:barcode")
////    fun getProductAsKargoKodu(kargoKodu: String, barcode: String): SATIRItem
//
//
//    @Query("Select EXISTS(SELECT * from SATIRItem where idSiparis = :idSiparis and bARCODE=:BarcodeOrKod)")
//    fun productIsExistBarcode(idSiparis: String, BarcodeOrKod: String): Boolean
//
//    @Query("Select EXISTS(SELECT * from SATIRItem where idSiparis = :idSiparis and uRUNKODU=:BarcodeOrKod)")
//    fun productIsExistKod(idSiparis: String, BarcodeOrKod: String): Boolean
////
////    @Transaction
////    @Query("Select EXISTS(SELECT * from SATIRItem where pAZARYERIKARGOKODU = :kargoKodu And bARCODE=:barcode)")
////    fun productIsExistsAsKargoKodu(kargoKodu: String, barcode: String): Boolean
////
////    @Transaction
//    @Query("Update SATIRItem set doing=:count where idSiparis = :idSiparis And bARCODE=:barcode")
//    fun increaseProductDoingBarcode(idSiparis: String, barcode: String, count: String): Int
//
//    @Query("Update SATIRItem set doing=:count where idSiparis = :idSiparis And uRUNKODU=:urunKod")
//    fun increaseProductDoingKod(idSiparis: String, urunKod: String, count: String): Int
////    @Transaction
////    @Query("Update SATIRItem set mIKTARCheckOut=:count where pAZARYERIKARGOKODU = :kargoKodu And bARCODE=:barcode")
////    fun increaseProductMiktarCheckoutKargoKodu(kargoKodu: String, barcode: String, count: String): Int
//
////    @Query("Update SIPARISItem set checkedOut= 1 where sIPARISNO = :barcodeSiparis ")
////    fun updateSiparisCheckout(barcodeSiparis: String): Int
////
////    @Query("Update SIPARISItem set checkedOut= 1 where pAZARYERIKARGOKODU = :barcodeSiparis ")
////    fun updateSiparisCheckoutAsKargoKodu(barcodeSiparis: String): Int
//
//
//    @Query(" DELETE FROM SATIRItem")
//    suspend fun dropSattirItem()
}