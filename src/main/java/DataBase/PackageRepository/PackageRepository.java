package DataBase.PackageRepository;

import Models.Packages;
import java.util.ArrayList;

abstract public class PackageRepository {

    abstract public ArrayList<Packages> getAllPackages();

    abstract public Packages getPackageById(int id);

    abstract public Packages getPackageByName(String PackageName);

    abstract public Packages addPackage(Packages newPackage);

    abstract public Packages updatePackage(int id, Packages newPackageInfo);

    abstract public Packages removePackage(int id);

    abstract public String getUpdatedValue(boolean condition, String newValue, String columnName);

}
