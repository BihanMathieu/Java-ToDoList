
import java.io.*;
import java.util.*;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        readCSV();


        Scanner sc = new Scanner(System.in);
        List<Task> myTasks = readCSV();
        boolean reset = true;

        System.out.println("Bienvenue dans votre ToDoList");

        while(reset){
            int result = choice(sc);

            if(result < 1 || result > 7 ){
                System.out.println("Veuillez choisir une entrer valide");
            }else if(result == 1){
                addTask(myTasks,sc);
            }else if(result == 2) {
                showTasks(myTasks);
            }else if(result == 3) {
                deleteTask(myTasks,sc);
            }else if(result == 4) {
                updateTask(myTasks,sc);
            }else if(result == 5){
                refreshTask(myTasks, sc);
            }else if(result == 6){
                reset = false;
                System.out.println("Au revoir");
            }
        }

    }

    private static List<Task> readCSV(){
        List<Task> myTasks = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File("list.csv"))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] items = line.split(",");
                Task task = new Task(items[0],Boolean.getBoolean(items[1]));
                myTasks.add(task);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return myTasks;
    }


    private static void writeCSV(List<Task> myTasks){
        try (FileWriter writer = new FileWriter("list.csv")) {
            for (Task item : myTasks) {
                writer.append(item.getValue()).append(",").append(check(item.isFinished())).append("\n");
            }
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *Selectionne le choix fait par l'utilisateur
     * @param sc entrer utilisateur
     * @return int
     */
    public static int choice(Scanner sc){
        lineBreak();
        System.out.println("Choisisser ce que vous voulez faire");
        System.out.println("1-Entrer une nouvelle tache");
        System.out.println("2-Voir les taches");
        System.out.println("3-Suprimer une taches");
        System.out.println("4-Modifier une tache");
        System.out.println("5-Actualiser une tache");
        System.out.println("6-Quitter");
        lineBreak();
        System.out.println("Veuillez saisir une option :");
        return Integer.parseInt(sc.nextLine());
    }

    /**
     * Ajoute une tache a la list et l'ecrit dans le fichier CSV
     * @param myTasks liste de tache
     * @param sc entrer utilisateur
     */
    public static void addTask(List<Task> myTasks, Scanner sc){
        lineBreak();
        System.out.println("Veuillez choisir une tache");
        Task task = new Task(sc.nextLine(),false);
        myTasks.add(task);
        writeCSV(myTasks);
    }

    /**
     * Affiche toutes les taches
     * @param myTasks liste de tache
     */
    public static void showTasks(List<Task> myTasks){
        lineBreak();
        for(int i = 0; i< myTasks.size(); i++){
            System.out.println((i+1)+ "-" + myTasks.get(i).getValue()+"  "+ myTasks.get(i).isFinished());
        }
    }

    /**
     * Suprime la tache selectionnée et réecrit le fichier CSV
     * @param myTasks liste de tache
     * @param sc entrer utilisateur
     */
    public static void deleteTask(List<Task> myTasks, Scanner sc){
        lineBreak();
        System.out.println("Veuillez choisir une tache a suprimer");
        showTasks(myTasks);
        int choice = Integer.parseInt(sc.nextLine());
        String choiceComfirmation = comfirmation(sc,myTasks,choice);
        if(Objects.equals(choiceComfirmation, "y") || Objects.equals(choiceComfirmation, "Y") ){
            myTasks.remove(choice-1);
            System.out.println("La tache a bien ete suprimer");
            writeCSV(myTasks);

        }
    }

    /**
     * Modifie la tache selectionnée et réecrit le fichier CSV
     * @param myTasks liste de tache
     * @param sc entrer utilisateur
     */
    public static void updateTask(List<Task> myTasks, Scanner sc){
        lineBreak();
        System.out.println("Quelles taches souhaitez vous modifier");
        showTasks(myTasks);
        int choice =Integer.parseInt(sc.nextLine());
        String choiceComfirmation = comfirmation(sc,myTasks,choice);
        if(Objects.equals(choiceComfirmation, "y") || Objects.equals(choiceComfirmation, "Y") ){
            System.out.println("Ecriver votre tache modifié");
            Task newTask = new Task(sc.nextLine(),false);
            myTasks.set(choice-1, newTask);
            System.out.println("La tache a bien ete modifier");
            writeCSV(myTasks);
        }
    }

    /**
     * Actualise la tache et réecrit le fichier CSV
     * @param myTasks liste de tache
     * @param sc entrer utilisateur
     */
    public static void refreshTask(List<Task> myTasks, Scanner sc){
        lineBreak();
        System.out.println("Quelles taches souhaitez vous actualiser");
        showTasks(myTasks);
        int choice =Integer.parseInt(sc.nextLine());
        String choiceComfirmation = comfirmation(sc,myTasks,choice);
        if(Objects.equals(choiceComfirmation, "y") || Objects.equals(choiceComfirmation, "Y") ){
            Task task = new Task(myTasks.get(choice-1).getValue(),true);
            myTasks.set(choice-1, task);
            System.out.println("La tache a bien ete modifier");
            writeCSV(myTasks);
        }
    }

    /**
     *Retourne le confirmation de l'utilisateur pour effectuer certaine opération
     * @param sc entrer utilisateur
     * @param myTasks liste de tache
     * @param choice entrer utilisateur
     * @return String
     */
    public static String comfirmation(Scanner sc, List<Task> myTasks, int choice ){
        lineBreak();
        System.out.println("Voulez vous vraiment effectuer cette opération Y/N ?");
        System.out.println(myTasks.get(choice-1).getValue());
        return sc.nextLine();
    }

    /**
     *traduis le bool de Tache pour plus de lisibilité dans le fichier CSV
     * @param bool bool de l'objet Tache
     * @return String
     */
    public static String check(boolean bool){
        return bool?"[X]":"[]";
    }

    /**
     * print un espace vide pour plus de lisibilité
     */
    public static void lineBreak(){
        System.out.println();
    }


}