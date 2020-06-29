package demo;

import entity.Course;
import entity.Instructor;
import entity.InstructorDetail;
import entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateCoursesAndStudents {
    public static void main(String args[]) {
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Student.class)
                .addAnnotatedClass(InstructorDetail.class)
                .buildSessionFactory();

        Session session = sessionFactory.getCurrentSession();

        try {
            System.out.println("Creating a Course object...");
            session.beginTransaction();
            Course course = new Course("This course is a new one");
            System.out.println("Saving the course...");
            session.save(course);

            Student student1 = new Student("Kristy", "Cook", "kristycook@gmail.com");
            Student student2 = new Student("Clint", "Geller", "clintgeller@gmail.com");

            course.addStudent(student1);
            course.addStudent(student2);

            System.out.println("Saving the students...");
            session.save(student1);
            session.save(student2);
            System.out.println("Saved students : " + course.getStudentList());

            session.getTransaction().commit();
            System.out.println("Done");
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            session.close();
            sessionFactory.close();
        }
    }
}
