
package Models;


public class Packages {
    private int packageId;
    private String title;
    private String fees;
    private String description;

    public Packages(int packageId, String title, String fees, String description) {
        this.packageId = packageId;
        this.title = title;
        this.fees = fees;
        this.description = description;
    }

  
    

    public int getPackageId() {
        return packageId;
    }

    public String getTitle() {
        return title;
    }

    public String getFees() {
        return fees;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Packages{" + "packageId=" + packageId + ", title=" + title + ", fees=" + fees + ", description=" + description + '}';
    }
    
    
}
