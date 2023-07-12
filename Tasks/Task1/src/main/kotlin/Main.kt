import java.io.*
import kotlin.collections.ArrayList
fun main() {
    var arrayOpSystems : ArrayList<OpSystem> = arrayListOf<OpSystem>()
    var arrFarmIndex:ArrayList<Int> = arrayListOf<Int>()
    leerArchivo(arrayOpSystems)
    do{
        println("Operative Systems and Distributions\n")
        println("1. Listar Playlist\n" +
                "2. Crear Playlist\n" +
                "3. Editar Playlist\n" +
                "4. Borrar Playlist\n"+
                "5. Ingresar a Playlist\n"+
                "6. Salir \n")
        var opcion= readLine()!!
        when (opcion) {
            "1" -> {
                printPlayLt(arrayOpSystems)
            }
            "2" -> {
                var continuar = "y"
                do {
                    crearPlaylist(arrayOpSystems)
                    printPlayLt(arrayOpSystems)
                    println("Seguir insertando? y/n")
                    continuar = readLine()!!
                } while (continuar == "y")
            }
            "3" -> {
                modificarPlaylist(arrayOpSystems)
                printPlayLt(arrayOpSystems)
            }
            "4" -> {
                borrarPlaylist(arrayOpSystems)
                printPlayLt(arrayOpSystems)
            }
            "5" -> {
                printPlayLt(arrayOpSystems)
                println("Seleccione el ID de la farmacia: ")
                var idPlt = readLine()!!.toInt()
                do {
                    printnombrePlaylist(arrayOpSystems,idPlt)
                    println(
                        "1. Listar Canciones\n" +
                                "2. Insertar canción\n" +
                                "3. Modificar datos de la canción\n" +
                                "4. Borrar cancion\n" +
                                "5. Salir"
                    )
                    var opcionSong = readLine()!!
                    when (opcionSong) {
                        "1" -> {
                            printCancion(arrayOpSystems, idPlt)
                        }
                        "2" -> {
                            var continuar = "y"
                            do {
                                insertarCancion(arrayOpSystems, idPlt)
                                printCancion(arrayOpSystems, idPlt)
                                println("Seguir insertando? y/n")
                                continuar = readLine()!!
                            }while (continuar=="y")
                        }
                        "3" -> {
                            println("Seleccione el ID de la cancion: ")
                            var idSong = readLine()!!.toInt()
                            modificarCancion(arrayOpSystems, idPlt,idSong)
                            printCancion(arrayOpSystems, idPlt)
                        }
                        "4" -> {
                            println("Seleccione el ID de la cancion: ")
                            var idSong = readLine()!!.toInt()
                            borrarCancion(arrayOpSystems, idPlt,idSong)
                            printCancion(arrayOpSystems, idPlt)
                        }
                        else -> {
                            println("Adios")
                            break
                        }
                    }
                } while (opcionSong!="5")
            }
            else -> {
                println("Adios")
                break
            }
        }
    }while (opcion!="6")
    saveFile(arrayOpSystems)
}

fun crearPlaylist(arrayOpSystem:ArrayList<OpSystem>){ // insertar playlist
    println("Ingrese el nombre de la Playlist")
    var nombre=readLine()!!
    println("Ingrese la descripcion")
    var descripcion=readLine()!!
    println("Insertar foto? (s/n)")
    var fotoptionS=readLine()!!
    var foto = deStringaBoolean(fotoptionS)

    if(arrayOpSystem.isEmpty()){
        arrayOpSystem.add(OpSystem(1,nombre,
            descripcion, foto, arrayListOf<Distro>()) )
    }else{
        arrayOpSystem.add(OpSystem(arrayOpSystem.get(arrayOpSystem.size - 1).idOpSystem +1,nombre,
            descripcion, foto, arrayListOf<Distro>()) )
    }
}

fun borrarPlaylist(arrayPLaylist:ArrayList<OpSystem>){ // b
    println("Eliminar Playlist :seleccionar ID: ")
    var idPborrar= readLine()!!.toInt()
    for (element in arrayPLaylist) {
        if (element.idOpSystem==idPborrar) {
            println("el index:"+arrayPLaylist.indexOf(element))
            arrayPLaylist.removeAt(arrayPLaylist.indexOf(element))
            break
        }
    }
}

fun modificarPlaylist(arrayOpSystem: ArrayList<OpSystem>){ // UPdate
    println("Seleccione el ID de la Playlist")
    var idPmodificar= readLine()!!.toInt()
    for (element in arrayOpSystem) {
        if (element.idOpSystem==idPmodificar) {
            println("Ingrese el nombre de la Playlist")
            var nombre=readLine()!!
            println("Ingrese la descripcion")
            var descripcion=readLine()!!
            println("Insertar foto? (s/n)")
            var fotoptionS=readLine()!!
            var foto = deStringaBoolean(fotoptionS)

            println("el index:"+arrayOpSystem.indexOf(element))
            arrayOpSystem.set(arrayOpSystem.indexOf(element),OpSystem(element.idOpSystem,nombre,
                descripcion, foto,element.opSystems))
        }
    }
}

fun printPlayLt(arrayOpSystem: ArrayList<OpSystem>){
    println("ID\tNombre Playlist\tDescripcion\tFoto\n" )

    for (playt in arrayOpSystem)
    {
        println(""+playt.idOpSystem+"\t"+playt.osName+"\t"+
                "\t"+playt.osContinued+"\t"+playt.opSystems)
    }
}

fun printnombrePlaylist(arrayOpSystem: ArrayList<OpSystem>, idPlaylst: Int){
    var idPlayLt= idPlaylst
    for (element in arrayOpSystem) {
        if (element.idOpSystem == idPlayLt) {
            println("PLAYLIST " + element.osName.uppercase())
        }
    }
}

fun printCancion(arrayOpSystem: ArrayList<OpSystem>, idPlaylst: Int){ //printMed
    var idPlayLt= idPlaylst
    for (element in arrayOpSystem) {
        if (element.idOpSystem==idPlayLt) {
            println("PLAYLIST " + element.osName.uppercase())
            println("\nIDP\tID\tNombre\tArtista\tGenero\n")
            for (song in element.opSystems)
            {
                println(""+song.idOs+"\t"+song.idDistro+"\t"+song.distroVersion+"\t"+
                        song.distroArqui+"\t"+song.fileManager)
            }
        }}
}
fun insertarCancion(arrayOpSystem: ArrayList<OpSystem>, idPlaylst: Int){
    println("Insertar cancion")
    var idPlayLt= idPlaylst
    for (element in arrayOpSystem) {
        if (element.idOpSystem==idPlayLt) {
            println("Ingrese el nombre de la cancion: ")
            var nombreCancion = readLine()!!
            println("Ingrese el nombre del artista: ")
            var artista = readLine()!!
            println("Ingrese el genero: ")
            var genero = readLine()!!

            if(element.opSystems.isEmpty()) {
                element.opSystems.add(Distro(idPlayLt, 1, nombreCancion, artista, genero))
            }else
            {
                element.opSystems.add(Distro(idPlayLt, element.opSystems.get(element.opSystems.size - 1).idDistro +1, nombreCancion, artista, genero))
            }
            println("\nIDP\tID\tNombre\tArtista\tGenero\n")
            for (song in element.opSystems)
            {
                println(""+song.idOs+"\t"+song.idDistro+"\t"+song.distroVersion+"\t"+
                        song.distroArqui+"\t"+song.fileManager)
            }
        }}
}


fun modificarCancion(arrayOpSystem: ArrayList<OpSystem>, idPlaylst: Int, idSong:Int)
{
    println("modificar medicamento")
    var idPlayLt = idPlaylst
    for (element in arrayOpSystem) {
        if (element.idOpSystem==idPlayLt) {
            for (song in element.opSystems){
                if (song.idDistro==idSong){
                    println("Ingrese el nombre de la cancion: ")
                    var nombreCancion = readLine()!!
                    println("Ingrese el nombre del artista: ")
                    var artista = readLine()!!
                    println("Ingrese el genero: ")
                    var genero = readLine()!!
                    element.opSystems.set(element.opSystems.indexOf(song),Distro(idPlayLt, idSong , nombreCancion, artista, genero))
                }
            }

        }}
}

fun borrarCancion(arrayOpSystem: ArrayList<OpSystem>, idPlaylst: Int, idSong:Int)
{
    println("borrar medicamento")
    var idPlaylst= idSong
    for (element in arrayOpSystem) {
        if (element.idOpSystem==idPlaylst) {
            for (song in element.opSystems){
                if (song.idDistro==idSong){
                    element.opSystems.removeAt(element.opSystems.indexOf(song))
                    break
                }
            }
        }}
}

fun saveFile(arrayOpSystem: ArrayList<OpSystem>){
    val nombre="out/Archivos/Playlists.txt"
    val archivo=File(nombre)
    if (!archivo.exists()){
        archivo.createNewFile()
    }
    val fileWriter = FileWriter(archivo)
    val bufferedWriter = BufferedWriter(fileWriter)
    var salida:String=""
    for(element in arrayOpSystem){
        salida+=("${element.toString()}")
    }
    println(salida)
    bufferedWriter.write("$salida")
    bufferedWriter.close()
}
fun leerArchivo(arrayOpSystem: ArrayList<OpSystem>){
    val fr = FileReader("out/Archivos/Playlists.txt")
    var fileText = ""
    var i: Int
    while (fr.read().also { i = it } != -1) {
        fileText += i.toChar()
    }
    var count = 0
    for (i in 0 until fileText.length) {
        val letter: Char = fileText[i]
        if (letter == '\n') ++count
    }
    var elemntosArchive=ArrayList<String>(fileText.split("\n"))
    for(i in 0..count-1){
        var (idPlaylist, nombre, descripcion, foto, songs)=elemntosArchive[i].split("|")
        var canciones=ArrayList<String>((songs.substring(1,songs.length-1)).split(","))
        var rolitas=ArrayList<Distro>() // rolitas = canciones
        if(canciones[0].length>0){
            for(cancion in canciones) {
                var (idPlyt, idCancion, nombreCan, artistaCan, generoCan) = cancion.split(";")
                idPlyt=idPlyt.replace("\\s".toRegex(),"")
                var rolas =Distro(idPlaylist.toInt(), idCancion.toInt(), nombreCan, artistaCan, generoCan)
                rolitas.add(rolas)
            }
        } else {
            println("")
        }
        var opSystem= OpSystem(idPlaylist.toInt(),nombre, descripcion, deStringaBoolean(foto), rolitas)
        arrayOpSystem.add(opSystem)
    }
}

fun deStringaBoolean(fotoptionS: String?): Boolean{
    var datoM= fotoptionS?.uppercase()
    if (datoM=="S"){
        return true
    }
    else {
        return false
    }
}

class OpSystem(
    var idOpSystem:Int,
    var osName:String,
    var oSVersion:String,
    var osContinued: Boolean,
    var opSystems: ArrayList<Distro>  = arrayListOf<Distro>()
) {
    override fun toString(): String {
        return "$idOpSystem|$osName|$oSVersion|$osContinued|${opSystems.toString()}\n"
    }
}

class Distro(
    var idOs:Int,
    var idDistro: Int,
    var distroVersion:String,
    var distroArqui: String,
    var fileManager:String,
) {
    override fun toString(): String{
        return "$idOs;$idDistro;$distroVersion;$distroArqui;$fileManager"
    }
}

