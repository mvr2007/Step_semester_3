import java.util.*;
// --- Question Abstraction & Types ---
abstract class Question {
    private String id;
    private String text;
    private int maxPoints;

    public Question(String id, String text, int maxPoints) {
        this.id = id;
        this.text = text;
        this.maxPoints = maxPoints;
    }

    public String getId() { return id; }
    public int getMaxPoints() { return maxPoints; }

    public abstract int evaluate(String answer);
}

class MultipleChoiceQuestion extends Question {
    private String correctAnswer;

    public MultipleChoiceQuestion(String id, String text, int maxPoints, String correctAnswer) {
        super(id, text, maxPoints);
        this.correctAnswer = correctAnswer;
    }

    @Override
    public int evaluate(String answer) {
        return correctAnswer.equalsIgnoreCase(answer) ? getMaxPoints() : 0;
    }
}

class TrueFalseQuestion extends Question {
    private String correctAnswer;

    public TrueFalseQuestion(String id, String text, int maxPoints, String correctAnswer) {
        super(id, text, maxPoints);
        this.correctAnswer = correctAnswer;
    }

    @Override
    public int evaluate(String answer) {
        return correctAnswer.equalsIgnoreCase(answer) ? getMaxPoints() : 0;
    }
}

// --- Examination ---
class Examination {
    private String examId;
    private String title;
    private List<Question> questions = new ArrayList<>();

    public Examination(String examId, String title) {
        this.examId = examId;
        this.title = title;
    }

    public void addQuestion(Question q) { questions.add(q); }
    public List<Question> getQuestions() { return questions; }
    public String getTitle() { return title; }
}

// --- Student & Attempt ---
class Student {
    private String id;
    private String name;

    public Student(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() { return name; }
}

class Attempt {
    private Student student;
    private Examination examination;
    private Map<String, String> answers = new HashMap<>();
    private boolean isSubmitted = false;

    public Attempt(Student student, Examination examination) {
        this.student = student;
        this.examination = examination;
        System.out.println(examination.getTitle() + " started by " + student.getName() + ".");
    }

    public void recordAnswer(Question q, String answer) {
        if (isSubmitted) {
            System.out.println("Cannot change answers for a submitted examination.");
            return;
        }
        answers.put(q.getId(), answer);
        System.out.println("Answer recorded for Question " + q.getId() + ".");
    }

    public void submit() {
        if (isSubmitted) {
            System.out.println("Examination already submitted.");
            return;
        }
        isSubmitted = true;
        System.out.println(examination.getTitle() + " submitted by " + student.getName() + ".");

        int totalScore = 0;
        int maxScore = 0;
        StringBuilder resultSummary = new StringBuilder("Result: ");

        List<Question> questions = examination.getQuestions();
        for (int i = 0; i < questions.size(); i++) {
            Question q = questions.get(i);
            String ans = answers.get(q.getId());
            int score = q.evaluate(ans);
            totalScore += score;
            maxScore += q.getMaxPoints();

            String statusStr = (score > 0) ? "Correct (" + score + " points)" : "Incorrect (" + score + " points)";
            resultSummary.append("Question ").append(q.getId()).append(": ").append(statusStr);
            if (i < questions.size() - 1) resultSummary.append(", ");
        }

        resultSummary.append(". Total score: ").append(totalScore).append("/").append(maxScore).append(".");
        System.out.println(resultSummary.toString());
    }
}

// --- Main ---
public class Main {
    public static void main(String[] args) {
        Examination examA = new Examination("EX101", "Exam A");
        Question q1 = new MultipleChoiceQuestion("1", "Select correct option", 5, "C");
        Question q2 = new TrueFalseQuestion("2", "Is Java OO?", 5, "True");
        examA.addQuestion(q1);
        examA.addQuestion(q2);

        Student student1 = new Student("S1", "Student 1");

        // 1. Student 1 starts Exam A
        Attempt attempt = new Attempt(student1, examA);

        // 2. Answers recorded
        attempt.recordAnswer(q1, "C");
        attempt.recordAnswer(q2, "False"); // Intentionally wrong to match sample output

        // 3. Submit Exam
        attempt.submit();

        // 4. Attempt to change answer after submission
        attempt.recordAnswer(q1, "A");
    }
}
