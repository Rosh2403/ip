public class Parser {
    private static final int TODO_PREFIX_LENGTH = 5;
    private static final int DEADLINE_PREFIX_LENGTH = 9;
    private static final int EVENT_PREFIX_LENGTH = 6;
    private static final int MARK_PREFIX_LENGTH = 5;
    private static final int DELETE_PREFIX_LENGTH = 7;
    private static final int BY_SLASH_OFFSET = 4;
    private static final int FROM_SLASH_OFFSET = 6;
    private static final int TO_SLASH_OFFSET = 4;

    public static Command parse(String line) {
        if (line.equals("bye")) {
            return new ExitCommand();
        } else if (line.equals("list")) {
            return new ListCommand();
        } else if (line.startsWith("mark")) {
            if (line.trim().equals("mark")) {
                return new InvalidCommand("You need a task number to mark");
            }
            int index = Integer.parseInt(line.substring(MARK_PREFIX_LENGTH)) - 1;
            return new MarkCommand(index);
        } else if (line.startsWith("delete")) {
            if (line.trim().equals("delete")) {
                return new InvalidCommand("You need a task number to delete");
            }
            int index = Integer.parseInt(line.substring(DELETE_PREFIX_LENGTH)) - 1;
            return new DeleteCommand(index);
        } else if (line.startsWith("todo")) {
            if (line.trim().equals("todo")) {
                return new InvalidCommand("You need a description for your todo");
            }
            String description = line.substring(TODO_PREFIX_LENGTH);
            return new AddTodoCommand(description);
        } else if (line.startsWith("deadline")) {
            if (line.trim().equals("deadline")) {
                return new InvalidCommand("You need a description for your deadline");
            }
            if (!line.contains("/by")) {
                return new InvalidCommand("Deadline format: deadline <description> /by <date>");
            }
            int slashIndex = line.indexOf("/");
            String description = line.substring(DEADLINE_PREFIX_LENGTH, slashIndex).trim();
            if (description.isEmpty()) {
                return new InvalidCommand("You need a description for your deadline");
            }
            String byDate = line.substring(slashIndex + BY_SLASH_OFFSET).trim();
            return new AddDeadlineCommand(description, byDate);
        } else if (line.startsWith("event")) {
            if (line.trim().equals("event")) {
                return new InvalidCommand("You need a description for your event");
            }
            if (!line.contains("/from") || !line.contains("/to")) {
                return new InvalidCommand("Event format: event <description> /from <start> /to <end>");
            }
            int slashIndex1 = line.indexOf("/");
            int slashIndex2 = line.indexOf("/", slashIndex1 + 1);
            String description = line.substring(EVENT_PREFIX_LENGTH, slashIndex1).trim();
            if (description.isEmpty()) {
                return new InvalidCommand("You need a description for your event");
            }
            String fromWhen = line.substring(slashIndex1 + FROM_SLASH_OFFSET, slashIndex2).trim();
            String toWhen = line.substring(slashIndex2 + TO_SLASH_OFFSET).trim();
            return new AddEventCommand(description, fromWhen, toWhen);
        } else {
            return new InvalidCommand("I don't understand what you mean");
        }
    }
}