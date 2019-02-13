package io.github.v7lin.redux.redux;

import android.content.Context;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.github.v7lin.redux.model.Todo;
import io.github.v7lin.redux.model.User;
import redux.Store;
import redux.android.StoreProvider;

public final class State {

    public final User user;
    public final List<Todo> todos;

    private State(User user, List<Todo> todos) {
        this.user = user;
        this.todos = todos;
    }

    public Builder newBuilder() {
        return new Builder(user, todos);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        State state = (State) o;

        if (user != null ? !user.equals(state.user) : state.user != null) return false;
        return todos != null ? todos.equals(state.todos) : state.todos == null;
    }

    @Override
    public int hashCode() {
        int result = user != null ? user.hashCode() : 0;
        result = 31 * result + (todos != null ? todos.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "State{" +
                "user=" + user +
                ", todos=" + todos +
                '}';
    }

    public static final class Builder {
        private User user;
        private List<Todo> todos;

        public Builder() {
            this(null, Collections.<Todo>emptyList());
        }

        Builder(User user, List<Todo> todos) {
            this.user = user;
            this.todos = new ArrayList<>(todos);
        }

        public Builder user(User user) {
            this.user = user;
            return this;
        }

        public Builder todos(List<Todo> todos) {
            this.todos = todos;
            return this;
        }

        public State build() {
            return new State(user, Collections.unmodifiableList(todos));
        }
    }

    public static State initialState(Context context) {
        return new Builder()
                .build();
    }

    public static Store<State> store(Context context) {
        return StoreProvider.of(context.getApplicationContext(), State.class);
    }
}
