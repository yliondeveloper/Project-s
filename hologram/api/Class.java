package xyz.starmc.hologram.api;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import xyz.starmc.hologram.Minecraft;

@Target({ java.lang.annotation.ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Class {
	String[] value();

	Minecraft.Version[] versions() default {};

	boolean ignoreExceptions() default true;
}
