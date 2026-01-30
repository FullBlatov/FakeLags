package kz.fullblatov.fakelags.manager;

import kz.fullblatov.fakelags.trolling.AbstractTrolling;
import kz.fullblatov.fakelags.trolling.TrollingFactory;
import kz.fullblatov.fakelags.trolling.TrollingType;
import lombok.Getter;
import lombok.experimental.UtilityClass;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.HashSet;

@UtilityClass
public final class TrollingManager {

    @Getter
    private final HashMap<Player, HashSet<AbstractTrolling>> trollings = new HashMap<>();

    public static void addTrolling(Player player, TrollingType type) {
        trollings.computeIfAbsent(player, p -> new HashSet<>())
                .add(TrollingFactory.getTrolling(type));
    }

    public static void removeTrolling(Player player, TrollingType type) {
        final HashSet<AbstractTrolling> trollingPlayer = trollings.get(player);

        if (trollingPlayer == null) return;
        trollingPlayer.removeIf((trolling) -> trolling.getType() == type);
    }

    public static boolean isAlreadyTrollingSetup(Player player, TrollingType type) {
        final HashSet<AbstractTrolling> trollingPlayer = trollings.get(player);

        if (trollingPlayer == null) return false;

        return trollingPlayer.stream()
                .filter((trolling) -> trolling.getType() == type)
                .findFirst()
                .orElse(null) != null;
    }
}
