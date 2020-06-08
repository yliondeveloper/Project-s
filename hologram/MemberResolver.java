package xyz.starmc.hologram;

import java.lang.reflect.*;

public abstract class MemberResolver<T extends Member> extends ResolverAbstract<T> {

	protected Class<?> clazz;

	public MemberResolver(Class<?> clazz) {
		if (clazz == null) {
			throw new IllegalArgumentException("class cannot be null");
		}
		this.clazz = clazz;
	}

	public MemberResolver(String className) throws ClassNotFoundException {
		this(new ClassResolver().resolve(className));
	}

	public abstract T resolveIndex(int p0) throws IndexOutOfBoundsException, ReflectiveOperationException;

	public abstract T resolveIndexSilent(int p0);

	public abstract WrapperAbstract resolveIndexWrapper(int p0);
}
