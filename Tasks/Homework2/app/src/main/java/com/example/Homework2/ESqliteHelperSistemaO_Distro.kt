package com.example.Homework2

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class ESqliteHelperSistemaO_Distro(
    contexto: Context?,
) : SQLiteOpenHelper(contexto, "moviles", null, 1) {

    override fun onCreate(db: SQLiteDatabase?) {
        val scriptSQLCrearTablaEntrenador:ArrayList<String> = arrayListOf(
            """
               CREATE TABLE DISTRO(
               id INTEGER PRIMARY KEY,
               nombreDistro VARCHAR(50),
               arquitecturaDistro VARCHAR(50),
               coresDistro VARCHAR(50),
               gestorFilesDistro VARCHAR(50),
               releaseDistro VARCHAR(50)
               );
             ""","""
               CREATE TABLE SISTEMAO(
               id INTEGER PRIMARY KEY,
               nombreSistemaO VARCHAR(50),
               descripcionSistemaO VARCHAR(50),
               releaseYearSistemaO VARCHAR(50),
               ownerSistemaO VARCHAR(50),
               numDistros VARCHAR(50)
               );
            """)
        for (i in scriptSQLCrearTablaEntrenador){
            db!!.execSQL(i)
        }
        Log.i("creart", "SistemaO")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }
    fun crearSistemaO(id:Int, nombreSisetmaO:String, descripcionSistemaO:String, releaseYearSistemaO:String,
                      ownerSistemaO:String, numDistros:String ): Boolean{
        val basedatosEscritura = writableDatabase
        val valoresAGuardar = ContentValues()
        valoresAGuardar.put("id",id)
        valoresAGuardar.put("nombreSistemaO", nombreSisetmaO)
        valoresAGuardar.put("descripcionSistemaO", descripcionSistemaO)
        valoresAGuardar.put("releaseYearSistemaO", releaseYearSistemaO)
        valoresAGuardar.put("ownerSistemaO", ownerSistemaO)
        valoresAGuardar.put("numDistros", numDistros)
        val resultadoGuardar = basedatosEscritura
            .insert(
                "SISTEMAO",
                null,
                valoresAGuardar
            )
        basedatosEscritura.close()
        return if(resultadoGuardar.toInt() == -1) false else true

    }

    fun listarSistemaO(): ArrayList<SistemaO>{
        var lista = arrayListOf<SistemaO>()
        var sistemaO: SistemaO?
        val baseDatosLectura = readableDatabase
        val scriptConsultarUsuario = "SELECT * FROM SISTEMAO"
        val resultadoConsultaLectura = baseDatosLectura.rawQuery(
            scriptConsultarUsuario,
            null
        )
        if(resultadoConsultaLectura.moveToFirst()){
            do {
                sistemaO=SistemaO(0,"","",0,"",0)
                sistemaO!!.idSO = resultadoConsultaLectura.getInt(0)
                sistemaO.nombreSO= resultadoConsultaLectura.getString(1)
                sistemaO.descripcionSO= resultadoConsultaLectura.getString(2)
                sistemaO.releaseYearSO= resultadoConsultaLectura.getString(3).toInt()
                sistemaO.ownerSO= resultadoConsultaLectura.getString(4)
                sistemaO.numDistribuciones= resultadoConsultaLectura.getString(5).toInt()
                lista.add(sistemaO)
            }while (resultadoConsultaLectura.moveToNext())
        }
        resultadoConsultaLectura.close()
        baseDatosLectura.close()
        return lista
    }
    fun actualizarSistemaO(idA:Int, nombreSistema:String, descripcionSistemaO:String, releaseYearSistemaO:String,
                           ownerSistemaO:String, numDistros:String ):Boolean{
        var lista= EquipoBaseDeDatos.TablaSistemaO!!.listarSistemaO()
        val id=lista[idA].idSO.toString()
        val conexionEscritura = writableDatabase
        val valoresAActualizar = ContentValues()
        valoresAActualizar.put("nombreSistemaO", nombreSistema)
        valoresAActualizar.put("descripcionSistemaO", descripcionSistemaO)
        valoresAActualizar.put("releaseYearSistemaO", releaseYearSistemaO)
        valoresAActualizar.put("ownerSistemaO", ownerSistemaO)
        valoresAActualizar.put("numDistros", numDistros)
        val resultadoActualizacion = conexionEscritura
            .update(
                "SISTEMAO", // Nombre tabla
                valoresAActualizar,  // Valores a actualizar
                "id=?", // Clausula Where
                arrayOf(
                    id.toString()
                ) // Parametros clausula Where
            )
        conexionEscritura.close()
        return if (resultadoActualizacion == -1) false else true
    }
    fun eliminarSistemaO(id:Int):Boolean{
        var lista= EquipoBaseDeDatos.TablaSistemaO!!.listarSistemaO()
        val idR=lista[id].idSO.toString()
        val conexion= writableDatabase
        val resultadoEliminacion=conexion
            .delete("SISTEMAO","id=?", arrayOf(idR))
        conexion.close()
        return if (resultadoEliminacion.toInt() == -1) false else true
    }
    fun crearDistro(id:Int, nombreDistro:String, arquitecturaDistro:String, coresDistro: String,
                    gestorFilesDistro: String, releaseDistro: String):Boolean{
        val basedatosEscritura = writableDatabase
        val valoresAGuardar = ContentValues()
        valoresAGuardar.put("id",id)
        valoresAGuardar.put("nombreDistro", nombreDistro)
        valoresAGuardar.put("arquitecturaDistro", arquitecturaDistro)
        valoresAGuardar.put("coresDistro", coresDistro)
        valoresAGuardar.put("gestorFilesDistro", gestorFilesDistro)
        valoresAGuardar.put("releaseDistro", releaseDistro)
        val resultadoGuardar = basedatosEscritura
            .insert(
                "DISTRO",
                null,
                valoresAGuardar
            )
        basedatosEscritura.close()
        return if(resultadoGuardar.toInt() == -1) false else true

    }
    fun listarDistro(): ArrayList<Distribucion>{
        var lista = arrayListOf<Distribucion>()
        var distribucion: Distribucion?
        val baseDatosLectura = readableDatabase
        val scriptConsultarUsuario = "SELECT * FROM DISTRO"
        val resultadoConsultaLectura = baseDatosLectura.rawQuery(
            scriptConsultarUsuario,
            null
        )
        if(resultadoConsultaLectura.moveToFirst()){
            do {
                distribucion= Distribucion(0,"","",0,"",0)
                distribucion!!.idDistro= resultadoConsultaLectura.getInt(0)
                distribucion.nombreDistro= resultadoConsultaLectura.getString(1)
                distribucion.arquitecturaDistro= resultadoConsultaLectura.getString(2)
                distribucion.coresDistro= resultadoConsultaLectura.getString(3).toInt()
                distribucion.gestorFilesDistro= resultadoConsultaLectura.getString(4)
                distribucion.releaseDistro= resultadoConsultaLectura.getString(5).toInt()
                lista.add(distribucion)
            }while (resultadoConsultaLectura.moveToNext())
        }
        resultadoConsultaLectura.close()
        baseDatosLectura.close()
        return lista
    }

    fun actualizarDistro(id:Int, nombreDistro:String, arquitecturaDistro:String, coresDistro: String,
                         gestorFilesDistro: String, releaseDistro: String ):Boolean{

        val conexionEscritura = writableDatabase
        val valoresAActualizar = ContentValues()
        valoresAActualizar.put("nombreDistro", nombreDistro)
        valoresAActualizar.put("arquitecturaDistro", arquitecturaDistro)
        valoresAActualizar.put("coresDistro", coresDistro)
        valoresAActualizar.put("gestorFilesDistro", gestorFilesDistro)
        valoresAActualizar.put("releaseDistro", releaseDistro)
        val resultadoActualizacion = conexionEscritura
            .update(
                "DISTRO", // Nombre tabla
                valoresAActualizar,  // Valores a actualizar
                "id=?", // Clausula Where
                arrayOf(
                    id.toString()
                ) // Parametros clausula Where
            )
        conexionEscritura.close()
        return if (resultadoActualizacion == -1) false else true
    }
    fun eliminarDistros(id:Int):Boolean{
        val conexion= writableDatabase
        val resultadoEliminacion=conexion
            .delete("DISTRO","id=?", arrayOf(id.toString()))
        conexion.close()
        return if (resultadoEliminacion.toInt() == -1) false else true
    }

}