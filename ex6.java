import java.io.*;
import java.util.*;

public class StudentRecordManagement {

    static final String FILE_NAME = "students.txt";

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\n--- STUDENT RECORD MANAGEMENT SYSTEM ---");
            System.out.println("1. Create / Overwrite Records");
            System.out.println("2. Display All Records");
            System.out.println("3. Append New Record");
            System.out.println("4. Update Record");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1 -> createRecords(sc);
                case 2 -> displayRecords();
                case 3 -> appendRecord(sc);
                case 4 -> updateRecord(sc);
                case 5 -> System.out.println("Exiting...");
                default -> System.out.println("Invalid choice!");
            }
        } while (choice != 5);
        sc.close();
    }

    // Create new records (overwrite existing file)
    public static void createRecords(Scanner sc) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            System.out.print("Enter number of students: ");
            int n = sc.nextInt();
            sc.nextLine();
            for (int i = 0; i < n; i++) {
                System.out.println("Enter details for student " + (i + 1));
                System.out.print("ID: ");
                String id = sc.nextLine();
                System.out.print("Name: ");
                String name = sc.nextLine();
                System.out.print("Department: ");
                String dept = sc.nextLine();
                System.out.print("Marks: ");
                String marks = sc.nextLine();
                bw.write(id + "," + name + "," + dept + "," + marks);
                bw.newLine();
            }
        }
        System.out.println("Records created successfully.");
    }

    // Display all records
    public static void displayRecords() throws IOException {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            System.out.println("No records found!");
            return;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            System.out.println("\n--- Student Records ---");
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                System.out.printf("ID: %s | Name: %s | Dept: %s | Marks: %s\n",
                        data[0], data[1], data[2], data[3]);
            }
        }
    }

    // Append new record
    public static void appendRecord(Scanner sc) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            System.out.println("Enter details to append:");
            System.out.print("ID: ");
            String id = sc.nextLine();
            System.out.print("Name: ");
            String name = sc.nextLine();
            System.out.print("Department: ");
            String dept = sc.nextLine();
            System.out.print("Marks: ");
            String marks = sc.nextLine();
            bw.write(id + "," + name + "," + dept + "," + marks);
            bw.newLine();
        }
        System.out.println("Record appended successfully.");
    }

    // Update an existing record
    public static void updateRecord(Scanner sc) throws IOException {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            System.out.println("No records found!");
            return;
        }

        List<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null)
                lines.add(line);
        }

        System.out.print("Enter ID to update: ");
        String targetId = sc.nextLine();
        boolean found = false;

        for (int i = 0; i < lines.size(); i++) {
            String[] data = lines.get(i).split(",");
            if (data[0].equals(targetId)) {
                found = true;
                System.out.println("Enter new details:");
                System.out.print("Name: ");
                String name = sc.nextLine();
                System.out.print("Department: ");
                String dept = sc.nextLine();
                System.out.print("Marks: ");
                String marks = sc.nextLine();
                lines.set(i, targetId + "," + name + "," + dept + "," + marks);
                break;
            }
        }

        if (found) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
                for (String l : lines) {
                    bw.write(l);
                    bw.newLine();
                }
            }
            System.out.println("Record updated successfully.");
        } else {
            System.out.println("Record not found!");
        }
    }
}
