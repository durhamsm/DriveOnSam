package Objects;

import User_Interface.Screens.Quiz.Question;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class QuizDatabaseRead {

    public static List<Question> getAllQuestions() {
        List<Question> allQuestions = new ArrayList<>();


        NodeList nList = getQuestionNodes();

        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);

            String questionString = ((Element) nNode).getElementsByTagName("qText").item(0).getTextContent();
            int answerIndex = Integer.valueOf(((Element) nNode).getElementsByTagName("correctAnswer").item(0).getTextContent()) - 1;
            String imagePath = ((Element) nNode).getElementsByTagName("qImg").item(0).getTextContent();

            allQuestions.add(new Question(questionString, imagePath, getOptionStrings(nNode), answerIndex));

        }

        return allQuestions;

    }

    private static NodeList getQuestionNodes() {

        NodeList nList= null;

        try {
            File fXmlFile = new File("traffic-quiz.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();

            nList = doc.getElementsByTagName("question");

        } catch (Exception e) {
            e.printStackTrace();
        }

        return nList;
    }

    private static List<String> getOptionStrings(Node questionNode) {
        List<String> optionStrings = new ArrayList<>();

        NodeList optionList = ((Element) questionNode).getElementsByTagName("option");

        for (int temp = 0; temp < optionList.getLength(); temp++) {
            optionStrings.add(optionList.item(temp).getTextContent());
        }

        return optionStrings;
    }

}
