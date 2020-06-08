package xyz.starmc.hologram;

import javax.annotation.*;
import org.bukkit.*;
import java.util.*;
import org.bukkit.entity.*;

public interface Hologram {

	boolean isSpawned();

	void spawn(@Nonnull @Nonnegative long p0);

	boolean spawn();

	boolean despawn();

	void setText(@Nullable String p0);

	String getText();

	void update();

	void update(long p0);

	void setLocation(@Nonnull Location p0);

	Location getLocation();

	void move(@Nonnull Location p0);

	void setTouchable(boolean p0);

	boolean isTouchable();

	void addTouchHandler(@Nonnull TouchHandler p0);

	void removeTouchHandler(@Nonnull TouchHandler p0);

	Collection<TouchHandler> getTouchHandlers();

	void clearTouchHandlers();

	void addViewHandler(@Nonnull ViewHandler p0);

	void removeViewHandler(@Nonnull ViewHandler p0);

	@Nonnull
	Collection<ViewHandler> getViewHandlers();

	void clearViewHandlers();

	@Nonnull
	Hologram addLineBelow(String p0);

	@Nullable
	Hologram getLineBelow();

	boolean removeLineBelow();

	@Nonnull
	Collection<Hologram> getLinesBelow();

	@Nonnull
	Hologram addLineAbove(String p0);

	@Nullable
	Hologram getLineAbove();

	boolean removeLineAbove();

	@Nonnull
	Collection<Hologram> getLinesAbove();

	@Nonnull
	Collection<Hologram> getLines();

	void setAttachedTo(@Nullable Entity p0);

	@Nullable
	Entity getAttachedTo();
}
