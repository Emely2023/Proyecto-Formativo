package Emely.Benitez.myapplication.ui.home

import Emely.Benitez.myapplication.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import Emely.Benitez.myapplication.databinding.FragmentHomeBinding
import Emely.Benitez.myapplication.databinding.FragmentNotificationsBinding
import Emely.Benitez.myapplication.ui.notifications.NotificationsViewModel
import Modelo.ClaseConexion
import Modelo.tbPacientes
import RecyclerViewHelper.Adaptador
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.UUID

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val notificationsViewModel =
            ViewModelProvider(this).get(NotificationsViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
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
        val rcvPacientes = root.findViewById<RecyclerView>(R.id.rcvPacientes)

        fun obtenerPacientes(): List<tbPacientes> {
            val objCon = ClaseConexion().CadenaConexion()
            val statement = objCon?.createStatement()
            val resulSet = statement?.executeQuery("SELECT * FROM TBPacientes")!!

            val listPatients = mutableListOf<tbPacientes>()

            while (resulSet.next()) {
                val uuid = resulSet.getString("UUID_Pacientes")
                val nombre = resulSet.getString("Nombre")
                val apellido = resulSet.getString("Apellido")
                val edad = resulSet.getInt("Edad")
                val Disease = resulSet.getString("Disease")
                val Medication = resulSet.getString("Medication")
                val AdmmissioDate = resulSet.getString("AdmmissionDate")
                val MedicationtTime = resulSet.getString("MedicationTime")
                val RoomNumber = resulSet.getInt("RoomNumber")
                val BedNumber = resulSet.getInt("BedNumber")

                val values = tbPacientes(uuid, nombre, apellido, edad, Disease, RoomNumber,BedNumber,Medication,AdmmissioDate,MedicationtTime)
                listPatients.add(values)
            }

            return listPatients
        }

        btnGuardarCambios.setOnClickListener{

            CoroutineScope(Dispatchers.IO).launch {

                val objConexion = ClaseConexion().CadenaConexion()

                //2. Crear una varaible que contenga un PrepareStatement
                val addPacientes = objConexion?.prepareStatement("insert into TBPACIENTES1 (UUID,Nombres,Apellidos,Edad,Enfermedad,NumHabitacion,NumCama,Medicamentos,FechaIngreso,HoraMedicacion) values (?,?,?,?,?,?,?,?,?,?)")!!
                addPacientes.setString(1, UUID.randomUUID().toString())
                addPacientes.setString(2,txtNombrePaciente.text.toString())
                addPacientes.setString(3,txtApellidoPacientes.text.toString())
                addPacientes.setInt(4,txtEdad.text.toString().toInt())
                addPacientes.setString(5,txtEnfermedad.text.toString())
                addPacientes.setString(6,txtMedicamentosAsignados.text.toString())
                addPacientes.setString(7,txtFechaIngreso.text.toString())
                addPacientes.setString(8,txtHoraAplicacion.text.toString())
                addPacientes.setInt(9,txtNumhabitacion.text.toString().toInt())
                addPacientes.setInt(10,txtNumCama.text.toString().toInt())
                addPacientes.executeUpdate()

                //Refresco la lista
                val nuevoPaciente = obtenerPacientes()
                withContext(Dispatchers.Main){
                    Toast.makeText(requireContext(), "Paciente agregado con éxito" , Toast.LENGTH_LONG).show()
                    (rcvPacientes.adapter as? Adaptador)?.UpdateList(nuevoPaciente)
                }
                val ticket = obtenerPacientes()
                withContext(Dispatchers.Main){
                    (rcvPacientes.adapter as? Adaptador)?.UpdateList(nuevoPaciente)
                    Toast.makeText(
                        requireContext(),
                        "Se agregó el paciente correctamente",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

        }

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



        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}