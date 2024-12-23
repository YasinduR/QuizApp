package quizapp;

import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
        System.out.println("WELCOME TO THE QUIZ APP");

        
        // Initialize the Quiz 
        Quiz quiz = new Quiz();
        
        List<Question> tfquestions = Database.getRandomTrueFalseQuestions(5); //load true false questions from database
        List<Question> mcquestions = Database.getRandomMultipleChoiceQuestions(3); //load multiple choice questions from database
        List<Question> openquestions = Database.getRandomOpenEndedQuestions(2); //load open ended questions from database
        
        for (Question q : tfquestions) {
        	 quiz.addQuestion(q);
        }	
        
        for (Question q : mcquestions) {
       	 quiz.addQuestion(q);
       }
        
        for (Question q : openquestions) {
          	 quiz.addQuestion(q);
          }

        // Start the quiz
        quiz.start(60);
        
        
        //--------
        // Sample tf questions
        TrueFalseQuestion q1 = new TrueFalseQuestion("The Earth is flat.",2); // 1 true 2 false
        TrueFalseQuestion q2 = new TrueFalseQuestion("Water boils at 100 degrees Celsius at sea level.",1);
        
        // Sample multiple-choice questions
        SelectionQuestion q3 = new SelectionQuestion(
            "What is the chemical symbol for water?",
            2,
            new String[]{"H2", "H2O", "HO", "H2O2"}
        );
        SelectionQuestion q4 = new SelectionQuestion(
            "Which planet is known as the Red Planet?",
            3,
            new String[]{"Earth", "Venus", "Mars", "Jupiter"}
        );
        SelectionQuestion q5 = new SelectionQuestion(
            "What is the speed of light in a vacuum?",
            4,
            new String[]{"150,000 km/s", "200,000 km/s", "250,000 km/s", "300,000 km/s"}
        );

        // Sample open-ended questions
        OpenEndedQuestion q6 = new OpenEndedQuestion(
            "What is the largest organ in the human body?",
            "skin"
        );
        OpenEndedQuestion q7 = new OpenEndedQuestion(
            "What gas do plants primarily use for photosynthesis?",
            "carbon dioxide"
        );
        
        // Add questions
        //quiz.addQuestion(q1);
        //quiz.addQuestion(q2);
        //quiz.addQuestion(q3);
        //quiz.addQuestion(q4);
        //quiz.addQuestion(q5);
        //quiz.addQuestion(q6);
        //quiz.addQuestion(q7);

    }
}
