package redux;

import java.util.ArrayList;
import java.util.List;

public abstract class StreamController<State> {
    public abstract void registerObserver(StateSetObserver<State> observer);

    public abstract void unregisterObserver(StateSetObserver<State> observer);

    protected abstract void add(State state);

    public interface StateSetObserver<State> {
        public void onChanged(State state);
    }

    // ---

    public static <State> StreamController<State> simple() {
        return new SimpleStreamController<>();
    }

    private static class SimpleStreamController<State> extends StreamController<State> {

        private final List<StateSetObserver<State>> observers = new ArrayList<>();

        @Override
        public void registerObserver(StateSetObserver<State> observer) {
            observers.add(observer);
        }

        @Override
        public void unregisterObserver(StateSetObserver<State> observer) {
            observers.remove(observer);
        }

        @Override
        protected void add(State state) {
            for (StateSetObserver<State> observer : observers) {
                observer.onChanged(state);
            }
        }
    }
}
