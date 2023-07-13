import java.io.File
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardOpenOption
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.contracts.contract
import kotlin.io.path.Path

class SistemaOperativo(
    //atributos
    var osId:Int,
    var osName:String,
    var osVersion:Double,
    var osReleaseDate: Date,
    var osContinued: Boolean
) {
    //constructor
    init {
        osId
        osName
        osVersion
        osReleaseDate
        osContinued
    }
}
