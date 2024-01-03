package com.example.feature.home.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.slidingpanelayout.widget.SlidingPaneLayout
import com.example.feature.home.HomeViewModel
import com.example.feature.home.TopicUiState
import com.example.feature.home.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by activityViewModels()


    companion object {
        fun newInstance() = HomeFragment()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        return FragmentHomeBinding.inflate(inflater, container, false).root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentHomeBinding.bind(view)
        binding.slidingPaneLayout.lockMode = SlidingPaneLayout.LOCK_MODE_LOCKED

        val adapter = RMCharactersAdapter {
            viewModel.updateCharacter(it)
            binding.slidingPaneLayout.openPane()
        }

        binding.recyclerView.adapter = adapter

        // Connect the SlidingPaneLayout to the system back button.
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            SportsListOnBackPressedCallback(binding.slidingPaneLayout)
        )
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect {
                    when(it) {
                        is TopicUiState.Success -> {
                            adapter.submitList(it.characters)
                        } else -> {

                        }
                    }
                    println("El resultado es")
                    println(it)
                }
            }
        }
        viewModel.getCharacters()
    }

}

class SportsListOnBackPressedCallback(
    private val slidingPaneLayout: SlidingPaneLayout
) : OnBackPressedCallback(slidingPaneLayout.isSlideable && slidingPaneLayout.isOpen),
    SlidingPaneLayout.PanelSlideListener {

    init {
        slidingPaneLayout.addPanelSlideListener(this)
    }
    /**
     * Callback for handling the [OnBackPressedDispatcher.onBackPressed] event.
     */
    override fun handleOnBackPressed() {
        slidingPaneLayout.closePane()
    }

    /**
     * Called when a detail view's position changes.
     *
     * @param panel       The child view that was moved
     * @param slideOffset The new offset of this sliding pane within its range, from 0-1
     */
    override fun onPanelSlide(panel: View, slideOffset: Float) {
    }

    /**
     * Called when a detail view becomes slid completely open.
     *
     * @param panel The detail view that was slid to an open position
     */
    override fun onPanelOpened(panel: View) {
        isEnabled = true
    }

    /**
     * Called when a detail view becomes slid completely closed.
     *
     * @param panel The detail view that was slid to a closed position
     */
    override fun onPanelClosed(panel: View) {
        isEnabled = false
    }
}
