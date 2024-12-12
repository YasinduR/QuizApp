package quizapp;

import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
        System.out.println("WELCOME TO THE QUIZ APP");
        // Sample tf questions
        TrueFalseQuestion q1 = new TrueFalseQuestion("The Earth is flat.",2); // 1 true 2 false
        TrueFalseQuestion q2 = new TrueFalseQuestion("Water boils at 100 degrees Celsius at sea level.",1);
        
        // Initialize the Quiz 
        Quiz quiz = new Quiz();
        
        // Add qssdss
        quiz.addQuestion(q1);
        quiz.addQuestion(q2);

        // Start the quiz
        quiz.start();
        
        
    }
}
