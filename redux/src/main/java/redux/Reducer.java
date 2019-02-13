package redux;

public interface Reducer<State, Action> {
    public State call(State state, Action action);
}
