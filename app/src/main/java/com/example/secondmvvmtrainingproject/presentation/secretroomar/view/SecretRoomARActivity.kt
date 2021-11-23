package com.example.secondmvvmtrainingproject.presentation.secretroomar.view

import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.secondmvvmtrainingproject.R
import com.google.ar.core.Anchor
import com.google.ar.sceneform.AnchorNode
import com.google.ar.sceneform.assets.RenderableSource
import com.google.ar.sceneform.math.Vector3
import com.google.ar.sceneform.rendering.ModelRenderable
import com.google.ar.sceneform.ux.ArFragment
import com.google.ar.sceneform.ux.TransformableNode

class SecretRoomARActivity : AppCompatActivity() {

    private var renderable: ModelRenderable? = null
    private lateinit var arFragment: ArFragment

    private var myPrizeUrl = ""

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_secret_room_aractivity)

        arFragment = supportFragmentManager.findFragmentById(R.id.ux_fragment) as ArFragment

        val bundle = intent.extras
        if (bundle != null) {
            myPrizeUrl = bundle.getString("option").toString()
        }

        downloadModel(myPrizeUrl)

        arFragment.setOnTapArPlaneListener { hitResult, plane, motionEvent ->
            if (renderable == null) { return@setOnTapArPlaneListener }
            val anchor: Anchor = hitResult.createAnchor()
            val anchorNode = AnchorNode(anchor)
            anchorNode.setParent(arFragment.arSceneView.scene)
            val node = TransformableNode(arFragment.transformationSystem)
            node.renderable = renderable
            node.scaleController.minScale = 0.06f
            node.scaleController.maxScale = 1.0f
            node.worldScale = Vector3(0.5f, 0.5f, 0.5f)
            node.setParent(anchorNode)
            node.select()
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun downloadModel(URL_ruta: String?) {
        val renderableSource = RenderableSource.builder()
            .setSource(this, Uri.parse(URL_ruta), RenderableSource.SourceType.GLB)
            .setRecenterMode(RenderableSource.RecenterMode.CENTER)
            .build()

        ModelRenderable.Builder()
            .setSource(this, renderableSource)
            .build()
            .thenAccept { modelRenderable ->
                renderable = modelRenderable
                val toast = Toast.makeText(this@SecretRoomARActivity,"Descarga completa, toque una superficie", Toast.LENGTH_LONG)
                toast.show()
            }
            .exceptionally { throwable ->
                val toast = Toast.makeText(this@SecretRoomARActivity,"No se pudo descargar el elemnto 3D, compruebe su red", Toast.LENGTH_LONG)
                toast.show()
                return@exceptionally null
            }
    }

    override fun onBackPressed() {
        onDestroy()
        super.onBackPressed()
    }
}