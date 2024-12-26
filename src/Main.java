import java.io.PrintStream;
import java.util.Scanner;
public class Main {
    public static Scanner in = new Scanner(System.in);
    public static PrintStream out = System.out;

    public static void main(String[] args) {
        int n = in.nextInt(); //количество строк
        int m = in.nextInt(); //количество столбцов
        double [][][] arr = new double[n][m][3]; //в каждой ячейке массива n*m хранится сначала температура, потом координаты (x, y)
        double [] ave = new double[n]; // массив средних температур
        double [][] max = new double[n][3]; //массив максимальных температур в строках и их координат

        //ввод массива с клавиатуры
        for (int i = 0; i < arr.length; i++)
            for (int j = 0; j < arr[i].length; j++)
                for (int k = 0; k < 3; k++)
                    arr[i][j][k] = in.nextDouble();

        //подсчет средней температуры в строках
        for (int i = 0; i < arr.length; i++) {
            double sum = 0;
            for (int j = 0; j < arr[i].length; j++) {
                sum += arr[i][j][0];
            }
            ave[i] = sum / arr[i].length;
        }

        //нахождение максимума в каждой строке
        for (int i = 0; i < arr.length; i++) {
            for (int k = 0; k < 3; k++)
                max[i][k] = arr[i][0][k];
            for (int j = 1; j < arr[i].length; j++) {
                if (max[i][0] < arr[i][j][0]){
                    for (int k = 0; k < 3; k++)
                        max[i][k] = arr[i][j][k];
                }
            }
        }

        //перестановка строк в порядке возрастания средней температуры
        for (int i = 0; i < arr.length - 1; i++)
            for (int j = 0; j < arr[i].length - i; j++) {
                if (ave[j] > ave[j + 1] || (ave[j] == ave[j + 1] && max[j][0] > max[j + 1][0])) {
                    double[][] swap = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = swap;
                    double temp = ave[j];
                    ave[j] = ave[j + 1];
                    ave[j + 1] = temp;
                    double [] tempmax = max[j];
                    max[j] = max[j + 1];
                    max[j + 1] = tempmax;
                }
            }

        for (int i = 0; i < ave.length; i++)
            out.print(ave[i] + " ");
        for (int i = 0; i < max.length; i++)
            out.print(max[i][0] + " ");
        for (int i = 0; i < arr.length; i++)
            for (int j = 0; j < arr[i].length; j++)
                out.print(arr[i][j][0] + " ");



        //поиск и вывод максимальной температуры
        double maxt = max[0][0];
        double maxx = max[0][1];
        double maxy = max[0][2];

        for (int i = 1; i < max.length; i++) {
            if (max[i][0] > maxt){
                maxt = max[i][0];
                maxx = max[i][1];
                maxy = max[i][2];
            }
        }

        out.print("Максимальная температура: " + maxt);
        out.print("\nКоординаты точки, в которой она зафиксирована: (" + maxx + ", " + maxy + ")");

        //поиск минимальной температуры и вывод разницы между ней и максимальной температурой

        double min = arr[0][0][0];
        for (int i = 0; i < arr.length; i++)
            for (int j = 0; j < arr[i].length; j++){
                if (arr[i][j][0] < min)
                    min = arr[i][j][0];
            }

        out.print("\nРазница между максимальной и минимальной температурами: " + (maxt - min));

        //вывод тепловой карты

        out.println();
        out.println("Тепловая карта (отрицательные температуры обозначены *, неотрицательные - !): ");
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j][0] < 0)
                    out.print(" * ");
                else
                    out.print(" ! ");
            }
            out.println();
        }

    }
}