import java.util.*

class Distribucion(
    var idSistemaO:Int,
    var idDistro: Int,
    var nombreDistro:String,
    var arquitectura: String,
    var releaseDate: Date
) {
    override fun toString(): String {
        //return "IdSO: $idSistemaO; IdDistro: $idDistro; NombreDistro: $nombreDistro; ArquitecturaDistro: $arquitectura; FLanzamientoDistro: $releaseDate"
        return "$idSistemaO|$idDistro|$nombreDistro|$arquitectura|$releaseDate"
    }
}


//imprime las distros de un sistemaOp, recibe como parámetros un ArrayList de SistemaOp y un id de sistemaO.
fun printDistribucion(arraySistemaOp: ArrayList<SistemaOp>, idSistemaOList: Int){

    var idSistemaOLst= idSistemaOList

    // Itera sobre cada elemento en arraySistemaOp.
    for (element in arraySistemaOp) {
        if (element.idSo==idSistemaOLst) {
            println("Sistema Operativo " + element.nombreSo.uppercase())
            println("%-4s%-4s%-20s%-20s%-20s".format("IDSistemaOp", "IDDistro", "NombreDistro", "Arquitectura", "FechaLanzamiento"))

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
                // Si no está vacía, añade una nueva  con el id de la última  incrementado en 1.
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


fun UpdateDistribucion(arraySistemaOp: ArrayList<SistemaOp>, idSistemaOList: Int, idDistribucion:Int)
{
    println("Update distribucion")

    // Almacena el id de la idSistemaOList en una nueva variable
    var idSistemaOLt = idSistemaOList

    for (element in arraySistemaOp) {

        // Si idSo actual coincide con idSistemaOLt, entra al bloque.
        if (element.idSo==idSistemaOLt) {

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
