package com.devandreschavez.blogappgason.ui.camera

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.devandreschavez.blogappgason.R
import com.devandreschavez.blogappgason.databinding.FragmentCameraBinding

class CameraFragment : Fragment(R.layout.fragment_camera) {
    private lateinit var binding: FragmentCameraBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCameraBinding.bind(view)
    }
}