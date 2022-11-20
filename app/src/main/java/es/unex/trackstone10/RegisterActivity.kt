package es.unex.trackstone10

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import es.unex.trackstone10.databinding.ActivityRegisterBinding
import es.unex.trackstone10.roomdb.Entity.UserEntity
import es.unex.trackstone10.roomdb.TrackstoneDatabase

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.registerButton.setOnClickListener {
            if (binding.editTextEmail.text.isNotEmpty() && binding.editTextTextPersonName.text.isNotEmpty()
                && binding.editTextPassword.text.isNotEmpty() && binding.confirmPassword.text.toString() == binding.editTextPassword.text.toString()) {
                AppExecutors.instance?.diskIO()?.execute {
                    val db = TrackstoneDatabase.getInstance(this)
                    db?.userdao?.insert(
                            UserEntity(
                            binding.editTextTextPersonName.text.toString(),
                            binding.editTextPassword.text.toString(),
                            binding.editTextEmail.text.toString()
                        )
                    )
                    val user = db?.userdao?.getAll()
                }
                val intent = Intent(this, ButtonNavigationMenuActivity::class.java)
                startActivity(intent)
            }
        }
    }
}