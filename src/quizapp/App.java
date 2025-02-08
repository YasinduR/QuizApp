package quizapp;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class App extends Application {

    private Quiz quiz;
    private String[] answersProvided;
    private int currentQuestionIndex = 0;
    
    private Object currentanswer;
    private int currentScore = 0;
    
    private Label questionLabel,questionNumberLabel,scoreLabel,correctAnswerLabel,timeLabel;
    private RadioButton option1, option2, option3, option4;
    
    private TextField answerField;
    private ToggleGroup optionsGroup;
    
    private Scene questionScene,endScene;
    
    private Timeline timeoutTimer; 
    private int timeElapsed =0;
    private int timeLimit =25;


    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Quiz App");

        // Initialize the Quiz
        intialize();

        // GUI Components
        questionLabel = new Label();
        scoreLabel = new Label("Current Score: ");
        questionNumberLabel = new Label();
        scoreLabel = new Label("Current Score: ");
        correctAnswerLabel =  new Label("");
        
        option1 = new RadioButton("1");
        option2 = new RadioButton("2");
        option3 = new RadioButton("3");
        option4 = new RadioButton("4");

        optionsGroup = new ToggleGroup();
        option1.setToggleGroup(optionsGroup);
        option2.setToggleGroup(optionsGroup);
        option3.setToggleGroup(optionsGroup);
        option4.setToggleGroup(optionsGroup);
        
        // Answer Text Field for Open-Ended Questions
        answerField = new TextField();
        answerField.setPromptText("Type your answer here");
 
        // Set Answer Field
        answerField.setMaxWidth(300); // Maximum width
        answerField.setMinWidth(100); // Minimum width
        
        // Width of the text box
        // Create question layout and scene
        VBox questionLayout = createMainLayout();
        questionScene = new Scene(questionLayout, 600, 400);

        // Create end layout and scene
        VBox endLayout = createEndLayout(primaryStage);
        endScene = new Scene(endLayout,  600, 400);
        
        primaryStage.setScene(questionScene);
        
        // Load the first question
        loadQuestion(quiz.getQuestion(currentQuestionIndex));
        
        // Start the timeout timer
        startTimeoutTimer();
        
        // Show the Stage
        primaryStage.show();
    }
    

    private VBox createEndLayout(Stage primaryStage) {
    	VBox layout = new VBox(10);
    	layout.setAlignment(Pos.CENTER);

    	Label endMessage = new Label("Quiz Completed!");
    	Label finalScore = new Label("Your Final Score: " + currentScore);

    	Button restartButton = new Button("Restart Quiz");
    	Button ExploreResultButton = new Button("Restart Quiz");
    	
    	
    	restartButton.setOnAction(e -> restartQuiz(primaryStage));
    	ExploreResultButton.setOnAction(e -> ExploreResult(primaryStage));
    	
    	layout.getChildren().addAll(endMessage, finalScore, restartButton,ExploreResultButton);
    	return layout;
    }

	private VBox createMainLayout() {
        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER); // Align items in the center
        layout.setPadding(new Insets(20, 20, 20, 20)); // Add padding around the VBox
        // Time Label and Timer
        timeLabel = new Label("Time Remaining: " + timeLimit + " seconds");
        Button nextButton = new Button("Next");
        nextButton.setOnAction(e -> loadNextQuestion());
        layout.getChildren().addAll(
            timeLabel, scoreLabel, questionNumberLabel, questionLabel, 
            answerField, option1, option2, option3, option4, nextButton,correctAnswerLabel
        );
        return layout;
    }
    
    
    private void intialize() {
    	quiz = new Quiz(4,4,2);
    	answersProvided = new String[quiz.getNumberOfQuestions()]; // Record Answers provided
    }
    
    private void restartQuiz(Stage primaryStage) {
        // Reset quiz logic
    	timeLabel.setText("Time Remaining: " + timeLimit  + " seconds");
        currentQuestionIndex = 0;
        currentScore = 0;
        intialize();

        // Reload the first question
        loadQuestion(quiz.getQuestion(currentQuestionIndex));

        startTimeoutTimer();
        // Switch to the question scene
        primaryStage.setScene(questionScene);
    }
    
    private void ExploreResult(Stage primaryStage) {
    	 
    	Scene ExploreScene =new QuizHistory(quiz, answersProvided).generateScene();
    	primaryStage.setScene(ExploreScene); // Switch the scene
    }
    
    private void resetFields() {
    	
    	// Reset answer
    	currentanswer=null;
    	
        // Reset the selection for option buttons 
        option1.setSelected(false);
        option1.setManaged(false); // ignore the space taken by op1
        
        option2.setSelected(false);
        option3.setSelected(false);
        option4.setSelected(false);
        
        // Reset the visibility
        option1.setVisible(false);
        option2.setVisible(false);
        option3.setVisible(false);
        option4.setVisible(false);
        
        // Reset the text fields
        answerField.setVisible(false);
        answerField.setManaged(false); // ignore the space taken 
        answerField.clear();
    }
    
    private int getCurrentSelectedOption() {
        if (option1.isSelected()) {
            return 1;
        } else if (option2.isSelected()) {
            return 2;
        } else if (option3.isSelected()) {
            return 3;
        } else if (option4.isSelected()) {
            return 4;
        }
        return 0;
    }
    
    private Object getCurrentSelectedOptionTorF() {
        if (option1.isSelected()) {
        	return true;
        }
        else if (option2.isSelected()) {
        	return false;
        }
        return 0; // Case if not answered
    }
    
    
    
    private void setAnswers(Question question) { // SET VALUES FOR ANSWER OPTIONS
    	List<String> answers = new ArrayList<>(Arrays.asList(((Question) question).getAnswers()));
        if((question instanceof TrueFalseQuestion)) {
        	answers.addAll(Arrays.asList("", ""));
        }
        option1.setText(answers.get(0));
        option2.setText(answers.get(1));
        option3.setText(answers.get(2));
        option4.setText(answers.get(3));
    }
    
    
    
    
    private void loadQuestion(Question question) {
    	resetFields();
    	scoreLabel.setText("Current Score: " +currentScore);
        questionLabel.setText(question.getQuestionText());
        questionNumberLabel.setText("Question Number : "+(currentQuestionIndex+1));
        
        if (question instanceof SelectionQuestion) 
        {
        	setAnswers(question);
            option1.setVisible(true);
            option1.setManaged(true);
            option2.setVisible(true);
            option3.setVisible(true);
            option4.setVisible(true);
        } 
        else if(question instanceof TrueFalseQuestion)
        {
    	   	setAnswers(question);
            option1.setVisible(true);
            option1.setManaged(true);
            option2.setVisible(true);
        }
        else if(question instanceof OpenEndedQuestion) 
        {
    	   answerField.setVisible(true);
    	   answerField.setManaged(true);
        }
    }
    
    private boolean checkAnswer(Question question) {
    	boolean isCorrect;
    	 if (question instanceof SelectionQuestion) {
    		 currentanswer = getCurrentSelectedOption();
    		 answersProvided[currentQuestionIndex]= question.getAnswerProvided((int) currentanswer);
    		 isCorrect = question.checkAnswer((int)currentanswer);
    	 }
    	 else if(question instanceof TrueFalseQuestion){
    		 currentanswer = getCurrentSelectedOptionTorF();
    		 if(currentanswer instanceof Boolean) {
    			 isCorrect = question.checkAnswer((boolean)currentanswer);
    			 answersProvided[currentQuestionIndex]= question.getAnswerProvided((boolean) currentanswer);
    		 }
    		 else {
    			 isCorrect = false;
    		 }
    	 }
    	 else { // open ended question
    		 currentanswer = answerField.getText().trim();
    		 answersProvided[currentQuestionIndex]= question.getAnswerProvided((String) currentanswer);
    		 isCorrect = question.checkAnswer((String)currentanswer);
    		 
    	 }
    	 
    	 correctAnswerLabel.setText(question.getQuestionText()+ " : "+ 	question.getCorrectAnswer());
    	 
    	 return isCorrect;
    }
    
    private void quizEnd() {
    	
    	timeoutTimer.stop();// Stop timer
    	resetTime(); // Elapsed Time = 0
    	// Update final score
        VBox endLayout = (VBox) endScene.getRoot();
        ((Label) endLayout.getChildren().get(1)).setText("Your Final Score: " + currentScore);
        // Switch to end scene
        Stage primaryStage = (Stage) questionLabel.getScene().getWindow();
        primaryStage.setScene(endScene);
    }
    
  
    private void loadNextQuestion() {
    	
    	if(checkAnswer(quiz.getQuestion(currentQuestionIndex))) {
    		currentScore++;
    	};
    	//recordCurrentAnswer();
        currentQuestionIndex++; // next questiom
        if (currentQuestionIndex < quiz.getNumberOfQuestions()) {
            loadQuestion(quiz.getQuestion(currentQuestionIndex));
        }
        else {
        	quizEnd();
        }
    }

    // Timer
    private void startTimeoutTimer() {
        timeoutTimer = new Timeline(new KeyFrame(Duration.seconds(1), e -> timer()));
        timeoutTimer.setCycleCount(Timeline.INDEFINITE); // Run contiously
        timeoutTimer.play();
    }
    
    private void timer () {
    	timeElapsed++;
    	if(timeElapsed>=timeLimit) { handleTimeout(); }
    	else {timeLabel.setText("Time Remaining: " + (timeLimit- timeElapsed)  + " seconds");}
    }

    private void handleTimeout() {
        // Stop the quiz and show the end screen
    	quizEnd();
    }

    private void resetTime() {
    	
    	timeElapsed =0;
    }
    
    
    
    
    
    
    
    
    
    public static void main(String[] args) {
        launch(args);
    }
    

}

