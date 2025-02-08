package quizapp;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class Quiz {
    private List<Question> questions = new ArrayList<>();
    private int score = 0;
    private boolean timeUp = false;
    private int elapsedTimeInSeconds = 0; // Custom timer in seconds
    private Timer timer; // Timer to track elapsed time

    public Quiz(int numOfTf,int numOfSelect,int numOfOpen) {
        // Load questions from the database
    	loadQuestions(numOfTf, numOfSelect, numOfOpen);
        startTimer(); // Start the timer when the quiz is initialized
    }

    // Method to load questions
    private void loadQuestions(int numOfTf, int numOfSelect, int numOfOpen) {
        questions.addAll(Database.getRandomTrueFalseQuestions(numOfTf));
        questions.addAll(Database.getRandomMultipleChoiceQuestions(numOfSelect));
        questions.addAll(Database.getRandomOpenEndedQuestions(numOfOpen));
    }
    


    // Method to get a question by index
    public Question getQuestion(int index) {
        return questions.get(index);
    }

    // Method to get the number of questions
    public int getNumberOfQuestions() {
        return questions.size();
    }

    // Get the elapsed time in seconds
    public int getElapsedTimeInSeconds() {
        return elapsedTimeInSeconds;
    }

    // Method to check if time is up
    public boolean isTimeUp(int timeLimitInSeconds) {
        return elapsedTimeInSeconds >= timeLimitInSeconds;
    }

    // Start the timer
    private void startTimer() {
        timer = new Timer(true); // Daemon thread
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                elapsedTimeInSeconds++;
            }
        }, 0, 1000); // Increment every second
    }

    // Stop the timer
    public void stopTimer() {
        if (timer != null) {
            timer.cancel();
        }
    }

    // Method to increment the score
    public void incrementScore() {
        score++;
    }

    // Get the score
    public int getScore() {
        return score;
    }
    
    
    // abonded 
    
    // Method to add a question to the quiz
    public void addQuestion(Question question) { 
        questions.add(question);
    }
    
    public void start_(int timeLimit) { // Time limit in seconds  // Not using currently
    	Scanner scanner = new Scanner(System.in);
    	
        // Set up a timer to end the quiz after the given time limit
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                timeUp = true; // Mark that time is up
                System.out.println("\nTime's up! The quiz is now over.");
                displayScore(); // Display the final score
                System.exit(0); // Forcefully exit the quiz
            }
        }, timeLimit * 1000);
    	
    	
    	for (Question q : questions) {
    	q.displayQuestion();
    	boolean isCorrect;
    	if (q instanceof OpenEndedQuestion) {
    		String ans = scanner.nextLine();
    		isCorrect = q.checkAnswer(ans);
    	}
    	else {
    		int ans = scanner.nextInt();
    		isCorrect = q.checkAnswer(ans);
    	}
    	if(isCorrect){
    		System.out.println("Your answer is correct");
    		score ++;
    	}
    	else {
    		System.out.println("Your answer is incorrect");
    		
    	}
    	}
    	
    	scanner.close();
        timer.cancel();
    	displayScore();
    	}
    
    private void displayScore() {
        System.out.println("\nYour total score is: " + score + "/" + questions.size());
    }
    
}
