package xyz.starmc.hologram;

public class ClassWrapper<R> extends WrapperAbstract {

	private Class<R> clazz;

	public ClassWrapper(Class<R> clazz) {
		this.clazz = clazz;
	}

	@Override
	public boolean exists() {
		return this.clazz != null;
	}

	public Class<R> getClazz() {
		return this.clazz;
	}

	public String getName() {
		return this.clazz.getName();
	}

	public R newInstance() {
		try {
			return this.clazz.newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public R newInstanceSilent() {
		try {
			return this.clazz.newInstance();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}
		if (object == null || this.getClass() != object.getClass()) {
			return false;
		}
		ClassWrapper<?> that = (ClassWrapper<?>) object;
		return (this.clazz != null) ? this.clazz.equals(that.clazz) : (that.clazz == null);
	}

	@Override
	public int hashCode() {
		return (this.clazz != null) ? this.clazz.hashCode() : 0;
	}
}
