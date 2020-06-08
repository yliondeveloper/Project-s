package xyz.starmc.hologram;

public enum TouchAction {

	RIGHT_CLICK, LEFT_CLICK, UNKNOWN;

	public static TouchAction fromUseAction(Object useAction) {
		if (useAction == null) {
			return TouchAction.UNKNOWN;
		}
		int i = ((Enum<?>) useAction).ordinal();
		switch (i) {
		case 0: {
			return TouchAction.RIGHT_CLICK;
		}
		case 1: {
			return TouchAction.LEFT_CLICK;
		}
		default: {
			return TouchAction.UNKNOWN;
		}
		}
	}
}
