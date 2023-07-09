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