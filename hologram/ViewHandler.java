package xyz.starmc.hologram;

import javax.annotation.*;
import org.bukkit.entity.*;

public interface ViewHandler {

	String onView(@Nonnull Hologram p0, @Nonnull Player p1, @Nonnull String p2);
}
