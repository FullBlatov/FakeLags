package kz.fullblatov.fakelags.commands;

import kz.fullblatov.fakelags.Config;
import kz.fullblatov.fakelags.manager.TrollingManager;
import kz.fullblatov.fakelags.trolling.TrollingType;
import kz.fullblatov.fakelags.utils.ColorUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TrollingCommand implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (!(sender.hasPermission(Config.adminPermission))) {
            sender.sendMessage(ColorUtil.translate(Config.Messages.noPermission));
            return true;
        }

        if (args.length != 2) {
            sender.sendMessage(ColorUtil.translate(Config.Messages.help));
            return true;
        }

        final Player target = Bukkit.getPlayer(args[1]);
        final TrollingType type;

        try {
            type = TrollingType.valueOf(args[0]);
        } catch (Exception ignore) {
            sender.sendMessage(ColorUtil.translate(Config.Messages.trollingNotFound));
            return true;
        }

        if (target == null) {
            sender.sendMessage(ColorUtil.translate(Config.Messages.playerNotFound));
            return true;
        }

        if (TrollingManager.isAlreadyTrollingSetup(target, type)) {
            TrollingManager.removeTrolling(target, type);
            sender.sendMessage(ColorUtil.translate(Config.Messages.trollingDisable)
                    .replace("%type%", type.name())
                    .replace("%player%", target.getName()));
            return true;
        }

        TrollingManager.addTrolling(target, type);
        sender.sendMessage(ColorUtil.translate(Config.Messages.trollingEnable
                .replace("%type%", type.name())
                .replace("%player%", target.getName())));

        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (args.length == 1) {
            if (sender.hasPermission(Config.adminPermission)) {
                return Arrays.stream(TrollingType.values())
                        .map(Enum::name)
                        .toList();
            }
        }

        if (args.length == 2) {
            if (sender.hasPermission(Config.adminPermission)) {
                return Bukkit.getOnlinePlayers()
                        .stream()
                        .map(Player::getName)
                        .toList();
            }
        }

        return Collections.emptyList();
    }
}
