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
import android.app.AlertDialog
import android.content.Intent
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast


class Adaptador(private var Datos: List<tbPacientes>) : RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val vista =
            LayoutInflater.from(parent.context).inflate(R.layout.activity_item_card, parent, false)

        return ViewHolder(vista)
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    fun UpdateList(newList: List<tbPacientes>)
    {
        Datos = newList
        notifyDataSetChanged()
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pacientes = Datos[position]
        holder.lbNombrePacientes.text = pacientes.nombre
        holder.lbApellidoPacientes.text = pacientes.apellido
        holder.lbEdad.text = pacientes.edad.toString()
        holder.lbEnfermedad.text = pacientes.Enfermedad
        holder.lbNumHabitacion.text = pacientes.NumHabitacion.toString()
        holder.lbNumCama.text = pacientes.NumCama.toString()
        holder.lbMedicamentosAsignados.text = pacientes.MedicamentosAsignados
        holder.lbFechaIngreso.text = pacientes.FechaIngreso
        holder.lbHoradeMedicacion.text = pacientes.HoradeMedicacion

        fun ActualizarPantalla(Nombre: String,
                               Apellido: String,
                               Edad: Int,
                               Enfermedad: String,
                               NumHabitacion: Int,
                               NumCama: Int,
                               MedicamentosAsignados: String,
                               FechaIngreso: String,
                               HoradeMedicacion: String,
                               UUID: String){
            val index = Datos.indexOfFirst { it.uuid == UUID }
            Datos[index].nombre = Nombre
            Datos[index].apellido = Apellido
            Datos[index].edad = Edad
            Datos[index].Enfermedad = Enfermedad
            Datos[index].NumHabitacion = NumHabitacion
            Datos[index].NumCama = NumCama
            Datos[index].MedicamentosAsignados = MedicamentosAsignados
            Datos[index].FechaIngreso = FechaIngreso
            Datos[index].HoradeMedicacion = HoradeMedicacion
            notifyDataSetChanged()
        }


        fun EditData(
            Nombre: String,
            Apellido: String,
            Edad: Int,
            Enfermedad: String,
            NumHabitacion: Int,
            NumCama: Int,
            MedicamentosAsignados: String,
            FechaIngreso: String,
            HoradeMedicacion: String,
            UUID: String
        ) {
            GlobalScope.launch(Dispatchers.IO) {
                val objCon = ClaseConexion().CadenaConexion()

                val updatePacientes =
                    objCon?.prepareStatement("UPDATE TbPacientes SET Nombre= ?,  Apellido = ?, Edad = ?, Enfermedad = ?, NumHabitacion = ?, NumCama = ?, MedicamentosAsignados = ?, FechaIngreso = ?, HoradeMedicacion = ?   WHERE UUID_Patients = ?")!!
                updatePacientes.setString(1, Nombre)
                updatePacientes.setString(2, Apellido)
                updatePacientes.setString(3, Edad.toString())
                updatePacientes.setString(4, Enfermedad)
                updatePacientes.setString(5, NumHabitacion.toString())
                updatePacientes.setString(6, NumCama.toString())
                updatePacientes.setString(7, MedicamentosAsignados)
                updatePacientes.setString(8, FechaIngreso)
                updatePacientes.setString(9, HoradeMedicacion)
                updatePacientes.setString(10,UUID)

                updatePacientes.executeUpdate()

                withContext(Dispatchers.Main){
                        ActualizarPantalla(
                        Nombre,
                        Apellido,
                        Edad,
                        Enfermedad,
                        NumHabitacion,
                        NumCama,
                        MedicamentosAsignados,
                        FechaIngreso,
                        HoradeMedicacion,
                        UUID)
                }
            }
        }

        fun DeleteData(Nombre: String, posicion: Int) {
            val dataList = Datos.toMutableList()
            dataList.removeAt(posicion)

            GlobalScope.launch(Dispatchers.IO) {
                val objCon = ClaseConexion().CadenaConexion()

                val borrarPaciente = objCon?.prepareStatement("DELETE FROM TBPacientes WHERE Name = ?")!!
                borrarPaciente.setString(1, Nombre)
                borrarPaciente.executeUpdate()

                val commit = objCon.prepareStatement("commit")
                commit.executeUpdate()
            }

            Datos = dataList.toList()

            notifyItemRemoved(posicion)
            notifyDataSetChanged()

        }
        val Paciente = Datos[position]

        holder.lbNombrePacientes.text = pacientes.nombre

        holder.btnDeletePacientes.setOnClickListener {

            val context = holder.itemView.context

            val builder = AlertDialog.Builder(context)
            builder.setMessage("¿Desea eliminar el paciente?")

            builder.setPositiveButton("Aceptar") { dialog, which ->
                DeleteData(pacientes.nombre, position)
                Toast.makeText(context, "Paciente eliminado correctamente", Toast.LENGTH_SHORT).show()
            }

            builder.setNegativeButton("No") { dialog, which ->
                dialog.dismiss()
            }

            val dialog = builder.create()
            dialog.show()
        }

        holder.btnEditPacientes.setOnClickListener {
            val context = holder.itemView.context

            val layout = LinearLayout(context)
            layout.orientation = LinearLayout.VERTICAL

            val txt1 = EditText(context)
            layout.addView(txt1)
            txt1.setText(pacientes.nombre)
            val txt2 = EditText(context)
            layout.addView(txt2)
            txt2.setText(pacientes.apellido)
            val txt3 = EditText(context)
            layout.addView(txt3)
            txt3.setText(pacientes.edad.toString())
            val txt4 = EditText(context)
            layout.addView(txt4)
            txt4.setText(pacientes.Enfermedad)
            val txt5 = EditText(context)
            txt5.setText(pacientes.NumHabitacion.toString())
            layout.addView(txt5)
            val txt6 = EditText(context);
            txt6.setText(pacientes.NumCama.toString())
            layout.addView(txt6)
            val txt7 = EditText(context)
            txt7.setText(pacientes.MedicamentosAsignados)
            layout.addView(txt7)
            val txt8 = EditText(context)
            txt8.setText(pacientes.FechaIngreso)
            layout.addView(txt8)
            val txt9 = EditText(context)
            txt9.setText(pacientes.HoradeMedicacion)
            layout.addView(txt9)

            val uuid = pacientes.uuid

            val builder = AlertDialog.Builder(context)
            builder.setView(layout)
            builder.setTitle("Editar paciente")


            builder.setPositiveButton("Aceptar") { dialog, which ->

                EditData(txt1.text.toString(),txt2.text.toString(),txt3.text.toString().toInt(),txt4.text.toString(),txt5.text.toString().toInt(),txt6.text.toString().toInt(),txt7.text.toString(),txt8.text.toString(),txt9.text.toString(),uuid)
                Toast.makeText(context, "Paciente editado correctamente", Toast.LENGTH_SHORT).show()

            }

            builder.setNegativeButton("Cancelar") { dialog, which ->
                dialog.dismiss()
            }

            val dialog = builder.create()
            dialog.show()
        }

    }





}





