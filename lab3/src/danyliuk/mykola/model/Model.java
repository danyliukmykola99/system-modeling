package danyliuk.mykola.model;

import java.util.Comparator;
import java.util.List;

/**
 * @author Mykola Danyliuk
 */
public class Model {

    private List<Element> elements;
    private Double nextTime, currentTime;
    private Element currentElement;

    public Model(List<Element> elements) {
        this.elements = elements;
        nextTime = 0.0;
        currentTime = 0.0;
        elements.forEach(e -> e.setCurrentTime(currentTime));
    }

    public void simulate(double time) {

        while (currentTime < time) {
            findClosestElement(); // визначення найближчої події
            elements.forEach(e -> e.doStatistics(nextTime - currentTime)); // просування часу

            currentTime = nextTime;
            elements.forEach(e -> e.setCurrentTime(currentTime));

            // здійснення відповідної події
            currentElement.outAct();

            // здійснення відповідної події для всіх елементів, час наступної події яких співпадає з поточним моментом часу.
            elements.stream().filter(e -> e.getTimeNext() == currentTime && e.getTimeNext() != 0.0)
                    .forEachOrdered(Element::outAct);

            // виведення результату після кожної прогонки
            printInfo();
        }
        printResult();
    }

    private void findClosestElement(){
        currentElement = elements.stream().min(Comparator.comparing(Element::getTimeNext)).get();
        nextTime = currentElement.getTimeNext();
        System.out.printf("%n%3.3f Next element: %s Next time: %3.3f%n",
                currentTime, currentElement.getName(), nextTime);
    }

    public void printInfo() {
        elements.forEach(Element::printInfo);
    }

    public void printResult() {
        System.out.println("\n-------------RESULTS-------------");
        elements.forEach(Element::printResult);
    }
}

