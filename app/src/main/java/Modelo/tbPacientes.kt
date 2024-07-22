package Modelo

data class tbPacientes(
    val uuid: String,
    val nombre: String,
    val apellido: String,
    val edad: String,
    val Enfermedad: String,
    val NumHabitacion: String,
    val NumCama: String,
    val MedicamentosAsignados: String,
    val FechaIngreso: String,
    val HoradeMedicacion: String
)
