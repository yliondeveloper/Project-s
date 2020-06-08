package xyz.starmc.hologram;

import javax.annotation.*;
import org.bukkit.entity.*;

public interface TouchHandler {

	void onTouch(@Nonnull Hologram p0, @Nonnull Player p1, @Nonnull TouchAction p2);
}
