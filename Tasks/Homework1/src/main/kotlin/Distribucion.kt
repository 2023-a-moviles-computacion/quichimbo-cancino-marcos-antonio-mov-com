import java.io.File
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardOpenOption
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class Distribucion(
    var distroId: Int,
    var distroName: String,
    var distroArquitecture: String,
    var distroReleaseDate: Date,
    var distroContinued: Boolean,
    var listOs: ArrayList<SistemaOperativo>?
){
    init {
        distroId
        distroName
        distroArquitecture
        distroReleaseDate
        distroContinued
        listOs
    }
}
