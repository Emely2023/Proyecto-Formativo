package Modelo

data class tbPacientes(
    val uuid: String,
    var nombre: String,
    var apellido: String,
    var edad: Int,
    var Enfermedad: String,
    var NumHabitacion: Int,
    var NumCama: Int,
    var MedicamentosAsignados: String,
    var FechaIngreso: String,
    var HoradeMedicacion: String
)
