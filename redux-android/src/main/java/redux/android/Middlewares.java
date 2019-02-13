package redux.android;

import android.util.Log;

import redux.Middleware;
import redux.NextDispatcher;
import redux.Store;

public final class Middlewares {
    private Middlewares() {
    }

    public static <State, Action> Middleware<State, Action> logging(String tag) {
        return logging(tag, Log.INFO);
    }

    public static <State, Action> Middleware<State, Action> logging(final String tag, final int priority) {
        return new Middleware<State, Action>() {
            @Override
            public void call(Store<State> store, Action action, NextDispatcher next) {
                Log.println(priority, tag, "Middleware: " + action.toString());
                next.dispatch(action);
            }
        };
    }
}
