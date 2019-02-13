package io.github.v7lin.redux.redux;

import android.content.Context;
import android.util.Log;

import redux.Middleware;
import redux.Middlewares;
import redux.Reducer;
import redux.Reducers;

public final class StateRedux {
    private StateRedux() {
    }

    public static Reducer<State, Object> stateReducer() {
        return Reducers.combineReducers(
                redux.android.Reducers.<State, Object>logging("Redux", Log.ERROR),
                new Reducer<State, Object>() {
                    @Override
                    public State call(State state, Object action) {
                        return state.newBuilder()
                                .user(UserRedux.userReducer().call(state.user, action))
                                .todos(TodoRedux.todosReducer().call(state.todos, action))
                                .build();
                    }
                }
        );
    }

    public static Middleware<State, Object>[] stateMiddlewares(Context applicationContext) {
        return new Middleware[]{
                redux.android.Middlewares.logging("Redux", Log.ERROR),
                Middlewares.typedMiddleware(TodoRedux.AddTodoAction.class, TodoRedux.addTodoMiddleware(applicationContext)),
        };
    }
}
