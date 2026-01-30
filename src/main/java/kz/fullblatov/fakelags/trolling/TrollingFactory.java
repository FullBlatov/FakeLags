package kz.fullblatov.fakelags.trolling;

import kz.fullblatov.fakelags.trolling.impl.*;

public class TrollingFactory {
    public static AbstractTrolling getTrolling(TrollingType type) {
        return switch (type) {
            case LAGS -> new LagsTrolling();
            case HOTBAR -> new HotbarTrolling();
        };
    }
}
