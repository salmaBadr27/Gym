package Models;


public class Packages {
    private int packageId;
    private String packageName;
    private String fees;
      private String duration;
    private String description;
  

    public Packages(int packageId, String packageName, String fees, String duration, String description) {
        this.packageId = packageId;
        this.packageName = packageName;
        this.fees = fees;
        this.description = description;
        this.duration = duration;
    }

    public int getPackageId() {
        return packageId;
    }

    public String getPackageName() {
        return packageName;
    }

    public String getFees() {
        return fees;
    }

    public String getDescription() {
        return description;
    }

    public String getDuration() {
        return duration;
    }

    @Override
    public String toString() {
        return "Packages{" + "packageId=" + packageId + ", packageName=" + packageName + ", fees=" + fees + ", description=" + description + ", duration=" + duration + '}';
    }

  

   

    
    
    
}
