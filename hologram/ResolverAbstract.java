package xyz.starmc.hologram;

import java.util.concurrent.*;
import java.util.*;

public abstract class ResolverAbstract<T> {

	protected Map<ResolverQuery, T> resolvedObjects;

	public ResolverAbstract() {
		this.resolvedObjects = new ConcurrentHashMap<ResolverQuery, T>();
	}

	protected T resolveSilent(ResolverQuery... queries) {
		try {
			return this.resolve(queries);
		} catch (Exception e) {
			return null;
		}
	}

	protected T resolve(ResolverQuery... queries) throws ReflectiveOperationException {
		if (queries == null || queries.length <= 0) {
			throw new IllegalArgumentException("Given possibilities are empty");
		}
		int length = queries.length;
		int i = 0;
		while (i < length) {
			ResolverQuery query = queries[i];
			if (this.resolvedObjects.containsKey(query)) {
				return this.resolvedObjects.get(query);
			}
			try {
				T resolved = this.resolveObject(query);
				this.resolvedObjects.put(query, resolved);
				return resolved;
			} catch (ReflectiveOperationException e) {
				++i;
				continue;
			}
		}
		throw this.notFoundException(Arrays.asList(queries).toString());
	}

	protected abstract T resolveObject(ResolverQuery p0) throws ReflectiveOperationException;

	protected ReflectiveOperationException notFoundException(String joinedNames) {
		return new ReflectiveOperationException("Objects could not be resolved: " + joinedNames);
	}
}
