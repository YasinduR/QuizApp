package quizapp;

public abstract class Question {
	    protected String questionText;
	    private int correctOption;

	    public Question(String question_Text, int correct_Option) {
	        this.questionText = question_Text;
	        this.correctOption = correct_Option;
	    }

	    public abstract void displayQuestion();

	    public boolean checkAnswer(int user_Answer) {
	        return user_Answer == correctOption;
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
