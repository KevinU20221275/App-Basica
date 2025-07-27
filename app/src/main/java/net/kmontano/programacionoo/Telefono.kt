package net.kmontano.programacionoo

class Telefono {
    // Propiedades (atributos)
    var numero : String = "7744-1024"
    var marca: String = "Samsung"
    var modelo: String = "Galaxy S23 Ultra"
    var bateria: Int = 100 // Porcentaje de batería con el que se desea que se inicie
    var saldo : Float = 1.25F // inicia con 1.25 de saldo
    var tarifaPorMinuto : Float = 0.05F // tarifa por minuto
    var precio : Float = 200F // precio inicial
    var vendido : Boolean = false // inicialmente el telefono no se a vendido

    // Método para encender el teléfono
    fun encender() {
        if (bateria > 0) {
            println("El telefono esta encendido")
        } else {
            println("No hay suficiente batería")
        }
    }

    // Método para usar batería
    fun usarBateria(cantidad: Int) {
        if (bateria >= cantidad) {
            bateria -= cantidad
            println("Se usaron $cantidad% de batería. Batería restante:$bateria%")
        } else {
            println("Batería insuficiente")
        }
    }

    // Método para cargar batería
    fun cargarBateria(cantidad: Int) {
        if (bateria + cantidad <= 100) {
            bateria += cantidad
            println("Se cargaron $cantidad% de batería. Batería actual:$bateria%")
        } else {
            bateria = 100
            println("Batería llena al 100%")
        }
    }

    // metodo para recargar saldo
    fun recargarSaldo(cantidad: Float) : String {
        if (!vendido) {
            return "Error: Debe comprar el telefono primero"
        }

        saldo += cantidad
        return "Saldo recargado exitosamente, su nuevo saldo es: $saldo"
    }

    // metodo para regalar saldo
    fun regalarSaldo(cantidad: Float, numeroDestino : String) : String {

        if (!vendido) {
            return "Error: Debe comprar el telefono primero"
        }

        if (saldo < cantidad){
            return "Error: Saldo insuficiente"
        } else {
            saldo -= cantidad
            return "Se transfirio $ $cantidad de saldo, al numero $numeroDestino, su nuevo saldo es: $saldo"
        }
    }

    // metodo para cambiar el costo por minuto
    fun cambioDeTarifa(cantidad: Float) : String {
        if (!vendido) {
            return "Error: Debe comprar el telefono primero"
        }

        if (cantidad <= 0){
            return "La tarifa debe ser mayor a cero"
        } else {
            tarifaPorMinuto = cantidad
            return "La tarifa se cambio a $$cantidad por minuto"
        }
    }

    // metodo para asignarle un precio al telefono
    fun comprarTelefono(precio: Float) : String {
        if (precio <= 0){
            return "El precio debe ser mayor a 0"
        } else {
            vendido = true
            this.precio = precio
            return "Compro el telefono por $precio"
        }
    }

    // mostrar informacion
    fun mostrarInformacion() : String {
        val info : String = "📱 Información del Teléfono\n" +
        "Marca: $marca \n" +
        "Modelo: $modelo \n" +
        "Numero: $numero \n" +
        "Precio: $$precio \n" +
        "Saldo: $$saldo \n" +
        "Tarifa por minuto: $$tarifaPorMinuto \n" +
        "Batería: $bateria% \n" +
        "¿Vendido?: ${if (vendido) "Sí" else "No"} \n"

        return info
    }
}