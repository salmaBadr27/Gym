package DataBase.PackageRepository;

import Models.Packages;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class PackageMySqlRepository extends PackageRepository {

    private Connection connection;
    private Statement statement;
    private ResultSet result;

    public PackageMySqlRepository(Connection connection, Statement statement) {
        this.connection = connection;
        this.statement = statement;
    }

    @Override
    public ArrayList<Packages> getAllPackages() {
        ArrayList<Packages> allPackages = new ArrayList();
        try {
            String selectQuery = "select * from packages";
            result = statement.executeQuery(selectQuery);
            if (result.next() == false) {
                System.out.print("no records found");
            } else {
                do {
                    int packageId = result.getInt("package_id");
                    String packageName = result.getString("name");
                    String packageFees = result.getString("fees");
                    String packageDuration = result.getString("duration");
                    String packageDescription = result.getString("description");
                    Packages packages = new Packages(packageId, packageName, packageFees, packageDuration, packageDescription);
                    allPackages.add(packages);
                } while (result.next());
            }

        } catch (SQLException ex) {
            System.out.print("caught in repo" + ex);
        }
        return allPackages;
    }

    @Override
    public Packages getPackageById(int id) {
        try {
            String selectQuery = "select * from packages where package_id ='" + id + "'";
            result = statement.executeQuery(selectQuery);
            if (result.next() == false) {
                System.out.print("no records found");
            } else {
                do {
                    int packageId = result.getInt("package_id");
                    String packageName = result.getString("name");
                    String packageFees = result.getString("fees");
                    String packageDuration = result.getString("duration");
                    String packageDescription = result.getString("description");
                    Packages packages = new Packages(packageId, packageName, packageFees, packageDuration, packageDescription);
                    return packages;
                } while (result.next());
            }
        } catch (SQLException ex) {
            System.out.print("caught in repo" + ex);
        }
        return null;
    }

    @Override
    public Packages getPackageByName(String PackageName) {
        try {
            String selectQuery = "select * from packages where name ='" + PackageName + "'";
            result = statement.executeQuery(selectQuery);
            if (result.next() == false) {
                System.out.print("no records found");
            } else {
                do {
                    int packageId = result.getInt("package_id");
                    String packageName = result.getString("name");
                    String packageFees = result.getString("fees");
                    String packageDuration = result.getString("duration");
                    String packageDescription = result.getString("description");
                    Packages packages = new Packages(packageId, packageName, packageFees, packageDuration, packageDescription);
                    return packages;
                } while (result.next());
            }

        } catch (SQLException ex) {
            System.out.print("caught in repo" + ex);
        }
        return null;
    }

    @Override
    public Packages addPackage(Packages newPackage) {
        try {
            statement.executeUpdate("insert into pakages (name,fees,duration,description) VALUES('" + newPackage.getPackageName() + "','" + newPackage.getFees() + "','" + newPackage.getDuration() + "','" + newPackage.getDescription() + "')");
            Packages addedPackage = getPackageByName(newPackage.getPackageName());
            return addedPackage;
        } catch (SQLException ex) {
            System.out.print("caught in repo" + ex);
        }
        return null;
    }

    @Override
    public Packages updatePackage(int id, Packages newPackageInfo) {
        Packages oldPackage = getPackageById(id);
        try {
            boolean packageNameCondition = !(oldPackage.getPackageName() == newPackageInfo.getPackageName());
            boolean packageFeesCondition = !(oldPackage.getFees() == newPackageInfo.getFees());
            boolean packageDurationCondition = !(oldPackage.getDuration() == newPackageInfo.getDuration());
            boolean packageDescriptionCondition = !(oldPackage.getDescription() == newPackageInfo.getDescription());
            String updateQuery = "update attendance set '"
                    + getUpdatedValue(packageNameCondition, newPackageInfo.getPackageName(), "name") + "'\n"
                    + getUpdatedValue(packageFeesCondition, newPackageInfo.getFees(), "fees") + "\n"
                    + getUpdatedValue(packageDurationCondition, newPackageInfo.getDuration(), "duration") + "'\n"
                    + getUpdatedValue(packageDescriptionCondition, newPackageInfo.getDescription(), "description") + "'\n";
            statement.executeUpdate(updateQuery);
            return newPackageInfo;
        } catch (SQLException ex) {
            System.out.print("someThing went wrong" + ex);
        }
        return oldPackage;
    }

    @Override
    public Packages removePackage(int id) {
        try {
            Packages deletedPackage = getPackageById(id);
            statement.executeUpdate("Delete from pacakges where package_id = '" + id + "'");
            return deletedPackage;
        } catch (SQLException ex) {
            System.out.print("someThing went wrong" + ex);
        }
        return null;
    }

    @Override
    public String getUpdatedValue(boolean condition, String newValue, String columnName) {
        try {
            if (condition) {
                return "'" + columnName + "'='" + newValue + "'";
            }
        } catch (Exception e) {
            System.out.print("error" + e);
        }
        return null;
    }

}
