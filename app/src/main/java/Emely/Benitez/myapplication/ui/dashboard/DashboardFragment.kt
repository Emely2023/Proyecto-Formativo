package Emely.Benitez.myapplication.ui.dashboard

import Emely.Benitez.myapplication.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import Emely.Benitez.myapplication.databinding.FragmentDashboardBinding
import Modelo.ClaseConexion
import Modelo.tbPacientes
import android.widget.Button
import android.widget.EditText

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val txtNombrePaciente = root.findViewById<EditText>(R.id.txtNombrePaciente)
        val txtApellidoPacientes = root.findViewById<EditText>(R.id.txtApellidoPaciente)
        val txtEdad = root.findViewById<EditText>(R.id.txtEdad)
        val txtEnfermedad = root.findViewById<EditText>(R.id.txtEnfermedad)
        val txtMedicamentosAsignados = root.findViewById<EditText>(R.id.txtMedicamentosAsignados)
        val txtFechaIngreso = root.findViewById<EditText>(R.id.txtFechadeIngreso)
        val txtHoraAplicacion = root.findViewById<EditText>(R.id.txtHoraAplicacion)
        val txtNumhabitacion = root.findViewById<EditText>(R.id.txtNumdeHabitacion)
        val txtNumCama = root.findViewById<EditText>(R.id.txtNumdeCama)
        val btnGuardarCambios = root.findViewById<Button>(R.id.btnGuardarCambios)


        fun clear()
        {
            txtNombrePaciente.setText("")
            txtApellidoPacientes.setText("")
            txtEdad.setText("")
            txtEnfermedad.setText("")
            txtMedicamentosAsignados.setText("")
            txtFechaIngreso.setText("")
            txtHoraAplicacion.setText("")
            txtNumhabitacion.setText("")
            txtNumCama.setText("")
        }

        fun getPatients(): List<tbPacientes> {
            val objCon = ClaseConexion().CadenaConexion()
            val statement = objCon?.createStatement()
            val resulSet = statement?.executeQuery("SELECT * FROM TBPacientes")!!

            val listPatients = mutableListOf<tbPacientes>()

            while (resulSet.next()) {
                val uuid = resulSet.getString("UUID_Pacientes")
                val txtNombrePaciente = resulSet.getString("Nombre")
                val txtApellido = resulSet.getString("Apellido")
                val Edad = resulSet.getInt("Edad")
                val Disease = resulSet.getString("Disease")
                val Medication = resulSet.getString("Medication")
                val AdmmissioDate = resulSet.getString("AdmmissionDate")
                val MedicationtTime = resulSet.getString("MedicationTime")
                val RoomNumber = resulSet.getInt("RoomNumber")
                val BedNumber = resulSet.getInt("BedNumber")

                val values = tbPacientes(uuid, name, lastName, Age, Disease, RoomNumber,BedNumber,Medication,AdmmissioDate,MedicationtTime)
                listPatients.add(values)
            }

            return listPatients
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}