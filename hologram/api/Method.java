package xyz.starmc.hologram.api;

import java.lang.annotation.*;

import xyz.starmc.hologram.Minecraft;

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Method {
	String className();

	String[] value();

	Minecraft.Version[] versions() default {};

	boolean ignoreExceptions() default true;
}
