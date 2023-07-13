import java.awt.AWTException
import java.awt.Robot
import java.awt.event.InputEvent
import java.io.BufferedWriter
import java.io.File
import java.io.FileReader
import java.io.FileWriter
import java.text.SimpleDateFormat
import java.util.*


fun main() {
    // Crea un ArrayList de SistemaOp para almacenar las SistemasOp.
    var arraySistemaOps : ArrayList<SistemaOp> = arrayListOf<SistemaOp>()

    // Lee los datos de archivo y los carga en el ArrayList de arraySistemaOps.
    readFile(arraySistemaOps)

    // Inicia un bucle do-while para mostrar el menú al usuario.
    do {
        println("Operative Systems History")
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
    // Crear un objeto FileReader para leer el archivo .txt
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

        // Crear una lista de distribuciones separando el campo de distribuciones por comas.
        var distribuciones=ArrayList<String>((sistemasOperativos.substring(1,sistemasOperativos.length-1)).split(","))

        // Crear una lista de objetos Distribucion.
        var distros=ArrayList<Distribucion>()

        // Si hay al menos una distro en la línea.
        if(distribuciones[0].length>0){
            // Iterar sobre cada distro.
            for(distribucion in distribuciones) {
                // Separar la información de la distro en sus respectivos campos.
                var (idSistemaO, idDistro, nombreDistro, arquitectura, fechaLanz) = distribucion.split("|")

                // Remover espacios en blanco del idSistemaO.
                idSistemaO=idSistemaO.replace("\\s".toRegex(),"")

                // Crear un objeto Distribucion.
                var distro =Distribucion(idOs.toInt(), idDistro.toInt(), nombreDistro, arquitectura, convertStringToDate(fechaLanz))

                // Añadir la distro a la lista de distros.
                distros.add(distro)
            }
        } else {
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





