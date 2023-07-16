
class SistemaOp(
    var idSo:Int,
    var nombreSo:String,
    var versionSo:Double,
    var continua: Boolean,
    var sistemasOperativos: ArrayList<Distribucion>  = arrayListOf<Distribucion>()
) {
    override fun toString(): String {
        return "$idSo|$nombreSo|$versionSo|$continua|${sistemasOperativos.toString()}\n"
    }
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

    println("%-4s%-20s%-30s%-20s%-10s".format("ID", "Nombre", "Version", "Descontinuado", "Distribuciones"))
    for (sistOp in arraySistemaOp)
    {
        println("%-4d%-20s%-30s%-20s%-10s".format(sistOp.idSo,sistOp.nombreSo,sistOp.versionSo
            ,sistOp.continua,sistOp.sistemasOperativos))
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
