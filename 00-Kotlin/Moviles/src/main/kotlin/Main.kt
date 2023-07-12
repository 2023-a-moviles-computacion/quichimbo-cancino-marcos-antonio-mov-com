fun main(args: Array<String>) {
    println("Hello World!")

    //tipos de variables
    //INMUTABLES (  No se reasignan "=")
    val inmutable: String = "Adrian";
    //inmutable = "vicente"

    //MUTABLES (re asignar)
    var mutable: String = "Vicente";
    mutable = "adrian"

    //val > var


    //Duck typing
    val ejemploVariable = "Adrian Eguez"
    var edadEjemplo: Int = 12
    ejemploVariable.trim()//quita los espacios antes y después del string
    //ejemploVariable = edadEjemplo;

    //variables primitivas
    var nombreProfesor: String = "Adrian Eguez"
    var sueldo: Double = 1.2
    val estadoCivil: Char = 'C'
    val mayorEdad: Boolean = true

    //clases en java
    //val fechaNacimiento: Date= Date()

    //if y else funcionan igual
    //no hay switch

    //SWITCH
    val estadoCivilWhen = "C"
    when (estadoCivilWhen) {
        ("C") -> {
            print("casado")
        }

        "S" -> {
            print("Soltero")
        }

        else -> {
            print("no sabemos")
        }
    }

    val coqueto = if (estadoCivilWhen == "S") "si" else "no"


    //void -> unit
    fun imprimirNombre(nombre: String): Unit {
        println("nombre: ${nombre}")

        //explicación de la función calcularSueldok
        calcularSueldo(10.00)//primer parámetro es requerido
        calcularSueldo(10.00, 12.00, 20.00)
        //parámetros nombrados
        calcularSueldo(10.00, bonoEspecial = 20.00)//named parameters
        calcularSueldo(bonoEspecial = 20.00, sueldo = 10.00, tasa = 12.00)
    }

    fun calcularSueldo(
        sueldo: Double, //requerido
        tasa: Double = 12.00, //opcional (defecto)
        bonoEspecial: Double? = null, //Opcion mull ->nulltable, puede ser nullo
    ): Double {
        //MUY IMPORTANTE
        //Int->Int (nullable)
        //String -> String? (nullable)
        //Date -> Date= (nullable)
        //puede tener un valor double o puede a su vez ser nul0
        //si lo llamo y no tiene asignado algún valor, es null,
        //no da errores
        if (bonoEspecial == null) {
            return sueldo * (100 / tasa)
        } else {
            return sueldo * (100 / tasa) + bonoEspecial
        }

    }

    abstract class NumerosJava {
        protected val numerouno: Int
        protected val numeroDos: Int

        constructor(
            uno: Int,
            dos: Int
        ) {//Bloque de código del constructor
            this.numeroDos = dos
            this.numerouno = uno
            print("Inicializado")
        }
    }

    abstract class Numeros(
// constructor PRIMARIO ya estamos definiendo el constructor primario
        //Ejemplo:
        //Uno: int, (parámetro (sin modificador de acceso))
        //public var uno: Int, //propieda pública de la clase numeros.uno
        //var uno: Int, //propiedad de la clasle (por defecto es PUBLIC)
        //public var uno: Int, es lo mismo

        protected val numeroUno: Int, //propiedad de la clase protected numeros.numeroUno
        protected val numeroDos: Int, //propiedad de la clase protected numeros.numerosDoss¿
    ) {
        // var cedula: string = "" ( public es por defecto)
        //private valorCalculado: int = 0 (private)
        init {
            this.numeroUno; this.numeroDos; //this es opcional
            numeroUno; numeroDos
            println("inicializado")
        }
    }

    class Suma(//constructor primario suma
        uno: Int, //parametro
        dos: Int//parametro
    ) : Numeros(uno, dos) {//<-Constructor del padre
    init {//Bloque constructor primario
        this.numeroUno; numeroUno;
        this.numeroDos; numeroDos;
    }

        constructor(//segundo constructor
            uno: Int?,//parametros
            dos: Int//parametros
        ) : this(//llamada constructor primario
            if (uno == null) 0 else uno, dos
        ) {//si necesitamos bloque de código lo usamos
            numeroUno;
        }

        constructor(//tercer constructor
            uno: Int, //parametros
            dos: Int? //parametros
        ) : this(//llamada construcor primario
            uno,
            if (dos == null) 0 else uno
        )//si no lo necesitamos al bloque de código "{}" lo omitimos

        constructor(//cuarto constructor
            uno: Int?, //parámetros
            dos: Int?  //parámetros
        ) : this(
//llamada construcor primario
            //uno,dos,
            if (uno == null) 0 else dos,
            if (dos == null) 0 else uno,
        )

        public fun sumar(): Int { //public por defecto, o suar private o protected
            var total = numeroUno + numeroDos
            agregarHistorial(total)
            return total
        }

        companion object { //atributos y métodos "compartidos"
            //entre las instancias
            val pi = 3.14
            fun elevarAlCuadrado(num: Int): Int {
                return num * num
            }

            val historialSumas = arrayListOf<Int>()
            fun agregarHistorial(valorNuevaSuma: Int) {
                historialSumas.add(valorNuevaSuma)
            }
        }}
        //ARREGLOS
        //TIPOS DE ARREGLOS

        //ARREGLO ESTÁTICO -> PUEDE MODIFICAR LOS ELEMENTOS
        val arregloEstatico: Array<Int> = arrayOf<Int>(1, 2, 3)
        println(arregloEstatico)

        //Arreglo dinámicos
        val arregloDinamico: ArrayList<Int> = arrayListOf<Int>(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        println(arregloDinamico)
        arregloDinamico.add(11)
        arregloDinamico.add(13)
        println(arregloDinamico)

        //Operadores -> sirven par los arreglos estático sy dinámicos

        //FOR EACH -> Unit
        //iterar un arreglo
    val respuestaForEach: Unit = arregloDinamico
        .forEach{ valorActual: Int ->
            println("valor actual: ${valorActual}")
        }
    arregloDinamico.forEach{println(it)}//it (en ingles eso) significa el elemento

    arregloEstatico.forEachIndexed{indice: Int, valorActual: Int ->
        println("valor ${valorActual}Indice: ${indice}")
    }
    println(respuestaForEach)



    //MAP -> Muta el arreglo (Cambia el arreglo)
    //1) Enviemos el nuevo valor de la iteración
    //2) NOs devuelve es un Nuevo Arreglo dcon los valores modificados

    val respuestaMap: List<Double> = arregloDinamico
        .map { valorActual: Int ->
            return@map valorActual.toDouble()+100.00
        }
    print(respuestaMap)
    val respuestaMapDos = arregloDinamico.map { it + 15 }

    //Filter -> FILTRAR EL ARREGLO
    //1)DEVOLVER UNA EXPRESIÓN (TRUE o FALSE)
    //2) Nuevo arreglo filtrado
    val respuestaFilter: List<Int> = arregloDinamico
        .filter { valorActual: Int->
            val mayoresACinco: boolean = valorActual > 5//Expresión Condición
            return@filter mayoresACinco
        }
    val respuestaFilterDos = arregloDinamico.filter {it <= 5}
    println(respuestaFilter)
    println(respuestaFilterDos)

    //OR NAD
    //OR -> ANY (Alguno cumple?)
    //AND -> All (todos cumplen?)

    val respuestaAny: Boolean = arregloDinamico
        .any{valorActual: Int ->
            return@any (valorActual > 5)
        }
    println(respuestaAny) //true

    val respuestaAll: Boolean = arregloDinamico
        .all {valorActual: Int ->
            return@all (valorActual >5)
        }
    println(respuestaAll) //false

    //REDUCE -> Valor acumulado
    //valor acumulado = 0 (Siempre 0 en lenguaje Kotlin)
    //[1,2,3,4,5] -> Súmeme todos los valores dela rreglo
    //valorIteracion1 = valorEmpieza + 1 = 0 + 1= 1 -> Iteracion 1
    //ValorIteracion2 = valorEmpieza1 + 2 = 1 + 2 = 3 -> Iteracion 2
    //ValorIteracion3 = valorEmpieza2 + 3 = 3 + 3 = 6 -> Iteracion 3
    //ValorIteracion4 = valorEmpieza3 + 4 = 6 + 4 = 10 -> Iteción 4

    val respuestaReduce: Int = arregloDinamico
        .reduce{//acumulado = 0 -> Siempre empieza en 0
            acumulado: Int, valorActual: Int ->
            return@reduce (acumulado + valorActual) //aqui se va hacindo las iteraciones, lógica de negocio
        }
    println(respuestaReduce) //78 

}

















