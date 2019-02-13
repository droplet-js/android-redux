package redux.android;

import android.content.Context;
import android.content.ContextWrapper;

import java.util.HashMap;
import java.util.Map;

import redux.Store;

public final class StoreProvider extends ContextWrapper {
    private final Map<String, Store<?>> storeMap = new HashMap<>();

    public StoreProvider(Context base, Store<?>... stores) {
        super(base);
        if (stores != null && stores.length > 0) {
            for (Store<?> store : stores) {
                storeMap.put(ClazzUtils.storeKey(store.state().getClass()), store);
            }
        }
    }

    @Override
    public Object getSystemService(String name) {
        if (storeMap.containsKey(name)) {
            return storeMap.get(name);
        }
        return super.getSystemService(name);
    }

    public static <State> Store<State> of(Context context, Class<State> stateClazz) {
        return (Store<State>) context.getSystemService(ClazzUtils.storeKey(stateClazz));
    }

    private static final class ClazzUtils {
        private ClazzUtils() {
        }

        public static String storeKey(Class<?> stateClazz) {
            return String.format("redux.store: %1$s", stateClazz.getName());
        }
    }
}
