import java.util.Scanner;

class GenericMax {

    // Generic function to find maximum element in an array
    public static <T extends Comparable<T>> T maxElement(T[] arr) {
        T max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i].compareTo(max) > 0) {
                max = arr[i];
            }
        }
        return max;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of students: ");
        int n = sc.nextInt();
        sc.nextLine(); // consume newline

        String[] names = new String[n];
        Float[] cgpa = new Float[n];
        Integer[] total = new Integer[n];

        // --- Getting input ---
        for (int i = 0; i < n; i++) {
            System.out.println("\nEnter details for student " + (i + 1) + ":");
            System.out.print("Name: ");
            names[i] = sc.nextLine();

            System.out.print("Total Marks: ");
            total[i] = sc.nextInt();

            System.out.print("CGPA: ");
            cgpa[i] = sc.nextFloat();
            sc.nextLine(); // consume newline
        }

        // --- Find highest total ---
        Integer maxTotal = maxElement(total);
        int maxTotalIndex = 0;
        for (int i = 0; i < n; i++) {
            if (total[i].equals(maxTotal)) {
                maxTotalIndex = i;
                break;
            }
        }

        // --- Find highest CGPA ---
        Float maxCGPA = maxElement(cgpa);
        int maxCGPAIndex = 0;
        for (int i = 0; i < n; i++) {
            if (cgpa[i].equals(maxCGPA)) {
                maxCGPAIndex = i;
                break;
            }
        }

        // --- Find name that comes last alphabetically ---
        String lastName = maxElement(names);

        // --- Display results ---
        System.out.println("\n===== RESULTS =====");
        System.out.println("Highest Total: " + maxTotal + " (Student: " + names[maxTotalIndex] + ")");
        System.out.println("Highest CGPA: " + maxCGPA + " (Student: " + names[maxCGPAIndex] + ")");
        System.out.println("Name comes last alphabetically: " + lastName);

        sc.close();
    }
}
