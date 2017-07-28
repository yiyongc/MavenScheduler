package assignment.exceptionlib.actions;

import java.util.Map;

public class Print extends Action {

	@Override
	public void execute(Map<String, String> attributes) {
		System.out.println("Printing Message: " + attributes.get("message"));
	}

}
