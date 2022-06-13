package com.etnetera.hr;

import com.etnetera.hr.data.JavaScriptFramework;
import com.etnetera.hr.service.JavaScriptFrameworkService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Class used for Spring Boot/MVC based tests.
 * 
 * @author Etnetera
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class JavaScriptFrameworkTests {

    public static final String ANGULAR = "Angular";
    @Autowired
    private MockMvc mvc;

    @Autowired
    private JavaScriptFrameworkService javaScriptFrameworkService;

    @Test
    public void createJavaScriptFramework() {
        // test creation of new framework
        final JavaScriptFramework angular = createFramework(ANGULAR);
        assertThat(angular).isNotNull();
        assertThat(angular.getName()).isEqualTo(ANGULAR);

        // test that framework with same name cannot be created
        final JavaScriptFramework framework = createFramework(ANGULAR);
        assertThat(framework).isNull();
    }


    @Test
    public void crateAndThenUpdateJavaScriptFramework() {
        final JavaScriptFramework angular = createFramework(ANGULAR);
        assertThat(angular).isNotNull();
        assertThat(angular.getName()).isEqualTo(ANGULAR);

        final JavaScriptFramework framework = javaScriptFrameworkService.updateFramework(ANGULAR, "1.0.1", BigDecimal.valueOf(6), null);
        assertThat(angular.getVersions().size()).isNotEqualTo(framework.getVersions().size());
        assertThat(angular.getHypeLevel()).isLessThan(framework.getHypeLevel());

    }

    @Test
    public void getAllFrameworks() {
        createFramework(ANGULAR);
        createFramework("React");

        final Iterable<JavaScriptFramework> frameworks = javaScriptFrameworkService.getFrameworks();
        assertThat(frameworks).isNotNull();
    }

    @Test
    public void deleteFramework() {
        createFramework(ANGULAR);

        javaScriptFrameworkService.deleteFramework(ANGULAR);
        assertThat(javaScriptFrameworkService.getFrameworks()).isNullOrEmpty();
    }

    private JavaScriptFramework createFramework(String frameworkName) {
        return javaScriptFrameworkService.createJavaScriptFramework(frameworkName, "1.0.0", BigDecimal.valueOf(5), null);
    }
}
