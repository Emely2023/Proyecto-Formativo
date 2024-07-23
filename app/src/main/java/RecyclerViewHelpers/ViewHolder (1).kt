package RecyclerViewHelper

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import Emely.Benitez.myapplication.R

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val lbNombrePacientes = view.findViewById<TextView>(R.id.lbNombrePaciente)
    val lbApellidoPacientes = view.findViewById<TextView>(R.id.lbApellidoPaciente)
    val lbEdad = view.findViewById<TextView>(R.id.lbEdadPaciente)
    val lbEnfermedad = view.findViewById<TextView>(R.id.lbEnfermedad)
    val lbNumCama = view.findViewById<TextView>(R.id.lbNumerodeCama)
    val lbNumHabitacion = view.findViewById<TextView>(R.id.lbNumerodeHabitacion)
    val lbHoradeMedicacion = view.findViewById<TextView>(R.id.lbHoradeMedicacion)
    val lbMedicamentosAsignados = view.findViewById<TextView>(R.id.lbMedicamentosAsignados)
    val lbFechaIngreso = view.findViewById<TextView>(R.id.lbFechaIngreso)

}
