package kz.fullblatov.fakelags.trolling;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public abstract class AbstractTrolling implements ITrolling {
    private final TrollingType type;
}
