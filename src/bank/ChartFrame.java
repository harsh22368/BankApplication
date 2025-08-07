package bank;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;

public class ChartFrame extends JFrame {

    public ChartFrame(String username) {
        setTitle("Transaction Chart - " + username);
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        // Dummy data - Replace this with real data from file or database
        dataset.addValue(5000, "Deposit", "Jan");
        dataset.addValue(2000, "Withdraw", "Jan");
        dataset.addValue(3000, "Deposit", "Feb");
        dataset.addValue(1000, "Withdraw", "Feb");
        dataset.addValue(4000, "Deposit", "Mar");
        dataset.addValue(1500, "Withdraw", "Mar");

        JFreeChart barChart = ChartFactory.createBarChart(
                "Transaction History",
                "Month",
                "Amount",
                dataset
        );

        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new Dimension(580, 360));
        setContentPane(chartPanel);

        setVisible(true);
    }
}
