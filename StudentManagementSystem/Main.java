import java.util.*;

class Student {
    private String id, name;
    private int marks;

    public Student(String id, String name, int marks) {
        this.id = id;
        this.name = name;
        this.marks = marks;
    }

    public String getId() {
        return id;

    }

    public int getMarks() {
        return marks;
    }

    public String getrole() {
        return "Undergraduate";
    }

    @Override
    public String toString() {
        return id + " - " + name + " (" + marks + ") " + getrole();
    }

}

class GraduateStudent extends Student { 
    private String area;

    public GraduateStudent(String id, String name, int marks, String area) {
        super(id, name, marks);
        this.area = area;
    }

    @Override
    public String getrole() {
        return "Graduate (" + area + ")";
    }


}
    
    class HonorStudent extends Student {
        private int bonusMarks;

        public HonorStudent(String id, String name, int marks, int bonusMarks) {
            super(id, name, marks);
            this.bonusMarks = bonusMarks;
        }

        @Override
        public int getMarks() {
            return super.getMarks() + bonusMarks;
        }

        @Override
        public String getrole() {
            return "Honor Student";
        }

        public String getTopper(List<Student> students) {
            int maxMarks = 0;
            String topperName = "";
            for (Student s : students) {
                if (s.getMarks() > maxMarks) {
                    maxMarks = s.getMarks();
                    topperName = s.toString();
                }
            }
        
            return topperName;
        }    
    }

    
    

    class Repository<T> {
        private  Map<String, T> data = new HashMap<>();

        public void save(String id, T obj) { data.put(id, obj); }
        public T find(String id) { return data.get(id); }
        public void delete(String id) { data.remove(id); }
    }

public class Main {
     public static void main(String[] args) {
        List<Student> list = new ArrayList<>();
        list.add(new Student("S1", "Imran", 75));
        list.add(new Student("S2", "Ayesha", 80));
        list.add(new Student("S3", "Tarak", 78));

        list.add(new GraduateStudent("G1", "Zara", 88, "Computer Science"));

        Repository<Student> repo = new Repository<>();
        for (Student s : list) {
            repo.save(s.getId(), s);
        }

        System.out.println("All Students:");
        list.forEach(System.out :: println);

        System.out.println("\nFind Student with ID S2:");
        Student s = repo.find("S2");
        System.out.println(s!= null ? s : "Student not found");

        Iterator<Student> it = list.iterator();

        while(it.hasNext()) {
            Student st = it.next();
            if(st.getMarks() < 80) {
                it.remove();
                repo.delete(st.getId());
            }
        }

        System.out.println("\nStudents after removing those with marks < 80:");
        list.forEach(System.out :: println);
        
        // get topper
        HonorStudent hs = new HonorStudent("H1", "Lina", 85, 10);
        String topper = hs.getTopper(list);
        System.out.println("\nTopper: " + topper);
}
}
