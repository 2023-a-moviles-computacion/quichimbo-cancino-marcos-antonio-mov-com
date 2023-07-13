import java.util.*
import kotlin.collections.ArrayList
import kotlin.contracts.contract

class SistemaOperativo(
    var osId:Int,
    var osName:String,
    var osVersion:Double,
    var osReleaseDate: Date,
    var osContinued: Boolean
){
       init {
           osId,
           osName,
           osVersion,
           osReleaseDate,
           osContinued
       }
    fun setOsId(osId: Int){
        this.osId = osId
    }
    fun setOsName(osName: String){
        this.osName = osName
    }
    fun setOsVersion(osVersion: Double){
        this.osVersion = osVersion
    }
    fun setOsReleaseDate(osReleaseDate: Date){
        this.osReleaseDate = osReleaseDate
    }
    fun setOsContinued(osContinued: Boolean){
        this.osContinued = osContinued
    }
    fun getOsId():Int{
        return osId
    }
    fun getOsName(): String{
        return osName
    }
    fun getOsVersion(): Double{
        return osVersion
    }
    fun getOsReleaseDate(): Date{
        return osReleaseDate
    }
    fun getOsContinued(): Boolean{
        return osContinued
    }






}
