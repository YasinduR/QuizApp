package quizapp;


import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;



public class QuizHistory {  // Preview result of the quiz
	 private Quiz quiz;
	 private String[] answers;
	 private Scene quizHistory;
	 
	 
	 QuizHistory(Quiz quiz, String[] answers) {
		 this.quiz = quiz;
		 this.answers = answers;
	 }
	 
	    // Generate the Scene
	    public Scene generateScene() {
	        VBox layout = new VBox(10); // VBox with 10px spacing
	        layout.setStyle("-fx-padding: 20; -fx-alignment: top-left;");

	        // Add all questions and provided answers to the layout
	        for (int i = 0; i < quiz.getNumberOfQuestions(); i++) {
	        	
	            String question = quiz.getQuestion(i).getQuestionText();
	            String correctAnswer = quiz.getQuestion(i).getCorrectAnswer();
	            String answer = (answers[i]) != null ? answers[i] : "No answer provided";
	            

	            Label questionLabel = new Label("Q" + (i + 1) + ": " + question);
	            Label answerLabel = new Label("Your Answer: " + answer);
	            Label CorrectAnswerLabel = new Label("Correct Answer: " + correctAnswer);
	            answerLabel.setStyle("-fx-font-style: italic; -fx-text-fill: gray;-fx-text-fill: gray;");

	            layout.getChildren().addAll(questionLabel, answerLabel,CorrectAnswerLabel);
	        }

	        quizHistory = new Scene(layout, 600, 400); // Width: 400, Height: 600
	        return quizHistory;
	    }
	 
}
