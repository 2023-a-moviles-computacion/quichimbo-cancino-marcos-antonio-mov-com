import java.io.*
import java.util.Date
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalQueries
import java.util.*
import kotlin.collections.ArrayList
fun main() {
    var arrayPlaylist : ArrayList<Playlist> = arrayListOf<Playlist>()
    var arrFarmIndex:ArrayList<Int> = arrayListOf<Int>()
    leerArchivo(arrayPlaylist)
    do{
        println("FREE MUSIC PLAYER\n")
        println("1. Listar Playlist\n" +
                "2. Crear Playlist\n" +
                "3. Editar Playlist\n" +
                "4. Borrar Playlist\n"+
                "5. Ingresar a Playlist\n"+
                "6. Salir")
        var opcion= readLine()!!
        when (opcion) {
            "1" -> {
                printPlayLt(arrayPlaylist)
            }
            "2" -> {
                var continuar = "y"
                do {
                    crearPlaylist(arrayPlaylist)
                    printPlayLt(arrayPlaylist)
                    println("Seguir insertando? y/n")
                    continuar = readLine()!!
                } while (continuar == "y")
            }
            "3" -> {
                modificarPlaylist(arrayPlaylist)
                printPlayLt(arrayPlaylist)
            }
            "4" -> {
                borrarPlaylist(arrayPlaylist)
                printPlayLt(arrayPlaylist)
            }
            "5" -> {
                printPlayLt(arrayPlaylist)
                println("Seleccione el ID de la farmacia: ")
                var idPlt = readLine()!!.toInt()
                do {
                    printnombrePlaylist(arrayPlaylist,idPlt)
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
                            printCancion(arrayPlaylist, idPlt)
                        }
                        "2" -> {
                            var continuar = "y"
                            do {
                                insertarCancion(arrayPlaylist, idPlt)
                                printCancion(arrayPlaylist, idPlt)
                                println("Seguir insertando? y/n")
                                continuar = readLine()!!
                            }while (continuar=="y")
                        }
                        "3" -> {
                            println("Seleccione el ID de la cancion: ")
                            var idSong = readLine()!!.toInt()
                            modificarCancion(arrayPlaylist, idPlt,idSong)
                            printCancion(arrayPlaylist, idPlt)
                        }
                        "4" -> {
                            println("Seleccione el ID de la cancion: ")
                            var idSong = readLine()!!.toInt()
                            borrarCancion(arrayPlaylist, idPlt,idSong)
                            printCancion(arrayPlaylist, idPlt)
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
    saveFile(arrayPlaylist)
}

fun crearPlaylist(arrayPlaylist:ArrayList<Playlist>){ // insertar playlist
    println("Ingrese el nombre de la Playlist")
    var nombre=readLine()!!
    println("Ingrese la descripcion")
    var descripcion=readLine()!!
    println("Insertar foto? (s/n)")
    var fotoptionS=readLine()!!
    var foto = deStringaBoolean(fotoptionS)

    if(arrayPlaylist.isEmpty()){
        arrayPlaylist.add(Playlist(1,nombre,
            descripcion, foto, arrayListOf<Cancion>()) )
    }else{
        arrayPlaylist.add(Playlist(arrayPlaylist.get(arrayPlaylist.size - 1).idPlaylist +1,nombre,
            descripcion, foto, arrayListOf<Cancion>()) )
    }
}

fun borrarPlaylist(arrayPLaylist:ArrayList<Playlist>){ // b
    println("Eliminar Playlist :seleccionar ID: ")
    var idPborrar= readLine()!!.toInt()
    for (element in arrayPLaylist) {
        if (element.idPlaylist==idPborrar) {
            println("el index:"+arrayPLaylist.indexOf(element))
            arrayPLaylist.removeAt(arrayPLaylist.indexOf(element))
            break
        }
    }
}

fun modificarPlaylist(arrayPlaylist: ArrayList<Playlist>){ // UPdate
    println("Seleccione el ID de la Playlist")
    var idPmodificar= readLine()!!.toInt()
    for (element in arrayPlaylist) {
        if (element.idPlaylist==idPmodificar) {
            println("Ingrese el nombre de la Playlist")
            var nombre=readLine()!!
            println("Ingrese la descripcion")
            var descripcion=readLine()!!
            println("Insertar foto? (s/n)")
            var fotoptionS=readLine()!!
            var foto = deStringaBoolean(fotoptionS)

            println("el index:"+arrayPlaylist.indexOf(element))
            arrayPlaylist.set(arrayPlaylist.indexOf(element),Playlist(element.idPlaylist,nombre,
                descripcion, foto,element.songs))
        }
    }
}

fun printPlayLt(arrayPlaylist: ArrayList<Playlist>){
    println("ID\tNombre Playlist\tDescripcion\tFoto\n" )

    for (playt in arrayPlaylist)
    {
        println(""+playt.idPlaylist+"\t"+playt.nombre+"\t"+
                "\t"+playt.foto+"\t"+playt.songs)
    }
}

fun printnombrePlaylist(arrayPlaylist: ArrayList<Playlist>,idPlaylst: Int){
    var idPlayLt= idPlaylst
    for (element in arrayPlaylist) {
        if (element.idPlaylist == idPlayLt) {
            println("PLAYLIST " + element.nombre.uppercase())
        }
    }
}

fun printCancion(arrayPlaylist: ArrayList<Playlist>,idPlaylst: Int){ //printMed
    var idPlayLt= idPlaylst
    for (element in arrayPlaylist) {
        if (element.idPlaylist==idPlayLt) {
            println("PLAYLIST " + element.nombre.uppercase())
            println("\nIDP\tID\tNombre\tArtista\tGenero\n")
            for (song in element.songs)
            {
                println(""+song.idPlyt+"\t"+song.idCancion+"\t"+song.nombreCancion+"\t"+
                        song.artista+"\t"+song.genero)
            }
        }}
}
// Define la función insertarCancion con dos parámetros: un ArrayList de Playlist y un id de playlist.
fun insertarCancion(arrayPlaylist: ArrayList<Playlist>,idPlaylst: Int){
    // Imprime un mensaje indicando que la operación es para insertar una canción.
    println("Insertar cancion")

    // Almacena el id de la Playlist en una nueva variable. Esto parece redundante porque podrías usar idPlaylst directamente.
    var idPlayLt= idPlaylst

    // Itera sobre cada elemento de arrayPlaylist.
    for (element in arrayPlaylist) {

        // Si el id de la playlist actual coincide con idPlayLt, entra al bloque.
        if (element.idPlaylist==idPlayLt) {

            // Solicita al usuario que ingrese el nombre de la canción.
            println("Ingrese el nombre de la cancion: ")
            var nombreCancion = readLine()!!

            // Solicita al usuario que ingrese el nombre del artista.
            println("Ingrese el nombre del artista: ")
            var artista = readLine()!!

            // Solicita al usuario que ingrese el género.
            println("Ingrese el genero: ")
            var genero = readLine()!!

            // Verifica si la lista de canciones de la playlist actual está vacía.
            if(element.songs.isEmpty()) {

                // Si está vacía, añade una nueva Canción con el id de canción 1.
                element.songs.add(Cancion(idPlayLt, 1, nombreCancion, artista, genero))

            } else {

                // Si no está vacía, añade una nueva Canción con el id de la última canción incrementado en 1.
                element.songs.add(Cancion(idPlayLt, element.songs.get(element.songs.size - 1).idCancion +1, nombreCancion, artista, genero))
            }

            // Imprime los títulos de las columnas de las canciones.
            println("\nIDP\tID\tNombre\tArtista\tGenero\n")

            // Itera sobre cada canción en la lista de canciones de la playlist actual.
            for (song in element.songs)
            {
                // Imprime los detalles de cada canción.
                println(""+song.idPlyt+"\t"+song.idCancion+"\t"+song.nombreCancion+"\t"+ song.artista+"\t"+song.genero)
            }
        }}
}


// Define la función modificarCancion con tres parámetros: un ArrayList de Playlist, un id de playlist y un id de canción.
fun modificarCancion(arrayPlaylist: ArrayList<Playlist>,idPlaylst: Int,idSong:Int)
{
    // Imprime un mensaje indicando que la operación es para modificar un medicamento.
    println("modificar medicamento")

    // Almacena el id de la Playlist en una nueva variable. Esto parece redundante porque podrías usar idPlaylst directamente.
    var idPlayLt = idPlaylst

    // Itera sobre cada elemento de arrayPlaylist.
    for (element in arrayPlaylist) {

        // Si el id de la playlist actual coincide con idPlayLt, entra al bloque.
        if (element.idPlaylist==idPlayLt) {

            // Itera sobre cada canción en la lista de canciones de la playlist actual.
            for (song in element.songs){

                // Si el id de la canción actual coincide con idSong, entra al bloque.
                if (song.idCancion==idSong){

                    // Solicita al usuario que ingrese el nuevo nombre de la canción.
                    println("Ingrese el nombre de la cancion: ")
                    var nombreCancion = readln()

                    // Solicita al usuario que ingrese el nombre del artista.
                    println("Ingrese el nombre del artista: ")
                    var artista = readln()

                    // Solicita al usuario que ingrese el género.
                    println("Ingrese el genero: ")
                    var genero = readln()

                    // Modifica la canción en el ArrayList de canciones de la playlist con los nuevos valores ingresados por el usuario.
                    element.songs.set(element.songs.indexOf(song),Cancion(idPlayLt, idSong , nombreCancion, artista, genero))
                }
            }
        }
    }
}


// Define la función borrarCancion con tres parámetros: un ArrayList de Playlist, un id de playlist y un id de canción.
fun borrarCancion(arrayPlaylist: ArrayList<Playlist>,idPlaylst: Int,idSong:Int)
{
    // Imprime un mensaje indicando que la operación es para borrar un medicamento.
    println("borrar medicamento")

    // Asigna el valor de idSong a idPlaylst, parece que es un error y se debería omitir esta línea.
    var idPlaylst= idSong

    // Itera sobre cada elemento de arrayPlaylist.
    for (element in arrayPlaylist) {

        // Si el id de la playlist actual coincide con idPlaylst, entra al bloque.
        if (element.idPlaylist==idPlaylst) {

            // Itera sobre cada canción en la lista de canciones de la playlist actual.
            for (song in element.songs){

                // Si el id de la canción actual coincide con idSong, entra al bloque.
                if (song.idCancion==idSong){

                    // Remueve la canción del ArrayList de canciones de la playlist.
                    element.songs.removeAt(element.songs.indexOf(song))

                    // Rompe el ciclo de canciones ya que se encontró y eliminó la canción.
                    break
                }
            }
        }
    }
}


fun saveFile(arrayPlaylist: ArrayList<Playlist>){
    // Se define la ruta y el nombre del archivo a guardar.
    val nombre="out/Archivos/Playlists.txt"

    // Se crea un objeto de tipo File con el nombre y ruta especificados.
    val archivo=File(nombre)

    // Verifica si el archivo existe, si no existe, se crea un nuevo archivo.
    if (!archivo.exists()){
        archivo.createNewFile()
    }

    // Se crea un objeto FileWriter para escribir en el archivo.
    val fileWriter = FileWriter(archivo)

    // Se utiliza BufferedWriter para proporcionar una escritura más eficiente en el archivo.
    val bufferedWriter = BufferedWriter(fileWriter)

    // Se inicializa una variable salida de tipo String vacía.
    var salida:String=""

    // Itera a través del array de playlists, convierte cada elemento a String y lo añade a la variable salida.
    for(element in arrayPlaylist){
        salida+=("${element.toString()}")
    }

    // Imprime la variable salida en la consola (útil para propósitos de depuración).
    println(salida)

    // Escribe la cadena salida en el archivo.
    bufferedWriter.write("$salida")

    // Cierra el BufferedWriter, liberando así los recursos del sistema.
    bufferedWriter.close()
}

fun leerArchivo(arrayPlaylist: ArrayList<Playlist>){
    // Crear un objeto FileReader para leer el archivo 'Playlists.txt'.
    val fr = FileReader("out/Archivos/Playlists.txt")

    // Variable para almacenar el texto del archivo.
    var fileText = ""

    // Variable usada para guardar el valor del caracter leído.
    var i: Int

    // Leer el archivo carácter por carácter y convertirlo a texto.
    while (fr.read().also { i = it } != -1) {
        fileText += i.toChar()
    }

    // Contador para las líneas del archivo.
    var count = 0

    // Recorrer el texto del archivo, caracter por caracter.
    for (i in 0 until fileText.length) {
        val letter: Char = fileText[i]

        // Incrementar el contador si el caracter es una nueva línea.
        if (letter == '\n') ++count
    }

    // Separar el texto del archivo por nuevas líneas en un ArrayList.
    var elemntosArchive=ArrayList<String>(fileText.split("\n"))

    // Iterar a través de cada línea en el ArrayList.
    for(i in 0..count-1){
        // Separar cada línea en sus respectivos campos.
        var (idPlaylist, nombre, descripcion, foto, songs)=elemntosArchive[i].split("|")

        // Crear una lista de canciones separando el campo de canciones por comas.
        var canciones=ArrayList<String>((songs.substring(1,songs.length-1)).split(","))

        // Crear una lista de objetos Cancion.
        var rolitas=ArrayList<Cancion>()

        // Si hay al menos una canción en la línea.
        if(canciones[0].length>0){
            // Iterar sobre cada canción.
            for(cancion in canciones) {
                // Separar la información de la canción en sus respectivos campos.
                var (idPlyt, idCancion, nombreCan, artistaCan, generoCan) = cancion.split(";")

                // Remover espacios en blanco del idPlyt.
                idPlyt=idPlyt.replace("\\s".toRegex(),"")

                // Crear un objeto Cancion.
                var rolas =Cancion(idPlaylist.toInt(), idCancion.toInt(), nombreCan, artistaCan, generoCan)

                // Añadir la canción a la lista de canciones.
                rolitas.add(rolas)
            }
        } else {
            // Imprimir una línea vacía si no hay canciones.
            println("")
        }

        // Crear un objeto Playlist.
        var playlist= Playlist(idPlaylist.toInt(),nombre, descripcion, deStringaBoolean(foto), rolitas)

        // Añadir la playlist al ArrayList de playlists.
        arrayPlaylist.add(playlist)
    }
}

fun deStringaBoolean(fotoptionS: String?): Boolean{
    // Convierte la cadena de entrada a mayúsculas.
    // Kotlin maneja la posibilidad de null de forma segura con el operador '?'.
    var datoM= fotoptionS?.uppercase()

    // Si el dato convertido a mayúsculas es "S", la función devuelve 'true'.
    if (datoM=="S"){
        return true
    }
    // En caso contrario (es decir, si el dato no es "S" o es null), la función devuelve 'false'.
    else {
        return false
    }
}


class Playlist(
    var idPlaylist:Int,
    var nombre:String,
    var descripcion:String,
    var foto: Boolean,
    var songs: ArrayList<Cancion>  = arrayListOf<Cancion>()
) {
    override fun toString(): String {
        return "$idPlaylist|$nombre|$descripcion|$foto|${songs.toString()}\n"
    }
}

class Cancion(
    var idPlyt:Int,
    var idCancion: Int,
    var nombreCancion:String,
    var artista: String,
    var genero:String
) {
    override fun toString(): String {
       return "$idPlyt;$idCancion;$nombreCancion;$artista;$genero"
    }
}

