package redux;

public final class Reducers {
    private Reducers() {
    }

    public static <State, Action> Reducer<State, Action> logging() {
        return new Reducer<State, Action>() {
            @Override
            public State call(State state, Action action) {
                System.out.println("Reducer: " + action.toString());
                return state;
            }
        };
    }

    public static <State, Action> Reducer<State, Action> combineReducers(final Reducer<State, Action>... reducers) {
        return new Reducer<State, Action>() {
            @Override
            public State call(State state, Action action) {
                for (Reducer<State, Action> reducer : reducers) {
                    state = reducer.call(state, action);
                }
                return state;
            }
        };
    }

    public static <State, Action, ReactAction extends Action> Reducer<State, Action> typedReducer(final Class<ReactAction> type, final Reducer<State, ReactAction> reducer) {
        return new Reducer<State, Action>() {
            @Override
            public State call(State state, Action action) {
                if (type.isInstance(action)) {
                    return reducer.call(state, (ReactAction) action);
                }
                return state;
            }
        };
    }
}
