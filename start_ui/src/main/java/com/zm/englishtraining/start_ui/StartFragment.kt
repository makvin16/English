package com.zm.englishtraining.start_ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.zm.englishtraining.core.base.BaseFragment
import com.zm.englishtraining.core.ext.setupRecyclerView
import com.zm.englishtraining.core.recycler.FingerprintAdapter
import com.zm.englishtraining.core.viewmodel.GenericSavedStateViewModelFactory
import com.zm.englishtraining.core.viewmodel.ViewModelAssistedFactory
import com.zm.englishtraining.start_ui.databinding.FragmentStartBinding
import com.zm.englishtraining.start_ui.fingerprints.CategoryFingerprint
import com.zm.englishtraining.start_ui.model.CategoryUi

class StartFragment : BaseFragment<FragmentStartBinding, StartNavigation>() {

    private lateinit var factory: ViewModelAssistedFactory<StartViewModel>
    private val viewModel: StartViewModel by viewModels() {
        GenericSavedStateViewModelFactory(factory, this)
    }

    private var adapter: FingerprintAdapter? = null

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentStartBinding.inflate(inflater, container, false)

    override fun onAttach(context: Context) {
        factory = (requireActivity().application as StartProvider).factoryStart
        super.onAttach(context)
    }

    override fun onViewCreated(): Unit = with(binding) {
        setupClickListener(btnChoiceAll, btnPlay)
        val categoryFingerprint = CategoryFingerprint(
            requireContext(),
            R.layout.item_category,
            ::onClickTopic
        )
        adapter = FingerprintAdapter(listOf(categoryFingerprint))
        recyclerViewTopics.setupRecyclerView(
            adapter = adapter,
            layoutManager = LinearLayoutManager(context),
            listItemDecorations = listOf()
        )
        recyclerViewTopics.itemAnimator = null
    }

    override fun onInitObservers() {
        observe(viewModel.categories) {
            adapter?.submitList(it)
        }
    }

    override fun onClick(v: View?) = with(binding) {
        when (v?.id) {
            btnChoiceAll.id -> {
                onClickChoiceAll()
            }
            btnPlay.id -> {
                onClickPlay()
            }
        }
    }

    private fun onClickChoiceAll() {
        viewModel.onClickChoiceAll()
    }

    private fun onClickPlay() {
        val ids = viewModel.categories.value
            ?.filter { it.isChoose }
            ?.map { it.id }?.toLongArray() ?: longArrayOf()
        navigation.navigateToMode(ids)
    }

    private fun onClickTopic(topic: CategoryUi) {
        viewModel.onClickTopic(topic)
    }
}