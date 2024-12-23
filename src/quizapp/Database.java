package quizapp;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {
    private static final String URL = "jdbc:postgresql://localhost:5432/quizApp";
    private static final String USER = "postgres";
    private static final String PASSWORD = "1234";

    public static List<Question> getRandomTrueFalseQuestions(int numQuestions) {
        List<Question> questions = new ArrayList<>();
        String query = "SELECT question_text, correct_answer FROM tfquestions ORDER BY RANDOM() LIMIT ?";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement statement = connection.prepareStatement(query)) {
            // Set the limit of questions to fetch
            statement.setInt(1, numQuestions);
            // Execute the query and get the result set
            ResultSet resultSet = statement.executeQuery();
            // Process the result set
            while (resultSet.next()) {
                String questionText = resultSet.getString("question_text");
                boolean correctAnswer = resultSet.getBoolean("correct_answer"); 
                int correctAnswer_ ;
                if(correctAnswer) {
                	correctAnswer_ = 1; // 1 True
                } else {
                	correctAnswer_ =2; // 2 False
                }
                // Create a TrueFalseQuestion object and add it to the list
                questions.add(new TrueFalseQuestion(questionText, correctAnswer_));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return questions;
    }
    
    public static List<Question> getRandomMultipleChoiceQuestions(int numQuestions) {
        List<Question> questions = new ArrayList<>();
        String query = "SELECT question_text, correct_option, answer_options FROM multiple_choice_questions ORDER BY RANDOM() LIMIT ?";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement statement = connection.prepareStatement(query)) {
            // Set the limit of questions to fetch
            statement.setInt(1, numQuestions);
            // Execute the query and get the result set
            ResultSet resultSet = statement.executeQuery();
            // Process the result set
            // Process the result set
            while (resultSet.next()) {
                String questionText = resultSet.getString("question_text");
                int correctOption = resultSet.getInt("correct_option");
                String[] options = (String[]) resultSet.getArray("answer_options").getArray();

                // Create a new SelectionQuestion object and add it to the list
                SelectionQuestion question = new SelectionQuestion(questionText, correctOption, options);
                questions.add(question);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return questions;
    }
    
    
    public static List<Question> getRandomOpenEndedQuestions(int numQuestions) {
        List<Question> questions = new ArrayList<>();
        String query = "SELECT question_text, correct_answer FROM open_ended_questions ORDER BY RANDOM() LIMIT ?";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement statement = connection.prepareStatement(query)) {
            // Set the limit of questions to fetch
            statement.setInt(1, numQuestions);
            // Execute the query and get the result set
            ResultSet resultSet = statement.executeQuery();
            // Process the result set
            // Process the result set
            while (resultSet.next()) {
                String questionText = resultSet.getString("question_text");
                String correctOption = resultSet.getString("correct_answer");
               
                // Create a new SelectionQuestion object and add it to the list
                OpenEndedQuestion question = new OpenEndedQuestion(questionText, correctOption);
                questions.add(question);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return questions;
    }


    
    
    
    
    
    
}
