package com.noor.homework2_groupb.view.profile
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.noor.homework2_groupb.R
import com.noor.homework2_groupb.base.BaseFragment
import com.noor.homework2_groupb.databinding.FragmentSettingsBinding


class SettingsFragment : BaseFragment<FragmentSettingsBinding>(FragmentSettingsBinding::inflate, true)

    /*override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val btn: findViewById<Switch>(R.id.switch1)
        return inflater.inflate(R.layout.fragment_settings,container,false)
        //super.onCreateView(inflater, container, savedInstanceState)
            //binding.root
        //)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val btn: findViewById<Switch>(R.id.switch1)
        super.onViewCreated(view, savedInstanceState)

        /*binding.switch1.setOnCheckedChangeListener{_,isChecked ->
            if(binding.switch1.isChecked){
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                binding.switch1.text = "disable dark"
            }
            else{
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                binding.switch1.text= "enable dark"
            }*/
        }
    }*/






