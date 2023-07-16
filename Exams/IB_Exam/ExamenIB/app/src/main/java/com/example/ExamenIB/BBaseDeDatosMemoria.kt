package com.example.ExamenIB

class BBaseDeDatosMemoria {

    companion object{
        var arregloSistemaO = arrayListOf<SistemaO>()
        var arregloDistribucion = arrayListOf<Distribucion>()
        var arregloSistemaODistribucion = arrayListOf<SistemaO_Distribucion>()

        init {
            // SistemaO
            arregloSistemaO.add(
                SistemaO(1,"Windows", "All Desktop Propouse",1985,"FileExplorer",12)
            )
            arregloSistemaO.add(
                SistemaO(2,"Ubuntu", "All Desktop Propouse",2004,"Gnome",35)
            )
            arregloSistemaO.add(
                SistemaO(3,"Backberry OS", "Cellphone OS",1999,"FileExplorer",8)
            )

            // Distribuciones
            arregloDistribucion.add(
                Distribucion(1,"Xubuntu","X64",20," Creative Commons",2006)
            )
            arregloDistribucion.add(
                Distribucion(2,"Windows","X32",2003,"Proprietary License",2003)
            )
            arregloDistribucion.add(
                Distribucion(3,"Backberry OS 2.0","X86",2,"Copyleft License",2008)
            )

            // Sistemas Operativos y distribuciones
            arregloSistemaODistribucion.add(
                SistemaO_Distribucion(1, "Windwos", 1,1)
            )
            arregloSistemaODistribucion.add(
                SistemaO_Distribucion(2, "Windows", 1, 2)
            )
            arregloSistemaODistribucion.add(
                SistemaO_Distribucion(3, "Windows",2, 3)
            )
            arregloSistemaODistribucion.add(
                SistemaO_Distribucion(4, "Ubuntu",2,1)
            )
            arregloSistemaODistribucion.add(
                SistemaO_Distribucion(5, "Ubuntu",2,2)
            )
            arregloSistemaODistribucion.add(
                SistemaO_Distribucion(6, "Ubuntu",2,3)
            )

        }

    }

}