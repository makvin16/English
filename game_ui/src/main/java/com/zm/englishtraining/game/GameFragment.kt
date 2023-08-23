package com.zm.englishtraining.game

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zm.englishtraining.core.base.BaseFragment
import com.zm.englishtraining.core.ext.hideKeyboard
import com.zm.englishtraining.core.ext.setupRecyclerView
import com.zm.englishtraining.core.model.EmptyUi
import com.zm.englishtraining.core.model.ModeUi
import com.zm.englishtraining.core.recycler.CancellationSnapHelper
import com.zm.englishtraining.core.recycler.FingerprintAdapter
import com.zm.englishtraining.core.viewmodel.GenericSavedStateViewModelFactory
import com.zm.englishtraining.core.viewmodel.ViewModelAssistedFactory
import com.zm.englishtraining.game.fingerprints.PhraseFingerprint
import com.zm.englishtraining.game.model.PhraseUi
import com.zm.englishtraining.game.result.ResultDialogFragment
import com.zm.englishtraining.game_ui.R
import com.zm.englishtraining.game_ui.databinding.FragmentGameBinding

class GameFragment : BaseFragment<FragmentGameBinding, GameNavigation>(), IGameFragment {

    private lateinit var factory: ViewModelAssistedFactory<GameViewModel>
    private val viewModel: GameViewModel by viewModels() {
        GenericSavedStateViewModelFactory(factory, this)
    }

    private val snapHelper = CancellationSnapHelper()
    private val layoutManager = object : LinearLayoutManager(context, RecyclerView.HORIZONTAL, false) {
        override fun canScrollHorizontally() = false
    }

    private var adapter: FingerprintAdapter? = null

    override val argCategoriesIds: LongArray
        get() = arguments?.getLongArray(ARG_CATEGORIES_IDS) ?: error("ids")

    override val argMode: ModeUi
        get() = arguments?.getParcelable(ARG_MODE) ?: error("mode")

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentGameBinding.inflate(inflater, container, false)

    override fun onAttach(context: Context) {
        factory = (requireActivity().application as GameProvider).factoryGame(
            ids = argCategoriesIds,
            mode = argMode
        )
        super.onAttach(context)
    }

    override fun onViewCreated(): Unit = with(binding) {
        val phraseFingerprint = PhraseFingerprint(
            context = requireContext(),
            containerId = R.layout.item_phrase,
            onClickHelp = ::onClickHelp,
            onClickConfirm = ::onClickConfirm
        )
        adapter = FingerprintAdapter(listOf(phraseFingerprint))
        recyclerView.setupRecyclerView(
            adapter = adapter,
            layoutManager = layoutManager,
            listItemDecorations = listOf()
        )
        recyclerView.itemAnimator = null
        snapHelper.attachToRecyclerView(recyclerView)
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            showAlertDialogExitFromGame()
        }
    }

    override fun onInitObservers() = with(viewModel) {
        observe(eventShowProgress, ::onShowLoading)
        observe(eventHideProgress, ::onHideLoading)
        observe(phrases, ::onUpdatePhrases)
        observe(scroll, ::onScroll)
    }

    override fun onShowLoading(empty: EmptyUi) = with(binding) {
        root.visibilitiesExcept(isVisible = false)
        progress.isVisible = true
    }

    override fun onHideLoading(empty: EmptyUi) = with(binding) {
        progress.isVisible = false
    }

    override fun onUpdatePhrases(phrases: List<PhraseUi>): Unit = with(binding) {
        root.visibilitiesExcept(
            isVisible = true,
            exceptIds = arrayOf(progress)
        )
        adapter?.submitList(phrases)
    }

    override fun onClickHelp(phrase: PhraseUi) {
        viewModel.onClickHelp(phrase)
    }

    override fun onClickConfirm(answer: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.CUPCAKE) {
            hideKeyboard()
        }
        viewModel.onClickConfirm(answer) { isCorrect, correctAnswer ->
            childFragmentManager.setFragmentResultListener(
                ResultDialogFragment.REQUEST_KEY, viewLifecycleOwner
            ) { requestKey, _ ->
                childFragmentManager.clearFragmentResult(requestKey)
                viewModel.onNextQuestion()
            }
            ResultDialogFragment.show(childFragmentManager, isCorrect, correctAnswer)
        }
    }

    override fun onScroll(pos: Int) {
        binding.recyclerView.scrollToPosition(pos)
    }

    private fun showAlertDialogExitFromGame() {
        val alertDialog = AlertDialog.Builder(requireContext())
            .setTitle(getString(com.zm.englishtraining.core_ui.R.string.exit_title))
            .setPositiveButton(getString(com.zm.englishtraining.core_ui.R.string.yes)) { _, _ ->
                navigation.navigateBack()
            }
            .setNegativeButton(getString(com.zm.englishtraining.core_ui.R.string.no)) { _, _ -> }
            .create()

        alertDialog.show()
    }

    companion object {
        private const val ARG_CATEGORIES_IDS = "categoriesIds"
        private const val ARG_MODE = "mode"

        fun makeArgs(ids: LongArray, mode: ModeUi): Bundle {
            return Bundle(2).apply {
                putLongArray(ARG_CATEGORIES_IDS, ids)
                putParcelable(ARG_MODE, mode)
            }
        }
    }
}