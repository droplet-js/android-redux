package redux;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class Store<State> {
    private State state;
    private List<NextDispatcher> nextDispatchers;
    private StreamController<State> streamController;

    public Store(State state, Reducer<State, Object> reducer, Middleware<State, Object>... middlewares) {
        this(state, reducer, false, middlewares);
    }

    public Store(State state, Reducer<State, Object> reducer, boolean distinct, Middleware<State, Object>... middlewares) {
        this.state = state;
        this.nextDispatchers = createDispatchers(createReduceAndNotify(reducer, distinct), middlewares);
        this.streamController = StreamController.simple();
    }

    private NextDispatcher createReduceAndNotify(final Reducer<State, Object> reducer, final boolean distinct) {
        return new NextDispatcher()  {

            @Override
            public void dispatch(Object action) {
                final State newState = reducer.call(state, action);
                if (distinct && (newState == state || (newState != null && state != null && newState.equals(state)))) {
                    return;
                }
                state = newState;
                if (streamController != null) {
                    streamController.add(state);
                }
            }
        };
    }

    private List<NextDispatcher> createDispatchers(NextDispatcher reduceAndNotify, Middleware<State, Object>... middlewares) {
        List<NextDispatcher> dispatchersReversed = new ArrayList<>();
        dispatchersReversed.add(reduceAndNotify);// 倒序
        if (middlewares != null && middlewares.length > 0) {
            List<Middleware<State, Object>> middlewaresReversed = Arrays.asList(middlewares);
            Collections.reverse(middlewaresReversed);// 倒序
            for (final Middleware<State, Object> middlewareReversed : middlewaresReversed) {
                final NextDispatcher next = dispatchersReversed.get(dispatchersReversed.size() - 1);
                dispatchersReversed.add(new NextDispatcher() {
                    @Override
                    public void dispatch(Object action) {
                        middlewareReversed.call(Store.this, action, next);
                    }
                });
            }
        }
        List<NextDispatcher> dispatchers = new ArrayList<>(dispatchersReversed);
        Collections.reverse(dispatchers);// 倒序
        return dispatchers;
    }

    public State state() {
        return state;
    }

    public StreamController<State> streamController() {
        return streamController;
    }

    public void dispatch(Object action) {
        nextDispatchers.get(0).dispatch(action);
    }
}
