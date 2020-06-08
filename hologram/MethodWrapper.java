package xyz.starmc.hologram;

import java.lang.reflect.*;
import java.util.regex.*;
import java.util.*;

public class MethodWrapper<R> extends WrapperAbstract {

	private Method method;

	public MethodWrapper(Method method) {
		this.method = method;
	}

	@Override
	public boolean exists() {
		return this.method != null;
	}

	public String getName() {
		return this.method.getName();
	}

	@SuppressWarnings("unchecked")
	public R invoke(Object object, Object... args) {
		try {
			return (R) this.method.invoke(object, args);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public R invokeSilent(Object object, Object... args) {
		try {
			return (R) this.method.invoke(object, args);
		} catch (Exception e) {
			return null;
		}
	}

	public Method getMethod() {
		return this.method;
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}
		if (object == null || this.getClass() != object.getClass()) {
			return false;
		}
		MethodWrapper<?> that = (MethodWrapper<?>) object;
		return (this.method != null) ? this.method.equals(that.method) : (that.method == null);
	}

	@Override
	public int hashCode() {
		return (this.method != null) ? this.method.hashCode() : 0;
	}

	public static String getMethodSignature(Method method, boolean fullClassNames) {
		return MethodSignature.of(method, fullClassNames).getSignature();
	}

	public static String getMethodSignature(Method method) {
		return getMethodSignature(method, false);
	}

	public static class MethodSignature {
		static Pattern SIGNATURE_STRING_PATTERN;
		private String returnType;
		private String name;
		private String[] parameterTypes;
		private String signature;

		public MethodSignature(String returnType, String name, String[] parameterTypes) {
			this.returnType = returnType;
			this.name = name;
			this.parameterTypes = parameterTypes;
			StringBuilder builder = new StringBuilder();
			builder.append(returnType).append(" ").append(name).append("(");
			boolean first = true;
			for (String parameterType : parameterTypes) {
				if (!first) {
					builder.append(",");
				}
				builder.append(parameterType);
				first = false;
			}
			this.signature = builder.append(")").toString();
		}

		public static MethodSignature of(Method method, boolean fullClassNames) {
			Class<?> returnType = method.getReturnType();
			Class<?>[] parameterTypes = method.getParameterTypes();
			String returnTypeString;
			if (returnType.isPrimitive()) {
				returnTypeString = returnType.toString();
			} else {
				returnTypeString = (fullClassNames ? returnType.getName() : returnType.getSimpleName());
			}
			String methodName = method.getName();
			String[] parameterTypeStrings = new String[parameterTypes.length];
			for (int i = 0; i < parameterTypeStrings.length; ++i) {
				if (parameterTypes[i].isPrimitive()) {
					parameterTypeStrings[i] = parameterTypes[i].toString();
				} else {
					parameterTypeStrings[i] = (fullClassNames ? parameterTypes[i].getName()
							: parameterTypes[i].getSimpleName());
				}
			}
			return new MethodSignature(returnTypeString, methodName, parameterTypeStrings);
		}

		public static MethodSignature fromString(String signatureString) {
			if (signatureString == null) {
				return null;
			}
			Matcher matcher = MethodSignature.SIGNATURE_STRING_PATTERN.matcher(signatureString);
			if (!matcher.find()) {
				throw new IllegalArgumentException("invalid signature");
			}
			if (matcher.groupCount() != 3) {
				throw new IllegalArgumentException("invalid signature");
			}
			return new MethodSignature(matcher.group(1), matcher.group(2), matcher.group(3).split(","));
		}

		public String getReturnType() {
			return this.returnType;
		}

		public boolean isReturnTypeWildcard() {
			return "?".equals(this.returnType) || "*".equals(this.returnType);
		}

		public String getName() {
			return this.name;
		}

		public boolean isNameWildcard() {
			return "?".equals(this.name) || "*".equals(this.name);
		}

		public String[] getParameterTypes() {
			return this.parameterTypes;
		}

		public String getParameterType(int index) throws IndexOutOfBoundsException {
			return this.parameterTypes[index];
		}

		public boolean isParameterWildcard(int index) throws IndexOutOfBoundsException {
			return "?".equals(this.getParameterType(index)) || "*".equals(this.getParameterType(index));
		}

		public String getSignature() {
			return this.signature;
		}

		public boolean matches(MethodSignature other) {
			if (other == null) {
				return false;
			}
			if (!Pattern.compile(this.returnType.replace("?", "\\w").replace("*", "\\w*")).matcher(other.returnType)
					.matches()) {
				return false;
			}
			if (!Pattern.compile(this.name.replace("?", "\\w").replace("*", "\\w*")).matcher(other.name).matches()) {
				return false;
			}
			if (this.parameterTypes.length != other.parameterTypes.length) {
				return false;
			}
			for (int i = 0; i < this.parameterTypes.length; ++i) {
				if (!Pattern.compile(this.getParameterType(i).replace("?", "\\w").replace("*", "\\w*"))
						.matcher(other.getParameterType(i)).matches()) {
					return false;
				}
			}
			return true;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) {
				return true;
			}
			if (o == null || this.getClass() != o.getClass()) {
				return false;
			}
			MethodSignature signature1 = (MethodSignature) o;
			return this.returnType.equals(signature1.returnType) && this.name.equals(signature1.name)
					&& Arrays.equals(this.parameterTypes, signature1.parameterTypes)
					&& this.signature.equals(signature1.signature);
		}

		@Override
		public int hashCode() {
			int result = this.returnType.hashCode();
			result = 31 * result + this.name.hashCode();
			result = 31 * result + Arrays.hashCode(this.parameterTypes);
			result = 31 * result + this.signature.hashCode();
			return result;
		}

		@Override
		public String toString() {
			return this.getSignature();
		}

		static {
			SIGNATURE_STRING_PATTERN = Pattern.compile("(.+) (.*)\\((.*)\\)");
		}
	}
}
