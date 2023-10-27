package Lab6;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ConversationSystem {
	private static ArrayList<Topic> topics = new ArrayList<Topic>();
	private static ArrayList<Var> variables = new ArrayList<Var>();

	public static Topic findTopicByName(String name) {
		for (Topic t : topics)
			if (t.getTitle().equals(name))
				return t;
		System.err.println("Topic named " + name + " not found");
		return null;
	}

	public static Var findVarialbesByName(String name) {
		for (Var v : variables)
			if (v.name.equals(name))
				return v;
		System.err.println("Topic named " + name + " not found");
		return null;
	}

	public static void main(String[] args) throws FileNotFoundException {

		topics = readConversation("Sample2.txt");
		runConversation();

	}

	public static void runConversation() {
		Scanner userInput = new Scanner(System.in);
		Topic current = findTopicByName("Start");
		while (true) {
			// displays content and options
			for (String content : current.getContent())
				if (content.startsWith("<<set", 0)) {
					setVars(content);
					continue;
				} else if (content.startsWith("<<get", 0)) {
					System.out.println(getVars(content));
					continue;
				} else if (content.startsWith("<<if", 0)) {
					System.out.println(checkVars(content));
					continue;
				} else
					System.out.println(content);

			// user options
			if (!current.hasOptions())
				break;
			for (int i = 0; i < current.getNumberOfOptions(); i++) {
				System.out.println(i + ": " + current.getOption(i).displayName);
			}
			// get user choice
			String input = userInput.next();
			if (!input.matches("\\d*")) {
				System.out.println("Invalid input");
				continue;
			}
			int choice = Integer.parseInt(input);
			// check for valid choice
			if (choice >= 0 && choice < current.getNumberOfOptions()) {
				// gets the next topic
				String nextTopicName = current.getOption(choice).topicName;
				current = findTopicByName(nextTopicName);
			} else {
				System.out.println("Invalid choice");
				continue;
			}
		}

	}

	private static String checkVars(String content) {
		String[] contentArray = content.split("@");
		String[] testClause = contentArray[1].split(" ");
		String varName = testClause[0];
		String varTest = testClause[2];
		String trueVal = contentArray[2];
		String falsVal = contentArray[3].substring(0, contentArray[3].length() - 2);

		Var variable = findVarialbesByName(varName);
		if (variable.value == Integer.parseInt(varTest)) {
			return trueVal;
		} else
			return falsVal;
	}

	private static void setVars(String content) {
		String[] contentArray = content.split(" ");
		String name = "";
		name = contentArray[1];
		String variableValue = contentArray[3].substring(0, contentArray[3].length() - 2);

		Var variable = findVarialbesByName(name);
		variable.value = Integer.valueOf(variableValue);
	}

	private static String getVars(String content) {
		String[] contentArray = content.split(" ");
		String name = "";
		name = contentArray[1].substring(0, contentArray[1].length() - 2);
		return String.valueOf(findVarialbesByName(name).value);
	}

	public static ArrayList<Topic> readConversation(String fileName) throws FileNotFoundException {
		Scanner scan = new Scanner(new File(fileName));
		Topic current = null;
		while (scan.hasNextLine()) {
			String line = scan.nextLine();
			if (line.length() < 2)// skip blank lines
				continue;
			if (line.startsWith("::")) {
				if (current != null)
					topics.add(current);
				current = new Topic(line.substring(2));
			} else if (line.startsWith("[[")) {
				String[] splitString = line.split("@", 2);
				Option option = new Option();
				option.displayName = splitString[0].substring(2);
				option.topicName = splitString.length > 1 ? splitString[1].substring(0, splitString[1].length() - 2)
						: option.displayName;
				current.addOption(option);
			} else if (line.startsWith("<<declare")) {
				Var variable = new Var();
				variable.name = line.substring(10, line.length() - 2);
				variables.add(variable);
			}

			else {
				current.addContent(line);
			}
		}
		topics.add(current);
		return topics;
	}
}
