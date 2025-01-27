import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Question {
    private String questionText;
    private List<String> options;
    private int correctOptionIndex;

    public Question(String questionText, List<String> options, int correctOptionIndex) {
        this.questionText = questionText;
        this.options = options;
        this.correctOptionIndex = correctOptionIndex;
    }

    public String getQuestionText() {
        return questionText;
    }

    public List<String> getOptions() {
        return options;
    }

    public int getCorrectOptionIndex() {
        return correctOptionIndex;
    }

    public boolean isCorrect(int userAnswerIndex) {
        return userAnswerIndex == correctOptionIndex;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(questionText).append("\n");
        for (int i = 0; i < options.size(); i++) {
            sb.append((i + 1)).append(". ").append(options.get(i)).append("\n");
        }
        return sb.toString();
    }
}

class Quiz {
    private List<Question> questions;
    private int score;

    public Quiz() {
        this.questions = new ArrayList<>();
        this.score = 0;
    }

    public void addQuestion(String questionText, List<String> options, int correctOptionIndex) {
        questions.add(new Question(questionText, options, correctOptionIndex));
    }

    public void conductQuiz(User user) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome, " + user.getName() + "! Let's begin the quiz.\n");

        int questionNumber = 1;
        for (Question question : questions) {
            System.out.println("Question " + questionNumber + ":");
            System.out.println(question);
            System.out.print("Your answer (enter the option number): ");
            int userAnswer = scanner.nextInt();
            if (question.isCorrect(userAnswer - 1)) {
                System.out.println("Correct!\n");
                score++;
            } else {
                System.out.println("Wrong. The correct answer was: " +
                        question.getOptions().get(question.getCorrectOptionIndex()) + "\n");
            }
            questionNumber++;
        }

        System.out.println("Quiz completed! Your score: " + score + "/" + questions.size());
        user.addResult(score, questions.size());
    }
}

class User {
    private String name;
    private List<String> results;

    public User(String name) {
        this.name = name;
        this.results = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addResult(int score, int totalQuestions) {
        results.add("Score: " + score + "/" + totalQuestions);
    }

    public void viewResults() {
        System.out.println("Results for " + name + ":");
        for (String result : results) {
            System.out.println(result);
        }
    }
}

public class OnlineQuizSystem {
    public static void main(String[] args) {
        Quiz quiz = new Quiz();

        // Adding questions to the quiz
        quiz.addQuestion(
                "What is the capital of France?",
                List.of("Berlin", "Paris", "Madrid", "Rome"),
                1);
        quiz.addQuestion(
                "Which programming language is platform-independent?",
                List.of("C++", "Java", "Python", "C#"),
                1);
        quiz.addQuestion(
                "What is the chemical symbol for water?",
                List.of("CO2", "H2O", "O2", "NaCl"),
                1);

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your name: ");
        String userName = scanner.nextLine();
        User user = new User(userName);

        // Conducting the quiz
        quiz.conductQuiz(user);

        // Viewing results
        user.viewResults();

        scanner.close();
    }
}
