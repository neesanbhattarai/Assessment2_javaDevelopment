
import java.util.*;

// Interface for maintenance activities
interface MaintenanceActivity {
    void recordMaintenance(String kind, Date date, String description, double cost);
}

// Interface for maintenance scheduling
interface MaintenanceScheduler {
    void scheduleMaintenanceTask(String vehicleId, Date date);
}

// Interface for handling repair requests
interface RepairRequestHandler {
    void processRepairRequest(String vehicleId, String issueDescription);
}

// Interface for generating maintenance reminders
interface MaintenanceReminderGenerator {
    void generateMaintenanceReminder(String vehicleId);
}

// Interface for managing spare parts inventory
interface SparePartsInventoryManager {
    void monitorSparePartsAvailability();
}

// Interface for monitoring vehicle mileage
interface MileageMonitor {
    void trackVehicleMileage(String vehicleId, double mileage);
}

// Interface for generating maintenance reports
interface MaintenanceReportGenerator {
    void generateMaintenanceReport(String vehicleId);
}

// Interface for managing maintenance schedule
interface MaintenanceScheduleManager {
    void manageMaintenanceSchedule(String vehicleId, Date scheduleDate);
}

// Interface for tracking warranty information
interface WarrantyTracker {
    void trackWarrantyInformation(String vehicleId);
}

// Interface for tracking repair status
interface RepairStatusTracker {
    void trackRepairStatus(String vehicleId);
}

// Abstract class to represent a vehicle
abstract class Vehicle {
    protected String vehicleId;
    protected double mileage;
    
    // Constructor
    public Vehicle(String vehicleId, double mileage) {
        this.vehicleId = vehicleId;
        this.mileage = mileage;
    }
    
    // Method to update mileage
    public void updateMileage(double mileage) {
        this.mileage = mileage;
    }
}

// Concrete class representing a maintenance technician
class MaintenanceTechnician {
    public void performMaintenance(String vehicleId, String description) {
        // Perform maintenance tasks
        System.out.println("Maintenance performed for vehicle " + vehicleId + ": " + description);
    }
}

// Concrete class implementing the interfaces
class MaintenanceSystem extends Vehicle implements MaintenanceActivity, MaintenanceScheduler, 
                                                RepairRequestHandler, MaintenanceReminderGenerator, 
                                                SparePartsInventoryManager, MileageMonitor, 
                                                MaintenanceReportGenerator, MaintenanceScheduleManager, 
                                                WarrantyTracker, RepairStatusTracker {
    private Map<String, List<String>> repairStatusMap;
    private Map<String, Date> maintenanceScheduleMap;
    private Map<String, Double> mileageMap;
    
    // Constructor with vehicle details
    public MaintenanceSystem(String vehicleId, double mileage) {
        super(vehicleId, mileage);
        this.repairStatusMap = new HashMap<>();
        this.maintenanceScheduleMap = new HashMap<>();
        this.mileageMap = new HashMap<>();
        this.mileage = mileage;
    }

    @Override
    public void recordMaintenance(String kind, Date date, String description, double cost) {
        // Record maintenance activities
        System.out.println("Maintenance recorded for vehicle " + vehicleId + ": " + description);
    }

    @Override
    public void scheduleMaintenanceTask(String vehicleId, Date date) {
        // Schedule maintenance tasks
        maintenanceScheduleMap.put(vehicleId, date);
        System.out.println("Maintenance scheduled for vehicle " + vehicleId + " on " + date);
    }

    @Override
    public void processRepairRequest(String vehicleId, String issueDescription) {
        // Process repair requests
        List<String> repairStatus = repairStatusMap.getOrDefault(vehicleId, new ArrayList<>());
        repairStatus.add(issueDescription);
        repairStatusMap.put(vehicleId, repairStatus);
        System.out.println("Repair request processed for vehicle " + vehicleId + ": " + issueDescription);
    }

    @Override
    public void generateMaintenanceReminder(String vehicleId) {
        // Generate maintenance reminders
        System.out.println("Maintenance reminder generated for vehicle " + vehicleId);
    }

    @Override
    public void monitorSparePartsAvailability() {
        // Monitor spare parts inventory
        System.out.println("Monitoring spare parts availability...");
    }

    @Override
    public void trackVehicleMileage(String vehicleId, double mileage) {
        // Track vehicle mileage
        mileageMap.put(vehicleId, mileage);
        System.out.println("Vehicle mileage tracked for " + vehicleId + ": " + mileage);
    }


     @Override
    public void generateMaintenanceReport(String vehicleId) {
        // Get repair status for the vehicle
        List<String> repairStatus = repairStatusMap.getOrDefault(vehicleId, new ArrayList<>());
        
        // Print maintenance report along with repair status
        System.out.println("Maintenance report for vehicle " + vehicleId + ":");
        System.out.println("Repair Status: " + repairStatus);
    }

  

     @Override
public void manageMaintenanceSchedule(String vehicleId, Date scheduleDate) {
    // Manage maintenance schedule
    maintenanceScheduleMap.put(vehicleId, scheduleDate);
    
    // Calculate next maintenance date based on initial mileage + 3000
    double initialMileage = mileageMap.getOrDefault(vehicleId, 0.0);
    double nextMaintenanceMileage = initialMileage + 3000.0;
    
    // Print next maintenance date
    System.out.println("Next maintenance for vehicle " + vehicleId + " should be scheduled when the mileage reaches: " + nextMaintenanceMileage);
}

    @Override
    public void trackWarrantyInformation(String vehicleId) {
        // Track warranty information
        System.out.println("Tracking warranty information for vehicle " + vehicleId);
    }

    @Override
    public void trackRepairStatus(String vehicleId) {
        // Track repair status
        List<String> repairStatus = repairStatusMap.getOrDefault(vehicleId, new ArrayList<>());
        System.out.println("Repair status for vehicle " + vehicleId + ": " + repairStatus);
    }
}

public class Main {
    public static void main(String[] args) {
        // Detail of the  vehicle 
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter vehicle ID:");
        String vehicleId = scanner.nextLine();
        System.out.println("Enter initial mileage:");
        double mileage = scanner.nextDouble();
        scanner.nextLine(); // Consume newline
        // Instantiate MaintenanceSystem with vehicle details
        MaintenanceSystem maintenanceSystem = new MaintenanceSystem(vehicleId, mileage);

        System.out.println("Enter maintenance description:");
        String description = scanner.nextLine();
        System.out.println("Enter cost:");
        double cost = scanner.nextDouble();
        scanner.nextLine(); // Consume newline
        maintenanceSystem.recordMaintenance("Oil Change", new Date(), description, cost);
        maintenanceSystem.scheduleMaintenanceTask(vehicleId, new Date());
        System.out.println("Enter repair issue description:");
        String issueDescription = scanner.nextLine();
        maintenanceSystem.processRepairRequest(vehicleId, issueDescription);
        maintenanceSystem.generateMaintenanceReminder(vehicleId);
        maintenanceSystem.monitorSparePartsAvailability();
        System.out.println("Enter updated mileage:");
        double updatedMileage = scanner.nextDouble();
        maintenanceSystem.trackVehicleMileage(vehicleId, updatedMileage); // Update mileage
        maintenanceSystem.generateMaintenanceReport(vehicleId);
        maintenanceSystem.manageMaintenanceSchedule(vehicleId, new Date());
        maintenanceSystem.trackWarrantyInformation(vehicleId);
        maintenanceSystem.trackRepairStatus(vehicleId);
        
        scanner.close();
    }
}

