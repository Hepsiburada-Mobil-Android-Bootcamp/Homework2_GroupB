package com.noor.homework2_groupb.view.user
import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.core.view.marginBottom
import com.noor.homework2_groupb.base.BaseFragment
import com.noor.homework2_groupb.databinding.FragmentUserPhotoBinding


class UserPhotoFragment : BaseFragment<FragmentUserPhotoBinding>(FragmentUserPhotoBinding::inflate, true){

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val getImage = registerForActivityResult(
            ActivityResultContracts.GetContent()
        ) {
            binding.imageUpload.setImageURI(it)
        }
        binding.imageUpload.setOnClickListener {
            if(ContextCompat.checkSelfPermission(this.requireContext(),Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                //no permission
                ActivityCompat.requestPermissions(this.requireActivity(), arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),1)
            }
            else{
                //binding.imageUpload.layoutParams.height = 600
                //binding.imageUpload.layoutParams.width = 600
                getImage.launch("image/*")
            }

        }
    }




}









