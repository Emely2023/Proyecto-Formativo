package Emely.Benitez.myapplication

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import Emely.Benitez.myapplication.databinding.ActivityMain2Binding
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMain2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main2)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        }

    val lbNombrePacientes = findViewById<TextView>(R.id.lbNombrePaciente)
    val lbApellidoPacientes = findViewById<TextView>(R.id.lbApellidoPaciente)
    val lbEdad = findViewById<TextView>(R.id.lbEdadPaciente)
    val lbEnfermedad = findViewById<TextView>(R.id.lbEnfermedad)
    val lbNumCama = findViewById<TextView>(R.id.lbNumerodeCama)
    val lbNumHabitacion = findViewById<TextView>(R.id.lbNumerodeHabitacion)
    val lbHoradeMedicacion = findViewById<TextView>(R.id.lbHoradeMedicacion)
    val lbMedicamentosAsignados = findViewById<TextView>(R.id.lbMedicamentosAsignados)
    val lbFechaIngreso = findViewById<TextView>(R.id.lbFechaIngreso)


    lbNombrePacientes.text = intent.getStringExtra("Nombre")
    lbApellidosPaciente.text = intent.getStringExtra("Apellido")
    lbEdad.text = intent.getIntExtra("Edad",0).toString()
    lbEnfermedad.text = intent.getStringExtra("Enfermedad")
    lbNumHabitacion.text = intent.getIntExtra("Numero de Habitacion",0).toString()
    lbNumCama.text = intent.getIntExtra("Numero de Cama",0).toString()
    lbMedicamentos.text = intent.getStringExtra("Medicamentos")
    lbFechaIngreso.text = intent.getStringExtra("Fecha Ingreso")
    lbHoraMedicamentos.text = intent.getStringExtra("Hora de Aplicacion")

    }
