
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import java.time.LocalDate;

import control.DatelineGraphControl;
import control.GanttChartHbox;
import control.GanttTableControl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.BorderPane;
import model.GanttTask;

import control.DatelineGraphResourceControl;
import control.GanttResourceControl;
import model.GanttResource;

public class ResourceChart extends Application {
    private BorderPane ganttChartWithMenu;
    private SplitPane ganttChart;
    private GanttTableControl<GanttTask> ganttTaskControl;
    private DatelineGraphControl<GanttTask> timelineGraphControl;
    private GanttChartHbox<GanttTask> menuChartHbox;

    private BorderPane graphicView;
    private SplitPane viewGantt;
    private GanttTableControl ganttTableView;
    private DatelineGraphControl timelineWithGraphicView;
    private GanttChartHbox menu;

    public ResourceChart() {

        ObservableList<GanttResource> resources = addGanttResources();
        ganttTableView = new GanttResourceControl();
        ganttTableView.setItems(resources);
        ganttTableView.generate();

        timelineWithGraphicView = new DatelineGraphResourceControl().init(13, true);
        timelineWithGraphicView.setGanttPiece(resources);

        menu = new GanttChartHbox().init(timelineWithGraphicView.getStartDay(), timelineWithGraphicView.getEndDay(),
                timelineWithGraphicView);
        graphicView = new BorderPane();

        graphicView.setTop(menu);
        graphicView.setCenter(timelineWithGraphicView);

        viewGantt = new SplitPane(ganttTableView, graphicView);
    }

    private ObservableList<GanttResource> addGanttResources() {
        ObservableList<GanttResource> resources = FXCollections
                .<GanttResource>observableArrayList();
        GanttResource resource1 = new GanttResource(0, "Machine A", LocalDate.of(2023, 02, 8),
                LocalDate.of(2023, 02, 15),
                "mix the chocolate");
        GanttResource resource2 = new GanttResource(1, "Machine B", LocalDate.of(2023, 02, 8),
                LocalDate.of(2023, 02, 9),
                "give a shape to fir trees");
        GanttResource resource3 = new GanttResource(2, "Machine C", LocalDate.of(2023, 02, 10),
                LocalDate.of(2023, 02, 15),
                "sorting chocolate");
        GanttResource resource4 = new GanttResource(3, "Machine D", LocalDate.of(2023, 02, 12),
                LocalDate.of(2023, 02, 17),
                "pack the chocolates");
        resources.addAll(resource1, resource2, resource3, resource4);
        return resources;
    }

    public BorderPane getGraphicView() {
        return graphicView;
    }

    public void setGraphicView(BorderPane view) {
        this.graphicView = view;
    }

    public SplitPane getViewGantt() {
        return viewGantt;
    }

    public void setViewGantt(SplitPane viewG) {
        this.viewGantt = viewGantt;
    }

    public void start(Stage primaryStage) {

        try {
            // Scene scene = new Scene(resourceChart.getResourceChartWithMenu());
            Scene scene = new Scene(getViewGantt());
            scene.getStylesheets().add(getClass().getResource("/css/gantt.css").toExternalForm());
            primaryStage.setTitle("Resourcechart");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}