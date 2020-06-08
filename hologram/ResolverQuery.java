package xyz.starmc.hologram;

import java.util.*;

public class ResolverQuery {

	private String name;
	private Class<?>[] types;

	public ResolverQuery(String name, Class<?>... types) {
		this.name = name;
		this.types = types;
	}

	public ResolverQuery(String name) {
		this.name = name;
		this.types = (Class<?>[]) new Class[0];
	}

	public ResolverQuery(Class<?>... types) {
		this.types = types;
	}

	public String getName() {
		return this.name;
	}

	public Class<?>[] getTypes() {
		return this.types;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || this.getClass() != o.getClass()) {
			return false;
		}
		ResolverQuery that = (ResolverQuery) o;
		if (this.name != null) {
			if (this.name.equals(that.name)) {
				return Arrays.equals(this.types, that.types);
			}
		} else if (that.name == null) {
			return Arrays.equals(this.types, that.types);
		}
		return false;
	}

	@Override
	public int hashCode() {
		int result = (this.name != null) ? this.name.hashCode() : 0;
		result = 31 * result + ((this.types != null) ? Arrays.hashCode(this.types) : 0);
		return result;
	}

	@Override
	public String toString() {
		return "ResolverQuery{name='" + this.name + '\'' + ", types=" + Arrays.toString(this.types) + '}';
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		private List<ResolverQuery> queryList;

		private Builder() {
			this.queryList = new ArrayList<ResolverQuery>();
		}

		public Builder with(String name, Class<?>[] types) {
			this.queryList.add(new ResolverQuery(name, types));
			return this;
		}

		public Builder with(String name) {
			this.queryList.add(new ResolverQuery(name));
			return this;
		}

		public Builder with(Class<?>[] types) {
			this.queryList.add(new ResolverQuery(types));
			return this;
		}

		public ResolverQuery[] build() {
			return this.queryList.toArray(new ResolverQuery[this.queryList.size()]);
		}
	}
}
