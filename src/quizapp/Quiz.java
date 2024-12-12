package quizapp;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Quiz {
    private List<Question> questions = new ArrayList<>();
    private int score = 0;

    public void addQuestion(Question question) {
        questions.add(question);
    }
    
    public void start() {
    	Scanner scanner = new Scanner(System.in);
    	for (Question q : questions) {
    	q.displayQuestion();
    	int ans = scanner.nextInt();
    	if(q.checkAnswer(ans)){
    		System.out.println("Your answer is correct");
    		score ++;
    	}
    	else {
    		System.out.println("Your answer is incorrect");
    		
    	}
    	}
    	System.out.println("Your total score is: " + score + "/" + questions.size());
    	}
    
}
