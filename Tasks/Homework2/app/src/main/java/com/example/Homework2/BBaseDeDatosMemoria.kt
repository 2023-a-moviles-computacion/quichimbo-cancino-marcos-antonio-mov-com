package com.example.Homework2

class BBaseDeDatosMemoria {

    companion object{
        var arregloSistemaO = arrayListOf<SistemaO>()
        var arregloDistribucion = arrayListOf<Distribucion>()
        var arregloSistemaO_Distribucion = arrayListOf<SistemaO_Distro>()

        init {
            // Sistema Operativo
            arregloSistemaO.add(
                SistemaO(1,"Windows", "Multiprosito",1985,"Microsoft",13)
            )
            arregloSistemaO.add(
                SistemaO(2,"Ubuntu", "Multipropisito",1995,"Canonical",15)
            )
            arregloSistemaO.add(
                SistemaO(3,"BlacberrySO", "Cellphone",2008,"Blackberry",17)
            )

            // Distribuciones
            arregloDistribucion.add(
                Distribucion(1,"Windows_Vista","X86",16,"FileExplorer",2007)
            )
            arregloDistribucion.add(
                Distribucion(2,"Xubuntu","X86",16,"Gnome",2013)
            )
            arregloDistribucion.add(
                Distribucion(3,"Blacberry_2","RISC",4,"Explorador",2010)
            )
        }

    }

}