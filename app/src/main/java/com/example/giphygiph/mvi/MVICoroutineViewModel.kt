package com.example.giphygiph.mvi

import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import com.example.giphygiph.ui.base.HasViewModelFactory
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlin.reflect.KClass

/**
 * Base class for a ViewModel that implements the MVI pattern and uses Coroutines and Flow.
 */
abstract class MVICoroutineViewModel<S : State, I : Intent, C : Change> constructor(
    private var initialState: S,
    private val initialIntent: I? = null
) : ViewModel(), IntentConsumer<I, C>, StateProducer<I, S> {

    /**
     * Hold onto the job that's collecting the View's intents in case it recreates and we need
     * to cancel the old one.
     */
    private var intentsJob: Job? = null

    /**
     * Hot stream for providing the consumed intents. We need a hot stream because the state
     * stream must only be created once to avoid lifecycle issues.
     */
    private val changes = Channel<C>(Channel.BUFFERED)

    /**
     * Create the state flow using the hot stream of changes as a source. The state needs to be
     * created only once to avoid lifecycle issues and flow re-collection.
     */
    private val state = liveData {
        changes
            .receiveAsFlow()
            .scan(initialState) { accumulator, value ->
                mutate(accumulator, value).also { newState ->
                    initialState = newState
                }
            }
            .distinctUntilChanged()
            .collect {
                emit(it)
            }
    }

    /**
     * The state that the StateConsumer observes when attaching to this ViewModel.
     */
    override fun getState(producer: IntentProducer<I>): LiveData<S> {
        intentsJob?.cancel()
        intentsJob = viewModelScope.launch {
            producer.intents
                .onStart { initialIntent?.let { emit(it) } }
                .flatMapMerge(DEFAULT_CONCURRENCY, consumer)
                .collect { changes.send(it) }
        }

        return state
    }

    /**
     * Implement this method to specify how a Change alters the State
     */
    protected abstract suspend fun mutate(state: S, change: C): S
}

/**
 * Extension used for Fragments attaching to ViewModels that use this MVI pattern.
 */
fun <T : MVICoroutineViewModel<S, I, C>, S : State, I : Intent, C : Change, V> V.initViewModel(
    viewModelClass: KClass<T>,
    shared: Boolean = false
) where V : IntentProducer<I>, V : StateConsumer<S>, V : Fragment, V : HasViewModelFactory {
    val provider = if (shared) {
        ViewModelProvider(requireActivity(), viewModelFactory)
    } else {
        ViewModelProvider(this, viewModelFactory)
    }
    provider.get(viewModelClass.java).let { viewModel ->
        viewModel.getState(this).observe(viewLifecycleOwner) { state ->
            consume(state)
        }
    }
}
