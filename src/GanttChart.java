import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class GanttChart {
    private ArrayList<GanttChartCell> cells;

    public GanttChart() {
        this.cells = new ArrayList<>();
    }

    public GanttChart(ArrayList<GanttChartCell> cells) {
        this.cells = cells;
    }

    public ArrayList<GanttChartCell> getCells() {
        return cells;
    }

    public void setCells(ArrayList<GanttChartCell> cells) {
        this.cells = cells;
    }

    public void addCell(GanttChartCell x) {
        cells.add(x);
    }

    private void removeDuplicates() {
        for (int i = 1; i < cells.size()-1; i++) {
            if (cells.get(i).getValue() == cells.get(i - 1).getValue()) {
                cells.remove(i);
            }
        }
    }

    private void fixDoubleProblem(){
        for (int i = 1; i < cells.size(); i++) {
            if (new DecimalFormat("###.###").format(cells.get(i).getBegin()).equals(new DecimalFormat("###.###").format(cells.get(i - 1).getBegin()))) {
                cells.remove(i-1);
            }
        }
    }

public void draw(HBox node, Scene scene, boolean drawDuplicates) {
    if (node == null || scene == null) {
        System.out.println("Scene or Node is null");
        return;
    }

    if (cells.isEmpty()) {
        return; // Return early if there are no cells to draw
    }

    double percentage = 0.9;
    double minWidth = 25;
    double totalTime = cells.get(cells.size() - 1).getBegin();
    fixDoubleProblem();
    if (!drawDuplicates) {
        removeDuplicates();
    }

    int max = cells.size() - 1;
    for (int i = 0; i <= max; i++) {
        if (cells.get(i) == null || cells.get(i).getValue() == null) {
            continue; // Skip null cells or cells with null values
        }

        VBox v = new VBox();
        Label PID = new Label(cells.get(i).getValue());
        Label time = new Label(new DecimalFormat("###.###").format(cells.get(i).getBegin()));
        PID.setPrefHeight(30);
        PID.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1, 0, 1, 1))));

        if (i < max) {
            v.setPrefWidth(percentage * scene.getWidth() * (cells.get(i + 1).getBegin() - cells.get(i).getBegin()) / totalTime);
        } else {
            v.setPrefWidth(minWidth); // Last cell or single cell
        }
        PID.setPrefWidth(v.getPrefWidth());
        time.setPrefWidth(v.getPrefWidth());

        v.getChildren().addAll(PID, time);
        node.getChildren().add(v);
    }
}


    public void draw(HBox node, Scene scene) {
        draw(node, scene, false);
    }

    public void drawWithDuplicates(HBox node, Scene scene) {
        draw(node, scene, true);
    }
}