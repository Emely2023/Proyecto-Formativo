package RecyclerViewHelper

import Emely.Benitez.myapplication.R
import Modelo.tbPacientes
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import Modelo.ClaseConexion


class Adaptador(private var Datos: List<tbPacientes>) : RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val vista =
            LayoutInflater.from(parent.context).inflate(R.layout.activity_item_card, parent, false)

        return ViewHolder(vista)
    }
    override fun getItemCount() = Datos.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pacientes = Datos[position]
        holder.lbNombrePacientes.text = pacientes.nombre
        holder.lbApellidoPacientes.text = pacientes.apellido
        holder.lbEdad.text = pacientes.edad
        holder.lbEnfermedad.text = pacientes.Enfermedad
        holder.lbNumHabitacion.text = pacientes.NumHabitacion
        holder.lbNumCama.text = pacientes.NumCama
        holder.lbMedicamentosAsignados.text = pacientes.MedicamentosAsignados
        holder.lbFechaIngreso.text = pacientes.FechaIngreso
        holder.lbHoradeMedicacion.text = pacientes.HoradeMedicacion

    }



}
