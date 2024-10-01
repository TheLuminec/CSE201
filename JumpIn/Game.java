import java.util.*;
import java.io.*;

public class Game {
    private Map<String, Room> rooms = new HashMap<>();
    private Player player = new Player();
    private Room currentRoom;
    private int timer;

    public void loadGame(String filePath) throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        Room room = null;
        Option option = null;
        Description description = null;

        while ((line = reader.readLine()) != null) {
            line = line.trim();

            // Skip empty lines and comments
            if (line.isEmpty() || line.startsWith("#")) {
                continue;
            }

            // Global settings
            if (line.startsWith("TIMER=")) {
                timer = Integer.parseInt(line.substring(6));
                continue;
            }

            if (line.startsWith("START=")) {
                String startRoomId = line.substring(6);
                currentRoom = new Room(startRoomId);
                rooms.put(startRoomId, currentRoom);
                continue;
            }

            // Room start
            if (line.startsWith("<") && line.endsWith(">")) {
                String roomId = line.substring(1, line.length() - 1);
                room = new Room(roomId);
                rooms.put(roomId, room);
                continue;
            }

            // Description start
            if (line.equals("[DESCRIPTION]")) {
                description = new Description();
                room.addDescription(description);
                option = null; // Reset option
                continue;
            }

            // Option start
            if (line.equals("[O]")) {
                option = new Option();
                room.addOption(option);
                description = null;
                continue;
            }

            if (line.contains("=")) {
                String[] parts = line.split("=", 2);
                String key = parts[0].trim();

                if (key.equals("TEXT")) {
                    StringBuilder textBuilder = new StringBuilder();
                    String value = parts[1].trim();

                    if (value.isEmpty()) {
                        // Multi-line text
                        while ((line = reader.readLine()) != null) {
                            line = line.trim();
                            if (line.equals("ENDTEXT")) {
                                break;
                            }
                            textBuilder.append(line).append("\n");
                        }
                    } else {
                        textBuilder.append(value);
                    }

                    String textValue = textBuilder.toString().trim();

                    if (description != null) {
                        description.setText(textValue);
                    } 
                    else if (option != null) {
                        option.setText(textValue);
                    }
                } 
                else {
                    String value = parts[1].trim();
                    if (key.equals("CONDITION")) {
                        List<String> conditions = Arrays.asList(value.split(","));
                        if (description != null) {
                            description.setConditions(conditions);
                        } else if (option != null) {
                            option.setConditions(conditions);
                        }
                    } 
                    else if (key.equals("ACTIONS") && option != null) {
                        String[] actions = value.split(",");
                        option.setActions(Arrays.asList(actions));
                    } 
                    else if (key.equals("TIMECOST") && option != null) {
                        option.setTimeCost(Integer.parseInt(value));
                    } 
                    else if (key.equals("NEXT") && option != null) {
                        option.setNextRoomId(value);
                    }
                }
            }
        }
        reader.close();

        if (currentRoom == null) {
            throw new Exception("Starting room not defined in the script.");
        } else {
            currentRoom = rooms.get(currentRoom.getId());
        }
    }

    public void play() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Starting...");

        main:while (true) {
            // Check if timer has run out
            if (timer <= 0) {
                if (rooms.containsKey("timeEnd")) {
                    currentRoom = rooms.get("timeEnd");
                } 
                else {
                    System.out.println("Time's up! Game over.");
                    break;
                }
            }

            // Display timer
            System.out.println("\nTime remaining: " + timer);

            // Display room descriptions
            displayRoomDescriptions();

            // Display available options
            List<Option> availableOptions = getAvailableOptions();
            if (availableOptions.isEmpty()) {
                break;
            }

            for (int i = 0; i < availableOptions.size(); i++) {
                Option opt = availableOptions.get(i);
                System.out.println((i + 1) + ". " + opt.getText() +
                        (opt.getTimeCost() > 1 ? " (Time Cost: " + opt.getTimeCost() + ")" : ""));
            }

            // Get choice
            System.out.print("Choose an option: ");
            int choice = 0;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } 
            catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            if (choice == -1) {
                System.out.println("Exiting...");
                break;
            } 
            else if (choice < 1 || choice > availableOptions.size()) {
                System.out.println("Invalid choice. Try again.");
                continue;
            }

            Option selectedOption = availableOptions.get(choice - 1);
            processOption(selectedOption);

            for(Description d : currentRoom.getDescriptions()) {
                if (d.getConditions() != null && d.getConditions().contains("endGame")) {
                    displayRoomDescriptions();
                    
                    break main;
                }
            }
        }
        scanner.close();
    }

    private void displayRoomDescriptions() {
        for (Description description : currentRoom.getDescriptions()) {
            if (isConditionMet(description.getConditions())) {
                System.out.println(description.getText());
            }
        }
    }

    private List<Option> getAvailableOptions() {
        List<Option> availableOptions = new ArrayList<>();

        for (Option option : currentRoom.getOptions()) {
            if (isOptionAvailable(option)) {
                availableOptions.add(option);
            }
        }

        return availableOptions;
    }

    private boolean isOptionAvailable(Option option) {
        return isConditionMet(option.getConditions());
    }

    private boolean isConditionMet(List<String> conditions) {
        if (conditions == null || conditions.isEmpty()) {
            return true;
        }

        for (String condition : conditions) {
            condition = condition.trim();
            boolean negate = condition.startsWith("!");
            String item = negate ? condition.substring(1) : condition;
            boolean hasItem = player.hasItem(item);
            boolean conditionMet = negate ? !hasItem : hasItem;

            if (!conditionMet) {
                return false; // If any condition is not met, return false
            }
        }
        return true;
    }

    private void processOption(Option option) {
        timer -= option.getTimeCost();

        if (option.getActions() != null) {
            for (String action : option.getActions()) {
                processAction(action.trim());
            }
        }

        currentRoom = rooms.get(option.getNextRoomId());
        if (currentRoom == null) {
            System.out.println("The room '" + option.getNextRoomId() + "' does not exist.");
            System.exit(0);
        }
    }

    private void processAction(String action) {
        if (action.startsWith("addItem:")) {
            String item = action.substring(8);
            player.addItem(item);
        } else if (action.startsWith("removeItem:")) {
            String item = action.substring(11);
            player.removeItem(item);
        }
    }
}
