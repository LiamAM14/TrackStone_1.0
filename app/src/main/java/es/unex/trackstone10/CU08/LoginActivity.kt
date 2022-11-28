package es.unex.trackstone10.CU08

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import es.unex.trackstone10.AppExecutors
import es.unex.trackstone10.ButtonNavigationMenuActivity
import es.unex.trackstone10.CU01.RegisterActivity
import es.unex.trackstone10.databinding.ActivityLoginBinding
import es.unex.trackstone10.roomdb.TrackstoneDatabase

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPreferences = getSharedPreferences("userid",Context.MODE_PRIVATE)
        var userid = sharedPreferences.getInt("userid",0)

        if(userid != 0){
            val intent = Intent(this, ButtonNavigationMenuActivity::class.java)
            startActivity(intent)
        }
        else {
            binding.loginButton.setOnClickListener {
                if (binding.editTextTextPersonName.text.isNotEmpty() && binding.editTextPassword.text.isNotEmpty()) {
                    var edit = sharedPreferences.edit()
                    AppExecutors.instance?.diskIO()?.execute {
                        val db = TrackstoneDatabase.getInstance(this)
                        val user =
                            db?.userdao?.getUserByName(binding.editTextTextPersonName.text.toString())
                        if (user != null) {
                            if (user.password.toString() == binding.editTextPassword.text.toString()) {
                                edit.putInt("userid",user.id)
                                edit.commit()
                                val intent = Intent(this, ButtonNavigationMenuActivity::class.java)
                                startActivity(intent)
                            } else {
                                runOnUiThread(Runnable() {
                                    Toast.makeText(this, "Wrong password", Toast.LENGTH_SHORT)
                                        .show()
                                })
                            }
                        } else {
                            runOnUiThread(Runnable() {
                                Toast.makeText(this, "User not found", Toast.LENGTH_SHORT).show()
                            })
                        }
                    }
                }
            }
        }

        binding.registerButton.setOnClickListener{
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

//    fun showToastWrong(){
//        showToast("Wrong password")
//    }
//    fun showToastNotFound(){
//        showToast("User not found")
//    }
//
//    fun showToast(toast:String) {
//        Toast.makeText(this,toast, Toast.LENGTH_SHORT).show()
//    }
}