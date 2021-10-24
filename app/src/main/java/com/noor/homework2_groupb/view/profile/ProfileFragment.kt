package com.noor.homework2_groupb.view.profile

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.noor.homework2_groupb.R
import com.noor.homework2_groupb.base.BaseFragment
import com.noor.homework2_groupb.data.model.Product
import com.noor.homework2_groupb.databinding.FragmentProfileBinding
import com.noor.homework2_groupb.view.addproduct.REQUEST_CODE_FOR_TAKE_PICTURE
import java.io.File
import java.net.URI
import java.text.SimpleDateFormat
import java.util.*

class ProfileFragment :
    BaseFragment<FragmentProfileBinding>(FragmentProfileBinding::inflate, true) {

    private val viewModel by viewModels<ProfileViewModel>()
    private val currentUser = FirebaseAuth.getInstance().currentUser!!.uid
    lateinit var ImageUri : Uri

    private val db = FirebaseFirestore.getInstance()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getProfileFromFirebase()
        viewModel.user.observe(viewLifecycleOwner) {
            binding.user = it
        }
        binding.btnSignOut.setOnClickListener {
            Firebase.auth.signOut()
            findNavController().navigate(R.id.profileFragment_to_loginFragment)
        }
        binding.btnEditProfile.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_fragment_edit_profile)
        }
        binding.btnSettings.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_settingsFragment)
        }
        binding.imageUser.setOnClickListener {
            goGalleryForTakePicture()

        }


    }

    private fun goGalleryForTakePicture() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, REQUEST_CODE_FOR_TAKE_PICTURE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE_FOR_TAKE_PICTURE) {
            if (data != null) {
                //ImageUri = data?.data!!
                //binding.imageUser.setImageURI(ImageUri)
                binding.imageUser.setImageURI(data.data) // handle chosen image
                viewModel.uplo(data.data!!) // upload chosen image
            }
        }
    }
    /*val profileUpdates = userProfileChangeRequest {
        photoUri = Uri.parse("https://example.com/jane-q-user/profile.jpg")
    }

    user!!.updateProfile(profileUpdates)
    .addOnCompleteListener { task ->
        if (task.isSuccessful) {
            Log.d("seyma", "User profile updated.")
        }
    }*/


}
    /*fun uploadImage(){
        val formatter = SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.getDefault())
        val now = Date()
        val fileName = formatter.format(now)
        val storageReference = FirebaseStorage.getInstance().getReference("user/$fileName")

        storageReference.putFile(ImageUri).addOnSuccessListener {
            binding.imageUser.setImageURI(null)
            Log.d("seyma","success")
        }.addOnFailureListener{
            Log.d("seyma","fail")
        }

    }*/
    /*fun getImagesFromFirebase(){
        val storageRef = FirebaseStorage.getInstance(currentUser).reference.child("images/")
        val localfile = File.createTempFile("tempImage","jpg")
        storageRef.getFile(localfile).addOnSuccessListener {
            val bitmap = BitmapFactory.decodeFile(localfile.absolutePath)
            binding.imageUser.setImageBitmap(bitmap)
        }.addOnFailureListener{
            Log.d("seyma","fail")
        }
    }*/

