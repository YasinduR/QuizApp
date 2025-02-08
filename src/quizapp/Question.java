package quizapp;

public abstract class Question {
	    protected String questionText;
	    protected String[] answers;
	    protected Object correctOption; //BOOL FOR TF QUESTION INTERGER FOR MULTIPLE ANS Q'S STRING FOR OPEN

	    // Initialize the question with multiple options
	    public Question(String questionText, int correctOption, String[] customAnswers) {
	        this.questionText = questionText;
	        this.correctOption = correctOption;
	        this.answers = customAnswers;
	    }

	    // Initialize the question with True and False
	    public Question(String questionText, boolean correctOption) {
	    	this.questionText = questionText;
	    	this.correctOption = correctOption;
	    	this.answers = new String[]{"True", "False"};
	    }

	    // Constructor to initialize an open-ended question
	    public Question(String questionText, String correctOption) {
	        this.questionText = questionText;
	        this.correctOption = correctOption;
	        this.answers = null; // No predefined answers
	    }
	    
	    public String getQuestionText() { 
	    	return questionText;
	    }
	    
	    
	    public String[] getAnswers() {
	    	return answers;
	    }
	 
		public abstract void displayQuestion(); // USED FOR VERSION WITHOUT GUI

	    public boolean checkAnswer(int user_Answer) { //Check questions with multiple options
	        if (correctOption instanceof Integer) {
	            return user_Answer == (int) correctOption;
	        }
	        return false;
	    }
	    
	    public boolean checkAnswer(boolean user_Answer) { //Check questions with TF questions
	        if (correctOption instanceof Boolean) {
	            return user_Answer == (boolean) correctOption;
	        }
	        return false;
	    }
	    
	    public boolean checkAnswer(String user_Answer) { //Check questions with multiple options
	        return ((String) correctOption).equalsIgnoreCase(user_Answer);
	    }
	    
	    
		public String getAnswerProvided(boolean answer) {
			if((boolean) answer) {
				return "True";
			}
			return "False";
		}
		
		public String getAnswerProvided(int answer) {
			if(answer>=1 && answer<=4 ) {
				return answers[answer-1];
			}
			else {
				return null;
			}
		}
		
		public String getAnswerProvided(String answer) {
			return answer;
		}
	    
	    public abstract String getCorrectAnswer();


		

	}

// 1 => True 2 => False
class TrueFalseQuestion extends Question {
    public TrueFalseQuestion(String question_Text, boolean correct_Option) {
        super(question_Text, correct_Option);
    }

    @Override
    public void displayQuestion() {
        System.out.println(questionText);
        System.out.println("1. True");
        System.out.println("2. False");
    }

	@Override
	public String getCorrectAnswer() {
		if((boolean) correctOption) {
			return "The Statement was true";
		}
		return "The Statement was false";
	}
	
	public String getAnswerProvided(boolean answer) {
		if((boolean) answer) {
			return "True";
		}
		return "False";
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

	@Override
	public String getCorrectAnswer() {
		return answers[(Integer)correctOption-1]; // 0 TH ELEMENT OF THE ARRAY IS THE FIRST OPTION AND LIKEWIS
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

	@Override
	public String getCorrectAnswer() {
		return (String)correctOption;
	}
}









