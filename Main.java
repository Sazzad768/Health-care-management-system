import java.util.Scanner;

class User {
    protected String name;
    protected int age;
    protected double waterIntake;

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void addWater(double water) {
        this.waterIntake += water;
    }

    public double getWaterIntake() {
        return waterIntake;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    void showType() {
        System.out.println("General User");
    }
}

class StudentUser extends User {
    @Override
    void showType() {
        System.out.println("Student User (Moderate Risk)");
    }
}

class WorkerUser extends User {
    @Override
    void showType() {
        System.out.println("Outdoor Worker (High Risk)");
    }
}

class ElderlyUser extends User {
    @Override
    void showType() {
        System.out.println("Elderly User (Very High Risk)");
    }
}

abstract class HealthSystem {
    abstract void checkHealth(User user, double temperature);
}

class HydrationSystem extends HealthSystem {
    @Override
    void checkHealth(User user, double temperature) {
        if (user.getWaterIntake() < 2.0) {
            System.out.println("💧 Warning: Drink more water!");
        } else {
            System.out.println("✅ Hydration level is good.");
        }
    }
}

class TemperatureSystem extends HealthSystem {
    @Override
    void checkHealth(User user, double temperature) {
        if (temperature >= 35) {
            System.out.println("🔥 High Temperature Detected!");
        } else {
            System.out.println("🌤️ Temperature is normal.");
        }
    }
}

interface AlertSystem {
    void sendAlert();
}

class SMSAlert implements AlertSystem {
    public void sendAlert() {
        System.out.println("📩 SMS Alert Sent!");
    }
}

class AppAlert implements AlertSystem {
    public void sendAlert() {
        System.out.println("📱 App Notification Sent!");
    }
}

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        User user;

        System.out.println("Select User Type:");
        System.out.println("1. Student");
        System.out.println("2. Worker");
        System.out.println("3. Elderly");
        int type = sc.nextInt();
        sc.nextLine();

        if (type == 1) {
            user = new StudentUser();
        } else if (type == 2) {
            user = new WorkerUser();
        } else {
            user = new ElderlyUser();
        }

        System.out.print("Enter Name: ");
        user.setName(sc.nextLine());

        System.out.print("Enter Age: ");
        user.setAge(sc.nextInt());

        System.out.print("Enter Temperature (°C): ");
        double temp = sc.nextDouble();

        System.out.print("Enter Water Intake (Liters): ");
        double water = sc.nextDouble();
        user.addWater(water);

        System.out.println("\n===== User Type =====");
        user.showType();

        System.out.println("\n===== Health Check =====");

        HealthSystem h1 = new HydrationSystem();
        HealthSystem h2 = new TemperatureSystem();

        h1.checkHealth(user, temp);
        h2.checkHealth(user, temp);

        if (temp >= 35 || user.getWaterIntake() < 2.0) {
            AlertSystem alert1 = new SMSAlert();
            AlertSystem alert2 = new AppAlert();

            alert1.sendAlert();
            alert2.sendAlert();
        }

        if (user instanceof ElderlyUser && temp >= 35) {
            System.out.println("⚠️ Elderly High Risk! Stay indoors!");
        }

        System.out.println("\n===== Summary =====");
        System.out.println("Name: " + user.getName());
        System.out.println("Age: " + user.getAge());
        System.out.println("Water Intake: " + user.getWaterIntake() + " L");

        System.out.println("\n✅ Stay Safe!");

        sc.close();
    }
}
