package src;

import java.lang.reflect.Method;

public class MethodGetter
{
	public static Method findMethod(String methodName) {
		Method m = null;
		try {
			m = Class.forName(Movie.class.getName()).getDeclaredMethod(methodName);
		} catch (NoSuchMethodException e) {
			System.err.println("NoSuchMethodException: " + e.getMessage());
		} catch (SecurityException e) {
			System.err.println("SecurityException: " + e.getMessage());
		} catch (ClassNotFoundException e) {
			System.err.println("ClassNotFoundException: " + e.getMessage());
		}

		return m;
	}
}