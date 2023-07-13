import java.util.*
import kotlin.collections.ArrayList

class Distro(
    var distroId: Int,
    var distroName: String,
    var distroArquitecture: String,
    var distroReleaseDate: Date,
    var distroContinued: Boolean,
    var listOs: ArrayList<Sistema>?
)

