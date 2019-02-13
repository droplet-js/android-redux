package redux;

public final class Middlewares {
    private Middlewares() {
    }

    public static <State, Action> Middleware<State, Action> logging() {
        return new Middleware<State, Action>() {
            @Override
            public void call(Store<State> store, Action action, NextDispatcher next) {
                System.out.println("Middleware: " + action.toString());
                next.dispatch(action);
            }
        };
    }

    public static <State, Action, ReactAction extends Action> Middleware<State, Action> typedMiddleware(final Class<ReactAction> type, final Middleware<State, ReactAction> middleware) {
        return new Middleware<State, Action>() {
            @Override
            public void call(Store<State> store, Action action, NextDispatcher next) {
                if (type.isInstance(action)) {
                    middleware.call(store, (ReactAction) action, next);
                } else {
                    next.dispatch(action);
                }
            }
        };
    }
}
