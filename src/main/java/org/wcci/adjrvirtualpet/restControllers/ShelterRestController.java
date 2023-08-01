package org.wcci.adjrvirtualpet.restControllers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import org.wcci.adjrvirtualpet.entities.Cat;
import org.wcci.adjrvirtualpet.entities.Dog;
import org.wcci.adjrvirtualpet.services.ShelterService;

@RestController
public class ShelterRestController {
    public static final String LIST_ALL_STUDENTS = "listAllStudents";
    public static final String LIST_ALL_COURSES = "listAllCourses";
    public static final String COURSES_FOR_STUDENT = "coursesForStudent";

    final private ShelterService shelterService;

    public ShelterRestController(@Autowired ShelterService studentRepo) {
        this.shelterService = studentRepo;
    }

    // Return all students
    // curl -s http://localhost:8080/api/students
    // This *reads* from the database and is the "R" in CRUD
    @GetMapping("/api/students")
    public CollectionModel<EntityModel<Student>> getStudents() {
        List<EntityModel<Student>> students = this.shelterService.studentStream()
                .map(student -> EntityModel.of(student))
                .collect(Collectors.toList());
        return CollectionModel.of(students);
    }

    @GetMapping("/api/courses")
    public CollectionModel<EntityModel<Course>> getCourses() {
        List<EntityModel<Course>> courses = this.shelterService.courseStream()
                .map(course -> EntityModel.of(course))
                .collect(Collectors.toList());
        return CollectionModel.of(courses);
    }

    // Return one students
    // curl -s http://localhost:8080/api/students/1
    // This *reads* from the database and is the "R" in CRUD
    @GetMapping("/api/students/{student_id}")
    public EntityModel<Student> getStudent(@PathVariable final Long student_id) {
        final Student student = shelterService.findStudent(student_id);
        return EntityModel.of(student,
                linkTo(methodOn(ShelterRestController.class).getStudents()).withRel(LIST_ALL_STUDENTS),
                linkTo(methodOn(ShelterRestController.class).getStudentCourses(student_id)).withRel(COURSES_FOR_STUDENT),
                linkTo(methodOn(ShelterRestController.class).getStudent(student_id)).withSelfRel());
    }

    @GetMapping("/api/courses/{course_id}")
    public EntityModel<Course> getCourse(@PathVariable final Long course_id) {
        final Course course = shelterService.findCourse(course_id);
        return EntityModel.of(course,
                linkTo(methodOn(ShelterRestController.class).getCourses()).withRel(LIST_ALL_COURSES),
                linkTo(methodOn(ShelterRestController.class).getCourse(course_id)).withSelfRel());
    }

    // Add a student
    // curl -s -X POST http://localhost:8080/api/students -d '{"name":
    // "marshall2"}' -H 'Content-Type: application/json'
    // This *creates* a database record and is the "C" in CRUD
    @PostMapping("/api/students")
    public EntityModel<Student> newStudent(@RequestBody final Student student) {
        return EntityModel.of(shelterService.writeToDatabase(student),
                linkTo(methodOn(ShelterRestController.class).getStudent(student.studentID)).withSelfRel(),
                linkTo(methodOn(ShelterRestController.class).getStudentCourses(student.studentID))
                        .withRel(COURSES_FOR_STUDENT),
                linkTo(methodOn(ShelterRestController.class).getStudents()).withRel(LIST_ALL_STUDENTS));
    }

    @PostMapping("/api/courses")
    public EntityModel<Course> newCourse(@RequestBody final Course course) {
        return EntityModel.of(shelterService.writeToDatabase(course),
                linkTo(methodOn(ShelterRestController.class).getCourse(course.getCourseID())).withSelfRel(),
                linkTo(methodOn(ShelterRestController.class).getCourses()).withRel(LIST_ALL_COURSES));
    }

    // Remove a student
    // curl -s -X DELETE http://localhost:8080/api/students/51
    // This *delete* a database record and is the "D" in CRUD
    @DeleteMapping("/api/students/{student_id}")
    public void deleteStudent(@PathVariable long student_id) {
        shelterService.deleteById(student_id);
    }

    // Talk to the Product Owner before changing this
    @DeleteMapping("/api/students")
    public void deleteAllStudents() {
        throw new ResponseStatusException(HttpStatus.FORBIDDEN);
    }

    // Update a student
    // curl -s -X PUT http://localhost:8080/api/students -d '{"studentID": 102,
    // "name": "marshall2"}' -H 'Content-Type: application/json'
    // This *updates* a database record and is the "C" in CRUD
    @PutMapping("/api/students/{student_id}")
    public EntityModel<Student> updateStudent(
            @PathVariable final long student_id, // the name of the parameter (student_id) must match "{student_id}" in
                                                 // the line above
            @RequestBody final Student student) {
        // Update the student if that is the right thing to do
        final Student databaseStudent = shelterService.updateStudent(student, student_id);

        // Return the modified database student
        return EntityModel.of(databaseStudent,
                linkTo(methodOn(ShelterRestController.class).getStudent(student.studentID)).withSelfRel());
    }

    @PutMapping("/api/courses/{course_id}")
    public EntityModel<Course> updateCourse(
            @PathVariable final long course_id, // the name of the parameter (student_id) must match "{student_id}" in
                                                // the line above
            @RequestBody final Course course) {
        // Update the student if that is the right thing to do
        final Course databaseCourse = shelterService.updateCourse(course, course_id);

        // Return the modified database student
        return EntityModel.of(databaseCourse,
                linkTo(methodOn(ShelterRestController.class).getCourse(course.getCourseID())).withSelfRel());
    }

    @GetMapping("/api/students/{student_id}/courses")
    public CollectionModel<EntityModel<Course>> getStudentCourses(@PathVariable final Long student_id) {
        List<EntityModel<Course>> courses = this.shelterService.courseStreamForStudent(student_id)
                .map(course -> EntityModel.of(course))
                .collect(Collectors.toList());
        return CollectionModel.of(courses);
    }

    @PostMapping("/api/students/{student_id}/courses")
    public EntityModel<Course> newCourseForStudent(
            @PathVariable final Long student_id,
            @RequestBody final Course course) {
        shelterService.addCourseToStudent(student_id, course);
        return EntityModel.of(course);
    }
}
