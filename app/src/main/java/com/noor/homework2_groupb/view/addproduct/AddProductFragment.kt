package com.noor.homework2_groupb.view.addproduct

import android.animation.Animator
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import com.noor.homework2_groupb.base.BaseFragment
import com.noor.homework2_groupb.data.model.Product
import com.noor.homework2_groupb.databinding.FragmentAddProductBinding

const val REQUEST_CODE_FOR_TAKE_PICTURE = 100

class AddProductFragment :
    BaseFragment<FragmentAddProductBinding>(FragmentAddProductBinding::inflate) {

    private val viewModel by viewModels<AddProductViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initGoBackHomeListener()
        initUploadButtonClickListener()
        initUploadButtonListener()
        observeProgressBar()
        observeSuccessfulAnimation()
        initChipListeners()
    }

    private fun initGoBackHomeListener() {
        binding.ivBackHome.setOnClickListener {
            findNavController().navigate(AddProductFragmentDirections.actionAddProductFragmentToHomeFragment())
        }
    }

    private fun initUploadButtonClickListener() {
        binding.ivProduct.setOnClickListener {
            askPermissionForGoToGallery()
            goGalleryForTakePicture()
        }
    }

    private fun initUploadButtonListener() {
        binding.btnSaveProduct.setOnClickListener {
            if (checkEditTexts()) {
                viewModel.uploadProductToFirebase(createProduct())
            }
        }
    }

    private fun observeProgressBar() {
        viewModel.isShowProgressBar.observe(viewLifecycleOwner) {
            when (it) {
                false -> {
                    binding.pbLoadProduct.visibility = View.GONE
                    binding.btnSaveProduct.isClickable = true
                }
                true -> {
                    binding.pbLoadProduct.visibility = View.VISIBLE
                    binding.btnSaveProduct.isClickable = false
                }
            }
        }
    }

    private fun initChipListeners() {
        binding.apply {
            productWearable.setOnClickListener {
                viewModel.productType.value = "wearable"
            }
            productLaptops.setOnClickListener {
                viewModel.productType.value = "laptop"
            }
            productPhones.setOnClickListener {
                viewModel.productType.value = "phone"
            }
            productTablets.setOnClickListener {
                viewModel.productType.value = "tablet"
            }
        }
    }

    private fun createProduct(): Product {
        return Product(
            name = binding.tietProductName.text.toString(),
            title = binding.tietProductTitle.text.toString(),
            type = viewModel.productType.value.toString(),
            description = binding.tietProductDesc.text.toString(),
            imageUrl = viewModel.productImageUrl.value.toString(),
            price = binding.tietProductPrice.text.toString().toInt(),
            likeCount = 0
        )
    }

    private fun checkEditTexts(): Boolean {
        binding.apply {
            return when {
                binding.tietProductName.text.isNullOrEmpty() -> {
                    binding.tietProductName.error = "Please fill in the blanks"
                    false
                }
                binding.tietProductTitle.text.isNullOrEmpty() -> {
                    binding.tietProductTitle.error = "Please fill in the blanks"
                    false
                }
                binding.tietProductDesc.text.isNullOrEmpty() -> {
                    binding.tietProductDesc.error = "Please fill in the blanks"
                    false
                }
                binding.tietProductPrice.text.isNullOrEmpty() -> {
                    binding.tietProductPrice.error = "Please fill in the blanks"
                    false
                }
                else -> true
            }
        }
    }

    private fun observeSuccessfulAnimation() {
        viewModel.isShowSuccessfulAnimation.observe(viewLifecycleOwner) {
            when (it) {
                false -> {
                    binding.lottieSuccessful.visibility = View.GONE
                }
                true -> {
                    binding.lottieSuccessful.addAnimatorListener(object :
                        Animator.AnimatorListener {
                        override fun onAnimationStart(p0: Animator?) {
                            binding.lottieSuccessful.visibility = View.VISIBLE
                        }

                        override fun onAnimationEnd(p0: Animator?) {
                            binding.lottieSuccessful.visibility = View.GONE
                            this@AddProductFragment.findNavController()
                                .navigate(AddProductFragmentDirections.actionAddProductFragmentToHomeFragment())
                        }

                        override fun onAnimationCancel(p0: Animator?) {
                        }

                        override fun onAnimationRepeat(p0: Animator?) {
                        }
                    })
                    binding.lottieSuccessful.visibility = View.VISIBLE
                    binding.lottieSuccessful.speed = 0.5F
                    binding.lottieSuccessful.playAnimation()
                }
            }
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
                binding.ivProduct.setImageURI(data.data) // handle chosen image
                viewModel.uploadImageToFirebase(data.data!!) // upload chosen image
            }
        }
    }

    private fun askPermissionForGoToGallery() {
        Dexter.withContext(context)
            .withPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE)
            .withListener(object : PermissionListener {
                override fun onPermissionGranted(p0: PermissionGrantedResponse?) {
                }

                override fun onPermissionDenied(p0: PermissionDeniedResponse?) {
                    Toast.makeText(
                        context,
                        "Please give permission for take a picture",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                override fun onPermissionRationaleShouldBeShown(
                    p0: PermissionRequest?,
                    p1: PermissionToken?
                ) {
                    Toast.makeText(
                        context,
                        "Please give permission for take a picture",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            })
            .check()
    }
}