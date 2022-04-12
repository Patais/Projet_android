package com.formation.sciencesufinalapp.ui.signup

import GameViewModel
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.formation.sciencesufinalapp.R
import com.formation.sciencesufinalapp.databinding.FragmentSignupBinding
import com.formation.sciencesufinalapp.databinding.FragmentSplashBinding
import com.formation.sciencesufinalapp.ui.splash.SplashFragmentDirections




class SignupFragment : Fragment() {

    private var _binding: FragmentSignupBinding? = null
    private val viewModel: SignUpViewModel by activityViewModels()
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSignupBinding.inflate(inflater, container, false)

        return binding.root
    }




    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.outlinedButton.setOnClickListener {
            viewModel.updatePlayerName(binding.nicknameInput.text.toString())
            val action = SignupFragmentDirections.actionSignupFragmentToHomeFragment()
            view.findNavController().navigate(action)
        }
    }


}