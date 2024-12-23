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
    

    public void addQuestion(Question question) {
        questions.add(question);
    }
    
    public void start(int timeLimit) { // Time limit in seconds
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
