
package ie.atu.sw;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jhealy.aicme4j.NetworkBuilderFactory;
import jhealy.aicme4j.net.Activation;
import jhealy.aicme4j.net.Loss;
import jhealy.aicme4j.net.Output;

public class train {
    // Modify method to return the read data
   
	 // Method to read data from a CSV file
    public List<double[]> readCSV(String filename) {
        List<double[]> dataList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                double[] dataRow = new double[values.length];
                for (int i = 0; i < values.length; i++) {
                    dataRow[i] = Double.parseDouble(values[i].trim());
                }
                dataList.add(dataRow);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("List >> "+dataList);
        return dataList;
    }

    public void go() throws Exception {
    	
    	// Read data and expected output from CSV files
        List<double[]> dataList = readCSV("data.csv");
        List<double[]> expectedList = readCSV("expected.csv");

        // Convert lists to arrays
        double[][] data = dataList.toArray(new double[0][]);
        double[][] expected = expectedList.toArray(new double[0][]);


        System.out.println("TESER");
       
            var net = NetworkBuilderFactory.getInstance().newNetworkBuilder()
                    .inputLayer("Input", 3)
                    .hiddenLayer("Hidden1", Activation.TANH, 6)
                    .outputLayer("Output", Activation.TANH, 1)
                    .train(data, expected, 0.001, 0.95, 100000, 0.00001, Loss.SSE)
                    .save("./planeNN.data")
                    .build();
            System.out.println(net);
    }

    public static void main() throws Exception {
        new train().go();
        System.out.println("Here girlie");
    }
}
