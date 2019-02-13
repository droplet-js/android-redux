package io.github.v7lin.redux;

import android.app.Activity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;

import java.util.UUID;

import io.github.v7lin.redux.model.Todo;
import io.github.v7lin.redux.model.User;
import io.github.v7lin.redux.redux.State;
import io.github.v7lin.redux.redux.TodoRedux;
import io.github.v7lin.redux.redux.UserRedux;
import redux.StreamController;

public final class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.change_user).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User(UUID.randomUUID().toString(), UUID.randomUUID().toString());
                State.store(MainActivity.this)
                        .dispatch(new UserRedux.UserChangedAction(user));
            }
        });

        findViewById(R.id.add_todo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Todo todo = new Todo(UUID.randomUUID().toString(), UUID.randomUUID().toString());
                State.store(MainActivity.this)
                        .dispatch(new TodoRedux.AddTodoAction(todo));
            }
        });

        State.store(this)
                .streamController()
                .registerObserver(stateSetObserver);
    }

    private StreamController.StateSetObserver<State> stateSetObserver = new StreamController.StateSetObserver<State>() {
        @Override
        public void onChanged(State state) {
            TextView info = findViewById(R.id.info);
            if (info != null) {
                info.setMovementMethod(ScrollingMovementMethod.getInstance());
                info.setText(state.toString());
            }
        }
    };

    @Override
    protected void onDestroy() {
        State.store(this)
                .streamController()
                .unregisterObserver(stateSetObserver);
        super.onDestroy();
    }
}
