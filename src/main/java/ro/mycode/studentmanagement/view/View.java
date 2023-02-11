package ro.mycode.studentmanagement.view;

import org.springframework.stereotype.Component;
import ro.mycode.studentmanagement.exceptii.ExceptieStudentDBEmpty;
import ro.mycode.studentmanagement.exceptii.ExceptieStudentExisten;
import ro.mycode.studentmanagement.exceptii.ExceptieStudentNeexistent;
import ro.mycode.studentmanagement.model.Student;
import ro.mycode.studentmanagement.service.StudentService;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Component
public class View {

    private StudentService studentService;
    private Scanner scanner;

    public View(StudentService studentService){
        this.studentService = studentService;
        scanner = new Scanner(System.in);
    }

    public void meniu() {
        System.out.println("Apasa 1 pentru a afisa toti studentii");
        System.out.println("Apasa 2 pentru a adauga un student");
        System.out.println("Apasa 3 pentru a sterge un student");
        System.out.println("Apasa 4 pentru a updata un student");
        System.out.println("Apasa 5 pentru a afisa studentii in ordine alfabetica ascendenta");
        System.out.println("Apasa 6 pentru a afisa studentii in ordine alfabetica descendenta");


    }
    public void meniuUpdate(){
        System.out.println("Apasa 1 pentru a updata emailul");
        System.out.println("Apasa 2 pentru a updata numele");
        System.out.println("Apasa 3 pentru a updata parola");
        System.out.println("Apasa 0 pentru a te intoarce la meniul principal");
    }

    public void play() {
        boolean run = true;
        meniu();
        while (run) {
            int buton = Integer.parseInt(scanner.nextLine());
            switch (buton) {
                case 1:
                    afisare();
                    break;
                case 2:
                    add();
                    break;
                case 3:
                    remove();
                    break;
                case 4:
                    playUpdate();
                    break;
                case 5:
                    ordineNumeAsc();
                    break;
                case 6:
                    ordineNumeDesc();
                    break;
                default:
                    run = false;
                    break;
            }
        }
    }
    public void playUpdate(){
        boolean run = true;
        meniuUpdate();
        while (run){
            int buton = Integer.parseInt(scanner.nextLine());
            switch (buton){
                case 1:
                    updateEmail();
                    break;
                case 2:
                    updateNume();
                    break;
                case 3:
                    updateParola();
                    break;
                case 0:
                    play();
                    run = false;
                    break;
                default:
                    meniuUpdate();
                    break;
            }
        }
    }
    public void afisare(){
        try{
            List<Student> students = studentService.gelAllStudents();
            for (Student student: students){
                System.out.println(student);
            }
        }catch (ExceptieStudentDBEmpty e){
            System.err.println(e.getMessage());
        }
    }

    public void add(){
        System.out.println("Intordu numele:");
        String nume = scanner.nextLine();
        System.out.println("Intodu prenumele:");
        String prenume = scanner.nextLine();
        System.out.println("Introdu varsta:");
        int varsta = Integer.parseInt(scanner.nextLine());
        System.out.println("Intordu adresa de email:");
        String email = scanner.nextLine();
        System.out.println("Introdu parola:");
        String parola = scanner.nextLine();
        try{
            Student student = Student.builder().email(email).nume(nume).parola(parola).prenume(prenume).varsta(varsta).build();
            studentService.addStudent(student);
            System.out.println("Ai agaugat un student cu succes!");
        }catch (ExceptieStudentExisten e){
            System.err.println(e.getMessage());
        }
    }
    public void remove(){
        System.out.println("Introdu emailul studentului:");
        String email = scanner.nextLine();
        try{
            Optional<Student> student = studentService.getStudentByEmail(email);
            studentService.removeStudent(student.get().getEmail());
        }catch (ExceptieStudentNeexistent e){
            System.err.println(e.getMessage());
        }
    }
    public void updateEmail(){
        System.out.println("Intordu emailul studentului:");
        String email = scanner.nextLine();
        System.out.println("Introdu noul email:");
        String emailNou = scanner.nextLine();
        try {
            studentService.updateStudentEmail(emailNou, email);
            System.out.println("Ai updata atributul cu succes!!");
        }catch (ExceptieStudentNeexistent e){
            System.err.println(e.getMessage());
        }
    }

    public void updateNume(){
        System.out.println("Intordu emailul studentului:");
        String email = scanner.nextLine();
        System.out.println("Introdu noul nume:");
        String numeNou = scanner.nextLine();
        try {
            studentService.updateStudentEmail(numeNou, email);
            System.out.println("Ai updata atributul cu succes!!");
        }catch (ExceptieStudentNeexistent e){
            System.err.println(e.getMessage());
        }
    }

    public void updateParola(){
        System.out.println("Intordu emailul studentului:");
        String email = scanner.nextLine();
        System.out.println("Introdu noua parola:");
        String parolaNoua = scanner.nextLine();
        try {
            studentService.updateStudentEmail(parolaNoua, email);
            System.out.println("Ai updata atributul cu succes!!");
        }catch (ExceptieStudentNeexistent e){
            System.err.println(e.getMessage());
        }
    }

    public void ordineNumeAsc(){
        try{
            List<Student> students = studentService.ordineNumeAsc();
            for (Student student: students){
                System.out.println(student);
            }
        }catch (ExceptieStudentDBEmpty e){
            System.err.println(e.getMessage());
        }
    }

    public void ordineNumeDesc(){
        try{
            List<Student> students = studentService.ordineNumeDesc();
            for (Student student: students){
                System.out.println(student);
            }
        }catch (ExceptieStudentDBEmpty e){
            System.err.println(e.getMessage());
        }
    }
}
