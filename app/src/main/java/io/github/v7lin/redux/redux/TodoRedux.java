package io.github.v7lin.redux.redux;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import io.github.v7lin.redux.model.Todo;
import redux.Middleware;
import redux.NextDispatcher;
import redux.Reducer;
import redux.Reducers;
import redux.Store;

public final class TodoRedux {
    private TodoRedux() {
    }

    public static Reducer<List<Todo>, Object> todosReducer() {
        return Reducers.combineReducers(
                Reducers.typedReducer(TodosChangedAction.class, CHANGE_TODOS)
        );
    }

    private static final class TodosChangedAction {
        public final List<Todo> todos;

        public TodosChangedAction(List<Todo> todos) {
            this.todos = todos;
        }
    }

    private static final Reducer<List<Todo>, TodosChangedAction> CHANGE_TODOS = new Reducer<List<Todo>, TodosChangedAction>() {

        @Override
        public List<Todo> call(List<Todo> todos, TodosChangedAction action) {
            return action.todos;
        }
    };

    public static final class AddTodoAction {
        public final Todo todo;

        public AddTodoAction(Todo todo) {
            this.todo = todo;
        }
    }

    public static Middleware<State, AddTodoAction> addTodoMiddleware(Context applicationContext) {
        return new Middleware<State, AddTodoAction>() {
            @Override
            public void call(Store<State> store, AddTodoAction action, NextDispatcher next) {
                next.dispatch(action);
                List<Todo> newTodos = new ArrayList<>(store.state().todos);
                newTodos.add(action.todo);
                store.dispatch(new TodosChangedAction(newTodos));
            }
        };
    }
}
