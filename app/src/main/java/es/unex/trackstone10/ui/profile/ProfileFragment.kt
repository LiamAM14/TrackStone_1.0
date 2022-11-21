package es.unex.trackstone10.ui.profile

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import es.unex.trackstone10.*
import es.unex.trackstone10.databinding.FragmentProfileBinding
import es.unex.trackstone10.roomdb.Entity.UserEntity
import es.unex.trackstone10.roomdb.TrackstoneDatabase

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        val sharedPreferences = activity?.getSharedPreferences("userid",Context.MODE_PRIVATE)
        var userid = sharedPreferences?.getInt("userid",0)
        binding = FragmentProfileBinding.inflate(inflater,container,false)
        val view = binding.root
        AppExecutors.instance?.diskIO()?.execute {
            val db = TrackstoneDatabase.getInstance(activity)
            val user = db?.userdao?.getUserById(userid)
            activity?.runOnUiThread {
                binding.textViewEmail.text = user?.mail
                binding.textViewUsername.text = user?.username
            }
        }

        binding.Change1.setOnClickListener {
            AppExecutors.instance?.diskIO()?.execute {
                val db = TrackstoneDatabase.getInstance(activity)
                var user = db?.userdao?.getUserById(userid)
                if (user != null) {
                    user.mail = binding.profileEmailChange.text.toString()
                    db?.userdao?.update(user)
                }
            }
            Toast.makeText(activity, "Email updated!", Toast.LENGTH_SHORT).show()
            val intent = Intent(activity, ButtonNavigationMenuActivity::class.java)
            startActivity(intent)
        }

        binding.Change2.setOnClickListener {
            AppExecutors.instance?.diskIO()?.execute {
                val db = TrackstoneDatabase.getInstance(activity)
                var user = db?.userdao?.getUserById(userid)
                if (user != null) {
                    user.username = binding.profileNameChange.text.toString()
                    db?.userdao?.update(user)
                }
            }
            Toast.makeText(activity, "Username updated!", Toast.LENGTH_SHORT).show()
            val intent = Intent(activity, ButtonNavigationMenuActivity::class.java)
            startActivity(intent)
        }

        binding.Change3.setOnClickListener {
            AppExecutors.instance?.diskIO()?.execute {
                val db = TrackstoneDatabase.getInstance(activity)
                var user = db?.userdao?.getUserById(userid)
                if (user != null) {
                    user.password = binding.profilePasswordChange.text.toString()
                    db?.userdao?.update(user)
                }
            }
            Toast.makeText(activity, "Password updated!", Toast.LENGTH_SHORT).show()
            val intent = Intent(activity, ButtonNavigationMenuActivity::class.java)
            startActivity(intent)
        }

        binding.closeSessionButton.setOnClickListener {
            var edit = sharedPreferences?.edit()
            edit?.clear()
            edit?.commit()
            val intent = Intent(activity, LoginActivity::class.java)
            startActivity(intent)
        }

        binding.deleteUserButton.setOnClickListener {
            AppExecutors.instance?.diskIO()?.execute {
                val db = TrackstoneDatabase.getInstance(activity)
                db?.userdao?.deleteUser(userid)
            }
            var edit = sharedPreferences?.edit()
            edit?.clear()
            edit?.commit()
            Toast.makeText(activity, "User deleted!", Toast.LENGTH_SHORT).show()
            val intent = Intent(activity, LoginActivity::class.java)
            startActivity(intent)
        }

        return view

    }
}