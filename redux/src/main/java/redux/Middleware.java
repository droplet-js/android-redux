package redux;

public interface Middleware<State, Action> {
    public void call(Store<State> store, Action action, NextDispatcher next);
}
