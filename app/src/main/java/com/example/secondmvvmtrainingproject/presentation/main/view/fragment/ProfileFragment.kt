package com.example.secondmvvmtrainingproject.presentation.main.view.fragment

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.secondmvvmtrainingproject.R
import com.example.secondmvvmtrainingproject.databinding.FragmentProfileBinding
import com.example.secondmvvmtrainingproject.presentation.main.utils.Constants
import com.example.secondmvvmtrainingproject.presentation.main.utils.MainAux
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.storage.FirebaseStorage
import java.io.ByteArrayOutputStream

class ProfileFragment : Fragment() {

    private var binding: FragmentProfileBinding? = null

    private var photoSelectedUri: Uri? = null

    private val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        if (it.resultCode == Activity.RESULT_OK){
            photoSelectedUri = it.data?.data

            binding?.let {
                Glide.with(this)
                    .load(photoSelectedUri)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.drawable.ic_access_time)
                    .error(R.drawable.ic_broken_image)
                    .centerCrop()
                    .circleCrop()
                    .into(it.ibProfile)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        binding?.let {
            return it.root
        }
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getUser()
        configButtons()
    }

    private fun getUser() {
        binding?.let { binding ->
            FirebaseAuth.getInstance().currentUser?.let { user ->
                binding.etFullName.setText(user.displayName)

                Glide.with(this)
                    .load(user.photoUrl)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.drawable.ic_access_time)
                    .error(R.drawable.ic_broken_image)
                    .centerCrop()
                    .circleCrop()
                    .into(binding.ibProfile)

                setupActionBar()
            }
        }
    }

    private fun setupActionBar(){
        binding?.tbProfileToolbar?.title = getString(R.string.profile_title)
        binding?.tbProfileToolbar?.setOnClickListener {
            activity?.onBackPressed()
        }
        /* Esto sería de aquí debajo sería para la barra nativa de android pero
        como yo la anulé, me pinto la de arriba sólo para esta pantalla:

        (activity as? AppCompatActivity)?.let {
            it.supportActionBar?.setDisplayHomeAsUpEnabled(true)
            it.supportActionBar?.title = getString(R.string.profile_title)
            setHasOptionsMenu(true)
        }*/
    }

    private fun configButtons() {
        binding?.let { binding ->
            binding.ibProfile.setOnClickListener {
                openGallery()
            }
            binding.bUpdate.setOnClickListener {
                binding.etFullName.clearFocus()

                FirebaseAuth.getInstance().currentUser?.let { user ->
                    if (photoSelectedUri == null) {
                        updateUserProfile(binding, user, Uri.parse(""))
                    } else {
                        uploadReducedImage(user)
                    }
                }
            }
        }
    }

    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        resultLauncher.launch(intent)
    }

    private fun updateUserProfile(binding: FragmentProfileBinding, user: FirebaseUser, uri: Uri) {
        val profileUpdated = UserProfileChangeRequest.Builder()
            .setDisplayName(binding.etFullName.text.toString().trim())
            .setPhotoUri(uri)
            .build()

        user.updateProfile(profileUpdated)
            .addOnSuccessListener {
                Toast.makeText(activity, R.string.user_updated, Toast.LENGTH_SHORT).show()
                (activity as? MainAux)?.updateTitle(user)
                activity?.onBackPressed()
            }
            .addOnFailureListener {
                Toast.makeText(activity, R.string.user_updating_error, Toast.LENGTH_SHORT).show()
            }
    }

    private fun uploadReducedImage(user: FirebaseUser){
        val profileRef = FirebaseStorage.getInstance().reference.child(user.uid)
            .child(Constants.PATH_PROFIlE).child(Constants.MY_PHOTO)

        photoSelectedUri?.let { uri ->
            binding?.let { binding ->
                getBitmapFromUri(uri)?.let { bitmap ->
                    binding.progressBar.visibility = View.VISIBLE

                    val baos = ByteArrayOutputStream()
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 80, baos)

                    profileRef.putBytes(baos.toByteArray())
                        .addOnProgressListener {
                            val progress = (100 * it.bytesTransferred / it.totalByteCount).toInt()
                            it.run {
                                binding.progressBar.progress = progress
                                binding.tvProgress.text = String.format("%s%%", progress)
                            }
                        }
                        .addOnCompleteListener {
                            binding.progressBar.visibility = View.INVISIBLE
                            binding.tvProgress.text = ""
                        }
                        .addOnSuccessListener {
                            it.storage.downloadUrl.addOnSuccessListener { downloadUrl ->
                                updateUserProfile(binding, user, downloadUrl)
                            }
                        }
                        .addOnFailureListener{
                            Toast.makeText(activity, "Error al subir imagen.", Toast.LENGTH_SHORT).show()
                        }
                }
            }
        }
    }

    private fun getBitmapFromUri(uri: Uri): Bitmap?{
        activity?.let {
            val bitmap = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                val source = ImageDecoder.createSource(it.contentResolver, uri)
                ImageDecoder.decodeBitmap(source)
            } else {
                MediaStore.Images.Media.getBitmap(it.contentResolver, uri)
            }

            return getResizedImage(bitmap, 256)
        }
        return null
    }

    private fun getResizedImage(image: Bitmap, maxSize: Int): Bitmap {
        var width = image.width
        var height = image.height
        if (width <= maxSize && height <= maxSize) return image

        val bitmapRatio = width.toFloat() / height.toFloat()
        if (bitmapRatio > 1){
            width = maxSize
            height = (width / bitmapRatio).toInt()
        } else {
            height = maxSize
            width = (height / bitmapRatio).toInt()
        }
        return Bitmap.createScaledBitmap(image, width, height, true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.drawable.ic_arrow_back) {
            activity?.onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    override fun onDestroy() {
        binding?.tbProfileToolbar?.visibility = View.GONE
        super.onDestroy()
    }
}