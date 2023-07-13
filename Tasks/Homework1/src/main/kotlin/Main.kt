import java.io.*
import java.text.SimpleDateFormat
import java.util.Date
import kotlin.collections.ArrayList

fun main() {
    // Crea un ArrayList de SistemaOp para almacenar las SistemasOp.
    var arraySistemaOps : ArrayList<SistemaOp> = arrayListOf<SistemaOp>()

    // Lee los datos de archivo y los carga en el ArrayList de Playlist.
    readFile(arraySistemaOps)

    // Inicia un bucle do-while para mostrar el menú al usuario.
    do {
        println("Operative Systems History\n")
        println("1. Listar Sistemas Operativos\n" +
                "2. Insertar Sistema Operativo\n" +
                "3. Actualizar Sistema Operativo\n" +
                "4. Delete Sistema Operativo\n"+
                "5. Ingresar a un Sistema Operativo\n"+
                "6. Guardar y salir")

        // Lee la opción seleccionada por el usuario.
        var opcion= readln()

        // Utiliza una estructura when para ejecutar el código correspondiente según la opción seleccionada.
        when (opcion) {
            "1" -> {
                // Si la opción es 1, llama a la función printSistemaOLst para imprimir la lista de sistemasOperativos.
                printSistemaOLst(arraySistemaOps)
            }
            "2" -> {
                // Si la opción es 2, permite al usuario crear un nuevo So.
                var continuar = "y"
                do {
                    createSistemaOp(arraySistemaOps)
                    printSistemaOLst(arraySistemaOps)
                    println("Seguir insertando? y/n")
                    continuar = readln()
                } while (continuar == "y")
            }
            "3" -> {
                // Si la opción es 3, permite al usuario modificar un SistOp existente.
                updteSistemaO(arraySistemaOps)
                printSistemaOLst(arraySistemaOps)
            }
            "4" -> {
                // Si la opción es 4, permite al usuario borrar  un SistOp existente.
                deleteSistemaO(arraySistemaOps)
                printSistemaOLst(arraySistemaOps)
            }
            "5" -> {
                // Si la opción es 5, permite al usuario ingresar un SistOp seleccionado.
                printSistemaOLst(arraySistemaOps)
                println("Seleccione el ID del Sistema Operativo")
                var idEnterSistemaO = readln().toInt()
                do {
                    printNombreSistemaOp(arraySistemaOps,idEnterSistemaO)
                    println(
                                "1. Listar Distribuciones\n" +
                                "2. Insertar Distribucion\n" +
                                "3. Actualizar Distribucion\n" +
                                "4. Eliminar Distribucion\n" +
                                "5. Salir"
                    )
                    var opcionDistro = readln()
                    when (opcionDistro) {
                        "1" -> {
                            // Si la opción es 1, muestra la lista de distribuciones del sistema operativo seleccionado
                            printDistribucion(arraySistemaOps, idEnterSistemaO)
                        }
                        "2" -> {
                            // Si la opción es 2, permite al usuario insertar una nueva distribución en ese sistema operativo.
                            var continuar = "y"
                            do {
                                createDistribucion(arraySistemaOps, idEnterSistemaO)
                                //printCancion(arrayPlaylist, idPlt)
                                println("Seguir insertando? y/n")
                                continuar = readln()
                            }while (continuar=="y")
                        }
                        "3" -> {
                            // Si la opción es 3, permite al usuario modificar una distribucion.
                            println("Seleccione el ID de la Distribucion: ")
                            var idDistro = readln().toInt()
                            UpdateDistribucion(arraySistemaOps, idEnterSistemaO,idDistro)
                            printDistribucion(arraySistemaOps, idEnterSistemaO)
                        }
                        "4" -> {
                            // Si la opción es 4, permite al usuario borrar una distro.
                            println("Seleccione el ID de la Distribucion: ")
                            var idDistro = readln().toInt()
                            deleteDistribucion(arraySistemaOps, idEnterSistemaO,idDistro)
                            printDistribucion(arraySistemaOps, idEnterSistemaO)
                        }
                        else -> {
                            // Si la opción es diferente de 1, 2, 3, 4 o 5, se muestra el mensaje de despedida.
                            println("Ninguna de las opciones ha sido seleccionada - Adios")
                            break
                        }
                    }
                } while (opcionDistro!="5")
            }
            else -> {
                // Si la opción es diferente de 1, 2, 3, 4 o 5, se muestra el mensaje de despedida.
                println("Adios")
                break
            }
        }
    } while (opcion!="6")

    // Llama a la función saveFile para guardar los datos en archivo.
    saveFile(arraySistemaOps)
}

fun createSistemaOp(arraySistemaOp:ArrayList<SistemaOp>){ // insertar

    println("Ingrese el nombre del Sistema Operativo")
    var nombreSistemaO= readln()
    println("Ingrese la version")
    var versionSo= readln().toDouble()
    println("Sistema Operativo descontinuado? (s/n)")
    var contSistemaO= readln()

    // Convierte la respuesta del usuario a un valor booleano utilizando la función deStringaBoolean.
    var cont = deStringaBoolean(contSistemaO)

    // Comprueba si el ArrayList de SistemaOp está vacío.
    if(arraySistemaOp.isEmpty()){
        // Si está vacío, agrega el primer SistemaOperativo con ID 1 al ArrayList.
        arraySistemaOp.add(SistemaOp(1,nombreSistemaO,
            versionSo, cont, arrayListOf<Distribucion>()) )
    }else{
        // Si no está vacío, agrega un nuevo SistemaOp al ArrayList con un ID calculado.
        // El ID se obtiene sumando 1 al ID del ultimo SistemaOp en el ArrayList.
        arraySistemaOp.add(SistemaOp(arraySistemaOp.get(arraySistemaOp.size - 1).idSo +1,nombreSistemaO,
            versionSo, cont, arrayListOf<Distribucion>()) )
    }
}

fun deleteSistemaO(arraySistemaOp:ArrayList<SistemaOp>){

    println("Eliminar Sistema Operativo :seleccionar ID: ")
    var idToDelete= readln().toInt()

    // Itera sobre cada elemento en el ArrayList de arraySistemaOp.
    for (element in arraySistemaOp) {
        if (element.idSo==idToDelete) {
            // Imprime el índice de la Playlist en el ArrayList.
            println("el index:"+arraySistemaOp.indexOf(element))

            // Elimina el SitemaOp del ArrayList utilizando su índice.
            arraySistemaOp.removeAt(arraySistemaOp.indexOf(element))
            break
        }
    }
}

fun updteSistemaO(arraySistemaOp: ArrayList<SistemaOp>) {

    println("Seleccione el ID del Sistema Operativo")
    var idToUpdate= readln().toInt()

    // Itera sobre cada elemento en el ArrayList de arraySistemaOp.
    for (element in arraySistemaOp) {
        // Comprueba si el ID del sistemaO coincide con el ID seleccionado.
        if (element.idSo==idToUpdate) {
            println("Ingrese nuevo nombre del Sistema Operativo")
            var upNombreSistemO= readln()
            println("Ingrese nueva version del Sistema Operativo")
            var upVersionSisteO= readln().toDouble()
            println("Sistema Opertivo Descontinuado (s/n)")
            var upContSisteO= readln()

            var cont = deStringaBoolean(upContSisteO)

            // Actualiza los valores del SitemaO con los nuevos valores ingresados.
            // Utiliza el índice de la SitemaO en el ArrayList para reemplazar el elemento existente.
            arraySistemaOp.set(arraySistemaOp.indexOf(element),SistemaOp(element.idSo,upNombreSistemO,
                upVersionSisteO, cont,element.sistemasOperativos))
        }
    }
}

fun printSistemaOLst(arraySistemaOp: ArrayList<SistemaOp>){

    println("%-4s%-20s%-30s%-20s%-10s".format("ID", "Nombre", "Arquitectura", "Descontinuado", "Distribuciones"))
    // Itera sobre cada playlist en arraySistemaOp.
    for (playt in arraySistemaOp)
    {
         println("%-4d%-20s%-30s%-20s%-10s".format(playt.idSo,playt.nombreSo,playt.versionSo
                ,playt.continua,playt.sistemasOperativos))
    }
}

fun printNombreSistemaOp(arraySistemaOp: ArrayList<SistemaOp>, idSistemaOList: Int){

    var idSistemaOLst= idSistemaOList

    // Itera sobre cada elemento en arraySistemaOp.
    for (element in arraySistemaOp) {
        if (element.idSo == idSistemaOLst) {
            println("SISTEMA OPERATIVO: " + element.nombreSo.uppercase())
        }
    }
}


//imprime las distros de un sistemaOp, recibe como parámetros un ArrayList de SistemaOp y un id de sistemaO.
fun printDistribucion(arraySistemaOp: ArrayList<SistemaOp>, idSistemaOList: Int){

    var idSistemaOLst= idSistemaOList

    // Itera sobre cada elemento en arraySistemaOp.
    for (element in arraySistemaOp) {
        if (element.idSo==idSistemaOLst) {
            // Imprime el nombre de la Playlist en mayúsculas.
            println("PLAYLIST " + element.nombreSo.uppercase())
            //println("\nIDP\tID\tNombre\tArtista\tGenero\n")
            println("%-4s%-4s%-20s%-20s%-20s".format("IDSistemaOp", "IDDistro", "NombreDistro", "Arquitectura", "FechaLanzamiento"))

            // Itera sobre cada canción en la lista de canciones de la playlist actual.
            for (song in element.sistemasOperativos)
            {
                //println(""+song.idSistemaO+"\t"+song.idDistro+"\t"+song.nombreDistro+"\t"+ song.arquitectura+"\t"+song.fileManager)
                println("%-4d%-4d%-20s%-20s%-20s".format(song.idSistemaO, song.idDistro, song.nombreDistro, song.arquitectura, song.releaseDate))
            }
        }}
}

fun createDistribucion(arraySistemaOp: ArrayList<SistemaOp>, idSistemaOList: Int){
    println("Insertar distribucion")

    var idSistemaOLt= idSistemaOList

    // Itera sobre cada elemento de arraySistemaOp.
    for (element in arraySistemaOp) {

        if (element.idSo==idSistemaOLt) {
            println("Ingrese el nombre de la Distribucion: ")
            var nombreDistribucion = readln()
            println("Ingrese el nombre de la arquitectura: ")
            var arquitect = readln()
            println("Ingrese fecha de lanzamiento: 2023-07-13")
            var fechaLanz = readln()

            // Verifica si la lista de distribuciones de sistemaOlist actual está vacía.
            if(element.sistemasOperativos.isEmpty()) {
                // Si está vacía, añade una nueva distribucion con el id 1.
                element.sistemasOperativos.add(Distribucion(idSistemaOLt, 1, nombreDistribucion, arquitect, convertStringToDate(fechaLanz)))

            } else {
                // Si no está vacía, añade una nueva Canción con el id de la última canción incrementado en 1.
                element.sistemasOperativos.add(Distribucion(idSistemaOLt, element.sistemasOperativos.get(element.sistemasOperativos.size - 1).idDistro +1, nombreDistribucion, arquitect, convertStringToDate(fechaLanz)))
            }
            println("%-10s%-10s%-30s%-20s%-10s".format("IdSistOp", "IdDistro", "NombreDistro", "Arquitectura", "FechaLanzamiento"))
            // Itera sobre cada distribucion en la lista de distribuciones de la sistemasOperativos actual.
            for (dist in element.sistemasOperativos)
            {
                println("%-10s%-10s%-30s%-20s%-10s".format(dist.idSistemaO,dist.idDistro,dist.nombreDistro,dist.arquitectura,dist.releaseDate))
            }
        }}
}


// Define la función modificarCancion con tres parámetros: un ArrayList de Playlist, un id de playlist y un id de canción.
fun UpdateDistribucion(arraySistemaOp: ArrayList<SistemaOp>, idSistemaOList: Int, idDistribucion:Int)
{
    println("Update distribucion")

    // Almacena el id de la idSistemaOList en una nueva variable. Esto parece redundante porque podrías usar idPlaylst directamente.
    var idSistemaOLt = idSistemaOList

    // Itera sobre cada elemento de arrayPlaylist.
    for (element in arraySistemaOp) {

        // Si idSo actual coincide con idSistemaOLt, entra al bloque.
        if (element.idSo==idSistemaOLt) {

            // Itera sobre cada distribucion en la lista de distribuciones de la playlist actual.
            for (dist in element.sistemasOperativos){

                // Si idDistro coincide con idDistribucion, entra al bloque.
                if (dist.idDistro==idDistribucion){
                    println("Ingrese el nombre de la distribucion: ")
                    var nombreDistribucion = readln()
                    println("Ingrese la arquitectura ")
                    var arquitect = readln()
                    println("Ingrese fecha de lanzameinto: 2023-07-13 ")
                    var fechaLaz = readln()
                    // Modifica la distribucion en el ArrayList de distribuciones de la sistemaoList con los nuevos valores ingresados por el usuario.
                    element.sistemasOperativos.set(element.sistemasOperativos.indexOf(dist),Distribucion(idSistemaOLt, idDistribucion , nombreDistribucion, arquitect, convertStringToDate(fechaLaz)))
                }
            }
        }
    }
}


// Define la función con tres parámetros.
fun deleteDistribucion(arraySistemaOp: ArrayList<SistemaOp>, idSystemasOList: Int, idDistribucion:Int)
{
    println("borrar distribucion")

    // Asigna el valor de idSong a idPlaylst, parece que es un error y se debería omitir esta línea.
    var idSistemasOList= idDistribucion

    // Itera sobre cada elemento de arraarraySistemaOp
    for (element in arraySistemaOp) {

        // Si idSo actual coincide con idSistemasOList, entra al bloque.
        if (element.idSo==idSistemasOList) {

            // Itera sobre cada distribucion en la lista de distribuciones actual.
            for (song in element.sistemasOperativos){

                // Si idDistro coincide con idDistribucion, entra al bloque.
                if (song.idDistro==idDistribucion){

                    // Remueve la distribucion del ArrayList de distribuciones de la sistemasOperativos.
                    element.sistemasOperativos.removeAt(element.sistemasOperativos.indexOf(song))

                    // Rompe el ciclo
                    break
                }
            }
        }
    }
}


fun saveFile(arraySistemaOp: ArrayList<SistemaOp>){
    // Se define la ruta y el nombre del archivo a guardar.
    val nombre="C:/Users/escritorio.virtual15/Documents/AppMobiles/quichimbo-cancino-marcos-antonio-mov-com/Tasks/Homework1/src/main/resources/Outputs/systems_distros.txt"

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

    // Itera a través del array de arraySistemaOp, convierte cada elemento a String y lo añade a la variable salida.
    for(element in arraySistemaOp){
        salida+=("${element.toString()}")
    }

    // Imprime la variable salida en la consola (útil para propósitos de depuración).
    println(salida)

    // Escribe la cadena salida en el archivo.
    bufferedWriter.write("$salida")

    // Cierra el BufferedWriter, liberando así los recursos del sistema.
    bufferedWriter.close()
}

fun readFile(arraySistemaOp: ArrayList<SistemaOp>){
    // Crear un objeto FileReader para leer el archivo 'Playlists.txt'.
    val fr = FileReader("C:/Users/escritorio.virtual15/Documents/AppMobiles/quichimbo-cancino-marcos-antonio-mov-com/Tasks/Homework1/src/main/resources/Outputs/systems_distros.txt")

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
        var (idOs, nombreOs, versionOs, continua, sistemasOperativos)=elemntosArchive[i].split("|")

        // Crear una lista de distribuciones separando el campo de canciones por comas.
        var distribuciones=ArrayList<String>((sistemasOperativos.substring(1,sistemasOperativos.length-1)).split(","))

        // Crear una lista de objetos Distribucion.
        var distros=ArrayList<Distribucion>()

        // Si hay al menos una canción en la línea.
        if(distribuciones[0].length>0){
            // Iterar sobre cada canción.
            for(distribucion in distribuciones) {
                // Separar la información de la canción en sus respectivos campos.
                var (idSistemaO, idDistro, nombreDistro, arquitectura, fechaLanz) = distribucion.split(";")

                // Remover espacios en blanco del idSistemaO.
                idSistemaO=idSistemaO.replace("\\s".toRegex(),"")

                // Crear un objeto Distribucion.
                var distro =Distribucion(idOs.toInt(), idDistro.toInt(), nombreDistro, arquitectura, convertStringToDate(fechaLanz))

                // Añadir la distro a la lista de distros.
                distros.add(distro)
            }
        } else {
            // Imprimir una línea vacía si no hay canciones.
            println("")
        }

        // Crear un objeto SistemaOp.

        var sistemaOp= SistemaOp(idOs.toInt(),nombreOs, versionOs.toDouble(), deStringaBoolean(continua), distros)

        // Añadir el sistemaOp al ArrayList de SistemaOp.
        arraySistemaOp.add(sistemaOp)
    }
}

fun convertStringToDate(dateString: String):Date{
    val formatPattern = "yyyy-MM-dd"
    val format = SimpleDateFormat(formatPattern)
    return format.parse(dateString)
}

fun deStringaBoolean(contOptions: String?): Boolean{
    // Convierte la cadena de entrada a mayúsculas.
    // Kotlin maneja la posibilidad de null de forma segura con el operador '?'.
    var cont= contOptions?.uppercase()

    // Si el dato convertido a mayúsculas es "S", la función devuelve 'true'.
    if (cont=="S"){
        return true
    }
    // En caso contrario (es decir, si el dato no es "S" o es null), la función devuelve 'false'.
    else {
        return false
    }
}


class SistemaOp(
    var idSo:Int,
    var nombreSo:String,
    var versionSo:Double,
    var continua: Boolean,
    var sistemasOperativos: ArrayList<Distribucion>  = arrayListOf<Distribucion>()
) {
    override fun toString(): String {
        return "IdSistemaOp: $idSo| NombreSistemaOp: $nombreSo| VersionSistemaOp: $versionSo| DescontinuadoSistemaOp: $continua| Distros: ${sistemasOperativos.toString()}\n"
    }
}

class Distribucion(
    var idSistemaO:Int,
    var idDistro: Int,
    var nombreDistro:String,
    var arquitectura: String,
    var releaseDate:Date
) {
    override fun toString(): String {
       return "IdSitemaOp: $idSistemaO; IdDistro: $idDistro; NombreDistro: $nombreDistro; ArquitecturaDistro: $arquitectura; FechaLanzamiento: $releaseDate"
    }
}

