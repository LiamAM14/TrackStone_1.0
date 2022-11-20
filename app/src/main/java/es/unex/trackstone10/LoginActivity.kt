package es.unex.trackstone10

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import es.unex.giiis.asee.todomanager_dbkotlin.AppExecutors
import es.unex.trackstone10.databinding.ActivityLoginBinding
import es.unex.trackstone10.roomdb.Entity.UserEntity
import es.unex.trackstone10.roomdb.TrackstoneDatabase
import es.unex.trackstone10.roomdb.dao.UserEntityCRUD

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginButton.setOnClickListener{
            if(binding.editTextTextPersonName.text.isNotEmpty() && binding.editTextPassword.text.isNotEmpty()) {
                AppExecutors.instance?.diskIO()?.execute{
                    val db = TrackstoneDatabase.getInstance(this)
                    val user = db?.userdao?.getUserByName(binding.editTextTextPersonName.text.toString())
                    if(user != null){
                        if(user.password.toString() == binding.editTextPassword.text.toString()){
                            val intent = Intent(this, ButtonNavigationMenuActivity::class.java)
                            startActivity(intent)
                        }
                        else{
                            runOnUiThread(Runnable(){
                                Toast.makeText(this,"Wrong password", Toast.LENGTH_SHORT).show()
                            })
                        }
                    }
                    else{
                        runOnUiThread(Runnable(){
                            Toast.makeText(this,"User not found", Toast.LENGTH_SHORT).show()
                        })
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