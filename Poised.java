package com.yazeed;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.stream.StreamSupport;

/**
 * Contains the main method and other methods to manipulate data
 */
public class Poised {

    // Scanner object to take user input
    static Scanner input = new Scanner(System.in);

    // Information needed to connect to database
    static String url = "jdbc:mysql://localhost:3306/poisepms";
    static String username = "otheruser";
    static String password = "swordfish";

    static String dashes = "-------------------------------------------------------------";
    static String stars = "**************************************************************";

    public static void main(String[] args) {

        System.out.println("\n\n===== POISED =====\n");

        boolean running = true;
        while (running) {

            //reset();

            // Allow user to select option to create, edit, finalise or view tasks
            System.out.println("Select an option (number):");
            System.out.println("1. Create new project.");
            System.out.println("2. Edit existing project.");
            System.out.println("3. Finalise project.");
            System.out.println("4. View all projects.");
            System.out.println("5. View overdue projects.");
            System.out.println("0. Exit");
            System.out.println();

            while (true) {
                try {
                    System.out.print("> ");
                    int command = Integer.parseInt(input.nextLine());

                    if (command == 1) {
                        System.out.println();
                        System.out.println(dashes);
                        System.out.println();
                        createNewProject();
                        break;
                    }
                    else if (command == 2) {
                        System.out.println();
                        System.out.println(dashes);
                        System.out.println();
                        editProject();
                        break;
                    }
                    else if (command == 3) {
                        System.out.println();
                        System.out.println(dashes);
                        System.out.println();
                        finaliseProject();
                        break;
                    }
                    else if (command == 4) {
                        System.out.println();
                        System.out.println(dashes);
                        System.out.println();
                        viewAll();
                        break;
                    }
                    else if (command == 5) {
                        System.out.println();
                        System.out.println(dashes);
                        System.out.println();
                        viewOverDue();
                        break;
                    }
                    else if (command == 0) {
                        System.out.println();
                        System.out.println("Bye!");
                        System.out.println();
                        System.out.println(dashes);
                        System.out.println();
                        running = false;
                        break;
                    }
                    else {
                        System.out.println();
                        System.out.println(">>> Please enter valid option.");
                        System.out.println();
                    }

                }
                catch (Exception e) {
                    System.out.println();
                    System.out.println(">>> Please enter valid option");
                    System.out.println();
                }
            }
        }
    }

    /**
     * Method to create new project
     */
    // Method to create new project
    public static void createNewProject() {

        // Attributes for each Project object
        int projectNumber = 0;
        String projectName;
        String buildingType;
        String address;
        int ERFNumber = 0;
        float totalFee = 0;
        float totalPaid = 0;
        String deadline;

        // Attributes for each manager
        int managerId = 0;
        String managerName;
        String managerTelNum;
        String managerEmail;
        String managerAddress;

        // Attributes for each customer
        int customerId = 0;
        String customerName;
        String customerTelNum;
        String customerEmail;
        String customerAddress;

        // Attributes for each architect
        int architectId = 0;
        String architectName;
        String architectTelNum;
        String architectEmail;
        String architectAddress;

        // Attributes for each contractor
        int contractorId = 0;
        String contractorName;
        String contractorTelNum;
        String contractorEmail;
        String contractorAddress;

        // Request project number
        // Use while loop and try-catch block to validate input
        boolean validProjectNumber = false;
        while (!validProjectNumber) {
            try {
                System.out.print("Project number: ");
                projectNumber = Integer.parseInt(input.nextLine());

                // Connect to database and check if project number already exists
                try {
                    Connection connection = DriverManager.getConnection(url, username, password);
                    Statement statement = connection.createStatement();
                    ResultSet results;

                    results = statement.executeQuery("SELECT proj_num FROM project_details");

                    // Loop through project numbers and store each project number in 'projectNumberList'
                    ArrayList<Integer> projectNumberList = new ArrayList<>();
                        while (results.next()) {
                            projectNumberList.add(results.getInt("proj_num"));
                        }

                        if (projectNumberList.contains(projectNumber)) {
                            System.out.println();
                            System.out.println(">>> Project number " + projectNumber + " already exists.");
                            System.out.println();
                        }
                        else {
                            projectNumberList.clear();
                            System.out.println();
                            validProjectNumber = true;
                        }

                    // Close connection to database
                    connection.close();
                    statement.close();
                    results.close();

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            catch (Exception e) {
                System.out.println();
                System.out.println(">>> Please enter valid project number.");
                System.out.println();
            }
        }

        // Request project name
        System.out.print("Project name: ");
        projectName = input.nextLine();
        System.out.println();

        // Request building type
        System.out.print("Building type: ");
        buildingType = input.nextLine();
        System.out.println();

        // Request address
        System.out.print("Address: ");
        address = input.nextLine();
        System.out.println();

        // Request ERF number
        // Use while loop and try-catch block to validate input
        boolean validERFNumber = false;
        while (!validERFNumber) {
            try {
                System.out.print("ERF Number: ");
                ERFNumber = Integer.parseInt(input.nextLine());
                System.out.println();
                validERFNumber = true;
            }
            catch (Exception e) {
                System.out.println();
                System.out.println(">>> Please enter valid ERF Number.");
                System.out.println();
            }
        }

        // Request total fee
        // Use while loop and try-catch block to validate input
        boolean validTotalFee = false;
        while (!validTotalFee) {
            try {
                System.out.print("Total fee: ");
                totalFee = Float.parseFloat(input.nextLine());
                System.out.println();
                validTotalFee = true;
            }
            catch (Exception e) {
                System.out.println();
                System.out.println(">>> Please enter valid total fee.");
                System.out.println();
            }
        }

        // Request total amount paid
        // Use while loop and try-catch block to validate input
        boolean validTotalPaid = false;
        while (!validTotalPaid) {
            try {
                System.out.print("Total amount paid: ");
                totalPaid = Float.parseFloat(input.nextLine());
                System.out.println();
                validTotalPaid = true;
            }
            catch (Exception e) {
                System.out.println();
                System.out.println(">>> Please enter valid total amount paid.");
                System.out.println();
            }
        }

        // Request deadline
        System.out.print("Deadline (YYYY-MM-DD): ");
        deadline = input.nextLine();
        System.out.println();

        // Request manager id number
        // Use while loop and try-catch to validate input
        boolean validManagerId = false;
        while (!validManagerId) {
            try {
                System.out.print("Manager id number: ");
                managerId = Integer.parseInt(input.nextLine());

                // Connect to database and check manager id already exists
                try {
                    Connection connection = DriverManager.getConnection(url, username, password);
                    Statement statement = connection.createStatement();
                    ResultSet results;

                    results = statement.executeQuery("SELECT manager_id FROM manager_details");

                    // Loop through all manager id numbers and store each id number in 'managerIdList'
                    ArrayList<Integer> managerIdList = new ArrayList<>();

                    while (results.next()) {
                        managerIdList.add(results.getInt("manager_id"));
                    }

                    if (managerIdList.contains(managerId)) {
                        System.out.println();
                        System.out.println(">>> A manager with id number " + managerId + " already exists.");
                        System.out.println();
                    }
                    else {
                        managerIdList.clear();
                        System.out.println();
                        validManagerId = true;
                    }

                    // Close connections to database
                    connection.close();
                    statement.close();
                    results.close();

                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            catch (Exception e) {
                System.out.println();
                System.out.println(">>> Please enter valid manager id number.");
                System.out.println();
            }
        }

        // Request manager name
        System.out.print("Manager name: ");
        managerName = input.nextLine();
        System.out.println();

        // Request manager telephone number
        System.out.print("Manager telephone number: ");
        managerTelNum = input.nextLine();
        System.out.println();

        // Request manager email
        System.out.print("Manager email address: ");
        managerEmail = input.nextLine();
        System.out.println();

        // Request manager address
        System.out.print("Manager address: ");
        managerAddress = input.nextLine();
        System.out.println();

        // Request customer id number
        // Use while loop and try-catch to validate input
        boolean validCustomerId = false;
        while (!validCustomerId) {
            try {
                System.out.print("Customer id number: ");
                customerId = Integer.parseInt(input.nextLine());

                // Connect to database and check customer id already exists
                try {
                    Connection connection = DriverManager.getConnection(url, username, password);
                    Statement statement = connection.createStatement();
                    ResultSet results;

                    results = statement.executeQuery("SELECT customer_id FROM customer_details");

                    // Loop through all customer id numbers and store each number in 'customerIdList'
                    ArrayList<Integer> customerIdList = new ArrayList<>();

                    while (results.next()) {
                        customerIdList.add(results.getInt("customer_id"));
                    }

                    if (customerIdList.contains(customerId)) {
                        System.out.println();
                        System.out.println(">>> A customer with id number " + customerId + " already exists.");
                        System.out.println();
                    }
                    else {
                        customerIdList.clear();
                        System.out.println();
                        validCustomerId = true;
                    }

                    // Close connections to database
                    connection.close();
                    statement.close();
                    results.close();

                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            catch (Exception e) {
                System.out.println();
                System.out.println(">>> Please enter valid customer id number.");
                System.out.println();
            }
        }

        // Request customer name
        System.out.print("Customer name: ");
        customerName = input.nextLine();
        System.out.println();

        // Request customer telephone number
        System.out.print("Customer telephone number: ");
        customerTelNum = input.nextLine();
        System.out.println();

        // Request customer email
        System.out.print("Customer email address: ");
        customerEmail = input.nextLine();
        System.out.println();

        // Request customer address
        System.out.print("Customer address: ");
        customerAddress = input.nextLine();
        System.out.println();

        // Request architect id number
        // Use while loop and try-catch to validate input
        boolean validArchitectId = false;
        while (!validArchitectId) {
            try {
                System.out.print("Architect id number: ");
                architectId = Integer.parseInt(input.nextLine());

                // Connect to database and check architect id already exists
                try {
                    Connection connection = DriverManager.getConnection(url, username, password);
                    Statement statement = connection.createStatement();
                    ResultSet results;

                    results = statement.executeQuery("SELECT architect_id FROM architect_details");

                    // Loop through all architect id numbers and store each number in 'architectIdList'
                    ArrayList<Integer> architectIdList = new ArrayList<>();

                    while (results.next()) {
                        architectIdList.add(results.getInt("architect_id"));
                    }

                    if (architectIdList.contains(architectId)) {
                        System.out.println();
                        System.out.println(">>> An architect with id number " + architectId + " already exists.");
                        System.out.println();
                    }
                    else {
                        architectIdList.clear();
                        System.out.println();
                        validArchitectId = true;
                    }

                    // Close connections to database
                    connection.close();
                    statement.close();
                    results.close();

                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            catch (Exception e) {
                System.out.println();
                System.out.println(">>> Please enter valid architect id number.");
                System.out.println();
            }
        }

        // Request architect name
        System.out.print("Architect name: ");
        architectName = input.nextLine();
        System.out.println();

        // Request architect telephone number
        System.out.print("Architect telephone number: ");
        architectTelNum = input.nextLine();
        System.out.println();

        // Request architect email
        System.out.print("Architect email address: ");
        architectEmail = input.nextLine();
        System.out.println();

        // Request architect address
        System.out.print("Architect address: ");
        architectAddress = input.nextLine();
        System.out.println();

        // Request contractor id number
        // Use while loop and try-catch to validate input
        boolean validContractorId = false;
        while (!validContractorId) {
            try {
                System.out.print("Contractor id number: ");
                contractorId = Integer.parseInt(input.nextLine());

                // Connect to database and check contractor id already exists
                try {
                    Connection connection = DriverManager.getConnection(url, username, password);
                    Statement statement = connection.createStatement();
                    ResultSet results;

                    results = statement.executeQuery("SELECT contractor_id FROM contractor_details");

                    // Loop through all contractor id numbers and store each number in 'contractorIdList'
                    ArrayList<Integer> contractorIdList = new ArrayList<>();

                    while (results.next()) {
                        contractorIdList.add(results.getInt("contractor_id"));
                    }

                    if (contractorIdList.contains(contractorId)) {
                        System.out.println();
                        System.out.println(">>> A contractor with id number " + contractorId + " already exists.");
                        System.out.println();
                    }
                    else {
                        contractorIdList.clear();
                        System.out.println();
                        validContractorId = true;
                    }

                    // Close connections to database
                    connection.close();
                    statement.close();
                    results.close();

                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            catch (Exception e) {
                System.out.println();
                System.out.println(">>> Please enter valid contractor id number.");
                System.out.println();
            }
        }

        // Request contractor name
        System.out.print("Contractor name: ");
        contractorName = input.nextLine();
        System.out.println();

        // Request contractor telephone number
        System.out.print("Contractor telephone number: ");
        contractorTelNum = input.nextLine();
        System.out.println();

        // Request contractor email
        System.out.print("Contractor email address: ");
        contractorEmail = input.nextLine();
        System.out.println();

        // Request contractor address
        System.out.print("Contractor address: ");
        contractorAddress = input.nextLine();
        System.out.println();

        // Create temporary Project object
        // Create temporary manager object
        // Create temporary customer object
        // Create temporary architect object
        // Create temporary contractor object
        Project tempProject = new Project(projectNumber, projectName, buildingType, address, ERFNumber, totalFee, totalPaid, deadline, managerId, customerId, architectId, contractorId);
        Person tempManager = new Person(managerId, managerName, managerTelNum, managerEmail, managerAddress);
        Person tempCustomer = new Person(customerId, customerName, customerTelNum, customerEmail, customerAddress);
        Person tempArchitect = new Person(architectId, architectName, architectTelNum, architectEmail, architectAddress);
        Person tempContractor = new Person(contractorId, contractorName, contractorTelNum, contractorEmail, contractorAddress);

        // Connect to database and enter relevant details into each table
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();

            statement.executeUpdate("INSERT INTO project_details VALUES ("
                    + tempProject.getProjectNumber() + ", "
                    + "'" + tempProject.getProjectName() + "'" + ", "
                    + "'" + tempProject.getBuildingType() + "'" + ", "
                    + "'" + tempProject.getAddress() + "'" + ", "
                    + tempProject.getERFNumber() + ", "
                    + tempProject.getTotalFee() + ", "
                    + tempProject.getTotalPaid() + ", "
                    + "'" + tempProject.getDeadline() + "'" + ", "
                    + tempProject.getManagerId() + ", "
                    + tempProject.getCustomerId() + ", "
                    + tempProject.getArchitectId() + ", "
                    + tempProject.getContractorId() + ")");

            statement.executeUpdate("INSERT INTO manager_details VALUES ("
                    + tempManager.getIdNumber() + ", "
                    + "'" + tempManager.getName() + "'" + ", "
                    + "'" + tempManager.getTel_num() + "'" + ", "
                    + "'" + tempManager.getEmail() + "'" +  ", "
                    + "'" + tempManager.getAddress() + "'" + ")");

            statement.executeUpdate("INSERT INTO customer_details VALUES ("
                    + tempCustomer.getIdNumber() + ", "
                    + "'" + tempCustomer.getName() + "'" + ", "
                    + "'" + tempCustomer.getTel_num() + "'" + ", "
                    + "'" + tempCustomer.getEmail() + "'" +  ", "
                    + "'" + tempCustomer.getAddress() + "'" + ")");

            statement.executeUpdate("INSERT INTO architect_details VALUES ("
                    + tempArchitect.getIdNumber() + ", "
                    + "'" + tempArchitect.getName() + "'" + ", "
                    + "'" + tempArchitect.getTel_num() + "'" + ", "
                    + "'" + tempArchitect.getEmail() + "'" +  ", "
                    + "'" + tempArchitect.getAddress() + "'" + ")");

            statement.executeUpdate("INSERT INTO contractor_details VALUES ("
                    + tempContractor.getIdNumber() + ", "
                    + "'" + tempContractor.getName() + "'" + ", "
                    + "'" + tempContractor.getTel_num() + "'" + ", "
                    + "'" + tempContractor.getEmail() + "'" +  ", "
                    + "'" + tempContractor.getAddress() + "'" + ")");

            connection.close();
            statement.close();

            System.out.println();
            System.out.println("New project successfully added.");
            System.out.println();
            System.out.println(dashes);
            System.out.println();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * Method to edit existing projects
     */
    // Method to edit existing projects
    public static void editProject() {

        // Print list of all projects
        System.out.println("LIST OF ALL PROJECTS:");
        try {
            printProject();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        // Request project to edit
        // Check if user input is valid
        boolean validProjectNumber = false;
        int projectToEdit = 0;
        int managerToEdit = 0;
        int customerToEdit = 0;
        int architectToEdit = 0;
        int contractorToEdit = 0;
        while (!validProjectNumber) {
            try {
                System.out.print("Enter project number of project to edit: ");
                projectToEdit = Integer.parseInt(input.nextLine());

                // check if project number exists
                ArrayList<Integer> projectNumberList = new ArrayList<>();

                // Connect to database and add each project number to 'projectNumberList'
                try {
                    Connection connection = DriverManager.getConnection(url, username, password);
                    Statement statement = connection.createStatement();
                    ResultSet results;

                    results = statement.executeQuery("SELECT proj_num FROM project_details WHERE proj_num");
                    while (results.next()) {
                        projectNumberList.add(results.getInt("proj_num"));
                    }

                    // Check if the project number exists
                    if (!projectNumberList.contains(projectToEdit)) {
                        System.out.println();
                        System.out.println(">>> Project number " + projectToEdit + " does not exist.");
                        System.out.println();
                    }
                    else {

                        // Get manager id number
                        managerToEdit = getPersonIdNumber("manager_id", projectToEdit);

                        // Get customer id number
                        customerToEdit = getPersonIdNumber("customer_id", projectToEdit);

                        // Get architect id number
                        architectToEdit = getPersonIdNumber("architect_id", projectToEdit);

                        // Get contractor id number
                        contractorToEdit = getPersonIdNumber("contractor_id", projectToEdit);

                        // Close connection to database
                        connection.close();
                        statement.close();
                        results.close();

                        projectNumberList.clear();
                        validProjectNumber = true;
                    }
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            catch (Exception e) {
                System.out.println();
                System.out.println(">>> Please enter valid project number.");
                System.out.println();
            }
        }

        // Display chosen project
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            ResultSet results;

            results = statement.executeQuery("SELECT * FROM project_details WHERE proj_num = " + projectToEdit);
            System.out.println();
            System.out.println(stars);
            System.out.println();
            System.out.println("PROJECT TO EDIT:");
            while (results.next()) {
                System.out.println("Project number:   " + results.getInt("proj_num") + "\n"
                        + "Project name:     " + results.getString("proj_name") + "\n"
                        + "Building Type:    " + results.getString("build_type") + "\n"
                        + "Address:          " + results.getString("address") + "\n"
                        + "ERF number:       " + results.getInt("tot_fee") + "\n"
                        + "Total Fee:        " + results.getFloat("tot_fee") + "\n"
                        + "Total paid:       " + results.getFloat("tot_paid") + "\n"
                        + "Deadline:         " + results.getString("deadline") + "\n"
                        + "Manager id:       " + results.getInt("manager_id") + "\n"
                        + "Customer id:      " + results.getInt("customer_id") + "\n"
                        + "Architect id:     " + results.getInt("architect_id") + "\n"
                        + "Contractor id:    " + results.getInt("contractor_id"));
                System.out.println();
                System.out.println(stars);
                System.out.println();

            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        // Display editing options
        System.out.println("Enter option to edit project:");
        System.out.println("1. Project name");
        System.out.println("2. Building type");
        System.out.println("3. Address");
        System.out.println("4. ERF number");
        System.out.println("5. Total fee");
        System.out.println("6. Total amount paid");
        System.out.println("7. Deadline");
        System.out.println("8. Manager details");
        System.out.println("9. Customer details");
        System.out.println("10. Architect details");
        System.out.println("11. Contractor details");
        System.out.println();

        // Request user edit option
        boolean validEditOption = false;
        int editOption = 0;
        while (!validEditOption) {
            try {
                System.out.print("Select option to edit project: ");
                editOption = Integer.parseInt(input.nextLine());
                if (editOption < 1 || editOption > 11) {
                    System.out.println();
                    System.out.println(">>> Please enter valid option.");
                    System.out.println();
                }
                else {
                    validEditOption = true;
                }
            }
            catch (Exception e) {
                System.out.println();
                System.out.println(">>> Please enter valid option.");
                System.out.println();
            }
        }

        // Request and change project name
        if (editOption == 1) {
            System.out.print("Project name: ");
            String newProjName = input.nextLine();
            editProjectDetail(projectToEdit, "proj_name", newProjName);
            System.out.println("\nProject name updated.\n");
            System.out.println(dashes + "\n");
        }

        // Request and change building ype
        else if (editOption == 2) {
            System.out.print("Building type: ");
            String newBuildingType = input.nextLine();
            editProjectDetail(projectToEdit, "build_type", newBuildingType);
            System.out.println("\nBuilding type updated.\n");
            System.out.println(dashes + "\n");
        }

        // Request and change address
        else if (editOption == 3) {
            System.out.print("Address: ");
            String newAddress = input.nextLine();
            editProjectDetail(projectToEdit, "address", newAddress);
            System.out.println("\nAddress updated.\n");
            System.out.println(dashes + "\n");
        }

        // Request and change ERF number
        else if (editOption == 4) {
            while (true) {
                System.out.print("ERF number: ");

                // Validate user input
                try {
                    int newERFNumber = Integer.parseInt(input.nextLine());
                    editProjectDetail(projectToEdit, "erf_num", newERFNumber);
                    System.out.println("\nERF number updated.\n");
                    System.out.println(dashes + "\n");
                    break;
                }
                catch (Exception e) {
                    System.out.println("\nPlease enter valid ERF number.\n");
                }
            }

        }

        // Request and change total fee
        else if (editOption == 5) {
            while (true) {
                System.out.print("Total fee: ");

                // Validate user input
                try {
                    float newTotalFee = Float.parseFloat(input.nextLine());
                    editProjectDetail(projectToEdit, "erf_num", newTotalFee);
                    System.out.println("\nTotal fee updated.\n");
                    System.out.println(dashes + "\n");
                    break;
                }
                catch (Exception e) {
                    System.out.println("\n>>> Please enter valid total fee.\n");
                }
            }

        }

        // Request and change total amount paid
        else if (editOption == 6) {
            while (true) {
                System.out.print("Total amount paid: ");

                // Validate user input
                try {
                    float newTotalPaid = Float.parseFloat(input.nextLine());
                    editProjectDetail(projectToEdit, "tot_paid", newTotalPaid);
                    System.out.println("\nTotal amount paid updated.\n");
                    System.out.println(dashes + "\n");
                    break;
                }
                catch (Exception e) {
                    System.out.println("\n>>> Please enter valid total amount paid.\n");
                }
            }
        }

        // Request and change deadline
        else if (editOption == 7) {
            System.out.print("Deadline: ");
            String newDeadline = input.nextLine();
            editProjectDetail(projectToEdit, "deadline", newDeadline);
            System.out.println("\nDeadline updated.\n");
            System.out.println(dashes + "\n");

        }

        else if (editOption == 8) {

            boolean running = true;
            while (running) {
                String tableName = "manager_details";

                // Print person to edit
                System.out.println("\n" + stars + "\n");
                printPerson("manager_details", managerToEdit);
                System.out.println("\n" + stars + "\n");

                System.out.println("Select option to edit:");
                System.out.println("1. Name");
                System.out.println("2. Telephone");
                System.out.println("3. Email address");
                System.out.println("4. Address");
                System.out.println("0. Cancel");

                // Request option to edit
                boolean validOption = false;
                int editPersonOption = 0;
                while (!validOption) {
                    try {
                        System.out.print("> ");
                        editPersonOption = Integer.parseInt(input.nextLine());

                        if ((editPersonOption < 0 || editPersonOption > 4)) {
                            System.out.println("\n>>> Please enter valid option\n");
                        } else {
                            validOption = true;
                        }
                    } catch (Exception e) {
                        System.out.println("\n>>> Please enter valid option.\n");
                    }
                }
                // Request and update manager name
                if (editPersonOption == 1) {
                    System.out.print("Name :");
                    String newName = input.nextLine();
                    editPersonDetails(tableName, "name", managerToEdit, newName);

                    System.out.println("\nManager name updated.\n");
                    System.out.println(dashes + "\n");
                }

                // Request and update manager telephone number
                else if (editPersonOption == 2) {
                    System.out.print("Telephone number: ");
                    String newTelNum = input.nextLine();
                    editPersonDetails(tableName, "tel_num", managerToEdit, newTelNum);

                    System.out.println("\nManager telephone number updated.\n");
                    System.out.println(dashes + "\n");
                }

                // Request and update manager email address
                else if (editPersonOption == 3) {
                    System.out.print("Email address: ");
                    String newEmail = input.nextLine();
                    editPersonDetails(tableName, "email", managerToEdit, newEmail);

                    System.out.println("\nManager email address updated.\n");
                    System.out.println(dashes + "\n");
                }

                // Request and update manager address
                else if (editPersonOption == 4){
                    System.out.print("Address: ");
                    String newAddress = input.nextLine();
                    editPersonDetails(tableName, "address", managerToEdit, newAddress);

                    System.out.println("\nManager address updated.\n");
                    System.out.println(dashes + "\n");
                }

                else {
                    System.out.println("\n" + dashes + "\n");
                    running = false;
                }
            }

        }

        else if (editOption == 9) {

            boolean running = true;
            while (running) {
                String tableName = "customer_details";

                // Print person to edit
                System.out.println("\n" + stars + "\n");
                printPerson("customer_details", customerToEdit);
                System.out.println("\n" + stars + "\n");

                System.out.println("Select option to edit:");
                System.out.println("1. Name");
                System.out.println("2. Telephone");
                System.out.println("3. Email address");
                System.out.println("4. Address");
                System.out.println("0. Cancel.");

                // Request option to edit
                boolean validOption = false;
                int editPersonOption = 0;
                while (!validOption) {
                    try {
                        System.out.print("> ");
                        editPersonOption = Integer.parseInt(input.nextLine());

                        if ((editPersonOption < 0 || editPersonOption > 4)) {
                            System.out.println("\n>>> Please enter valid option\n");
                        } else {
                            validOption = true;
                        }
                    } catch (Exception e) {
                        System.out.println("\n>>> Please enter valid option.\n");
                    }
                }
                // Request and update customer name
                if (editPersonOption == 1) {
                    System.out.print("Name :");
                    String newName = input.nextLine();
                    editPersonDetails(tableName, "name", customerToEdit, newName);

                    System.out.println("\nCustomer name updated.\n");
                    System.out.println(dashes + "\n");
                }

                // Request and update customer telephone number
                else if (editPersonOption == 2) {
                    System.out.print("Telephone number: ");
                    String newTelNum = input.nextLine();
                    editPersonDetails(tableName, "tel_num", customerToEdit, newTelNum);

                    System.out.println("\nCustomer telephone number updated.\n");
                    System.out.println(dashes + "\n");
                }

                // Request and update customer email address
                else if (editPersonOption == 3) {
                    System.out.print("Email address: ");
                    String newEmail = input.nextLine();
                    editPersonDetails(tableName, "email", customerToEdit, newEmail);

                    System.out.println("\nCustomer email address updated.\n");
                    System.out.println(dashes + "\n");
                }

                // Request and update customer address
                else if (editPersonOption == 4) {
                    System.out.print("Address: ");
                    String newAddress = input.nextLine();
                    editPersonDetails(tableName, "address", customerToEdit, newAddress);

                    System.out.println("\nCustomer address updated.\n");
                    System.out.println(dashes + "\n");
                }

                else {
                    System.out.println("\n" + dashes + "\n");
                    running = false;
                }
            }
        }

        else if (editOption == 10) {

            boolean running = true;
            while (running) {
                String tableName = "architect_details";

                // Print person to edit
                System.out.println("\n" + stars + "\n");
                printPerson("architect_details", architectToEdit);
                System.out.println("\n" + stars + "\n");

                System.out.println("Select option to edit:");
                System.out.println("1. Name");
                System.out.println("2. Telephone");
                System.out.println("3. Email address");
                System.out.println("4. Address");
                System.out.println("0. Cancel");

                // Request option to edit
                boolean validOption = false;
                int editPersonOption = 0;
                while (!validOption) {
                    try {
                        System.out.print("> ");
                        editPersonOption = Integer.parseInt(input.nextLine());

                        if ((editPersonOption < 0 || editPersonOption > 4)) {
                            System.out.println("\n>>> Please enter valid option\n");
                        } else {
                            validOption = true;
                        }
                    } catch (Exception e) {
                        System.out.println("\n>>> Please enter valid option.\n");
                    }
                }
                // Request and update architect name
                if (editPersonOption == 1) {
                    System.out.print("Name :");
                    String newName = input.nextLine();
                    editPersonDetails(tableName, "name", architectToEdit, newName);

                    System.out.println("\nArchitect name updated.\n");
                    System.out.println(dashes + "\n");
                }

                // Request and update architect telephone number
                else if (editPersonOption == 2) {
                    System.out.print("Telephone number: ");
                    String newTelNum = input.nextLine();
                    editPersonDetails(tableName, "tel_num", architectToEdit, newTelNum);

                    System.out.println("\nArchitect telephone number updated.\n");
                    System.out.println(dashes + "\n");
                }

                // Request and update architect email address
                else if (editPersonOption == 3) {
                    System.out.print("Email address: ");
                    String newEmail = input.nextLine();
                    editPersonDetails(tableName, "email", architectToEdit, newEmail);

                    System.out.println("\nArchitect email address updated.\n");
                    System.out.println(dashes + "\n");
                }

                // Request and update architect address
                else if (editPersonOption == 4){
                    System.out.print("Address: ");
                    String newAddress = input.nextLine();
                    editPersonDetails(tableName, "address", architectToEdit, newAddress);

                    System.out.println("\nArchitect address updated.\n");
                    System.out.println(dashes + "\n");
                }

                else {
                    System.out.println("\n" + dashes + "\n");
                    running = false;
                }
            }
        }

        else {

            boolean running = true;
            while (running) {
                String tableName = "contractor_details";

                // Print person to edit
                System.out.println("\n" + stars + "\n");
                printPerson("contractor_details", contractorToEdit);
                System.out.println("\n" + stars + "\n");

                System.out.println("Select option to edit:");
                System.out.println("1. Name");
                System.out.println("2. Telephone");
                System.out.println("3. Email address");
                System.out.println("4. Address");
                System.out.println("0. Cancel");

                // Request option to edit
                boolean validOption = false;
                int editPersonOption = 0;
                while (!validOption) {
                    try {
                        System.out.print("> ");
                        editPersonOption = Integer.parseInt(input.nextLine());

                        if ((editPersonOption < 0 || editPersonOption > 4)) {
                            System.out.println("\n>>> Please enter valid option\n");
                        } else {
                            validOption = true;
                        }
                    } catch (Exception e) {
                        System.out.println("\n>>> Please enter valid option.\n");
                    }
                }
                // Request and update contractor name
                if (editPersonOption == 1) {
                    System.out.print("Name :");
                    String newName = input.nextLine();
                    editPersonDetails(tableName, "name", contractorToEdit, newName);

                    System.out.println("\nContractor name updated.\n");
                    System.out.println(dashes + "\n");
                }

                // Request and update contractor telephone number
                else if (editPersonOption == 2) {
                    System.out.print("Telephone number: ");
                    String newTelNum = input.nextLine();
                    editPersonDetails(tableName, "tel_num", contractorToEdit, newTelNum);

                    System.out.println("\nContractor telephone number updated.\n");
                    System.out.println(dashes + "\n");
                }

                // Request and update contractor email address
                else if (editPersonOption == 3) {
                    System.out.print("Email address: ");
                    String newEmail = input.nextLine();
                    editPersonDetails(tableName, "email", contractorToEdit, newEmail);

                    System.out.println("\nContractor email address updated.\n");
                    System.out.println(dashes + "\n");
                }

                // Request and update contractor address
                else if (editPersonOption == 4) {
                    System.out.print("Address: ");
                    String newAddress = input.nextLine();
                    editPersonDetails(tableName, "address", contractorToEdit, newAddress);

                    System.out.println("\nContractor address updated.\n");
                    System.out.println(dashes + "\n");
                }

                else {
                    System.out.println("\n" + dashes + "\n");
                    running = false;
                }

            }
        }
    }

    /**
     * Method to finalise a project
     */
    // Method to finalise a project
    public static void finaliseProject() {

        // Store all project numbers - used to validate user input later
        ArrayList<Integer> projectNumberList = new ArrayList<>();

        // Print all projects
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            ResultSet results;

            results = statement.executeQuery("SELECT * FROM project_details");

            System.out.println();
            while (results.next()) {
                projectNumberList.add(results.getInt("proj_num"));
                System.out.println(results.getInt("proj_num") + " -- "
                        + results.getString("proj_name") + " -- "
                        + results.getString("build_type") + " -- "
                        + results.getString("address") + " -- "
                        + results.getInt("erf_num") + " -- "
                        + results.getFloat("tot_fee") + " -- "
                        + results.getFloat("tot_paid") + " -- "
                        + results.getString("deadline") + " -- "
                        + results.getInt("manager_id") + " -- "
                        + results.getInt("customer_id") + " -- "
                        + results.getInt("architect_id") + " -- "
                        + results.getInt("contractor_id"));
            }
            System.out.println();

        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        // Request project to finalise
        int projectFinalise = 0;
        int managerFinalise;
        int customerFinalise;
        int architectFinalise;
        int contractorFinalise;
        float feeFinalise;
        float paidFinalise;

        boolean validProjectNumber = false;
        while (!validProjectNumber) {
            try {
                System.out.print("Select project to finalise by entering project number: ");
                projectFinalise = Integer.parseInt(input.nextLine());

                if (projectNumberList.contains(projectFinalise)) {
                    validProjectNumber = true;
                }
                else {
                    System.out.println();
                    System.out.println(">>> Project number " + projectFinalise + " does not exist.");
                    System.out.println();
                }
            } catch (Exception e) {
                System.out.println();
                System.out.println(">>> Please enter a valid option.");
                System.out.println();
            }
        }

        // Get id numbers of people related to the project
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            ResultSet results;

            ArrayList<Integer> managerIdNumber = new ArrayList<>();
            ArrayList<Integer> customerIdNumber = new ArrayList<>();
            ArrayList<Integer> architectIdNumber = new ArrayList<>();
            ArrayList<Integer> contractorIdNumber = new ArrayList<>();
            ArrayList<Float> totalFeeList = new ArrayList<>();
            ArrayList<Float> totalPaidList = new ArrayList<>();

            // Get manager id number
            results = statement.executeQuery("SELECT manager_id FROM project_details WHERE proj_num = " + projectFinalise);
            while (results.next()) {
                managerIdNumber.add(results.getInt("manager_id"));
            }
            managerFinalise = managerIdNumber.get(0);

            // Get customer id number
            results = statement.executeQuery("SELECT customer_id FROM project_details WHERE proj_num = " + projectFinalise);
            while (results.next()) {
                customerIdNumber.add(results.getInt("customer_id"));
            }
            customerFinalise = customerIdNumber.get(0);

            // Get architect id number
            results = statement.executeQuery("SELECT architect_id FROM project_details WHERE proj_num = " + projectFinalise);
            while (results.next()) {
                architectIdNumber.add(results.getInt("architect_id"));
            }
            architectFinalise = architectIdNumber.get(0);

            // Get contractor id number
            results = statement.executeQuery("SELECT contractor_id FROM project_details WHERE proj_num = " + projectFinalise);
            while (results.next()) {
                contractorIdNumber.add(results.getInt("contractor_id"));
            }
            contractorFinalise = contractorIdNumber.get(0);

            // Get total fee
            results = statement.executeQuery("SELECT tot_fee FROM project_details WHERE proj_num = " + projectFinalise);
            while (results.next()) {
                totalFeeList.add(results.getFloat("tot_fee"));
            }
            feeFinalise = totalFeeList.get(0);

            // Get total paid
            results = statement.executeQuery("SELECT tot_paid FROM project_details WHERE proj_num = " + projectFinalise);
            while (results.next()) {
                totalPaidList.add(results.getFloat("tot_paid"));
            }
            paidFinalise = totalPaidList.get(0);

            // Create invoice if needed
            if (paidFinalise < feeFinalise) {

                float outstandingAmount = feeFinalise - paidFinalise;

                // Print customer details and outstanding amount
                results = statement.executeQuery("SELECT * FROM customer_details WHERE customer_id = " + customerFinalise);
                System.out.println();
                System.out.println(stars);
                System.out.println();
                while (results.next()) {
                    System.out.println(results.getString("name") + " -- "
                            + results.getString("tel_num") + " -- "
                            + results.getString("email") + " -- "
                            + results.getString("address"));
                }
                System.out.println();
                System.out.println(">>> Outstanding amount: R" + outstandingAmount);
                System.out.println();
                System.out.println(stars);
            }
            else {
                System.out.println();
                System.out.println("Project successfully finalised.");
            }

            // Delete project and people after finalising project
            statement.executeUpdate("DELETE FROM project_details WHERE proj_num = " + projectFinalise);
            statement.executeUpdate("DELETE FROM manager_details WHERE manager_id = " + managerFinalise);
            statement.executeUpdate("DELETE FROM customer_details WHERE customer_id = " + customerFinalise);
            statement.executeUpdate("DELETE FROM architect_details WHERE architect_id = " + architectFinalise);
            statement.executeUpdate("DELETE FROM contractor_details WHERE contractor_id = " + architectFinalise);

            // Close connections
            connection.close();
            statement.close();
            results.close();

            System.out.println();
            System.out.println(dashes);
            System.out.println();

        }
        catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * Method to view all projects
     */
    // Method to view all projects
    public static void viewAll() {

        // Print all projects
        System.out.println("LIST OF ALL PROJECTS:");
        try {
            printProject();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Connect to database and print all managers, customers, architects and contractors
        try {
            // Connect to database
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            ResultSet results;
            System.out.println();

            // Print all managers
            System.out.println("LISTS OF ALL MANAGERS:");
            results = statement.executeQuery("SELECT * FROM manager_details");
            while (results.next()) {
                System.out.println(results.getInt("manager_id") + " -- "
                        + results.getString("name") + " -- "
                        + results.getString("tel_num") + " -- "
                        + results.getString("email") + " -- "
                        + results.getString("address"));
            }
            System.out.println();

            // Print all customer
            System.out.println("LISTS OF ALL CUSTOMERS:");
            results = statement.executeQuery("SELECT * FROM customer_details");
            while (results.next()) {
                System.out.println(results.getInt("customer_id") + " -- "
                        + results.getString("name") + " -- "
                        + results.getString("tel_num") + " -- "
                        + results.getString("email") + " -- "
                        + results.getString("address"));
            }
            System.out.println();

            // Print all architectures
            System.out.println("LISTS OF ALL ARCHITECTURES:");
            results = statement.executeQuery("SELECT * FROM architect_details");
            while (results.next()) {
                System.out.println(results.getInt("architect_id") + " -- "
                        + results.getString("name") + " -- "
                        + results.getString("tel_num") + " -- "
                        + results.getString("email") + " -- "
                        + results.getString("address"));
            }
            System.out.println();

            // Print all contractor
            System.out.println("LISTS OF ALL CONTRACTORS:");
            results = statement.executeQuery("SELECT * FROM contractor_details");
            while (results.next()) {
                System.out.println(results.getInt("contractor_id") + " -- "
                        + results.getString("name") + " -- "
                        + results.getString("tel_num") + " -- "
                        + results.getString("email") + " -- "
                        + results.getString("address"));
            }

            System.out.println();
            System.out.println(dashes);
            System.out.println();

            // Close connections to database
            connection.close();
            statement.close();
            results.close();

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method to view overdue projects
     */
    // Method to view overdue projects
    public static void viewOverDue() {

        SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");

        // Get current date
        Date date = new Date();
        String currentDate = SDF.format(date);
        Date date1 = null;
        try {
            date1 = SDF.parse(currentDate);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        // Get deadline of each project and compare it to current date
        // If project is overdue - display project

        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            ResultSet results;

            results = statement.executeQuery("SELECT * FROM project_details");

            ArrayList<Date> dateList = new ArrayList<>();

            // Loop through deadlines and print the project if it is overdue
            System.out.println("OVERDUE PROJECTS:");
            System.out.println();
            while (results.next()) {
                dateList.add(results.getDate("deadline"));
                Date deadline = dateList.get(0);
                int dateCompare = date1.compareTo(deadline);

                if (dateCompare > 0) {
                    System.out.println("Project number:   " + results.getInt("proj_num") + "\n"
                            + "Project name:     " + results.getString("proj_name") + "\n"
                            + "Building Type:    " + results.getString("address") + "\n"
                            + "ERF number:       " + results.getInt("tot_fee") + "\n"
                            + "Total Fee:        " + results.getFloat("tot_paid") + "\n"
                            + "Deadline:         " + results.getString("deadline") + "\n"
                            + "Manager id:       " + results.getInt("manager_id") + "\n"
                            + "Customer id:      " + results.getInt("customer_id") + "\n"
                            + "Architect id:     " + results.getInt("architect_id") + "\n"
                            + "Contractor id:    " + results.getInt("contractor_id"));
                    System.out.println();
                }
                dateList.clear();
            }

            // Close connections to database
            connection.close();
            statement.close();
            results.close();

            System.out.println();
            System.out.println(dashes);
            System.out.println();

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Methods to edit project details
     * @param proj_num Project number of project to update
     * @param detailName Specific detail to be updated
     * @param newDetail The new information
     */
    // Methods to edit project details
    public static void editProjectDetail(int proj_num, String detailName, String newDetail) {

        // Connect to database and change detail
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();

            statement.executeUpdate("UPDATE project_details Set " + detailName + " = '" + newDetail + "' WHERE proj_num = " + proj_num);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public static void editProjectDetail(int proj_num, String detailName, int newDetail) {

        // Connect to database and change detail
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();

            statement.executeUpdate("UPDATE project_details Set " + detailName + " = " + newDetail + " WHERE proj_num = " + proj_num);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public static void editProjectDetail(int proj_num, String detailName, float newDetail) {

        // Connect to database and change detail
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();

            statement.executeUpdate("UPDATE project_details Set " + detailName + " = " + newDetail + " WHERE proj_num = " + proj_num);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * Method to edit people details
     * @param tableName Name of table where person to edit is located
     * @param columnName Name of the column where the person to edit is located
     * @param idNumber Name of the id
     * @param newDetail New information
     */
    // Method to edit people details
    public static void editPersonDetails(String tableName, String columnName, int idNumber, String newDetail) {

        // Determine table name
        String idColumn = (tableName.split("_"))[0];

        // Connect to database and change detail
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();

            statement.executeUpdate("UPDATE " + tableName + " SET " + columnName + " = '" + newDetail + "' WHERE " + idColumn + "_id = " + idNumber);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method to print projects
     * @throws SQLException Exception handling
     */
    // Method to print projects
    public static void printProject() throws SQLException {

        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            ResultSet results;

            results = statement.executeQuery("SELECT * FROM project_details");

            System.out.println();
            while (results.next()) {
                System.out.println("Project number:   " + results.getInt("proj_num") + "\n"
                        + "Project name:     " + results.getString("proj_name") + "\n"
                        + "Building Type:    " + results.getString("build_type") + "\n"
                        + "Address:          " + results.getString("address") + "\n"
                        + "ERF number:       " + results.getInt("tot_fee") + "\n"
                        + "Total Fee:        " + results.getFloat("tot_fee") + "\n"
                        + "Total paid:       " + results.getFloat("tot_paid") + "\n"
                        + "Deadline:         " + results.getString("deadline") + "\n"
                        + "Manager id:       " + results.getInt("manager_id") + "\n"
                        + "Customer id:      " + results.getInt("customer_id") + "\n"
                        + "Architect id:     " + results.getInt("architect_id") + "\n"
                        + "Contractor id:    " + results.getInt("contractor_id"));
                System.out.println();
            }

            // Close connections to database
            connection.close();
            statement.close();
            results.close();

        }
        catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * Method to print details about person
     * @param tableName Name of the table where the person information is located
     * @param idNumber Id number of the person to be printed
     */
    // Method to print person
    public static void printPerson(String tableName, int idNumber) {

        String columnName = tableName.split("_")[0] + "_id";
        StringBuilder personDetails = new StringBuilder();

        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            ResultSet results;

            results = statement.executeQuery("SELECT * FROM " + tableName + " WHERE " + columnName + " = " + idNumber);
            while (results.next()) {
                personDetails.append("Id Number:       ").append(results.getInt(columnName)).append("\nName:            ").append(results.getString("name")).append("\nTel number:      ").append(results.getString("tel_num")).append("\nEmail address:   ").append(results.getString("email")).append("\nAddress:         ").append(results.getString("address"));
            }

        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println(personDetails);

    }

    /**
     * Method to get id numbers of people
     * @param columnName Name of column where person is located
     * @param proj_num Project number
     * @return Returns the id number of specific person
     */
    // Method to get id numbers
    public static int getPersonIdNumber(String columnName, int proj_num) {

        int idNumber = 0;

        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            ResultSet results;

            results = statement.executeQuery("SELECT " + columnName + " FROM project_details " + " WHERE proj_num = " + proj_num);
            while (results.next()) {
                idNumber = results.getInt(columnName);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return idNumber;
    }

    /**
     * This method contains the code to reset database tables to predefined values
     */
    // Method to reset tables
    public static void reset() {

        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            ResultSet results;

            statement.executeUpdate("DELETE FROM project_details");
            statement.executeUpdate("DELETE FROM manager_details");
            statement.executeUpdate("DELETE FROM customer_details");
            statement.executeUpdate("DELETE FROM architect_details");
            statement.executeUpdate("DELETE FROM contractor_details");

            statement.executeUpdate("INSERT INTO project_details VALUES (" +
                    "1, " +
                    "'First Home', " +
                    "'House', " +
                    "'1st Street', " +
                    "123, " +
                    "1000000, " +
                    "200000, " +
                    "'2022-03-20', " +
                    "1, " +
                    "1, " +
                    "1, " +
                    "1" +
                    ")");

            statement.executeUpdate("INSERT INTO project_details VALUES (" +
                    "2, " +
                    "'Sky Hotel', " +
                    "'Hotel', " +
                    "'Town Street', " +
                    "456, " +
                    "2000000, " +
                    "2000000, " +
                    "'2020-10-10', " +
                    "2, " +
                    "2, " +
                    "2, " +
                    "2)");

            statement.executeUpdate("INSERT INTO manager_details VALUES (" +
                    "1, " +
                    "'Vivian Parker', " +
                    "'072 483 3094', " +
                    "'vivparker@gamail.com', " +
                    "'1st Street')");

            statement.executeUpdate("INSERT INTO customer_details VALUES (" +
                    "1, " +
                    "'Esmond Baxter', " +
                    "'082 849 1244'," +
                    "'esbax@gmail.com', " +
                    "'2nd Road')");

            statement.executeUpdate("INSERT INTO architect_details VALUES (" +
                    "1, " +
                    "'Kendrick Valdez', " +
                    "'027 984 2385', " +
                    "'kendy@gmail.com', " +
                    "'3rd Street')");

            statement.executeUpdate("INSERT INTO contractor_details VALUES (" +
                    "1, " +
                    "'Timothy Tomlinson', " +
                    "'021 893 2948', " +
                    "'timmyy@gmail.com', " +
                    "'4th Road')");

            statement.executeUpdate("INSERT INTO manager_details VALUES (" +
                    "2, " +
                    "'Horace Delgado', " +
                    "'072 294 2482', " +
                    "'delgo@gmail.com', " +
                    "'5th Street')");

            statement.executeUpdate("INSERT INTO customer_details VALUES (" +
                    "2, " +
                    "'Sirena Lee', " +
                    "'021 888 2923', " +
                    "'sirilee@gmail.com', " +
                    "'3rd Street')");

            statement.executeUpdate("INSERT INTO architect_details VALUES (" +
                    "2, " +
                    "'Nelson Mcbride', " +
                    "'021 874 2299', " +
                    "'mandela@gmail.com', " +
                    "'4th Road')");

            statement.executeUpdate("INSERT INTO contractor_details VALUES (" +
                    "2, " +
                    "'Paul Griffith', " +
                    "'072 991 0332', " +
                    "'griffithpaul@gmail.com', " +
                    "'7th Street')");

        }
        catch (SQLException e) {
            e.printStackTrace();
        }

    }









}
