package com.yazeed;

/**
 * Creates different Project objects.
 */
public class Project {

    // Attributes
    private int projectNumber;
    private String projectName;
    private String buildingType;
    private String address;
    private int ERFNumber;
    private float totalFee;
    private float totalPaid;
    private String deadline;
    private int managerId;
    private int customerId;
    private int architectId;
    private int  contractorId;

    /**
     * Project constructor
     * @param projectNumber Project number - unique to each project
     * @param projectName Project name
     * @param buildingType Building Type
     * @param address Address of the building
     * @param ERFNumber ERF number
     * @param totalFee Total fee of the project
     * @param totalPaid Total amount paid by the customer
     * @param deadline Deadline of the project
     * @param managerId Id number of the person the managers the project
     * @param customerId Id number of the customer
     * @param architectId Id number of the architect
     * @param contractorId Id number of the contractor
     */
    // Constructor
    public Project(int projectNumber, String projectName, String buildingType, String address, int ERFNumber, float totalFee, float totalPaid, String deadline, int managerId, int customerId, int architectId, int contractorId) {
        this.projectNumber = projectNumber;
        this.projectName = projectName;
        this.buildingType = buildingType;
        this.address = address;
        this.ERFNumber = ERFNumber;
        this.totalFee = totalFee;
        this.totalPaid = totalPaid;
        this.deadline = deadline;
        this.managerId = managerId;
        this.customerId = customerId;
        this.architectId = architectId;
        this.contractorId = contractorId;
    }

    // Getters and setters

    public int getProjectNumber() {
        return projectNumber;
    }
    public void setProjectNumber(int projectNumber) {
        this.projectNumber = projectNumber;
    }

    public String getProjectName() {
        return projectName;
    }
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getBuildingType() {
        return buildingType;
    }
    public void setBuildingType(String buildingType) {
        this.buildingType = buildingType;
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public int getERFNumber() {
        return ERFNumber;
    }
    public void setERFNumber(int ERFNumber) {
        this.ERFNumber = ERFNumber;
    }

    public float getTotalFee() {
        return totalFee;
    }
    public void setTotalFee(float totalFee) {
        this.totalFee = totalFee;
    }

    public float getTotalPaid() {
        return totalPaid;
    }
    public void setTotalPaid(float totalPaid) {
        this.totalPaid = totalPaid;
    }

    public String getDeadline() {
        return deadline;
    }
    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public int getManagerId() {
        return managerId;
    }
    public void setManagerId(int managerId) {
        this.managerId = managerId;
    }

    public int getCustomerId() {
        return customerId;
    }
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getArchitectId() {
        return architectId;
    }
    public void setArchitectId(int architectId) {
        this.architectId = architectId;
    }

    public int getContractorId() {
        return contractorId;
    }
    public void setContractorId(int contractorId) {
        this.contractorId = contractorId;
    }

    // toString
    public String toString() {
        String output = "Project Number:             " + projectNumber;
        output += "\n\nProject Name:                " + projectName;
        output += "\n\nBuilding type:               " + buildingType;
        output += "\n\nAddress:                     " + address;
        output += "\n\nERF Number:                  " + ERFNumber;
        output += "\n\nTotal fee:                   " + " R" + totalFee;
        output += "\n\nAmount paid to date:         " + " R" + totalPaid;
        output += "\n\nDeadline:                    " + deadline;
        output += "\n\nManager id :                 " + managerId;
        output += "\n\nCustomer id:                 " + customerId;
        output += "\n\nArchitect id:                " + architectId;
        output += "\n\nContrator id:                " + contractorId;

        return output;
    }
}
