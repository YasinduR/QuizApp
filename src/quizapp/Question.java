package quizapp;

public abstract class Question {
	    protected String questionText;
	    protected String[] answers;
	    private Object correctOption;

	    // Initialize the question with multiple options
	    public Question(String questionText, int correctOption, String[] customAnswers) {
	        this.questionText = questionText;
	        this.correctOption = correctOption;
	        this.answers = customAnswers;
	    }

	    // Initialize the question with True and False
	    public Question(String questionText, int correctOption) {
	        this(questionText, correctOption, new String[]{"True", "False"});
	    }

	    // Constructor to initialize an open-ended question
	    public Question(String questionText, String correctOption) {
	        this.questionText = questionText;
	        this.correctOption = correctOption;
	        this.answers = null; // No predefined answers for open-ended questions
	    }
	    
		public abstract void displayQuestion();

	    public boolean checkAnswer(int user_Answer) { //Check 
	        if (correctOption instanceof Integer) {
	            return user_Answer == (int) correctOption;
	        }
	        return false;
	    }
	    public boolean checkAnswer(String user_Answer) {
	    	System.out.println("Your answer is :"+user_Answer);
	        return ((String) correctOption).equalsIgnoreCase(user_Answer);
	    }
	    

	}

// 1 => True 2 => False
class TrueFalseQuestion extends Question {
    public TrueFalseQuestion(String question_Text, int correct_Option) {
        super(question_Text, correct_Option);
    }

    @Override
    public void displayQuestion() {
        System.out.println(questionText);
        System.out.println("1. True");
        System.out.println("2. False");
    }
    
}


class SelectionQuestion extends Question { // selection from 4 answers
    public SelectionQuestion(String question_Text, int correct_Option,String[] All_options) {
        super(question_Text, correct_Option,All_options);
    }
    
    @Override
    public void displayQuestion() {
        System.out.println(questionText);
        for (int i = 0; i < answers.length; i++) {
            System.out.println((i + 1) + ". " + answers[i]);
        }
    }
}

class OpenEndedQuestion extends Question { // selection from 4 answers
    public OpenEndedQuestion(String question_Text,String answer_Text) {
        super(question_Text,answer_Text);
    }
    
    @Override
    public void displayQuestion() {
        System.out.println(questionText);
    }
}









