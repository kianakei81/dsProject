
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int T = scanner.nextInt();
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int p = scanner.nextInt();

        Lists s = new Lists();

        int[] answer = new int[T];
        for (int i = 0; i < T; i++) {
            answer[i] = -1;
        }

        String[] answer2 = new String[T];
        for (int i = 0; i < T; i++) {
            answer2[i] = null;
        }

        for (int i = 0; i < T; i++) {
            String f = scanner.next();
            switch (f) {
                case "ADDS":
                    int studentCode10 = scanner.nextInt();
                    String studentName10 = scanner.next();
                    Student AddStudent = new Student(studentCode10, studentName10);
                    s.students.ADD(AddStudent);
                    s.studentGrades.addFirst(AddStudent);

                    // add student.name to studentTree
                    StudentGrade sg = s.studentGrades.findStudentGrade(studentCode10);
                    s.studentTree.addStudent(studentName10, sg);
                    break;
                case "ADDC":
                    int courseCode10 = scanner.nextInt();
                    String courseName10 = scanner.next();
                    Course AddCourse = new Course(courseCode10, courseName10);
                    s.courses.ADD(AddCourse);
                    s.courseGrades.addFirst(AddCourse);

                    // add course.name to courseTree
                    courseGrade cg = s.courseGrades.findCourseGrade(courseCode10);
                    s.courseTree.addCourse(courseName10, cg);
                    break;
                case "ADDG":
                    int student_code = scanner.nextInt();
                    int course_code = scanner.nextInt();
                    int semester_code = scanner.nextInt();
                    double grade = scanner.nextDouble();

                    Student r = s.students.findStudent(student_code);
                    Course c = s.courses.findCourse(course_code);
                    Grade AddGrade = new Grade(grade, semester_code, r, c);

                    s.studentGrades.addS(r, AddGrade);
                    s.courseGrades.addC(c, AddGrade);
                    break;
                case "EDITS":
                    int student_code3 = scanner.nextInt();
                    String new_student_name = scanner.next();

                    // edit student.name in studentTree
                    Student v3 = s.students.findStudent(student_code3);
                    String oldStudentName = v3.getName();

                    s.students.EDIT(student_code3, new_student_name);
                    s.studentGrades.changeStudentName(student_code3, new_student_name);
                    s.courseGrades.changeStudentName(student_code3, new_student_name);

                    StudentGrade stg = s.studentGrades.findStudentGrade(student_code3);
                    s.studentTree.BST_EDIT_STUDENT(oldStudentName, new_student_name, stg);

                    break;
                case "EDITC":
                    int course_code4 = scanner.nextInt();
                    String new_course_name2 = scanner.next();

                    // edit course.name in courseTree
                    Course v4 = s.courses.findCourse(course_code4);
                    String oldCourseName = v4.getName();

                    s.courses.EDIT(course_code4, new_course_name2);
                    s.courseGrades.changeCourseName(course_code4, new_course_name2);
                    s.studentGrades.changeCourseName(course_code4, new_course_name2);

                    courseGrade csg = s.courseGrades.findCourseGrade(course_code4);
                    s.courseTree.BST_EDIT_COURSE(oldCourseName, new_course_name2, csg);

                    break;
                case "EDITG":
                    int student_code2 = scanner.nextInt();
                    int course_code2 = scanner.nextInt();
                    double newGrade = scanner.nextDouble();

                    s.studentGrades.editG(student_code2, course_code2, newGrade);
                    s.courseGrades.editGrade(student_code2, course_code2, newGrade);

                    // edit in tree
                    courseGrade csg2 = s.courseGrades.findCourseGrade(course_code2);
                    StudentGrade stg2 = s.studentGrades.findStudentGrade(student_code2);

                    Student sls = s.students.findStudent(student_code2);
                    Course clc = s.courses.findCourse(course_code2);

                    s.courseTree.BST_EDIT_COURSE(clc.getName(), clc.getName(), csg2);
                    s.studentTree.BST_EDIT_STUDENT(sls.getName(), sls.getName(), stg2);

                    break;
                case "DELETES":
                    int stCode = scanner.nextInt();

                    // delete from studentTree
                    Student v = s.students.findStudent(stCode);
                    s.studentTree.deleteStudent(v.getName());

                    s.students.DeleteS(stCode);
                    s.studentGrades.deleteStudent(stCode);
                    s.courseGrades.deleteStudentsGrades(stCode);

                    break;
                case "DELETEC":
                    int csCode = scanner.nextInt();

                    // delete from courseTree
                    Course v2 = s.courses.findCourse(csCode);
                    s.courseTree.deleteCourse(v2.getName());

                    s.courses.DeleteCs(csCode);
                    s.courseGrades.deleteCourse(csCode);
                    s.studentGrades.deleteCoursesGrades(csCode);

                    break;
                case "DELETEG":
                    int studentCode5 = scanner.nextInt();
                    int courseCode5 = scanner.nextInt();
                    s.studentGrades.deleteGrade(studentCode5, courseCode5);
                    s.courseGrades.deleteGrade2(studentCode5, courseCode5);
                    break;
                case "NUMBERC":
                    int studentCode = scanner.nextInt();
                    Student t = s.students.findStudent(studentCode);
                    answer[i] = s.studentGrades.courseNumber(t);
                    break;
                case "NUMBERS":
                    int courseCode = scanner.nextInt();
                    Course y = s.courses.findCourse(courseCode);
                    answer[i] = s.courseGrades.studentNumber(y);
                    break;
                case "SEARCHSN":
                    String sName = scanner.next();
                    StudentGrade data = s.studentTree.SEARCHSN(sName);

                    // print
                    System.out.println(data.student.getCode() + " " + data.student.getName() + " " + data.grades.getSize());
                    if (data.grades.getSize() != 0) {
                        node<Grade> g = data.grades.getFirst();
                        while (g != null) {
                            System.out.println(g.element.course.getCode() + " " + g.element.getSemester() + " " + g.element.getGrade());
                            g = g.next;
                        }
                    }

                    break;
                case "SEARCHCN":
                    String cName = scanner.next();
                    courseGrade data2 = s.courseTree.SEARCHCN(cName);

                    // print
                    System.out.println(data2.course.getCode() + " " + data2.course.getName() + " " + data2.grades.getSize());
                    if (data2.grades.getSize() != 0) {
                        node<Grade> g = data2.grades.getFirst();
                        while (g != null) {
                            System.out.println(g.element.student.getCode() + " " + g.element.getSemester() + " " + g.element.getGrade());
                            g = g.next;
                        }
                    }
                    break;
                case "SEARCHSC" :

                case "SEARCHCC":

                case "ISRELATIVE" :
                    int course_code_1 = scanner.nextInt();
                    int course_code_2 = scanner.nextInt();

                    Course c1 = s.courses.findCourse(course_code_1);
                    Course c2 = s.courses.findCourse(course_code_2);

                    int studentNumberCourse1 = s.courseGrades.studentNumber(c1);
                    int studentNumberCourse2 = s.courseGrades.studentNumber(c2);

                    int relatives = s.studentGrades.ISRELATIVE(c1, c2);

                    if(2*relatives > (studentNumberCourse1) && 2*relatives > (studentNumberCourse2)) {
                        answer2[i] = "yes";
                    } else {
                        answer2[i] = "no";
                    }
                    break;
            }
        }

        for (int i = 0; i < T; i++) {
            if (answer[i] != -1) {
                System.out.println(answer[i]);
            }
        }

//        for (int i = 0; i < T; i++) {
//            if (answer2[i] != null) {
//                System.out.println(answer2[i]);
//            }
//        }

    }


}

class Course {

    public String name;
    public Integer code;

    public Course(Integer code, String name) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}

class Grade {

    public double grade;
    public Integer semester;
    public Student student;
    public Course course;

    public Grade(double grade, Integer semester, Student student, Course course) {
        this.grade = grade;
        this.semester = semester;
        this.student = student;
        this.course = course;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public Integer getSemester() {
        return semester;
    }

    public void setSemester(Integer semester) {
        this.semester = semester;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}

class StudentGrade {

    public Student student;
    public gradeList grades;

    public StudentGrade(Student student, gradeList grades) {
        this.student = student;
        this.grades = grades;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public gradeList getGrades() {
        return grades;
    }

    public void setGrades(gradeList grades) {
        this.grades = grades;
    }

}

class courseGrade {

    public Course course;
    public gradeList grades;

    public courseGrade(Course course, gradeList grades) {
        this.course = course;
        this.grades = grades;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public gradeList getGrades() {
        return grades;
    }

    public void setGrades(gradeList grades) {
        this.grades = grades;
    }
}



class Student {
    public String name;
    public Integer code;

    public Student(Integer code, String name) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}


class Lists {

    public studentList students = new studentList(0, null);

    public courseList courses = new courseList(0, null);

    public LinkedList<Grade> grades = new gradeList(0, null);

    public studentGradeList studentGrades = new studentGradeList(0, null);

    public courseGradeList courseGrades = new courseGradeList(0, null);

    public studentTree studentTree = new studentTree(null);

    public courseTree courseTree = new courseTree(null);

}


class LinkedList<T> {

    public T obj;
    public Integer size;
    public node<T> first;

    public LinkedList(Integer size, node<T> first) {
        this.size = size;
        this.first = first;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public node<T> getFirst() {
        return first;
    }

    public void setFirst(node<T> first) {
        this.first = first;
    }

    public boolean isEmpty() {
        boolean b = false;
        if(first == null) {
            b = true;
        }
        return b;
    }


}

class courseGradeList extends LinkedList<courseGrade> {

    private courseGradeNode firstElement;
    private courseGradeNode lastElement;

    public courseGradeList(Integer size, node<courseGrade> first) {
        super(size, first);
    }


    public int studentNumber(Course c) {
        node<courseGrade> n = firstElement;
        while (n != null) {
            if(n.element.course.equals(c)) {
                return n.element.grades.size;
            }
            n = n.next;
        }
        return 0;
    }

    public courseGrade findCourseGrade(Integer code) {
        node<courseGrade> n = firstElement;
        while (n != null) {
            if(n.element.course.getCode().equals(code)) {
                return n.element;
            }
            n = n.next;
        }
        return null;
    }

    public void deleteStudentsGrades(Integer studentCode) {
        node<courseGrade> n = firstElement;

        while (n != null) {
            if (n.element.grades.getSize() != 0) {
                node<Grade> m = n.element.grades.getFirst();
                while (m != null) {
                    if (m.element.student.getCode().equals(studentCode)) {
                        if (n.element.grades.getSize() == 1) {
                            n.element.grades.setFirst(null);
                            n.element.grades.setSize(0);
                            break;
                        } else {
                            if(m.equals(n.element.grades.getFirst())) {
                                n.element.grades.setFirst(m.next);
                                n.element.grades.getFirst().setPrev(null);
                                n.element.grades.size--;
                                break;
                            } else if(m.next != null) {
                                m.next.prev = m.prev;
                                m.prev.next = m.next;
                                n.element.grades.size--;
                                break;
                            } else {
                                m.prev.setNext(null);
                                n.element.grades.size--;
                                break;
                            }
                        }
                    }
                    m = m.next;
                }
            }
            n = n.next;
        }

    }

    public void deleteCourse(Integer code) {
        node<courseGrade> n = firstElement;
        while (n != null) {
            if(n.element.course.getCode().equals(code)) {
                if (this.size == 1) {
                    firstElement = null;
                    lastElement = null;
                    this.setFirst(null);
                    this.setSize(0);
                    break;
                } else {
                    if (n.equals(firstElement)) {
                        firstElement = (courseGradeNode) n.next;
                        this.setFirst(firstElement);
                        firstElement.setPrev(null);
                        size--;
                        break;
                    } else if (n.next != null) {
                        n.next.prev = n.prev;
                        n.prev.next = n.next;
                        size--;
                        break;
                    } else {
                        lastElement = (courseGradeNode) n.prev;
                        lastElement.setNext(null);
                        size--;
                        break;
                    }
                }
            }
            n = n.next;
        }
    }

    public void deleteGrade2(Integer studentCode, Integer courseCode) {
        node<courseGrade> n = firstElement;
        while (n != null) {
            if(n.element.course.getCode().equals(courseCode)) {
                node<Grade> m = n.element.grades.getFirst();
                while (m != null) {
                    if (m.element.student.getCode().equals(studentCode)) {
                        // delete the grade
                        if(n.element.grades.size == 1) {
                            n.element.grades.setFirst(null);
                            n.element.grades.setSize(0);
                        } else {
                            if (m.equals(n.element.grades.getFirst())) {
                                n.element.grades.setFirst(m.next);
                                n.element.grades.getFirst().setPrev(null);
                                n.element.grades.size--;
                                break;
                            } else if (m.next != null) {
                                m.next.prev = m.prev;
                                m.prev.next = m.next;
                                n.element.grades.size--;
                                break;
                            } else {
                                m.prev.setNext(null);
                                n.element.grades.size--;
                                break;
                            }
                        }
                    }
                    m = m.next;
                }
            }
            n = n.next;
        }
    }

    public void addC(Course c, Grade g) {
        node<courseGrade> n = firstElement;
        while(n != null) {
            if(n.element.course.equals(c)) {
                n.element.grades.ADD(g);
                break;
            }
            n = n.next;
        }
    }


    public void changeCourseName(Integer code, String newName) {
        node<courseGrade> n = firstElement;
        while (n != null) {
            if(n.element.course.getCode().equals(code)) {
                n.element.course.setName(newName);
                if(n.element.grades.getSize() != 0) {
                    node<Grade> m = n.element.grades.getFirst();
                    while (m != null) {
                        m.element.course.setName(newName);
                        m = m.next;
                    }
                }
                break;
            }
            n = n.next;
        }
    }

    public void addFirst(Course s) {
        gradeList k = new gradeList(0,null);
        courseGrade j = new courseGrade(s, k);
        if(isEmpty()) {
            firstElement = new courseGradeNode(j, null, null);
            lastElement = firstElement;
            this.setFirst(firstElement);
        } else {
            courseGradeNode newNode = new courseGradeNode(j, lastElement, null);
            lastElement.setNext(newNode);
            lastElement = newNode;
        }
        size++;
    }

    public void changeStudentName(Integer code, String newName) {
        node<courseGrade> n = firstElement;
        while (n != null) {
            if (n.element.grades.getSize() != null) {
                node<Grade> m = n.element.grades.getFirst();
                while (m != null) {
                    if(m.element.student.getCode().equals(code)) {
                        m.element.student.setName(newName);
                    }
                    m = m.next;
                }
            }
            n = n.next;
        }
    }

    public void editGrade(Integer studentCode, Integer courseCode, double newGrade) {
        node<courseGrade> n = firstElement;
        while (n != null) {
            if(n.element.course.getCode().equals(courseCode)) {
                node<Grade> m = n.element.grades.getFirst();
                while (m != null) {
                    if(m.element.student.getCode().equals(studentCode)) {
                        m.element.setGrade(newGrade);
                    }
                    m = m.next;
                }
            }
            n = n.next;
        }
    }

}

class studentGradeList extends LinkedList<StudentGrade> {

    private studentGradeNode firstElement;
    private studentGradeNode lastElement;

    public studentGradeList(Integer size, node<StudentGrade> first) {
        super(size, first);
    }

    public void addS(Student s, Grade g) {
        node<StudentGrade> n = firstElement;
        while (n != null) {
            if(n.element.student.equals(s)) {
                n.element.grades.ADD(g);
            }
            n = n.next;
        }
    }

    public void changeCourseName(Integer code, String newName) {
        node<StudentGrade> n = firstElement;
        while (n != null) {
            if (n.element.grades.getSize() != null) {
                node<Grade> m = n.element.grades.getFirst();
                while (m != null) {
                    if(m.element.course.getCode().equals(code)) {
                        m.element.course.setName(newName);
                    }
                    m = m.next;
                }
            }
            n = n.next;
        }
    }

    public int ISRELATIVE(Course c1, Course c2) {
        int isC1 = 0;
        int isC2 = 0;
        int answer = 0;
        node<StudentGrade> n = firstElement;
        while (n != null) {
            if (n.element.grades.getSize() >= 2) {
                node<Grade> m = n.element.grades.getFirst();
                while (m != null) {
                    if (m.element.getCourse().equals(c1)) {
                        isC1++;
                    }
                    if (m.element.getCourse().equals(c2)) {
                        isC2++;
                    }
                    m = m.next;
                }
                if(isC1 > 0 && isC2 > 0) {
                    answer++;
                }
                isC1 = 0;
                isC2 = 0;
            }
            n = n.next;
        }
        return answer;
    }

    public void deleteGrade(Integer studentCode, Integer courseCode) {
        node<StudentGrade> n = firstElement;
        while (n != null) {
            if(n.element.student.getCode().equals(studentCode)) {
                node<Grade> m = n.element.grades.getFirst();
                while (m != null) {
                    if (m.element.course.getCode().equals(courseCode)) {
                        // delete the grade
                        if(n.element.grades.size == 1) {
                            n.element.grades.setFirst(null);
                            n.element.grades.size = 0;
                        } else {
                            if (m.equals(n.element.grades.getFirst())) {
                                n.element.grades.setFirst(m.next);
                                n.element.grades.getFirst().setPrev(null);
                                n.element.grades.size--;
                                break;
                            } else if (m.next != null) {
                                m.next.prev = m.prev;
                                m.prev.next = m.next;
                                n.element.grades.size--;
                                break;
                            } else {
                                m.prev.setNext(null);
                                n.element.grades.size--;
                                break;
                            }
                        }

                    }
                    m = m.next;
                }
            }
            n = n.next;
        }
    }

    public void deleteCoursesGrades(Integer code) {
        node<StudentGrade> n = firstElement;

        while (n != null) {
            if (n.element.grades.getSize() != 0) {
                node<Grade> m = n.element.grades.getFirst();
                while (m != null) {
                    if (m.element.course.getCode().equals(code)) {
                        if (n.element.grades.getSize() == 1) {
                            n.element.grades.setFirst(null);
                            n.element.grades.setSize(0);
                            break;
                        } else {
                            if(m.equals(n.element.grades.getFirst())) {
                                n.element.grades.setFirst(m.next);
                                n.element.grades.getFirst().setPrev(null);
                                n.element.grades.size--;
                                break;
                            } else if(m.next != null) {
                                m.next.prev = m.prev;
                                m.prev.next = m.next;
                                n.element.grades.size--;
                                break;
                            } else {
                                m.prev.setNext(null);
                                n.element.grades.size--;
                                break;
                            }
                        }
                    }
                    m = m.next;
                }
            }
            n = n.next;
        }
    }

    public int courseNumber(Student s) {
        node<StudentGrade> n = firstElement;
        while (n != null) {
            if(n.element.student.equals(s)) {
                return n.element.grades.size;
            }
            n = n.next;
        }
        return 0;
    }

    public StudentGrade findStudentGrade(Integer code) {
        node<StudentGrade> n = firstElement;
        while (n != null) {
            if(n.element.student.getCode().equals(code)) {
                return n.element;
            }
            n = n.next;
        }
        return null;
    }

    public void changeStudentName(Integer code, String newName) {
        node<StudentGrade> n = firstElement;
        while (n != null) {
            if(n.element.student.getCode().equals(code)) {
                n.element.student.setName(newName);
                if(n.element.grades.getSize() != 0) {
                    node<Grade> m = n.element.grades.getFirst();
                    while (m != null) {
                        m.element.student.setName(newName);
                        m = m.next;
                    }
                }
                break;
            }
            n = n.next;
        }
    }

    public void addFirst(Student s) {
        gradeList k = new gradeList(0,null);
        StudentGrade j = new StudentGrade(s, k);
        if(isEmpty()) {
            firstElement = new studentGradeNode(j, null, null);
            lastElement = firstElement;
            this.setFirst(firstElement);
        } else {
            studentGradeNode newNode = new studentGradeNode(j, lastElement, null);
            lastElement.setNext(newNode);
            lastElement = newNode;
        }
        size++;
    }

    public void deleteStudent(Integer studentCode) {
        node<StudentGrade> n = firstElement;
        while (n != null) {
            if(n.element.student.getCode().equals(studentCode)) {
                if (this.size == 1) {
                    firstElement = null;
                    lastElement = null;
                    this.setFirst(null);
                    this.setSize(0);
                    break;
                } else {
                    if (n.equals(firstElement)) {
                        firstElement = (studentGradeNode) n.next;
                        this.setFirst(firstElement);
                        firstElement.setPrev(null);
                        size--;
                        break;
                    } else if (n.next != null) {
                        n.next.prev = n.prev;
                        n.prev.next = n.next;
                        size--;
                        break;
                    } else {
                        lastElement = (studentGradeNode) n.prev;
                        lastElement.setNext(null);
                        size--;
                        break;
                    }
                }
            }
            n = n.next;
        }
    }

    public void editG(Integer studentCode, Integer courseCode, double newGrade) {
        node<StudentGrade> n = firstElement;
        while (n != null) {
            if(n.element.student.getCode().equals(studentCode)) {
                node<Grade> m = n.element.grades.getFirst();
                while (m != null) {
                    if(m.element.course.getCode().equals(courseCode)) {
                        m.element.setGrade(newGrade);
                    }
                    m = m.next;
                }
            }
            n = n.next;
        }
    }

}

class studentList extends LinkedList<Student> {

    private studentNode firstElement;
    private studentNode lastElement;

    public studentList(Integer size, node<Student> first) {
        super(size, first);
    }

    public Student findStudent(Integer code) {
        node<Student> n = firstElement;
        while (n != null) {
            if(Objects.equals(n.element.code, code)) {
                return n.element;
            }
            n = n.next;
        }
        return null;
    }


    public void ADD(Student student) {
        if(isEmpty()) {
            firstElement = new studentNode(student, null, null);
            lastElement = firstElement;
            this.setFirst(firstElement);
        } else {
            studentNode node = new studentNode(student, lastElement, null);
            lastElement.setNext(node);
            lastElement = node;
        }
        size++;
    }

    public void EDIT(Integer code, String newName) {
        node<Student> n = firstElement;
        while(n != null) {
            if(n.element.getCode().equals(code)) {
                n.element.setName(newName);
                break;
            }
            n = n.next;
        }
    }

    public void DeleteS(Integer code) {
        node<Student> n = firstElement;
        while (n != null) {
            if(n.element.getCode().equals(code)) {
                if (this.size == 1) {
                    firstElement = null;
                    lastElement = null;
                    this.setFirst(null);
                    this.setSize(0);
                    break;
                } else {
                    if (n.equals(firstElement)) {
                        firstElement = (studentNode) n.next;
                        this.setFirst(firstElement);
                        firstElement.setPrev(null);
                        size--;
                        break;
                    } else if (n.next != null) {
                        n.next.prev = n.prev;
                        n.prev.next = n.next;
                        size--;
                        break;
                    } else {
                        lastElement = (studentNode) n.prev;
                        lastElement.setNext(null);
                        size--;
                        break;
                    }
                }
            }
            n = n.next;
        }
    }

}

class courseList extends LinkedList<Course> {

    private courseNode firstElement;
    private courseNode lastElement;

    public courseList(Integer size, node<Course> first) {
        super(size, first);
    }

    public Course findCourse(Integer code) {
        node<Course> n = firstElement;
        while (n != null) {
            if(Objects.equals(n.element.code, code)) {
                return n.element;
            }
            n = n.next;
        }
        return null;
    }


    public void ADD(Course course) {
        if(isEmpty()) {
            firstElement = new courseNode(course, null, null);
            lastElement = firstElement;
            this.setFirst(firstElement);
        } else {
            courseNode node = new courseNode(course, lastElement, null);
            lastElement.setNext(node);
            lastElement = node;
        }
        size++;
    }


    public void EDIT(Integer code, String newName) {
        node<Course> n = firstElement;
        while(n != null) {
            if(Objects.equals(n.element.code, code)) {
                n.element.setName(newName);
            }
            n = n.next;
        }
    }


    public void DeleteCs(Integer code) {
        node<Course> n = firstElement;
        while (n != null) {
            if(n.element.getCode().equals(code)) {
                if (this.size == 1) {
                    firstElement = null;
                    lastElement = null;
                    this.setFirst(null);
                    this.setSize(0);
                    break;
                } else {
                    if (n.equals(firstElement)) {
                        firstElement = (courseNode) n.next;
                        this.setFirst(firstElement);
                        firstElement.setPrev(null);
                        size--;
                        break;
                    } else if (n.next != null) {
                        n.next.prev = n.prev;
                        n.prev.next = n.next;
                        size--;
                        break;
                    } else {
                        lastElement = (courseNode) n.prev;
                        lastElement.setNext(null);
                        size--;
                        break;
                    }
                }
            }
            n = n.next;
        }
    }

}

class gradeList extends LinkedList<Grade> {

    private gradeNode firstElement;
    private gradeNode lastElement;

    public gradeList(Integer size, node<Grade> first) {
        super(size, first);
    }



    public void ADD(Grade grade) {
        if(isEmpty()) {
            firstElement = new gradeNode(grade, null, null);
            lastElement = firstElement;
            this.setFirst(firstElement);
        } else {
            gradeNode node = new gradeNode(grade, lastElement, null);
            lastElement.setNext(node);
            lastElement = node;
        }
        size++;
    }

}

class node<T> {

    public T element;
    public node<T> next;
    public node<T> prev;

    public node(T element, node<T> prev, node<T> next) {
        this.element = element;
        this.prev = prev;
        this.next = next;
    }

    public T getElement() {
        return element;
    }

    public void setElement(T element) {
        this.element = element;
    }

    public node<T> getNext() {
        return next;
    }

    public void setNext(node<T> next) {
        this.next = next;
    }

    public node<T> getPrev() {
        return prev;
    }

    public void setPrev(node<T> prev) {
        this.prev = prev;
    }
}

class studentNode extends node<Student> {

    public studentNode(Student element, node<Student> prev, node<Student> next) {
        super(element, prev, next);
    }
}

class gradeNode extends node<Grade> {

    public gradeNode(Grade element, node<Grade> prev, node<Grade> next) {
        super(element, prev, next);
    }
}

class courseNode extends node<Course> {

    public courseNode(Course element, node<Course> prev, node<Course> next) {
        super(element, prev, next);
    }
}

class courseGradeNode extends node<courseGrade> {

    public courseGradeNode(courseGrade element, node<courseGrade> prev, node<courseGrade> next) {
        super(element, prev, next);
    }
}

class studentGradeNode extends node<StudentGrade> {

    public studentGradeNode(StudentGrade element, node<StudentGrade> prev, node<StudentGrade> next) {
        super(element, prev, next);
    }
}


class binarySearchTree<T> {

    public treeNode<T> root;

    public binarySearchTree(treeNode<T> root) {
        this.root = root;
    }

    public treeNode<T> getRoot() {
        return root;
    }

    public void setRoot(treeNode<T> root) {
        this.root = root;
    }
}

class treeNode<T> {

    public String key;
    public treeNode<T> parent;
    public treeNode<T> left;
    public treeNode<T> right;
    public T data;

    public treeNode(String key, treeNode<T> parent, treeNode<T> left, treeNode<T> right, T data) {
        this.key = key;
        this.parent = parent;
        this.left = left;
        this.right = right;
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public treeNode<T> getParent() {
        return parent;
    }

    public void setParent(treeNode<T> parent) {
        this.parent = parent;
    }

    public treeNode<T> getLeft() {
        return left;
    }

    public void setLeft(treeNode<T> left) {
        this.left = left;
    }

    public treeNode<T> getRight() {
        return right;
    }

    public void setRight(treeNode<T> right) {
        this.right = right;
    }
}

class studentTree extends binarySearchTree<StudentGrade> {

    public studentTree(treeNode<StudentGrade> root) {
        super(root);
    }

    public void addStudent(String x, StudentGrade studentGrade) {
        treeNode<StudentGrade> n = new treeNode<StudentGrade>(x, null, null, null, studentGrade);
        treeNode<StudentGrade> prep = null;
        treeNode<StudentGrade> p = this.getRoot();
        while (p != null) {
            prep = p;
            if (x.compareTo(p.getKey()) < 0) {
                p = p.getLeft();
            } else if (x.compareTo(p.getKey()) > 0) {
                p = p.getRight();
            }
        }
        n.setParent(prep);
        if (prep == null) {
            this.setRoot(n);
        } else {
            if (x.compareTo(prep.getKey()) < 0) {
                prep.setLeft(n);
            } else {
                prep.setRight(n);
            }
        }
    }


    public void deleteStudent(String name) {
        treeNode<StudentGrade> z = BST_SEARCH(this.getRoot(), name);
        treeNode<StudentGrade> y = null;
        treeNode<StudentGrade> x = null;

        if (z.getLeft() == null || z.getRight() == null) {
            y = z;
        } else {
            y = BST_SUCCESSOR(z);
        }

        if (y.getLeft() != null) {
            x = y.getLeft();
        } else {
            x = y.getRight();
        }

        if (x != null) {
            x.setParent(y.getParent());
        }

        if (y.getParent() == null) {
            this.setRoot(x);
        } else {
            if (y == y.getParent().getLeft()) {
                y.getParent().setLeft(x);
            } else {
                y.getParent().setRight(x);
            }
        }

        if (y != z) {
            z.setKey(y.getKey());
        }
    }

    public treeNode<StudentGrade> BST_SEARCH(treeNode<StudentGrade> r, String x) {
        while (r != null && !x.equals(r.getKey())) {
            if (x.compareTo(r.getKey()) < 0) {
                r = r.getLeft();
            } else {
                r = r.getRight();
            }
        }
        return r;
    }

    public StudentGrade SEARCHSN(String x) {
        return SEARCHSN2(this.getRoot(), x);
    }

    public StudentGrade SEARCHSN2(treeNode<StudentGrade> r, String x) {
        while (r != null && !x.equals(r.getKey())) {
            if (x.compareTo(r.getKey()) < 0) {
                r = r.getLeft();
            } else {
                r = r.getRight();
            }
        }
        return r.getData();
    }

    public treeNode<StudentGrade> BST_SUCCESSOR(treeNode<StudentGrade> r) {
        if (r.getRight() != null) {
            return BST_MINIMUM(r.getRight());
        }
        treeNode<StudentGrade> y = r.getParent();
        while (y != null && r == y.getRight()) {
            r = y;
            y = y.getParent();
        }
        return y;
    }

    public treeNode<StudentGrade> BST_MINIMUM(treeNode<StudentGrade> r) {
        while (r.getLeft() != null) {
            r = r.getLeft();
        }
        return r;
    }

    public void BST_EDIT_STUDENT(String oldName, String newName, StudentGrade stg) {
        treeNode<StudentGrade> z = BST_SEARCH(this.getRoot(), oldName);

        // delete
        treeNode<StudentGrade> y = null;
        treeNode<StudentGrade> x = null;

        if (z.getLeft() == null || z.getRight() == null) {
            y = z;
        } else {
            y = BST_SUCCESSOR(z);
        }

        if (y.getLeft() != null) {
            x = y.getLeft();
        } else {
            x = y.getRight();
        }

        if (x != null) {
            x.setParent(y.getParent());
        }

        if (y.getParent() == null) {
            this.setRoot(x);
        } else {
            if (y == y.getParent().getLeft()) {
                y.getParent().setLeft(x);
            } else {
                y.getParent().setRight(x);
            }
        }

        if (y != z) {
            z.setKey(y.getKey());
        }

        // insert
        treeNode<StudentGrade> n = new treeNode<StudentGrade>(newName, null, null, null, stg);
        treeNode<StudentGrade> prep = null;
        treeNode<StudentGrade> p = this.getRoot();
        while (p != null) {
            prep = p;
            if (newName.compareTo(p.getKey()) < 0) {
                p = p.getLeft();
            } else if (newName.compareTo(p.getKey()) > 0) {
                p = p.getRight();
            }
        }
        n.setParent(prep);
        if (prep == null) {
            this.setRoot(n);
        } else {
            if (newName.compareTo(prep.getKey()) < 0) {
                prep.setLeft(n);
            } else {
                prep.setRight(n);
            }
        }

    }

}

class courseTree extends binarySearchTree<courseGrade> {

    public courseTree(treeNode<courseGrade> root) {
        super(root);
    }

    public void addCourse(String x, courseGrade cg) {
        treeNode<courseGrade> n = new treeNode<courseGrade>(x, null, null, null, cg);
        treeNode<courseGrade> prep = null;
        treeNode<courseGrade> p = this.getRoot();
        while (p != null) {
            prep = p;
            if (x.compareTo(p.getKey()) < 0) {
                p = p.getLeft();
            } else if (x.compareTo(p.getKey()) > 0) {
                p = p.getRight();
            }
        }
        n.setParent(prep);
        if (prep == null) {
            this.setRoot(n);
        } else {
            if (x.compareTo(prep.getKey()) < 0) {
                prep.setLeft(n);
            } else {
                prep.setRight(n);
            }
        }
    }

    public void deleteCourse(String name) {

        treeNode<courseGrade> z = BST_SEARCH(this.getRoot(), name);
        treeNode<courseGrade> y = null;
        treeNode<courseGrade> x = null;

        if (z.getLeft() == null || z.getRight() == null) {
            y = z;
        } else {
            y = BST_SUCCESSOR(z);
        }

        if (y.getLeft() != null) {
            x = y.getLeft();
        } else {
            x = y.getRight();
        }

        if (x != null) {
            x.setParent(y.getParent());
        }

        if (y.getParent() == null) {
            this.setRoot(x);
        } else {
            if (y == y.getParent().getLeft()) {
                y.getParent().setLeft(x);
            } else {
                y.getParent().setRight(x);
            }
        }

        if (y != z) {
            z.setKey(y.getKey());
        }

    }

    public courseGrade SEARCHCN(String x) {
        return SEARCHSN2(this.getRoot(), x);
    }

    public courseGrade SEARCHSN2(treeNode<courseGrade> r, String x) {
        while (r != null && !x.equals(r.getKey())) {
            if (x.compareTo(r.getKey()) < 0) {
                r = r.getLeft();
            } else {
                r = r.getRight();
            }
        }
        return r.getData();
    }


    public treeNode<courseGrade> BST_SEARCH(treeNode<courseGrade> r, String x) {
        while (r != null && !x.equals(r.getKey())) {
            if (x.compareTo(r.getKey()) < 0) {
                r = r.getLeft();
            } else {
                r = r.getRight();
            }
        }
        return r;
    }

    public treeNode<courseGrade> BST_SUCCESSOR(treeNode<courseGrade> r) {
        if (r.getRight() != null) {
            return BST_MINIMUM(r.getRight());
        }
        treeNode<courseGrade> y = r.getParent();
        while (y != null && r == y.getRight()) {
            r = y;
            y = y.getParent();
        }
        return y;
    }

    public treeNode<courseGrade> BST_MINIMUM(treeNode<courseGrade> r) {
        while (r.getLeft() != null) {
            r = r.getLeft();
        }
        return r;
    }

    public void BST_EDIT_COURSE(String oldName, String newName, courseGrade cg) {
        treeNode<courseGrade> z = BST_SEARCH(this.getRoot(), oldName);

        // delete
        treeNode<courseGrade> y = null;
        treeNode<courseGrade> x = null;

        if (z.getLeft() == null || z.getRight() == null) {
            y = z;
        } else {
            y = BST_SUCCESSOR(z);
        }

        if (y.getLeft() != null) {
            x = y.getLeft();
        } else {
            x = y.getRight();
        }

        if (x != null) {
            x.setParent(y.getParent());
        }

        if (y.getParent() == null) {
            this.setRoot(x);
        } else {
            if (y == y.getParent().getLeft()) {
                y.getParent().setLeft(x);
            } else {
                y.getParent().setRight(x);
            }
        }

        if (y != z) {
            z.setKey(y.getKey());
        }

        // insert
        treeNode<courseGrade> n = new treeNode<courseGrade>(newName, null, null, null, cg);
        treeNode<courseGrade> prep = null;
        treeNode<courseGrade> p = this.getRoot();
        while (p != null) {
            prep = p;
            if (newName.compareTo(p.getKey()) < 0) {
                p = p.getLeft();
            } else if (newName.compareTo(p.getKey()) > 0) {
                p = p.getRight();
            }
        }
        n.setParent(prep);
        if (prep == null) {
            this.setRoot(n);
        } else {
            if (newName.compareTo(prep.getKey()) < 0) {
                prep.setLeft(n);
            } else {
                prep.setRight(n);
            }
        }
    }

}

