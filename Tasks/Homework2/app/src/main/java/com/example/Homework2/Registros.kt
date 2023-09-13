package com.example.Homework2

class Registros {

    companion object{

        var arregloSistemaODistro = arrayListOf<SistemaO_Distro>()

        init {
            // SistemaO

            EquipoBaseDeDatos.TablaSistemaO!!.crearSistemaO(1,"Windows","Multiproposito","1985","Microsoft","23")

            EquipoBaseDeDatos.TablaSistemaO!!.crearSistemaO(2,"Ubuntu","Multiproposito","1995","Canonical","32")

            EquipoBaseDeDatos.TablaSistemaO!!.crearSistemaO(3,"Blackberry","Blackberry","2009","Blackberry","12")


            // Distros
            EquipoBaseDeDatos.TablaSistemaO!!.crearDistro(1,"Windows vista","X86","16","FileExplorer","2007")

            EquipoBaseDeDatos.TablaSistemaO!!.crearDistro(2,"Xubutnu","X86","16","Gnome","2022")

            EquipoBaseDeDatos.TablaSistemaO!!.crearDistro(3,"Blackberry 2.0","RISC","2","Explorer","2010")


            // SistemaO y Distros
            arregloSistemaODistro.add(
                SistemaO_Distro(1, 1,2)
            )
            arregloSistemaODistro.add(
                SistemaO_Distro(2, 1, 3)
            )
            arregloSistemaODistro.add(
                SistemaO_Distro(3,2, 3)
            )
            arregloSistemaODistro.add(
                SistemaO_Distro(4,2,1)
            )
            arregloSistemaODistro.add(
                SistemaO_Distro(5,2,2)
            )
            arregloSistemaODistro.add(
                SistemaO_Distro(6,3,2)
            )

        }

    }
}