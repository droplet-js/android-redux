package redux.android;

import android.util.Log;

import redux.Reducer;

public final class Reducers {
    private Reducers() {
    }

    public static <State, Action> Reducer<State, Action> logging(String tag) {
        return logging(tag, Log.INFO);
    }

    public static <State, Action> Reducer<State, Action> logging(final String tag, final int priority) {
        return new Reducer<State, Action>() {
            @Override
            public State call(State state, Action action) {
                Log.println(priority, tag, "Reducer: " + action.toString());
                return state;
            }
        };
    }
}
