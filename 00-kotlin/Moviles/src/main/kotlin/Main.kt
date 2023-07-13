import java.nio.DoubleBuffer
import java.security.KeyStore.TrustedCertificateEntry
import java.util.Date

fun main(args: Array<String>) {
    println("Hello World!")
    println("Me llamo marcos")

    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
    //println("Program arguments: ${args.joinToString()}")

    // TIPOS DE VARIABLES

    //INMUTABLES (NO se reasignan "=")
	//comentario solo para el commit 
    val inmutable: String = "Marcos"
    //inmutable = "Pedro" can not be reasigned

    //MUTABLES (SI se reasignan)
    var mutable: String = "Vicente"
    mutable = "Pedro"


    //preferimos usar el val en lugar del var
    //val > var

    // DUCK TYPING
    var ejemploVariable = "Adrian Eguez"
    val edadEjemplo: Int = 12
    ejemploVariable.trim()
    //ejemploVariable = edadEjemplo

    // VARIABLES PRIMITIVAS
    val nombreProfesor: String = "Adrian Eguez"
    val sueldo: Double = 1.2
    val estadoCivil:Char = 'C'
    val mayorEdad: Boolean = true

    //CLASES DE JAVA
    val fechanNacimiento: Date = Date()

    //SWITCH
    val estadoCivilWhen = "C"
    when (estadoCivilWhen){
        ("C") -> {
            println("Casado")
        }
        ("S") -> {
            println("Soltero")
        }
        else -> {
            println("No sabemos")
        }
    }

    val coqueteo = if (estadoCivilWhen == "S") "Si" else "No"

    calcularSueldo(100.00) //REQUERIDO
    calcularSueldo(10.00, 12.00, 20.00)
    //NAMED PARAMETROS
    calcularSueldo(10.00, bonoEspecial = 20.00)
    calcularSueldo(bonoEspecial = 20.00, sueldo = 10.00, tasa = 14.00) //no importa el orden

    val sumaUno = Suma(1,1)
    val sumaDos = Suma(null, 1)
    val sumaTres = Suma(1,null)
    val sumaCuatro = Suma(null, null)
    sumaUno.sumar()
    sumaDos.sumar()
    sumaTres.sumar()
    sumaCuatro.sumar()
    println(Suma.pi)
    println(Suma.elevarCuadrado(2))
    println(Suma.historialSumas)

    //ARREGLOS

    //Tipos de arreglos

    // arreglo estatico
    val arregloEstatico: Array<Int> = arrayOf<Int>(1,2,3)
    println(arregloEstatico)

    //Arreglos dinamicos
    val arregloDinamico: ArrayList<Int> = arrayListOf<Int>(1,2,3,4,5,6,7,8,9,10)
    println(arregloDinamico)
    arregloDinamico.add(11)
    arregloDinamico.add(12)
    println(arregloDinamico)


    //TIPOS DE OPERADORES -> SIRVEN PARA LOS ARREGLOS ESTATICOS Y DINAMICOS

    println("=====================================================================")
    println("For each")
    // 1. FOR EACH -> Unit
    // sirve para iterar un arreglo
    //forma 1 de iterar
    val respuestaForEach: Unit = arregloDinamico
        .forEach{valorActual: Int ->
        println("Valor actual: ${valorActual}")}
    //forma 2 de iterar
    arregloDinamico.forEach { print(it) }
    //forma 3 de iterar
    arregloEstatico
        .forEachIndexed() { indice: Int, valorActual: Int ->
            println("Valor ${valorActual} Indice: ${indice}")
        }
    println(respuestaForEach)
    
    println("=====================================================================")
    println("Map")
    //MAP -> Muta el arreglo (Cambia el arreglo)
    // 1) enviemos el nuevo valor de la iteracion
    // 2) nos devuelve es un nuevo arreglo con los valores modificados

    val respuestaMap: List<Double> = arregloDinamico
        .map { valorActual: Int ->
            return@map valorActual.toDouble() + 100.00 //al arreglo original le sumo 100
        }
    println(respuestaMap)
    val respuestaMapDos = arregloDinamico.map { it + 15 }
    println(respuestaMapDos)

    println("=====================================================================")
    println("Filter")
    //FILTER -> FILTRAR EL ARREGLO
    // 1) DEVOLVER UNA EXPRESION (TRUE O FALSE)
    // 2) NUEVO ARREGLO FILTRADO
    //forma 1
    val respuestaFilter: List<Int> = arregloDinamico
        .filter { valorActual: Int ->
            val mayoresACinco: Boolean = valorActual > 5// expresion condicion
            return@filter mayoresACinco
        }
    //forma 2
    val respuestaFilterDos = arregloDinamico.filter { it <=5 }
    println(respuestaFilter)
    println(respuestaMapDos)

    println("=====================================================================")
    println("OR AND")
    // OR AND
    // OR -> ANY (ALGUNO CUMPLE)
    // AND -> ALL (TODOS CUMPLEN)
    val respuestaAny: Boolean = arregloDinamico
        .any{ valorActual: Int ->
        return@any (valorActual > 5)}
    println(respuestaAny)//true

    val respuestaAll: Boolean = arregloDinamico
        .all { valorActual: Int ->
            return@all (valorActual > 5)}
    println(respuestaAll) //false
    println("=====================================================================")
    println("REDUCE")
    //sirve para devolver el total del carrito de compras
    //REDUCE -> VALOR ACUMULADO
    // [1,2,3,4,5] -> Sumeme todos los valores del arreglo
    // valorIteracion1 = valorEmpieza + 1 = 0 + 1 = 1 -> Iteracion1
    // valorIteracion2 = valorIteracion1 + 2 = 1 + 2 = 3 -> Iteracion2
    // valorIteracion3 = valorIteracion2 + 3 = 3 + 3 = 6 -> Iteracion3
    // valorIteracion4 = valorIteracion3 + 4 = 6 + 4 = 10 -> Iteracion4
    // valorIteracion5 = valorIteracion4 + 5 = 10 + 5 = 15 -> Iteracion5

    val respuestaReduce: Int = arregloDinamico
        .reduce {// acc, i -> acumulado = 0 -> SIEMPRE EMPIEZA EN 0
                    acumulado:Int, valorActual: Int ->
            return@reduce (acumulado + valorActual) //logica del negocio
        }
    println(respuestaReduce)
}
//void -> unit
fun imprimirNombre(nombre: String): Unit{
    println("Nombre: ${nombre}")
}

fun calcularSueldo(
    sueldo: Double, //requerido
    tasa: Double = 12.00, // Opcional (defecto)
    bonoEspecial: Double? = null, // Opcion null -> nullable // puede ser null
): Double{
    //int -> int?
    //puede ser nullo
    // Date -> Date? (nullable)
    if(bonoEspecial == null){
        return sueldo * (100/tasa)
    }else{
        return sueldo * (100/tasa) + bonoEspecial
    }
}

abstract class NumerosJava{
    protected val numeroUno: Int
    private  val numeroDos: Int
    constructor(
        uno:Int,
        dos:Int
    ){//BLOQUE DE CODIGO DEL CONSTRUCTOR
        this.numeroUno = uno
        this.numeroDos = dos
        println("Inicializando")
    }
}

abstract class Numeros(//constructor PRIMARIO
    //ejemplo:
    //uno: Int, (parametro(sin modificador de acceso))
    //private var uno: Int, //propiedad publica clase numeros.uno
    //var uno: Int, //propiedad de la clase (por defecto es public)
    //public var uno: Int,

    protected val numeroUno: Int, //propiedad de la clase protected numeros.numeroUno
    protected val numeroDos: Int, //propiedad de la clase protected numeros.numeroDos
){
    //var cedula: String = "" (public es por defecto)
    // private valorCalculado: Int = 0 (private)

    //BLOQUE DE CODIGO INIT COMO EL CONSTRUCTOR
    init {
        this.numeroUno; this.numeroDos; //this is opcional
        numeroUno; numeroDos; // sin el this, es lo mismo
        println("Inicializado")
    }

}

class Suma( //Constructor primario Suma
    uno: Int, //parametro
    dos: Int //parametro
): Numeros(uno, dos){ // <- este es el constructor del padre
    init { //bloque construcor primario
        this.numeroUno; numeroUno
        this.numeroDos; numeroDos
    }
    constructor(//segundo constructor
        uno:Int?, //parametros
        dos:Int //parametros
    ): this(//llamada al constructor primario
        if(uno == null) 0 else uno,
        dos
       ){//si necesitamos un bloque de codigo lo usamos
            numeroUno
         }

    constructor(//tercer constructor
        uno:Int, //parametros
        dos:Int? //parametros)
    ): this( //llamada al constructor primario
        uno,
        if (dos == null) 0 else uno
    )// si no necesitamos al bloque de codigo lo omitimos {}

    constructor(//cuarto constructor
        uno: Int?, //parametros
        dos: Int?//parametros
    ): this( //llamada al constructor primario
        if (uno == null) 0 else uno,
        if (dos == null) 0 else uno
    )

    public fun sumar(): Int{ //public por defecto, o usar private o protected
        val total = numeroUno + numeroDos

        agregarHistorial(total)
        return total
    }

    companion object { //atributos y metodos compartidos
        //entre las instancias
        val pi = 3.14
        fun elevarCuadrado(num: Int): Int{
            return num * num
        }
        val historialSumas = arrayListOf<Int>()
        fun agregarHistorial(valorNuevaSuma: Int){
            historialSumas.add(valorNuevaSuma)
        }
    }







}












