
package Models;

public class Trainers {
    private int trainerId;
    private String trainerName;
    private String mobile;
    private String age;

    public Trainers(int trainerId, String trainerName, String mobile, String age) {
        this.trainerId = trainerId;
        this.trainerName = trainerName;
        this.mobile = mobile;
        this.age = age;
    }

  
    
    

    public int getTrainerId() {
        return trainerId;
    }

    public String getTrainerName() {
        return trainerName;
    }

    public String getMobile() {
        return mobile;
    }

    public String getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Trainers{" + "trainerId=" + trainerId + ", trainerName=" + trainerName + ", mobile=" + mobile + ", age=" + age + '}';
    }
    
    
}
