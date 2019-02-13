package io.github.v7lin.redux.redux;

import io.github.v7lin.redux.model.User;
import redux.Reducer;
import redux.Reducers;

public final class UserRedux {
    private UserRedux() {
    }

    public static Reducer<User, Object> userReducer() {
        return Reducers.combineReducers(
                Reducers.typedReducer(UserChangedAction.class, CHANGE_USER)
        );
    }

    public static final class UserChangedAction {
        public final User user;

        public UserChangedAction(User user) {
            this.user = user;
        }
    }

    private static final Reducer<User, UserChangedAction> CHANGE_USER = new Reducer<User, UserChangedAction>() {
        @Override
        public User call(User user, UserChangedAction action) {
            return action.user;
        }
    };
}
