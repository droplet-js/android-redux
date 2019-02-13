package io.github.v7lin.redux.redux;

import android.app.Application;
import android.content.Context;

import redux.Store;
import redux.android.StoreProvider;

public abstract class ReduxApp extends Application {
    @Override
    protected void attachBaseContext(Context base) {
        // 未执行完成 super.attachBaseContext(base)，不能使用 this 作上下文
        super.attachBaseContext(new StoreProvider(
                base,
                new Store<>(State.initialState(base), StateRedux.stateReducer(), true, StateRedux.stateMiddlewares(base.getApplicationContext()))
        ));
    }
}
