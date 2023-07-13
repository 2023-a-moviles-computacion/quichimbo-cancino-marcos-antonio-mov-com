import java.io.*
import java.util.Date
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalQueries
import java.util.*
import kotlin.collections.ArrayList
// La función principal del programa.
fun main() {
    // Crea un ArrayList de Playlist para almacenar las playlists.
    var arrayPlaylist : ArrayList<Playlist> = arrayListOf<Playlist>()

    // Crea un ArrayList de Int para almacenar los índices de las farmacias.
    var arrFarmIndex:ArrayList<Int> = arrayListOf<Int>()

    // Lee los datos de archivo y los carga en el ArrayList de Playlist.
    leerArchivo(arrayPlaylist)

    // Inicia un bucle do-while para mostrar el menú al usuario.
    do {
        println("FREE MUSIC PLAYER\n")
        println("1. Listar Playlist\n" +
                "2. Crear Playlist\n" +
                "3. Editar Playlist\n" +
                "4. Borrar Playlist\n"+
                "5. Ingresar a Playlist\n"+
                "6. Salir")

        // Lee la opción seleccionada por el usuario.
        var opcion= readln()

        // Utiliza una estructura when para ejecutar el código correspondiente según la opción seleccionada.
        when (opcion) {
            "1" -> {
                // Si la opción es 1, llama a la función printPlayLt para imprimir la lista de playlists.
                printPlayLt(arrayPlaylist)
            }
            "2" -> {
                // Si la opción es 2, permite al usuario crear una nueva playlist.
                var continuar = "y"
                do {
                    crearPlaylist(arrayPlaylist)
                    printPlayLt(arrayPlaylist)
                    println("Seguir insertando? y/n")
                    continuar = readLine()!!
                } while (continuar == "y")
            }
            "3" -> {
                // Si la opción es 3, permite al usuario modificar una playlist existente.
                modificarPlaylist(arrayPlaylist)
                printPlayLt(arrayPlaylist)
            }
            "4" -> {
                // Si la opción es 4, permite al usuario borrar una playlist existente.
                borrarPlaylist(arrayPlaylist)
                printPlayLt(arrayPlaylist)
            }
            "5" -> {
                // Si la opción es 5, permite al usuario ingresar a una playlist seleccionada.
                printPlayLt(arrayPlaylist)
                println("Seleccione el ID de la playlist: ")
                var idPlt = readln().toInt()
                do {
                    printnombrePlaylist(arrayPlaylist,idPlt)
                    println(
                        "1. Listar Canciones\n" +
                                "2. Insertar canción\n" +
                                "3. Modificar datos de la canción\n" +
                                "4. Borrar canción\n" +
                                "5. Salir"
                    )
                    var opcionSong = readln()
                    when (opcionSong) {
                        "1" -> {
                            // Si la opción es 1, muestra la lista de canciones de la playlist seleccionada.
                            printCancion(arrayPlaylist, idPlt)
                        }
                        "2" -> {
                            // Si la opción es 2, permite al usuario insertar una nueva canción en la playlist.
                            var continuar = "y"
                            do {
                                insertarCancion(arrayPlaylist, idPlt)
                                printCancion(arrayPlaylist, idPlt)
                                println("Seguir insertando? y/n")
                                continuar = readln()
                            }while (continuar=="y")
                        }
                        "3" -> {
                            // Si la opción es 3, permite al usuario modificar los datos de una canción existente.
                            println("Seleccione el ID de la canción: ")
                            var idSong = readln().toInt()
                            modificarCancion(arrayPlaylist, idPlt,idSong)
                            printCancion(arrayPlaylist, idPlt)
                        }
                        "4" -> {
                            // Si la opción es 4, permite al usuario borrar una canción existente.
                            println("Seleccione el ID de la canción: ")
                            var idSong = readln().toInt()
                            borrarCancion(arrayPlaylist, idPlt,idSong)
                            printCancion(arrayPlaylist, idPlt)
                        }
                        else -> {
                            // Si la opción es diferente de 1, 2, 3, 4 o 5, se muestra el mensaje de despedida.
                            println("Adios")
                            break
                        }
                    }
                } while (opcionSong!="5")
            }
            else -> {
                // Si la opción es diferente de 1, 2, 3, 4 o 5, se muestra el mensaje de despedida.
                println("Adios")
                break
            }
        }
    } while (opcion!="6")

    // Llama a la función saveFile para guardar los datos en archivo.
    saveFile(arrayPlaylist)
}

// Define la función crearPlaylist que permite insertar una nueva Playlist.
// Recibe como parámetro un ArrayList de Playlist.
fun crearPlaylist(arrayPlaylist:ArrayList<Playlist>){ // insertar playlist

    // Solicita al usuario que ingrese el nombre de la Playlist.
    println("Ingrese el nombre de la Playlist")
    var nombre= readln()

    // Solicita al usuario que ingrese la descripción de la Playlist.
    println("Ingrese la descripcion")
    var descripcion= readln()

    // Solicita al usuario que indique si desea insertar una foto para la Playlist.
    println("Insertar foto? (s/n)")
    var fotoptionS= readln()

    // Convierte la respuesta del usuario a un valor booleano utilizando la función deStringaBoolean.
    var foto = deStringaBoolean(fotoptionS)

    // Comprueba si el ArrayList de Playlist está vacío.
    if(arrayPlaylist.isEmpty()){
        // Si está vacío, agrega la primera Playlist con ID 1 al ArrayList.
        arrayPlaylist.add(Playlist(1,nombre,
            descripcion, foto, arrayListOf<Cancion>()) )
    }else{
        // Si no está vacío, agrega una nueva Playlist al ArrayList con un ID calculado.
        // El ID se obtiene sumando 1 al ID de la última Playlist en el ArrayList.
        arrayPlaylist.add(Playlist(arrayPlaylist.get(arrayPlaylist.size - 1).idPlaylist +1,nombre,
            descripcion, foto, arrayListOf<Cancion>()) )
    }
}

// Define la función borrarPlaylist que permite eliminar una Playlist.
// Recibe como parámetro un ArrayList de Playlist.
fun borrarPlaylist(arrayPLaylist:ArrayList<Playlist>){ // b

    // Solicita al usuario que seleccione el ID de la Playlist a eliminar.
    println("Eliminar Playlist :seleccionar ID: ")
    var idPborrar= readln().toInt()

    // Itera sobre cada elemento en el ArrayList de Playlist.
    for (element in arrayPLaylist) {
        // Comprueba si el ID de la Playlist coincide con el ID seleccionado.
        if (element.idPlaylist==idPborrar) {
            // Imprime el índice de la Playlist en el ArrayList.
            println("el index:"+arrayPLaylist.indexOf(element))

            // Elimina la Playlist del ArrayList utilizando su índice.
            arrayPLaylist.removeAt(arrayPLaylist.indexOf(element))

            // Sale del bucle una vez que se ha eliminado la Playlist.
            break
        }
    }
}


// Define la función modificarPlaylist que permite actualizar una Playlist existente.
// Recibe como parámetro un ArrayList de Playlist.
fun modificarPlaylist(arrayPlaylist: ArrayList<Playlist>) { // UPdate

    // Solicita al usuario que seleccione el ID de la Playlist a modificar.
    println("Seleccione el ID de la Playlist")
    var idPmodificar= readln().toInt()

    // Itera sobre cada elemento en el ArrayList de Playlist.
    for (element in arrayPlaylist) {
        // Comprueba si el ID de la Playlist coincide con el ID seleccionado.
        if (element.idPlaylist==idPmodificar) {
            // Solicita al usuario que ingrese el nuevo nombre de la Playlist.
            println("Ingrese el nombre de la Playlist")
            var nombre= readln()

            // Solicita al usuario que ingrese la nueva descripción de la Playlist.
            println("Ingrese la descripcion")
            var descripcion= readln()

            // Solicita al usuario que indique si desea insertar una foto (s/n).
            println("Insertar foto? (s/n)")
            var fotoptionS= readln()

            // Convierte la respuesta del usuario a un valor booleano utilizando la función deStringaBoolean.
            var foto = deStringaBoolean(fotoptionS)

            // Actualiza los valores de la Playlist con los nuevos valores ingresados.
            // Utiliza el índice de la Playlist en el ArrayList para reemplazar el elemento existente.
            arrayPlaylist.set(arrayPlaylist.indexOf(element),Playlist(element.idPlaylist,nombre,
                descripcion, foto,element.songs))
        }
    }
}


// Define la función printPlayLt que imprime las Playlist disponibles
// Recibe como parámetro un ArrayList de Playlist.
fun printPlayLt(arrayPlaylist: ArrayList<Playlist>){

    // Imprime los nombres de las columnas del registro de Playlist.
    println("|ID |\t |Nombre Playlist| \t |Descripcion |\t |Foto |\n" )

    // Itera sobre cada playlist en arrayPlaylist.
    for (playt in arrayPlaylist)
    {
        // Imprime los detalles de cada Playlist: id, nombre, foto y canciones.
        // Nota que en este código no imprime la descripción a pesar de tenerla en los nombres de las columnas.
        println(""+playt.idPlaylist+"|\t"+playt.nombre+"|\t"+"|\t"+playt.descripcion+
                "|\t"+playt.foto+"|\t"+playt.songs)
    }
}


// Define la función printnombrePlaylist que imprime el nombre de una Playlist específica
// Recibe como parámetros un ArrayList de Playlist y un id de playlist.
fun printnombrePlaylist(arrayPlaylist: ArrayList<Playlist>,idPlaylst: Int){

    // Asigna el valor de idPlaylst a una nueva variable. Esto parece redundante porque podrías usar idPlaylst directamente.
    var idPlayLt= idPlaylst

    // Itera sobre cada elemento en arrayPlaylist.
    for (element in arrayPlaylist) {

        // Si el id de la playlist actual coincide con idPlayLt, entra al bloque.
        if (element.idPlaylist == idPlayLt) {

            // Imprime el nombre de la Playlist en mayúsculas.
            println("PLAYLIST " + element.nombre.uppercase())
        }
    }
}


// Define la función printCancion que imprime las canciones de una Playlist específica, recibe como parámetros un ArrayList de Playlist y un id de playlist.
fun printCancion(arrayPlaylist: ArrayList<Playlist>,idPlaylst: Int){

    // Asigna el valor de idPlaylst a una nueva variable. Esto parece redundante porque podrías usar idPlaylst directamente.
    var idPlayLt= idPlaylst

    // Itera sobre cada elemento en arrayPlaylist.
    for (element in arrayPlaylist) {

        // Si el id de la playlist actual coincide con idPlayLt, entra al bloque.
        if (element.idPlaylist==idPlayLt) {

            // Imprime el nombre de la Playlist en mayúsculas.
            println("PLAYLIST " + element.nombre.uppercase())

            // Imprime los títulos de las columnas para las canciones.
            println("\nIDP\tID\tNombre\tArtista\tGenero\n")

            // Itera sobre cada canción en la lista de canciones de la playlist actual.
            for (song in element.songs)
            {
                // Imprime los detalles de cada canción.
                println(""+song.idPlyt+"\t"+song.idCancion+"\t"+song.nombreCancion+"\t"+ song.artista+"\t"+song.genero)
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

