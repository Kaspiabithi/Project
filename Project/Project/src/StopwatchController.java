import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.SelectionMode;

/**
 * FXML Controller class for Stopwatch
 *
 * @author Walton
 */
public class StopwatchController {

    @FXML
    private Label displayLabel;
    @FXML
    private Label alarmLabel; // Label for showing "Time is up"
    @FXML
    private ListView<String> lapListView;
    @FXML
    private Button startButton;
    @FXML
    private Button lapButton;
    @FXML
    private Button pauseButton;
    @FXML
    private Button resetButton;
    @FXML
    private Button stopButton;
    @FXML
    private TextField lapUpdateTextField;
    @FXML
    private TextField alarmHoursTextField; // TextField for hours
    @FXML
    private TextField alarmMinutesTextField; // TextField for minutes
    @FXML
    private TextField alarmSecondsTextField; // TextField for seconds

    private long startTime;
    private long pauseTime = 0;
    private boolean paused = false;
    private boolean running = false;

    private AnimationTimer timer;
    private ObservableList<String> laps = FXCollections.observableArrayList();
    
    private long alarmTimeMillis = 0; // Alarm time in milliseconds

    public void initialize() {
        // Lap list view initialization
        lapListView.setItems(laps);
        lapListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        alarmLabel.setText(""); // Initialize empty
    }

    @FXML
    private void handleStart() {
        if (!running) {
            if (paused) {
                startTime = System.currentTimeMillis() - pauseTime;
            } else {
                startTime = System.currentTimeMillis();
            }
            running = true;
            paused = false;

            timer = new AnimationTimer() {
                @Override
                public void handle(long now) {
                    long elapsedMillis = System.currentTimeMillis() - startTime;
                    updateDisplay(elapsedMillis);
                    
                    // Check if alarm time is reached
                    if (alarmTimeMillis > 0 && elapsedMillis >= alarmTimeMillis) {
                        alarmLabel.setText("Time is up!"); // Show alarm message
                        alarmTimeMillis = 0; // Disable further checks
                    }
                }
            };
            timer.start();
        }
    }

    @FXML
    private void handleLap() {
        if (running) {
            long elapsedMillis = System.currentTimeMillis() - startTime;
            laps.add(formatTime(elapsedMillis));
        }
    }

    @FXML
    private void handlePause() {
        if (running && !paused) {
            timer.stop();
            pauseTime = System.currentTimeMillis() - startTime;
            running = false;
            paused = true;
        } else if (paused) {
            handleStart();
        }
    }

    @FXML
    private void handleReset() {
        displayLabel.setText("00:00:00");
        laps.clear();
        timer.stop();
        running = false;
        paused = false;
        pauseTime = 0;
        alarmLabel.setText(""); // Clear alarm label
    }

    @FXML
    private void handleStop() {
        if (timer != null) {
            timer.stop();
        }
        running = false;
        paused = false;
        pauseTime = 0;
    }

    private void updateDisplay(long elapsedMillis) {
        displayLabel.setText(formatTime(elapsedMillis));
    }

    private String formatTime(long elapsedMillis) {
        long hours = (elapsedMillis / 3600000);
        long minutes = (elapsedMillis / 60000) % 60;
        long seconds = (elapsedMillis / 1000) % 60;
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

    // CRUD Operations for laps

    @FXML
    private void handleDeleteLap() {
        String selectedLap = lapListView.getSelectionModel().getSelectedItem();
        if (selectedLap != null) {
            laps.remove(selectedLap);
        }
    }

    @FXML
    private void handleUpdateLap() {
        String selectedLap = lapListView.getSelectionModel().getSelectedItem();
        String newLapText = lapUpdateTextField.getText();
        int selectedIndex = lapListView.getSelectionModel().getSelectedIndex();
        if (selectedLap != null && newLapText != null && !newLapText.isEmpty()) {
            laps.set(selectedIndex, newLapText);
        }
    }

    private void handleReadLap() {
        String selectedLap = lapListView.getSelectionModel().getSelectedItem();
        if (selectedLap != null) {
            lapUpdateTextField.setText(selectedLap);
        }
    }

    // Handle alarm setting
    @FXML
    private void handleSetAlarm() {
        try {
            long hours = Long.parseLong(alarmHoursTextField.getText());
            long minutes = Long.parseLong(alarmMinutesTextField.getText());
            long seconds = Long.parseLong(alarmSecondsTextField.getText());

            // Convert to total milliseconds
            alarmTimeMillis = (hours * 3600 + minutes * 60 + seconds) * 1000;

            if (alarmTimeMillis > 0) {
                alarmLabel.setText("Alarm set for " + formatTime(alarmTimeMillis));
            } else {
                alarmLabel.setText("Invalid time, please set a valid time.");
            }
        } catch (NumberFormatException e) {
            alarmLabel.setText("Invalid time format");
        }
    }
}
