package Inlämningsupgift;

import jdk.swing.interop.SwingInterOpUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;



/*
Inlämningsuppgit i kursen Funktionell Programmering för JAVA-programmet

För samtliga funktioner i denna fil, byt ut "throw UnSupportedException"-raden
och skriv ett pipeline-uttryck som returnerar det som ska returneras.

Streams MÅSTE användas i samtliga funktioner som lämnas in
För att testa om era funktioner funkar, kör testerna som hör till denna fil

För att bli godkänd på denna uppgift måste minst 7 av funktionerna vara implementerade.

Sigruns bedömning av denna uppgift kommer att gå till så att hon klipper in er version av denna fil
i sitt projekt och kör testerna.
Hennes kontroll om ni har klarat uppgifterna eller inte görs genom att
hon kollar att tillräckeligt många av tester går gröna. Pga detta ska ni inte ändra i någon fil
medföljande detta projekt, och inte heller metodsignaturerna i denna fil eller era tester, eftersom
ni då riskerar att påverka rättningen i negativ riktning.
 */

public class RewriteMe {

    public Database database = new Database();
    public List<Question> questions = database.getQuestions();


    //Skriv en funktioner som returnerar hur många frågor det finns i databasen?
    public int getAmountOfQuestionsInDatabase() {
        return questions.stream().collect(toList()).size();

    }

    //Hur många frågor finns i databasen för en viss, given kategori (som ges som inparameter)
    public int getAmountOfQuestionsForACertainCategory(Category category) {
        return questions.stream().filter(s -> s.getCategory().equals(category)).
                collect(toList()).size();

    }

    //Skapa en lista innehållandes samtliga frågesträngar i databasen
    public List<String> getListOfAllQuestions() {
        return questions.stream().map(q -> q.getQuestionString()).
                collect(toList());

    }

    //Skapa en lista innehållandes samtliga frågesträngar där frågan tillhör en viss kategori
    //Kategorin ges som inparameter
    public List<String> getAllQuestionStringsBelongingACategory(Category category) {
        return questions.stream().filter(s -> s.getCategory().equals(category)).
                map(Question::getQuestionString).collect(toList());

    }

    //Skapa en lista av alla svarsalternativ, där varje svarsalternativ får förekomma
    // en och endast en gång i den lista som du ska returnera
    public List<String> getAllAnswerOptionsDistinct() {
        return questions.stream().flatMap(a -> a.getAllAnswers().stream()).
                distinct().collect(toList());

    }

    //Finns en viss sträng, given som inparameter, som svarsalternativ till någon fråga i vår databas?
    public boolean isThisAnAnswerOption(String answerCandidate) {
        return questions.stream().anyMatch(a -> a.getAllAnswers().
                contains(answerCandidate));

    }

    //Hur ofta förekommer ett visst svarsalternativ, givet som inparameter, i databasen
    public int getAnswerCandidateFrequncy(String answerCandidate) {
        return (int) questions.stream().filter(q -> q.getAllAnswers().
                contains(answerCandidate)).count();

    }

    //Skapa en Map där kategorierna är nycklar och värdena är en lista
    //av de frågesträngar som tillhör varje kategori
    public Map<Category, List<String>> getQuestionGroupedByCategory() {
        return questions.stream().collect(groupingBy(q -> q.getCategory(),
                mapping(Question::getQuestionString, toList())));

    }


    //Skapa en funktion som hittar det svarsalternativ som har flest bokstäver, i en kategori, given som inparameter
    // OBS: Du måste använda Reduce!
    public String getLongestLettercountAnwerInAGivenCategory(Category c) {
        return questions.stream().filter(q -> q.getCategory().equals(c)).
                flatMap(q -> q.getAllAnswers().stream()).distinct().
                reduce("", (u, e) -> u.length() > e.length() ? u : e);

    }


/*
    public RewriteMe(){

        System.out.println(getAmountOfQuestionsInDatabase());
        System.out.println();

        System.out.println(getAmountOfQuestionsForACertainCategory(Category.CHEMISTRY));
        System.out.println(getAmountOfQuestionsForACertainCategory(Category.HISTORY));
        System.out.println(getAmountOfQuestionsForACertainCategory(Category.FOOD));
        System.out.println();

        getListOfAllQuestions().forEach(q -> System.out.println(q));
        System.out.println();

        getAllQuestionStringsBelongingACategory(Category.CHEMISTRY).forEach(q -> System.out.println(q));
        getAllQuestionStringsBelongingACategory(Category.HISTORY).forEach(q -> System.out.println(q));
        getAllQuestionStringsBelongingACategory(Category.FOOD).forEach(q -> System.out.println(q));
        System.out.println();

        getAllAnswerOptionsDistinct().forEach(q -> System.out.println(q));
        System.out.println();

        System.out.println(isThisAnAnswerOption("Couscous"));
        System.out.println(isThisAnAnswerOption("sfdsfsfs"));
        System.out.println();

        System.out.println(getAnswerCandidateFrequncy("Couscous"));
        System.out.println();

        getQuestionGroupedByCategory().forEach((k,v) -> System.out.println(k +" "+v));
        System.out.println();

        System.out.println(getLongestLettercountAnwerInAGivenCategory(Category.FOOD));
        System.out.println(getLongestLettercountAnwerInAGivenCategory(Category.CHEMISTRY));
        System.out.println(getLongestLettercountAnwerInAGivenCategory(Category.HISTORY));

    }
*/


    public static void main(String[] args) {
        RewriteMe uppg4 = new RewriteMe();

    }

}
