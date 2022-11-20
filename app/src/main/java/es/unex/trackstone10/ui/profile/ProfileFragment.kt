package es.unex.trackstone10.ui.profile

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import es.unex.trackstone10.AppExecutors
import es.unex.trackstone10.LoginActivity
import es.unex.trackstone10.R
import es.unex.trackstone10.databinding.FragmentProfileBinding
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
        binding.closeSessionButton.setOnClickListener {
            var edit = sharedPreferences?.edit()
            edit?.clear()
            edit?.commit()
            val intent = Intent(activity, LoginActivity::class.java)
            startActivity(intent)
        }
        return view

    }




}