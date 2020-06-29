package demo;

import entity.Course;
import entity.Instructor;
import entity.InstructorDetail;
import entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateCoursesForStudent {
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
            session.beginTransaction();
            System.out.println("Getting Student object...");
            int id = 1;
            Student student = session.get(Student.class, id);
            System.out.println("Student : " + student);
            System.out.println("Student courses : " + student.getCourseList());

            Course course1 = new Course("Your newly added course");
            Course course2 = new Course("Take another one");

            course1.addStudent(student);
            course2.addStudent(student);

            System.out.println("Saving new courses...");
            session.save(course1);
            session.save(course2);

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
