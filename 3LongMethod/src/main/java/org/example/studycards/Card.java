package org.example.studycards;

public class Card {
    private String question;
    private String answer;

    public Card(String question, String answer) {
        setQuestion(question);
        setAnswer(answer);
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        if (question == null || question.trim().isEmpty()) {
            throw new IllegalArgumentException("A pergunta não pode ser nula ou vazia.");
        }
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        if (answer == null || answer.trim().isEmpty()) {
            throw new IllegalArgumentException("A resposta não pode ser nula ou vazia.");
        }
        this.answer = answer;
    }

    public void edit(String newQuestion, String newAnswer) {
        setQuestion(newQuestion);
        setAnswer(newAnswer);
    }

    /**
     * Verifica se a resposta do usuário está correta (ignorando maiúsculas e espaços).
     */
    public boolean isCorrectAnswer(String userAnswer) {
        return answer.equalsIgnoreCase(userAnswer.trim());
    }

    /**
     * Retorna a pergunta formatada.
     */
    public String displayQuestion() {
        return "Q: " + question;
    }

    /**
     * Retorna a resposta formatada.
     */
    public String displayAnswer() {
        return "A: " + answer;
    }
}

