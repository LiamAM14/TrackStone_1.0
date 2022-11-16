package es.unex.trackstone10

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import es.unex.trackstone10.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.registerButton.setOnClickListener {
            if (binding.editTextEmail.text.isNotEmpty() && binding.editTextTextPersonName.text.isNotEmpty()
                && binding.editTextPassword.text.isNotEmpty() && binding.confirmPassword.text.toString() == binding.editTextPassword.text.toString()) {
                val intent = Intent(this, ButtonNavigationMenuActivity::class.java)
                startActivity(intent)
            }
        }
    }
}