package com.example.site_pl_99.controller;

import com.example.site_pl_99.dto.CourseDtoRequest;
import com.example.site_pl_99.excaption.BaseException;
import com.example.site_pl_99.mapper.CourseMapper;
import com.example.site_pl_99.service.AuthService;
import com.example.site_pl_99.service.CourseService;
import com.example.site_pl_99.utils.Internalization;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;
@Tag(name = "Контроллер Курсов")
@RestController
@RequestMapping("/api/courses")
@SecurityRequirement(name = "bearerAuth")
public class CourseController {
    private final CourseService courseService;
    private final AuthService authService;

    public CourseController(CourseService courseService, AuthService authService) {
        this.courseService = courseService;
        this.authService = authService;
    }
    @Operation(
            summary = "Добавить курс",
            description ="Создает новый курс для указанного работника, принимает данные курса в теле запроса"
    )
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Курс добавлен"),
    @ApiResponse(responseCode = "400",description = "Некорректный ввод"),
            @ApiResponse(responseCode = "403", description = "Недостаточно прав для созданий курса")
    })
    @PostMapping("/add-course")
    public ResponseEntity<?> addNewCourse(@Parameter(description = "Данные курса")@RequestBody CourseDtoRequest courseDtoRequest,
                                          @Parameter(description = "Id работника который создает курс") @RequestParam Long workerId) throws BaseException {
            return ResponseEntity.ok(CourseMapper.toCourseDtoResponse(courseService.saveCourse(courseDtoRequest, workerId, authService.getCurrentUser())));
    }
    @Operation(
            summary = "Вернуть курс по айди",
            description = "Возвращает курс по указанному Id "
    )
    @ApiResponses(value = {@ApiResponse(responseCode = "200",description = "Курс найден"),
            @ApiResponse(responseCode = "404", description = "Курс не найден"),
            @ApiResponse(responseCode = "400", description = "Некоректный ввод")})
    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<?> getCourseById(@Parameter(description = "Id курса") @PathVariable Long id) throws BaseException {
            return ResponseEntity.ok(CourseMapper.toCourseDtoResponse(courseService.getCourseId(id)));
    }
    @Operation(
            summary = "Возвращает лист курсов",
            description = "Возвращает все добавленные курсы ввиде списка"
    )
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Успешно")})
    @GetMapping("/get-all")
    public ResponseEntity<?> getAllCourses() throws BaseException {
            return ResponseEntity.ok(CourseMapper.toCourseDtoRequestList(courseService.getAllCourse()));
    }
    @Operation(
            summary = "Вернуть курс по Id пользователя",
            description = "Возвращет список всех созданных курсов указанным пользователем "
    )
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Успешно"),
    @ApiResponse(responseCode = "404", description = "Курс не найден"),
    @ApiResponse(responseCode = "400", description = "Некоректный ввод")})
    @GetMapping("/get-by-user/{id}")
    public ResponseEntity<?> getByUser(@Parameter(description = "Id пользовавателя")@PathVariable Long id) throws BaseException {
            return ResponseEntity.ok(CourseMapper.toCourseDtoRequestList(courseService.getCourseByUser(id)));
    }
}
